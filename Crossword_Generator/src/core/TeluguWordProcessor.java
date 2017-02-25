package core;
// The complete implementation along with the documentation 
// will count towards two software engineering assignments
// Software Engineering Assignment 2
// Software Engineering Assignment 3


import java.util.*;


import core.WordProcessor;


/**
 * This class provides several operations in the context of single word.
 * This class is expected to work against multiple languages so the implementation
 * should not rely on the assumption that the string contain characters.
 */

public class TeluguWordProcessor extends WordProcessor {
	
	
	// Instantiating the Telugu LetterProcessor
	LetterProcessor te = new LetterProcessor();
	
	/**
	 * Overloaded constructor that takes the word
	 * @param a_word
	 */
	
	public TeluguWordProcessor(String a_word)
	{
		setWord(a_word);
	}
	
	/**
	 * Overloaded constructor that takes the logical characters as input
	 * @param some_logical_chars
	 */
	public TeluguWordProcessor(ArrayList<String> some_logical_chars)
	{
		setLogicalChars(some_logical_chars);
	}
	
	
	
	/**
	 * Telugu method - overriding the parent method in WordProcessor
	 * 
	 * Parses the specified string of characters in a native script into a list
	 * of logical  characters.
	 * 
	 * It populates  "ArrayList<String> logicalChars;" from the "String word"
	 */

	public void parseToLogicalChars() {
		// get the arry of logical characters from Telugu Letter Processor
	   String[] logical_chars =   te.parseStringToLogicalCharacters(getWord());
	   // then convert the data type to array list
	 
	   List<String> list_of_logical_chars = Arrays.<String>asList(logical_chars);
	   ArrayList<String> array_list_of_logical_chars = new ArrayList<String>(list_of_logical_chars);
	   this.setLogicalChars(array_list_of_logical_chars);  
	}

	/**
	 * Returns the word strength
	 * Word Strength = 1  --> Simple Words (No Diacritics = No Gunitaalu)
	 * Word Strength = 2 ---> Diacritics (Gunitaalu)
	 * Word Strength = 3 and 4 --> 2_Consonant Blends  
	 * Word Strength = 4 and 5 --> 3_consonant Blends
	 * Here are some examples
	            అవతల , కడవ ,  పలకల కలప  (1)	
		  తార , గంప, మా తాత పిలక  (2)
  	            గుండు, అత్త, ఆర్య (3)	
		  కిర్క్, యూనివర్సిటీ, మెట్రో పాలిటన్ స్టేట్ యూనివర్సిటీ  (4)
		  రాష్ట్రము (5)
		  స్ట్రా, ఆస్ట్రేలియా (6)
	 */
	public int getWordStrength()
	{	
		// get the length of the word in terms of logical characters
		int length = this.getLength();  
		
		// counter variable to keep track of the strength
		// strength = number of code points in each logical characters
		int strength = 1; 
		
		for (int i=0; i < length; i++)
		{
			// get the logical character
			String logical_char = this.logicalCharAt(i);
			
			// len_2 gives the code point length of the logical character
			int no_code_points = logical_char.length();  
			
			// increase the strength if needed
			if (no_code_points > strength)  { strength = no_code_points;}	
		}
		
		return strength;  
	}	
	
	/**
	 * This method returns the total strength of the word
	 * by adding the strength of each logical character
	 * @return
	 */
	public int getWordWeight()
	{	
		// get the length of the word in terms of logical characters
		int length = this.getLength();  
		
		// counter variable to keep track of the strength
		// weight  = total number of code points in each logical characters
		int weight = 0; 
		
		for (int i=0; i < length; i++)
		{
			// get the logical character
			String logical_char = this.logicalCharAt(i);
			
			// len_2 gives the code point length of the logical character
			weight = weight + logical_char.length();  
			
		}
		System.out.println( this.getWord() + " = " + weight);
		return weight;  
	}
	

	
}

/*
 * An Internal private class Used to compare two Letters based on their
 * Unicode values
 */
 class LetterCompareUnicode implements Comparator<Letter> {

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
