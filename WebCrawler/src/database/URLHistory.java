package database;

import java.io.*;
import java.text.*;
import java.util.*;

import core.*;

public class URLHistory {

	// Line separator var
	String SEPARATOR_STR = "||";
	String NEWLINE = System.getProperty("line.separator");


	// Default constructor
	public URLHistory() {

	}



	// Constructor with parameters
	public URLHistory(ArrayList<String> visited) {
	}


	public void saveHistory(ArrayList<HistoryObject> sites, int i, int j) throws IOException {
		saveHistory(sites, 0, 0); // Default to revisit time of now
	}

	public void saveHistory(ArrayList<HistoryObject> sites, int revisitDays,
			int revisitHours, int revisitMonths, int revisitYEARS) throws IOException {

		File historyFile = new File("urls" + ".txt"); // TODO: let user define
														// history file?
		PrintWriter out = new PrintWriter(historyFile, "UTF-8");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (HistoryObject url : sites) {
			out.write(url.getUrl() + " " + " "+ " " + SEPARATOR_STR + " "
					+ dateFormat.format(url.getRevisit()) + NEWLINE);
		}

		out.close();

	}



	/* Retrieve method to be call in MainGUI for retrieving crawled urls in text area.
	 * Setting method to be used to retrieve urls history in textarea.
	 */
	public  ArrayList<String> retrieveHistory(ArrayList<HistoryObject> sites) throws IOException {
		ArrayList<String> list= new ArrayList<String>();
		for (HistoryObject url : sites) {
			list.add(url.getUrl() + NEWLINE);
		}
		return list;
	}




	/*
	 * Parses the history file Adds URLs that have not had their revisit time
	 * expire to the visited list Relies on proper formatting of the urls
	 * history file
	 * Updated the date format to a readable format. See below.
	 */
	public void loadHistoryFile() throws IOException, ParseException {
		String line = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();

		Config.GUI.logMessage("Loading history file...");

		// TODO : let user define history file?
		File f = new File("urls.txt");
		if (!f.exists()) {
			System.out.println("History file not found, creating a blank one");
			Config.GUI.logMessage("No previous history file found!");
			PrintWriter out = new PrintWriter(f, "UTF-8");
			out.close();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new FileInputStream("urls.txt"), "UTF-8"));

		// Logging
		System.out.println("Loading history file!");

		int lineNumber = 0;
		//String urlMessage = "";
		while ((line = reader.readLine()) != null) {
			if (line.equals("")) {
				System.out.println("BLANK LINE: " + lineNumber);
				continue;
			}

			String[] list = line.split("\\|\\|");
			List<String> entries = Arrays.asList(list);

			String URL = entries.get(0).trim(); // Address
			String rvTime = entries.get(1).trim(); // Date revisits are allowed

			System.out.println("PARSING LINE " + lineNumber + ": " + line);
			System.out.println("URL: " + URL + " DATE: " + rvTime);

			Date revisit = dateFormat.parse(rvTime);
			if (now.compareTo(revisit) < 0) {
				// Prevent URL from being visited again on this run
				HistoryObject h = new HistoryObject(URL, revisit);
				Config.visited.add(h);
				/*urlMessage += "Revisit time for " + h.getUrl()
						+ " has not yet expired!" + "\n"; */
				System.out.println("Revisit time for " + h.getUrl()
						+ " has not yet expired!");
				/*Config.GUI.logMessage("Revisit time for " + h.getUrl()
	                    + " has not yet expired!");*/
			}

			lineNumber++;
		}
		//JOptionPane.showMessageDialog(null, urlMessage);
		reader.close();
	}

}
