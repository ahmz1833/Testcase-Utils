import com.ahmz.test.tester.MTester;
import com.ahmz.test.tester.Scanner;  // My Scanner import is necessary

import java.io.IOException;

public class Main
{
	
	public static void main(String[] args)
	{
		/* TODO: Attention: Only one of usages below should be used */
		
		
//		// Usage 1: For Testing Code with a testcase
//		try {
//			MTester.initTestcaseConsumer("/home/tests_folder/", "in/input1.txt", "out/output1.txt", "myOut/myOut1.txt");
//		} catch (Exception e) {
//			// This case occurs when code is judging in quera
//		}
		
		
		
//		// Usage 2: For generating an output for a testcase using accepted code
//		try {
//			MTester.initOutputGenerator("/home/tests_folder", "in/input1.txt", "myOut/output1.txt");
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}



		// Usage 3: For creating a test case using accepted code
		try {
			MTester.initTestCaseProducer("mytests/", "in/input1.txt", "out/output1.txt");
		} catch (IOException e) {
			// This case occurs when failed to create files
			throw new RuntimeException(e);
		}
		
		
		
		// TODO: Main Program (just, using com.ahmz.test.tester.Scanner instead of java.util.Scanner)
		Menu.run(new Scanner(System.in));
	}
}
