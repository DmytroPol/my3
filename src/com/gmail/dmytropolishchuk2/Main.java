package com.gmail.dmytropolishchuk2;

import java.io.*;
import java.nio.file.Files;
import java.util.Date;

public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {

		File folderIn = new File("C://Users//Дмитрий//Desktop//JAVA");// входной каталог
		File folderOut = new File("C://Users//Дмитрий//Desktop//JAVA1");// выходной каталог
		String extension = "docx";// расширение
		Filter mFF = new Filter(extension);// создаем фильтр по расширению

		if (!folderIn.isDirectory()) {// если каталог не есть каталогом
			System.out.format("Error! The directory '%s' isn't exists", folderIn);// формат строкового представления
																					// аргумента
			return;
		}

		folderOut.mkdir();// создаем каталог
		File[] fileArray = folderIn.listFiles(mFF);// проходим массивом по файлам с фильтром в данном каталоге
		if (fileArray.length == 0) {// если файла с данным расширением не найдено
			System.out.println("No files to copy!");// вывод в консоль
			return;
		}

		for (File file : fileArray) {// для массива файлов
			try {// пробуем
				File copy = new File(folderOut + "\\" + file.getName());// файл для копии и получить имя
				copy(file, copy);// вызов метода ниже
				System.out.println("...\\" + file);// вывести имена полученных файлов
			} catch (IOException e) {// ловим ошибку: попытка создать новый файл, который уже существует
				System.out.println("Error!");// вывод в консоль
			}
		}
		System.out.format("All files with extension '%s' were copied to folder '%s'!\n ", extension, folderOut);// вывод
																												// результата
		// System.out.printf("%0$s %2$te %2$tB, %2$tY\n", "Дата:", new Date());
		System.out.printf("%s %te %<tB, %<tY\n", "Сегодня:", new Date());// шутка
		File textOne = new File("C://Users//Дмитрий//Desktop//JAVA//Java.txt");// файл 1

		PrintWriter pw = new PrintWriter(System.out, true);//
		try {// попробовать
			textOne.createNewFile();// создать новый файл
		} catch (IOException e) {// ловим ошибку: попытка создать новый файл, который уже существует
			pw.println("Error, textOne was not copied!");
		}

		File textTwo = new File("a.txt");

		try {
			textTwo.createNewFile();
		} catch (IOException e) {
			pw.println("Error, textTwo was not copied!");
		}
		copyFile(textOne, textTwo);// копируем файлы
		System.out.format("The file with extension txt was copied from '%s' to file '%s'!\n", textOne, textTwo);
	}

	public static void copy(File source, File dest) throws IOException {
		Files.copy(source.toPath(), dest.toPath());
	}

	public static void copyFile(File fileIn, File fileOut) throws IOException {
		if (fileIn == null || fileOut == null) {
			throw new IllegalArgumentException("Null File pointer");
		}
		try (InputStream in = new FileInputStream(fileIn); OutputStream out = new FileOutputStream(fileOut)) {
			byte[] buffer = new byte[1024 * 1024];
			int readByte = 0;
			for (; (readByte = in.read(buffer)) > 0;) {
				out.write(buffer, 0, readByte);
			}
		} catch (IOException e) {
			throw e;
		}
	}
}
