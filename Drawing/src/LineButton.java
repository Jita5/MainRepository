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
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The button to create lines. Processes the mouse movements and clicks calling
 * the appropriate methods of controller.
 *
 */
@SuppressWarnings("serial")
public class LineButton extends JButton implements ActionListener {

	/** The drawing panel. */
	protected JPanel drawingPanel;

	/** The view. */
	protected View view;

	/** The mouse handler. */
	private MouseHandler mouseHandler;

	/** The line command. */
	private LineCommand lineCommand;

	/**
	 * Creates the button for the line.
	 *
	 * @param jFrame
	 *            the frame where the label is put
	 * @param jPanel
	 *            the panel within the frame
	 */
	public LineButton(View jFrame, JPanel jPanel) {
		super("Line");
		addActionListener(this);
		view = jFrame;
		drawingPanel = jPanel;
		mouseHandler = new MouseHandler();
	}

	/**
	 * Handle click for creating a new line.
	 *
	 * @param event
	 *            the event
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		drawingPanel.addMouseListener(mouseHandler);
	}

	/**
	 * Handles mouse click so that the points can now be captured.
	 * 
	 */
	private class MouseHandler extends MouseAdapter {

		/** The point count. */
		private int pointCount = 0;

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseClicked(MouseEvent event) {
			if (++pointCount == 1) {
				lineCommand = new LineCommand(View.mapPoint(event.getPoint()));
				UndoManager.instance().beginCommand(lineCommand);
			} else if (pointCount == 2) {
				pointCount = 0;
				lineCommand.setLinePoint(View.mapPoint(event.getPoint()));
				drawingPanel.removeMouseListener(this);
				view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				UndoManager.instance().endCommand(lineCommand);
			}
		}
	}
}