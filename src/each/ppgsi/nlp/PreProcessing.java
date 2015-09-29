package each.ppgsi.nlp;

import org.tartarus.snowball.ext.englishStemmer;

public class PreProcessing {

	private final int MIN_TERM_FREQ = 3;
	
	public WordCountTable preProcessDocument(Document document){
		String content = document.getContent();
		
		// Remove upper cases
		content = content.toLowerCase();
		
		// Get words without digits, punctuation
		String[] tokenizedContent = content.split("[^a-z']+");
		
		// Stem the words
		for(int i = 0; i < tokenizedContent.length; i++){
			englishStemmer stem = new englishStemmer();
			stem.setCurrent(tokenizedContent[i]);
			if(stem.stem())
				tokenizedContent[i] = stem.getCurrent();
		}
		
		// Count terms
		WordCountTable wc = new WordCountTable();
		for(String term : tokenizedContent){
			wc.addTerm(term);
		}
		
		// Remove terms using Minimum Term Frequency
		wc.removeTermsByFreq(MIN_TERM_FREQ);
		
		return wc;
	}
}
