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
 * For undoing line creations.
 */
public class LineCommand extends Command {

	/** The line. */
	private Line line;

	/**
	 * Create sa line command with one end point.
	 * 
	 * @param point
	 *            one of the end points
	 */
	public LineCommand(Point point) {
		line = new Line(point);
	}

	/**
	 * Sets the second end point.
	 *
	 * @param point
	 *            the second point
	 */
	public void setLinePoint(Point point) {
		line.setPoint2(point);
	}

	/**
	 * Executes the command to add the item to the model.
	 */
	@Override
	public void execute() {
		model.addItem(line);
	}

	/**
	 * Undoes the command by removing the item from the model.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean undo() {
		model.removeItem(line);
		return true;
	}

	/**
	 * Brings the line back by calling execute.
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
		if (line.getPoint2() == null) {
			line.setPoint2(line.getPoint1());
		}
	}
}
