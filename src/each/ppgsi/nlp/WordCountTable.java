package each.ppgsi.nlp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class WordCountTable {
	private HashMap<String, int[]> table;
	private int documentCount;
	
	public WordCountTable(int documentCount){
		table = new HashMap<String, int[]>();
		this.documentCount = documentCount;
	}
	
	public WordCountTable(){
		this(1);
	}
	
	public void addTerm(String term, int count, int documentId){
		if(!table.containsKey(term)) {
			table.put(term, new int[documentCount]);	
		}
		//System.out.println("Sum term: " + term + " " + table.get(term)[documentId]);
		table.get(term)[documentId] += count;
	}
	
	public void removeTermsByFreq(int minFreq){
		Iterator<Entry<String, int[]>> it = table.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
		
			if(((int[])pair.getValue())[0] < minFreq){
				it.remove();
			}
		}
	}
	
	public void addTerm(String term){
		addTerm(term, 1, 0);
	}
	
	public Set<String> getTerms(){
		return table.keySet();
	}
	
	public int getTermCount(String term){
		return table.get(term)[0];
	}
}
