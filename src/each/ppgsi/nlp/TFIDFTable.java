package each.ppgsi.nlp;

import java.util.Set;

public class TFIDFTable extends WordCountTable{
	
	public TFIDFTable(int documentCount){
		super(documentCount);
	}
	
	public void merge(WordCountTable wc, int documentId){
		Set<String> terms = wc.getTerms();
		for(String term : terms){
			addTerm(term, wc.getTermCount(term), documentId);
		}
	}
}
