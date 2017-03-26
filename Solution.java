import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Solution {
	private static final int[] CHAR_TO_KEY_TABLE = buildCharToKeyTable("abcdefghijklmnopqrstuvwxyz0123456789");
	private static final int[] CHAR_TO_NUMKEYPRESS_TABLE = buildCharToNumkeypressTable("abcdefghijklmnopqrstuvwxyz0123456789");
	private static final HashMap<Character,String> numToCharsMap = buildNumToCharsMap();
	private static final String DICT_FILENAME = "words.txt";
	private static final WordTrie dictionary = WordTrie.buildDict(DICT_FILENAME);
	
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
				question3(input);
				break;
			case 4:
				System.out.print("Please enter a number: ");
				input = sc.next();
				question4(input);
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
	
	//Param: a number
	//Return: all possible letter combinations the number could represent
	public static void question3(String input) {
		ArrayList<String> output = new ArrayList<String>();
		output.add("");
		
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i)=='0' || input.charAt(i)=='1') {
				continue;
			}
			String chars = numToCharsMap.get(input.charAt(i));
			for (int j = output.size(); j > 0; j--) {
				String tempOutput = output.get(0);
				output.remove(0); 
				for (int k = 0; k < chars.length(); k++) {
	                output.add(tempOutput + chars.charAt(k)); 
	            }
			}
		}
		
		output = getListDoubleQuotes(output);
		System.out.println(output.toString());
	}
	
	private static ArrayList<String> getListDoubleQuotes(ArrayList<String> list) {
		ArrayList<String> newList = new ArrayList<String>();
		
		for(String str : list) {
			StringBuilder builder = new StringBuilder();
			builder.append("\"");
			builder.append(str);
			builder.append("\"");
			newList.add(builder.toString());
		}
		return newList;	
	}
	
	private static HashMap<Character, String> buildNumToCharsMap() {
		HashMap<Character,String> map = new HashMap<Character,String>();
		map.put('2',"abc");
		map.put('3',"def");
		map.put('4',"ghi");
		map.put('5',"jkl");
		map.put('6',"mno");
		map.put('7',"pqrs");
		map.put('8',"tuv");
		map.put('9',"wxyz");
		return map;
	}
	
	//Param: a number
	//Return: all possible word combinations from Dictionary that the number could represent
	public static void question4(String input) {
		ArrayList<String> output = getMatches(input, dictionary);
		output = getListDoubleQuotes(output);
		System.out.println(output.toString());
	}
	
	private static ArrayList<String> getMatches(String input, WordTrie trie) {
		return getMatchesHelper(input, trie.root, 0);
	}
	
	private static ArrayList<String> getMatchesHelper(String input, WordTrie.Node node, int i) {
		ArrayList<String> output = new ArrayList<String>();
		if (i >= input.length()) {
			if (node.isWord) {
				output.add("");
			}
			return output;
		}
		String chars = numToCharsMap.get(input.charAt(i));
		for (char firstChar: chars.toCharArray()) {
			WordTrie.Node subNode = node.get(firstChar);
			if (subNode != null) {
				ArrayList<String> suffixes = getMatchesHelper(input, subNode, i + 1);
				for (String suffix : suffixes)
					output.add("" + firstChar + suffix);
			}
		}
		return output;
	}
}