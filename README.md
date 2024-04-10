# Testcase Utils

Testcase Utils is a Java package designed to assist in testing Java programs by providing utilities for handling test cases, monitoring input/output streams, and generating test outputs.

## Features

- **Input Stream Monitoring:** Includes classes for intercepting and monitoring input streams (`MInputStream`) with support for setting breakpoints.
- **Output Stream Monitoring:** Provides classes for intercepting and monitoring output streams (`MPrintStream`) with support for comparing output with expected results.
- **Testcase Management:** Includes a class (`MTester`) for initializing test cases, generating test outputs, and creating test cases using accepted code.

## Installation
To use Testcase Utils in your Java project, you can copy its folder to your project src foler.

## Usage

The program includes three different usages. Only one usage should be used at a time, depending on the specific purpose:

- **Usage 1:** Testing code with a full-testcase (sample in/out provided). It initializes a test case consumer by providing the paths to input, expected output, and actual output files. It verifies each line of program output with expected true output, and pause with breakpoint when a mismatch occured.

    ***Add this code to first of your Main.java:*** 
    ```java
    // Usage 1: For Testing Code with a testcase
    try {
        MTester.initTestcaseConsumer("/home/tests_folder/", "in/input1.txt", "out/output1.txt", "myOut/myOut1.txt");
    } catch (Exception e) {
        // This occurs when code is judging in quera
        // Therefore, There is no need to remove or comment this section of code when uploading. 
    }
    ```

- **Usage 2:** Generating an output for a only-input-testcase using accepted code. It initializes an output generator by providing the paths to input and output files.

    ***Add this code to first of your Main.java:*** 
    ```java
    // Usage 2: For generating an output for a testcase using accepted code
    try {
        MTester.initOutputGenerator("/home/tests_folder", "in/input1.txt", "myOut/output1.txt");
    } catch (Exception e) {
        // This case occurs when failed to open input file or create output file
        // Therefore, There is no need to remove or comment this section of code when uploading.
    }
    ```

- **Usage 3:** Creating a full-testcase using accepted code. It initializes a test case producer by providing the paths to input and output files.

    ***Add this code to first of your Main.java:***
    ```java
    // Usage 3: For creating a test case using accepted code
    try {
        MTester.initTestCaseProducer("mytests/", "in/input1.txt", "out/output1.txt");
    } catch (IOException e) {
        // This case occurs when failed to create files
        throw new RuntimeException(e);
    }
    ```

.

***A Main.java Example is provided in this repository.***
# _Important Notes_
- **You must set breakpoint in Line 81 and 100 in `MPrintStream.java` and Line 31 in `MInputStream.java` in your IDE.**


- **You must use `com.ahmz.test.tester.Scanner` instead of `java.util.Scanner` everywhere in your project codes. It doesn't have any bad side effect on your project behavior, but it's necessary for using the TestCase Utils**


- **There is no conflict of this package with Quera judge! in Usage 1 & 2. you can use the package for Testing your code with your testcases, and upload your project to Quera without any need to comment or change the code.**