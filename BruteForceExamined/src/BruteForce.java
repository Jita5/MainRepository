
/**
 * The Class BruteForce.
 */
public class BruteForce {
	
	/** The short path. */
	static String shortPath = "";
	
	/** The short total. */
	static int shortTotal = 999999999;
	
	/** The route and total. */
	static String routeAndTotal = "";	
	
	/**
	 * Bf clac.
	 *
	 * @param a the a
	 * @param k the k
	 */
	public static void bfClac(int a[], int k) {
		
		String convert = "";
		String shortestPath = "";
		int route[] = a;
		int length = k;
		int path = 0;
		int n = 0;
		int m = 0;
		
		for (int i = 0; i < length; i++) {			
			n = route[i];
			m = route[i+1];			
			path += Data.adjMat[n][m];			
			convert = Data.cityList[n];
			shortestPath += Data.cityList[n] + " ";
			routeAndTotal += convert + " ";
		}			
		routeAndTotal += path + "\r\n";		
		if (path < shortTotal) {
			shortTotal = path;
			shortPath = shortestPath + path;	
		}
	}
}