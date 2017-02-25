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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Creates a button for selecting items.
 *
 */
@SuppressWarnings("serial")
public class SelectButton extends JButton implements ActionListener {

	/** The drawing panel. */
	protected JPanel drawingPanel;

	/** The view. */
	protected View view;

	/** The mouse handler. */
	@SuppressWarnings("unused")
	private MouseHandler mouseHandler;

	/** The select command. */
	private SelectCommand selectCommand;

	/**
	 * Creates the button.
	 *
	 * @param jFrame
	 *            frame where the button lives
	 * @param jPanel
	 *            the panel where the items are
	 */
	public SelectButton(View jFrame, JPanel jPanel) {
		super("Select");
		addActionListener(this);
		view = jFrame;
		drawingPanel = jPanel;
	}

	/**
	 * Handles the click by listening to mouse clicks.
	 *
	 * @param event
	 *            the click event
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		selectCommand = new SelectCommand();
		drawingPanel.addMouseListener(mouseHandler = new MouseHandler());
	}

	/**
	 * Handles mouse clicks for selecting items.
	 */
	private class MouseHandler extends MouseAdapter {

		/**
		 * Handles the clicks.
		 *
		 * @param event
		 *            the click event
		 */
		@Override
		public void mouseClicked(MouseEvent event) {
			selectCommand.setPoint(View.mapPoint(event.getPoint()));
			drawingPanel.removeMouseListener(this);
			UndoManager.instance().endCommand(selectCommand);
		}
	}
}
