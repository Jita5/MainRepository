package core;
// The complete implementation along with the documentation 
// will count towards two software engineering assignments
// Software Engineering Assignment 2
// Software Engineering Assignment 3

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class provides several operations in the context of single word.
 * This class is expected to work against multiple languages so the implementation
 * should not rely on the assumption that the string contain characters.
 */

public class WordProcessor {
	
	//word represents the string we are processing in this class
	private String word;
	
	// logicalChars are derived from the word
	// word can also be derived from logicalChars
	// these two are dependent on each other
	// if one changes, the other changes
	private ArrayList<String> logicalChars;
	
	/**
	 * Default constructor
	 */
	public WordProcessor()
	{
		
	}
	
	/**
	 * Overloaded constructor that takes the word
	 * @param a_word
	 */
	
	public WordProcessor(String a_word)
	{
		setWord(a_word);
	}
	
	/**
	 * Overloaded constructor that takes the logical characters as input
	 * @param some_logical_chars
	 */
	public WordProcessor(ArrayList<String> some_logical_chars)
	{
		setLogicalChars(some_logical_chars);
	}
	
	/**
	 * set method for the word
	 * @param a_word
	 */
	public void setWord(String a_word)
	{
		word = a_word;
		parseToLogicalChars();
	}
	
	/**
	 * set method for the logical characters
	 * @param some_logical_chars
	 */
	public void setLogicalChars(ArrayList<String> some_logical_chars)
	{
		logicalChars = some_logical_chars;
		StringBuilder sb = new StringBuilder();
		for(String s : some_logical_chars){
			sb.append(s);
		}
		word = sb.toString();
	}
	
	/**
	 * get method for the word
	 * @return
	 */
	public String getWord()
	{
		return word;
	}
	
	/**
	 * get method for the logical characters
	 * @return
	 */
	public ArrayList<String> getLogicalChars()
	{
		return logicalChars;
	}
	
	/**
	 * Returns the length of the word
	 * length = number of logical characters
	 * @return
	 */
	public int getLength()
	{
		return logicalChars.size();
	}
	
	/**
	 * Returns the number of code points in the word
	 * @return
	 */
	public int getCodePointLength()
	{
		int count = 0;
		for(@SuppressWarnings("unused") int c : word.codePoints().toArray()){
		    count++;
		}
		return count;
	}
	
	/**
	 * This method breaks the input word into logical characters
	 * For English,
	 * 	  convert the string to char array
	 * 	  and convert each char to a string
	 *    and populate logicalChars
	 */
	public void parseToLogicalChars()
	{		
		this.logicalChars = parseToLogicalChars(word);
	}
	
	public ArrayList<String> parseToLogicalChars(String input){

		char[] chars = input.toCharArray();
		
		ArrayList<String> logicalChars = new ArrayList<String>();
		
		for (char c : chars) 
		{
			logicalChars.add(String.valueOf(c));		
		}
		
		return logicalChars;
	}
	
	/**
	 * If the word starts with the logical character, 
	 * this method returns true.
	 * @param start_char
	 * @return
	 */
	public boolean startsWith(String start_char)
	{
		return word.indexOf(start_char) == 0;
	}
	
	/**
	 * If the word ends with the logical character, 
	 * this method returns true.
	 * @param start_char
	 * @return
	 */
	public boolean endsWith(String end_string)
	{
		return word.endsWith(end_string);
	};
	
	/**
	 * This method checks whether the sub_string or logical character
	 * is contained within the word
	 * @param sub_string
	 * @return
	 */
	public boolean containsString(String sub_string)
	{
		return containsChar(sub_string);
	}
	
	/**
	 * This method checks whether the sub_string or logical character
	 * is contained within the word
	 * @param sub_string
	 * @return
	 */
	public boolean containsChar(String sub_string)
	{
		return word.indexOf(sub_string) > -1;
	}
	
