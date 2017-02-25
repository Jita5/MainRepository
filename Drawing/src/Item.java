/**
 * 
 * @author Brahma Dathan and Sarnath Ramnath
 * @Copyright (c) 2010
 
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - Neither the name of Brahma Dathan or Sarnath Ramnath
 *     may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The authors do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.  
 */
import java.awt.Point;
import java.io.Serializable;

/**
 * Superclass for all types of drawable objects.
 *
 */
public abstract class Item implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The ui context. */
	protected static UIContext uiContext;

	/**
	 * Stores reference to the type of UI.
	 * 
	 * @param uiContext
	 *            reference to the
	 */
	public static void setUIContext(UIContext uiContext) {
		Item.uiContext = uiContext;
	}

	/**
	 * Checks whether a point falls within the figure.
	 * 
	 * @param point
	 *            the point
	 * @return true iff the point is in the figure
	 */
	public abstract boolean includes(Point point);

	/**
	 * Calculates the distance between two points.
	 *
	 * @param point1
	 *            one of the two points
	 * @param point2
	 *            one of the two points
	 * @return distance between the points
	 */
	protected double distance(Point point1, Point point2) {
		double xDifference = point1.getX() - point2.getX();
		double yDifference = point1.getY() - point2.getY();
		return ((Math.sqrt(xDifference * xDifference + yDifference
				* yDifference)));
	}

	/**
	 * Draw the figure on the UI.
	 */
	public void render() {
		uiContext.draw(this);
	}
}