package ui;

import java.awt.*;
import javax.swing.*;

public class InputJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	JTextArea textArea;
	public JButton button;
	JLabel label;
	public String Text;
	JScrollPane scrollPane;
	
	public InputJFrame(){
		setLayout(new FlowLayout());
		
		label = new JLabel("Paste your words list below. One word per line.");
		add(label);
		
		textArea = new JTextArea(10, 40);
		
		scrollPane = new JScrollPane(textArea);
		add(scrollPane);
		
		button = new JButton("Submit");
		add(button);		
	}
}