	/**
	 * This method checks whether the logical characters
	 * are contained within the string/word.
	 * is contained within the word
	 * @param sub_string
	 * @return
	 */
	public boolean containsLogicalChars(ArrayList<String> logical_chars)
	{
		Boolean returnBool = true;
		
		for (String s : logical_chars){
			if(word.indexOf(s) == -1){
				returnBool = false;
			}
		}
		
		return returnBool;
	}
	
	/**
	 * This method checks whether the logical characters
	 * are contained within the string/word.
	 * is contained within the word
	 * @param sub_string
	 * @return
	 */
	public boolean containsLogicalChars(ArrayList<String> logical_chars, String input)
	{
		Boolean returnBool = true;
		
		for (String s : logical_chars){
			if(input.indexOf(s) == -1){
				returnBool = false;
			}
			else{
				input = input.replaceFirst(s, "");
			}
		}
		
		return returnBool;
	}
	
	
	/**
	 * This method checks whether *ALL* the logical characters
	 * are contained within the string/word.
	 * is contained within the word
	 * @param sub_string
	 * @return
	 */
	public boolean containsAllLogicalChars(ArrayList<String> logical_chars)
	{
		return containsLogicalChars(logical_chars);
	}
	
	/**
	 * This method checks whether *ALL* the logical characters
	 * are contained within the string/word.
	 * is contained within the word
	 * @param sub_string
	 * @return
	 */
	public boolean containsLogicalCharSequence(ArrayList<String> logical_chars)
	{
		StringBuilder sb = new StringBuilder();
		for(String s : logical_chars){
			sb.append(s);
		}
		return word.indexOf(sb.toString()) > -1;
	};
	
	/**
	 * This method checks whether a word can be made out of the original word
	 * example:  original word = POST;   a_word = POT
	 * @param a_word
	 * @return
	 */
	public boolean canMakeWord(String a_word)
	{
		ArrayList<String> logical_chars = parseToLogicalChars(a_word);
		return containsLogicalChars(logical_chars, word);
	}
	
	/**
	 * This method checks whether all the words in the collection
	 * can be made out of the original word
	 * example:  original word = POST;   a_word = POT; STOP; TOP; SOP
	 * @param a_word
	 * @return
	 */
	public boolean canMakeAllWords(ArrayList<String> some_words)
	{
		for(String s : some_words){
			if(!canMakeWord(s))
				return false;
		}
		return true;
	}
	
	/**
	 * returns true if the word contains the space
	 * @return
	 */
	public boolean containsSpace()
	{
		return word.indexOf(" ") != -1;
	};
	
	/**
	 * returns true if the word is a palindrome
	 * @return
	 */
	public boolean isPalindrome()
	{
		ArrayList<String> reversedLogicalChars = new ArrayList<>(getLogicalChars());
		Collections.reverse(reversedLogicalChars);
		return getLogicalChars().equals(reversedLogicalChars);
	}
	
	/**
	 * returns true if the word_2 is an anagram of the word
	 * @return
	 */
	public boolean isAnagram(String word_2)
	{
		if(word_2.length() != word.length())
			return false;
		
		ArrayList<String> chars = parseToLogicalChars(word_2);

		return containsAllLogicalChars(chars);
	}
	
	/**
	 * returns true if the logical_chars are contained with in the word
	 * @return
	 */
	public boolean isAnagram(ArrayList<String>  logical_chars)
	{
		if(logical_chars.size() != logicalChars.size())
			return false;
		
		return containsAllLogicalChars(logical_chars);
	}
	
	// String manipulation methods
	/**
	 * strips of leading and trailing spaces
	 * @return
	 */
	public String trim()
	{
		return word.trim();
	}
	
	/**
	 * strips of all spaces in the word
	 * @return
	 */
	public String stripSpaces()
	{
		return word.replaceAll("\\s+","");
	}
	
	/**
	 * strips of all special characters and symbols from the word
	 * @return
	 */
	public String stripAllSymbols()
	{
		String pattern = "[\\p{S}\\p{P}]*";
		return word.replaceAll(pattern,"");
	};
	
