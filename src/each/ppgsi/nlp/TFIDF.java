package each.ppgsi.nlp;

import java.util.HashMap;

public class TFIDF {

	private static double TF(Document doc, String term) {
		HashMap<String, Integer> terms = doc.getPreProcessedContent();
		
		return ((double) terms.get(term) / doc.getWordCount());
	}
	
	private static double IDF(String term) {
		return (double) Math.log(DocumentList.getDocumentCount() / DocumentList.countDocumentsWithTerm(term));
	}
	
	public static double calculateTFIDF(Document doc, String term){
		return TF(doc, term) * IDF(term);
	}
}
