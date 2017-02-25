import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.awt.event.ActionEvent;

public class DecimalConverter {

	private JFrame frame;
	private JTextField decimalInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DecimalConverter window = new DecimalConverter();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DecimalConverter() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblDecimalToBinary = new JLabel("Decimal to Binary Converter");
		lblDecimalToBinary.setForeground(Color.BLUE);
		lblDecimalToBinary.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblDecimalToBinary.setBounds(57, 56, 344, 39);
		frame.getContentPane().add(lblDecimalToBinary);
		
		JLabel lblDecimalToConvert = new JLabel("Decimal to Convert");
		lblDecimalToConvert.setBounds(50, 140, 120, 14);
		frame.getContentPane().add(lblDecimalToConvert);
		
		decimalInput = new JTextField();
		decimalInput.setBounds(170, 140, 120, 20);
		frame.getContentPane().add(decimalInput);
		decimalInput.setColumns(10);
		
		JLabel lblBinaryNumber = new JLabel("Binary Number");
		lblBinaryNumber.setBounds(50, 180, 120, 14);
		frame.getContentPane().add(lblBinaryNumber);
		
		JLabel binaryLbl = new JLabel("");
		binaryLbl.setBounds(170, 180, 260, 14);
		frame.getContentPane().add(binaryLbl);
		
		JButton btnNewButton = new JButton("Convert");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (decimalInput.getText().length() >18) {
					binaryLbl.setText("");
					JOptionPane.showMessageDialog(null, "Please do not enter values larger than 18 digits");
				}
				else if (decimalInput.getText().length() <=0){
					binaryLbl.setText("");
					JOptionPane.showMessageDialog(null, "Please enter a value");
				}
				else {
					String input = decimalInput.getText();
					long decimalNum = Long.parseLong(input);
					if (decimalNum <= 0) {
						binaryLbl.setText("");
						JOptionPane.showMessageDialog(null, "Please enter a value larger than 0");
					}
					else {
						input = decimalConversion(decimalNum);				
						binaryLbl.setText(input);
					}
				}
			}
		});
		btnNewButton.setBounds(167, 214, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
	
	public String decimalConversion(long x) {
		long input = x;
		long remainder = 0;
		String output = "";
		Stack<Long> myStack = new Stack<Long>();
		
		while (!(input == 0)){
			remainder = input % 2;
			myStack.push(remainder);
			input = input / 2;			
		}
		
		while (!(myStack.isEmpty())){
			output += myStack.pop();
		}
		
		return output;		
	}
}
