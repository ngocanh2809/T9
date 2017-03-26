import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

class WordTrie {
	
	public Node root = new Node(false);
	public static class Node extends HashMap<Character, Node> {
		public boolean isWord;

		public Node(boolean isWord) {
			this.isWord = isWord;
		}
	}
	
	public WordTrie(List<String> dict) {
		for (String word : dict) {
			addWord(word);
		}
	}
	
	public void addWord(String word) {
		addWordHelper(root, word);
	}
	
	private void addWordHelper(Node node, String word) {
		if (word.length() == 0) {
			node.isWord = true;
			return;
		}
		else {
			char first = word.charAt(0);
			String rest = word.substring(1);
			if (!node.containsKey(first)) {
				node.put(first, new Node(false));
			}
			addWordHelper(node.get(first), rest);
		}
	}
	
	//Param: name of file containing all words
	//Return: a trie used for question 4
	public static WordTrie buildDict(String fileName) {
		try {
			List<String> dict = Files.readAllLines(Paths.get(fileName));
			return new WordTrie(dict);
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}