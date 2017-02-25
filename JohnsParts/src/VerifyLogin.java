import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class VerifyLogin {
	private String username;
	private String password;
	protected Boolean loginSuccess = false;

	protected VerifyLogin(String uname, String pword) {
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
					.prepareStatement("SELECT username, password FROM employee WHERE username= ? AND password= ?");
			prepSt.setString(1, username);
			prepSt.setString(2, password);
			ResultSet rs = prepSt.executeQuery();

			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "Login in was successful!");
				loginSuccess = true;
			} else {
				loginSuccess = false;
			}
			st.close();
			prepSt.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
