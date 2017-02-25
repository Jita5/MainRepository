import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 * The Class Data.
 */
public class Data {
	
	/** The output. */
	static String output = "";
	
	/** The adj mat. */
	static int adjMat[][];	
	
	/** The city list. */
	static String cityList[]; 
	
	/** The dp output. */
	static String dpOutput = "";
	
	/** The max verts. */
	private final int MAX_VERTS = 15; 		 
	
	/** The route. */
	private int route[];
	
	/** The count. */
	private int count = 0;		
	
	/** The start. */
	private String start = "";
	
	/** The cities. */
	private String cities = "";
	
	/** The order. */
	private String order = "";
	
	/** The bf time start. */
	private long bfTimeStart = 0;
	
	/** The bf time end. */
	private long bfTimeEnd = 0;
	
	/** The dp time start. */
	private long dpTimeStart = 0;
	
	/** The dp time end. */
	private long dpTimeEnd = 0;
	
	/** The bf total time. */
	private long bfTotalTime = 0;
	
	/** The dp total time. */
	private long dpTotalTime = 0;
	
	/** The dynam string. */
	private String dynamString = "";
	
	/**
	 * Instantiates a new data.
	 *
	 * @param line the line
	 * @param city the city
	 */
	public Data(String line, String city){
		String regex = "[A-Za-z]";	 
		this.order = line.replaceAll(regex, "");
		cityList = new String [MAX_VERTS];
		route = new int [MAX_VERTS];
		//String tokenizer to get out the city letters
		StringTokenizer st = new StringTokenizer(city);
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(order);
		 
		//Get the cities out and add to City
		while (st.hasMoreTokens()) {			
			cityList[count] = st.nextToken();
			count++;			 
		}		
		
		//Getting home city out and setting remaining cities
		for (int i = 0; i < count; i++) {
			if (i == 0) {
				start = cityList[i];
			}
			else {
				cities += cityList[i];
			}
		}
		
		//Getting distances out	
		adjMat = new int [count][count];
		int counter = 0;
		String tempStr = "";
		for (int i = 0; i < count; i++) {
			tempStr = sc.nextLine();
			getDistance(tempStr, counter);	
			counter++;
		} 			
		
		//Filling array to hold all costs for each city
		fillArray();
					
		//Start time
		bfTimeStart = System.currentTimeMillis();
		//Getting all possible variations of the routes
		getPerms(start, cities);
		//End time
		bfTimeEnd = System.currentTimeMillis();
		
		//Finding shortest path with dynamic calculation
		dpTimeStart = System.currentTimeMillis();
		Dynamic.dynamCalc(adjMat, count);
		dpTimeEnd = System.currentTimeMillis();
		
		//Converting shortest path to String for Dynamic Calculation output
		for(int i =0; i < count+1; i++) {
			String n;
			int m;
			m = Dynamic.order[i];
			n = cityList[m];
			dynamString += n;			
		}	
		//Dynamic program output with time taken calculated
		dpOutput = "Dynamic Program Algorithm\r\n"+"\r\nShortest route: "+
				    dynamString+ " " + Dynamic.total;
		dpTotalTime = dpTimeEnd - dpTimeStart;		
	}	
	
	//Put distances into adjMat 2D-array
	/**
	 * Gets the distance.
	 *
	 * @param distance the distance
	 * @param counter the counter
	 * @return the distance
	 */
	public void getDistance(String distance, int counter) {
		
		String miles = distance;
		int counting = counter;		
		StringTokenizer st2 = new StringTokenizer(miles); 
		int tempInt = 0;
 	   	 		   		 	        
 	    for (int i = 0; i < count; i++) { 	    		
 	    	if (!st2.hasMoreTokens() && i < count) { 	    			 	    		
	 	    	adjMat [counting][i] = 0;	 	    		
 	    	}
 	    	else {  	    			
 	    		tempInt = Integer.parseInt(st2.nextToken()); 	    		
	 	    	adjMat [counting][i] = tempInt;	
 	    	}
 	    }
	}
	
	//Filling empty portion of array to hold all costs for each city at each point
	/**
	 * Fill array.
	 */
	public void fillArray() {
		
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {				
				if ((adjMat[j][i] != 0) && (adjMat[i][j] == 0)) {
					adjMat[i][j] = adjMat[j][i];
				}
			}		
		}
	}
	
	//Getting all of the possible permutations for the routes
	/**
	 * Gets the perms.
	 *
	 * @param prePost the pre post
	 * @param str the str
	 * @return the perms
	 */
	public void getPerms(String prePost, String str) {
		
	    int n = str.length();  
	    
	    if (n==0) { 		
	    	getIndexes(prePost);
	    }
	    else {
	        for (int i = 0; i < n; i++) {
	            getPerms(prePost + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));	            
	        }
	    }
	}
	
	//Getting the index of each permutation of city to visit
	/**
	 * Gets the indexes.
	 *
	 * @param prePost the pre post
	 * @return the indexes
	 */
	public void getIndexes(String prePost) {
		order = prePost;
		int k = order.length() + 1;
		String n, p;		
		
		for (int j = 0; j < order.length(); j++) {			
			n = String.valueOf(order.charAt(j));
			
			for (int i = 0; i < order.length(); i++) {					
				p = cityList[i];	
				route[k] = 0;				
				
				if (p.equals(n)) {
					route[j] = i;											
				}
			}	
		}
		BruteForce.bfClac(route, k);		
	}
	
	/** String output for writing to file	
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		
		bfTotalTime = bfTimeEnd - bfTimeStart;		
		output = "Brute Force Approach\r\n\r\n"+BruteForce.routeAndTotal;
		System.out.println("Brute Force Shortest route: "+BruteForce.shortPath);		
		System.out.println("Brute Force runtime(in Milliseconds) = " + bfTotalTime);		
		JOptionPane.showMessageDialog(null, BruteForce.shortPath);
		
		System.out.println("\r\nDynam Shortest route: "+dynamString+ " " + Dynamic.total);
		System.out.println("Dynamic Program runtime(in Milliseconds) = " + dpTotalTime);
		
		return output;
	}
}//end Data
	
	  
		
