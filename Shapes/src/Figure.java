/**
 * Author Brahma Dathan
 * Date: January 15, 2015
 */
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
/**
 * Serves as a type for all figures in the simple
 * drawing program. Some implementation might be added 
 * at a future date.
 * @author Brahma Dathan
 *
 */
@SuppressWarnings("serial")
public abstract class Figure implements Serializable {
	
	/** The color. */
	protected Color color;
	
	/**
	 * Instantiates a new figure.
	 *
	 * @param color the color
	 */
	public Figure(Color color){
		this.color = color;
	}
	
	/**
	 * Draws the figure using the given Graphics parameter.
	 *
	 * @param graphics the Graphics object for drawing the figure
	 */
	public void draw(Graphics graphics) {
		graphics.setColor(color);		
	}
}
