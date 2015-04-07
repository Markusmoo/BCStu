package ca.tonsaker.bcstu;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ca.tonsaker.bcstu.Student.Result;
import ca.tonsaker.bcstu.Student.Result.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Creates a BStu window that generates random users from <a href="http://randomuser.me/">http://api.randomuser.me/</a>
 * in a JSON Format.  This application's purpose was solely for testing API's and for educational purposes only.
 * 
 * @author Markus Tonsaker
 *
 */
public class MainFrame extends JFrame implements ActionListener, ListSelectionListener{

	/**
	 *  Serial UID
	 */
	private static final long serialVersionUID = -493516604584877793L;
	 
	/**
	 *  String URL to the Random User API Site
	 */
	public static final String NAME_API_URL = "http://api.randomuser.me/";
	
	private JPanel contentPane;
	
	JProfilePicture pic;
	
	JScrollPane pane;
	JList<User> list;
	DefaultListModel<User> students = new DefaultListModel<User>();
	
	JButton btn_addStu;
	JButton btn_remStu;
	JButton btn_stuInfo;
	
	JMenuItem mntm_AddStudents_1;
	JMenuItem mntm_AddStudents_5;
	JMenuItem mntm_AddStudents_10;
	
	JMenuItem mntm_RemoveStudent;
	JMenuItem mntm_RemoveAllStudents;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		
		//Adds all the menu bars
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntm_Load = new JMenuItem("Load");
		mntm_Load.setEnabled(false);
		mnFile.add(mntm_Load);
		
		JMenu mn_Students = new JMenu("Students");
		menuBar.add(mn_Students);
		
		mntm_AddStudents_1 = new JMenuItem("Add 1 Student");
		mntm_AddStudents_1.addActionListener(this);
		mn_Students.add(mntm_AddStudents_1);
		
		mntm_AddStudents_5 = new JMenuItem("Add 5 Students");
		mntm_AddStudents_5.addActionListener(this);
		mn_Students.add(mntm_AddStudents_5);
		
		mntm_AddStudents_10 = new JMenuItem("Add 10 Students");
		mntm_AddStudents_10.addActionListener(this);
		mn_Students.add(mntm_AddStudents_10);
		
		mntm_RemoveStudent = new JMenuItem("Remove Student");
		mntm_RemoveStudent.addActionListener(this);
		mn_Students.add(mntm_RemoveStudent);
		
		mntm_RemoveAllStudents = new JMenuItem("Remove All Students");
		mntm_RemoveAllStudents.addActionListener(this);
		mn_Students.add(mntm_RemoveAllStudents);
		
