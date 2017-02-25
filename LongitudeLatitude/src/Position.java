
/**
 * The Class Position.  Used with subclass Latitude and Longitude.  This gathers
 * the information needed for each, the information is degree, minute, and direction.
 */
public abstract class Position {
	
	/** The degree. */
	private int degree;
	
	/** The minute. */
	private int minute;		
	
	/**
	 * Instantiates a new position.
	 *
	 * @param degree the degree
	 * @param minute the minute
	 */
	public Position(int degree, int minute){
		this.degree = degree;
		this.minute = minute;	
	}
	
	/**
	 * @return the string
	 * @see java.lang.Object#toString()
	 *  This toString returns to weather information to print 
	 *  the degree and minute for latitude and longitude
	 */
	public String toString() {
		return "degree=" + degree + ", minute=" + minute + ",";
		
	}	
}
