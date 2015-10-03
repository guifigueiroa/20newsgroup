package each.ppgsi.nlp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

	private static ArrayList<Document> documents;
	private static final String FILES_FOLDER = "./20_newsgroups";
	private static final String STOP_WORDS = "stopwords.txt";

	private void readDocuments(File folder) {
		for (File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory() ) {
				readDocuments(fileEntry);
			} else if(!fileEntry.getName().startsWith(".")){
				documents.add(getDocumentContent(fileEntry));
			}
		}
	}

	public ArrayList<Document> getDocuments() {
		documents = new ArrayList<Document>();
		File folder = new File(FILES_FOLDER);
		readDocuments(folder);
		return documents;
	}

	public Document getDocumentContent(File file){
		Document document = new Document();
		document.setNewsGroup(file.getPath().split("/")[2]);
		
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);
			fis.close();
			
			HeaderHandler header = new HeaderHandler(new String(data));
			String content = header.removeHeader();
			
			document.setContent(content);
		} catch (IOException e) {
			System.err.println("Erro ao ler arquivo.\n" + e.getMessage());
		}
		
		return document;
	}

	public static ArrayList<String> getStopWords() {
		ArrayList<String> stopWords = new ArrayList<String>();

		FileReader file;
		try {
			file = new FileReader(STOP_WORDS);

			BufferedReader reader = new BufferedReader(file);

			String word = reader.readLine();
			do {
				stopWords.add(word);

				word = reader.readLine();

			} while (word != null);

			reader.close();
		} catch (FileNotFoundException e) {
			System.err.println("Erro ao ler arquivo de stop words\n" + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro ao ler arquivo de stop words\n" + e.getMessage());
		}

		return stopWords;
	}
}
