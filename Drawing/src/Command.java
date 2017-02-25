/**
 * The Class Command.
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
/**
 * The Command class implements the Command pattern.
 *
 */
public abstract class Command {

	/** The manager. */
	protected static UndoManager manager = UndoManager.instance();

	/** The model. */
	protected static Model model;

	/**
	 * Remember the model so the commands can ask the model to change the view.
	 *
	 * @param model
	 *            the model
	 */
	public static void setModel(Model model) {
		Command.model = model;
	}

	/**
	 * Undoes the command.
	 *
	 * @return true iff the operation is successful
	 */
	public abstract boolean undo();

	/**
	 * Redoes the command.
	 * 
	 * @return true iff the operation is successful
	 */
	public abstract boolean redo();

	/**
	 * Executes command.
	 */
	public abstract void execute();

	/**
	 * The default end of a command.
	 */
	public void end() {
	}
}
