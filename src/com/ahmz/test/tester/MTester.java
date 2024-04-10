package com.ahmz.test.tester;

import java.io.*;

public class MTester
{
	private static final InputStream actualStdIn = System.in;
	private static final PrintStream actualStdOut = System.out;
	
	public static void initTestcaseConsumer(String parent, String in, String trueOut, String testOut) throws Exception
	{
		InputStream fileInput = new FileInputStream(new File(parent, in));
		InputStream trueOutInput = trueOut != null ? new FileInputStream(new File(parent, trueOut)) : null;
		
		MPrintStream eavesdrop = new MPrintStream(actualStdOut, "\u001B[33m%s\u001B[0m\n");
		MInputStream virtualStdIn = new MInputStream(fileInput, eavesdrop);
		
		FileOutputStream out = getFileOutputStream(parent, testOut);
		MPrintStream virtualStdOut = new MPrintStream(out, actualStdOut, "\u001B[36m%s\u001B[0m\n");
		
		if (trueOutInput != null)
			virtualStdOut.setChecker(new java.util.Scanner(trueOutInput), virtualStdIn,
					"\u001B[33m%s (Line %d of Input)\u001B[0m\n",
					"\u001B[31m%s (Line %d of Output)\u001B[0m\n");
		
		System.setIn(virtualStdIn);
		System.setOut(virtualStdOut);
	}
	
	public static void initOutputGenerator(String parent, String in, String out) throws Exception
	{
		initTestcaseConsumer(parent, in, null, out);
	}
	
	public static void initTestCaseProducer(String parent, String inFileToBeWritten, String outFileToBeWritten) throws IOException
	{
		System.setIn(new MInputStream(actualStdIn, new MPrintStream(getFileOutputStream(parent, inFileToBeWritten), "%s\n")));
		System.setOut(new MPrintStream(getFileOutputStream(parent, outFileToBeWritten), actualStdOut, "\u001B[92m%s\u001B[0m\n"));
	}
	
	private static FileOutputStream getFileOutputStream(String parent, String testOut) throws IOException
	{
		File testOutFile = new File(parent, testOut);
		testOutFile.getParentFile().mkdirs();
		testOutFile.createNewFile();
		if (!testOutFile.exists())
			throw new IOException("Failed to create test output File");
		FileOutputStream out = new FileOutputStream(testOutFile);
		return out;
	}
}