		//Creates the content pane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.add(contentPane);
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		//Adds Labels
		JLabel lbl_classroom = new JLabel("Classroom:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lbl_classroom, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lbl_classroom, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lbl_classroom, 20, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lbl_classroom, 150, SpringLayout.WEST, contentPane);
		contentPane.add(lbl_classroom);
		
		//Adds Buttons
		btn_addStu = new JButton("Add Random Student");
		sl_contentPane.putConstraint(SpringLayout.WEST, btn_addStu, 5, SpringLayout.WEST, contentPane);
		btn_addStu.addActionListener(this);
		contentPane.add(btn_addStu);
		
		//Removes the student
		btn_remStu = new JButton("Remove Student");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btn_remStu, -50, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btn_remStu, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btn_addStu, -50, SpringLayout.NORTH, btn_remStu);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btn_remStu, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btn_addStu, -6, SpringLayout.NORTH, btn_remStu);
		btn_remStu.addActionListener(this);
		contentPane.add(btn_remStu);
		
		//Creates the JList containing students
		list = new JList<User>(students);
		pane = new JScrollPane(list);
		sl_contentPane.putConstraint(SpringLayout.EAST, btn_remStu, -5, SpringLayout.WEST, pane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btn_addStu, -5, SpringLayout.WEST, pane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, pane, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, pane, 0, SpringLayout.EAST, lbl_classroom);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, pane, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, pane, -10, SpringLayout.EAST, contentPane);
		list.addListSelectionListener(this);
		contentPane.add(pane);
		
		//Creates the Profile picture.
		pic = new JProfilePicture();
		sl_contentPane.putConstraint(SpringLayout.NORTH, pic, -150, SpringLayout.NORTH, btn_addStu);
		sl_contentPane.putConstraint(SpringLayout.WEST, pic, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, pic, -6, SpringLayout.NORTH, btn_addStu);
		sl_contentPane.putConstraint(SpringLayout.EAST, pic, -5, SpringLayout.WEST, pane);
		contentPane.add(pic);
		
		//View student info
		btn_stuInfo = new JButton("View Student Info");
		btn_stuInfo.setEnabled(false);
		btn_stuInfo.addActionListener(this);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btn_stuInfo, -30, SpringLayout.NORTH, pic);
		sl_contentPane.putConstraint(SpringLayout.WEST, btn_stuInfo, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btn_stuInfo, -6, SpringLayout.NORTH, pic);
		sl_contentPane.putConstraint(SpringLayout.EAST, btn_stuInfo, -5, SpringLayout.WEST, pane);
		contentPane.add(btn_stuInfo);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		//Add students through menu or button
		if(src == this.btn_addStu){
			this.addRandomStudent(1);
		}else if(src == this.mntm_AddStudents_1){
			this.addRandomStudent(1);
		}else if(src == this.mntm_AddStudents_5){
			this.addRandomStudent(5);
		}else if(src == this.mntm_AddStudents_10){
			this.addRandomStudent(10);
		}
		
		//Remove students through button or menu
		else if(src == this.mntm_RemoveStudent){
			this.removeSelectedStudent();
		}else if(src == this.mntm_RemoveAllStudents){
			this.removeAllStudents();
		}else if(src == this.btn_remStu){
			this.removeSelectedStudent();
		}
		
		
		
	}
	
	/**
	 * Takes a String URL and gets raw text from URL 
	 * 
	 * @param urlString - the site to read the raw text from
	 * @return String Raw site text
	 * @throws IOException if the URL could not be reached
	 */
	private static String readUrl(String urlString) throws IOException{
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
	
	/**
	 *  Removes all students from the list.
	 */
	public void removeAllStudents(){
		this.students.clear();
	}
	
	/**
	 * Removes the current selected student from the list.
	 */
	public void removeSelectedStudent(){
		if(list.getSelectedIndex() < 0) return;
		this.students.remove(list.getSelectedIndex());
	}
	
	/**
	 * Adds <b>amount</b> students to the list from the specified NAME_API_URL.
	 * 
	 * @param amount - the amount of random students to add
	 */
	public void addRandomStudent(int amount){
		Student e;
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create(); //Create the GSON object
		String url;
		
		try {
			url = readUrl(NAME_API_URL+"?results="+amount); //Grabs x amount of JSON Formatted RAW text from the website
			e = gson.fromJson(url, Student.class); //Parses the JSON Formatted RAW text into a JSON Object and then creates a Student.java object with said data.
			
			//For each Result (Result.java is a student, Student.java contains an array of results) add said result to the list.
			for(Result r : e.results){
				r.user.name.first = Character.toString(r.user.name.first.charAt(0)).toUpperCase() + r.user.name.first.substring(1, r.user.name.first.length());
				r.user.name.last = Character.toString(r.user.name.last.charAt(0)).toUpperCase() + r.user.name.last.substring(1, r.user.name.last.length());
				students.addElement(r.user);
				System.out.println("Added: "+r.user.name.first+" "+r.user.name.last);
			}
			
			//Select the last added Student and scroll down.
			list.setSelectedIndex(list.getModel().getSize()-1);
			pane.getVerticalScrollBar().setValue(pane.getVerticalScrollBar().getMaximum());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		try {
			//If nothing is selected change the profile picture to res/picoffline.png and return.
			if(list.getSelectedValue() == null){
				pic.setPicture(null);
				return;
			}
			//Get the profile picture from the selected student's picture URL
			pic.setPicture(new URL(list.getSelectedValue().picture.medium));
		} catch (MalformedURLException e1) {
			pic.setPicture(null);
			e1.printStackTrace();
		}
	}
}
