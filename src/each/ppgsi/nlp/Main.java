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
			preProc.preProcessDocument(docArray.get(i));
		}
		
		for(Document doc : docArray){
			System.out.println("Getting words from doc " + doc.getDocumentId());
			for(String term : doc.getPreProcessedContent()){
				tfidf.addTerm(term);
			}
		}
		
		tfidf.generateTable();
		
		for(Document doc : docArray){
			System.out.println("Counting words from doc " + doc.getDocumentId());
			for(String term : doc.getPreProcessedContent()){
				tfidf.increaseTermCount(term, doc.getDocumentId());
			}
		}
		System.out.println("End");
	}

	
}
