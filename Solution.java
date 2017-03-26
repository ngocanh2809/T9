import java.util.*;

public class Solution {
	private static final int[] CHAR_TO_KEY_TABLE = buildCharToKeyTable("abcdefghijklmnopqrstuvwxyz0123456789");
	private static final int[] CHAR_TO_NUMKEYPRESS_TABLE = buildCharToNumkeypressTable("abcdefghijklmnopqrstuvwxyz0123456789");

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
				question1(input);
				break;
			case 2:
				System.out.print("Please enter a word: ");
				input = sc.next();
				question2(input);
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

	//Param: a word
	//Return: total number of key presses to spell the word
	public static void question1(String input) {
		int length = input.length();
		int numKeypress = 0;
		
		for (int i = 0; i < length; i++) {
			int key = lookUpNumkeypress(input.charAt(i));
			if (key > 0) {
				numKeypress += key;
	        } else {
	        	System.out.println("Unable to spell \"" + input + "\" using the given keypad");
	        	return;
	        }
		}

		System.out.println(numKeypress);
	}
	
	private static int getNumkeypressFromChar(char c) {
		switch (Character.toLowerCase(c)) {
			case 'a': case 'd': case 'g': case 'j': case 'm': case 'p': case 't': case 'w': case '0': case '1': 
				return 1;
			case 'b': case 'e': case 'h': case 'k': case 'n': case 'q': case 'u': case 'x':
				return 2;
			case 'c': case 'f': case 'i': case 'l': case 'o': case 'r': case 'v': case 'y':
				return 3;
			case 's': case 'z': case '2': case '3': case '4': case '5': case '6': case '8': 
				return 4;
			case '7': case '9':
				return 5;
		}
		return -1;
	}
	
	private static int[] buildCharToNumkeypressTable(String str) {
	    int max = -1;
	    for (char c : str.toCharArray()) {
	        max = Math.max(max, Math.max(Character.toLowerCase(c), Character.toUpperCase(c)));
	    }
	    int[] table = new int[max + 1];
	    Arrays.fill(table, -1);
	    for (char c : str.toCharArray()) {
	    	table[Character.toLowerCase(c)] = getNumkeypressFromChar(c);
	    	table[Character.toUpperCase(c)] = getNumkeypressFromChar(c);
	    }
	    return table;
	}
	
	private static int lookUpNumkeypress(char ch) {
	    return ch >= CHAR_TO_NUMKEYPRESS_TABLE.length ? -1 : CHAR_TO_NUMKEYPRESS_TABLE[ch];
}
	
	//Param: a word
	//Return: the number the word could represent
	public static void question2(String input) {
		int length = input.length();
		int[] output = new int[length];
		
		for (int i = 0; i < length; i++) {
			int key = lookUpKey(input.charAt(i));
			if (key >= 0) {
	            output[i] = key;
	        } else {
	        	System.out.println("Unable to represent \"" + input + "\" using the given keypad");
	        	return;
	        }
		}
		
		StringBuilder builder = new StringBuilder();
		for(int s : output) {
			builder.append(s);
		}
		System.out.println(builder.toString());
	}
	
	private static int[] buildCharToKeyTable(String str) {
	    int max = -1;
	    for (char c : str.toCharArray()) {
	        max = Math.max(max, Math.max(Character.toLowerCase(c), Character.toUpperCase(c)));
	    }
	    int[] table = new int[max + 1];
	    Arrays.fill(table, -1);
	    for (char c : str.toCharArray()) {
	    	table[Character.toLowerCase(c)] = getKeyFromChar(c);
	    	table[Character.toUpperCase(c)] = getKeyFromChar(c);
	    }
	    return table;
	}
	
	private static int lookUpKey(char ch) {
	    return ch >= CHAR_TO_KEY_TABLE.length ? -1 : CHAR_TO_KEY_TABLE[ch];
	}
	
	private static int getKeyFromChar(char c) {
	    switch (Character.toLowerCase(c)) {
	        case '0':
	            return 0;
	        case '1':
	            return 1;
	        case 'a': case 'b': case 'c': case '2':
	            return 2;
	        case 'd': case 'e': case 'f': case '3':
	            return 3;
	        case 'g': case 'h': case 'i': case '4':
	            return 4;
	        case 'j': case 'k': case 'l': case '5':
	            return 5;
	        case 'm': case 'n': case 'o': case '6':
	            return 6;
	        case 'p': case 'q': case 'r': case 's': case '7':
	            return 7;
	        case 't': case 'u': case 'v': case '8': 
	            return 8;
	        case 'w': case 'x': case 'y': case 'z': case '9':
	            return 9;
	    }
	    return -1;
	}
}