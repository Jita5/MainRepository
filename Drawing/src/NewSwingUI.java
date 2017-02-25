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
import java.awt.Graphics;
import java.awt.Image;

/**
 * A UI that uses the swing package.
 */
public class NewSwingUI implements UIContext {

	/** The graphics. */
	private Graphics graphics;

	/** The swing ui. */
	private static NewSwingUI swingUI;

	/**
	 * For the singleton pattern.
	 */
	private NewSwingUI() {
	}

	/**
	 * Returns the instance.
	 *
	 * @return the instance
	 */
	public static NewSwingUI getInstance() {
		if (swingUI == null) {
			swingUI = new NewSwingUI();
		}
		return swingUI;
	}

	/**
	 * The Graphics object for drawing.
	 *
	 * @param graphics
	 *            the Graphics object
	 */
	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	/**
	 * Draws a label.
	 *
	 * @param label
	 *            the label
	 */
	@Override
	public void draw(Label label) {
		if (label.getStartingPoint() != null) {
			if (label.getText() != null) {
				graphics.drawString(label.getText(), (int) label
						.getStartingPoint().getX(), (int) label
						.getStartingPoint().getY());
			}
		}
		int length = graphics.getFontMetrics().stringWidth(label.getText());
		graphics.drawString("_",
				(int) label.getStartingPoint().getX() + length, (int) label
						.getStartingPoint().getY());
	}

	/**
	 * Draws a line.
	 *
	 * @param line
	 *            the line to be drawn
	 */
	@Override
	public void draw(Line line) {
		int i1 = 0;
		int i2 = 0;
		int i3 = 0;
		int i4 = 0;
		if (line.getPoint1() != null) {
			i1 = Math.round((float) (line.getPoint1().getX()));
			i2 = Math.round((float) (line.getPoint1().getY()));
			if (line.getPoint2() != null) {
				i3 = Math.round((float) (line.getPoint2().getX()));
				i4 = Math.round((float) (line.getPoint2().getY()));
			} else {
				i3 = i1;
				i4 = i2;
			}
			graphics.drawLine(i1, i2, i3, i4);
		}
	}

	/**
	 * Inserts an image in the area selected by user
	 * 
	 * @see UIContext#draw(ImpImage)
	 */
	@Override
	public void draw(ImpImage image) {
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		int sx1 = 0;
		int sy1 = 0;
		int sx2 = 0;
		int sy2 = 0;
		Image img = null;
		if ((image.getPoint1() != null) && (image.getPoint1() != null)) {
			x1 = Math.round((float) (image.getPoint1().getX()));
			y1 = Math.round((float) (image.getPoint1().getY()));
			x2 = Math.round((float) (image.getPoint2().getX()));
			y2 = Math.round((float) (image.getPoint2().getY()));
			sx1 = 0;
			sy1 = 0;
			sx2 = image.getImgWidth();
			sy2 = image.getImgHeight();
			if (image.getImg() != null) {
				img = image.getImg();
			} else {
				System.out.println("No image selected!");
			}
		} else {
			System.out
					.println("Need to select two points to insert image into!");
		}
		graphics.drawImage(img, x1, y1, x2, y2, sx1, sy1, sx2, sy2, null);
	}

	/**
	 * Draws a polygon based on the points selected by user.
	 * 
	 * @see UIContext#draw(Polygon)
	 */
	@Override
	public void draw(Polygon polygon) {
		double casting = 0;
		int x1 = 0;
		int y1 = 0;
		int x2 = 0;
		int y2 = 0;
		if ((polygon.getPointX() != null) && (polygon.getPointY() != null)) {
			for (int i = 0; i < polygon.getPointX().size(); i++) {
				int n = i + 1;
				casting = Math.round((polygon.getPointX().get(i)));
				x1 = (int) casting;
				casting = Math.round((polygon.getPointY().get(i)));
				y1 = (int) casting;
				if (n < polygon.getPointX().size()) {
					casting = Math.round((polygon.getPointX().get(n)));
					x2 = (int) casting;
					casting = Math.round((polygon.getPointY().get(n)));
					y2 = (int) casting;
				} else {
					x2 = x1;
					y2 = y1;
				}
				graphics.drawLine(x1, y1, x2, y2);
			}
		}
	}

	/**
	 * Captures undefined items.
	 *
	 * @param item
	 *            the item
	 */
	@Override
	public void draw(Item item) {
		System.out.println("Cant draw unknown Item \n");
	}
}