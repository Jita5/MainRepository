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
import javax.swing.JOptionPane;

/**
 * The button to save the items.
 *
 */
@SuppressWarnings("serial")
public class SaveButton extends JButton implements ActionListener {

	/** The view. */
	protected View view;

	/**
	 * Creates the button.
	 *
	 * @param jFrame
	 *            the frame where the button lives
	 */
	public SaveButton(View jFrame) {
		super("Save");
		addActionListener(this);
		view = jFrame;
	}

	/**
	 * Handles the click by notifying the controller.
	 *
	 * @param event
	 *            the click event
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		String string = view.getFileName();
		if (string == null) {
			string = JOptionPane.showInputDialog(view,
					"Please specify file name");
			view.setFileName(string);
		}
		SaveCommand command = new SaveCommand(string);
		UndoManager.instance().beginCommand(command);
		UndoManager.instance().endCommand(command);
	}
}
