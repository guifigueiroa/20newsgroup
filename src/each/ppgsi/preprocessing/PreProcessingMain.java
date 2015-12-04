package each.ppgsi.preprocessing;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.SortedSet;

public class PreProcessingMain {

	private static final String outputSeparator = ",";
	
	public static void main(String args[]) {
		// Load documents
		ArrayList<Document> documents = DocumentList.getInstance().getDocuments();
		
		// Preprocess documents
		System.out.println("Preprocessing documents");
		for(int i = 0; i < documents.size(); i++){
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
		//createSparseOutput(documents, terms);
		//createClassOutput(documents);
		createTermsOutput(terms);
		
		System.out.println("Process ended!");
	}
	
	private static void createOutput(ArrayList<Document> documents, SortedSet<String> terms){
		System.out.println("Generating output files");
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
					tfidfOut.print(df.format(tfidf[j]));
				} else {
					tfidf[j] = 0;
				}
				
				tfidfOut.print(outputSeparator);
				
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
				if(tfidfNorm != 0) {
					tfidfNormOut.print(df.format(tfidfNorm));
				}
				tfidfNormOut.print(outputSeparator);
			}
			
			// Create document with classes
			//tfidfOut.print(doc.getBinaryNewsGroup(outputSeparator));
			//tfidfNormOut.print(doc.getBinaryNewsGroup(outputSeparator));
			
			tfidfNormOut.println();
			tfidfOut.println();
		}
		tfidfOut.close();
		tfidfNormOut.close();
	}

	private static void createSparseOutput(ArrayList<Document> documents, SortedSet<String> terms){
		System.out.println("Generating output files");
		DecimalFormat df = new DecimalFormat("0.000");
		PrintWriter tfidfOut = FileManager.createOutputFile("tfidfSparse.txt");
		PrintWriter tfidfNormOut = FileManager.createOutputFile("tfidfNormalizadoSparse.txt");
		
		for(int i = 0; i < documents.size(); i++){
			Document doc = documents.get(i);
			
			double[] tfidf = new double[terms.size()];
			int j = 0;
			for(String term : terms){
				
				// calculate tf-idf for each term
				if(doc.containsTerm(term)){
					tfidf[j] = TFIDF.calculateTFIDF(doc, term);
					tfidfOut.println((i+1) + "\t" + (j+1) + "\t" + df.format(tfidf[j]));
				} else {
					tfidf[j] = 0;
				}
				
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
				if(tfidfNorm != 0 && Double.isFinite(tfidfNorm)) {
					tfidfNormOut.println((i+1) + "\t" + (j+1) + "\t" + df.format(tfidfNorm));
				}
			}
		}
		tfidfOut.close();
		tfidfNormOut.close();
	}
	
	private static void createClassOutputBinary(ArrayList<Document> documents){
		PrintWriter classOut = FileManager.createOutputFile("classesBinary.txt");
		for(Document doc : documents){
			classOut.println(doc.getBinaryNewsGroup(outputSeparator));
		}
		classOut.close();
	}
	
	private static void createClassOutput(ArrayList<Document> documents){
		PrintWriter classOut = FileManager.createOutputFile("classes.txt");
		for(Document doc : documents){
			classOut.println(doc.getNumberNewsGroup());
		}
		classOut.close();
	}
	
	private static void createTermsOutput(SortedSet<String> terms){
		PrintWriter termOut = FileManager.createOutputFile("terms.txt");
		int i = 0;
		for(String term : terms){
			termOut.println(i + " - " + term);
			i++;
		}
		termOut.close();
	}
}
