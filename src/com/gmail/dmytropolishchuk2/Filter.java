package com.gmail.dmytropolishchuk2;

import java.io.*;

public class Filter implements FileFilter {
	
	private String[] arr;

	public Filter(String...arr) {
		super();
		this.arr = arr;
	}

		private boolean check(String ext) {
		for (String stringExt : arr) {
			if (stringExt.equals(ext)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean accept(File pathname) {
		int pointerIndex = pathname.getName().lastIndexOf(".");
		if (pointerIndex == -1) {
			return false;
		}
		String ext = pathname.getName().substring(pointerIndex + 1);
		return check(ext);
	}
}