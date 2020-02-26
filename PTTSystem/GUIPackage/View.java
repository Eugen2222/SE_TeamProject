package GUIPackage;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
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
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;




public class View extends JFrame  implements ActionListener{
//	JLabel title,courseDirctor; 
	public JButton loginBN,logoutBN,semesterBN,createClassBN,classListBN,teacherList,requestList,createCourse,createClassOKBN,createClassCBN;
	public JTextField usernameTF,passwordTF, semesterTF,courseNameTF,requirment1TF,requirment2TF;
	public Color blue = new java.awt.Color(135,206,250);
	public JPanel barPanel, loginPanel, semesterPanel, framePanel, centerPanel, createClassPanel, classListPanel, rootPanel, classDetailPanel;
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
		public CourseDetailPage courseDetailPage;
		Main(){
			centerPage =new CardLayout();
			centerPanel = new JPanel(centerPage);
			centerPanel.setBackground(Color.white);
			courseDetailPage = new CourseDetailPage();
		}
		
		
		public void buildCreateClassPanel() {
			JPanel center = new JPanel();
			center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
			center.setBorder(BorderFactory.createEmptyBorder(40,180,100,180));
			JPanel buttonPanel = new JPanel(new FlowLayout());
			JPanel textAreaPanel = new JPanel(new BorderLayout());
			textAreaPanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
			JLabel classNameL = new JLabel("Class name:     ");
			JLabel reqL1 = new JLabel("Requirement:  ");
//			JLabel reqL2 = new JLabel("Requirement 2:  ");
			center.setBackground(Color.white);
			buttonPanel.setBackground(Color.white);
			textAreaPanel.setBackground(Color.white);
			
			JLabel label = new JLabel("Create new class", SwingConstants.CENTER);
			Font f = new Font("TimesRoman",Font.PLAIN,23);
			label.setFont(f);
			
			courseNameTF = new JTextField();
			int TA_row = 10;
			int TA_col = 30;
			
			requirmentTA= new JTextArea(TA_row, TA_col);
			requirmentTA.setMaximumSize(new Dimension(5, 30));
//			requirment2TF= new JTextField();
			requirmentTA.setWrapStyleWord(true);
			requirmentTA.setLineWrap(true);
			JScrollPane scrollPane = new JScrollPane( requirmentTA );
			courseNameTF.addFocusListener(new JTextFieldHintListener(courseNameTF, defaultClassName));
//			requirment.addFocusListener(new JTextFieldHintListener(requirment, defaultClassRequirements));
//			requirment2TF.addFocusListener(new JTextFieldHintListener(requirment2TF, defaultClassRequirements));
			
			courseNameTF.setBorder(BorderFactory.createLineBorder(blue));
			scrollPane.setBorder(BorderFactory.createLineBorder(blue));
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
			textAreaPanel.add(reqL1, BorderLayout.WEST);
			textAreaPanel.add(scrollPane, BorderLayout.CENTER);
//			rowPanel4.add(reqL2, BorderLayout.WEST);
//			rowPanel4.add(requirment2TF, BorderLayout.CENTER);
//			center.add(add);
			center.add(rowPanel1);
			center.add(rowPanel2);
			center.add(textAreaPanel);
//			center.add(rowPanel4);
			
			center.add(buttonPanel);
			createClassPanel = center;
			centerPanel.add(createClassPanel, "createClassPanel");
			
		}
		
