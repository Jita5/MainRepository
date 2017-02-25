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

/**
 * Represents a line.
 */
public class Line extends Item {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The point1. */
	private Point point1;

	/** The point2. */
	private Point point2;

	/**
	 * Creates a line with the given endpoints.
	 *
	 * @param point1
	 *            one endpoint
	 * @param point2
	 *            another endpoint
	 */
	public Line(Point point1, Point point2) {
		this.point1 = point1;
		this.point2 = point2;
	}

	/**
	 * Creates a line with one endpoint.
	 *
	 * @param point1
	 *            one endpoint
	 */
	public Line(Point point1) {
		this(point1, null);
	}

	/**
	 * Creates a line with no specific endpoints.
	 */
	public Line() {
	}

	/**
	 * Checks whether the given point falls within the line.
	 *
	 * @param point
	 *            the point
	 * @return true iff the given point is close to one of the endpoints
	 */
	@Override
	public boolean includes(Point point) {
		return ((distance(point, point1) < 10.0) || (distance(point, point2) < 10.0));
	}

	/**
	 * Displays the line.
	 */
	@Override
	public void render() {
		uiContext.draw(this);
	}

	/**
	 * Sets one of the endpoints.
	 *
	 * @param point
	 *            an endpoint
	 */
	public void setPoint1(Point point) {
		point1 = point;
	}

	/**
	 * Sets one of the endpoints.
	 *
	 * @param point
	 *            an endpoint
	 */
	public void setPoint2(Point point) {
		point2 = point;
	}

	/**
	 * Returns one of the endpoints.
	 *
	 * @return an endpoint
	 */
	public Point getPoint1() {
		return point1;
	}

	/**
	 * Returns one of the endpoints.
	 *
	 * @return an endpoint
	 */
	public Point getPoint2() {
		return point2;
	}

	/**
	 * Returns a string representation of the line.
	 *
	 * @return a string representation
	 */
	@Override
	public String toString() {
		return "Line  from " + point1 + " to " + point2;
	}
}