	/**
	 * Reverse the word and returns a new word
	 * @return
	 */
	public String reverse()
	{
		ArrayList<String> reversedLogicalChars = new ArrayList<>(getLogicalChars());
		Collections.reverse(reversedLogicalChars);
		
		StringBuilder sb = new StringBuilder();
		
		for(String s : reversedLogicalChars){
			sb.append(s);
		}
		
		return sb.toString();
	}; 
	
	/**
	 * Replaces a specific sub-string with a substitute_string
	 * if the sub-string is not found, it does nothing
	 * It does not disturb the original string
	 * It returns a new string
	 * @return
	 */
	public String replace(String sub_string, String substitute_string)
	{
		return word.replace(sub_string, substitute_string);
	}
	
	/**
	 * Add a logical character at the specified index
	 * It does not disturb the original string
	 * It returns a new string
	 * @return
	 */
	public String addCharacterAt(int index, String a_logical_char)
	{
		ArrayList<String> chars = new ArrayList<String>(logicalChars);
		chars.add(index, a_logical_char);
		
		StringBuilder sb = new StringBuilder();
		for(String s : chars){
			sb.append(s);
		}
		return sb.toString();
	}
	
	/**
	 * Add a logical character at the end of the word
	 * It does not disturb the original string
	 * It returns a new string
	 * @return
	 */
	public String addCharacterAtEnd(String a_logical_char)
	{
		return new StringBuffer(word).insert(word.length(), a_logical_char).toString();
	}
	
	/**
	 * Compares the given word with the original word
	 * If there is a match on any logical character, it returns true
	 * @return
	 */
	public boolean isIntersecting(String word_2)
	{
		for(String s : logicalChars){
			if(word_2.indexOf(s) != -1){
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Compares the given word with the original word
	 * And returns the count of matches on the logical characters
	 * @return
	 */
	public int getIntersectingRank(String word_2)
	{
		int count=0;
		for(String s : logicalChars){
			if(word_2.indexOf(s) != -1){
				count++;
			}
		}
		
		return count;
	}
	
	
	/**
	 * This method gets a logical character at the specified index
	 * @param index
	 * @return
	 */
	public String logicalCharAt(int index)
	{
		return logicalChars.get(index);
	}
	
	/**
	 * This method gets a unicode code point at the specified index
	 * @param index
	 * @return
	 */ 
	public int codePointAt(int index)
	{
		return word.codePointAt(index);
	}
	
	// Returns the position at which the first logical character is appearing in the string
	
	/**
	 * This method returns the index at which the logical character is appearing
	 * It returns the first appearance of the logical character
	 * @param index
	 * @return
	 */ 
	int indexOf(String logical_char)
	{
		return word.indexOf(logical_char);
	}
	
	/**
	 * This method compares two strings lexicographically.
	 * It is simplay a wrapper on java compareTo
	 * @param word_2
	 * @return
	 */
	public int compareTo(String word_2)
	{
		return word.compareTo(word_2);
	}
	
	/**
	 * This method compares two strings lexicographically, ignoring case differences.
	 * It is simplay a wrapper on java compareTo
	 * @param word_2
	 * @return
	 */
	public int compareToIgnoreCase(String word_2)
	{
		return word.compareTo(word_2);
	}
	
	/**
	 * This method takes one collection and returns another randomized collection
	 * of string (or logical characters)
	 * @param some_strings
	 * @return
	 */
	public ArrayList<String> randomize(ArrayList<String> some_strings)
	{
		return null;
	}
	
	/**
	 * This method splits the word into a 2-dimensional matrix
	 * based on the number of columns
	 * @param no_of_columns
	 * @return
	 */
	public String[][]  splitWord(int no_of_columns)
	{
		return null;
	}
	
	/**
	 * Returns the string representation of WordProcessor
	 * Basially, prints the word and logicalChars
	 */
	public String toString()
	{
		return word;
	}
	
	/**
	 * compares two strings; wrapper on the java method
	 */
	public boolean equals(String word_2)
	{
		return word.equals(word_2);
	}
	
	/**
	 * compares two strings after reversing the original word
	 */
	public boolean reverseEquals(String word_2)
	{
		return reverse().equals(word_2);
	}
}
