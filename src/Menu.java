import com.ahmz.test.tester.Scanner; // My Scanner import is necessary

public class Menu
{
	public static void run(Scanner scanner)
	{
		while(true)
		{
			String command = scanner.nextLine().trim();
			switch (command)
			{
				case "salam":
					System.out.println("Salam va Doroud va Adab!");
					break;
				case "khodahafez":
					System.out.println("Be Omide Didaar!");
					break;
				case "exit":
					System.out.println("GoodBye!");
					return;
			}
		}
	}
}
