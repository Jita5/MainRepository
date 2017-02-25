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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * The button for issuing redo.
 */
@SuppressWarnings("serial")
public class UndoButton extends JButton implements ActionListener {
	/**
	 * The button is set up. It listens to its own clicks.
	 */
	public UndoButton() {
		super("Undo");
		addActionListener(this);
	}

	/**
	 * Asks the Undomanager to undo a command.
	 *
	 * @param event
	 *            the event
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		UndoManager.instance().undo();
	}
}