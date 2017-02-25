/**
 * Author: David Brazeau
 * Date: February 23, 2015
 * 
 * A heuristic depth first search program.  This program performs
 * a depth first search and chooses the path to take by heuristic value.
 * This program also keeps track of nodes visited, the order visited, 
 * along with their pre and post numbers.  The input is an adjacency matrix
 * from a text file, and the output is writte to a text file in the same 
 * directory the file was read from with an addition to the filename <_out>.
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;

class HDepthFirstSearch {
    
    /**
     * The main method.
     *
     * @param args the arguments
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void main (String[] args) throws IOException {
	    //Setting dir to where executing from
	    String directory = System.getProperty("user.dir");
	    //New JFileChooser with path
	    JFileChooser fileChooser = new JFileChooser(directory);
	    //Showing dialog
	    int result = fileChooser.showOpenDialog(fileChooser);
	    //Creating variables to be used
	    String fileName, convert;
	    String alphaOrder = "";
	    String line = "";
	    char vert;
	    int counter = 0, lineLength = 0, count = 0, index = 0, 
	    	cost = 0, listIterator = 0;	   
	    File outFile = null;
	    //JFileChooser to pick file   
	    if (result == JFileChooser.APPROVE_OPTION) {
		    //Getting selected file
		    File selectedFile = fileChooser.getSelectedFile();
	 	    //Getting dir of selected file
	 	    String dest = selectedFile.getParent();
	 	    //Getting file name
	 	    fileName = selectedFile.getName();
	 	    //Removing file extension
	 	    fileName = fileName.substring(0, fileName.lastIndexOf("."));
	 	    //Creating patch for new file with new name format
	 	    outFile = new File (dest + "\\" + fileName + "_out.txt");   
	 	    //Creating scanner to read in the file         	            	            	                    
	 	    @SuppressWarnings("resource")
	 	    Scanner sc = new Scanner(selectedFile);  
	 	    //Looping the input file and storing into string
	 	    while (sc.hasNext()){	 	    	
	 	        line += sc.next().replaceAll("n", "0") + ",";
	 	        counter++;				        		        	           		    		
	 	    }	
	    }
	   
	    //Creating new instance of Graph
	    Graph theGraph = new Graph();	
	    //Array list to store cost to all nodes
	    List<Integer> weights = new ArrayList<Integer>();
	    //Get variable to know number of rows/columns
	    lineLength =  (int) Math.sqrt(counter);
	    //Storing scanned file into array
	    String [] stAry = line.split(",");		    
	   
	    //Get the vertex names out and add to Graph
	    for (int i = 1; i < lineLength; i++) {		
		    convert = stAry[i];
		    alphaOrder += convert;
		    vert = convert.charAt(0);
		    theGraph.addVertex(vert);
	    }
	   
	    //Get the costs out and putting in array list   
	    count = 1;
	    index = 0;	
	    for (int j = 1; j < lineLength; j++) {
		    count++;
		    index++;
		    for (int i = ((index * lineLength) + 1); i < (lineLength * count); i++) {			
			    cost = Integer.parseInt(stAry[i]);
			    weights.add(cost);			   
		    }		
	    }
	   
	    //Adding weight for every edge to Graph if it is not 0; 
	    //If its 0 ignore it and go next edge
	    count = 1;
	    index = 0;
	    listIterator = 0;
	    for (int i = 0; i < lineLength - 1; i++){
		    for (int j = 0; j < lineLength -1; j++){
			    int x = 0;
			    x = weights.get(listIterator);
			    listIterator++;
			    if ( x != 0){
				    theGraph.addEdge(i, j, x);
			    }
		    }
	    }
	    
	    //Instantiate a new instance of Graph
        theGraph.dfs(); 
        
        //Cite** http://stackoverflow.com/questions/11883294/writing-to-txt-file-from-stringwriter
        //FileWriter to write output to file
        FileWriter fw = new FileWriter(outFile);
        StringWriter sw = new StringWriter();
        sw.write(theGraph.toString() + "\r\n" + "" + "\r\n" + alphaOrder);
        fw.write(sw.toString());
        fw.close();  
        
    }  //End main()
}  //End class HDepthFirstSearch
/**
 * CITED SOURCE(s), they are also commented in by code used:
 * https://www.youtube.com/watch?v=uT1p5Eiw9CE
 * http://stackoverflow.com/questions/11883294/writing-to-txt-file-from-stringwriter
 *
 */