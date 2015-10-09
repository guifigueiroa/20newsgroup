package each.ppgsi.preprocessing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class DocumentList {
	
	private static DocumentList instance;
	private ArrayList<Document> documentList;
	private HashMap<String, Integer> corpusTermCount;
	private SortedSet<String> termsSet;
	private static final int MIN_DF = 3;
	
	public static DocumentList getInstance(){
		if(instance == null) {
			instance = new DocumentList();
		}

		return instance;
	}
	
	public ArrayList<Document> getDocuments(){
		System.out.println("Loading document files");
		if(documentList == null){
			FileManager manager = new FileManager();
			documentList = manager.getDocuments();
		}
		
		return documentList;
	}
	
	public int getDocumentCount(){
		return documentList.size();
	}
	
	private int countDocumentsWithTerm(String term){
		int count = 0;
		for(Document doc : documentList){
			if(doc.containsTerm(term))
				count++;
		}
		return count;
	}
	
	public SortedSet<String> getTerms(){
		System.out.println("Creating term set from all documents");
		termsSet = new TreeSet<String>();
		for(Document doc : documentList) {
			termsSet.addAll(doc.getAllTerms());
		}
		return termsSet;
	}
	
	public int getCorpusTermCount(String term){
		if(corpusTermCount.containsKey(term)){
			return corpusTermCount.get(term);
		} else {
			return 0;
		}
	}
	
	public void deleteLessFrequentTerms(){
		System.out.println("Deleting not chosen terms from documents");
		
		for(Document doc : documentList){
			Iterator<Map.Entry<String,Integer>> it = doc.getPreProcessedContent().entrySet().iterator();
			while(it.hasNext()){
				Map.Entry<String, Integer> pair = it.next();
				if(!termsSet.contains(pair.getKey()))
					it.remove();
			}
		}
	}
	
	public void generateCorpusTermCountHash(){
		System.out.println("Generating hash of term count from all documents");
		
		corpusTermCount = new HashMap<String, Integer>();
		Iterator<String> it = getTerms().iterator();
		while(it.hasNext()){
			String term = it.next();
			int DF = countDocumentsWithTerm(term);
			if(DF > MIN_DF) {
				corpusTermCount.put(term, DF);
			} else {
				it.remove(); // Remove less frequent words
			}
		}
		System.out.println("Terms being used: " + corpusTermCount.size());
	}
}
