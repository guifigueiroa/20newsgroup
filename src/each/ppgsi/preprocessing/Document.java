package each.ppgsi.preprocessing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Document {

	private String newsGroup;
	private String content;
	private HashMap<String, Integer> preProcessedContent;
	private static TreeSet<String> newsGroupSet = new TreeSet<String>();
	
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
		newsGroupSet.add(newsGroup);
		this.newsGroup = newsGroup;
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
		return preProcessedContent.keySet().size();
	}
	
	public boolean containsTerm(String term){
		return preProcessedContent.containsKey(term);
	}
	
	public String getBinaryNewsGroup(String separator){
		StringBuilder binary = new StringBuilder();
		Iterator<String> it = newsGroupSet.iterator();
		while(it.hasNext()){
			binary.append(separator);
			if(it.next().equals(newsGroup)){
				binary.append("1");
			} else {
				binary.append("0");
			}
			
		}
		return binary.toString();
	}
}
