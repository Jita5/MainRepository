/**
 * 
 * @author David Brazeau
 * @Copyright (c) 2015
 *
 * Redistribution and use with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - the use is for academic purpose only
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *   - The name David Brazeau may not be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * The author do not make any claims regarding the correctness of the code in this module
 * and are not responsible for any loss or damage resulting from its use.
 */
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * The button to create lines. Processes the mouse movements and clicks calling
 * the appropriate methods of controller.
 *
 */
@SuppressWarnings("serial")
public class PolygonButton extends JButton implements ActionListener {

	/** The drawing panel. */
	protected JPanel drawingPanel;

	/** The view. */
	protected View view;

	/** The mouse handler. */
	private MouseHandler mouseHandler;

	/** The key handler. */
	private KeyHandler keyHandler;

	/** The poly command. */
	private PolygonCommand polyCommand;

	/** The point count. */
	private int pointCount;

	/**
	 * Creates the button for the line.
	 *
	 * @param jFrame
	 *            the frame where the label is put
	 * @param jPanel
	 *            the panel within the frame
	 */
	public PolygonButton(View jFrame, JPanel jPanel) {
		super("Polygon");
		addActionListener(this);
		view = jFrame;
		drawingPanel = jPanel;
		keyHandler = new KeyHandler();
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
		view.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		drawingPanel.addMouseListener(mouseHandler);
		drawingPanel.addFocusListener(keyHandler);
		drawingPanel.requestFocusInWindow();
		drawingPanel.addKeyListener(keyHandler);
	}

	/**
	 * Handles mouse click so that the points can now be captured.
	 * 
	 */
	private class MouseHandler extends MouseAdapter {

		/**
		 * Mouse listener to get the points selected by user
		 * 
		 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseClicked(MouseEvent event) {
			pointCount++;
			if (polyCommand != null) {
				UndoManager.instance().endCommand(polyCommand);
			}
			if (pointCount == 1) {
				polyCommand = new PolygonCommand(
						View.mapPoint(event.getPoint()));
				polyCommand.setPolygonPoint(View.mapPoint(event.getPoint()));
			} else if (pointCount > 1) {
				polyCommand.setPolygonPoint(View.mapPoint(event.getPoint()));
			}
		}
	}

	/**
	 * The Class KeyHandler.
	 */
	private class KeyHandler extends KeyAdapter implements FocusListener {

		/**
		 * Ends the listeners and begans the undo commands to store in model
		 * when enter key pressed
		 * 
		 * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
		 */
		@Override
		public void keyPressed(KeyEvent event) {
			if (event.getKeyCode() == KeyEvent.VK_ENTER) {
				UndoManager.instance().beginCommand(polyCommand);
				pointCount = 0;
				view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				drawingPanel.removeMouseListener(mouseHandler);
				drawingPanel.removeKeyListener(keyHandler);
				UndoManager.instance().endCommand(polyCommand);
				view.refresh();
			}
		}

		/**
		 * When the panel gets the focus, starts listening for key strokes.
		 *
		 * @param event
		 *            the event
		 */
		@Override
		public void focusGained(FocusEvent event) {
			drawingPanel.addKeyListener(this);
		}

		/**
		 * When the panel loses focus, wraps up the command. Stops listening to
		 * key strokes.
		 *
		 * @param event
		 *            the event
		 */
		@Override
		public void focusLost(FocusEvent event) {
			view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			drawingPanel.removeMouseListener(mouseHandler);
			drawingPanel.removeKeyListener(keyHandler);
			UndoManager.instance().endCommand(polyCommand);
			view.refresh();
		}
	}
}