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
import java.awt.Image;
import java.awt.Point;

/**
 * Implements ability to import an image; stores the end points.
 *
 */
public class ImpImage extends Item {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The point1. */
	private Point point1;

	/** The point2. */
	private Point point2;

	/** The img. */
	private Image img;

	/** The height. */
	private int height;

	/** The width. */
	private int width;

	/**
	 * Creates a label object with the starting point determined.
	 *
	 * @param point1
	 *            the point1
	 * @param point2
	 *            the point2
	 */
	public ImpImage(Point point1, Point point2) {
		this.point1 = point1;
		this.point2 = point2;
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
	 * Sets the img.
	 *
	 * @param image
	 *            the new img
	 */
	public void setImg(Image image) {
		img = image;
	}

	/**
	 * Gets the img.
	 *
	 * @return the img
	 */
	public Image getImg() {
		return img;
	}

	/**
	 * Sets the img width.
	 *
	 * @param x
	 *            the new img width
	 */
	public void setImgWidth(int x) {
		width = x;
	}

	/**
	 * Sets the img height.
	 *
	 * @param x
	 *            the new img height
	 */
	public void setImgHeight(int x) {
		height = x;
	}

	/**
	 * Gets the img width.
	 *
	 * @return the img width
	 */
	public int getImgWidth() {
		return width;
	}

	/**
	 * Gets the img height.
	 *
	 * @return the img height
	 */
	public int getImgHeight() {
		return height;
	}

	/**
	 * Checks if the given point is in the label.
	 *
	 * @param point
	 *            the point
	 * @return true, if successful
	 */
	@Override
	public boolean includes(Point point) {
		return ((distance(point, point1) < 10.0) || (distance(point, point2) < 10.0));
	}

	/**
	 * Displays the label.
	 */
	@Override
	public void render() {
		uiContext.draw(this);
	}
}