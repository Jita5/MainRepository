import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Main {

	private JFrame frame;
	private JTextField usernameText;
	private JPasswordField passwordText;
	JPanel loginPanel;
	JPanel employeePanel;
	JPanel customerPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 525, 424);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		loginPanel = new JPanel();
		loginPanel.setBounds(0, 0, 510, 385);
		frame.getContentPane().add(loginPanel);
		loginPanel.setVisible(true);
		loginPanel.setLayout(null);

		JLabel welcomeLabel = new JLabel("Welcome to Petes Parts");
		welcomeLabel.setBounds(133, 43, 265, 51);
		welcomeLabel.setForeground(Color.BLUE);
		welcomeLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		loginPanel.add(welcomeLabel);

		JLabel usernameLbl = new JLabel("Username:");
		usernameLbl.setBounds(150, 159, 80, 14);
		loginPanel.add(usernameLbl);

		JLabel passwordLbl = new JLabel("Password:");
		passwordLbl.setBounds(150, 205, 80, 14);
		loginPanel.add(passwordLbl);

		usernameText = new JTextField();
		usernameText.setBounds(255, 156, 86, 20);
		usernameText.setColumns(10);
		loginPanel.add(usernameText);

		JLabel loginLbl = new JLabel("Please login below");
		loginLbl.setBounds(200, 116, 146, 14);
		loginPanel.add(loginLbl);

		passwordText = new JPasswordField();
		passwordText.setBounds(255, 202, 86, 20);
		passwordText.setColumns(10);
		loginPanel.add(passwordText);

		JLabel loginFailed = new JLabel("");
		loginFailed.setBounds(200, 315, 112, 16);
		loginFailed.setForeground(Color.RED);
		loginFailed.setFont(new Font("Tahoma", Font.BOLD, 13));
		loginPanel.add(loginFailed);

		JButton loginBtn = new JButton("Login");
		loginBtn.setBounds(200, 263, 80, 23);
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loginFailed.setText("");
				String username = usernameText.getText();
				@SuppressWarnings("deprecation")
				String password = passwordText.getText();
				VerifyLogin ver = new VerifyLogin(username, password);
				if (ver.loginSuccess) {
					System.out.println("Logged In");
					loginPanel.setVisible(false);
					employeePanel.setVisible(true);
				} else {
					loginFailed.setText("Login Failed!");
					System.out.println("Log In Failed");
				}
			}
		});
		loginPanel.add(loginBtn);

		employeePanel = new JPanel();
		employeePanel.setBounds(0, 0, 510, 385);
		frame.getContentPane().add(employeePanel);
		employeePanel.setVisible(false);
		employeePanel.setLayout(null);

		JLabel firstLastNameLbl = new JLabel("DDDDDDDDDDDDDDDDD");
		firstLastNameLbl.setBounds(39, 115, 119, 14);
		employeePanel.add(firstLastNameLbl);

		JLabel addressLbl = new JLabel("DDDDDDDDDDDDDDDDD");
		addressLbl.setBounds(39, 130, 119, 14);
		employeePanel.add(addressLbl);

		JLabel cityStateLbl = new JLabel("DDDDDDDDDDDDDDDDD");
		cityStateLbl.setBounds(39, 145, 119, 14);
		employeePanel.add(cityStateLbl);

		JLabel currHrsLbl = new JLabel("Current Hours:");
		currHrsLbl.setBounds(250, 115, 80, 14);
		employeePanel.add(currHrsLbl);

		JLabel dispCurrHrsLbl = new JLabel("DDDDDDDDDDDDDDDDD");
		dispCurrHrsLbl.setBounds(340, 115, 145, 14);
		employeePanel.add(dispCurrHrsLbl);

		JLabel ytdHrsLbl = new JLabel("YTD Hours:");
		ytdHrsLbl.setBounds(250, 130, 80, 14);
		employeePanel.add(ytdHrsLbl);

		JLabel dispYtdLbl = new JLabel("DDDDDDDDDDDDDDDDD");
		dispYtdLbl.setBounds(340, 130, 119, 14);
		employeePanel.add(dispYtdLbl);

		JLabel lastPayLbl = new JLabel("Last Paycheck:");
		lastPayLbl.setBounds(250, 145, 80, 14);
		employeePanel.add(lastPayLbl);

		JLabel dispLastPayLbl = new JLabel("DDDDDDDDDDDDDDDDD");
		dispLastPayLbl.setBounds(340, 145, 119, 14);
		employeePanel.add(dispLastPayLbl);

		JButton logOutBtn = new JButton("Log Out");
		logOutBtn.setBounds(233, 266, 71, 23);
		employeePanel.add(logOutBtn);
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// employeePanel.setVisible(false);
				// PetesLogin pp = new PetesLogin();
				// pp.main(null);
			}
		});

		customerPanel = new JPanel();
		customerPanel.setBounds(0, 0, 510, 385);
		frame.getContentPane().add(customerPanel);
		customerPanel.setLayout(null);
		customerPanel.setVisible(true);
	}

}
