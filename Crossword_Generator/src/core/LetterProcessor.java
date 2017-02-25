package core;



//*******1*********2*********3*********4*********5*********6*********7*********8
// LetterProcessor.java, LetterProcessor, 02/11/2012


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Class that uses transliteration to convert between the Telugu alphabet and
 * the English alphabet.
 * 
 */
public class LetterProcessor {
	// Contains the largest size of a transliterate character.
	private int maxTransliterateSize = 0;
	
	// Contains the Unicode for the halant character
	// which is used to combine consonants.
	// (this is for consonant blends)
	private Letter halant;

	// Contains the default vowel.
	private Letter defaultVowel;

	// Contains the alphabet.
	private ArrayList<Letter> alphabet;

	// turn this to false, if you don't want the clutter of system.out.printlns
	boolean debug = false;

	/**
	 * For testing all the methods in LetterProcessor methods
	 * @param args
	 */
	public static void main(String[] args)
	{
		LetterProcessor telugu = new LetterProcessor("/resources/telugu_unicode.txt");	
		String s1 = "ఇంద్రధనుస్సు ";   //ఇం . ద్ర . ధ. ను. స్సు
		System.out.println("Number of Unicode Characters = " + s1.length());
		System.out.println("Number of Logical Characters = " + telugu.getLength(s1));
		
		String s2 = "ప్రోగ్రామింగ్ "; //ప్రో  . గ్రా  . మిం  . గ్
		System.out.println("Number of Unicode Characters = " + s2.length());
		System.out.println("Number of Logical Characters = " + telugu.getLength(s2));
		
		
		String s3 = "ఆనందమకరందము";  //"ఆ . నం . ద . మ. క. రం . ద .ము "; 
		System.out.println("Number of Unicode Characters = " + s3.length());
		System.out.println("Number of Ulogical Characters = " + telugu.getLength(s3));
		
	}
	
	/**
	 * Default constructor for creating Telugu transliteration object
	 * @param filename
	 */
	public LetterProcessor() {
		this(Config.TELUGU_UNICODE_FILE);
	}
	
	/**
	 * Creates a LetterProcessor object and loads transliteration data from the
	 * specified file. Instances of the File class are immutable and there is
	 * not a default constructor because the transliteration data must be loaded
	 * from a file.
	 * 
	 * @param filename
	 *            string containing the filename of the file containing the
	 *            transliteration data.
	 * @throws FileNotFoundException
	 *             If the file does not exist, is a directory rather than a
	 *             regular file, or for some other reason cannot be opened for
	 *             reading.
	 */
	public LetterProcessor(String filename) {
		loadTransliterationData(filename);
	}

	/*
	 * Loads the transliteration data from the specified file.
	 * 
	 * @param filename string containing the filename of the file containing the
	 * transliteration data.
	 * 
	 * @throws FileNotFoundException If the file does not exist, is a directory
	 * rather than a regular file, or for some other reason cannot be opened for
	 * reading.
	 */
	private void loadTransliterationData(String filename) {
		// Used to store the line being read.
		String[] get_line;

		// Used to read the file.
		Scanner input = new Scanner(getClass().getResourceAsStream(filename));

		alphabet = new ArrayList<Letter>();

		// Reads file line by line.
		while (input.hasNextLine()) {
			String nextLine = input.nextLine();
			get_line = nextLine.trim().split("\\s+");
			
			//System.out.println(nextLine);

			// Checks that it isn't empty and stores line.
			if (get_line.length > 0) {

				// Adds vowel to alphabet
				if (get_line[0].toLowerCase().equals("vowel")) {
					if (get_line.length == 3) {
						alphabet.add(new Letter(Letter.VOWEL, get_line[1],
								hexToChar(get_line[2])));
						maxTransliterateSize = Math.max(maxTransliterateSize,
								get_line[1].length());
						// Stores default vowel
						if (defaultVowel == null) {
							defaultVowel = new Letter(Letter.VOWEL,
									get_line[1], hexToChar(get_line[2]));
						}
					} else if (get_line.length == 4) {
						alphabet.add(new Letter(Letter.VOWEL, get_line[1],
								hexToChar(get_line[2]), hexToChar(get_line[3])));
						maxTransliterateSize = Math.max(maxTransliterateSize,
								get_line[1].length());
					}
				}

				// Adds consonant to alphabet
				else if (get_line[0].toLowerCase().equals("consonant")
						&& get_line.length == 3) {
					alphabet.add(new Letter(Letter.CONSONANT, get_line[1],
							hexToChar(get_line[2])));
					maxTransliterateSize = Math.max(maxTransliterateSize,
							get_line[1].length());
				}

				// Stores halant
				else if (get_line[0].toLowerCase().equals("halant")
						&& get_line.length == 3 && halant == null) {
					halant = new Letter(Letter.HALANT, get_line[1],
							hexToChar(get_line[2]));
					alphabet.add(halant);
					maxTransliterateSize = Math.max(maxTransliterateSize,
							get_line[1].length());
				}

				// Adds number to alphabet
				else if (get_line[0].toLowerCase().equals("number")
						&& get_line.length == 3) {
					alphabet.add(new Letter(Letter.NUMBER, get_line[1],
							hexToChar(get_line[2])));
					maxTransliterateSize = Math.max(maxTransliterateSize,
							get_line[1].length());
				}

				// Adds other characters to alphabet
				else if (get_line[0].toLowerCase().equals("other")
						&& get_line.length == 3) {
					alphabet.add(new Letter(Letter.OTHER, get_line[1],
							hexToChar(get_line[2])));
					maxTransliterateSize = Math.max(maxTransliterateSize,
							get_line[1].length());
				}

				// Adds equivalents to equivalent list
				else if (get_line[0].toLowerCase().equals("equivalent")
						&& get_line.length == 3) {
					// To DO
				}
			}
		}
		// Close file
		input.close();
	}

