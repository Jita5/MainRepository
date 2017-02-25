/**
 * Author: David Brazeau
 * Date: February 4th, 2015
 */
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * The Class PushbackTokenizer.  This class will put the string from user into
 * a string tokenizer and then populate a stack with the data.  Once the user
 * reads the next token it will display that token.  If the user choses to push a
 * token back, this class will push the tokens back onto the stack in the order
 * they were read to the user.  The user can then read the tokens again and repeat
 * as many times as neccessary.
 */
public class PushbackTokenizer implements PushbackableTokenizer {
	
	//Creating stacks to use for tokens
	Stack<String> stack = new Stack<String>();
	Stack<String> stack2 = new Stack<String>();	
	String data;
	
	/**
	 * Instantiates a new pushbacktokenizer.  This also populates stack2 with
	 * the tokens in the correct reading order.
	 *
	 * @param data the data
	 */
	public PushbackTokenizer(String data) {
		
		//Creating string tokenizer
		this.data = data;		
		StringTokenizer st = new StringTokenizer(data);
		
		//Pushing tokenized string onto first stack
		while (st.hasMoreTokens()) {
			data = st.nextToken();
			stack.push(data);
		}
		
		//Popping tokens off from first stack onto second stack for correct order
		while (!stack.isEmpty()){			
			data = stack.pop();
			stack2.push(data);
		}
	}
	
	/** 
	 * Returns the next available token to the driver class to print out.  If the
	 * stack is empty it will display a message and exit.  This also keeps track of
	 * the tokens already read to user by putting them into the first stack.
	 * @see PushbackableTokenizer#nextToken()
	 */
	@Override
	public String nextToken() {
		
		//If second stack not empty, pop token and return it to Main
		if(!stack2.isEmpty()){			
			data = stack2.pop();	
			//Pushing to popped token that will be read onto first stack, to keep track
			stack.push(data);
		}
		else{
			//Exit if stack is empty
			System.out.println("------No more tokens to read, goodbye------");
			System.exit(0);			
		}		
		return data;		
	}
	
	/**
	 * Checks to see if there are more tokens available to read
	 * returns true if and only if there are.
	 * @see PushbackableTokenizer#hasMoreTokens()
	 */
	@Override
	public boolean hasMoreTokens() {
		
		if(!stack.isEmpty()){		
			System.out.println("has more");
			return true;
		}
		else{
			return false;	
		}		
	}
	
	/**
	 * Pushes the tokens that have already been read back onto the stack.  If the
	 * stack is empty, it will display a message notifying user and exit.
	 * @see PushbackableTokenizer#pushback()
	 */
	@Override
	public void pushback() {
		
		//Popping token from the already read and then pushing back to unread stack
		if(!stack.isEmpty()){
			data = stack.pop();
			stack2.push(data);	
		}
		else{
			//Exit if stack is empty, and no more tokens to pushback
			System.out.println("------No more tokens to push back, goodbye------");
			System.exit(0);	
		}		
	}
}
