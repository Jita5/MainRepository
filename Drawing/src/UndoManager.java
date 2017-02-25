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
import java.util.Stack;

/**
 * The undo manager essentially supports undo and redo operations. In addition,
 * it has a command to begin and end commands.
 * 
 * @author mh6624pa
 *
 */
public class UndoManager {

	/** The model. */
	private static Model model;

	/** The history. */
	private Stack<Command> history;

	/** The redo stack. */
	private Stack<Command> redoStack;

	/** The manager. */
	private static UndoManager manager;

	/** The current command. */
	private Command currentCommand;

	/**
	 * Sets up the stacks.
	 */
	private UndoManager() {
		history = new Stack<Command>();
		redoStack = new Stack<Command>();
	}

	/**
	 * For the singleton pattern.
	 *
	 * @return the instance
	 */
	public static UndoManager instance() {
		if (manager == null) {
			manager = new UndoManager();
		}
		return manager;
	}

	/**
	 * sets the model.
	 *
	 * @param model
	 *            the model
	 */
	public static void setModel(Model model) {
		UndoManager.model = model;
	}

	/**
	 * Pushes a command onto the history stack. If a previous command is
	 * unfinished, it is ended first. The redo stack (future) is cleared and the
	 * command is executed.
	 *
	 * @param command
	 *            the command
	 */
	public void beginCommand(Command command) {
		if (currentCommand != null) {
			currentCommand.end();
			history.push(currentCommand);
		}
		currentCommand = command;
		redoStack.clear();
		command.execute();
	}

	/**
	 * Normal end of a command. The command is ended and pushed onto the history
	 * stack.
	 *
	 * @param command
	 *            the command
	 */
	public void endCommand(Command command) {
		command.end();
		history.push(command);
		currentCommand = null;
		model.updateView();
	}

	/**
	 * Undoes the command by calling undo on the Command object. The command is
	 * moved to the redo stack (future).
	 */
	public void undo() {
		if (!(history.empty())) {
			Command command = (history.peek());
			if (command.undo()) {
				history.pop();
				redoStack.push(command);
			}
		}
	}

	/**
	 * Redoes the command by calling redo on the Command object that is on top
	 * of the redo stack (future). The command is then pushed onto the history
	 * stack.
	 */
	public void redo() {
		if (!(redoStack.empty())) {
			Command command = (redoStack.peek());
			if (command.redo()) {
				redoStack.pop();
				history.push(command);
			}
		}
	}
}