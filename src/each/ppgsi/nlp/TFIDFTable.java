package each.ppgsi.nlp;

import java.util.HashMap;
import java.util.Set;

public class TFIDFTable {
	
	private HashMap<String, Integer> terms;
	private int[][] table;
	private int documentCount;
	
	public TFIDFTable(int documentCount){
		this.documentCount = documentCount;
		terms = new HashMap<String, Integer>();
	}
	
	public void addTerm(String term){
		if(!terms.containsKey(term)){
			terms.put(term, terms.keySet().size());
		}
	}
	
	public void generateTable(){
		System.out.println(terms.keySet().size());
		table = new int[documentCount][terms.keySet().size()];
	}
	
	public void increaseTermCount(String term, int documentId) {
		int termId = terms.get(term);
		table[documentId][termId]++;
	}
}
