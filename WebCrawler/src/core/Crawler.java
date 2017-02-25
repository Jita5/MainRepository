package core;

import java.io.*;
import java.text.*;
import java.util.*;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import database.*;

public class Crawler {

	public static int depth;

	// Selvin : Creating urls arraylist as static to be visible in other MainGui class.
	// Bjorn : You can't do this without breaking the crawler logic
	//         so I'm adding a count variables instead.
	//public static ArrayList<String> urls = new ArrayList<String>();
	  private static int crawledURLs = 0;
	  private static int foundURLs = 0;

    /**
     * Handles input from the crawler GUI
     * param URLs String array containing the seed URLs
     * param d The user's depth setting
     * */
	public static void handleInput(String[] URLs, int d) throws IOException {

		Config.visited = new ArrayList<HistoryObject>();
		Config.history = new URLHistory();
		Config.crawlerWords = new WordsRecord();
		depth = d;
		crawledURLs = 0;
		foundURLs = 0;



		// Revisit urls times set up
		Calendar revisit = Calendar.getInstance();
		revisit.set(Calendar.DAY_OF_MONTH, revisit.get(Calendar.DAY_OF_MONTH)
				+ Config.REVISIT_DAYS);
		revisit.set(Calendar.HOUR_OF_DAY, revisit.get(Calendar.HOUR_OF_DAY)
				+ Config.REVISIT_HOURS);
		revisit.set(Calendar.YEAR, revisit.get(Calendar.YEAR)
				+ Config.REVISIT_YEARS);

		try {
			Config.history.loadHistoryFile();
		} catch (ParseException ex) {
			ex.printStackTrace();
		}


		for (String userInput : URLs) {
		    userInput = cleanURL(userInput);
		    System.out.println("CLEANED: " + userInput);
			if (shouldVisit(userInput)) {
				HistoryObject h = new HistoryObject(userInput,
						revisit.getTime());
				Config.visited.add(h);
				crawl(userInput, revisit.getTime(), d);
			}
		}


		/*
		for (String userInput : URLs) {
			if (shouldVisit(userInput)) {
				HistoryObject h = new HistoryObject(userInput,
						revisit.getTime());
				Config.visited.add(h);
				crawl(userInput, revisit.getTime(), d);
			}
		}
		*/


		Config.GUI.logMessage("Crawling complete!");
		Config.crawlerWords.serializeWordsMap(Config.WORDS_FILE);
		Config.crawlerWords.generateWordsHTML();

		/* Added revisitMonths, and revisitYears vars */
		Config.history.saveHistory(Config.visited, Config.REVISIT_DAYS,
		Config.REVISIT_HOURS, Config.REVISIT_MONTHS, Config.REVISIT_YEARS);

	}



	/**
     * Crawls a URL and stores any words it finds in Config.crawlerWords
     * @param url is the URL String to crawl
     * @param revisit is a Date object containing the recrawl dateTime
     * @param d is how many discovered links the crawler should follow
     * */
	public static void crawl(String url, Date revisit, int d) throws IOException {

		String a = null;
		Document doc = null;

		System.out.println("CRAWLING: " + url + " with depth " + d);
        Config.GUI.logMessage("Crawling: " + url);

		try {
			doc = Jsoup.connect(url).get();
		} catch (Exception ex) {
			Config.GUI.logMessage("ERROR: " + ex.getLocalizedMessage() );
			System.out.println( ex.getLocalizedMessage() );
			return;
		}
		crawledURLs++;
		String text = doc.text();

		// Separate words (uses spaces for now)
		String[] docWords = text.split(" ");

		for (String word : docWords) {
			// Pass each word to the words record for counting & cleanup
			Config.crawlerWords.processWord(word);
		}

	    // If depth has reached zero, end here
        // Otherwise discover links and follow them
        if(d < 1)
            return;

		// get all links and recursively call the crawl method
		Elements tags = doc.select("a[href]");

		ArrayList<String> urls = new ArrayList<String>();

		// Build an arraylist of links
		for (Element e : tags) {
			urls.add(e.absUrl("href"));
		}
		System.out.println("Found " + urls.size() + " potential URLS");
		foundURLs += urls.size();



		for (int i = 0; i < urls.size(); i++) {
			a = urls.get(i);
			a = cleanURL(a);
			System.out.println("CRAWLING URL NUMBER " + i);
			if (shouldVisit(a)) {
				HistoryObject h = new HistoryObject(a, revisit);
				Config.visited.add(h);
				crawl(a, revisit, d-1); // Crawl with depth - 1
			}
		}
	}


		/*
		for (int i = 0; i < Math.min(d, urls.size()); i++) {
			a = urls.get(i);
			System.out.println(a);
			if (shouldVisit(a)) {
				HistoryObject h = new HistoryObject(a, revisit);
				Config.visited.add(h);
				crawl(a, revisit, 0);
			}
		}
	}
	*/

    /**
     * Determines if a URL should be visited on this crawl
     * @param url URL string to check the history against
     * @return true if allowed to crawl url
     **/
	private static Boolean shouldVisit(String url) {

		// This could be more efficient? :(
		for (HistoryObject history : Config.visited) {
			if (history.getUrl().equals(url)) {
		        System.out.println("REVISIT: " + url);
		        Config.GUI.logMessage("Revisit time not expired for: " + url);
				return false;
			}
		}

		return true;
	}




	/**
	 * Attempt to clean URLs to make them more crawler-friendly
	 * @param url URL string to clean
	 * @return processed URL string
	 **/
	private static String cleanURL(String url) {
	    String newURL = url;

	    // Try and prevent malformedURLexceptions
	    // TODO: Needs a better (GUI) solution
	    if(!newURL.startsWith("http://") && !newURL.startsWith("https://")
	        && !newURL.startsWith("file://")){
	        newURL = "http://" + newURL;
	    }

	    // Strip some not needed ending characters
	    if(newURL.endsWith("/") || newURL.endsWith("#")){
	        newURL = newURL.substring(0, newURL.length()-1);
	    }

	    return newURL;
	}

	public static int getCrawledURLs() {
        return crawledURLs;
    }

	public static int getFoundURLs() {
        return foundURLs;
    }

}
