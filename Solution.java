import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int qtnNumber = Integer.MAX_VALUE;
		String input = new String();
		boolean exit=false;
		do {
			System.out.print("Please enter question number: ");
			if (sc.hasNextInt()) {
				qtnNumber = sc.nextInt();
			} else {
				System.out.println("Please enter an integer from 0 to 4");
				sc.next(); continue;
			}

			switch (qtnNumber) {
			case 1:
				System.out.print("Please enter a word: ");
				input = sc.next();
				//question1(input);
				break;
			case 2:
				System.out.print("Please enter a word: ");
				input = sc.next();
				//question2(input);
				break;
			case 3:
				System.out.print("Please enter a number: ");
				input = sc.next();
				//question3(input);
				break;
			case 4:
				System.out.print("Please enter a number: ");
				input = sc.next();
				//question4(input);
				break;
			case 0:
				exit = true;
				break;
			default:
				System.out.println("Please enter an integer from 0 to 4");
				continue;
			}
		} while (!exit);
		sc.close();
	}
}