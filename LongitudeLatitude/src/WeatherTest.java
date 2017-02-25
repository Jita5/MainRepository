/**
 * The Class WeatherTest.
 */
public class WeatherTest {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args){
		
		double [] minTemp = new double[10];
		double [] maxTemp = new double[10];
		int [] degree = new int[10];
		int [] minute = new int[10];
		int [] degree2 = new int[10];
		int [] minute2 = new int[10];
		String [] lat = {"N","S","N","S","N","S","S","N","S","N"};
		String [] longi = {"E","W","E","W","W","E","E","W","E","W"};
		
		for(int i = 0; i < 10; i++){
			degree [i] = (int)(Math.random()*89+10);			
			minute [i] = (int)(Math.random()*49+10);
			degree2 [i] = (int)(Math.random()*89+10);
			minute2 [i] = (int)(Math.random()*49+10);
			minTemp[i] = (Math.random()*11);
			if( i % 2 == 0)
				minTemp [i] = (Math.random()*-35);
			maxTemp [i] = (Math.random()*112);			
		
			Latitude latitude = new Latitude(degree[i], minute[i], lat[i]);
			Longitude longitude = new Longitude(degree2[i], minute2[i], longi[i]);
			
			WeatherInformation string = new WeatherInformation(latitude,
												longitude, minTemp [i], maxTemp [i]);
			System.out.println(string);							
		}
	}
}
