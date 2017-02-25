/*
 * Author: David Brazeau
 * ICS 372 Prog Assign 2
 * 
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class FigureGUI extends JFrame implements ActionListener {
	
	private static enum Action {RECTANGLE, CIRCLE, DATE};	
	private JButton redButton = new JButton("Red");
	private JButton greenButton = new JButton("Green");
	private JButton blueButton = new JButton("Blue");
	private JButton rectangleButton = new JButton("Rectangle");
	private JButton circleButton = new JButton("Circle");
	private JButton exitButton = new JButton("Exit");	
	private JTextArea listArea = new JTextArea(10, 10);
	private GregorianCalendar currentDate;
	private FiguresPanel figuresPanel = new FiguresPanel();
	private Action action; 
	private Point p1;
	private Point p2;
	private Color color;		
	
	public class FiguresPanel extends JPanel implements MouseListener {
		
		/**
		 * Paints the screen with figures from file and date.
		 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
		 */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);				
		}
		/**
		 * Waits for the mouse click and creates the appropriate figures.
		 */
		@Override
		public void mouseClicked(MouseEvent event) {
			switch(action) {
			case RECTANGLE:
				
				//setting point 1
				if (p1 == null || p2 != null){
					p1 = event.getPoint();	
					p2 = null;
				}
				else{					
					//setting point 2
					p2 = event.getPoint();
				}	
				
				//getting x and y from point 1
				int x1 = p1.x;
				int y1 = p1.y;
				int x2;
				int y2;
				
				//only running once p2 is NOT null, so second click
				if(p2 != null){
				x2 = p2.x;
				y2 = p2.y;		
				
				int width = x2 - x1;
				int height = y2 - y1;	
				
				//creating object
				Rectangle rectangle = new Rectangle(x1, y1, width, height, color);
				
				//calling rectangle to draw object
				rectangle.draw(getGraphics());	
				
				//printing object information to listArea
				listArea.append("Rectangle: ["+"x="+x1+","+" y="+y1+","+" width="+width+","+" height="+height+","+" color="+color+"\n");				
				}
				break;
				
			case CIRCLE:
				
				//setting point 2
				if (p1 == null || p2 != null){
					p1 = event.getPoint();
					p2 = null;					
				}
				else{
					
					//setting point 2
					p2 = event.getPoint();
				}				
				int cX = p1.x;
				int cY = p1.y;
				int r;
				
				//only performing once point 2 is not null, so second click
				if(p2 != null){
					
			    //calculating radius
				r = (int)(p1.distance((p2)));						
				Circle circle = new Circle(p2.x, p1.y, r, color);
				
				//calling circle to draw object
				circle.draw(getGraphics());	
				
				//printing object information to listArea
				listArea.append("Circle: ["+"x="+cX+","+" y="+cY+","+" radius="+r+","+" color="+color+"\n");
				}
				break;
				
			case DATE:
								
				break;			
			}			
		}		
		/**
		 * Not used
		 */
		@Override
		public void mouseEntered(MouseEvent arg0) {}
		/**
		 * Not used
		 */
		@Override
		public void mouseExited(MouseEvent arg0) {}
		/**
		 * Not used
		 */
		@Override
		public void mousePressed(MouseEvent arg0) {}
		/**
		 * Not used
		 */
		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}	
	/**
	 * Listener for all buttons. 
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		
		//setting color to red if that button is clicked
		if (event.getSource() == redButton){
			color = Color.red;			
		}
		
		//setting color to green if that button is clicked
		if (event.getSource() == greenButton){
			color = Color.green;			
		}
		
		//setting color to blue if that button is clicked
		if (event.getSource() == blueButton){
			color = Color.blue;			
		}
		
		//setting action to RECTANGLE if that is clicked
		if (event.getSource() == rectangleButton) {				
			action = Action.RECTANGLE;		
		}	
		
		//setting action to CIRCLE if that is clicked
		if(event.getSource() == circleButton){				
			action = Action.CIRCLE;					
		}	
		
		//exiting application if EXIT is clicked
		if(event.getSource() == exitButton){
			 try {
				 //writing serialized object to file
		         FileOutputStream fileOut = new FileOutputStream("figures.ser");
		         ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		         objectOut.writeObject(listArea);
		         objectOut.close();
		         fileOut.close();
		         System.out.printf("Serialized data is saved in user.dir as 'figures.ser'");
		      }catch(IOException i) {
		    	  
		          i.printStackTrace();
		      }
			
			System.exit(0);	
		}		    
	}
	/**
	 * Sets up the entire interface.
	 */
	public FigureGUI(){
		super("Figures GUI");	
		JPanel panel = new JPanel(new GridLayout(1, 3));
		JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
		JPanel listPanel = new JPanel(new GridLayout(1, 1));
		JPanel mainPanel = new JPanel(new GridLayout(1, 4));
		
		currentDate = new GregorianCalendar();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String formattedDate = dateFormat.format(currentDate.getTime());
		JLabel dateLabel = new JLabel();
						
		buttonPanel.add(redButton);
		buttonPanel.add(greenButton);
		buttonPanel.add(blueButton);
		buttonPanel.add(rectangleButton);
		buttonPanel.add(circleButton);
		buttonPanel.add(exitButton);
		listPanel.add(new JScrollPane(listArea));		
		mainPanel.add(figuresPanel);		
		dateLabel.setText(formattedDate);
		figuresPanel.add(dateLabel);
		
		redButton.addActionListener(this);
		greenButton.addActionListener(this);
		blueButton.addActionListener(this);
		rectangleButton.addActionListener(this);
		circleButton.addActionListener(this);
		exitButton.addActionListener(this);
		figuresPanel.addMouseListener(figuresPanel);
					
		panel.add(mainPanel);
		panel.add(buttonPanel);
		panel.add(listPanel);		
		getContentPane().add(panel);
		setSize(1000,  400);		
		setVisible(true);	
		
		/*
		 * Use a File object to represent the figures file. Check
		 * if it exists using the exists method.
		 * 
		 */	
			
		File file = new File("figures.ser");		
		
		 try{ 	         
	         if(file.exists()){
	        	 try {
	                FileInputStream fileIn = new FileInputStream(file);
	                ObjectInputStream oIn = new ObjectInputStream(fileIn);
	                	     
	                Object obj = oIn.readObject();	
	                oIn.close();
	                fileIn.close();
	                
	                System.out.println(obj);
//	                Graphics g = null;
	               
//	                g.getColor();
//					super.paintComponents(g);
	          	               
	             }catch(IOException i) {
	            	 
	                i.printStackTrace();
	                return;
	                
	             }
	        	 catch(ClassNotFoundException c) {
	        		 
	                System.out.println("File not found");
	                c.printStackTrace();
	                return;
	             }
	         }	         
	      }catch(Exception e) {
	    	  
	         // if any error occurs
	         e.printStackTrace();
	      }
				
	}		
	/**
	 * The method creates an FigureGUI object
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		new FigureGUI();
	}
}







