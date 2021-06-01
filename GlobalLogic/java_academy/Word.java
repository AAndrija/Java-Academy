package java_academy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//word statistics (word length and set of targeted characters that match)
public class Word implements Comparable<Word>{
	int length;
	Set<Character> characters = new HashSet<Character>();
	
	public Word(String value, Set<Character> targets) {
		this.length = value.length();
		
		for(int i=0;i<value.length();i++) {
			if(targets.contains(value.charAt(i))) {
				characters.add(value.charAt(i));
			}
		}
	}
	
	public Set<Character> getCharacters(){
		return characters;
	}
	
	public int getLength() {
		return length;
	}
	
	@Override
	public int hashCode() {
		return Arrays.hashCode(new Object[] {characters, length});
	}
	
	@Override
	public boolean equals(Object o) {
		Word w = (Word) o;
		
		if(length == w.getLength() && characters.equals(w.getCharacters())) {
			return true;
		}
		else return false;
	}

	@Override
	public int compareTo(Word o) {
		return length - o.getLength();
	}
}

