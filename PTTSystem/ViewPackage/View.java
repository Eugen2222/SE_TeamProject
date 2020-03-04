package ViewPackage;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.table.TableRowSorter;

import javax.swing.RowFilter;

import javax.swing.SortOrder;
import javax.swing.RowSorter;

import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.ComboPopup;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.util.HashMap;



public class View extends JFrame  implements ActionListener{
//	JLabel title,courseDirctor; 
	public JButton loginBN,logoutBN,semesterBN,createClassBN,classListBN,myClassListBN,requestListBN,createCourse,createClassOKBN,createClassCBN;
	
	public JTextField usernameTF,passwordTF, semesterTF,courseNameTF,requirment1TF,requirment2TF;
	public Color themeGrey = new Color(30, 40, 80);
	public Color red = new Color(238, 73, 87);
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
		this.setResizable(false);
		try {
		    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		 } catch (Exception e) {
		            e.printStackTrace();
		 }
		this.getContentPane().removeAll();

		this.setTitle("PTT Manage System");
		this.setSize(800,550);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(240,80);
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
			lblUsername.setForeground(new Color(30, 45, 85));
			lblUsername.setBounds(288, 176, 61, 16);
			
			usernameTF = new JTextField(16);
//			usernameTF.addFocusListener(new JTextFieldHintListener(usernameTF, " Username"));
			usernameTF.setBounds(288,194,195,41); 
			usernameTF.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			
			usernameTF.setForeground(new Color(30, 45, 85));
			JLabel lblPassword = new JLabel("Password");
			lblPassword.setFont(new Font("Arial", Font.PLAIN, 12));
			lblPassword.setForeground(new Color(30, 45, 85));
			lblPassword.setBounds(288, 240, 61, 16);
			
			passwordTF = new JTextField(16);
//			passwordTF.addFocusListener(new JTextFieldHintListener(passwordTF, " Password"));
			passwordTF.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			passwordTF.setBounds(288,258,195,41);
			passwordTF.setForeground(new Color(30, 45, 85));
			loginBN = buildThemeButton("OK");
			
			loginBN.setBounds(288,320,195,35);
	
