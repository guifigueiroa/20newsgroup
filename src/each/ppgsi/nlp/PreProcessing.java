package each.ppgsi.nlp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.tartarus.snowball.ext.englishStemmer;

public class PreProcessing {

	private ArrayList<String> stopWords;
	
	public PreProcessing(){
		stopWords = FileManager.getStopWords();
	}
	
	public void preProcessDocument(Document document){
		String content = document.getContent();
		
		// Remove upper cases
		content = content.toLowerCase();
		
		// Get words without digits, punctuation
		String[] tokenizedContent = content.split("[^a-z']+");
		HashMap<String, Integer> documentTerms = new HashMap<String, Integer>();
		
		int wordCount = 0;
		
		for(int i = 0; i < tokenizedContent.length; i++){
			
			String word = tokenizedContent[i];
			
			// if the word is not a stop word
			if(!isStopWord(word)) {
				
				wordCount++;
				
				// Stem the word
				englishStemmer stem = new englishStemmer();
				stem.setCurrent(word);
				if(stem.stem()) {
					tokenizedContent[i] = stem.getCurrent();
					word = tokenizedContent[i];
				}
				
				// Add to the hash
				if(documentTerms.containsKey(word)) {
					int count = documentTerms.get(word);
					documentTerms.put(word, count+1);
				} else {
					documentTerms.put(word, 1);
				}
			}

		}
		
		// Remove less frequent words (?) Can't be here because words are used in TDF
		/*Iterator<Map.Entry<String,Integer>> it = documentTerms.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, Integer> pair = it.next();
			if(pair.getValue() < 3)
				it.remove();
		}*/
		
		document.setContent(null); // clear variable to save memory
		document.setPreProcessedContent(documentTerms);
		document.setWordCount(wordCount);
		
		
	}
	
	private boolean isStopWord(String word){
		return (Collections.binarySearch(stopWords, word) >= 0) ? true : false;
	}
}
