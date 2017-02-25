
/**
 * The Class Longitude.
 */
public class Longitude extends Position {
	
	/** The east or west. */
	private String eastOrWest;

	/**
	 * Instantiates a new longitude.
	 *
	 * @param degree the degree
	 * @param minute the minute
	 * @param eastOrWest the east or west
	 */
	public Longitude(int degree, int minute, String eastOrWest){
		super(degree, minute);
		this.eastOrWest = eastOrWest;		
	}

	/**
	 * To string.
	 *
	 * @return the string
	 * @see java.lang.Object#toString()
	 *  This toString returns to weather information to print 
	 *  the longitude portion of the message
	 */
	@Override
	public String toString() {
		return super.toString() + " East or West=" + eastOrWest + "}";
	}	
}
