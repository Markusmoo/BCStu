package ca.tonsaker.bcstu;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.SpringLayout;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.google.gson.Gson;

public class MainFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -493516604584877793L;
	
	public static final String NAME_API_URL = "http://api.randomuser.me/";
	
	private JPanel contentPane;
	
	JList<Student> list;
	private DefaultListModel<Student> students = new DefaultListModel<Student>();
	JButton btn_addStu;
	JButton btn_remStu;

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
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mnFile.add(mntmLoad);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lbl_classroom = new JLabel("Classroom:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lbl_classroom, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lbl_classroom, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lbl_classroom, 20, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lbl_classroom, 150, SpringLayout.WEST, contentPane);
		contentPane.add(lbl_classroom);
		
		btn_addStu = new JButton("Add Random Stu");
		sl_contentPane.putConstraint(SpringLayout.EAST, btn_addStu, 150, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btn_addStu, 40, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btn_addStu, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btn_addStu, -226, SpringLayout.SOUTH, contentPane);
		btn_addStu.addActionListener(this);
		contentPane.add(btn_addStu);
		
		btn_remStu = new JButton("Remove Rand Stu");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btn_remStu, 1, SpringLayout.SOUTH, btn_addStu);
		sl_contentPane.putConstraint(SpringLayout.WEST, btn_remStu, 10, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btn_remStu, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btn_remStu, 150, SpringLayout.WEST, contentPane);
		contentPane.add(btn_remStu);
		
		list = new JList<Student>(students);
		sl_contentPane.putConstraint(SpringLayout.NORTH, list, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, list, 10, SpringLayout.EAST, btn_addStu);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, list, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, list, -10, SpringLayout.EAST, contentPane);
		contentPane.add(list);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == btn_addStu){
			try {
				this.addRandomStudent();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
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
	
	public void addRandomStudent() throws MalformedURLException, IOException{
		Student e = new Student();
		Gson gson = new Gson();
		
		String url;
		try {
			url = readUrl(NAME_API_URL);
			System.out.println(url);
			e = gson.fromJson(url, Student.class);
			students.addElement(e);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
