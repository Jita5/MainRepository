	package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class Config {
	
	//current language (TE or EN)
	public static String CurrentLanguage = "EN";
		
	//default grid rows
	//can be overridden via API constructor
	//exposed to admin mode gui
	public static int GridRows = 10;
	
	//default grid columns
	//can be overridden via API constructor
	//exposed to admin mode gui
	public static int GridColumns = 10;
	
	//default minimum # of words to be placed
	//can be overridden via API constructor
	//exposed to admin mode gui
	public static int MinWordsPlaced = 10;
	
	//default maximum # of words to be placed
	//can be overridden via API constructor
	//exposed to admin mode gui
	public static int MaxWordsPlaced = 10;
	
	//default # of puzzles to generate (1-6)
	//can be overridden via API constructor
	//exposed to admin mode gui
	public static int NumPuzzlesToGenerate = 6;
	
	//default max allowed time allowed to generate crossword puzzles
	//can be overridden via API constructor
	//exposed to admin mode gui
	public static int MaxAllowedTime = 60;
	
	//default input source (file = 0 or gui = 1)
	//can be overridden via API constructor
	//exposed to admin mode gui
	public static int InputSource = 0;
	
	
	//minimum word placement is multiplied by this value
	//to determine how big of a random words list to generate
	public static int GetNumWordsMultiple(){
		switch(CurrentLanguage){
		case "EN":
			return 3;
		case "TE":
			return 5;
		}
		return 0;
	}
	
	//big words list
	public static BigWordCollection BigWordsCollection;
	
	//input file configs
	public static String INPUT_FILE = "/resources/input_words_5.txt";
	public static final String DELIMETER = "\\|";
	public static final int MAX_ITEMS_PER_LINE = 7;

	//method to get some random words
	public static ArrayList<BigWord> GetRandomWordsList(){
		BigWordCollection privateCollection = new BigWordCollection();
		
		BigWordsCollection = privateCollection;
				
		ArrayList<BigWord> strings = new ArrayList<BigWord>();
		
		for(BigWord word : BigWordsCollection.getAllBigWords()){
			if(word != null){
				switch(CurrentLanguage){
				case "TE":
					String returnWord = word.getTelugu();
					returnWord = new TeluguWordProcessor(returnWord.trim().replace(" ", "")).stripAllSymbols();
					returnWord = new TeluguWordProcessor(returnWord.trim().replace(" ", "")).stripSpaces();
					strings.add(word);
					break;
				default:
					strings.add(word);
					break;
				}
			}
		}
		return strings;
	}
	
	//get big word from word
	public static BigWord FindBigWordInFilteredBigWords(String word){
		for(BigWord bw : FilteredBigWords){
			switch(CurrentLanguage){
			case "EN":
				if(word.equals(Cleaned(bw.getEnglish()))){
					return bw;
				}
			case "TE":
				if(word.equals(Cleaned(bw.getTelugu()))){
					return bw;
				}
			}
		}
		return null;
	}
	
	//sanitize
	public static String Cleaned(String inputWord){
		switch(CurrentLanguage){
		case "TE":
			String returnWord = inputWord;
			returnWord = new TeluguWordProcessor(returnWord.trim().replace(" ", "")).stripAllSymbols();
			returnWord = new TeluguWordProcessor(returnWord.trim().replace(" ", "")).stripSpaces();
			return returnWord;
		default:
			return new WordProcessor(inputWord.trim()).stripSpaces();
		}
	}
	
	//filtered big words
	public static ArrayList<BigWord> FilteredBigWords = new ArrayList<BigWord>();
	
	//get filtered randomized words list
	public static ArrayList<String> GetFilteredRandomWordsList(){
		
		ArrayList<BigWord> bigWords = GetRandomWordsList();
		
		ArrayList<String> filteredWords = new ArrayList<String>();
		
		int numToSelect = MinWordsPlaced * GetNumWordsMultiple();
		
		while(filteredWords.size() < numToSelect){
			Random randomGenerator = new Random();
		    int index = randomGenerator.nextInt(bigWords.size());
		    
		    BigWord testWord = bigWords.get(index);
		    
		    switch(CurrentLanguage){
			case "EN":
				String cleanedEN = Cleaned(testWord.getEnglish());
				if(cleanedEN.length() < Config.GridRows){
					filteredWords.add(cleanedEN);
					FilteredBigWords.add(testWord);
				}
				break;
			case "TE":
				String cleanedTE = Cleaned(testWord.getTelugu());
				if(cleanedTE.length() < Config.GridRows){
					filteredWords.add(cleanedTE);
					FilteredBigWords.add(testWord);
				}
				break;
			}
		}
		
		return filteredWords;
	}
	
	//manual input words list (gui)
	public static HashMap<String, String> ManualWordsList = new HashMap<String, String>();
	
	//get manual input words list
	public static ArrayList<String> GetManualWordsList(){
		ArrayList<String> returnWords = new ArrayList<String>();
		Map<String, String> map = ManualWordsList;
		Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
		    Entry<String, String> entry = entries.next();
		    returnWords.add(entry.getKey());
		}
		return returnWords;
	}

	//get clue from manualwordslist hashmap
	public static String GetClueFromManualWordsList(String inputWord){
		Map<String, String> map = ManualWordsList;
		Iterator<Map.Entry<String, String>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
		    Entry<String, String> entry = entries.next();
		    if(inputWord == entry.getKey()){
		    	return entry.getValue();
		    }
		}
		return null;
	}
	
	//teludu unicode
	public static String TELUGU_UNICODE_FILE = "/resources/telugu_unicode.txt";
	
	//overloaded method to get word processor
	public static WordProcessor GetWordProcessor(String word){
		switch(Config.CurrentLanguage){
		case "TE":
			return new TeluguWordProcessor(word);
		default:
				return new WordProcessor(word);
		}
	}
	
	//html blank character EN
	public static String BlankCharacterEN = "@";
	
	//html blank character TE
	public static String BlankCharacterTE = "గు";
	
	//method to get blank character
	public static String GetBlankCharacter(){
		switch(CurrentLanguage){
		case "EN":
			return BlankCharacterEN;
		case "TE":
			return BlankCharacterTE;
		}
		return null;
	}
	
	//font file
	public static String FontFile = "/resources/Gidugu.ttf";
	
	//application icon 16x16
	public static String AppIcon16 = "/resources/imgCrosswordIcon16x16.gif";
	
	//application icon 32x32
	public static String AppIcon32 = "/resources/imgCrossWordIcon32x32.png";
	
	//application background
	public static String AppBackground = "/resources/imgAppBG.jpg";
	
	//loading icon
	public static String LoadingIcon = "/resources/imgPleaseWait.gif";
	
	//options - number of puzzles
	public static String[] OptionsNumberOfPuzzles = {"1", "2", "3", "4", "5", "6"};
	
	//options - wait times
	public static String[] OptionsWaitTime = {"60", "120", "180", "5"};
	
	//options - grid sizes
	public static String[] OptionsGridSizes = {"10x10", "15x15", "20x20", "25x25"};
	
	//options - words list source
	public static String[] OptionsWordSource = {"File", "GUI"};
	
/*
----------
english test gui input
----------
HELLO,CLUE
TURK,CLUE
RANDOM,CLUE
INTERNET,CLUE
BEAST,CLUE
RADICAL,CLUE
FAVORITE,CLUE
DARWIN,CLUE
COFFEE,CLUE
FAN,CLUE
BANFF,CLUE
FELT,CLUE
CUB,CLUE
BURGER,CLUE
SANTA,CLUE
BOTTLE,CLUE
----------
telugu test gui input
----------
పసిరికపాము,పసిరికపాము
మురుగులు,మురుగులు
పఉడుము,పఉడుము
మైసూర్ పాక్,మైసూర్ పాక్
సీతాకోకచిలుక,సీతాకోకచిలుక
చిరుతపులి,చిరుతపులి
బాతుపిల్ల,బాతుపిల్ల
జెముడుకాకి,జెముడుకాకి
ఆడసింహము,ఆడ సింహము
సముద్రపుపక్షి,సముద్రపు పక్షి
ఉల్లంగిపిట్ట,ఉల్లంగి పిట్ట
కాకినాడకాజా,కాకినాడకాజా
జాంగ్రి,జాంగ్రి
కాల్జోనె,కాల్జోనె
*/
}
