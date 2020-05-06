import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Login extends JFrame 
{
	
	private JPanel content = new JPanel();
	private JLabel userLabel = new JLabel("Username:");
	private JLabel passLabel = new JLabel("Password:");
	private JTextField username = new JTextField(15);
	final JPasswordField passwordField = new JPasswordField(15);
	private JButton login = new JButton("Login");
	private JButton register = new JButton("Register");
	private Border panelTitle = BorderFactory.createTitledBorder("Login User");
	
	
	public Login()
	{
		super("Sneaker Releases App");
		frame();
		
		
		
		

	}
	
	public void frame()
	
	{
			//Set the layout of the window
			//=============================================================================

			JFrame window = new JFrame();
			window.setSize(500,300);
			window.setVisible(true);
			window.setResizable(false);
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			window.add(content);
			content.setBorder(panelTitle);
			
			content.setLayout(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();

			gbc.insets = new Insets(3, 3, 3,3);
			gbc.gridx = 0;
			gbc.gridy = 0;
			
			// User Label
			gbc.anchor = GridBagConstraints.LINE_END; //push to the right to align
			gbc.gridx = 0;
			gbc.gridy = 0;
			//gbc.weightx = 1;
			//gbc.weighty = 1;
			content.add(userLabel, gbc);
			
			//Password Label
			gbc.gridx = 0;
			gbc.gridy = 1;
			content.add(passLabel, gbc);
			
			//UserName text box
			gbc.anchor = GridBagConstraints.LINE_START;//push to the left to align 
			gbc.gridx = 1;
			gbc.gridy = 0;
			content.add(username, gbc);
			
			//Password text box
			gbc.gridx = 1;
			gbc.gridy = 1;
			content.add(passwordField, gbc);
			
			//Login button 
			gbc.anchor = GridBagConstraints.LINE_START; // Push right
			gbc.gridx = 1;
			gbc.gridy = 2;
			login.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try
					{
						Class.forName("com.mysql.jdbc.Driver"); // The driver class for the mysql database
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/UserInfoDB","root",""); // Establish connection to my localhost and the phpmyadmin pass
						Statement stmt = con.createStatement(); 
						String sql = "Select * from userinfo where Username ='"+username.getText()+"' and Password='"+passwordField.getText().toString()+"'"; //SQL query to select from the fields highlighted to check validation
						ResultSet rs = stmt.executeQuery(sql); //execute query above
						if(rs.next()) // IF statement to show result 
						{ 
							window.dispose();
							JOptionPane.showMessageDialog(null, "Login Successfully");
							new UI();


						}
							
						else
							JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
						con.close();
					}
					catch (Exception b)
					{
						System.out.print(b);
					}
				}
			});
			content.add(login, gbc);
			//=============================================================
			
			//Register Button
			gbc.anchor = GridBagConstraints.LINE_END; //Push left
			gbc.gridx = 1;
			gbc.gridy = 2;
			register.addActionListener(new ActionListener() { // button press takes you to register window
				public void actionPerformed(ActionEvent e) {
					new Register();
					window.dispose();
				}
			});
			content.add(register, gbc);
			//==============================================================
			
		
	}
}

