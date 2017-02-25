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
 * Implements the Label command, so label creations can be undone.
 *
 */
public class LabelCommand extends Command {

	/** The label. */
	private Label label;

	/**
	 * Creates the label.
	 *
	 * @param point
	 *            starting point
	 */
	public LabelCommand(Point point) {
		label = new Label(point);
	}

	/**
	 * Adds a character to the label.
	 *
	 * @param character
	 *            the character
	 */
	public void addCharacter(char character) {
		label.addCharacter(character);
		model.updateView();
	}

	/**
	 * Removes the last character from the label.
	 */
	public void removeCharacter() {
		label.removeCharacter();
		model.updateView();
	}

	/**
	 * Executes the command, essentially by supplying it to the model.
	 */
	@Override
	public void execute() {
		model.addItem(label);
	}

	/**
	 * Removes the label.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean undo() {
		model.removeItem(label);
		return true;
	}

	/**
	 * Re-creates the label by supplying it to the model.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean redo() {
		execute();
		return true;
	}
}
