package each.ppgsi.nlp;

import java.util.HashMap;

public class TFIDFTable {

	private HashMap<String, Integer> terms;
	private float[][] table;
	private int documentCount;
	private int termCount;

	public TFIDFTable(int documentCount) {
		this.documentCount = documentCount;
		terms = new HashMap<String, Integer>();
	}

	public void addTerm(String term) {
		if (!terms.containsKey(term)) {
			terms.put(term, terms.keySet().size());
		}
	}

	public void generateTable() {
		termCount = terms.keySet().size();
		System.out.println("Terms to add: " + termCount);
		table = new float[documentCount][termCount];
	}

	public void increaseTermCount(String term, int documentId) {
		int termId = terms.get(term);
		table[documentId][termId]++;
	}

	private float TF(int documentId, int termId) {
		// Calculate term count
		int termOccurrencesInDoc = 0;
		for (int j = 0; j < termCount; j++) {
			termOccurrencesInDoc += table[documentId][j];
		}
		return ((float) table[documentId][termId] / termOccurrencesInDoc);
	}

	private float IDF(int termId) {
		// Calculate how many docs term appear
		int docsWithTerm = 0;
		for (int i = 0; i < documentCount; i++) {
			if (table[i][termId] > 0)
				docsWithTerm++;
		}

		return (float) Math.log(documentCount / docsWithTerm);
	}

	public void calculateTFIDF() {
		for (int i = 0; i < documentCount; i++) {
			System.out.println("Calculating TFIDF for doc " + i);
			for (int j = 0; j < termCount; j++) {
				table[i][j] = TF(i, j) * IDF(j);
				System.out.println("term " + j + ": " + table[i][j]);
			}
		}
	}
}
