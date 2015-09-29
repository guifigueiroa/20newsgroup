package each.ppgsi.nlp;

import java.util.ArrayList;

public class Main {

	public static void main(String args[]) {
		FileManager docs = new FileManager();
		ArrayList<Document> docArray = docs.getDocuments();
		
		
		//System.out.println(docArray.get(1).getNewsGroup());
		
		TFIDFTable tfidf = new TFIDFTable(docArray.size());
		
		for(int i = 0; i < docArray.size(); i++){
			Document doc = docArray.get(i);
			doc.setDocumentId(i);
			
			PreProcessing preProc = new PreProcessing();
			WordCountTable wc = preProc.preProcessDocument(docArray.get(i));
			
			System.out.println("Merging doc " + i);
			tfidf.merge(wc, i);
		}
		
		System.out.println("Acabou");
	}

	
}
