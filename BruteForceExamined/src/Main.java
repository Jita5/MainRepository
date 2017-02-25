/**
 * Author: David Brazeau
 * Date: April 4, 2015
 * This program is designed to perform two calculations for a TSP.  The first
 * will involve a brute force approach, calculating all the possible variations
 * for routes and then calculating their totals.  This gurantees an optimal solution
 * but can take a long time.  The second approach is to use dynamic programming
 * to find the optimal solution.  This method works extremely fast in comparison
 * and essentially is using a greedy algorithm to pick the paths as it goes.
 * The maximum input this program accepts is 15 cities and will calculate the
 * optimal route from start city, and visit all the other cities and back to 
 * the start city.
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 * The Class ProgramTwo.
 */
class Main {

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
	    int count = 0;
	    String fileName;
	    String line = "";
	    String city = "";
	    File outFileBF = null;
	    File outFileDP = null;
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
	 	    //Creating scanner to read in the file         	            	            	                    
	 	    @SuppressWarnings("resource")
	 	    Scanner sc = new Scanner(selectedFile);  
	 	    if( sc.hasNextLine()) {
	 	    	city = sc.nextLine().replaceAll("(^[A-Z]|-*)", "");	 	    	
	 	    }
	 	    //Looping the input file and storing into string
	 	    while (sc.hasNext()){	
	 	    	line += sc.nextLine() + "\r\n";
	 	    	count++;
	 	    }
	 	    //Creating patch for new file with new name format
	 	    outFileBF = new File (dest + "\\" + "bf"+count+"cities" + ".txt");
	 	    outFileDP = new File (dest + "\\" + "dp"+count+"cities" + ".txt");
	    }
	    //Creating new instance
		Data theData = new Data(line, city);
        
        //Cite** http://stackoverflow.com/questions/11883294/writing-to-txt-file-from-stringwriter
        //FileWriter to write output to file
        FileWriter fw = new FileWriter(outFileBF);
        StringWriter sw = new StringWriter();
        sw.write(theData.toString());
        fw.write(sw.toString());
        fw.close(); 
        sw.close();
        FileWriter fw2 = new FileWriter(outFileDP);
        StringWriter sw2 = new StringWriter();
        sw2.write(Data.dpOutput);
        fw2.write(sw2.toString());
        fw2.close(); 
        sw2.close();        
    }  //End main()
}  //End class ProgramTwo
/**
 * CITED SOURCE(s), they are also commented in by code used:
 * https://www.youtube.com/watch?v=uT1p5Eiw9CE
 * http://stackoverflow.com/questions/11883294/writing-to-txt-file-from-stringwriter
 * https://sites.google.com/site/indy256/algo/dynamic_tsp
 *
 */