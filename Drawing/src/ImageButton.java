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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 * The button to import an image. Processes the mouse movements and clicks
 * calling the appropriate methods of controller to find area to import image.
 *
 */
@SuppressWarnings("serial")
class ImageButton extends JButton implements ActionListener {

	/** The drawing panel. */
	protected JPanel drawingPanel;

	/** The view. */
	protected View view;

	/** The mouse handler. */
	private MouseHandler mouseHandler;

	/** The image command. */
	private ImageCommand imageCommand;

	/** The directory. */
	private String directory;

	/** The file chooser. */
	private JFileChooser fileChooser;

	/** The result. */
	private int result;

	/** The selected image. */
	private File selectedImage;

	/** The img. */
	private BufferedImage img;

	/** The width. */
	private int width;

	/** The height. */
	private int height;

	/**
	 * Creates the button for the label.
	 *
	 * @param jFrame
	 *            the frame where the label is put
	 * @param jPanel
	 *            the panel within the frame
	 */
	public ImageButton(View jFrame, JPanel jPanel) {
		super("Image");
		addActionListener(this);
		view = jFrame;
		drawingPanel = jPanel;
		mouseHandler = new MouseHandler();
	}

	/**
	 * Handle click for creating a new image on the display panel.
	 *
	 * @param event
	 *            the event
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		drawingPanel.addMouseListener(mouseHandler);
		view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
	}

	/**
	 * Handles mouse click so that the area for the image to be imported can now
	 * be captured. Then the JFileChooser dialog provides the user to select an
	 * image to be imported into the selected area of the drawing panel.
	 * 
	 */
	private class MouseHandler extends MouseAdapter {

		/** The point count. */
		private int pointCount = 0;

		/**
		 * Handles Mouse clicks to get points and insert image.
		 * 
		 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
		 */
		@Override
		public void mouseClicked(MouseEvent event) {
			pointCount++;
			if (imageCommand != null) {
				UndoManager.instance().endCommand(imageCommand);
			}
			if (pointCount == 1) {
				imageCommand = new ImageCommand(View.mapPoint(event.getPoint()));
				imageCommand.setPolygonPoint1(View.mapPoint(event.getPoint()));
			} else if (pointCount == 2) {
				imageCommand.setPolygonPoint2(View.mapPoint(event.getPoint()));
				directory = System.getProperty("user.dir");
				fileChooser = new JFileChooser(directory);
				result = fileChooser.showOpenDialog(fileChooser);
				if (result == JFileChooser.APPROVE_OPTION) {
					selectedImage = fileChooser.getSelectedFile();
					try {
						img = ImageIO.read(selectedImage);
						imageCommand.setImage(img);
						width = img.getWidth();
						imageCommand.setImageWidth(width);
						height = img.getHeight();
						imageCommand.setImageHeight(height);
						pointCount = 0;
					} catch (IOException e) {
					}
				}
				UndoManager.instance().beginCommand(imageCommand);
				view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				drawingPanel.removeMouseListener(mouseHandler);
				UndoManager.instance().endCommand(imageCommand);
			}
		}
	}
}