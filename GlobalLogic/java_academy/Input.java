package java_academy;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


//this class stores text that is given as an input
public class Input {
	//list of valid words
	List<String> words = new ArrayList<String>();
	//valid words statistics
	List<Word> wordStatistics = new ArrayList<Word>();
	
	public Input(String input, Set<Character> special_chars, Set<Character> targets) {
		StringBuilder sb;
		String cleaned;
		String[] words = input.split("\\s+");
		
		for(int i=0; i<words.length;i++) {
			if(words[i].matches(".*[a-z].*") == true || words[i].matches(".*[A-Z].*") == true) {
				sb = new StringBuilder(words[i]);
				
				for(int j=0; j<words[i].length();j++) {
					if(special_chars.contains(words[i].charAt(j))) {
						sb.deleteCharAt(j--);
						words[i] = sb.toString();
					}
				}
				
				cleaned = sb.toString();
				
				this.words.add(cleaned);
				this.wordStatistics.add(new Word(cleaned, targets));
			}
				
		}
	}

	public List<Word> getWordStatistics() {
		return this.wordStatistics;
	}

	public void setWordStatistics(List<Word> wordStatistics) {
		this.wordStatistics = wordStatistics;
	}

	public List<String> getWords() {
		return words;
	}

	public void setWords(List<String> words) {
		this.words = words;
	}
	
	
}
