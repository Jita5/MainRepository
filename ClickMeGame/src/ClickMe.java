/**
 * Author Brahma Dathan
 * Date January 15, 2015
 */
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
/**
 * An example of using JPanel, Graphics, MouseMotionListener, and
 * MouseListener.
 * It is a simple game challenging the user to click a rectangle on
 * the screen.
 * @author Brahma Dathan
 *
 */
public class ClickMe extends JFrame {
	/**
	 * The JPanel subclass has almost all of the functionality.
	 * It draws the rectangle and listens to mouse motion.
	 * When the mouse is too close, the rectangle is redrawn.
	 * @author mh6624pa
	 *
	 */
	public class MyPanel extends JPanel implements MouseListener, MouseMotionListener {
		private int mouseX;
		private int mouseY;
		private int rectTopLeftX;
		private int rectTopLeftY;
		private static final int RECTWIDTH = 20;
		private static final int RECTHEIGHT = 20;
		private int dangerMinX;
		private int dangerMinY;
		private int dangerMaxX;
		private int dangerMaxY;
		private boolean userWon;
		private static final int MARGIN = 5;
		/**
		 * Sets up the panel with the rectangle at the center.
		 */
		public MyPanel() {
			setSize(800, 800);
			rectTopLeftX = getWidth() / 2;
			rectTopLeftY = getHeight() / 2;
			setDangerZone();
			addMouseMotionListener(this);
			addMouseListener(this);
		}
		/**
		 * The rectangle is redrawn. Usually called when the mouse is
		 * too close.
		 */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (userWon) {
				g.setColor(Color.black);
				g.drawString("You won", getWidth() / 2, getHeight() / 2);
			} else {
				g.setColor(Color.red);
				g.fillRect(rectTopLeftX, rectTopLeftY, RECTWIDTH, RECTHEIGHT);
			}
		}
		/*
		 * Checks if the mouse is too close to the rectangle.
		 */
		private boolean isMouseTooClose() {
			return (mouseX >= dangerMinX && mouseX <= dangerMaxX 
				&& mouseY >= dangerMinY && mouseY <= dangerMaxY);
		}
		/*
		 * Computes a new position for the rectangle. Sometimes 
		 * it is close to the mouse. When the mouse is near the 
		 * boundary of the panel, we simply redraw the rectangle at the
		 * center.
		 */
		private void findNewPosition() {
			if (Math.random() < 0.8) {
				return;
			}
			if (mouseX > 40 && mouseY > 40 && mouseX < getBounds().width - 40
					&& mouseY < getBounds().height - 40) {
				if (mouseX < rectTopLeftX) {
					rectTopLeftX = mouseX + MARGIN;
				} else {
					rectTopLeftX = mouseX - MARGIN - 20;
				}
				if (mouseY < rectTopLeftY) {
					rectTopLeftY = mouseY + MARGIN;
				} else {
					rectTopLeftY = mouseY - MARGIN - 20;
				}
			} else {
				rectTopLeftX = this.getWidth() / 2;
				rectTopLeftY = this.getHeight() / 2;
			} 
		}
		/*
		 * This sets the parameters of the rectangle in which the mouse
		 * may be too close to the red rectangle. 
		 */
		private void setDangerZone() {
			dangerMinX = rectTopLeftX - MARGIN;
			dangerMinY = rectTopLeftY - MARGIN;
			dangerMaxX = rectTopLeftX + RECTWIDTH + MARGIN;
			dangerMaxY = rectTopLeftY + RECTHEIGHT + MARGIN;
		}
		/**
		 * Not used
		 */
		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * When the mouse moves, we must listen to see if it is
		 * in a position to click.
		 */
		@Override
		public void mouseMoved(MouseEvent event) {
			mouseX = event.getX();
			mouseY = event.getY();
			if (isMouseTooClose()) {
				findNewPosition();
				setDangerZone();
				repaint();
			}
			
		}
		/**
		 * Checks if the user managed to click within the rectangle.
		 */
		@Override
		public void mouseClicked(MouseEvent event) {
			mouseX = event.getX();
			mouseY = event.getY();
			if (mouseX >= rectTopLeftX && mouseY >= rectTopLeftY 
				&& mouseX <= rectTopLeftX + 20 
				&& mouseY <= rectTopLeftY + 20) {
				userWon = true;
				repaint();
			}
			
		}
		/**
		 * Not used
		 */
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * Not used
		 */
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * Not used
		 */
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * Not used
		 */
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	/**
	 * Adds the panel to the frame.
	 */
	public ClickMe() {
		this.setSize(800, 800);
		getContentPane().add(new MyPanel());
		setVisible(true);
	}
	/**
	 * Starts the GUI
	 * @param args not used
	 */
	public static void main(String[] args) {
		new ClickMe();
	}

}
