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

/**
 * For undoing poly creations.
 */
public class PolygonCommand extends Command {

	/** The poly. */
	private Polygon poly;

	/**
	 * Create sa poly command with one end point.
	 * 
	 * @param point
	 *            one of the end points
	 */
	public PolygonCommand(Point point) {
		poly = new Polygon(point);
	}

	/**
	 * Sets the second end point.
	 *
	 * @param point
	 *            the second point
	 */
	public void setPolygonPoint(Point point) {
		poly.setPoints(point);
	}

	/**
	 * Executes the command to add the item to the model.
	 */
	@Override
	public void execute() {
		model.addItem(poly);
	}

	/**
	 * Undoes the command by removing the item from the model.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean undo() {
		model.removeItem(poly);
		return true;
	}

	/**
	 * Brings the poly back by calling execute.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean redo() {
		execute();
		return true;
	}

	/**
	 * Ends the command by setting the second end point the same as the first,
	 * if needed.
	 */
	@Override
	public void end() {
		if (poly.getPointY() == null) {
			poly.setPointY(poly.getPointX());
		}
	}
}
