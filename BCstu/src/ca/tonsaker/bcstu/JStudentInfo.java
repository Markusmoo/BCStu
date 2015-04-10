package ca.tonsaker.bcstu;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ca.tonsaker.bcstu.Student.Result.User;

public class JStudentInfo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3711704548056817964L;
	
	private JPanel contentPane;
	private JTextField txt_firstName;
	private JTextField txt_lastName;
	private JTextField txt_street;
	private JTextField txt_cityState;
	private JTextField txt_email;
	private JTextField txt_username;
	private JTextField txt_password;
	private JTextField txt_phone;
	private JTextField txt_cell;

	/**
	 * Create the frame.
	 */
	public JStudentInfo(URL pic, String first, String last, String street, String city, String state, String email, String username, String password, String phone, String cell) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 261, 447);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JProfilePicture profilePic = new JProfilePicture();
		profilePic.setBounds(62, 29, 60, 60);
		profilePic.setPicture(pic);
		contentPane.add(profilePic);
		
		JLabel lbl_picture = new JLabel("Picture:");
		lbl_picture.setBounds(15, 15, 37, 14);
		contentPane.add(lbl_picture);
		
		JLabel lbl_firstName = new JLabel("First Name:");
		lbl_firstName.setBounds(15, 119, 55, 14);
		contentPane.add(lbl_firstName);
		
		txt_firstName = new JTextField();
		txt_firstName.setBounds(75, 117, 115, 20);
		txt_firstName.setEditable(false);
		contentPane.add(txt_firstName);
		txt_firstName.setColumns(10);
		
		JLabel lbl_lastName = new JLabel("Last Name:");
		lbl_lastName.setBounds(15, 143, 54, 14);
		contentPane.add(lbl_lastName);
		
		txt_lastName = new JTextField();
		txt_lastName.setBounds(75, 141, 115, 20);
		txt_lastName.setEditable(false);
		txt_lastName.setColumns(10);
		contentPane.add(txt_lastName);
		
		JLabel lbl_street = new JLabel("Street:");
		lbl_street.setBounds(15, 184, 34, 14);
		contentPane.add(lbl_street);
		
		txt_street = new JTextField();
		txt_street.setBounds(75, 182, 155, 20);
		txt_street.setEditable(false);
		contentPane.add(txt_street);
		txt_street.setColumns(10);
		
		JLabel lbl_cityState = new JLabel("State/City:");
		lbl_cityState.setBounds(15, 208, 53, 14);
		contentPane.add(lbl_cityState);
		
		txt_cityState = new JTextField();
		txt_cityState.setBounds(75, 206, 155, 20);
		txt_cityState.setEditable(false);
		contentPane.add(txt_cityState);
		txt_cityState.setColumns(10);
		
		JLabel lbl_email = new JLabel("Email:");
		lbl_email.setBounds(15, 259, 46, 14);
		contentPane.add(lbl_email);
		
		txt_email = new JTextField();
		txt_email.setEditable(false);
		txt_email.setBounds(75, 256, 155, 20);
		contentPane.add(txt_email);
		txt_email.setColumns(10);
		
		JLabel lbl_username = new JLabel("Username:");
		lbl_username.setBounds(15, 349, 55, 14);
		contentPane.add(lbl_username);
		
		txt_username = new JTextField();
		txt_username.setEditable(false);
		txt_username.setBounds(75, 346, 155, 20);
		contentPane.add(txt_username);
		txt_username.setColumns(10);
		
		JLabel lbl_password = new JLabel("Password:");
		lbl_password.setBounds(15, 374, 55, 14);
		contentPane.add(lbl_password);
		
		txt_password = new JTextField();
		txt_password.setEditable(false);
		txt_password.setBounds(75, 371, 155, 20);
		contentPane.add(txt_password);
		txt_password.setColumns(10);
		
		JLabel lbl_phone = new JLabel("Phone:");
		lbl_phone.setBounds(15, 284, 46, 14);
		contentPane.add(lbl_phone);
		
		txt_phone = new JTextField();
		txt_phone.setEditable(false);
		txt_phone.setBounds(75, 281, 155, 20);
		contentPane.add(txt_phone);
		txt_phone.setColumns(10);
		
		JLabel lbl_cell = new JLabel("Cell:");
		lbl_cell.setBounds(15, 309, 46, 14);
		contentPane.add(lbl_cell);
		
		txt_cell = new JTextField();
		txt_cell.setEditable(false);
		txt_cell.setBounds(75, 306, 155, 20);
		contentPane.add(txt_cell);
		txt_cell.setColumns(10);
		
		txt_firstName.setText(first);
		txt_lastName.setText(last);
		txt_street.setText(street);
		txt_cityState.setText(state+", "+city);
		txt_email.setText(email);
		txt_username.setText(username);
		txt_password.setText(password);
		txt_phone.setText(phone);
		txt_cell.setText(cell);
		
		this.setVisible(true);
	}
	
	public JStudentInfo(User user) throws MalformedURLException{
		this(new URL(user.picture.medium), user.name.first, user.name.last, user.location.street, user.location.city, user.location.state, user.email, user.username, user.password, user.phone, user.cell);
	}
}
