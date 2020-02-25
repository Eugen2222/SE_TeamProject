package GUIPackage;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class View extends JFrame  implements ActionListener{
//	JLabel title,courseDirctor; 
	public JButton loginBN,logoutBN,semesterBN,createClassBN,classListBN,teacherList,requestList,createCourse,createClassOKBN,createClassCBN;
	public JTextField usernameTF,passwordTF, semesterTF,courseNameTF,requirment1TF,requirment2TF;
	public Color blue = new java.awt.Color(135,206,250);
	public JPanel barPanel, loginPanel, semesterPanel, framePanel, centerPanel, createClassPanel, classListPanel, rootPanel;
	public JTable classListTable;
	public JTextArea requirmentTA;
	public Semester semester;
	public Login login;
	public Bar bar;
	public Main main;
	public Frame frame;
	private CardLayout page;
	private CardLayout centerPage;	
	public View(){
		//Set Frame title, size, the location where execution occurs, and action to close a window;
	
	}
	public void initialise() {
		this.getContentPane().removeAll();

		this.setTitle("PTT Manage System");
		this.setSize(900,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(400,240);
		semester = new Semester();
		login = new Login();
		bar = new Bar();
		main = new Main();
		frame = new Frame();
		loginPanel=null;
		barPanel=null;
		rootPanel = new JPanel();
		page = new CardLayout();
		rootPanel.setLayout(page);
		this.add(rootPanel);
		this.setVisible(true);
		refresh();
		
		System.out.println("initilase");
	}
	//clean all registered panel
	
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	
	public void refresh() {
		this.revalidate();
		this.repaint();
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
		
				loginPanel.add(title);
				loginPanel.add(usernameTF);
				loginPanel.add(passwordTF);
				loginPanel.add(loginBN);
				View.this.rootPanel.add(loginPanel,"LoginPage");
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
		
		//when user type wrong
		public void cleanLogin() {
			usernameTF.addFocusListener(new JTextFieldHintListener(usernameTF, "username"));
			passwordTF.addFocusListener(new JTextFieldHintListener(passwordTF, "password"));
		}
		
		public int logOutCheck() {
			int n = JOptionPane.showConfirmDialog(null, "Are you sure to log out?", "wanring",JOptionPane.YES_NO_OPTION);
			return n;
		}
		
		public void displayLoginPanel() {
			View.this.page.show(rootPanel, "LoginPage");
			refresh();
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
				View.this.rootPanel.add(semesterPanel,"SemesterPage");
		}
		
		public void displayLatestSemester(int num) {
			semesterTF.addFocusListener(new JTextFieldHintListener(semesterTF, ""+num));
		}
		
		public int getSelecetedSemester() {
			return Integer.parseInt(semesterTF.getText());
		}
		
		public void displaySemesterPanel() {
			View.this.page.show(rootPanel, "SemesterPage");
			View.this.refresh();
		}
		
	}
	
	
	public class Frame{
		public void buildFramePanel(JPanel bar) {
			framePanel = new JPanel(new BorderLayout());
			framePanel.add(bar, BorderLayout.WEST);
			framePanel.add(View.this.centerPanel, BorderLayout.CENTER);
			rootPanel.add(framePanel, "mainPage");
			
		}
		public void displayFramePanel() {
			page.show(rootPanel, "mainPage");
			View.this.refresh();
		}
	}
	
	public class Main{
		String defaultClassName = "class name";
		String defaultClassRequirements = "requirment";
		
		Main(){
			centerPage =new CardLayout();
			centerPanel = new JPanel(centerPage);
			centerPanel.setBackground(Color.white);
		}
		
		
		public void buildCreateClassPanel() {
			JPanel center = new JPanel(new GridLayout(5,1,0,10));
			center.setBorder(BorderFactory.createEmptyBorder(40,180,100,180));
			JPanel buttonPanel = new JPanel(new FlowLayout());
			JLabel classNameL = new JLabel("Class name:     ");
			JLabel reqL1 = new JLabel("Requirement:  ");
//			JLabel reqL2 = new JLabel("Requirement 2:  ");
			center.setBackground(Color.white);
			buttonPanel.setBackground(Color.white);
	
			
			JLabel label = new JLabel("Create new class", SwingConstants.CENTER);
			Font f = new Font("TimesRoman",Font.PLAIN,23);
			label.setFont(f);
			
			courseNameTF = new JTextField();
			requirmentTA= new JTextArea();
//			requirment2TF= new JTextField();
			
			courseNameTF.addFocusListener(new JTextFieldHintListener(courseNameTF, defaultClassName));
//			requirment.addFocusListener(new JTextFieldHintListener(requirment, defaultClassRequirements));
//			requirment2TF.addFocusListener(new JTextFieldHintListener(requirment2TF, defaultClassRequirements));
			
			courseNameTF.setBorder(BorderFactory.createLineBorder(blue));
			requirmentTA.setBorder(BorderFactory.createLineBorder(blue));
//			requirment2TF.setBorder(BorderFactory.createLineBorder(blue));
//			add = new JButton("add a new requirement");
			createClassOKBN = new JButton("Ok");
			createClassCBN = new JButton("Cancel");
			createClassCBN.addActionListener(View.this);
			createClassOKBN.setBackground(blue);
			createClassOKBN.setFocusPainted(false);
		
			createClassCBN.setBackground(blue);
			createClassCBN.setFocusPainted(false);

			
			buttonPanel.add(createClassOKBN);
			buttonPanel.add(createClassCBN);
			JPanel rowPanel1 = new JPanel(new BorderLayout());
			JPanel rowPanel2 = new JPanel(new BorderLayout());
			JPanel rowPanel3 = new JPanel(new BorderLayout());
			JPanel rowPanel4 = new JPanel(new BorderLayout());
			
			rowPanel1.setBackground(Color.white);
			rowPanel2.setBackground(Color.white);
			rowPanel3.setBackground(Color.white);
			rowPanel4.setBackground(Color.white);
			
			
			rowPanel1.add(label, BorderLayout.CENTER);
			rowPanel2.add(classNameL, BorderLayout.WEST);
			rowPanel2.add(courseNameTF, BorderLayout.CENTER);
			rowPanel3.add(reqL1, BorderLayout.WEST);
			rowPanel3.add(requirmentTA, BorderLayout.CENTER);
//			rowPanel4.add(reqL2, BorderLayout.WEST);
//			rowPanel4.add(requirment2TF, BorderLayout.CENTER);
//			center.add(add);
			center.add(rowPanel1);
			center.add(rowPanel2);
			center.add(rowPanel3);
//			center.add(rowPanel4);
			
			center.add(buttonPanel);
			createClassPanel = center;
			centerPanel.add(createClassPanel, "createClassPanel");
			
		}
		
		public void displayCreateClassPanel() {
			centerPage.show(centerPanel, "createClassPanel");
			View.this.refresh();
		}
		
		
		
		
		public String getCreateClassString() {
			String s = "";
			if(courseNameTF.getText().equals(defaultClassName)|| courseNameTF.getText().equals("")) {
				return null;
			}
			s += encodeString(courseNameTF.getText());
			String req ="";
			if(requirmentTA.getText().equals(" ")|| requirmentTA.getText().equals("")) {
				return null;
			}
			req += requirmentTA.getText();
			
			s += encodeString(req);
	
			return s;
		}
		
		public void cleanCreateClassText() {
			courseNameTF.addFocusListener(new JTextFieldHintListener(courseNameTF, defaultClassName));
		}
		
	
	
		
		
		
		public void buildClassListPanel(String[] header, String[][] list) {
			classListPanel = new JPanel(new BorderLayout());
			JPanel classListSubP = new JPanel(new BorderLayout());
			JLabel space = new JLabel("");
			createClassBN = new JButton("Create a class");
			createClassBN.setVisible(false);
			createClassBN.setBackground(blue);
			createClassBN.addActionListener(View.this);
			classListSubP.setBackground(Color.white);
			classListPanel.setBackground(Color.white);
			classListSubP.add(space, BorderLayout.CENTER);
			classListSubP.add(createClassBN, BorderLayout.EAST);
			classListPanel.add(classListSubP, BorderLayout.NORTH);
			classListPanel.add(createListTable(header, list), BorderLayout.CENTER);
			classListPanel.setVisible(true);
			centerPanel.add(classListPanel, "classListPanel");
		}
		
		
		public JScrollPane createListTable(String[] header, String[][] list) {
			classListTable = new JTable(list, header);
			classListTable.setCellSelectionEnabled(true);  
	        ListSelectionModel select= classListTable.getSelectionModel();  
	        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
	        select.addListSelectionListener(new ListSelectionListener() {  
	        	public void valueChanged(ListSelectionEvent e) {  
	        		String Data = null;    
	        		int selectedRow = classListTable.getSelectedRow();  
	                Data = (String) classListTable.getValueAt(selectedRow, 0);  
	                System.out.println("Table element selected is: " + Data);    
	             }       
	         });  
	        
	        classListTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
	        classListTable.setBackground(Color.white);
	        JScrollPane sp=new JScrollPane(classListTable);
	        sp.setBackground(Color.white);
	        return sp;
		}
		


		
		public void displayClassListPanel(String[] header, String[][] list) {	
			TableModel m = new DefaultTableModel(list, header) ;
			for(int i = 0 ; i<header.length; i++) {
				System.out.print(header[i]);
			}
			

			View.this.classListTable.setModel(m);
			centerPage.show(centerPanel, "classListPanel");
			refresh();
		}
		
		public void centerRefresh() {
			View.this.centerPanel.revalidate();
			View.this.centerPanel.repaint();
		}
		
		public String encodeString(String s) {
			if(s!=null) {
				return (!s.equals("")) ? "\""+ s + "\"" : "";
			}
			return null;
		}
		
	}
	

	
		
	// Course Director 
	public class Bar {
		public void addSelfListener() {
			classListBN.addActionListener(View.this);
		}
		public void buildCDBar(String ID, String name) {
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
			JLabel nameL = new JLabel("Hi "+name,SwingConstants.CENTER);
			classListBN = new JButton("Class List");
			teacherList = new JButton("Teacher List");
			requestList = new JButton("Request List");
			logoutBN = new JButton("Log Out");
			
		
			classListBN.setBackground(new java.awt.Color(135,206,250));
			classListBN.setBorder(BorderFactory.createLineBorder(blue));
			
			teacherList.setBackground(new java.awt.Color(135,206,250));
			teacherList.setBorder(BorderFactory.createLineBorder(blue)); 
			requestList.setBackground(new java.awt.Color(135,206,250));
			requestList.setBorder(BorderFactory.createLineBorder(blue)); 
			logoutBN.setBackground(new java.awt.Color(135,206,250));
			logoutBN.setBorder(BorderFactory.createLineBorder(blue)); 
			
			classListBN.setFocusPainted(false);
			teacherList.setFocusPainted(false);
			requestList.setFocusPainted(false);
			logoutBN.setFocusPainted(false);
			
			westNorth.add(courseDirector);
			Font f = new Font("TimesRoman",Font.PLAIN,15);
			courseDirector.setFont(f);
			
			list.add(classListBN);
			list.add(teacherList);
			list.add(requestList);
			westCenter.add(list);
			
			westSouth.add(nameL);
			westSouth.add(logoutBN);
			
			westPanel.add(westNorth,BorderLayout.NORTH);
			westPanel.add(westCenter,BorderLayout.CENTER);
			westPanel.add(westSouth,BorderLayout.SOUTH);
			addSelfListener();
			barPanel = westPanel;
		}
		
		public void buildABar(String ID, String name) {
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
			
			JLabel courseDirector = new JLabel("Administrator");
			JLabel nameL = new JLabel("Hi "+name,SwingConstants.CENTER);
			classListBN = new JButton("Course List");
			teacherList = new JButton("Teacher List");
			requestList = new JButton("Request List");
			logoutBN = new JButton("Log Out");
			
		
			classListBN.setBackground(new java.awt.Color(135,206,250));
			classListBN.setBorder(BorderFactory.createLineBorder(blue));
			
			teacherList.setBackground(new java.awt.Color(135,206,250));
			teacherList.setBorder(BorderFactory.createLineBorder(blue)); 
			requestList.setBackground(new java.awt.Color(135,206,250));
			requestList.setBorder(BorderFactory.createLineBorder(blue)); 
			logoutBN.setBackground(new java.awt.Color(135,206,250));
			logoutBN.setBorder(BorderFactory.createLineBorder(blue)); 
			
			classListBN.setFocusPainted(false);
			teacherList.setFocusPainted(false);
			requestList.setFocusPainted(false);
			logoutBN.setFocusPainted(false);
			
			westNorth.add(courseDirector);
			Font f = new Font("TimesRoman",Font.PLAIN,15);
			courseDirector.setFont(f);
			
			list.add(classListBN);
			list.add(teacherList);
			list.add(requestList);
			westCenter.add(list);
			
			westSouth.add(nameL);
			westSouth.add(logoutBN);
			
			westPanel.add(westNorth,BorderLayout.NORTH);
			westPanel.add(westCenter,BorderLayout.CENTER);
			westPanel.add(westSouth,BorderLayout.SOUTH);
			addSelfListener();
			barPanel =  westPanel;

		}
		
		
		public void buildPDBar(String ID, String name) {
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
			
			JLabel courseDirector = new JLabel("PTT Dirctor");
			JLabel nameL = new JLabel("Hi "+name,SwingConstants.CENTER);
			classListBN = new JButton("Course List");
			teacherList = new JButton("Teacher List");
			requestList = new JButton("Request List");
			logoutBN = new JButton("Log Out");
			
		
			classListBN.setBackground(new java.awt.Color(135,206,250));
			classListBN.setBorder(BorderFactory.createLineBorder(blue));
			
			teacherList.setBackground(new java.awt.Color(135,206,250));
			teacherList.setBorder(BorderFactory.createLineBorder(blue)); 
			requestList.setBackground(new java.awt.Color(135,206,250));
			requestList.setBorder(BorderFactory.createLineBorder(blue)); 
			logoutBN.setBackground(new java.awt.Color(135,206,250));
			logoutBN.setBorder(BorderFactory.createLineBorder(blue)); 
			
			classListBN.setFocusPainted(false);
			teacherList.setFocusPainted(false);
			requestList.setFocusPainted(false);
			logoutBN.setFocusPainted(false);
			
			westNorth.add(courseDirector);
			Font f = new Font("TimesRoman",Font.PLAIN,15);
			courseDirector.setFont(f);
			
			list.add(classListBN);
			list.add(teacherList);
			list.add(requestList);
			westCenter.add(list);
			
			westSouth.add(nameL);
			westSouth.add(logoutBN);
			
			westPanel.add(westNorth,BorderLayout.NORTH);
			westPanel.add(westCenter,BorderLayout.CENTER);
			westPanel.add(westSouth,BorderLayout.SOUTH);
			addSelfListener();
			barPanel =  westPanel;
		}
	}
	
	
	
//	
//	
//	
//	public JPanel centerPanel() {
//		JPanel centerPanel = new JPanel(new BorderLayout());
//		JPanel centerTop = new JPanel(new BorderLayout());
//		centerPanel.setBackground(Color.white);
//		centerTop.setBackground(Color.white);
//		
//		createCourse = new JButton("create new course");
//		createCourse.setFocusPainted(false);
//	
//		centerTop.setBorder(BorderFactory.createEmptyBorder(20,10,10,50));
//		centerTop.add(createCourse,BorderLayout.EAST);
//		centerPanel.add(centerTop,BorderLayout.NORTH);
//		return centerPanel;
//	}
//	
//	// Create course
//	public JPanel CreateCoursePanel() {
//		JPanel center = new JPanel(new GridLayout(5,1,0,10));
//		center.setBorder(BorderFactory.createEmptyBorder(40,180,100,180));
//		JPanel buttonPanel = new JPanel(new FlowLayout());
//		
//		center.setBackground(Color.white);
//		buttonPanel.setBackground(Color.white);
//		
//		JLabel label = new JLabel("create new course", SwingConstants.CENTER);
//		Font f = new Font("TimesRoman",Font.PLAIN,23);
//		label.setFont(f);
//		
//		courseNameTF = new JTextField();
//		requirment1TF= new JTextField();
//		requirment2TF= new JTextField();
//		
//		courseNameTF.addFocusListener(new JTextFieldHintListener(courseNameTF, "class name"));
//		requirment1TF.addFocusListener(new JTextFieldHintListener(requirment1TF, "requirment 1"));
//		requirment2TF.addFocusListener(new JTextFieldHintListener(requirment2TF, "requirment 2"));
//		
//		courseNameTF.setBorder(BorderFactory.createLineBorder(blue));
//		requirment1TF.setBorder(BorderFactory.createLineBorder(blue));
//		requirment2TF.setBorder(BorderFactory.createLineBorder(blue));
//		
////		add = new JButton("add a new requirement");
//		ok = new JButton("Ok");
//		cancel = new JButton("Cancel");
//		
//		ok.setBackground(blue);
//		ok.setFocusPainted(false);
//	
//		
//		cancel.setBackground(blue);
//		cancel.setFocusPainted(false);
//
//		
//		buttonPanel.add(ok);
//		buttonPanel.add(cancel);
//		
//		center.add(label);
//		center.add(courseNameTF);
//		center.add(requirment1TF);
//		center.add(requirment2TF);
////		center.add(add);
//		center.add(buttonPanel);
//		return center;
//	}
//	
//	
	
	
	
}
