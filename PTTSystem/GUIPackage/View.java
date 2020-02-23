package GUIPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class View extends JFrame{
//	JLabel title,courseDirctor; 
	public JButton loginBN,logoutBN,semesterBN,courseList,teacherList,requestList,createCourse,ok,cancel;
	public JTextField usernameTF,passwordTF, semesterTF,courseNameTF,requirment1TF,requirment2TF;
	public Color blue = new java.awt.Color(135,206,250);
	public JPanel barPanel, loginPanel, CDPanel, semesterPanel;
	public Semester semester;
	public Login login;
	
	public List<JPanel> pList = new ArrayList<JPanel>();
	public View() {
		//Set Frame title, size, the location where execution occurs, and action to close a window;
	}
	
	public void initialise() {
		this.setTitle("PTT Manage System");
		this.setSize(600,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(400,240);
		semester = new Semester();
		login = new Login();
		login.buildLoginPanel();
		loginPanel=login.getLoginPanel();
		this.add(loginPanel);
		this.setVisible(true);
		barPanel=null;
		System.out.println("inv");
	}
	
	public void cleanAllPanel() {
		for(JPanel p : this.pList) {
			p.setVisible(false);
			this.remove(p);
		}
	}
	
	
	public class Login{
		public void buildLoginPanel() {
				loginPanel = new JPanel(new GridLayout(4,1,0,12));
				loginPanel.setBackground(Color.white);
				loginPanel.setFocusable(true);
				loginPanel.setBorder(BorderFactory.createEmptyBorder(70,200,90,200));
				
				JLabel title = new JLabel("Login", SwingConstants.CENTER);
				Font f = new Font("TimesRoman",Font.PLAIN,23);
				title.setFont(f);
				
				usernameTF = new JTextField(16);
				usernameTF.addFocusListener(new JTextFieldHintListener(usernameTF, "username"));
				usernameTF.setBorder(BorderFactory.createLineBorder(blue)); 
				
				passwordTF = new JTextField(16);
				passwordTF.addFocusListener(new JTextFieldHintListener(passwordTF, "password"));
				passwordTF.setBorder(BorderFactory.createLineBorder(blue));
				
				loginBN = new JButton("ENTER");
				loginBN.setBackground(blue);
				loginBN.setFocusPainted(false);
				System.out.println("123");
		
				loginPanel.add(title);
				loginPanel.add(usernameTF);
				loginPanel.add(passwordTF);
				loginPanel.add(loginBN);
		}
		
		public JPanel getLoginPanel() {
			return loginPanel;
		}
		
		//get login input
		public String[] getAccount() {
			String[] s = new String[2];
			s[0]= usernameTF.getText();
			s[1]= passwordTF.getText();	
			return s;
		}
		
		
		public void cleanLogin() {
			usernameTF.addFocusListener(new JTextFieldHintListener(usernameTF, "username"));
			passwordTF.addFocusListener(new JTextFieldHintListener(passwordTF, "password"));
		}
		
		public int logout() {
			int n = JOptionPane.showConfirmDialog(null, "Are you sure to log out?", "wanring",JOptionPane.YES_NO_OPTION);
			return n;
		}
	}
	
	public class Semester{
		public void buildSemesterPanel() {
				semesterPanel = new JPanel(new GridLayout(4,1,0,12));
				semesterPanel.setBackground(Color.white);
				semesterPanel.setFocusable(true);
				semesterPanel.setBorder(BorderFactory.createEmptyBorder(70,200,90,200));
				
				JLabel title = new JLabel("Select a semester to access.", SwingConstants.CENTER);
				Font f = new Font("TimesRoman",Font.PLAIN,12);
				title.setFont(f);
				
				semesterTF = new JTextField(4);
		
				semesterTF.setBorder(BorderFactory.createLineBorder(blue)); 
				
				
				semesterBN = new JButton("Select");
				semesterBN.setBackground(blue);
				semesterBN.setFocusPainted(false);
		
				semesterPanel.add(title);
				semesterPanel.add(semesterTF);
				semesterPanel.add(semesterBN);
		}
		
		
		public JPanel getSemesterPanel(){
			return semesterPanel;
		}
		
		public void diplayLatestSemester(int num) {
			semesterTF.addFocusListener(new JTextFieldHintListener(semesterTF, ""+num));
		}
		
		public int getSelecetedSemester() {
			return Integer.parseInt(semesterTF.getText());
		}
		
		
	}
	
	
	
	public JPanel aa() {
		if(barPanel==null) {
			barPanel = buildCDBar();
		}
		return barPanel;
	}
	
	// Course Director 
	public JPanel buildCDPanel() {
		if(CDPanel==null) {
			JPanel CDPanel = new JPanel(new BorderLayout());
			JPanel bar = buildCDBar();
			CDPanel.add(bar,BorderLayout.WEST);
			CDPanel.add(centerPanel(),BorderLayout.CENTER);
		}
		return CDPanel;
	}
	
	public JPanel buildCDBar() {
		JPanel westPanel = new JPanel(new BorderLayout());
		JPanel westNorth = new JPanel();
		JPanel westCenter = new JPanel(new GridLayout(2,1));
		JPanel westSouth = new JPanel(new GridLayout(3,1,0,20));
		JPanel list = new JPanel(new GridLayout(3,1,0,5));
		
		westNorth.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
		westNorth.setBackground(blue);
		westCenter.setBackground(blue);
		westSouth.setBackground(blue);
		list.setBackground(blue);
		
		JLabel courseDirector = new JLabel("Course Dirctor");
		JLabel name = new JLabel("Mike",SwingConstants.CENTER);
		courseList = new JButton("Course List");
		teacherList = new JButton("Teacher List");
		requestList = new JButton("Request List");
		logoutBN = new JButton("Log Out");
		
	
		courseList.setBackground(new java.awt.Color(135,206,250));
		courseList.setBorder(BorderFactory.createLineBorder(blue));
		
		teacherList.setBackground(new java.awt.Color(135,206,250));
		teacherList.setBorder(BorderFactory.createLineBorder(blue)); 
		requestList.setBackground(new java.awt.Color(135,206,250));
		requestList.setBorder(BorderFactory.createLineBorder(blue)); 
		logoutBN.setBackground(new java.awt.Color(135,206,250));
		logoutBN.setBorder(BorderFactory.createLineBorder(blue)); 
		
		courseList.setFocusPainted(false);
		teacherList.setFocusPainted(false);
		requestList.setFocusPainted(false);
		logoutBN.setFocusPainted(false);
		
		westNorth.add(courseDirector);
		Font f = new Font("TimesRoman",Font.PLAIN,15);
		courseDirector.setFont(f);
		
		list.add(courseList);
		list.add(teacherList);
		list.add(requestList);
		westCenter.add(list);
		
		westSouth.add(name);
		westSouth.add(logoutBN);
		
		westPanel.add(westNorth,BorderLayout.NORTH);
		westPanel.add(westCenter,BorderLayout.CENTER);
		westPanel.add(westSouth,BorderLayout.SOUTH);
		
		return westPanel;
	}
	
	public JPanel getCDBar() {
		if(barPanel==null) {
			System.out.print("123123");
			barPanel = buildCDBar();
		}
		return barPanel;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public JPanel centerPanel() {
		JPanel centerPanel = new JPanel(new BorderLayout());
		JPanel centerTop = new JPanel(new BorderLayout());
		centerPanel.setBackground(Color.white);
		centerTop.setBackground(Color.white);
		
		createCourse = new JButton("create new course");
		createCourse.setFocusPainted(false);
	
		centerTop.setBorder(BorderFactory.createEmptyBorder(20,10,10,50));
		centerTop.add(createCourse,BorderLayout.EAST);
		centerPanel.add(centerTop,BorderLayout.NORTH);
		return centerPanel;
	}
	
	// Create course
	public JPanel CreateCoursePanel() {
		JPanel center = new JPanel(new GridLayout(5,1,0,10));
		center.setBorder(BorderFactory.createEmptyBorder(40,180,100,180));
		JPanel buttonPanel = new JPanel(new FlowLayout());
		
		center.setBackground(Color.white);
		buttonPanel.setBackground(Color.white);
		
		JLabel label = new JLabel("create new course", SwingConstants.CENTER);
		Font f = new Font("TimesRoman",Font.PLAIN,23);
		label.setFont(f);
		
		courseNameTF = new JTextField();
		requirment1TF= new JTextField();
		requirment2TF= new JTextField();
		
		courseNameTF.addFocusListener(new JTextFieldHintListener(courseNameTF, "class name"));
		requirment1TF.addFocusListener(new JTextFieldHintListener(requirment1TF, "requirment 1"));
		requirment2TF.addFocusListener(new JTextFieldHintListener(requirment2TF, "requirment 2"));
		
		courseNameTF.setBorder(BorderFactory.createLineBorder(blue));
		requirment1TF.setBorder(BorderFactory.createLineBorder(blue));
		requirment2TF.setBorder(BorderFactory.createLineBorder(blue));
		
//		add = new JButton("add a new requirement");
		ok = new JButton("Ok");
		cancel = new JButton("Cancel");
		
		ok.setBackground(blue);
		ok.setFocusPainted(false);
	
		
		cancel.setBackground(blue);
		cancel.setFocusPainted(false);

		
		buttonPanel.add(ok);
		buttonPanel.add(cancel);
		
		center.add(label);
		center.add(courseNameTF);
		center.add(requirment1TF);
		center.add(requirment2TF);
//		center.add(add);
		center.add(buttonPanel);
		return center;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String[] askLogin() {
		String key [] = new String [2];
		
		// set which swing component to be displayed
		
		
		
		
		key[0] = "007";
		key[1] = "hey baby";
		
		return key;
	}
	
	
	public int askSelectSemester(int latestSemester) {
		// set which swing component to be displayed
		//display currentSemester to user
		//continually ask user to input a correct semester
		int input = 0;
		while(input > latestSemester || input < 1) {
			input = 4; //get semester from user
		}
		
		return input;
	}
	
	
}
