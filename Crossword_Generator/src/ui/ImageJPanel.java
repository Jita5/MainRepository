package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImageJPanel extends JPanel {

	private Image image;

	public ImageJPanel(Image img) {
		this.image = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(new FlowLayout());
	}

	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, null);
	}
}
