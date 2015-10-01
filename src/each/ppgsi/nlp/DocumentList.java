package each.ppgsi.nlp;

import java.util.ArrayList;

public class DocumentList {

	private static ArrayList<Document> documentList;
	
	public static ArrayList<Document> getInstance(){
		if(documentList == null) {
			FileManager manager = new FileManager();
			documentList = manager.getDocuments();
		}

		return documentList;
	}
	
	public static int getDocumentCount(){
		return documentList.size();
	}
	
	public static int countDocumentsWithTerm(String term){
		int count = 0;
		for(Document doc : documentList){
			if(doc.containsTerm(term))
				count++;
		}
		return count;
	}
}
