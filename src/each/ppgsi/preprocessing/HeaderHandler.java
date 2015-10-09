package each.ppgsi.preprocessing;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HeaderHandler {

	
	private final String linesRegex = "^Lines.\\s(.*)";
	private final String summaryRegex = "^Summary.\\s(.*)";
	private final String keywordRegex = "^Keywords.\\s(.*)";
	private final String subjectRegex = "^Subject.*:\\s(.*)";

	private String text;
	
	public HeaderHandler(String text){
		this.text = text;
	}
	
	public String removeHeader(){
		StringBuilder newText = new StringBuilder();
		
		String[] lines = text.split("\\r?\\n",-1);
		int startLine = lines.length - getLineCount();
		
		if(startLine < 0) startLine = 0;
		
		for(int i = startLine; i < lines.length; i++){
			if(newText.length() != 0)
				newText.append("\n");
			newText.append(lines[i]);	
		}
		
		newText.append("\n");
		newText.append(getSubject());
		newText.append("\n");
		newText.append(getSummary());
		newText.append("\n");
		newText.append(getKeywords());
		
		return newText.toString();
	}
	
	public static void main(String args[]){
		FileManager file = new FileManager();
		String content = file.getDocumentContent(new File("./20_newsgroups/talk.politics.mideast/76277")).getContent();
		System.out.println(content);
	}
	
	private int getLineCount(){
		Matcher m = Pattern.compile(linesRegex, Pattern.MULTILINE).matcher(text);
		if(m.find()) {
			
			if(m.group(1).equals("dog")) return 26; // rsrsrsrsrs
			return Integer.parseInt(m.group(1).trim()); 
		}
		return 0;
	}
	
	private String getSummary(){
		Matcher m = Pattern.compile(summaryRegex, Pattern.MULTILINE).matcher(text);
		if(m.find()) {
			return m.group(1); 
		}
		return "";
	}
	
	private String getSubject(){
		Matcher m = Pattern.compile(subjectRegex, Pattern.MULTILINE).matcher(text);
		if(m.find()) {
			return m.group(1); 
		}
		return "";
	}
	
	private String getKeywords(){
		Matcher m = Pattern.compile(keywordRegex, Pattern.MULTILINE).matcher(text);
		if(m.find()) {
			return m.group(1); 
		}
		return "";
	}
}
