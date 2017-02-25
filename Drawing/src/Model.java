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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 * The model in the MVC pattern. Stores all items organized as two lists.
 *
 */
public class Model extends Observable {

	/** The item list. */
	private List<Item> itemList;

	/** The selected list. */
	private List<Item> selectedList;
	// list of "currently selected" items
	/** The ui context. */
	private static UIContext uiContext;

	/**
	 * Creates the two lists, one to store items not selected and the other to
	 * store selected items.
	 */
	public Model() {
		itemList = new LinkedList<Item>();
		selectedList = new LinkedList<Item>();
	}

	/**
	 * Stores the UI context.
	 *
	 * @param uiContext
	 *            the UI context to be used
	 */
	public static void setUI(UIContext uiContext) {
		Model.uiContext = uiContext;
		Item.setUIContext(uiContext);
	}

	/**
	 * Marks an item as selected by moving it to the selceted list.
	 * 
	 * @param item
	 *            the item that is selected
	 */
	public void markSelected(Item item) {
		if (itemList.contains(item)) {
			itemList.remove(item);
			selectedList.add(item);
			updateView();
		}
	}

	/**
	 * Marks an item as unselected by moving it from the selceted list.
	 * 
	 * @param item
	 *            the item that is unselected
	 */
	public void unSelect(Item item) {
		if (selectedList.contains(item)) {
			selectedList.remove(item);
			itemList.add(item);
			updateView();
		}
	}

	/**
	 * Deletes the selected items.
	 */
	public void deleteSelectedItems() {
		selectedList.clear();
		updateView();
	}

	/**
	 * Stores a new item.
	 *
	 * @param item
	 *            the new item
	 */
	public void addItem(Item item) {
		itemList.add(item);
		updateView();
	}

	/**
	 * Removes an item.
	 *
	 * @param item
	 *            the item to be removed
	 */
	public void removeItem(Item item) {
		itemList.remove(item);
		updateView();
	}

	/**
	 * Returns an enumeration of the unselected items.
	 *
	 * @return enumeration of the unselected items
	 */
	public Iterator<Item> getItems() {
		return itemList.iterator();
	}

	/**
	 * Notifies the view.
	 */
	public void updateView() {
		setChanged();
		notifyObservers(null);
	}

	/**
	 * Returns an enumeration of the selected items.
	 *
	 * @return enumeration of the selected items
	 */
	public Iterator<Item> getSelectedItems() {
		return selectedList.iterator();
	}

	/**
	 * Saves the items in the specified file.
	 *
	 * @param fileName
	 *            file to be used for storing
	 */
	public void save(String fileName) {
		try {
			FileOutputStream file = new FileOutputStream(fileName);
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(itemList);
			output.writeObject(selectedList);
			output.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Retrieves the items in the specified file.
	 *
	 * @param fileName
	 *            file that contains the items
	 */
	@SuppressWarnings("unchecked")
	public void retrieve(String fileName) {
		try {
			FileInputStream file = new FileInputStream(fileName);
			ObjectInputStream input = new ObjectInputStream(file);
			itemList = (List<Item>) input.readObject();
			selectedList = (List<Item>) input.readObject();
			Item.setUIContext(uiContext);
			updateView();
			input.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	}
}
