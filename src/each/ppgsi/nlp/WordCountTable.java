package each.ppgsi.nlp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class WordCountTable {
	
	private HashMap<String, Integer> terms;
	private int[][] table;
	private int documentCount;
	private Document document;
	
	public WordCountTable(Document document){
		this(1);
		this.document = document;
	}
	
	public int getDocumentId(){
		return document.getDocumentId();
	}
	
	public WordCountTable(int documentCount){
		this.documentCount = documentCount;
		this.terms = new HashMap<String, Integer>();
	}
	
	public void addTerm(String term){
		if(!terms.containsKey(term)){
			terms.put(term, terms.keySet().size());
		}
	}
	
	public void countTerm(String term){
		int termId = terms.get(term);
		table[0][termId] += 1;
	}
	
	public void generateTable(){
		table = new int[documentCount][terms.keySet().size()];
	}
	
	public void setTermCount(String term, int documentId, int count) {
		int termId = terms.get(term);
		table[documentId][termId] = count;
	}
	
	public void setTermCount(String term, int count) {
		int termId = terms.get(term);
		table[0][termId] = count;
	}
	
	public int getTermCount(String term) {
		int termId = terms.get(term);
		return table[0][termId];
	}
	
	public void removeTermsByFreq(int termFreq){
		for(int i = 0; i < table[0].length; i++){
			if(table[0][i] < 3) {
				Iterator<Entry<String, Integer>> it = terms.entrySet().iterator();
				while(it.hasNext()) {
					Map.Entry pair = (Map.Entry)it.next();
					if(((int)pair.getValue()) == i){
						it.remove();
					}
				}
			}
		}
	}
	
	public Set<String> getTerms(){
		return terms.keySet();
	}
}
