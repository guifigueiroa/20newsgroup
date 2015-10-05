package each.ppgsi.nlp;

import java.util.HashMap;

public class TFIDF {

	private static double TF(Document doc, String term) {
		HashMap<String, Integer> terms = doc.getPreProcessedContent();
		
		return ((double) terms.get(term) / doc.getWordCount());
	}
	
	private static double IDF(String term) {
		DocumentList list = DocumentList.getInstance();
		return (double) Math.log10(list.getDocumentCount() / list.getCorpusTermCount(term));
	}
	
	public static double calculateTFIDF(Document doc, String term){
		return TF(doc, term) * IDF(term);
	}
}
