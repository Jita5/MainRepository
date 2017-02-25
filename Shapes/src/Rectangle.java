/*
 * Author: David Brazeau
 * ICS 372 Prog Assign 2
 * 
 */
import java.awt.Color;
import java.awt.Graphics;

/**
 * The Class Rectangle.
 */
@SuppressWarnings("serial")
public class Rectangle extends Figure{
	
    /** The x. */
    private int x;
    
    /** The y. */
    private int y;
    
    /** The width. */
    private int width;
    
    /** The height. */
    private int height;
    
    /**
     * Instantiates a new rectangle.
     *
     * @param x the x
     * @param y the y
     * @param width the width
     * @param height the height
     * @param color the color
     */
    public Rectangle(int x, int y, int width, int height, Color color) {
        super(color);       
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

	/**
	 * Draws the new rectangle object
	 * @see Figure#draw(java.awt.Graphics)
	 */
	public void draw(Graphics graphics) {
		super.draw(graphics);
		graphics.fillRect(x, y, width, height); 
    }		
}
