package com.gmail.dmytropolishchuk2;

import java.io.*;
import java.nio.file.Files;
import java.util.Date;

public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {

		File folderIn = new File("C://Users//�������//Desktop//JAVA");// ������� �������
		File folderOut = new File("C://Users//�������//Desktop//JAVA1");// �������� �������
		String extension = "docx";// ����������
		Filter mFF = new Filter(extension);// ������� ������ �� ����������

		if (!folderIn.isDirectory()) {// ���� ������� �� ���� ���������
			System.out.format("Error! The directory '%s' isn't exists", folderIn);// ������ ���������� �������������
																					// ���������
			return;
		}

		folderOut.mkdir();// ������� �������
		File[] fileArray = folderIn.listFiles(mFF);// �������� �������� �� ������ � �������� � ������ ��������
		if (fileArray.length == 0) {// ���� ����� � ������ ����������� �� �������
			System.out.println("No files to copy!");// ����� � �������
			return;
		}

		for (File file : fileArray) {// ��� ������� ������
			try {// �������
				File copy = new File(folderOut + "\\" + file.getName());// ���� ��� ����� � �������� ���
				copy(file, copy);// ����� ������ ����
				System.out.println("...\\" + file);// ������� ����� ���������� ������
			} catch (IOException e) {// ����� ������: ������� ������� ����� ����, ������� ��� ����������
				System.out.println("Error!");// ����� � �������
			}
		}
		System.out.format("All files with extension '%s' were copied to folder '%s'!\n ", extension, folderOut);// �����
																												// ����������
		// System.out.printf("%0$s %2$te %2$tB, %2$tY\n", "����:", new Date());
		System.out.printf("%s %te %<tB, %<tY\n", "�������:", new Date());// �����
		File textOne = new File("C://Users//�������//Desktop//JAVA//Java.txt");// ���� 1

		PrintWriter pw = new PrintWriter(System.out, true);//
		try {// �����������
			textOne.createNewFile();// ������� ����� ����
		} catch (IOException e) {// ����� ������: ������� ������� ����� ����, ������� ��� ����������
			pw.println("Error, textOne was not copied!");
		}

		File textTwo = new File("a.txt");

		try {
			textTwo.createNewFile();
		} catch (IOException e) {
			pw.println("Error, textTwo was not copied!");
		}
		copyFile(textOne, textTwo);// �������� �����
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
