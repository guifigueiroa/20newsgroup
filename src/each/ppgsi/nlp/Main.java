package each.ppgsi.nlp;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.SortedSet;

public class Main {

	
	
	public static void main(String args[]) {
		// Load documents
		ArrayList<Document> documents = DocumentList.getInstance().getDocuments();
		
		// Preprocess documents
		for(int i = 0; i < documents.size(); i++){
			System.out.println("Preprocessing document " + i);
			PreProcessing preProc = new PreProcessing();
			preProc.preProcessDocument(documents.get(i));
		}
		
		// Generate Corpus Term count hash (DF)
		DocumentList.getInstance().generateCorpusTermCountHash();
		
		// Delete from documents less frequent terms (using DF)
		DocumentList.getInstance().deleteLessFrequentTerms();
		
		// Get set of all terms from documents
		SortedSet<String> terms = DocumentList.getInstance().getTerms();
		
		// Create output file
		DecimalFormat df = new DecimalFormat("0.000");
		PrintWriter output = FileManager.createOutputFile("tfidf.txt");
		for(Document doc : documents){
			for(String term : terms){
				if(doc.containsTerm(term)){
					output.print(df.format(TFIDF.calculateTFIDF(doc, term)) + ",");
				} else {
					output.print(0 + ",");
				}
			}
			output.println();
		}
		output.close();
		
		System.out.println("Qtd de termos: " + terms.size());
	}

	
}
