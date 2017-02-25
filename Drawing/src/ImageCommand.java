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
 * 
 */
import java.awt.Image;
import java.awt.Point;

/**
 * Implements the Image command, so image creations can be undone.
 *
 */
public class ImageCommand extends Command {

	/** The image. */
	private ImpImage image;

	/**
	 * Creates the image.
	 *
	 * @param point
	 *            starting point
	 */
	public ImageCommand(Point point) {
		image = new ImpImage(point, null);
	}

	/**
	 * Sets the first end point.
	 *
	 * @param point
	 *            the first point
	 */
	public void setPolygonPoint1(Point point) {
		image.setPoint1(point);
	}

	/**
	 * Sets the second end point.
	 *
	 * @param point
	 *            the second point
	 */
	public void setPolygonPoint2(Point point) {
		image.setPoint2(point);
	}

	/**
	 * Sets the image.
	 *
	 * @param img
	 *            the new image
	 */
	public void setImage(Image img) {
		image.setImg(img);
	}

	/**
	 * Sets the image width.
	 *
	 * @param x
	 *            the new image width
	 */
	public void setImageWidth(int x) {
		image.setImgWidth(x);
	}

	/**
	 * Sets the image height.
	 *
	 * @param x
	 *            the new image height
	 */
	public void setImageHeight(int x) {
		image.setImgHeight(x);
	}

	/**
	 * Executes the command, essentially by supplying it to the model.
	 */
	@Override
	public void execute() {
		model.addItem(image);
	}

	/**
	 * Removes the image.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean undo() {
		model.removeItem(image);
		return true;
	}

	/**
	 * Re-creates the image by supplying it to the model.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean redo() {
		execute();
		return true;
	}
}
