package database;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

import core.*;

public class WordsRecord {

	private HashMap<String, Integer> wordsMap = new HashMap<String, Integer>();
	private int crawlCount;

	public WordsRecord() {
	    crawlCount = 0;
	    // Load serialized words hashmap
	    loadWordsFromFile(Config.WORDS_FILE);
	}

	public HashMap<String, Integer> getWordsMap() {
        return wordsMap;
    }

	public int getWordsCount() {
	    return wordsMap.size();
	}

    /**
     * Serialize and save the words map to a file
     * @param filename File path to save to
     * @return true if successful
     */
	public boolean serializeWordsMap(String filename) {

        try {
            FileOutputStream fileOS;
            fileOS = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOS);
            out.writeObject(wordsMap);
            out.close();
            fileOS.close();
        } catch ( Exception ex ) {
           ex.printStackTrace();
           return false;
        }
	    return true;
	}

    /**
     * Load a serialized words map
     * @param filename File path to load from
     * @return true if successful
     */
    public boolean loadWordsFromFile(String filename) {

        File f = new File(filename);
        if (!f.exists()) {
            Config.GUI.logMessage("No previous words database found!");
            return false;
        }

        try {
            FileInputStream fileIS;
            fileIS = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIS);
            wordsMap = (HashMap<String, Integer>) in.readObject();
            in.close();
            fileIS.close();
        } catch ( Exception ex ) {
            ex.printStackTrace();
            return false;
        }
	    return true;
	}

	/**
	 * Given a word, strip out punctuation and make sure it's a valid word. Then
	 * add it to the wordsMap.
	 */
	public void processWord(String word) {

		if (null == word)
			return;

		// Do stripping of punctuation & stuff here!

		// Femi's updated regex for multi-language support
		word = word.replaceAll("[^a-zA-Z-A-Za-zÀ-ÖØ-öø-ʯͰ-῿]+", "");
		if (null == word || word.isEmpty()) {
			return;
		}
		if (null == word || word.isEmpty()) {
			return;
		}


		// If this is still a valid word, add to/increment the hashmap
		if (wordsMap.containsKey(word)) {
			wordsMap.put(word, wordsMap.get(word) + 1);
		} else {
			wordsMap.put(word, 1);
		}

		// Add one to the count for this crawl session for reporting purposes
		crawlCount++;

	}

	/**
	 * Generates a text file containing all words in the database
	 * and how often they appear
	 * @param sorted list to use when populating the file
	 */
	public void updateWordsFile(List<Map.Entry<String, Integer>> list) throws IOException {

		String NEWLINE = System.getProperty("line.separator");
		File words = new File("allwords" + ".txt");
		PrintWriter out = new PrintWriter(words, "UTF-8");

		// Making this a param so we don't sort twice...
		//List<Map.Entry<String, Integer>> list = sortByFrequency();

		for (int i = 0; i < wordsMap.size(); i++) {
			out.write(list.get(list.size() - i - 1).getKey() + " "
					+ list.get(list.size() - i - 1).getValue() + NEWLINE);
		}

		out.close();
		//Desktop.getDesktop().open(words);
	}



	/**
	 * Bjorn: Depreciated method for creating the HTML results file Merged from
	 * out of date master branch just in case
	 */
	private void updateWordsFile(ArrayList<String> crawledWords)
			throws IOException {

		StringBuilder ls = new StringBuilder();

		File words = new File("CrawlerWords" + ".txt");
		PrintWriter out = new PrintWriter(words, "UTF-8");

		for (String word : crawledWords) {
			//word = word.replaceAll("[^a-zA-Z]", " ");
			// Updated this with new regex, see below.
			word = word.replaceAll("[^a-zA-Z-A-Za-zÀ-ÖØ-öø-ʯͰ-῿]+", "");
			if (word != null && !word.isEmpty())

				out.write(ls
						.append("<div style='margin:0 auto; width:600px; position:relative;'>")
						+ "<p></p> " + word + ls.append("<p></p>" + "</div>"));
		}

		out.close();
		Desktop.getDesktop().open(words);
	}

	/**
	 * Bjorn: A method to display crawl results as HTML. This method uses the
	 * words HashMap
	 */
	public void generateWordsHTML() throws IOException {
		File htmlWords = new File("CrawlerResults" + ".html");
		PrintWriter out = new PrintWriter(htmlWords, "UTF-8");

		out.write("<!DOCTYPE html><html><head><meta charset='UTF-8'>"
				+ "<title>Crawler Results</title>" + "</head>" + "<body>");

        out.write("<p style='font-size: 20px; margin:0 auto; width:600px;'>"
            + "Words crawled this session: " + crawlCount + "</p>");
		out.write("<p style='font-size: 20px; margin:0 auto; width:600px;'>"
				+ "Database total: " + wordsMap.size() + " unique words</p><br>");
		out.write("<p style='font-size: 20px; margin:0 auto; width:600px;'>"
            + "Below are the 50 most common words discovered.</p>");
	      out.write("<p style='font-size: 20px; margin:0 auto; width:600px;'>"
	            + "See <a href='allwords.txt'>allwords.txt</a> for a complete list of words found.</p>");

		List<Map.Entry<String, Integer>> list = sortByFrequency();

		for (int i = 0; i < 50; i++) {
			out.write("<div style='margin:0 auto; width:600px; position:relative;'><p></p>");
			out.write(list.get(list.size() - i - 1).getKey() + " - "
					+ list.get(list.size() - i - 1).getValue());
			out.write("<p></p></div>");
		}

		out.write("</body></html>");
		out.close();

		updateWordsFile(list);
		// Reset the crawl session count
		crawlCount = 0;
		// Comment out this line to disable automatically opening results after crawl
		Desktop.getDesktop().browse(htmlWords.toURI());
	}

	// Sorts the wordsMap by how often each word appears
	// Returns a list of map entries
	private List<Map.Entry<String, Integer>> sortByFrequency() {

		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
				wordsMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Map.Entry<String, Integer> e1,
					Map.Entry<String, Integer> e2) {
				return e1.getValue().compareTo(e2.getValue());
			}
		});

		return list;
	}

	// Sorts the wordsMap alphabetically (sort of)
	// Returns a list of map entries
	private List<Map.Entry<String, Integer>> sortByWord() {

		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
				wordsMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Map.Entry<String, Integer> e1,
					Map.Entry<String, Integer> e2) {
				return e1.getKey().compareTo(e2.getKey()) * -1;
			}
		});

		return list;
	}
}
