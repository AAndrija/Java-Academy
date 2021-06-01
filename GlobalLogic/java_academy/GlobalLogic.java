package java_academy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GlobalLogic {
	Set<Character> target = new HashSet<Character>();
	Set<Character> special_chars = new HashSet<Character>();
	String special_string = new String(" !\"#$%&'()*+,-./:;<=>?@\\^_`{|}[]~;");
	Input input;
	int total = 0;
	int total_targets = 0;
	Map<Word,Integer> result = new HashMap<Word,Integer>();
	String outputPath;
	
	public GlobalLogic(String target, String input, String outputPath) {
		String lowercase_input = input.toLowerCase();
		String lowercase_target = target.toLowerCase();
		for(int i=0; i<target.length(); i++) {
			if(lowercase_target.charAt(i)>='a' && lowercase_target.charAt(i)<='z'){
				this.target.add(lowercase_target.charAt(i));
			}
		}
		for(int i=0; i<special_string.length();i++) {
			this.special_chars.add(special_string.charAt(i));
		}
		this.input = new Input(lowercase_input, special_chars, this.target);
		this.outputPath = outputPath;
	}

	public Set<Character> getInput() {
		return target;
	}

	public void setInput(Set<Character> target) {
		this.target = target;
	}
	
	public void addSpecial(char c) {
		special_chars.add(c);
	}
	
	public void removeSpecial(char c) {
		special_chars.remove(c);
	}
	
	public void addTarget(char c) {
		target.add(c);
	}
	
	public void removeTarget(char c) {
		target.remove(c);
	}
	
	public LinkedHashMap<Word, Integer> sortByValue() {
		List<Map.Entry<Word, Integer>> list = new ArrayList<>(result.entrySet());
		list.sort(new ValueKeyComparator<Word,Integer>());
		LinkedHashMap<Word, Integer> sortedMap = new LinkedHashMap<>();
      	list.forEach(e -> sortedMap.put(e.getKey(), e.getValue()));
        return sortedMap;
    }
	
	public void theDude() {
		total_targets = 0;
		total = 0;
		int target_count = 0;
		result.clear();
		for(int k=0; k<input.getWords().size();k++) {
			Word currentWordStat = input.getWordStatistics().get(k);
			target_count = 0;
			for(int i=0;i<input.getWords().get(k).length();i++) {
				if(target.contains(input.getWords().get(k).charAt(i))) {
					target_count++;
					total_targets++;
				}
				total++;
			}
			if(result.containsKey(currentWordStat)) {
				result.put(currentWordStat, result.get(currentWordStat)+target_count);
			}
			else {
				result.put(currentWordStat, target_count);
			}
		}	
	}
	
	public void Report() throws IOException {
		File outputFile = new File(outputPath);
		if(!outputFile.exists()) {
			outputFile.createNewFile();
		}
		FileWriter writer = new FileWriter(outputPath);
		LinkedHashMap<Word, Integer> sortedResult = sortByValue();
		float frequency;
		StringBuilder sb = new StringBuilder();
		for(Map.Entry<Word, Integer> entry: sortedResult.entrySet()) {
			sb.setLength(0);
			for(char c: entry.getKey().getCharacters()) {
				sb.append(c + ", ");
			}
			sb.delete(sb.length()-2, sb.length());
			frequency = (float)entry.getValue()/(float)total_targets;
			writer.write("{(" + sb.toString() + "), " + entry.getKey().getLength() + "} = " + String.format("%.2f",Math.round(frequency * 100.0) / 100.0) + " (" + entry.getValue() + "/" + total_targets + ")\n");
			System.out.println("{(" + sb.toString() + "), " + entry.getKey().getLength() + "} = " + String.format("%.2f",Math.round(frequency * 100.0) / 100.0) + " (" + entry.getValue() + "/" + total_targets + ")");
		}
		frequency = (float)total_targets/(float)total;
		System.out.println("TOTAL Frequency: " + Math.round(frequency * 100.0) / 100.0 + "(" + total_targets + "/" + total + ")");
		writer.write("TOTAL Frequency: " + Math.round(frequency * 100.0) / 100.0 + "(" + total_targets + "/" + total + ")\n");
		writer.close();
	}
	
	
}
