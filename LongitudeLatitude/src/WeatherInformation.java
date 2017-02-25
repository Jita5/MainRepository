
/**
 * The Class WeatherInformation.
 */
public class WeatherInformation implements WeatherRecord{
		
	/** The latitude. */
	Latitude latitude;
	
	/** The longitude. */
	Longitude longitude;
	
	/** The min temperature. */
	double minTemperature;
	
	/** The max temperature. */
	double maxTemperature;	

	/**
	 *  Sets Min Temp.
	 *
	 * @param minTemperature the new min temperature
	 * @see WeatherRecord#setMinTemperature(double)
	 */
	public void setMinTemperature(double minTemperature) {
		this.minTemperature = minTemperature;
	}

	/**
	 *  Sets Max Temp.
	 *
	 * @param maxTemperature the new max temperature
	 * @see WeatherRecord#setMaxTemperature(double)
	 */
	public void setMaxTemperature(double maxTemperature) {
		this.maxTemperature = maxTemperature;
	}
	
	/**
	 * Instantiates a new weather information.
	 *
	 * @param latitude the latitude
	 * @param longitude the longitude
	 * @param minTemperature the min temperature
	 * @param maxTemperature the max temperature
	 */
	public WeatherInformation(Latitude latitude, Longitude longitude, double
							  minTemperature, double maxTemperature){
		
		this.latitude = latitude;
		this.longitude = longitude;
		this.minTemperature = minTemperature;
		this.maxTemperature = maxTemperature;		
	}

	/**
	 * To string.
	 *
	 * @return the string
	 * @see java.lang.Object#toString()
	 * This toString returns to weather information to print 
	 * the full message from the driver class.  This will have all of the information 
	 * gathered from the other classes to compile in this toString.
	 */
	@Override
	public String toString() {
		return "WeatherInformation- {Latitude: " + latitude + " {"
				+ "Longitude: " + longitude + " {Max Temp=" + maxTemperature
				+ ", Min Temp=" + minTemperature + "}";
	}
}
