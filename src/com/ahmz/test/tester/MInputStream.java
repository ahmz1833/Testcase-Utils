package com.ahmz.test.tester;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class MInputStream extends InputStream
{
	private final InputStream originalInputStream;
	private final MPrintStream eavesdropPrintStream;
	private int breakPointLineNumber = -1;
	
	public MInputStream(InputStream originalInputStream, MPrintStream eavesdrop)
	{
		this.originalInputStream = originalInputStream;
		this.eavesdropPrintStream = eavesdrop;
	}
	
	public void breakpoint(int lineNumber)
	{
		this.breakPointLineNumber = lineNumber;
	}
	
	@Override
	public int read() throws IOException
	{
		int nextByte = originalInputStream.read();
		if (nextByte != -1)
			eavesdropPrintStream.print((char) nextByte); // Print the byte being read
		if (breakPointLineNumber != -1 && eavesdropPrintStream.getLineNumber() >= breakPointLineNumber)
			System.out.flush(); // The Command is not important! Put breakpoint on this Line!!!!
		return nextByte;
	}
	
	@Override
	public void close() throws IOException
	{
		originalInputStream.close();
	}
	
	public String lastReadLine()
	{
		return eavesdropPrintStream.lastPrintedLine();
	}
	
	public int lastLineNumber()
	{
		return eavesdropPrintStream.getLineNumber();
	}
}
