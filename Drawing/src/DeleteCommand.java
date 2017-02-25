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
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Implements the delete command.
 *
 */
public class DeleteCommand extends Command {

	/** The item list. */
	private List<Item> itemList;

	/**
	 * Asks the model for the list of all selected items, so the deletions can
	 * be undone if needed.
	 */
	public DeleteCommand() {
		itemList = new LinkedList<Item>();
		Iterator<Item> iterator = model.getSelectedItems();
		while (iterator.hasNext()) {
			Item item = iterator.next();
			itemList.add(item);
		}
		model.deleteSelectedItems();
	}

	/**
	 * Undoes the operation by adding the items to the selected list in the
	 * model.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean undo() {
		Iterator<Item> iterator = itemList.iterator();
		while (iterator.hasNext()) {
			Item item = iterator.next();
			model.addItem(item);
			model.markSelected(item);
		}
		return true;
	}

	/**
	 * Redoes the command by re-executing it.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean redo() {
		execute();
		return true;
	}

	/**
	 * Re-executes the command.
	 */
	@Override
	public void execute() {
		model.deleteSelectedItems();
	}
}
