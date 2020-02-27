package GUIPackage;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;




public class View extends JFrame  implements ActionListener{
//	JLabel title,courseDirctor; 
	public JButton loginBN,logoutBN,semesterBN,createClassBN,classListBN,myClassListBN,requestListBN,createCourse,createClassOKBN,createClassCBN;
	public JTextField usernameTF,passwordTF, semesterTF,courseNameTF,requirment1TF,requirment2TF;
	public Color blue = new Color(56, 151, 240);
	public JPanel barPanel, loginPanel, semesterPanel, framePanel, centerPanel, createClassPanel, 
					classListPanel, rootPanel, classDetailPanel, selectTeacherPanel;
	public JTable classListTable, staffListTable;
	public JFrame selectTeacherWindow;
	public JTextArea requirementTA;
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
		this.setSize(800,550);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(50,50);
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
	//clean all registered classDetailPanel
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==main.selectTeacherPage.selectTeacherSubmitBN) {
			main.selectTeacherPage.setSelectedTeacher();
			centerPage.show(centerPanel, "classDetailPanel");
		}
		
	}
	
	
	public void refresh() {
		this.revalidate();
		this.repaint();
	}
	
	
	
	public class Login{
		public void buildLoginPanel() {
			loginPanel = new JPanel();
			loginPanel.setBackground(Color.white);
			loginPanel.setFocusable(true);
//			loginPanel.setBorder(BorderFactory.createEmptyBorder(70,200,90,200));
			loginPanel.setLayout(null);
			
			JLabel title = new JLabel("LOGIN");
			Font f = new Font("Arial",Font.PLAIN,18);
			title.setFont(f);
			title.setBounds(359,122,61,49);
			
			JLabel lblUsername = new JLabel("Username");
			lblUsername.setFont(new Font("Arial", Font.PLAIN, 12));
			lblUsername.setForeground(new Color(114, 114, 114));
			lblUsername.setBounds(288, 176, 61, 16);
			
			usernameTF = new JTextField(16);
//			usernameTF.addFocusListener(new JTextFieldHintListener(usernameTF, " Username"));
			usernameTF.setBounds(288,194,195,41); 
			
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setFont(new Font("Arial", Font.PLAIN, 12));
			lblPassword.setForeground(new Color(114, 114, 114));
			lblPassword.setBounds(288, 240, 61, 16);
			
			passwordTF = new JTextField(16);
//			passwordTF.addFocusListener(new JTextFieldHintListener(passwordTF, " Password"));
//			passwordTF.setBorder(BorderFactory.createLineBorder(blue));
			passwordTF.setBounds(288,258,195,41);
			
			loginBN = buildBlueButton("OK");
			
			loginBN.setBounds(288,320,195,35);
	
			loginPanel.add(title);
			loginPanel.add(lblUsername);
			loginPanel.add(usernameTF);
			loginPanel.add(lblPassword);
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
			usernameTF.addFocusListener(new JTextFieldHintListener(usernameTF, ""));
			passwordTF.addFocusListener(new JTextFieldHintListener(passwordTF, ""));
		}
		
		public int logOutCheck() {
			UIManager.put("Button.background", new Color(20,20,20));
			UIManager.put("Button.FocusPainted",false);
			UIManager.put("Button.foreground", Color.white);
			UIManager.put("Panel.background", Color.white);
			UIManager.put("OptionPane.background", Color.white);
			int n = JOptionPane.showConfirmDialog(null, "Are you sure to log out?", "wanring",JOptionPane.YES_NO_OPTION);
			return n;
		}
		
		public void displayLoginPanel() {
			View.this.page.show(rootPanel, "LoginPage");
			refresh();
		}
	}
	
	public class Semester{
		private int latestSemester = 0;
		public void buildSemesterPanel(int num) {
			semesterPanel = new JPanel(new GridLayout(4,1,0,12));
			semesterPanel.setBackground(Color.white);
			semesterPanel.setFocusable(true);
//			semesterPanel.setBorder(BorderFactory.createEmptyBorder(70,200,90,200));
			semesterPanel.setLayout(null);
			
//			JLabel title = new JLabel("Select a semester to access.", SwingConstants.CENTER);
			JLabel title = new JLabel("Select a semester");
			Font f = new Font("Arial",Font.PLAIN,18);
			title.setFont(f);
			title.setBounds(315,122,250,60);
			
			semesterTF = new JTextField(4);
	
//			semesterTF.setBorder(BorderFactory.createLineBorder(blue)); 
			semesterTF.setBounds(293,183,195,41);

			
			semesterBN = buildBlueButton("Select");
			semesterBN.setBounds(293,250,195,35);
	
			semesterPanel.add(title);
			semesterPanel.add(semesterTF);
			semesterPanel.add(semesterBN);
			latestSemester =num;
			View.this.rootPanel.add(semesterPanel,"SemesterPage");

		}
		
		public void displayLatestSemester() {
			
			semesterTF.addFocusListener(new JTextFieldHintListener(semesterTF, ""+latestSemester));
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
		String defaultClassName = "  Please enter a class name, max length 20.";
		String defaultClassRequirement = "  Please enter a requirement, max length 200 words.";
		public CourseDetailPage courseDetailPage;
		public SelectTeacherPage selectTeacherPage;
	
		Main(){
			centerPage =new CardLayout();
			centerPanel = new JPanel(centerPage);
			centerPanel.setBackground(Color.white);
			courseDetailPage = new CourseDetailPage();
			selectTeacherPage = new SelectTeacherPage();
		}
		
		
		public void buildCreateClassPanel() {
			JPanel centerP = new JPanel(null);
			centerP.setBorder(BorderFactory.createEmptyBorder(20,180,100,180));
			JPanel buttonPanel = new JPanel(new FlowLayout());
			JPanel textAreaPanel = new JPanel(new BorderLayout());
			centerP.setBackground(Color.white);
			buttonPanel.setBackground(Color.white);
			textAreaPanel.setBackground(Color.white);
			
			JLabel createClassTitleL = new JLabel("Create a new class", SwingConstants.CENTER);
			createClassTitleL.setFont(new Font("Arial", Font.PLAIN, 23));
			createClassTitleL.setBounds(200, 30, 200, 62);
			centerP.add(createClassTitleL);
			
			
			textAreaPanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
			JLabel classNameL = new JLabel("Class name:     ");
			classNameL.setForeground(new Color(114, 114, 114));
			classNameL.setFont(new Font("Arial", Font.PLAIN, 14));
			classNameL.setBounds(148, 104, 112, 21);
			centerP.add(classNameL);
			JLabel reqLTitle1 = new JLabel("Requirement:  ");
			
			reqLTitle1.setForeground(new Color(114, 114, 114));
			reqLTitle1.setFont(new Font("Arial", Font.PLAIN, 14));
			reqLTitle1.setBounds(148, 184, 112, 21);
			centerP.add(reqLTitle1);
			
			
			
			courseNameTF = new JTextField();
		
			courseNameTF.setFont(new Font("Arial", Font.PLAIN, 12));
			courseNameTF.setForeground(new Color(20,20,20));
			courseNameTF.setBounds(148, 125, 319, 36);

			courseNameTF.setColumns(10);
//			courseNameTF.addFocusListener(new JTextFieldHintListener(courseNameTF, defaultClassName));
			
			centerP.add(courseNameTF);
			
			
			
			
			int TA_row = 10;
			int TA_col = 30;
			
			requirementTA= new JTextArea(TA_row, TA_col);
			requirementTA.setFont(new Font("Arial", Font.PLAIN, 12));
			requirementTA.setMaximumSize(new Dimension(5, 30));
			requirementTA.setForeground(new Color(20,20,20));

			requirementTA.setWrapStyleWord(true);
			requirementTA.setLineWrap(true);
			
//			requirementTA.addFocusListener(new JTextFieldHintListener(requirementTA, defaultClassRequirement));
			
			JScrollPane reqScrollPanel = new JScrollPane( requirementTA );
			reqScrollPanel.setBounds(148, 208, 319, 160);


			reqScrollPanel.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
			
			centerP.add(reqScrollPanel);
			
			
			createClassOKBN = buildBlueButton("Ok");
			createClassOKBN.setBounds(395, 391, 70, 33);
			
			centerP.add(createClassOKBN);
			
			
			
			createClassCBN = buildBlackButton("Cancel");
			createClassCBN.setBounds(148, 391, 80, 33);
			
			centerP.add(createClassCBN);
			
			createClassPanel = centerP;
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
			if(requirementTA.getText().equals(" ")|| requirementTA.getText().equals("")) {
				return null;
			}
			req += requirementTA.getText();
			
			s += encodeString(req);
	
			return s;
		}
		
		public void cleanCreateClassText() {
			courseNameTF.addFocusListener(new JTextFieldHintListener(courseNameTF, defaultClassName));
			requirementTA.addFocusListener(new JTextFieldHintListener(requirementTA, defaultClassRequirement));

		}
		
	
	
		
		
		
		public void buildClassListPanel(String[] header, String[][] list) {
			classListPanel = new JPanel(new BorderLayout());
			classListPanel.setBorder(new EmptyBorder(50, 20, 5, 20));
			classListPanel.setBackground(Color.white);
			JLabel courseListTitleL = new JLabel("Course list");
			courseListTitleL.setFont(new Font("Arial",Font.PLAIN,18));
			classListPanel.add(courseListTitleL, BorderLayout.NORTH);
			classListTable = buildModelListTable(header, list);
			
			enableTableHoverEffect(classListTable);
			JScrollPane sp=new JScrollPane();
			sp.setBorder(new EmptyBorder(40, 0, 0, 0));
	        sp.setViewportView(classListTable);
	        sp.getViewport().setBackground(Color.WHITE);
	        sp.setBackground(Color.white);
			
			classListPanel.add(sp, BorderLayout.CENTER);
			classListPanel.setVisible(true);
			centerPanel.add(classListPanel, "classListPanel");
		}
		
		
		
		public class ForcedListSelectionModel extends DefaultListSelectionModel {

		    public ForcedListSelectionModel () {
		        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		    }

		    @Override
		    public void clearSelection() {
		    }

		    @Override
		    public void removeSelectionInterval(int index0, int index1) {
		    }

		}
		
		
		public void displayClassListPanel(String[] header, String[][] list) {	
			
			
			
			
			TableModel m = new DefaultTableModel(list, header) ;
			View.this.classListTable.setModel(m);
			centerPage.show(centerPanel, "classListPanel");
			refresh();
		}
		
		
		public class CourseDetailPage {
			JLabel classNameL;
			JLabel classIDTitleL;
			JLabel directorNameTitleL;
			JLabel directorIDTitleL;
			JLabel classIDL;
			JLabel directorNameL;
			JLabel directorIDL;
			JLabel requirementTitleL;
			JLabel staffTitleL;
			JLabel staffIDL;
			JLabel staffNameL;
			JLabel statusTitleL;
			JLabel statusL;
			JLabel trainingTitleL;
			JTextArea requirementTA;
			JTextArea trainingTA;
			public JButton courseDetailWBN;
			public JButton courseDetailCBN;
			public JButton courseDetailSBN;
			public JButton normalPageBBN;
			public JButton assignTeacherBN;
			CardLayout submitButtonsLayout = new CardLayout();
			JPanel submitButtonsPanel = new JPanel(submitButtonsLayout);
			Color titleFontColor = new Color(114,114,114);
			private int statusIndex = -1;
			private List<JLabel> labelList = new ArrayList<JLabel>();
			List<JTextArea> TAList = new ArrayList<JTextArea>();
			
			public String[] getClassInform() {
				String[]s = new String[3];
				s[0] = classIDL.getText();
				
				return s;
			}
			
		
			
			public void buildClassDetailPanel() {
				classDetailPanel = new JPanel();
				classDetailPanel.setBackground(Color.WHITE);

				classDetailPanel.setLayout(null);
				
				classNameL = new JLabel("");
				classNameL.setFont(new Font("Arial", Font.PLAIN, 20 ));
				classNameL.setBounds(65, 30, 370, 62);
				classDetailPanel.add(classNameL);
				labelList.add(classNameL);
			
				classIDTitleL = new JLabel("Course ID");
				classIDTitleL.setForeground(titleFontColor);
				classIDTitleL.setFont(new Font("Arial", Font.PLAIN, 14));
				classIDTitleL.setBounds(65, 92, 91, 29);
				classDetailPanel.add(classIDTitleL);
				
				
				
				directorIDTitleL = new JLabel("CDID");
				directorIDTitleL.setForeground(titleFontColor);
				directorIDTitleL.setFont(new Font("Arial", Font.PLAIN, 14));
				directorIDTitleL.setBounds(184, 91, 53, 29);
				classDetailPanel.add(directorIDTitleL);
				
				directorNameTitleL = new JLabel("Director");
				directorNameTitleL.setForeground(titleFontColor);
				directorNameTitleL.setFont(new Font("Arial", Font.PLAIN, 14));
				directorNameTitleL.setBounds(293, 90, 116, 29);
				classDetailPanel.add(directorNameTitleL);

				
				classIDL= new JLabel();
				classIDL.setForeground(Color.BLACK);
				classIDL.setFont(new Font("Arial", Font.PLAIN, 12));
				classIDL.setBounds(65, 112, 53, 29);
				classDetailPanel.add(classIDL);
				labelList.add(classIDL);
				
				
				
				directorIDL = new JLabel();
				directorIDL.setForeground(Color.BLACK);
				directorIDL.setFont(new Font("Arial", Font.PLAIN, 14));
				directorIDL.setBounds(184, 112, 97, 29);
				classDetailPanel.add(directorIDL);
				labelList.add(directorIDL);
				
				
				directorNameL = new JLabel();
				directorNameL.setForeground(Color.BLACK);
				directorNameL.setFont(new Font("Arial", Font.PLAIN, 14));
				directorNameL.setBounds(292, 111, 82, 29);
				classDetailPanel.add(directorNameL);
				labelList.add(directorNameL);
				
				
				statusTitleL = new JLabel("Status: ");
				statusTitleL .setBounds(65, 284, 82, 29);
				statusTitleL.setForeground(titleFontColor);
				statusTitleL.setFont(new Font("Arial", Font.PLAIN, 14));
				classDetailPanel.add(statusTitleL);
				
				
				
				
				statusL = new JLabel("Teacher has not been assigned.");
				statusL .setBounds(115, 288, 400, 21);
				statusL.setForeground(Color.BLACK);
				statusL.setFont(new Font("Arial", Font.PLAIN, 14));
				classDetailPanel.add(statusL);
				labelList.add(statusL);
				
				requirementTitleL = new JLabel("Requirement");
				requirementTitleL.setForeground(titleFontColor);
				requirementTitleL.setFont(new Font("Arial", Font.PLAIN, 14));
				requirementTitleL.setBounds(65, 145, 89, 21);
				classDetailPanel.add(requirementTitleL);
				
				
				JPanel operateP = new JPanel();
				operateP.setBorder(new LineBorder(Color.LIGHT_GRAY));
				operateP.setBackground(Color.WHITE);
				operateP.setBounds(65, 312, 495, 160);
				classDetailPanel.add(operateP);
				operateP.setLayout(null);
				
				staffTitleL = new JLabel("Teacher");
				staffTitleL.setBounds(16, 13, 76, 14);
				staffTitleL.setForeground(titleFontColor);
				staffTitleL.setFont(new Font("Arial", Font.PLAIN, 14));
				operateP.add(staffTitleL);
				
				staffIDL = new JLabel();
				staffIDL.setForeground(Color.BLACK);
				staffIDL.setFont(new Font("Arial", Font.PLAIN, 14));
				staffIDL.setBounds(16, 36, 97, 21);
				operateP.add(staffIDL);
				labelList.add(staffIDL);
				
				staffNameL = new JLabel();
				staffNameL.setForeground(Color.BLACK);
				staffNameL.setFont(new Font("Arial", Font.PLAIN, 14));
				staffNameL.setBounds(16, 58, 124, 21);
				operateP.add(staffNameL);
				labelList.add(staffNameL);
				
				
				
				trainingTitleL = new JLabel("Organized training");
				trainingTitleL.setForeground(titleFontColor);
				trainingTitleL.setFont(new Font("Arial", Font.PLAIN, 14));
				trainingTitleL.setBounds(158, 11, 147, 18);
				operateP.add(trainingTitleL);
				
				
				JScrollPane requirementSP = new JScrollPane();
				requirementSP.setBounds(65, 170, 495, 106);
				classDetailPanel.add(requirementSP);
				
				requirementTA = new JTextArea();
				requirementTA.setFont(new Font("Arial", Font.PLAIN, 14));
				requirementTA.setEditable(false);
				requirementTA.setWrapStyleWord(true);
				requirementTA.setLineWrap(true);
				requirementTA.setText("");
				requirementSP.setViewportView(requirementTA);
				TAList.add(requirementTA);
				
				JScrollPane trainingSP = new JScrollPane();
				trainingSP.setToolTipText("");
				trainingSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				trainingSP.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				trainingSP.setBounds(158, 36, 319, 70);
				operateP.add(trainingSP);
				trainingTA  = new JTextArea();
				trainingTA.setWrapStyleWord(true);
				trainingTA.setLineWrap(true);
				trainingTA.setEditable(false);
				trainingTA.setText("");
				trainingSP.setViewportView(trainingTA);
				TAList.add(trainingTA);
				
				
				
				GridLayout submitBNLayout = new GridLayout (1,2,8,1);
				submitButtonsPanel.setBounds(20, 113, 470, 28);
				submitButtonsPanel.setBackground(Color.white);

				
				courseDetailWBN = buildBlackButton("");
				courseDetailCBN = buildBlackButton("");						
				courseDetailSBN = buildBlueButton("");
				
						
				
				JPanel subBP = new JPanel (null);
				subBP.setBackground(Color.white);
				GridLayout innerRightBPLayout = new GridLayout (1,2,8,1);
				JPanel innerRightBP = new JPanel (innerRightBPLayout);
				innerRightBP.setBackground(Color.white);
				innerRightBP.setBounds(285, 0, 170, 28);
				innerRightBP.setBackground(Color.white);
				innerRightBP.add(courseDetailCBN);
				innerRightBP.add(courseDetailSBN);
				
				JPanel innerLeftBP = new JPanel (new GridLayout (1,2,3,1));
				innerLeftBP.setBounds(0, 0, 70, 28);
				innerLeftBP.setBackground(Color.white);
				innerLeftBP.add(courseDetailWBN);
				subBP.add(innerLeftBP);
				subBP.add(innerRightBP);
				
				submitButtonsPanel.add(subBP, "activeMode");
				
				
				

				JPanel emptyP = new JPanel (null);
				emptyP.setBackground(Color.white);
				normalPageBBN = buildBlackButton("Back");	
				normalPageBBN.setBounds(385, 0, 70, 28);
				emptyP.setBackground(Color.white);
				emptyP.add(normalPageBBN);
				submitButtonsPanel.add(emptyP, "normalMode");
	
				operateP.add(submitButtonsPanel);
				
				assignTeacherBN = buildBlackButton("Assign");
				assignTeacherBN.setBounds(75, 13, 40, 16);
				assignTeacherBN.setFont(new Font("Arial", Font.BOLD, 8));
				assignTeacherBN.setVisible(false);
				assignTeacherBN.setEnabled(false);
				assignTeacherBN.addActionListener(View.this);
				operateP.add(assignTeacherBN);
				
			
				
				centerPanel.add(classDetailPanel, "classDetailPanel");
			}
			
			
			public void buildSubmittedBNPanel(JButton withdrawBN, JButton cancelBN, JButton okBN, String key) {
				JButton[] tem  = new JButton [3];
				JPanel subBP = new JPanel (null);
				subBP.setBackground(Color.white);
				GridLayout innerRightBPLayout = new GridLayout (1,2,8,1);
				JPanel innerRightBP = new JPanel (innerRightBPLayout);
				innerRightBP.setBackground(Color.white);
				cancelBN = buildBlackButton("Cancel");		
				okBN = buildBlackButton("Submit");
				innerRightBP.setBounds(285, 0, 170, 28);
				innerRightBP.setBackground(Color.white);
				innerRightBP.add(cancelBN);
				innerRightBP.add(okBN);
				
				JPanel innerLeftBP = new JPanel (new GridLayout (1,2,3,1));
				withdrawBN = buildBlackButton("Withdraw");
				innerLeftBP.setBounds(0, 0, 70, 28);
				innerLeftBP.add(withdrawBN);
				innerLeftBP.setBackground(Color.white);
				subBP.add(innerLeftBP);
				subBP.add(innerRightBP);
				
				submitButtonsPanel.add(subBP, key);
			}
			
			
			public int withdrawCheck() {
				UIManager.put("Button.background", new Color(20,20,20));
				UIManager.put("Button.foreground", Color.white);
				UIManager.put("Panel.background", Color.white);
				UIManager.put("OptionPane.background", Color.white);
				int n = JOptionPane.showConfirmDialog(null, "Are you sure to withdraw?", "wanring",JOptionPane.YES_NO_OPTION);
				return n;
			}
			
			
			
			
			
			
			public String[] getAssignTeacher() {
				String [] s = new String [3];
				s[0] =	classIDL.getText();
				s[1] = staffIDL.getText();
				s[2] = trainingTA.getText();			
				return s;
			}
			
			

			public void updateData(String[] data) {
				centerPage.show(centerPanel, "classDetailPanel");
				int i = 1;	//start with class id #2 element
				String [] refinedData = new String[data.length];
				
				
				refinedData[0] = data[0]; //semester
				refinedData[1] = data[2]; //classname
				refinedData[2] = data[1]; //classid
				refinedData[3] = data[5]; //directorid
				refinedData[4] = data[6]; //directorname
				refinedData[5] = data[4]; //status
				refinedData[6] = data[7]; //teacherid
				refinedData[7] = data[8]; //teachername
				refinedData[8] = data[3]; //requirement
				refinedData[9] = data[9]; //training				
				
				if(refinedData[5].equals("Pending")) {
					statusIndex = 1;
					refinedData[5] = "Teacher is not assigned.";
				}
				else if(refinedData[5].equals("Assigned")) {
					statusIndex = 2;
					refinedData[5] = "Waiting for Class Director to submit teaching request.";
				}
				else if(refinedData[5].equals("Submitted")) {
					statusIndex = 3;
					refinedData[5] = "Waiting for PTT Director to approve teaching request.";
				}
				else if(refinedData[5].equals("Approved")) {
					statusIndex = 4;
					refinedData[5] = "Teaching request had been approved";
				}else {
					refinedData[5] = "Error" + refinedData[5];
				}
				
				
				System.out.println();
				
				for(JLabel l : labelList) {
					if(i<refinedData.length) {
						System.out.print(refinedData[i]);
						l.setText(refinedData[i]);
						i++;
					}
				}
				for(JTextArea l : TAList) {
					System.out.print(l.getName());
					if(i<refinedData.length) {
						System.out.print(refinedData[i]);
						System.out.print(l.getName());
						l.setText(refinedData[i]);
						i++;
					}
				}

				View.this.refresh();
			}
			
			
			
			public void displayDCMode(String[] data) {
				updateData(data);
				courseDetailWBN.setText("Withdraw");
				courseDetailCBN.setText("Cancel");				
				courseDetailSBN.setText("Submit");


				if(View.this.main.courseDetailPage.statusIndex == 1) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "normalMode");
				}else if(View.this.main.courseDetailPage.statusIndex == 2) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "activeMode");

				}else if(View.this.main.courseDetailPage.statusIndex == 3) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "normalMode");
				}else if(View.this.main.courseDetailPage.statusIndex == 4) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "normalMode");
				}
				centerRefresh();
			}
			
			public void displayAdminsMode(String[] data) {
				updateData(data);
				courseDetailCBN.setText("Cancel");				
				courseDetailSBN.setText("Submit");
				courseDetailWBN.setVisible(false);
				if(View.this.main.courseDetailPage.statusIndex == 1) {	
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "activeMode");		
					trainingTA.setEditable(true);
					View.this.main.courseDetailPage.assignTeacherBN.setVisible(true);
					View.this.main.courseDetailPage.assignTeacherBN.setEnabled(true);
				}else if(View.this.main.courseDetailPage.statusIndex == 2) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "normalMode");
					trainingTA.setEditable(false);
					View.this.main.courseDetailPage.assignTeacherBN.setVisible(false);
					View.this.main.courseDetailPage.assignTeacherBN.setEnabled(false);
				}else if(View.this.main.courseDetailPage.statusIndex == 3) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "normalMode");	
					trainingTA.setEditable(false);
					View.this.main.courseDetailPage.assignTeacherBN.setVisible(false);
					View.this.main.courseDetailPage.assignTeacherBN.setEnabled(false);
				}else if(View.this.main.courseDetailPage.statusIndex == 4) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "normalMode");
					trainingTA.setEditable(false);
					View.this.main.courseDetailPage.assignTeacherBN.setVisible(false);
					View.this.main.courseDetailPage.assignTeacherBN.setEnabled(false);
				}
				refresh();
			}
			
		
			
			public void displayPDMode(String[] data) {
				updateData(data);
				courseDetailWBN.setText("Withdraw");
				courseDetailCBN.setText("Cancel");				
				courseDetailSBN.setText("Submit");
				courseDetailWBN.setVisible(true);
				System.out.println("hhhhhhhhhhh"+courseDetailWBN.isVisible());
				if(View.this.main.courseDetailPage.statusIndex == 1) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "normalMode");
				}else if(View.this.main.courseDetailPage.statusIndex == 2) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "normalMode");
				}else if(View.this.main.courseDetailPage.statusIndex == 3) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "activeMode");
				}else if(View.this.main.courseDetailPage.statusIndex == 4) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "normalMode");
				}
				refresh();
			}
			public void displayNormalMode(String[] data) {
				updateData(data);
				
				if(View.this.main.courseDetailPage.statusIndex == 1) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}else if(View.this.main.courseDetailPage.statusIndex == 2) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}else if(View.this.main.courseDetailPage.statusIndex == 3) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}else if(View.this.main.courseDetailPage.statusIndex == 4) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}
				refresh();
			}
			
			
		}
		public class  SelectTeacherPage{
			String staffID = "";
			String staffName = "";
			public JButton selectTeacherSubmitBN;
			JLabel semesterlabel ;
			public void buildSelectTeacherPanel() {

				selectTeacherPanel = new JPanel();
				selectTeacherPanel.setBackground(Color.white);
				selectTeacherPanel.setLayout(new BorderLayout());
				selectTeacherPanel.setBorder(new EmptyBorder(30, 50, 0, 50));
				JLabel StaffListTitleL = new JLabel("Staff List");
				StaffListTitleL.setFont(new Font("Arial", Font.PLAIN, 18));
				JPanel titleP= new JPanel();
				titleP.add(StaffListTitleL);

				selectTeacherPanel.add(titleP, BorderLayout.NORTH);
				titleP.setBackground(Color.white);
				JPanel ListP = new JPanel(new BorderLayout());

				ListP.setBackground(Color.white);
				semesterlabel = new JLabel("Semester: ");
				semesterlabel.setFont(new Font("Arial", Font.PLAIN, 12));

				ListP.add(semesterlabel, BorderLayout.NORTH);
				
				JScrollPane scrollPane = new JScrollPane();

				
				staffListTable = buildModelListTable(null, null);	
				
				staffListTable.addMouseListener(new java.awt.event.MouseAdapter() {
					@Override
					public void mouseClicked(java.awt.event.MouseEvent evt) {
						int selectedRow = staffListTable.rowAtPoint(evt.getPoint());
						if (selectedRow >= 0&& selectedRow < staffListTable.getRowCount()) {
						staffID = staffListTable.getValueAt(selectedRow, 0).toString();
						staffName = staffListTable.getValueAt(selectedRow, 2).toString();
						}
					}
				});

				scrollPane.setViewportView(staffListTable);			
				ListP.add(scrollPane, BorderLayout.CENTER);
				JPanel buttonP = new JPanel(new GridLayout(1,1));
				buttonP.setPreferredSize(new Dimension(50, 80));
				buttonP.setBackground(Color.white);
				JPanel subbuttonP = new JPanel(null);
				selectTeacherSubmitBN = buildBlueButton("Select");
				selectTeacherSubmitBN.addActionListener(View.this);
				selectTeacherSubmitBN.setBounds(240, 10, 60, 30);
				subbuttonP.setBorder(new EmptyBorder(30, 180, 0, 180));
				subbuttonP.setBackground(Color.white);
				subbuttonP.add(selectTeacherSubmitBN);
				buttonP.add(subbuttonP);
				ListP.add(buttonP, BorderLayout.SOUTH);
				selectTeacherPanel.add(ListP, BorderLayout.CENTER);
				centerPanel.add(selectTeacherPanel, "selectTeacherPanel");
			}
			
			public void displaySelectTeacherPanel(String []staffTableHeader,String [][] staffList, String semester) {
				TableModel m = new DefaultTableModel(staffList, staffTableHeader) ;
				View.this.staffListTable.setModel(m);
				semesterlabel.setText("Semester: "+semester);
				centerPage.show(centerPanel, "selectTeacherPanel");
				refresh();
			}
			
			public void setSelectedTeacher() {

				View.this.main.courseDetailPage.staffIDL.setText(staffID);
				View.this.main.courseDetailPage.staffNameL.setText(staffName);
				refresh();
			}
			
			
				
			
		}
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
		
	public JTable buildModelListTable(String[] header, String [][] list) {	
		if(header==null || list == null) {
			String [] tem  =  {""};
			String [][] tem2  =  {{""}};
			header= tem;
			list =tem2;
		}
		
		JTable modelTable = new JTable(list, header){
			public boolean isCellEditable(int row, int column) {                
		           return false;    
			}
		};
			     
		modelTable.setBackground(Color.WHITE);
		modelTable.getTableHeader().setBackground(Color.WHITE);
		modelTable.getTableHeader().setReorderingAllowed(false);
		modelTable.setFillsViewportHeight(true);
		return modelTable;		
	}
	
	
	
	public void enableTableHoverEffect(JTable modelTable) {
		
		modelTable.addMouseMotionListener(new MouseMotionListener() {
			int hoveredRow = -1, hoveredColumn = -1;
			@Override
			public void mouseMoved(MouseEvent e) {
				java.awt.Point p = e.getPoint();
			    hoveredRow = modelTable.rowAtPoint(p);
			    hoveredColumn = modelTable.columnAtPoint(p);
			    if(hoveredRow<0||hoveredRow>modelTable.getRowCount()) {}
			    	else {
			    		modelTable.setRowSelectionInterval(hoveredRow, hoveredRow);
			    		modelTable.repaint();  
			        }
			     }
			@Override
			public void mouseDragged(MouseEvent e) {
				hoveredRow = hoveredColumn = -1;
			    modelTable.repaint();
			}
		});
	}
	
	
	
		
	// Course Director 
	public class Bar {
		public void addSelfListener() {
			classListBN.addActionListener(View.this);
		}
		public void buildCDBar(String ID, String name) {

			JPanel westPanel = new JPanel(new BorderLayout());
			westPanel.setPreferredSize(new Dimension(180, 550));
			JPanel northPanel = new JPanel(new GridLayout(2,1,8,8));
			JPanel list = new JPanel(new GridLayout(9,1,0,5));
			
			northPanel.setBorder(BorderFactory.createEmptyBorder(50,30,30,20));
			list.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));
			northPanel.setBackground(blue);
			JLabel titleL = new JLabel("Course Dirctor");
			JLabel nameL = new JLabel("Hi, "+name);
			list.setBackground(blue);
			list.setAlignmentX(Component.LEFT_ALIGNMENT);			
			titleL.setFont(new Font("Arial",Font.BOLD,15));
			nameL.setFont(new Font("Arial",Font.BOLD,15));
			titleL.setForeground(Color.WHITE);
			nameL.setForeground(Color.WHITE);
			
			northPanel.add(nameL);
			northPanel.add(titleL);
			westPanel.add(northPanel, BorderLayout.NORTH);
			
			List<JButton> listPS = new LinkedList<JButton>();
			createClassBN = buildBlueBorderlessButton("         Course +");
			myClassListBN = buildBlueBorderlessButton("         My class list");
			classListBN = buildBlueBorderlessButton("         Class list");
			requestListBN = buildBlueBorderlessButton("         Request list");
			logoutBN = buildBlueBorderlessButton("         Log out");
			listPS.add(createClassBN);
			listPS.add(myClassListBN);
			listPS.add(classListBN);
			listPS.add(requestListBN);
			listPS.add(logoutBN);
			for(JButton bn : listPS) {
				bn.setBorder(BorderFactory.createEmptyBorder(8,0,10,0));
				bn.setHorizontalAlignment(SwingConstants.LEFT);
				list.add(bn);
			}
			
			
			westPanel.add(list,BorderLayout.CENTER);
			addSelfListener();
			barPanel = westPanel;
		}
		
		public void buildABar(String ID, String name) {

			JPanel westPanel = new JPanel(new BorderLayout());
			westPanel.setPreferredSize(new Dimension(180, 550));
			JPanel northPanel = new JPanel(new GridLayout(2,1,8,8));
			JPanel list = new JPanel(new GridLayout(9,1,0,5));
			
			northPanel.setBorder(BorderFactory.createEmptyBorder(50,30,30,20));
			list.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));
			northPanel.setBackground(blue);
			JLabel titleL = new JLabel("Administrator");
			JLabel nameL = new JLabel("Hi, "+name);
			list.setBackground(blue);
			list.setAlignmentX(Component.LEFT_ALIGNMENT);			
			titleL.setFont(new Font("Arial",Font.BOLD,15));
			nameL.setFont(new Font("Arial",Font.BOLD,15));
			titleL.setForeground(Color.WHITE);
			nameL.setForeground(Color.WHITE);
			
			northPanel.add(nameL);
			northPanel.add(titleL);
			westPanel.add(northPanel, BorderLayout.NORTH);
			
			List<JButton> listPS = new LinkedList<JButton>();
			classListBN = buildBlueBorderlessButton("         Class list");
			requestListBN = buildBlueBorderlessButton("         Request list");
			logoutBN = buildBlueBorderlessButton("         Log out");
			listPS.add(classListBN);
			listPS.add(requestListBN);
			listPS.add(logoutBN);
			for(JButton bn : listPS) {
				bn.setBorder(BorderFactory.createEmptyBorder(8,0,10,0));
				bn.setHorizontalAlignment(SwingConstants.LEFT);
				list.add(bn);
			}
			
			
			westPanel.add(list,BorderLayout.CENTER);
			addSelfListener();
			barPanel = westPanel;
		}
		
		
		public void buildPDBar(String ID, String name) {

			JPanel westPanel = new JPanel(new BorderLayout());
			westPanel.setPreferredSize(new Dimension(180, 550));
			JPanel northPanel = new JPanel(new GridLayout(2,1,8,8));
			JPanel list = new JPanel(new GridLayout(9,1,0,5));
			
			northPanel.setBorder(BorderFactory.createEmptyBorder(50,30,30,20));
			list.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));
			northPanel.setBackground(blue);
			JLabel titleL = new JLabel("PTT Dirctor");
			JLabel nameL = new JLabel("Hi, "+name);
			list.setBackground(blue);
			list.setAlignmentX(Component.LEFT_ALIGNMENT);			
			titleL.setFont(new Font("Arial",Font.BOLD,15));
			nameL.setFont(new Font("Arial",Font.BOLD,15));
			titleL.setForeground(Color.WHITE);
			nameL.setForeground(Color.WHITE);
			
			northPanel.add(nameL);
			northPanel.add(titleL);
			westPanel.add(northPanel, BorderLayout.NORTH);
			
			List<JButton> listPS = new LinkedList<JButton>();
			classListBN = buildBlueBorderlessButton("         Class list");
			requestListBN = buildBlueBorderlessButton("         Request list");
			logoutBN = buildBlueBorderlessButton("         Log out");
			listPS.add(classListBN);
			listPS.add(requestListBN);
			listPS.add(logoutBN);
			for(JButton bn : listPS) {
				bn.setBorder(BorderFactory.createEmptyBorder(8,0,10,0));
				bn.setHorizontalAlignment(SwingConstants.LEFT);
				list.add(bn);
			}
			
			
			westPanel.add(list,BorderLayout.CENTER);
			addSelfListener();
			barPanel = westPanel;
		}
	}
	
	
	private JButton buildBlackButton(String name) {
		JButton btn = new JButton(name);
		Color darkGrey = new Color(30,30,30);
		Color lightGrey = new Color(150,150,150);
		btn.setBackground(darkGrey);
		btn.setBorder(BorderFactory.createEmptyBorder());
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("Arial", Font.PLAIN, 12));
		btn.setFocusPainted(false);
		btn.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btn.setBackground(Color.white);
		    	btn.setForeground(darkGrey);
		    	btn.setBorder(BorderFactory.createLineBorder(darkGrey, 1));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btn.setBackground(darkGrey);
		    	btn.setForeground(Color.white);
		    	btn.setBorder(BorderFactory.createEmptyBorder());
		    }
		});

		return btn;
	}
	private JButton buildBlueButton(String name) {
		JButton btn = new JButton(name);
		
		btn.setBackground(new Color(56, 151, 240));
		btn.setForeground(Color.white);
		btn.setBorder(BorderFactory.createEmptyBorder());
		btn.setFont(new Font("Arial", Font.PLAIN, 12));
		btn.setFocusPainted(false);
		btn.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btn.setBackground(Color.white);
		    	btn.setForeground(blue);
		    	btn.setBorder(BorderFactory.createLineBorder(blue, 1));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btn.setBackground(blue);
		    	btn.setForeground(Color.white);
		    	btn.setBorder(BorderFactory.createEmptyBorder());
		    }
		});

		return btn;
	}
	
	private JButton buildBlueBorderlessButton(String name) {
		JButton btn = new JButton(name);
		
		btn.setBackground(new Color(56, 151, 240));
		btn.setForeground(Color.white);
		btn.setBorder(BorderFactory.createEmptyBorder());
		btn.setFocusPainted(false);
		btn.setFont(new Font("Arial", Font.PLAIN, 12));
    	btn.setBorder(BorderFactory.createEmptyBorder());
		btn.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btn.setBackground(Color.white);
		    	btn.setForeground(blue);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btn.setBackground(blue);
		    	btn.setForeground(Color.white);
		    }
		});

		return btn;
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
