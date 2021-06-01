package java_academy;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

//sort map by value then by key
public class ValueKeyComparator<K extends Comparable<? super K>,
								V extends Comparable<? super V>>
								implements Comparator<Map.Entry<K, V>> {

	@Override
	public int compare(Entry<K, V> o1, Entry<K, V> o2) {
		int cmp1 = o1.getValue().compareTo(o2.getValue());
		
        if (cmp1 != 0) {
            return cmp1;
        }
        else {
            return o1.getKey().compareTo(o2.getKey());
        }
	}
}
