package each.ppgsi.nlp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String args[]) {
		
		ArrayList<Document> documents = DocumentList.getInstance();
		
		
		for(int i = 0; i < documents.size(); i++){
			System.out.println("Preprocessing document " + i);
			PreProcessing preProc = new PreProcessing();
			preProc.preProcessDocument(documents.get(i));
		}
		
		/*long startTime = System.currentTimeMillis();
		System.out.println("teste " + TFIDF.calculateTFIDF(documents.get(1), "atheism"));
		long endTime = System.currentTimeMillis();
		System.out.println("That took " + (endTime - startTime) + " milliseconds");*/
		
		Set<String> terms = new HashSet<String>();
		for(Document doc : documents) {
			terms.addAll(doc.getAllTerms());
		}
		
		System.out.println("Qtd de termos: " + terms.size());
	}

	
}