	/**
	 * Converts the specified hex number to a char.
	 * 
	 * @param hex
	 *            String containing a hex number.
	 * @return A char containing the specified hex number.
	 */
	private char hexToChar(String hex) {
		return (char) Integer.decode("#" + hex).intValue();
	}

	/**
	 * Searches the alphabet for Dependent Unicode that match the specified
	 * Unicode value.
	 * 
	 * @param unicode
	 *            A Unicode value that is being searched for.
	 * @return Returns the index if a match is found or -1 if no match is found.
	 */
	private int searchDependentUnicode(char unicode) {
		// Iterates through each letter in the alphabet searching for a match.
		for (int i = 0; i < alphabet.size(); i++) {
			if (alphabet.get(i).getDependentUnicode() == unicode) {
				return i;
			}
		}

		// No match found.
		return -1;
	}

	/**
	 * Parses the specified string of characters in a native script into a list
	 * of the characters.
	 * 
	 * @param text
	 *            A String in a native script to be parsed into individual
	 *            characters.
	 * @return Returns a an array of Strings a list of the characters parsed
	 *         from the specified string.
	 */

	public String[] parseStringToLogicalCharacters(String text) {
		
		// Used to create the list of the characters parsed from the specified
		// string.
		LinkedList<String> logical_char_list = new LinkedList<String>();

		// Used to create the return value;
		// This is used to simply convert the LinkedList to Array
		String[] result;

		// Check for valid string.
		if (text == null || text.isEmpty()) {
			return new String[0];
		}

		// Contains the index of the the matching character in alphabet.
		int match;
		int vowel_match;
		boolean add_to_previous = false;
		String str = null;
		Collections.sort(alphabet, new LetterCompareUnicode());

		// Iterates through each character in the text.
		for (int i = 0; i < text.length(); i++) {

			if (i != 0) {// Look for M, M can not be the 1'st letter.
				match = Collections.binarySearch(alphabet,
						new Letter(text.charAt(i)), new LetterCompareUnicode());
				if (match == 1) {
					str = logical_char_list.removeLast()
							+ Character.toString(text.charAt(i));
					logical_char_list.addLast(str);
					continue;
				}
			}
			if (add_to_previous) {
				str = logical_char_list.removeLast()
						+ Character.toString(text.charAt(i));
				logical_char_list.addLast(str);
			} else {
				logical_char_list.addLast(Character.toString(text.charAt(i)));
			}

			// Searches for a match in the alphabet.
			match = Collections.binarySearch(alphabet,
					new Letter(text.charAt(i)), new LetterCompareUnicode());
			// Match found
			if (match > 0 && i + 1 < text.length()) {
				switch (alphabet.get(match).getType()) {

				case Letter.HALANT:
					// Check if next letter is a consonant.
					match = Collections.binarySearch(alphabet,
							new Letter(text.charAt(i + 1)),
							new LetterCompareUnicode());
					if (match > 0
							&& alphabet.get(match).getType() == Letter.CONSONANT) {
						add_to_previous = true;
					} else {
						add_to_previous = false;
					}
					break;
				case Letter.CONSONANT:
					// Check if next letter is a dependent vowel or a halant.
					match = Collections.binarySearch(alphabet,
							new Letter(text.charAt(i + 1)),
							new LetterCompareUnicode());
					vowel_match = searchDependentUnicode(text.charAt(i + 1));
					if ((match > 0 && alphabet.get(match).getType() == Letter.HALANT)
							|| (vowel_match > 0)) {
						add_to_previous = true;
					} else {
						add_to_previous = false;
					}
					break;
				default:
					add_to_previous = false;
				}
			} else {
				add_to_previous = false;
			}
		}
		// Convert result from LinkedList to an array.
		result = new String[logical_char_list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = logical_char_list.removeFirst();
		}
		return result;
	}

	public ArrayList<Letter> getAlphabet() {
		return new ArrayList<Letter>(alphabet);
	}

	public Letter getHalant() {
		return halant;
	}


	/*
	 * An Internal private class Used to compare two Letters based on their
	 * Unicode values
	 */
	private class LetterCompareUnicode implements Comparator<Letter> {

		/*
		 * Compares the Unicode value of the two specified Letters for order.
		 * 
		 * Compares the Unicode value of the two specified Letters for order.
		 * Returns a negative integer, zero, or a positive integer as the
		 * Unicode value of the first Letter is less than, equal to, or greater
		 * than the Unicode value of the second Letter.
		 * 
		 * @param compare_1 The first Letter to be compared.
		 * 
		 * @param compare_2 The second Letter to be compared.
		 * 
		 * @return a negative integer, zero, or a positive integer as the
		 * Unicode value of the first Letter is less than, equal to, or greater
		 * than the Unicode value of the second Letter.
		 */
		@Override
		public int compare(Letter compare_1, Letter compare_2) {
			return (int) (compare_1.getUnicode() - compare_2.getUnicode());
		}
	}

	/**
	 * This method returns the length of logical characters in a Telugu String
	 * 
	 * @param phrase
	 *            - The phrase to count characters for.
	 * @return The number of logical characters in the telugu string.
	 */
	public int getLength(String phrase) {
		String[] logical_char_array = parseStringToLogicalCharacters(phrase);
		return logical_char_array.length;
	}
	
}



