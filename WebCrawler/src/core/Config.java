package core;

import java.util.*;

import database.*;
import gui.*;

public class Config {

	public static URLHistory history;
	public static WordsRecord crawlerWords;
	public static MainGUI GUI;
	

	// Record of visited URLs
	public static ArrayList<HistoryObject> visited;

	// Revisit time settings
	public static int REVISIT_DAYS = 0;
	public static int REVISIT_HOURS = 0;
	public static int REVISIT_MONTHS = 0;
	public static int REVISIT_YEARS = 0;

	public static String WORDS_FILE = "record.words";

	public static void resetRevisitTimes() {
	    REVISIT_DAYS = 0;
	    REVISIT_HOURS = 0;
	    REVISIT_MONTHS = 0;
	    REVISIT_YEARS = 0;
	}


}


