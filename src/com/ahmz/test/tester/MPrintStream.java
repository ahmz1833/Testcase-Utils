package com.ahmz.test.tester;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.Scanner;

public class MPrintStream extends PrintStream
{
	private final PrintStream eavesdropPrintStream;
	private final String eachLineformat;
	private final boolean printToOriginalOut;
	private MInputStream programInput;
	private String wrongLineFormat, wrongedCommandFormat;
	private Scanner sc;
	private String currentLine = "";
	private int outputLineCnt = 0;
	private String lastPrintedLine;
	private int breakPointLineNumber = -1;
	
	public MPrintStream(OutputStream out, OutputStream eavesdrop, String eachLineformat)
	{
		super(out);
		this.eavesdropPrintStream = new PrintStream(eavesdrop);
		this.eachLineformat = eachLineformat;
		this.printToOriginalOut = true;
	}
	
	public MPrintStream(OutputStream eavesdrop, String eachLineformat)
	{
		super(eavesdrop);
		this.eavesdropPrintStream = new PrintStream(eavesdrop);
		this.eachLineformat = eachLineformat;
		this.printToOriginalOut = false;
	}
	
	public void setChecker(Scanner checkerScanner, MInputStream programInput, String wrongedCommandFormat, String wrongLineFormat)
	{
		this.sc = checkerScanner;
		this.wrongLineFormat = wrongLineFormat;
		this.wrongedCommandFormat = wrongedCommandFormat;
		this.programInput = programInput;
	}
	
	private void printEavesdrop(String format, String line, int number)
	{
		String s = format.replace("%s", line);
		if (s.contains("%")) s = String.format(Locale.ENGLISH, s, number);
		eavesdropPrintStream.print(s);
	}
	
	@Override
	public void println()
	{
		println("");
	}
	
	@Override
	public void println(String x)
	{
		print(x + "\n");
	}
	
	public void breakpoint(int lineNumber)
	{
		this.breakPointLineNumber = lineNumber;
	}
	
	@Override
	public void print(char c)
	{
		print(String.valueOf(c));
	}
	
	@Override
	public void print(String s)
	{
		if(printToOriginalOut) super.print(s);
		
		if (breakPointLineNumber != -1 && outputLineCnt >= breakPointLineNumber)
			System.out.flush();  // The Command is not important! Put breakpoint on this Line!!!!
		
		for (char c : s.toCharArray()) {
			currentLine += c;
			if (c == '\n') {
				String trueOutput = "", ourOutput;
				ourOutput = currentLine.trim();
				lastPrintedLine = ourOutput;
				outputLineCnt++;
				if (sc == null)
					printEavesdrop(eachLineformat, ourOutput, outputLineCnt);
				else {
					try {
						trueOutput = sc.nextLine().trim();
					} catch (Exception ignored) {}
					
					if (!ourOutput.equals(trueOutput)) {
						printEavesdrop(wrongedCommandFormat, programInput.lastReadLine(), programInput.lastLineNumber());
						printEavesdrop(wrongLineFormat, ourOutput, outputLineCnt);
						int i = 0; // Put breakPoint on this
					} else
						printEavesdrop(eachLineformat, ourOutput, outputLineCnt);
				}
				currentLine = "";
			}
		}
	}
	
	public int getLineNumber()
	{
		return outputLineCnt;
	}
	
	public String lastPrintedLine()
	{
		return lastPrintedLine;
	}
}
