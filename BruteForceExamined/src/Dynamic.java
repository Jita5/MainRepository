import java.util.Arrays;

/**
 * The Class Dynamic.
 */
public class Dynamic {
	
	/** The order. */
	public static int order[];
	
	/** The total. */
	public static int total;
	
	/**
	 * Dynam calc.
	 *
	 * @param a the a
	 * @param x the x
	 * @return the int
	 */
	public static int dynamCalc(int a[][], int x) {
		int dist [][] = a;		
		
		//Cited: https://sites.google.com/site/indy256/algo/dynamic_tsp
		int n = a.length;
	    int[][] dp = new int[1 << n][n];
	    for (int[] d : dp)
	      Arrays.fill(d, Integer.MAX_VALUE / 2);
	    dp[1][0] = 0;
	    for (int mask = 1; mask < 1 << n; mask += 2) {	
	      for (int i = 1; i < n; i++) {	 
	        if ((mask & 1 << i) != 0) {
	          for (int j = 0; j < n; j++) {  
	            if ((mask & 1 << j) != 0) {
	              dp[mask][i] = Math.min(dp[mask][i], dp[mask ^ (1 << i)][j] + dist[j][i]);	              
	            }	            
	          }
	        }
	      }
	    }
	    int res = Integer.MAX_VALUE;
	    for (int i = 1; i < n; i++) {	      
	      res = Math.min(res, dp[(1 << n) - 1][i] + dist[i][0]);
	      total = res;	      
	    }

	    // reconstruct path
	    int cur = (1 << n) - 1;	    
	    order = new int[n+1];
	    int last = 0;
	    for (int i = n - 1; i >= 1; i--) {
	      int bj = -1;
	      for (int j = 1; j < n; j++) {
	        if ((cur & 1 << j) != 0 && (bj == -1 || dp[cur][bj] + dist[bj][last] > dp[cur][j] + dist[j][last])) {
	          bj = j;	
	        }
	      }
	      order[i] = bj;
	      cur ^= 1 << bj;
	      last = bj;
	    }
	    return res;
	  }//Cited: https://sites.google.com/site/indy256/algo/dynamic_tsp
}