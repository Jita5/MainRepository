/**
 * Author: David Brazeau
 * Date: February 4th, 2015
 * 
 * This is the driver class for the PushbackableTokenizer
 * and will test that a user can enter a string and be able to call for the next
 * token in that string or pushback an already read token. 
 *
 */
public class TokenizerDriver{
	
	/**
	 * The main method.  This is the driver class for the PushbackableTokenizer
	 * and will test that a user can enter a string and be able to call for the next
	 * token in that string or pushback an already read token.  The user can do this 
	 * many times as they would like as long as the stack is not empty.  If the stack 
	 * is empty, the program will exit.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		//Creating new PushbackTokenizer object
		PushbackableTokenizer pushbackTokenizer = new PushbackTokenizer("This is a "
				+ "test ok! It is also a very long string to print out for this test.");
		
		System.out.println(pushbackTokenizer.nextToken()); //prints This
		System.out.println(pushbackTokenizer.nextToken()); //prints is
		System.out.println(pushbackTokenizer.nextToken()); //prints a
				
		pushbackTokenizer.pushback(); // pushes back “a”
		pushbackTokenizer.pushback(); // pushes back “is”
		
		System.out.println(pushbackTokenizer.nextToken());//prints is
		System.out.println(pushbackTokenizer.nextToken());//prints a
		System.out.println(pushbackTokenizer.nextToken());//prints test
		
		pushbackTokenizer.pushback(); // pushes back “test”
		pushbackTokenizer.pushback(); // pushes back “a”
		
		System.out.println(pushbackTokenizer.nextToken());//prints a
		System.out.println(pushbackTokenizer.nextToken());//prints test
		System.out.println(pushbackTokenizer.nextToken());//prints ok!
				
		System.out.println(pushbackTokenizer.nextToken());//prints It
		System.out.println(pushbackTokenizer.nextToken());//prints is
		System.out.println(pushbackTokenizer.nextToken());//prints also		
		
		pushbackTokenizer.pushback(); // pushes back “also”
		pushbackTokenizer.pushback(); // pushes back “is”
		pushbackTokenizer.pushback(); // pushes back “It”
		pushbackTokenizer.pushback(); // pushes back “ok!”
		
		System.out.println(pushbackTokenizer.nextToken());//prints ok!
		System.out.println(pushbackTokenizer.nextToken());//prints It
		System.out.println(pushbackTokenizer.nextToken());//prints is
		System.out.println(pushbackTokenizer.nextToken());//prints also
		System.out.println(pushbackTokenizer.nextToken());//prints a
		System.out.println(pushbackTokenizer.nextToken());//prints very
		System.out.println(pushbackTokenizer.nextToken());//prints long
		System.out.println(pushbackTokenizer.nextToken());//prints string
		System.out.println(pushbackTokenizer.nextToken());//prints to
		System.out.println(pushbackTokenizer.nextToken());//prints print
		System.out.println(pushbackTokenizer.nextToken());//prints out
		System.out.println(pushbackTokenizer.nextToken());//prints for
		System.out.println(pushbackTokenizer.nextToken());//prints this
		System.out.println(pushbackTokenizer.nextToken());//prints test.
				
		System.out.println(pushbackTokenizer.nextToken());//Check for empty stack
		System.out.println(pushbackTokenizer.nextToken());//Check for empty stack
	}
}
