package com.ahmz.test.tester;

import java.io.IOException;
import java.io.InputStream;

public class Scanner
{
	private final InputStream inputStream;
	
	public Scanner(InputStream is)
	{
		this.inputStream = is;
	}
	
	public String nextLine()
	{
		int read;
		StringBuilder buf = new StringBuilder();
		try {
			while ((read = inputStream.read()) != '\n' && read != -1) buf.append((char) read);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return String.valueOf(buf);
	}
	
	public void close()
	{
		try {
			inputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
