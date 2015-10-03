package each.ppgsi.nlp;

import java.util.HashMap;
import java.util.Set;

public class Document {

	private String newsGroup;
	private String content;
	private HashMap<String, Integer> preProcessedContent;
	private int wordCount;
	
	public Set<String> getAllTerms(){
		return preProcessedContent.keySet();
	}
	
	public void setPreProcessedContent(HashMap<String, Integer> preProcessedContent){
		this.preProcessedContent = preProcessedContent;
	}
	
	public HashMap<String, Integer> getPreProcessedContent(){
		return preProcessedContent;
	}
	
	public void setNewsGroup(String newsGroup){
		this.newsGroup = newsGroup;
	}
	
	public void setWordCount(int wordCount){
		this.wordCount = wordCount;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getNewsGroup(){
		return newsGroup;
	}
	
	public String getContent(){
		return content;
	}
	
	public int getWordCount(){
		return wordCount;
	}
	
	public boolean containsTerm(String term){
		return preProcessedContent.containsKey(term);
	}
}
