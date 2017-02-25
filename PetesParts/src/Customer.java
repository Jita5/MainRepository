import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.CardLayout;

public class Customer extends JFrame {

	private static final long serialVersionUID = -3067467514794648884L;
	private JPanel customerPane;
	private String firstName = "";
	private String lastName = "";

	public Customer() {

		try {
			// creating mysql connection
			Class.forName("com.mysql.jdbc.Driver");
			String myUrl = "jdbc:mysql://localhost:3306/dsbtable";
			Connection con = DriverManager.getConnection(myUrl, "root", "root");

			// creating select query & statement
			Statement st = con.createStatement();
			PreparedStatement prepSt = con.prepareStatement("SELECT * FROM customer");
			ResultSet rs = prepSt.executeQuery();
			while (rs.next()) {
				firstName = rs.getString("first_name");
				lastName = rs.getString("last_name");
			}
			st.close();
			prepSt.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		customerPane = new JPanel();
		customerPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(customerPane);
		customerPane.setLayout(new CardLayout(0, 0));

		JPanel customerPanel = new JPanel();
		customerPane.add(customerPanel, "name_9642682930517");
		customerPanel.setLayout(null);

		JLabel firstNameLbl = new JLabel(firstName);
		firstNameLbl.setBounds(25, 60, 120, 14);
		customerPanel.add(firstNameLbl);

		JLabel lastNameLbl = new JLabel(lastName);
		lastNameLbl.setBounds(155, 60, 120, 14);
		customerPanel.add(lastNameLbl);

	}
}
