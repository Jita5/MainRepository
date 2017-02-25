/**
 * The Class SaveCommand.
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
 * The command to save a file; can't be undone.
 *
 */
public class SaveCommand extends Command {

	/** The file name. */
	private String fileName;

	/**
	 * Creates the command to save the drawing in a file with the given name.
	 * 
	 * @param fileName
	 *            the name of the file
	 */
	public SaveCommand(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * The file is saved by having the model do the dirty work of opening the
	 * necessary output file writing to it.
	 */
	@Override
	public void execute() {
		model.save(fileName);
	}

	/**
	 * Undo does not work for save.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean undo() {
		return false;
	}

	/**
	 * Redo does not work for save.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean redo() {
		return false;
	}
}
