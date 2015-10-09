package each.ppgsi.pca;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args){
		System.out.println("Reading file");
		double[][] data = readFile("tfidfNormalizado.txt");
		
		System.out.println("Calculating PCA");
		PCA pca = new PCA(data);
		data = pca.getPrincipalComponents();
		
		System.out.println("Ended");
	}
	
	private static double[][] readFile(String fileName){
		double[][] data = new double[19996][24823];
		
		FileReader file;
		try {
			file = new FileReader(fileName);

			BufferedReader reader = new BufferedReader(file);

			for(int i = 0; i < data.length; i++){
				String[] columns = reader.readLine().split(",");
				for(int j = 0; j < columns.length; j++){
					if(columns[j].isEmpty() || columns[j].equals("?"))
						data[i][j] = 0;
					else
						data[i][j] = Double.parseDouble(columns[j]);
				}
			}

			reader.close();
		} catch (FileNotFoundException e) {
			System.err.println("Erro ao ler arquivo de stop words\n" + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro ao ler arquivo de stop words\n" + e.getMessage());
		}
		
		return data;
	}
}
