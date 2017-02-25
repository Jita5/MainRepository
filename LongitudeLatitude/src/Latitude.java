
/**
 * The Class Latitude.
 */
public class Latitude extends Position {
	
	/** The north or south. */
	private String northOrSouth;
	
	/**
	 * Instantiates a new Latitude.
	 *
	 * @param degree the degree
	 * @param minute the minute
	 * @param northOrSouth the north or south
	 */
	public Latitude(int degree, int minute, String northOrSouth){
		super(degree, minute);
		this.northOrSouth = northOrSouth;		
	}

	/**
	 * To string.
	 *
	 * @return the string
	 * @see java.lang.Object#toString()
	 *  This toString returns to weather information to print 
	 *  the Latitude portion of the message
	 */
	@Override
	public String toString() {
		return super.toString()  + " North or South=" + northOrSouth + "}";
	}	
}
