/**
 * 
 * @author David Brazeau
 * @Copyright (c) 2015
 *
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - The name David Brazeau may not be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The author do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.
 */
import java.awt.Point;
import java.util.ArrayList;

/**
 * Represents a Polygon.
 */
public class Polygon extends Item {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The point1. */
	private Point point1;

	/** The point2. */
	private Point point2;

	/** The x s. */
	private double xS;

	/** The point x. */
	private ArrayList<Double> pointX = new ArrayList<Double>();

	/** The y s. */
	private double yS;

	/** The point y. */
	private ArrayList<Double> pointY = new ArrayList<Double>();

	/**
	 * Creates a Polygon with the given endpoints.
	 *
	 * @param point1
	 *            one endpoint
	 * @param point2
	 *            another endpoint
	 */
	public Polygon(Point point1, Point point2) {
		this.point1 = point1;
		this.point2 = point2;
	}

	/**
	 * Creates a Polygon with one endpoint.
	 *
	 * @param point1
	 *            one endpoint
	 */
	public Polygon(Point point1) {
		this(point1, null);
	}

	/**
	 * Creates a Polygon with no specific endpoints.
	 */
	public Polygon() {
	}

	/**
	 * Checks whether the selected point falls within any of the vertices.
	 *
	 * @param point
	 *            the point
	 * @return true iff the given point is close to one of the endpoints
	 */
	@Override
	public boolean includes(Point point) {
		boolean inArea = false;
		double xIn = point.getX();
		double yIn = point.getY();

		for (int i = 0; i < pointX.size(); i++) {
			double casting = 0;
			double xDiff = 0;
			double yDiff = 0;
			double diff = 0;
			casting = pointX.get(i);
			int xCheck = (int) casting;
			casting = pointY.get(i);
			int yCheck = (int) casting;
			xDiff = xIn - xCheck;
			yDiff = yIn - yCheck;
			diff = ((Math.sqrt(xDiff * xDiff + yDiff * yDiff)));
			if (diff < 10.0) {
				inArea = true;
			}
		}
		return inArea;
	}

	/**
	 * Displays the Polygon.
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
	public void setPoints(Point point) {
		point2 = point;
		xS = point2.getX();
		yS = point2.getY();
		pointX.add(xS);
		pointY.add(yS);
	}

	/**
	 * Gets the point x.
	 *
	 * @return the point x
	 */
	public ArrayList<Double> getPointX() {
		return pointX;
	}

	/**
	 * Sets the point x.
	 *
	 * @param pointX
	 *            the new point x
	 */
	public void setPointX(ArrayList<Double> pointX) {
		this.pointX = pointX;
	}

	/**
	 * Gets the point y.
	 *
	 * @return the point y
	 */
	public ArrayList<Double> getPointY() {
		return pointY;
	}

	/**
	 * Sets the point y.
	 *
	 * @param pointY
	 *            the new point y
	 */
	public void setPointY(ArrayList<Double> pointY) {
		this.pointY = pointY;
	}

	/**
	 * Returns a string representation of the Polygon.
	 *
	 * @return a string representation
	 */
	@Override
	public String toString() {
		return "Polygon  from " + point1 + " to " + point2;
	}
}