		public void displayCreateClassPanel() {
			centerPage.show(centerPanel, "createClassPanel");
			cleanCreateClassText();
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
			View.this.requirmentTA.setText("");
		}
		
	
	
		
		
		
		public void buildClassListPanel(String[] header, String[][] list) {
			classListPanel = new JPanel(new BorderLayout());
			JPanel classListSubP = new JPanel(new BorderLayout());
			JLabel space = new JLabel("");
			createClassBN = new JButton ("Create a class");
			createClassBN.setVisible(false);
			createClassBN.setBackground(blue);
			createClassBN.addActionListener(View.this);
			classListSubP.setBackground(Color.white);
			classListPanel.setBackground(Color.white);
			classListSubP.add(space, BorderLayout.CENTER);
			classListSubP.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
			classListSubP.add(createClassBN, BorderLayout.EAST);
			classListPanel.add(classListSubP, BorderLayout.NORTH);
			classListPanel.add(buildListTable(header, list), BorderLayout.CENTER);
			classListPanel.setVisible(true);
			centerPanel.add(classListPanel, "classListPanel");
		}
		
		
		public JScrollPane buildListTable(String[] header, String[][] list) {
			classListTable = new JTable(list, header){
				public boolean isCellEditable(int row, int column) {                
		            return false;    
					}
				};	//disable edit cell
//			classListTable.setCellSelectionEnabled(true);  
	      
	        
	        
//	        classListTable.setSelectionModel(new ForcedListSelectionModel());
	        ListSelectionModel select= classListTable.getSelectionModel();  
//	        select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
//	        select.addListSelectionListener(new ListSelectionListener() {  
//	        	public void valueChanged(ListSelectionEvent e) {  
//	        		String Data = null;    
//	        		int selectedRow = classListTable.getSelectedRow();  
//	        		if(selectedRow<0|| selectedRow>classListTable.getRowCount()) {}
//	        		else {
//	        			Data = (String) classListTable.getValueAt(selectedRow, 0);  
//	        			System.out.println("Table element selected is: " + Data);    
//	        		}
//	             }  
//	        	
//	        	
//	         });  
	        
	        

	        classListTable.addMouseMotionListener(new MouseMotionListener() {
	        	int hoveredRow = -1, hoveredColumn = -1;
	            @Override
	            public void mouseMoved(MouseEvent e) {
	                java.awt.Point p = e.getPoint();
	                hoveredRow = classListTable.rowAtPoint(p);
	                hoveredColumn = classListTable.columnAtPoint(p);
	                if(hoveredRow<0||hoveredRow>classListTable.getRowCount()) {}
	                else {
	                	classListTable.setRowSelectionInterval(hoveredRow, hoveredRow);
	                	classListTable.repaint();  
	                }
	            }
	            @Override
	            public void mouseDragged(MouseEvent e) {
	                hoveredRow = hoveredColumn = -1;
	                classListTable.repaint();
	            }
	        });
	        
	        classListTable.setBackground(Color.WHITE);
	        classListTable.getTableHeader().setBackground(Color.WHITE);
	        classListTable.getTableHeader().setReorderingAllowed(false);
	        classListTable.setFillsViewportHeight(true);
			
	        JScrollPane sp=new JScrollPane(classListTable);
	        sp.setViewportView(classListTable);
	        sp.getViewport().setBackground(Color.WHITE);
	        sp.setBackground(Color.white);
	        return sp;
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
			public JButton submitTeachingRequestBN;
			public JButton approveTeachingRequestBN;
			public JButton submitTeacherBN;
			public JButton assignTeacherBN;
			CardLayout submitButtonsLayout = new CardLayout();
			JPanel submitButtonsPanel = new JPanel(submitButtonsLayout);
			Color titleFontColor = new Color(114,114,114);
			private int statusIndex = -1;
			
			
			private List<JLabel> labelList = new ArrayList<JLabel>();
			List<JTextArea> TAList = new ArrayList<JTextArea>();		
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
				

				submitButtonsPanel.setBounds(365, 113, 100, 30);
				
				submitTeacherBN = createBlackButton("Submit");
				submitButtonsPanel.add(submitTeacherBN, "submitTeacherBN");
				approveTeachingRequestBN = createBlackButton("Approve");
				submitButtonsPanel.add(approveTeachingRequestBN, "approveTeachingRequestBN");
				submitTeachingRequestBN = createBlackButton("Submit");
				submitButtonsPanel.add(submitTeachingRequestBN, "submitTeachingRequestBN");
				JPanel emptyP = new JPanel ();
				emptyP.setBackground(Color.WHITE);
				submitButtonsPanel.add(emptyP, "emptyP");		
				operateP.add(submitButtonsPanel);
				
				assignTeacherBN = createBlackButton("Assign");
				assignTeacherBN.setBounds(75, 13, 40, 16);
				assignTeacherBN.setFont(new Font("Arial", Font.BOLD, 8));
				assignTeacherBN.setVisible(false);
				assignTeacherBN.setEnabled(false);
				operateP.add(assignTeacherBN);
				
			
				
				centerPanel.add(classDetailPanel, "classDetailPanel");
			}
			
			
			private JButton createBlackButton(String name) {
				JButton btn = new JButton(name);
				btn.setBackground(new java.awt.Color(0,0,0));
				btn.setBorder(BorderFactory.createEmptyBorder());
				btn.setForeground(Color.WHITE);
				btn.setFont(new Font("Arial", Font.PLAIN, 12));
				return btn;
			}
			
			public String[] getAssignTeacher() {
				String [] s = new String [2];
				s[0] = staffNameL.getText();
				s[1] = requirementTA.getText();			
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
					refinedData[5] = "Require the assignment of a teacher.";
				}
				else if(refinedData[5].equals("Assigned")) {
					statusIndex = 2;
					refinedData[5] = "Require the submission of the teaching request.";
				}
				else if(refinedData[5].equals("Submitted")) {
					statusIndex = 3;
					refinedData[5] = "Require the approvement of the teaching request.";
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
			public void displayAdminsMode(String[] data) {
				updateData(data);
				if(View.this.main.courseDetailPage.statusIndex == 1) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "submitTeacherBN");
					trainingTA.setEditable(true);
					View.this.main.courseDetailPage.assignTeacherBN.setVisible(true);
					View.this.main.courseDetailPage.assignTeacherBN.setEnabled(true);
				}else if(View.this.main.courseDetailPage.statusIndex == 2) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
					trainingTA.setEditable(false);
					View.this.main.courseDetailPage.assignTeacherBN.setVisible(false);
					View.this.main.courseDetailPage.assignTeacherBN.setEnabled(false);
				}else if(View.this.main.courseDetailPage.statusIndex == 3) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");	
					trainingTA.setEditable(false);
					View.this.main.courseDetailPage.assignTeacherBN.setVisible(false);
					View.this.main.courseDetailPage.assignTeacherBN.setEnabled(false);
				}else if(View.this.main.courseDetailPage.statusIndex == 4) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
					trainingTA.setEditable(false);
					View.this.main.courseDetailPage.assignTeacherBN.setVisible(false);
					View.this.main.courseDetailPage.assignTeacherBN.setEnabled(false);
				}
				
			}
			
			public void displayDCMode(String[] data) {
				updateData(data);
				if(View.this.main.courseDetailPage.statusIndex == 1) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}else if(View.this.main.courseDetailPage.statusIndex == 2) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "submitTeachingRequestBN");
				}else if(View.this.main.courseDetailPage.statusIndex == 3) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}else if(View.this.main.courseDetailPage.statusIndex == 4) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}
				
			}
			
			public void displayPDMode(String[] data) {
				updateData(data);
				if(View.this.main.courseDetailPage.statusIndex == 1) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}else if(View.this.main.courseDetailPage.statusIndex == 2) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}else if(View.this.main.courseDetailPage.statusIndex == 3) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "approveTeachingRequestBN");
				}else if(View.this.main.courseDetailPage.statusIndex == 4) {
					View.this.main.courseDetailPage.submitButtonsLayout.show(submitButtonsPanel, "emptyP");
				}
				
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
