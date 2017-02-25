import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.Window;

public class Access extends JFrame {

	private static final long serialVersionUID = -3067467514794648884L;
	private JPanel employeePane;
	private JPanel customerPane;
	private String username;
	private String password;
	private String fullName = "";
	private String address = "";
	private String cityState = "";
	private String currHours = "";
	private String ytdHours = ""; 
	private double paycheck = 0;
	private String payDisp = "";

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Access frame = new Access();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Access(String uname, String pword) {
		
		this.username = uname;
		this.password = pword;
		
		try {
			// creating mysql connection
			Class.forName("com.mysql.jdbc.Driver");
			String myUrl = "jdbc:mysql://localhost:3306/dsbtable";
			Connection con = DriverManager.getConnection(myUrl, "root", "root");

			// creating select query & statement
			Statement st = con.createStatement();
			PreparedStatement prepSt = con
					.prepareStatement("SELECT username, password, first_name, last_name, address, city, " +
									  "state, current_hours, ytd_hours, last_paycheck FROM employee WHERE username= ? AND password= ?");
			prepSt.setString(1, username);
			prepSt.setString(2, password);
			ResultSet rs = prepSt.executeQuery();
			while (rs.next()) {
				fullName = rs.getString("last_name")+", "+rs.getString("first_name");
				address = rs.getString("address");
				cityState = rs.getString("city")+", "+rs.getString("state");
				double cuHr = rs.getDouble("current_hours");
				currHours = String.valueOf(cuHr);
				double ytdHr = rs.getDouble("ytd_hours");
				ytdHours = String.valueOf(ytdHr);
				paycheck = rs.getDouble("last_paycheck");
				payDisp = "$"+paycheck;								
			}						
			st.close();
			prepSt.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		employeePane = new JPanel();
		employeePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(employeePane);
		employeePane.setLayout(new CardLayout(0, 0));
		
		JPanel employeePanel = new JPanel();
		employeePane.add(employeePanel, "name_9642682930517");
		employeePanel.setLayout(null);
		
		JLabel firstLastNameLbl = new JLabel(fullName);
		firstLastNameLbl.setBounds(25, 60, 120, 14);
		employeePanel.add(firstLastNameLbl);
		
		JLabel addressLbl = new JLabel(address);
		addressLbl.setBounds(25, 75, 120, 14);
		employeePanel.add(addressLbl);
		
		JLabel cityStateLbl = new JLabel(cityState);
		cityStateLbl.setBounds(25, 90, 120, 14);
		employeePanel.add(cityStateLbl);
		
		JLabel currHrsLbl = new JLabel("Current Hours:");
		currHrsLbl.setBounds(215, 60, 100, 14);
		employeePanel.add(currHrsLbl);
		
		JLabel dispCurrHrsLbl = new JLabel(currHours);
		dispCurrHrsLbl.setBounds(320, 60, 80, 14);
		employeePanel.add(dispCurrHrsLbl);
		
		JLabel ytdHrsLbl = new JLabel("YTD Hours:");
		ytdHrsLbl.setBounds(215, 75, 100, 14);
		employeePanel.add(ytdHrsLbl);
		
		JLabel dispYtdLbl = new JLabel(ytdHours);
		dispYtdLbl.setBounds(320, 75, 80, 14);
		employeePanel.add(dispYtdLbl);
		
		JLabel lastPayLbl = new JLabel("Last Paycheck:");
		lastPayLbl.setBounds(215, 90, 100, 14);
		employeePanel.add(lastPayLbl);
		
		JLabel dispLastPayLbl = new JLabel(payDisp);
		dispLastPayLbl.setBounds(320, 90, 80, 14);
		employeePanel.add(dispLastPayLbl);
		
		JButton logOutBtn = new JButton("Log Out");
		logOutBtn.setBounds(154, 185, 71, 23);
		employeePanel.add(logOutBtn);
		
		JPanel customerPanel = new JPanel();
		employeePane.add(customerPanel, "name_10885107217858");
		customerPanel.setLayout(null);
		
		JLabel lblHello = new JLabel("HELLO");
		lblHello.setBounds(111, 97, 46, 14);
		customerPanel.add(lblHello);
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				employeePane.setVisible(false);
				Customer cust = new Customer();
				cust.setVisible(true);
			}
		});
	}
}
