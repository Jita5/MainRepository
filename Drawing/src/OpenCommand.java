/**
 * The Class OpenCommand.
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
 * The command to open a file; can't be undone.
 *
 */
public class OpenCommand extends Command {

	/** The file name. */
	private String fileName;

	/**
	 * Creates the command to open the file with the given name.
	 * 
	 * @param fileName
	 *            the name of the file
	 */
	public OpenCommand(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * The file is opened by having the model open and read the file.
	 */
	@Override
	public void execute() {
		model.retrieve(fileName);
	}

	/**
	 * Undo does not work for open.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean undo() {
		return false;
	}

	/**
	 * Redo does not work for open.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean redo() {
		return false;
	}
}
