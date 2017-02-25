/**
 * Author: David Brazeau
 * Date: February 23, 2014
 * 
 */
import java.util.Stack;

/**
 * The Class Graph.
 */
class Graph {
    
	//Declaring variables and data structures
    private final int MAX_VERTS = 26;
    private final int INFINITY = 650;    
    private Vertex vertexList[]; 
    private int adjMat[][];   
    private int numVerts;     
    private int[] preNum;
    private int[] postNum;
    private Stack<Integer> vertVisited = new Stack<Integer>();
    private int count;
    private int parent;
   
    /**
     * Instantiates a new graph.
     */
    public Graph() {
	    //Vertex array
        vertexList = new Vertex[MAX_VERTS]; 
        preNum = new int[MAX_VERTS];
        postNum = new int[MAX_VERTS];
        //2D array for edges
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        numVerts = 0;
        for (int i = 0; i < MAX_VERTS; i++) {           	
            for (int n = 0; n < MAX_VERTS; n++) { 
            adjMat[i][n] = INFINITY;     
            }
        }
    }
   
    /**
     * Adds the vertex. Method to add each vertex to Class Vertex.
     * Stores location and label.
     *
     * @param label the label
     */
    public void addVertex (char label) {
        vertexList[numVerts++] = new Vertex(label);
    }
   
    /**
     * Adds the edge.
     * Adds a new row and all the costs with that row.
     * @param start the start
     * @param end the end
     * @param weight the weight
     */
    public void addEdge (int start, int end, int weight) {
        adjMat[start][end] = weight; 
    }
     
    /**
     * Dfs method that calls the dfs recursive method.
     * This also checks to make sure all nodes are visited
     */
    public void dfs() {
    //Cite** https://www.youtube.com/watch?v=uT1p5Eiw9CE	
	    count = 0;	
	    parent = 0;
	    //Boolean array to track which nodes have been visited
	    boolean [] visited = new boolean [numVerts];
	    
	    //Checking which nodes are still not visited
	    for (int index = 0; index < visited.length; index++) {
		    if (!visited [index]) {	  
		    	dfs(index, count, parent, visited);
		    }
	    } 	 
	    //Assigning post count numbers
	    count++;
	    for (int i = (numVerts - 1); i >= 0; i--) {
	    	if (postNum[i] == 0) {	 
	    		count++;
	    		postNum[i] = count;	  
	    	}
	    }
    }
   
    /**
     * Dfs Recursive function.  Finds the minimum cost path and performs
     * a depth first search until it reaches all nodes.
     *
     * @param index the index
     * @param count the count
     * @param visited the visited
     */
    public void dfs (int index, int count, int parent, boolean[] visited) {
    //Cite**  https://www.youtube.com/watch?v=uT1p5Eiw9CE
	    visited[index] = true;	
	    int minDist = INFINITY;
	    int indexMin = 0;
	    this.count = count;
	    this.parent =  parent;
	    
	    count++;
	    vertVisited.push(index);
	    preNum[index] = count;
	   
	    //Getting least cost node to current node
	    for (int i = 0; i < numVerts; i++) {	    	 
	        if ( adjMat[index][i] <  minDist ) {
	            minDist = adjMat[index][i];
	            indexMin = i;
	        }  
	    }
	    
	    //If the next node hasn't been visited recursively call the method
	    if (!visited[indexMin]) {  
	    	parent = index;            
            dfs(indexMin, count, parent, visited);
	    } 	  	
	    
	    //Check for node having no outputs
	    if (indexMin == 0) {	
	    	count++;
	    	postNum[index] = count;
	    	getNextMin(index, count, parent, visited);	    	
	    }
    }  
    
    /**
     * Gets the next min from the parent index.
     *
     * @param index the index
     * @param count the count
     * @param parent the parent
     * @param visited the visited
     * @return the next min
     */
    public void getNextMin(int index, int count, int parent, boolean[] visited) {
    	visited[parent] = true;
	    int minDist = INFINITY;
	    int indexMin = 0;
	    this.count = count;
	    
	    //Getting least cost node to current node
	    for (int i = 0; i < numVerts; i++) {	    	 
	        if ( adjMat[parent][i] <  minDist && index != i && (!visited[i])) {
	            minDist = adjMat[parent][i];
	            indexMin = i;
	        }  
	    } 	
	    dfs(indexMin, count, parent, visited);
    }
    
    /** 
     * toString to gather the data and return output to write file.
     * @see java.lang.Object#toString()
     */
    public String toString() {
    	String order = "";
    	for (int i = 0; i < vertVisited.size(); i++) {
    		int x = vertVisited.get(i);
    		//Printing out vertexes in order visited
    		order += String.valueOf(vertexList[x].label);    		
    	}
    	order += "\r\n";
    	
    	for (int i = 0; i < MAX_VERTS; i++) {
    		int x = preNum[i];
    		int y = postNum[i];
    		
    		//Printing out pre order.
    		if (x != 0){    				
    			order += "\r\n" + vertexList[i].label + " " + x;
    		}
    		
    		//Printing out post order.
    		if (y != 0) {
    			order += " " + y;    				
    		}    		   		
    	}    	
		return order;    	
    }
}  //End class Graph