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

import java.awt.Canvas;

public class MainFrame extends JFrame implements ActionListener, ListSelectionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -493516604584877793L;
	 
	//public static final String TEST_API = "{\"results\":[{\"user\":{\"gender\":\"male\",\"name\":{\"title\":\"mr\",\"first\":\"gilbert\",\"last\":\"ray\"},\"location\":{\"street\":\"1293 ash dr\",\"city\":\"desoto\",\"state\":\"missouri\",\"zip\":\"20704\"},\"email\":\"gilbert.ray77@example.com\",\"username\":\"heavytiger275\",\"password\":\"revoluti\",\"salt\":\"pAGjTtDW\",\"md5\":\"b02ec65267764121f0d44f9376c38b32\",\"sha1\":\"d71b219dda456fe958f455c5fa64ed0d362747a2\",\"sha256\":\"10f81094320d80e1315f20fcc0bf6553aacf09d02fde845f37a1eb8382916e9a\",\"registered\":\"915513209\",\"dob\":\"456379693\",\"phone\":\"(960)-504-6524\",\"cell\":\"(370)-745-2002\",\"SSN\":\"101-56-3253\",\"picture\":{\"large\":\"http://api.randomuser.me/portraits/men/92.jpg\",\"medium\":\"http://api.randomuser.me/portraits/med/men/92.jpg\",\"thumbnail\":\"http://api.randomuser.me/portraits/thumb/men/92.jpg\"},\"version\":\"0.5\",\"nationality\":\"US\"},\"seed\":\"09d997f9ccff15bc\"},{\"user\":{\"gender\":\"male\",\"name\":{\"title\":\"mr\",\"first\":\"wade\",\"last\":\"jordan\"},\"location\":{\"street\":\"2495 e center st\",\"city\":\"salt lake city\",\"state\":\"rhode island\",\"zip\":\"28155\"},\"email\":\"wade.jordan76@example.com\",\"username\":\"beautifulduck799\",\"password\":\"inferno\",\"salt\":\"SPaMe8ss\",\"md5\":\"b532310c743c387030547cc15c348119\",\"sha1\":\"4d8c357c1503c680589b926818c799ecde352d2b\",\"sha256\":\"dae1b6c2deb731af2158ba166f3818445889fabd1e2129d378a5ee574f1fb875\",\"registered\":\"1175684117\",\"dob\":\"28981519\",\"phone\":\"(843)-891-1192\",\"cell\":\"(888)-652-3972\",\"SSN\":\"559-42-5081\",\"picture\":{\"large\":\"http://api.randomuser.me/portraits/men/18.jpg\",\"medium\":\"http://api.randomuser.me/portraits/med/men/18.jpg\",\"thumbnail\":\"http://api.randomuser.me/portraits/thumb/men/18.jpg\"},\"version\":\"0.5\",\"nationality\":\"US\"},\"seed\":\"84aac161b63af30f\"},{\"user\":{\"gender\":\"male\",\"name\":{\"title\":\"mr\",\"first\":\"johnny\",\"last\":\"holland\"},\"location\":{\"street\":\"2622 hickory creek dr\",\"city\":\"fountain valley\",\"state\":\"west virginia\",\"zip\":\"43601\"},\"email\":\"johnny.holland63@example.com\",\"username\":\"redrabbit678\",\"password\":\"captain1\",\"salt\":\"9WPeQ3PY\",\"md5\":\"5fb18a1190d4a64f54808c8caff0e197\",\"sha1\":\"7e986b74000232223cdd6541d5c1a3707efd48b1\",\"sha256\":\"13fd7a29c33f9003f2b5abc250d8568e5b5e38adcc2ce7b2437b5f83e9971c83\",\"registered\":\"1020348023\",\"dob\":\"43812718\",\"phone\":\"(186)-346-5713\",\"cell\":\"(135)-824-3122\",\"SSN\":\"240-99-5590\",\"picture\":{\"large\":\"http://api.randomuser.me/portraits/men/21.jpg\",\"medium\":\"http://api.randomuser.me/portraits/med/men/21.jpg\",\"thumbnail\":\"http://api.randomuser.me/portraits/thumb/men/21.jpg\"},\"version\":\"0.5\",\"nationality\":\"US\"},\"seed\":\"c617cdaf469f0a96\"},{\"user\":{\"gender\":\"female\",\"name\":{\"title\":\"ms\",\"first\":\"wilma\",\"last\":\"porter\"},\"location\":{\"street\":\"9380 paddock way\",\"city\":\"modesto\",\"state\":\"new mexico\",\"zip\":\"48469\"},\"email\":\"wilma.porter25@example.com\",\"username\":\"goldenladybug252\",\"password\":\"blades\",\"salt\":\"DqAJYgLV\",\"md5\":\"4c22c43e52a45668ac6f3d15ff8ddd7f\",\"sha1\":\"78cb6cd9d683c3a17855a07d51caf9081d3efede\",\"sha256\":\"a32ccef4edf1574948c1aafb2e8625d31642cb4136c822e27db49ca3451bb674\",\"registered\":\"1035362862\",\"dob\":\"290557436\",\"phone\":\"(872)-832-2133\",\"cell\":\"(199)-963-8300\",\"SSN\":\"387-72-6113\",\"picture\":{\"large\":\"http://api.randomuser.me/portraits/women/94.jpg\",\"medium\":\"http://api.randomuser.me/portraits/med/women/94.jpg\",\"thumbnail\":\"http://api.randomuser.me/portraits/thumb/women/94.jpg\"},\"version\":\"0.5\",\"nationality\":\"US\"},\"seed\":\"357da641646ca9e7\"},{\"user\":{\"gender\":\"female\",\"name\":{\"title\":\"mrs\",\"first\":\"lillian\",\"last\":\"hopkins\"},\"location\":{\"street\":\"9279 parker rd\",\"city\":\"coppell\",\"state\":\"indiana\",\"zip\":\"84857\"},\"email\":\"lillian.hopkins19@example.com\",\"username\":\"silvermouse3\",\"password\":\"willow\",\"salt\":\"RkZDmGAS\",\"md5\":\"ba96c8b0301fb5d9a6782fd10c1abe31\",\"sha1\":\"a9aa9873af00225bab54c40fb6228866b3117879\",\"sha256\":\"a931dbad876bb1d77f19bfd07da102360ed1be9c1c65a1c0e88e56d229a774f3\",\"registered\":\"1236219657\",\"dob\":\"319598766\",\"phone\":\"(382)-147-9140\",\"cell\":\"(194)-487-8403\",\"SSN\":\"945-50-6525\",\"picture\":{\"large\":\"http://api.randomuser.me/portraits/women/22.jpg\",\"medium\":\"http://api.randomuser.me/portraits/med/women/22.jpg\",\"thumbnail\":\"http://api.randomuser.me/portraits/thumb/women/22.jpg\"},\"version\":\"0.5\",\"nationality\":\"US\"},\"seed\":\"a1759b5b6cd2dbc0\"},{\"user\":{\"gender\":\"female\",\"name\":{\"title\":\"ms\",\"first\":\"taylor\",\"last\":\"hart\"},\"location\":{\"street\":\"1050 w sherman dr\",\"city\":\"fort collins\",\"state\":\"louisiana\",\"zip\":\"66857\"},\"email\":\"taylor.hart34@example.com\",\"username\":\"bigkoala552\",\"password\":\"coral\",\"salt\":\"GminbLxS\",\"md5\":\"8d135cddffe9ec89409975b87495fef5\",\"sha1\":\"d1cdced7ee5e58f4f62fb759fab3a8211653d1d6\",\"sha256\":\"4f969774890eed68408fa18ccb14e606ef190b58aeecfdeeb9fec755b3186392\",\"registered\":\"1347633715\",\"dob\":\"398209547\",\"phone\":\"(285)-815-3168\",\"cell\":\"(152)-283-6790\",\"SSN\":\"232-22-4646\",\"picture\":{\"large\":\"http://api.randomuser.me/portraits/women/90.jpg\",\"medium\":\"http://api.randomuser.me/portraits/med/women/90.jpg\",\"thumbnail\":\"http://api.randomuser.me/portraits/thumb/women/90.jpg\"},\"version\":\"0.5\",\"nationality\":\"US\"},\"seed\":\"edd33b52576ebd92\"},{\"user\":{\"gender\":\"female\",\"name\":{\"title\":\"miss\",\"first\":\"taylor\",\"last\":\"barrett\"},\"location\":{\"street\":\"5243 james st\",\"city\":\"roanoke\",\"state\":\"new hampshire\",\"zip\":\"99740\"},\"email\":\"taylor.barrett82@example.com\",\"username\":\"heavyostrich336\",\"password\":\"stang\",\"salt\":\"cJCde2WG\",\"md5\":\"9f77dfa66cd90c57a332c9d10d5556de\",\"sha1\":\"028fb83c17251e6bcf92a34e433a1b7f3b0bcf0f\",\"sha256\":\"c9f914620cab8751694f3366dfd7c5daa023447cff513c77b0863f3b203721f7\",\"registered\":\"1383147007\",\"dob\":\"360622521\",\"phone\":\"(845)-942-1488\",\"cell\":\"(232)-800-8989\",\"SSN\":\"934-68-9874\",\"picture\":{\"large\":\"http://api.randomuser.me/portraits/women/70.jpg\",\"medium\":\"http://api.randomuser.me/portraits/med/women/70.jpg\",\"thumbnail\":\"http://api.randomuser.me/portraits/thumb/women/70.jpg\"},\"version\":\"0.5\",\"nationality\":\"US\"},\"seed\":\"76d9928db4b10620\"},{\"user\":{\"gender\":\"female\",\"name\":{\"title\":\"miss\",\"first\":\"vanessa\",\"last\":\"butler\"},\"location\":{\"street\":\"1031 ranchview dr\",\"city\":\"yakima\",\"state\":\"florida\",\"zip\":\"11049\"},\"email\":\"vanessa.butler63@example.com\",\"username\":\"blackwolf963\",\"password\":\"audio\",\"salt\":\"mzVrTus9\",\"md5\":\"e3f84222d4820e96d90bd55dfd14eda0\",\"sha1\":\"6b8a6535c23daccfd94188eb84c654f28711f652\",\"sha256\":\"881cfd14a9e131b7de29b4ef9aab6818edb49e7770810807f29e3f555c8f493f\",\"registered\":\"1265664143\",\"dob\":\"120513547\",\"phone\":\"(168)-697-9353\",\"cell\":\"(146)-669-9752\",\"SSN\":\"130-59-2542\",\"picture\":{\"large\":\"http://api.randomuser.me/portraits/women/31.jpg\",\"medium\":\"http://api.randomuser.me/portraits/med/women/31.jpg\",\"thumbnail\":\"http://api.randomuser.me/portraits/thumb/women/31.jpg\"},\"version\":\"0.5\",\"nationality\":\"US\"},\"seed\":\"4c512d3eee29a234\"},{\"user\":{\"gender\":\"male\",\"name\":{\"title\":\"mr\",\"first\":\"norman\",\"last\":\"ray\"},\"location\":{\"street\":\"4601 brown terrace\",\"city\":\"shelby\",\"state\":\"alabama\",\"zip\":\"60214\"},\"email\":\"norman.ray93@example.com\",\"username\":\"brownmeercat785\",\"password\":\"billabon\",\"salt\":\"G1U6usVm\",\"md5\":\"c3f28e5b8b1dccfb3908a10fa91e1359\",\"sha1\":\"6831d474dc8c76bbafee291c3283c36e40dec6c2\",\"sha256\":\"4e94d8d7619a6aa95b8981fb7b1834c84c1b4d6784a0f05f773b711b60bd8886\",\"registered\":\"1036386725\",\"dob\":\"369214007\",\"phone\":\"(161)-237-4757\",\"cell\":\"(660)-439-5891\",\"SSN\":\"107-45-2148\",\"picture\":{\"large\":\"http://api.randomuser.me/portraits/men/30.jpg\",\"medium\":\"http://api.randomuser.me/portraits/med/men/30.jpg\",\"thumbnail\":\"http://api.randomuser.me/portraits/thumb/men/30.jpg\"},\"version\":\"0.5\",\"nationality\":\"US\"},\"seed\":\"be209763d30cdf72\"},{\"user\":{\"gender\":\"male\",\"name\":{\"title\":\"mr\",\"first\":\"lewis\",\"last\":\"howard\"},\"location\":{\"street\":\"8509 n stelling rd\",\"city\":\"seagoville\",\"state\":\"louisiana\",\"zip\":\"46933\"},\"email\":\"lewis.howard50@example.com\",\"username\":\"beautifulkoala946\",\"password\":\"wayer\",\"salt\":\"kcgk4BLZ\",\"md5\":\"dbd4650c3b1e5e3b9a36635e1ee4c845\",\"sha1\":\"0f24b97833610f0c3c665d4da8e98b4d99f5bd93\",\"sha256\":\"36997e4f637f27bb08bccf3d9d738147d07a0baf0bd62f691fc6b727bed61f0a\",\"registered\":\"973208297\",\"dob\":\"223861380\",\"phone\":\"(635)-311-4942\",\"cell\":\"(613)-835-2508\",\"SSN\":\"615-10-2660\",\"picture\":{\"large\":\"http://api.randomuser.me/portraits/men/80.jpg\",\"medium\":\"http://api.randomuser.me/portraits/med/men/80.jpg\",\"thumbnail\":\"http://api.randomuser.me/portraits/thumb/men/80.jpg\"},\"version\":\"0.5\",\"nationality\":\"US\"},\"seed\":\"8488ecaaba306154\"}]}";
	public static final String NAME_API_URL = "http://api.randomuser.me/";
	
	private JPanel contentPane;
	
	JList<User> list;
	private DefaultListModel<User> students = new DefaultListModel<User>();
	JButton btn_addStu;
	JButton btn_remStu;
	JProfilePicture pic;
	JScrollPane pane;
	
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
		
		btn_addStu = new JButton("Add Random Student");
		sl_contentPane.putConstraint(SpringLayout.WEST, btn_addStu, 5, SpringLayout.WEST, contentPane);
		btn_addStu.addActionListener(this);
		contentPane.add(btn_addStu);
		
		btn_remStu = new JButton("Remove Student");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btn_remStu, -50, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btn_remStu, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btn_addStu, -50, SpringLayout.NORTH, btn_remStu);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btn_remStu, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btn_addStu, -6, SpringLayout.NORTH, btn_remStu);
		btn_remStu.addActionListener(this);
		contentPane.add(btn_remStu);
		
		list = new JList<User>(students);
		pane = new JScrollPane(list);
		sl_contentPane.putConstraint(SpringLayout.EAST, btn_remStu, -5, SpringLayout.WEST, pane);
		sl_contentPane.putConstraint(SpringLayout.EAST, btn_addStu, -5, SpringLayout.WEST, pane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, pane, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, pane, 0, SpringLayout.EAST, lbl_classroom);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, pane, -10, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, pane, -10, SpringLayout.EAST, contentPane);
		//contentPane.add(list);
		list.addListSelectionListener(this);
		contentPane.add(pane);
		
		pic = new JProfilePicture();
		sl_contentPane.putConstraint(SpringLayout.NORTH, pic, -150, SpringLayout.NORTH, btn_addStu);
		sl_contentPane.putConstraint(SpringLayout.WEST, pic, 5, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, pic, -6, SpringLayout.NORTH, btn_addStu);
		sl_contentPane.putConstraint(SpringLayout.EAST, pic, -5, SpringLayout.WEST, pane);
		contentPane.add(pic);
		
		JButton btn_stuInfo = new JButton("View Student Info");
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
			try {
				this.addRandomStudent(1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(src == this.mntm_AddStudents_1){
			try {
				this.addRandomStudent(1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(src == this.mntm_AddStudents_5){
			try {
				this.addRandomStudent(5);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(src == this.mntm_AddStudents_10){
			try {
				this.addRandomStudent(10);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
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
	
	public void removeAllStudents(){
		this.students.clear();
	}
	
	public void removeSelectedStudent(){
		if(list.getSelectedIndex() < 0) return;
		this.students.remove(list.getSelectedIndex());
	}
	
	public void addRandomStudent(int amount) throws MalformedURLException, IOException{
		Student e;
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String url;
		try {
			url = readUrl(NAME_API_URL+"?results="+amount); //TODO Online use only
			//url = TEST_API; //TODO Offline use only
			e = gson.fromJson(url, Student.class);
			for(Result r : e.results){
				System.out.println("Added: "+r.user.name.first+" "+r.user.name.last);
				students.addElement(r.user);
			}
			list.setSelectedIndex(list.getModel().getSize()-1);
			pane.getVerticalScrollBar().setValue(pane.getVerticalScrollBar().getMaximum());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		try {
			if(list.getSelectedValue() == null){
				pic.setPicture(null);
				return;
			}
			pic.setPicture(new URL(list.getSelectedValue().picture.medium));
		} catch (MalformedURLException e1) {
			pic.setPicture(null);
			e1.printStackTrace();
		}
	}
}
