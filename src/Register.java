import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Register extends JFrame {
	
	private JPanel content = new JPanel();
	private JLabel userLabel = new JLabel("Username:");
	private JLabel passLabel = new JLabel("Password:");
	private JLabel emailLabel = new JLabel("Email:");
	private JTextField username = new JTextField(15);
	private JTextField email = new JTextField(25);
	final JPasswordField passwordField = new JPasswordField(15);
	private JButton register = new JButton("Register");
	private JButton back = new JButton("Back");
	private Border panelTitle = BorderFactory.createTitledBorder("Register User	");
	
	public Register()
	{
		super("Register new user");
		
		JFrame userReg = new JFrame();
	
		userReg.setSize(600,300);
		userReg.setVisible(true);
		userReg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//userReg.setLocation(null);
		
		userReg.add(content);
		content.setBorder(panelTitle);
		
		content.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets (3,3,3,3);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		content.add(userLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		content.add(passLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.LINE_START;
		content.add(emailLabel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		content.add(username, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		content.add(passwordField, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.LINE_START;
		content.add(email, gbc);
		
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.PAGE_END;
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					Class.forName("com.mysql.jdbc.Driver"); // The driver class for the mysql database
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UserInfoDB", "root",""); // Connection establish to the phpmyadmin database. Due to me being the localhost i use my port
					PreparedStatement ps = conn.prepareStatement("insert into userinfo(Username,Password,Email) values(?,?,?);"); // SQL statement to take the values and insert them into the columns we need
					ps.setString(1, username.getText()); // take username text and put it into the 1st assigned (etc with the rest)
					ps.setString(2, passwordField.getText().toString());
					ps.setString(3, email.getText());
					if ( username.getText().trim().isEmpty() || passwordField.getText().toString().trim().isEmpty() || email.getText().trim().isEmpty()) // if statement to show if the fields are filled and 
					{
						JOptionPane.showMessageDialog(null, "Please fill out all of the fields");

					}
					else
					{

						JOptionPane.showMessageDialog(null, "Registeration successfull");
						userReg.dispose();
						new Login();
						ps.executeUpdate(); //execute the update
						
						
					}
					
				}
				catch (Exception b)
				{
						System.out.println(b);
				}
					
		}
				
		});
		content.add(register, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.LINE_START;
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				userReg.dispose();
				new Login();
			}
		});
		content.add(back, gbc);
		
		
		
		
		
		
		
	
	}
	

}
