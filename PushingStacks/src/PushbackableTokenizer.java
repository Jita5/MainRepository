
/**
* A PushbackableTokenizer allows the user to read a token and push it back to the stream
* from which the token was read. Tokens are assumed to be separated by white space.
* Any number of tokens may be pushed back.
* @author Brahma Dathan
*
*/
public interface PushbackableTokenizer {
	/**
	* Returns the next token
	* @return the next token
	*/
	public String nextToken();
	/**
	* Returns true if and only if there are more tokens
	* @return true if there is at least one more token; else false
	*/
	public boolean hasMoreTokens();
	/**
	* The last token read and is not pushed back
	* is pushed back, so it can be read again using nextToken.
	*/
	public void pushback();
}