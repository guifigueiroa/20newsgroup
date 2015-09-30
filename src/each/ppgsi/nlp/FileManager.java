package each.ppgsi.nlp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

	private ArrayList<Document> documents;
	private static final String FILES_FOLDER = "./20_newsgroups";
	
	public FileManager(){
		documents = new ArrayList<Document>();
	}
	
	private void readDocuments(File folder) {
		for (File fileEntry : folder.listFiles()) {
			//if(documents.size() > 10) break;
			if (fileEntry.isDirectory()) {
				readDocuments(fileEntry);
			} else {
				documents.add(getDocumentContent(fileEntry));
			}
		}
	}
	
	public ArrayList<Document> getDocuments(){
		File folder = new File(FILES_FOLDER);
		readDocuments(folder);
		return documents;
	}
	
	private Document getDocumentContent(File file){
		Document document = new Document();
		document.setNewsGroup(file.getPath().split("/")[2]);
		
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
			fis.close();
			
			
			document.setContenct(new String(data));
		} catch (IOException e) {
			System.err.println("Erro ao ler arquivo.\n" + e.getMessage());
		}
		
		return document;
	}
}
