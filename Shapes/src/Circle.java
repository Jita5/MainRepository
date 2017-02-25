/*
 * Author: David Brazeau
 * ICS 372 Prog Assign 2
 * 
 */
import java.awt.Color;
import java.awt.Graphics;

/**
 * The Class Circle.
 */
@SuppressWarnings("serial")
public class Circle extends Figure{
	
	/** The x. */
    private int x;
    
    /** The y. */
    private int y;
    
    /** The r. */
    private int r;
    
    /**
     * Instantiates a new circle.
     *
     * @param x the x
     * @param y the y
     * @param r the r
     * @param color the color
     */
    public Circle(int x, int y, int r, Color color) {
        super(color);       
        this.x = x;
        this.y = y;
        this.r = r;
    }

	/**
	 * Draws a new circle object
	 * @see Figure#draw(java.awt.Graphics)
	 */
	public void draw(Graphics graphics) {
		super.draw(graphics);			
		graphics.fillOval(x - r, y - r, 2*r, 2*r); 
    }		
}
