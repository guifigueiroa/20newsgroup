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
		PrintWriter tfidfOut = FileManager.createOutputFile("tfidf.txt");
		PrintWriter tfidfNormOut = FileManager.createOutputFile("tfidfNormalizado.txt");
		
		for(Document doc : documents){
			double[] tfidf = new double[terms.size()];
			int j = 0;
			for(String term : terms){
				
				// calculate tf-idf for each term
				if(doc.containsTerm(term)){
					tfidf[j] = TFIDF.calculateTFIDF(doc, term);
				} else {
					tfidf[j] = 0;
				}
				
				if(tfidf[j] == 0)
					tfidfOut.print(0 + ",");
				else
					tfidfOut.print(df.format(tfidf[j]) + ",");
				
				j++;
			}
			
			// Calculate division value for normalizing
			double normalizeFactor = 0;
			for(j = 0; j < terms.size(); j++){
				normalizeFactor += (tfidf[j] * tfidf[j]);
			}
			normalizeFactor = Math.sqrt(normalizeFactor);
			
			// Calculate normalized tf-idf
			for(j = 0; j < terms.size(); j++){
				double tfidfNorm = (tfidf[j]/normalizeFactor);
				if(tfidfNorm == 0)
					tfidfNormOut.print(0 + ",");
				else
					tfidfNormOut.print(df.format(tfidfNorm) + ",");
			}
			
			tfidfNormOut.println();
			tfidfOut.println();
		}
		tfidfOut.close();
		tfidfNormOut.close();
		
		System.out.println("Qtd de termos: " + terms.size());
	}

	
}