			loginPanel.add(title);
			loginPanel.add(lblUsername);
			loginPanel.add(usernameTF);
			loginPanel.add(lblPassword);
			loginPanel.add(passwordTF);
			loginPanel.add(loginBN);
			View.this.rootPanel.add(loginPanel,"LoginPage");
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
			UIManager.put("Button.background", new Color(150,150,150));
			UIManager.put("Button.FocusPainted",false);
			UIManager.put("Button.foreground", Color.white);
			UIManager.put("Panel.background", Color.white);
			UIManager.put("OptionPane.background", Color.white);
			JOptionPane.showMessageDialog(null, "Unauthorised user!\nPlease re-enter!", 
					"System info", JOptionPane.ERROR_MESSAGE); 
		}
		
		
		
		//get users'selection from the dialog box to check if they want to log out
		public int logOutCheck() {
			UIManager.put("Button.background", themeGrey);
			UIManager.put("Button.FocusPainted",false);
			UIManager.put("Button.foreground", Color.white);
			UIManager.put("Panel.background", Color.white);
			UIManager.put("OptionPane.background", Color.white);
			int n = JOptionPane.showConfirmDialog(null, "Are you sure to log out?", "wanring",JOptionPane.YES_NO_OPTION);
			usernameTF.addFocusListener(new JTextFieldHintListener(usernameTF, ""));
			passwordTF.addFocusListener(new JTextFieldHintListener(passwordTF, ""));
			return n;
		}
		
		public void displayLoginPanel() {
			View.this.page.show(rootPanel, "LoginPage");
			refresh();
		}
	}
	
	
	
	// for users to choose a specific semester 
	public class Semester{
		private int latestSemester = 0;
		public JComboBox semesterBox;

		public void buildSemesterPanel(int [] num) {
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
			String [] semesterList = new String[num.length];
			for(int i =0; i< num.length ; i++) {
				semesterList[i] = Integer.toString(num[i]);			
			}
			DefaultComboBoxModel semesterListModel = new DefaultComboBoxModel(semesterList)  ;
			semesterBox = themeStylishComboBox();
			semesterBox.setModel(semesterListModel);
			semesterBox.setSelectedIndex(semesterList.length-1);
//			semesterTF.setBorder(BorderFactory.createLineBorder(themeGrey)); 
			semesterBox.setBounds(293,200,195,30);

			
			semesterBN = buildThemeButton("Select");
			semesterBN.setBounds(293,250,195,35);
	
			semesterPanel.add(title);
			semesterPanel.add(semesterBox);
			semesterPanel.add(semesterBN);
//			latestSemester =num;
			View.this.rootPanel.add(semesterPanel,"SemesterPage");

		}
		
		
	
		
		
		public int getSelecetedSemester() {  
			return Integer.parseInt(semesterBox.getSelectedItem().toString());
		}
	
		
		public void displaySemesterPanel() {
			View.this.page.show(rootPanel, "SemesterPage");
			View.this.refresh();
		}
		
	}
	
	
	
	
	//frame panel of main operator interface
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
	
	
	//represent the main content block
	public class Main{
		String defaultClassName = "  Please enter a class name, max length 20.";
		String defaultClassRequirement = "  Please enter a requirement, max length 200 words.";
		public CourseDetailPage courseDetailPage;
		public SelectTeacherPage selectTeacherPage;
		public CreateClassPage createClassPage;		
		public ListPage listPage;		
		Main(){
			centerPage =new CardLayout();
			centerPanel = new JPanel(centerPage);
			centerPanel.setBackground(Color.white);
			courseDetailPage = new CourseDetailPage();
			selectTeacherPage = new SelectTeacherPage();
			createClassPage = new CreateClassPage();
			listPage= new ListPage();	
		}
		
		public class CreateClassPage{
			JLabel classIDL;
			JLabel timeL;

			public void buildCreateClassPanel() {
				JPanel centerP = new JPanel();
			//centerP.setBorder(BorderFactory.createEmptyBorder(20,180,100,180));
					centerP.setLayout(null);
//					JPanel buttonPanel = new JPanel(new FlowLayout());
//					JPanel textAreaPanel = new JPanel(new BorderLayout());
					centerP.setBackground(Color.white);
//					buttonPanel.setBackground(Color.white);
//					textAreaPanel.setBackground(Color.white);
					
					JLabel createClassTitleL = new JLabel("Create a new course", SwingConstants.CENTER);
					createClassTitleL.setFont(new Font("Arial", Font.PLAIN, 24));
					createClassTitleL.setBounds(150, 50, 300, 62);
					centerP.add(createClassTitleL);
					
					
//				textAreaPanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
					JLabel classNameL = new JLabel("Class name:     ");
					classNameL.setForeground(new Color(114, 114, 114));
					classNameL.setFont(new Font("Arial", Font.PLAIN, 14));
					classNameL.setBounds(148, 149, 112, 21);
					centerP.add(classNameL);
					
					JLabel reqLTitle1 = new JLabel("Requirement:  ");		
					reqLTitle1.setForeground(new Color(114, 114, 114));
					reqLTitle1.setFont(new Font("Arial", Font.PLAIN, 14));
					reqLTitle1.setBounds(148, 215, 112, 21);
					centerP.add(reqLTitle1);
					
					JLabel classIDTitle=new JLabel("ID:");
					classIDTitle.setForeground(new Color(114, 114, 114));
					classIDTitle.setFont(new Font("Arial", Font.PLAIN, 14));
					classIDTitle.setBounds(148, 119, 112, 21);
					centerP.add(classIDTitle);
					
					classIDL=new JLabel();
					classIDL.setFont(new Font("Arial", Font.PLAIN, 14));
					classIDL.setBounds(170, 119, 112, 21);
					centerP.add(classIDL);
					
					
					JLabel timeTitle=new JLabel("Date:");
					timeTitle.setForeground(new Color(114, 114, 114));
					timeTitle.setFont(new Font("Arial", Font.PLAIN, 14));
					timeTitle.setBounds(258, 119, 112, 21);
					centerP.add(timeTitle);
					
					timeL=new JLabel();
					timeL.setFont(new Font("Arial", Font.PLAIN, 14));
					timeL.setBounds(298, 119, 112, 21);
					centerP.add(timeL);
					
					
					
					courseNameTF = new JTextField();
				
					courseNameTF.setFont(new Font("Arial", Font.PLAIN, 12));
					courseNameTF.setForeground(new Color(20,20,20));
					courseNameTF.setBounds(148, 170, 319, 36);

					courseNameTF.setColumns(10);
//					courseNameTF.addFocusListener(new JTextFieldHintListener(courseNameTF, defaultClassName));
					
					centerP.add(courseNameTF);

					
					int TA_row = 10;
					int TA_col = 30;
					
					requirementTA= new JTextArea(TA_row, TA_col);
					requirementTA.setFont(new Font("Arial", Font.PLAIN, 12));
					requirementTA.setMaximumSize(new Dimension(5, 30));
					requirementTA.setForeground(new Color(20,20,20));

					requirementTA.setWrapStyleWord(true);
					requirementTA.setLineWrap(true);
					
//					requirementTA.addFocusListener(new JTextFieldHintListener(requirementTA, defaultClassRequirement));
					
					JScrollPane reqScrollPanel = buildStylishScrollP();
					reqScrollPanel.setViewportView(requirementTA);
					reqScrollPanel.getVerticalScrollBar().setPreferredSize(new Dimension(6, 0));
					requirementTA.setEditable(true);
					reqScrollPanel.setBounds(148, 238, 319, 160);

//					reqScrollPanel.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
					
					centerP.add(reqScrollPanel);
					
					
					createClassOKBN = buildThemeButton("Ok");
					createClassOKBN.setBounds(375, 426, 90, 30);
					
					centerP.add(createClassOKBN);
					
					
					createClassCBN = buildBlackButton("Cancel");
					createClassCBN.setBounds(148, 426, 90, 30);
					
					centerP.add(createClassCBN);
					
					createClassPanel = centerP;
					centerPanel.add(createClassPanel, "createClassPanel");
			
			}
			
			public void displayCreateClassPanel() {
				bar.switchMainPage(createClassBN);
				centerPage.show(centerPanel, "createClassPanel");
				View.this.refresh();
			}
		
			//set the class ID and time
			public void updatePage(String [] data) {
				classIDL.setText(data[0] );
				timeL.setText(data[1]  );
			}
		
		
			public String getCreateClassString() {
				boolean finished = true;
				String s = "";
				s+= encodeString(classIDL.getText());
				if(courseNameTF.getText().equals(defaultClassName)|| courseNameTF.getText().equals("")) {
					courseNameTF.addFocusListener(new JTextFieldHintListener(courseNameTF, defaultClassName));
					finished = false;
				}
				s += encodeString(courseNameTF.getText());
				String req ="";
				if(requirementTA.getText().equals(" ")|| requirementTA.getText().equals("")) {
					requirementTA.addFocusListener(new JTextFieldHintListener(requirementTA, defaultClassRequirement));
					finished = false;
				}
				
				if(finished==false) {return null;}
				req += requirementTA.getText();
				
				s += encodeString(req);
		
				return s;
			}
		
		
			//show the warning message if users input nothing before they submit
			public void emptyTextWarning() {
				courseNameTF.addFocusListener(new JTextFieldHintListener(courseNameTF, defaultClassName));
				requirementTA.addFocusListener(new JTextFieldHintListener(requirementTA, defaultClassRequirement));
	
			}
			
			public void cleanText() {
				courseNameTF.setText("");
				requirementTA.setText("");
				
			}
	
		}
		
		public class ListPage{
			JLabel listTitleL;
			JComboBox statusList;
			JScrollPane listScrollP;
			int classListClickedHeader= 0;
			SortOrder order = SortOrder.ASCENDING;
			String courseClickedFilter="All";
			String [] courseListFilters = {"All", "Pending", "Assigned", "Submitted", "Approved"};
			String [] requestListFilters = {"All","Submitted", "Approved"};
			DefaultComboBoxModel courseListModel = new DefaultComboBoxModel(courseListFilters)  ;
			DefaultComboBoxModel requestListModel = new DefaultComboBoxModel( requestListFilters );

			String [] tableHeader = {"ID","Name","TeachingStatus","DCID","TID", "Date"};
			public String [] getHeader() {
				return tableHeader;
				
			}
			public void buildClassListPanel(String[] header, String[][] list) {
				classListPanel = new JPanel(new BorderLayout());
				classListPanel.setBorder(new EmptyBorder(50, 60, 5, 60));
				classListPanel.setBackground(Color.white);
				listTitleL = new JLabel("Course list");
				listTitleL.setFont(new Font("Arial",Font.PLAIN,22));
				classListPanel.add(listTitleL, BorderLayout.NORTH);
				classListTable = buildModelListTable(header, list);
				JPanel centerP = new JPanel(new BorderLayout());
	
				statusList = stylishComboBox();
				statusList.setModel(courseListModel);
				statusList.setSelectedIndex(0);
				
				statusList.setBounds(405, 0, 80, 20);
				
				JPanel boxP = new JPanel(null);
				boxP.setPreferredSize(new Dimension(200,30));
				boxP.setBackground(Color.white);
				boxP.add(statusList);
				centerP.setBorder(new EmptyBorder(15, 0, 0, 0));
				centerP.setBackground(Color.white);
				centerP.add(boxP, BorderLayout.NORTH);
				enableTableHoverEffect(classListTable);
				listScrollP= buildStylishScrollP ();
				listScrollP.setBorder(new EmptyBorder(0, 0, 0, 0));
				listScrollP.setViewportView(classListTable);
				
		        
		        
		      
		        centerP.add(listScrollP, BorderLayout.CENTER);
		        JPanel emptyP = new JPanel(null);
		        emptyP.setPreferredSize(new Dimension(200,30));
		        emptyP.setBackground(Color.white);
				classListPanel.add(centerP, BorderLayout.CENTER);
				classListPanel.add(emptyP, BorderLayout.SOUTH);
				classListPanel.setVisible(true);
	
				centerPanel.add(classListPanel, "classListPanel");
	
			}
			
			public void displayMyCourseListPanel(String[] header, String[][] list) {	
				TableModel m = new DefaultTableModel(list, header) ;
				View.this.classListTable.setModel(m);
				View.this.classListTable.setRowSorter(null);
				View.this.classListTable = main.setMainTableColSize(View.this.classListTable);
				listTitleL.setText("My course list");
				View.this.classListTable.setAutoCreateRowSorter(true);
				statusList.setModel(courseListModel);
				bar.switchMainPage(myClassListBN);
				centerPage.show(centerPanel, "classListPanel");
	
			}
			
		
			
			
			public void displayClassListPanel(String[] header, String[][] list) {	
				TableModel m = new DefaultTableModel(list, header) ;
				View.this.classListTable.setModel(m);
				View.this.classListTable.setRowSorter(null);
				View.this.classListTable = main.setMainTableColSize(View.this.classListTable);
				listTitleL.setText("Course list");
				View.this.classListTable.setAutoCreateRowSorter(true);
				statusList.setModel(courseListModel);
				bar.switchMainPage(classListBN);
				centerPage.show(centerPanel, "classListPanel");
				refresh();
			}
			
		
			
			
			public void displayTeachingRequestListPanel(String[] header, String[][] list) {	
				DefaultTableModel  m = new DefaultTableModel(null, header) ;
				
				int key = -1;
				
				if(list != null) {
					//find the entries that need to be added to the request list
					for(int i = 0 ; i<header.length; i++) {
						if(header[i].equals("TeachingStatus")) {
							key=i;
						}
					}
		
					for(int i =0 ; i< list.length ; i++) {
		
						if(list[i][key].equals("Submitted")||list[i][key].equals("Approved")) {		
							m.addRow(list[i]);
						}else {
						}
					}
					View.this.classListTable.setModel(m);
				}
				classListTable.setAutoCreateRowSorter(true);
				classListTable = main.setMainTableColSize(classListTable);
				listTitleL.setText("Teaching request list");
				statusList.setModel(requestListModel);
				bar.switchMainPage(requestListBN);
				centerPage.show(centerPanel, "classListPanel");
				refresh();
			}
			
			public void buildSorter() {
				statusList.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
					courseClickedFilter = (String) statusList.getSelectedItem();
	
				     RowFilter<DefaultTableModel, Object> rf = null;
	
				     try {
				    	 if(courseClickedFilter.equals("All")) {}
				    	 else {
				    		 rf = RowFilter.regexFilter(courseClickedFilter,
				    				 classListTable.getColumnModel().getColumnIndex("TeachingStatus"));
				    		 
				    	 }
				      } catch (PatternSyntaxException ex) {
				                     ex.printStackTrace();
				      }
				      ((TableRowSorter)classListTable.getRowSorter()).setRowFilter(rf);
	
				     }
	
				});
				
				
				//set a sorter for table header
				JTableHeader listHeader = classListTable.getTableHeader();
				listHeader.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						classListClickedHeader = (listHeader.columnAtPoint(e.getPoint()));
						RowSorter.SortKey key = classListTable.getRowSorter().getSortKeys().get(0);
						order= key.getSortOrder();
						System.out.print(order);
					}
					
				});
				classListTable.getRowSorter().toggleSortOrder(classListClickedHeader);
				
	
			}
		}
		
		public class CourseDetailPage {
			JLabel classNameL;
			JLabel dateL;
			JLabel classIDTitleL;
			JLabel directorNameTitleL;
			JLabel directorIDTitleL;
			JLabel admIDTitleL;
			JLabel admIDL;
			JLabel PTTIDTitleL;
			JLabel PTTIDL;
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
			JScrollPane trainingSP;
			String [] query = {"Semester","Name","Date","ClassID","ClassDirectorID","ClassDirectorName","AdminID","PDID", 
					"TeacherStatus", "TeacherID", "TeacherName", "Requirements", "Trainning"};
			
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
			
			public String[] getQuery() {
				return query;
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
				
				
				dateL = new JLabel("");
				dateL.setForeground(Color.black);
				dateL.setFont(new Font("Arial", Font.PLAIN, 12));
				dateL.setBounds(475, 50, 70, 29);
				classDetailPanel.add(dateL);
				labelList.add(dateL);
				
				classIDTitleL = new JLabel("Course ID");
				classIDTitleL.setForeground(titleFontColor);
				classIDTitleL.setFont(new Font("Arial", Font.PLAIN, 14));
				classIDTitleL.setBounds(65, 92, 91, 29);
				classDetailPanel.add(classIDTitleL);
				
				
				
				directorIDTitleL = new JLabel("CD ID");
				directorIDTitleL.setForeground(titleFontColor);
				directorIDTitleL.setFont(new Font("Arial", Font.PLAIN, 14));
				directorIDTitleL.setBounds(184, 91, 53, 29);
				classDetailPanel.add(directorIDTitleL);
				
				directorNameTitleL = new JLabel("Director");
				directorNameTitleL.setForeground(titleFontColor);
				directorNameTitleL.setFont(new Font("Arial", Font.PLAIN, 14));
				directorNameTitleL.setBounds(273, 90, 140, 29);
				classDetailPanel.add(directorNameTitleL);

				
				
				admIDTitleL = new JLabel("Admin ID");
				admIDTitleL.setForeground(titleFontColor);
				admIDTitleL.setFont(new Font("Arial", Font.PLAIN, 14));
				admIDTitleL.setBounds(378, 90, 116, 29);
				classDetailPanel.add(admIDTitleL);
				
				
				
				PTTIDTitleL = new JLabel("PTT ID");
				PTTIDTitleL.setForeground(titleFontColor);
				PTTIDTitleL.setFont(new Font("Arial", Font.PLAIN, 14));
				PTTIDTitleL.setBounds(475, 90, 116, 29);
				classDetailPanel.add(PTTIDTitleL);
			
				

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
				directorNameL.setBounds(273, 111, 90, 29);
				classDetailPanel.add(directorNameL);
				labelList.add(directorNameL);
				
				admIDL = new JLabel("");
				admIDL.setForeground(Color.BLACK);
				admIDL.setFont(new Font("Arial", Font.PLAIN, 14));
				admIDL.setBounds(378, 111, 82, 29);
				classDetailPanel.add(admIDL);
				labelList.add(admIDL);
				
				PTTIDL = new JLabel("");
				PTTIDL.setForeground(Color.BLACK);
				PTTIDL.setFont(new Font("Arial", Font.PLAIN, 14));
				PTTIDL.setBounds(475, 111, 82, 29);
				classDetailPanel.add(PTTIDL);
				labelList.add(PTTIDL);
				
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
				
				
				JScrollPane requirementSP = buildStylishScrollP();
				requirementSP.setBounds(65, 170, 495, 106);
				classDetailPanel.add(requirementSP);
				
				requirementTA = new JTextArea();
				requirementTA.setFont(new Font("Arial", Font.PLAIN, 14));
				requirementTA.setEditable(false);
				requirementTA.setWrapStyleWord(true);
				requirementTA.setLineWrap(true);
				requirementTA.setText("");
				requirementSP.getVerticalScrollBar().setUI(new stylishDarkScrollBar());
				requirementSP.setViewportView(requirementTA);
				requirementSP.getVerticalScrollBar().setPreferredSize(new Dimension(4, 0));
				TAList.add(requirementTA);
				
				
				trainingTA  = new JTextArea();
				trainingTA.setWrapStyleWord(true);
				trainingTA.setLineWrap(true);
				trainingTA.setEditable(false);
				trainingTA.setText("");
				
				trainingSP = buildStylishScrollP();
				trainingSP.setToolTipText("");
				trainingSP.setBounds(158, 36, 319, 70);
				operateP.add(trainingSP);
				trainingSP.getVerticalScrollBar().setUI(new stylishDarkScrollBar());
				trainingSP.setViewportView(trainingTA);
				trainingSP.getVerticalScrollBar().setPreferredSize(new Dimension(4, 0));

				
				TAList.add(trainingTA);
				
				
				

				submitButtonsPanel.setBounds(20, 113, 470, 28);
				submitButtonsPanel.setBackground(Color.white);

				
				courseDetailWBN = buildRedButton("");
				courseDetailCBN = buildBlackButton("");						
				courseDetailSBN = buildThemeButton("");
				
						
				
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
				
				assignTeacherBN = buildThemeButton("Assign");
				assignTeacherBN.setBounds(75, 13, 40, 16);
				assignTeacherBN.setFont(new Font("Arial", Font.BOLD, 8));
				assignTeacherBN.setVisible(false);
				assignTeacherBN.setEnabled(false);
				assignTeacherBN.addActionListener(View.this);
				operateP.add(assignTeacherBN);
				
			
				
				centerPanel.add(classDetailPanel, "classDetailPanel");
			}
			
			
			
			
			
			public int withdrawCheck() {
				UIManager.put("Button.background", red);
				UIManager.put("Button.foreground", Color.white);
				UIManager.put("Panel.background", Color.white);
				UIManager.put("OptionPane.background", Color.white);
				int n = JOptionPane.showConfirmDialog(null, "Are you sure to withdraw?", "wanring",JOptionPane.YES_NO_OPTION);
				return n;
			}
			
			
			
			
			
			
			public String[] getAssignTeacher() {
				String [] s = new String [3];
				String teacherWarning = "Please assign a teacher.";
				String trainingWarning= "The length of the training must be larger than 5.";
				s[0] =	classIDL.getText();
				
				if(staffIDL.getText().length()<3||staffIDL.getText().equals(teacherWarning)){
					staffIDL.setText(teacherWarning);			
					if(trainingTA.getText().length()<5||trainingTA.getText().equals(trainingWarning)) {
						trainingTA.addFocusListener(new JTextFieldHintListener(trainingTA, trainingWarning));
					}
					return null;
				}	
				if(trainingTA.getText().length()<5||trainingTA.getText().equals(trainingWarning)) {
						trainingTA.addFocusListener(new JTextFieldHintListener(trainingTA, trainingWarning));
						return null;
				}
				else {
					s[1] = staffIDL.getText();
					s[2] = trainingTA.getText();	
					return s;
				}
			}
			
			

			public void updateData(String[] data) {
				centerPage.show(centerPanel, "classDetailPanel");
				int i = 1;	//start with class id #2 element
				
//				refinedData[0] = data[0]; //semester
//				refinedData[1] = data[2]; //classname
//				refinedData[2] = data[1]; //classid
//				refinedData[3] = data[5]; //directorid
//				refinedData[4] = data[6]; //directorname
//				refinedData[5] = data[4]; //status
//				refinedData[6] = data[7]; //teacherid
//				refinedData[7] = data[8]; //teachername
//				refinedData[8] = data[3]; //requirement
//				refinedData[9] = data[9]; //training				
				
				if(data[8].equals("Pending")) {
					statusIndex = 1;
					data[8] = "Teacher is not assigned.";
				}
				else if(data[8].equals("Assigned")) {
					statusIndex = 2;
					data[8] = "Waiting for Class Director to submit teaching request.";
				}
				else if(data[8].equals("Submitted")) {
					statusIndex = 3;
					data[8] = "Waiting for PTT Director to approve teaching request.";
				}
				else if(data[8].equals("Approved")) {
					statusIndex = 4;
					data[8] = "Teaching request had been approved";
				}else {
					data[8] = "Error" + data[8];
				}
				
				

				
				for(JLabel l : labelList) {
					if(i<data.length) {
						l.setText(data[i]);
						i++;
					}
				}
				for(JTextArea l : TAList) {

					if(i<data.length) {
						l.setText(data[i]);
						i++;
					}
				}

				View.this.refresh();
			}
			
			private void defualtTextArea() {
				trainingTA.setSelectionStart(0);
				trainingTA.setSelectionEnd(0);
				requirementTA.setSelectionStart(0);
				requirementTA.setSelectionEnd(0);
			}
			
			public void displayCDMode(String[] data, String DCID) {
				updateData(data);
				courseDetailWBN.setText("Withdraw");
				courseDetailCBN.setText("Cancel");				
				courseDetailSBN.setText("Submit");
				defualtTextArea();
				
				if(View.this.main.courseDetailPage.statusIndex == 1) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "normalMode");
				}else if(View.this.main.courseDetailPage.statusIndex == 2) {
					if(View.this.main.courseDetailPage.directorIDL.getText().equals(DCID)) {
						View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "activeMode");
					}else {
						View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "normalMode");
					}
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
				defualtTextArea();
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
				courseDetailSBN.setText("Approve");
				courseDetailWBN.setVisible(true);
				defualtTextArea();
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
				defualtTextArea();
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
			String [] query = {"ID", "Type", "Name" };
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
				
				JScrollPane scrollPane = buildStylishScrollP();

				
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
				scrollPane.getVerticalScrollBar().setUI(new stylishScrollBar());
				
				ListP.add(scrollPane, BorderLayout.CENTER);
				JPanel buttonP = new JPanel(new GridLayout(1,1));
				buttonP.setPreferredSize(new Dimension(50, 80));
				buttonP.setBackground(Color.white);
				JPanel subbuttonP = new JPanel(null);
				selectTeacherSubmitBN = buildThemeButton("Select");
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
			
			public String[] getQuery() {
				return query;
			}
			
			
			public void displaySelectTeacherPanel(String [][] staffList, String semester) {
				TableModel m = new DefaultTableModel(staffList, query) ;
				View.this.staffListTable.setModel(m);
				staffListTable.setAutoCreateRowSorter(true);		
				staffListTable.getRowSorter().toggleSortOrder(0);

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
		
		
		public JTable setMainTableColSize(JTable t) {
			JTable t1 =t;
		     t1.getColumnModel().getColumn(0).setPreferredWidth(8);
	         t1.getColumnModel().getColumn(1).setPreferredWidth(40);
	         t1.getColumnModel().getColumn(3).setPreferredWidth(14);
	         t1.getColumnModel().getColumn(4).setPreferredWidth(14);
	         t1.getColumnModel().getColumn(5).setPreferredWidth(18);
	         return t1;
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
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) 	
		modelTable.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.LEFT);					//Align table headers
		modelTable.setBackground(Color.WHITE);
		modelTable.getTableHeader().setBackground(Color.white);
		modelTable.getTableHeader().setReorderingAllowed(false);
		modelTable.setFillsViewportHeight(true);
		modelTable.setRowHeight(32);
		modelTable.setShowVerticalLines(false);
		modelTable.setGridColor(new Color(222, 222, 222));



		Dimension size = modelTable.getTableHeader().getPreferredSize();
        size.height = 30;
        modelTable.getTableHeader().setPreferredSize(size);
        UIManager.getDefaults().put("TableHeader.cellBorder" , BorderFactory.createEmptyBorder(0,0,0,0));
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
			    if(hoveredRow>-1&&hoveredRow<modelTable.getRowCount()+1) {
			    	modelTable.repaint(); 
		    		modelTable.setRowSelectionInterval(hoveredRow, hoveredRow);
	
			    }else {
			    	modelTable.clearSelection();
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
		public HashMap<String, BarListener> BNListenerList ;
		String myClassListBNName = "         My course list";
		String createClassBNName = "         + Course";		
		String requestListBNName = "         Teaching Request list";
		String classListBNName = "         Course list";
		String logoutBNName = "         Log out";
		JButton selectedButton = null;
		Bar(){
			BNListenerList = new HashMap<String, BarListener>();
		}
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
			northPanel.setBackground(themeGrey);
			JLabel titleL = new JLabel("Class Dirctor");
			JLabel nameL = new JLabel("Hi, "+name);
			list.setBackground(themeGrey);
			list.setAlignmentX(Component.LEFT_ALIGNMENT);			
			titleL.setFont(new Font("Arial",Font.BOLD,15));
			nameL.setFont(new Font("Arial",Font.BOLD,15));
			titleL.setForeground(Color.WHITE);
			nameL.setForeground(Color.WHITE);
			
			northPanel.add(nameL);
			northPanel.add(titleL);
			westPanel.add(northPanel, BorderLayout.NORTH);
			
			List<JButton> listPS = new LinkedList<JButton>();
			createClassBN = buildThemeBorderlessButton(createClassBNName);
			myClassListBN = buildThemeBorderlessButton(myClassListBNName);
			classListBN = buildThemeBorderlessButton(classListBNName);
			requestListBN = buildThemeBorderlessButton(requestListBNName);
			logoutBN = buildThemeBorderlessButton(logoutBNName);
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
			northPanel.setBackground(themeGrey);
			JLabel titleL = new JLabel("Administrator");
			JLabel nameL = new JLabel("Hi, "+name);
			list.setBackground(themeGrey);
			list.setAlignmentX(Component.LEFT_ALIGNMENT);			
			titleL.setFont(new Font("Arial",Font.BOLD,15));
			nameL.setFont(new Font("Arial",Font.BOLD,15));
			titleL.setForeground(Color.WHITE);
			nameL.setForeground(Color.WHITE);
			
			northPanel.add(nameL);
			northPanel.add(titleL);
			westPanel.add(northPanel, BorderLayout.NORTH);
			
			List<JButton> listPS = new LinkedList<JButton>();
			classListBN = buildThemeBorderlessButton(classListBNName);
			requestListBN = buildThemeBorderlessButton(requestListBNName);
			logoutBN = buildThemeBorderlessButton(logoutBNName);
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
			northPanel.setBackground(themeGrey);
			JLabel titleL = new JLabel("PTT Dirctor");
			JLabel nameL = new JLabel("Hi, "+name);
			list.setBackground(themeGrey);
			list.setAlignmentX(Component.LEFT_ALIGNMENT);			
			titleL.setFont(new Font("Arial",Font.BOLD,15));
			nameL.setFont(new Font("Arial",Font.BOLD,15));
			titleL.setForeground(Color.WHITE);
			nameL.setForeground(Color.WHITE);
			
			northPanel.add(nameL);
			northPanel.add(titleL);
			westPanel.add(northPanel, BorderLayout.NORTH);
			
			List<JButton> listPS = new LinkedList<JButton>();
			classListBN = buildThemeBorderlessButton(classListBNName);
			requestListBN = buildThemeBorderlessButton(requestListBNName);
			logoutBN = buildThemeBorderlessButton(logoutBNName);
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
		
		
		private JButton buildThemeBorderlessButton(String name) {
			JButton btn = new JButton(name);
			btn.setOpaque(true);
			btn.setBackground(themeGrey);
			btn.setForeground(Color.white);
			btn.setBorder(BorderFactory.createEmptyBorder());
			btn.setFocusPainted(false);
			btn.setFont(new Font("Arial", Font.PLAIN, 12));
	    	btn.setBorder(BorderFactory.createEmptyBorder());
	    	BarListener listener = new BarListener(btn);
	    	btn.getModel().addChangeListener(listener);
	    	BNListenerList.put(name,listener);
			return btn;
		}
		
		public void switchMainPage(JButton btn) {
			System.out.println("yo");
			System.out.println(btn.getText());
			if(selectedButton!=null) {
				System.out.println(selectedButton.getText());
			}
			if(selectedButton!=null&&selectedButton.equals(btn)) {
				System.out.println("yo");
				System.out.println(main.listPage.courseClickedFilter+"2");
				RowFilter<DefaultTableModel, Object> rf = null;
				try {					
				    if(main.listPage.courseClickedFilter.equals("All")) {}
				    else {
				    	 rf=RowFilter.regexFilter(main.listPage.courseClickedFilter,
				    	 classListTable.getColumnModel().getColumnIndex("TeachingStatus"));
				    		 
				    }
				} catch (PatternSyntaxException ex) {
				                  ex.printStackTrace();
				}


		    	TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(classListTable.getModel());
		   
		    	classListTable.setRowSorter(sorter);

		    	List<RowSorter.SortKey> sortKeys = new ArrayList<>(1);
		    	System.out.println(main.listPage.order+"1");
		    	sortKeys.add(new RowSorter.SortKey(main.listPage.classListClickedHeader, main.listPage.order));
		    	sorter.setSortKeys(sortKeys);
		    	classListTable.setRowSorter(sorter);
		    	((TableRowSorter)classListTable.getRowSorter()).setRowFilter(rf);
			}			
			else {
				View.this.main.listPage.statusList.setSelectedIndex(0);
				View.this.main.listPage.classListClickedHeader=0;
				View.this.main.listPage.buildSorter();
				main.listPage.order = SortOrder.ASCENDING;
				System.out.println(main.listPage.courseClickedFilter+"22");
				System.out.println(main.listPage.order+"22");
				selectedButton= btn;
				btn.setBackground(Color.white);
			    btn.setForeground(themeGrey);
				for(HashMap.Entry<String, BarListener> entry : bar.BNListenerList.entrySet()) {
					if(entry.getKey().equals(btn.getText())) {
						entry.getValue().press=true;
			    	}
					else {
						entry.getValue().setDefault();
					}
				}
			}
			 
	    	View.this.refresh();
		}
		
		public class BarListener implements ChangeListener{
			JButton btn;
	        public Boolean press = false;
	        BarListener(JButton btn){
		    	this.btn = btn;
		    }
		    @Override
		    public void stateChanged(ChangeEvent e) {
		        ButtonModel model = (ButtonModel) e.getSource();
		        if(press==false) {
		        	if (model.isPressed()) {
				       btn.setBackground(Color.white);
				       btn.setForeground(themeGrey);
				       btn.setBorder(BorderFactory.createEmptyBorder());
				       btn.setFont(new Font("Arial", Font.PLAIN, 12));
				      
				        press = true;		        
				    } else if (model.isRollover()) {
				       btn.setBackground(Color.white);
					   btn.setForeground(themeGrey);
					   btn.setBorder(BorderFactory.createEmptyBorder());		
				     } else {
				       btn.setBackground(themeGrey);
					   btn.setForeground(Color.white);
					   btn.setBorder(BorderFactory.createEmptyBorder());
				    }
		       	}
		    }
		    
		    public void setDefault() {
		    	 btn.setBackground(themeGrey);
				 btn.setForeground(Color.white);
				 btn.setBorder(BorderFactory.createEmptyBorder());
				 press=false;
		    }
		
		}
	}
	
	
	private JButton buildBlackButton(String name) {
		JButton btn = new JButton(name);
		Color lightGrey = new Color(200,200,200);
		btn.setBackground(lightGrey);
		btn.setBorder(BorderFactory.createEmptyBorder());
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("Arial", Font.PLAIN, 12));
		btn.setFocusPainted(false);
		btn.setOpaque(true);
		btn.getModel().addChangeListener(new ChangeListener() {
			    @Override
			    public void stateChanged(ChangeEvent e) {
			        ButtonModel model = (ButtonModel) e.getSource();
			        if (model.isRollover()) {
			        	btn.setBackground(Color.white);
				    	btn.setForeground(lightGrey);
				    	btn.setBorder(BorderFactory.createLineBorder(lightGrey, 1));
			        } else if (model.isPressed()) {
			        	btn.setBackground(Color.white);
				    	btn.setForeground(lightGrey);
				    	btn.setBorder(BorderFactory.createEmptyBorder());
			        } else {
			        	btn.setBackground(lightGrey);
				    	btn.setForeground(Color.white);
				    	btn.setBorder(BorderFactory.createEmptyBorder());
			        }
			    }
	
		});		
		return btn;
	}
	
	private JButton buildRedButton(String name) {
		JButton btn = new JButton(name);
		Color red = new Color(227, 23, 13);
		Color lightGrey = new Color(150,150,150);
		btn.setBackground(red);
		btn.setBorder(BorderFactory.createEmptyBorder());
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("Arial", Font.PLAIN, 12));
		btn.setFocusPainted(false);
		btn.setOpaque(true);
		btn.getModel().addChangeListener(new ChangeListener() {
			    @Override
			    public void stateChanged(ChangeEvent e) {
			        ButtonModel model = (ButtonModel) e.getSource();
			        if (model.isRollover()) {
			        	btn.setBackground(Color.white);
				    	btn.setForeground(red);
				    	btn.setBorder(BorderFactory.createLineBorder(red, 1));
			        } else if (model.isPressed()) {
			        	btn.setBackground(Color.white);
				    	btn.setForeground(red);
				    	btn.setBorder(BorderFactory.createEmptyBorder());
			        } else {
			        	btn.setBackground(red);
				    	btn.setForeground(Color.white);
				    	btn.setBorder(BorderFactory.createEmptyBorder());
			        }
			    }
	
		});		
		return btn;
	}
	
	private JButton buildThemeButton(String name) {
		JButton btn = new JButton(name);
		
		btn.setBackground(themeGrey);
		btn.setForeground(Color.white);
		btn.setBorder(BorderFactory.createEmptyBorder());
		btn.setFont(new Font("Arial", Font.PLAIN, 12));
		btn.setFocusPainted(false);
		btn.setOpaque(true);
		btn.getModel().addChangeListener(new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
		        ButtonModel model = (ButtonModel) e.getSource();
		        if (model.isRollover()) {
		        	btn.setBackground(Color.white);
			    	btn.setForeground(themeGrey);
			    	btn.setBorder(BorderFactory.createLineBorder(themeGrey, 1));
		        } else if (model.isPressed()) {
		        	
		        } else {
		        	btn.setBackground(themeGrey);
			    	btn.setForeground(Color.white);
			    	btn.setBorder(BorderFactory.createEmptyBorder());
		        }
		    }

	});		
		

		return btn;
	}
	
	public JScrollPane buildStylishScrollP () {
		JScrollPane sp = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp.setComponentZOrder(sp.getVerticalScrollBar(), 0);
		sp.setComponentZOrder(sp.getViewport(), 1);
		sp.getVerticalScrollBar().setOpaque(false);
		sp.getVerticalScrollBar().setUI(new stylishScrollBar());
		sp.getViewport().setBackground(Color.WHITE);
		sp.setBackground(Color.white);
		sp.getVerticalScrollBar().setBackground(Color.WHITE);
		sp.getVerticalScrollBar().setForeground(Color.WHITE);
		sp.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
		return sp;
	}
	
	public class stylishScrollBar extends BasicScrollBarUI {
		 private final Dimension d = new Dimension();
	      @Override 
	      protected JButton createDecreaseButton(int orientation) {
	        return new JButton() {
	          @Override 
	          public Dimension getPreferredSize() {
	            return d;
	          }
	        };
	      }
	      @Override 
	      protected JButton createIncreaseButton(int orientation) {
	        return new JButton() {
	          @Override 
	          public Dimension getPreferredSize() {
	            return d;
	          }
	        };
	      }
	      @Override
	      protected void paintTrack(Graphics g, JComponent c, Rectangle r) {}
	      @Override
	      protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
	        Graphics2D g2 = (Graphics2D)g.create();
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                            RenderingHints.VALUE_ANTIALIAS_ON);
	        Color color = null;
	        JScrollBar sb = (JScrollBar)c;
	        if(!sb.isEnabled() || r.width>r.height) {
	          return;
	        }else if(isDragging) {
	          color = new Color(200,200,205,200);
	        }else if(isThumbRollover()) {
	          color = new Color(200,200,205,200);
	        }else {
	          color = new Color(220,220,220,200);
	        }
	        g2.setPaint(color);
	        g2.fillRoundRect(r.x,r.y,r.width,r.height,10,10);
	        g2.setPaint(Color.WHITE);
	        g2.drawRoundRect(r.x,r.y,r.width,r.height,10,10);
	        g2.dispose();
	      }
	      @Override
	      protected void setThumbBounds(int x, int y, int width, int height) {
	        super.setThumbBounds(x, y, width, height);
	        scrollbar.repaint();
	      
//	       main.listPage.listScrollP.setLayout(new ScrollPaneLayout() {
//	      @Override
//	      public void layoutContainer(Container parent) {
//	    	  super.layoutContainer(parent);
//	        JScrollPane scrollPane = (JScrollPane)parent;
//	        
//	        Rectangle availR = scrollPane.getBounds();
//////	        availR.x = availR.y = 0;
////	        Rectangle availR = colHead.getBounds();
////	        Insets insets = parent.getInsets();
////	        availR.x = insets.left;
////	        availR.y = insets.top;
////	        availR.width  -= insets.left + insets.right;
////	        availR.height -= insets.top  + insets.bottom;
//
//	        Rectangle vsbR = new Rectangle();
//	        vsbR.width  = 7;
//	        vsbR.height = availR.height;
//	        vsbR.x = availR.x + availR.width - vsbR.width;
//	        vsbR.y = availR.y;
//
////	        if(viewport != null) {
//////	          viewport.setBounds(availR);
////	          colHead.setBounds(availR);
////	        }
//	        if(vsb != null) {
//	          vsb.setVisible(true);
//	          vsb.setBounds(vsbR);
//	        }
//	      }
//});
	      	}
		}
	      public class stylishDarkScrollBar extends BasicScrollBarUI {
	 		 private final Dimension d = new Dimension();
	 	      @Override 
	 	      protected JButton createDecreaseButton(int orientation) {
	 	        return new JButton() {
	 	          @Override 
	 	          public Dimension getPreferredSize() {
	 	            return d;
	 	          }
	 	        };
	 	      }
	 	      @Override 
	 	      protected JButton createIncreaseButton(int orientation) {
	 	        return new JButton() {
	 	          @Override 
	 	          public Dimension getPreferredSize() {
	 	            return d;
	 	          }
	 	        };
	 	      }
	 	      @Override
	 	      protected void paintTrack(Graphics g, JComponent c, Rectangle r) {}
	 	      @Override
	 	      protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
	 	        Graphics2D g2 = (Graphics2D)g.create();
	 	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	 	                            RenderingHints.VALUE_ANTIALIAS_ON);
	 	        Color color = null;
	 	        JScrollBar sb = (JScrollBar)c;
	 	        if(!sb.isEnabled() || r.width>r.height) {
	 	          return;
	 	        }else if(isDragging) {
	 	          color = themeGrey;
	 	        }else if(isThumbRollover()) {
	 	          color = themeGrey;
	 	        }else {
	 	          color = new Color(114,114,114,200);
	 	        }
	 	        g2.setPaint(color);
	 	        g2.fillRoundRect(r.x,r.y,r.width,r.height,10,10);
	 	        g2.setPaint(Color.WHITE);
	 	        g2.drawRoundRect(r.x,r.y,r.width,r.height,10,10);
	 	        g2.dispose();
	 	      }
	 	      @Override
	 	      protected void setThumbBounds(int x, int y, int width, int height) {
	 	        super.setThumbBounds(x, y, width, height);
	 	        scrollbar.repaint();
	 	      
	 	      }
	      }
	      
	      public JComboBox stylishComboBox() {
	    	  JComboBox box = new JComboBox();
	    	  box.setBackground(Color.white);
	    	  box.setForeground(new Color(70,70,70));
	    	  box.setUI(new BasicComboBoxUI() {
				    @Override
				    protected JButton createArrowButton() {
				        final Color background = new Color(230,230,230);     //Default is UIManager.getColor("ComboBox.buttonBackground").
			            final Color pressedButtonBorderColor = new Color(140,140,140); //Default is UIManager.getColor("ComboBox.buttonShadow"). The color of the border of the button, while it is pressed.
			            final Color triangle = Color.WHITE;               //Default is UIManager.getColor("ComboBox.buttonDarkShadow"). The color of the triangle.
			            final Color highlight = new Color(140,140,140);              //Default is UIManager.getColor("ComboBox.buttonHighlight"). Another color to show the button as highlighted.
			            final JButton button = new BasicArrowButton(BasicArrowButton.SOUTH, background, pressedButtonBorderColor, triangle, highlight);
			            button.setName("ComboBox.arrowButton"); //Mandatory, as	 per BasicComboBoxUI#createArrowButton().
			   
	//		            button.setBackground(themeGrey);
			            box.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230,200)));
			            button.setBorder(new EmptyBorder(0, 0, 0, 0)) ;
			            button.setBorderPainted(true);
			            button.setContentAreaFilled(false);
			            
			            button.getModel().addChangeListener(new ChangeListener() {
			    		    @Override
			    		    public void stateChanged(ChangeEvent e) {
			    		        ButtonModel model = (ButtonModel) e.getSource();
			    		        if (model.isRollover()) {
			    		        	button.setBackground(new Color(200,200,205));
			    		        } else if (model.isPressed()) {
			    		        	button.setBackground(new Color(200,200,205));
			    		        } else {
			    		        	button.setBackground(new Color(230,230,230));
			    		        	
			    		        }
			    		    }
	
			            });		
	//		            button.setFocusPainted(false);
	//		            button.setFocusable(false);
			            return button; 
				    }
				    
				    protected ComboPopup createPopup() {
				        BasicComboPopup basicComboPopup = new BasicComboPopup(comboBox);
				        basicComboPopup.setBorder(new LineBorder(new Color(230,230,230,200)));
				        return basicComboPopup;
				    }
	
				});
	    	  box.setFocusable(false);		
	    	  box.setBorder(BorderFactory.createLineBorder( new Color(230,230,230)));
	    	  box.setFont(new Font("Arial",Font.PLAIN,12));
	    	  return box;
	      }
	      
	      
	      
	      public JComboBox themeStylishComboBox() {
	    	  JComboBox box = new JComboBox();
	    	  box.setBackground(Color.white);
	    	  box.setForeground(new Color(70,70,70));
	    	  box.setUI(new BasicComboBoxUI() {
				    @Override
				    protected JButton createArrowButton() {
				        final Color background = themeGrey;     //Default is UIManager.getColor("ComboBox.buttonBackground").
			            final Color pressedButtonBorderColor = new Color(140,140,140); //Default is UIManager.getColor("ComboBox.buttonShadow"). The color of the border of the button, while it is pressed.
			            final Color triangle = Color.WHITE;               //Default is UIManager.getColor("ComboBox.buttonDarkShadow"). The color of the triangle.
			            final Color highlight = themeGrey;              //Default is UIManager.getColor("ComboBox.buttonHighlight"). Another color to show the button as highlighted.
			            final JButton button = new BasicArrowButton(BasicArrowButton.SOUTH, background, pressedButtonBorderColor, triangle, highlight);
			            button.setName("ComboBox.arrowButton"); //Mandatory, as	 per BasicComboBoxUI#createArrowButton().
			   
	//		            button.setBackground(themeGrey);
			            box.setBorder(BorderFactory.createLineBorder(themeGrey));
			            button.setBorder(new EmptyBorder(0, 0, 0, 0)) ;
			            button.setBorderPainted(true);
			            button.setContentAreaFilled(false);
			            
			            button.getModel().addChangeListener(new ChangeListener() {
			    		    @Override
			    		    public void stateChanged(ChangeEvent e) {
			    		        ButtonModel model = (ButtonModel) e.getSource();
			    		        if (model.isRollover()) {
			    		        	button.setBackground(new Color(200, 200, 200,200));
			    		        } else if (model.isPressed()) {
			    		        	button.setBackground(new Color(200, 200, 200,200));
			    		        } else {
			    		        	button.setBackground(themeGrey);
			    		        	
			    		        }
			    		    }
	
			            });		
	//		            button.setFocusPainted(false);
	//		            button.setFocusable(false);
			            return button; 
				    }
				    
				    protected ComboPopup createPopup() {
				        BasicComboPopup basicComboPopup = new BasicComboPopup(comboBox);
				        basicComboPopup.setBorder(new LineBorder(new Color(114, 114, 114,200)));
				        return basicComboPopup;
				    }
	
				});
	    	  box.setFocusable(false);		
	    	  box.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	    	  box.setFont(new Font("Arial",Font.PLAIN,12));
	    	  return box;
	      }
}

	
	

