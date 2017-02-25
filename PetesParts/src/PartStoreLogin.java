import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class PartStoreLogin {

	private JFrame loginFrame;
	private JTextField usernameText;
	private JPasswordField passwordText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PartStoreLogin window = new PartStoreLogin();
					window.loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
		 * Create the application.
		 */
		public PartStoreLogin() {
			initialize();
		}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		loginFrame = new JFrame();
		loginFrame.setBounds(100, 100, 450, 300);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setLayout(null);
		loginFrame.setLocationRelativeTo(null);
//		loginFrame.setVisible(true);

		JLabel welcomeLabel = new JLabel("Welcome to Petes Parts");
		welcomeLabel.setForeground(Color.BLUE);
		welcomeLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		welcomeLabel.setBounds(99, 22, 265, 51);
		loginFrame.add(welcomeLabel);

		JLabel usernameLbl = new JLabel("Username:");
		usernameLbl.setBounds(125, 125, 100, 18);
		loginFrame.add(usernameLbl);

		JLabel passwordLbl = new JLabel("Password:");
		passwordLbl.setBounds(125, 165, 100, 18);
		loginFrame.add(passwordLbl);

		usernameText = new JTextField();
		usernameText.setBounds(200, 125, 86, 20);
		usernameText.setColumns(10);
		loginFrame.add(usernameText);

		passwordText = new JPasswordField();
		passwordText.setBounds(200, 165, 86, 20);
		passwordText.setColumns(10);
		loginFrame.add(passwordText);

		JLabel loginLbl = new JLabel("Please login below");
		loginLbl.setBounds(125, 86, 146, 14);
		loginFrame.add(loginLbl);

		JLabel loginFailed = new JLabel("");
		loginFailed.setBounds(175, 239, 111, 14);
		loginFrame.add(loginFailed);

		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				loginFailed.setText("");
				String username = usernameText.getText();
				String password = passwordText.getText();
				VerifyLogin ver = new VerifyLogin(username, password);
				if (ver.loginSuccess) {
					System.out.println("Logged In");
					loginFrame.dispose();
					Access emp = new Access(username, password);
					emp.setVisible(true);
				} else {
					loginFailed.setText("Login Failed!");
					System.out.println("Log In Failed");
				}
			}
		});
		loginBtn.setBounds(161, 205, 89, 23);
		loginFrame.add(loginBtn);
	}	
}
