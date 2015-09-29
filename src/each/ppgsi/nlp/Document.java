package each.ppgsi.nlp;

public class Document {

	private int documentId;
	private String newsGroup;
	private String content;
	
	public void setDocumentId(int documentId){
		this.documentId = documentId;
	}
	
	public void setNewsGroup(String newsGroup){
		this.newsGroup = newsGroup;
	}
	
	public void setContenct(String content){
		this.content = content;
	}
	
	public int getDocumentId(){
		return documentId;
	}
	
	public String getNewsGroup(){
		return newsGroup;
	}
	
	public String getContent(){
		return content;
	}
}
