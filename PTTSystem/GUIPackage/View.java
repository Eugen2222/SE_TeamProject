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
		
		Main(){
			centerPage =new CardLayout();
			centerPanel = new JPanel(centerPage);
			centerPanel.setBackground(Color.white);
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
			createClassBN = new JButton("Create a class");
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
		
		
		
		
		public void buildClassDetailPanel() {
			classDetailPanel = new JPanel();
			classDetailPanel.setBackground(Color.WHITE);

			classDetailPanel.setLayout(null);
			centerPanel.add(classDetailPanel, "classDetailPanel");
			
			JLabel lblAdvanceProgramming = new JLabel("Advance Programming");
			lblAdvanceProgramming.setFont(new Font("Arial", Font.PLAIN, 22));
			lblAdvanceProgramming.setBounds(135, 10, 229, 81);
			classDetailPanel.add(lblAdvanceProgramming);
			
			JLabel lblNewLabel = new JLabel("ClassID");
			lblNewLabel.setForeground(SystemColor.controlShadow);
			lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNewLabel.setBounds(45, 70, 53, 29);
			classDetailPanel.add(lblNewLabel);
			
			JLabel lblDirector = new JLabel("Director");
			lblDirector.setForeground(SystemColor.controlShadow);
			lblDirector.setFont(new Font("Arial", Font.PLAIN, 12));
			lblDirector.setBounds(108, 70, 53, 29);
			classDetailPanel.add(lblDirector);
			
			JLabel lblNewLabel_1_1 = new JLabel("ID");
			lblNewLabel_1_1.setForeground(SystemColor.controlShadow);
			lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNewLabel_1_1.setBounds(215, 70, 53, 29);
			classDetailPanel.add(lblNewLabel_1_1);
			
			JLabel lblNewLabel_1 = new JLabel("10");
			lblNewLabel_1.setForeground(Color.BLACK);
			lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(45, 91, 53, 29);
			classDetailPanel.add(lblNewLabel_1);
			
			JLabel lblNewLabel_1_2 = new JLabel("John Wick");
			lblNewLabel_1_2.setForeground(Color.BLACK);
			lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNewLabel_1_2.setBounds(108, 91, 97, 29);
			classDetailPanel.add(lblNewLabel_1_2);
			
			JLabel lblNewLabel_1_2_1 = new JLabel("0001");
			lblNewLabel_1_2_1.setForeground(Color.BLACK);
			lblNewLabel_1_2_1.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNewLabel_1_2_1.setBounds(215, 91, 82, 29);
			classDetailPanel.add(lblNewLabel_1_2_1);
			
			JLabel lblRequirement = new JLabel("Requirement");
			lblRequirement.setForeground(SystemColor.controlShadow);
			lblRequirement.setFont(new Font("Arial", Font.PLAIN, 12));
			lblRequirement.setBounds(45, 118, 89, 21);
			classDetailPanel.add(lblRequirement);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
			panel_1.setBackground(Color.WHITE);
			panel_1.setBounds(45, 250, 415, 118);
			classDetailPanel.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblStuffName = new JLabel("Stuff name");
			lblStuffName.setBounds(23, 11, 76, 14);
			lblStuffName.setForeground(SystemColor.controlShadow);
			lblStuffName.setFont(new Font("Arial", Font.PLAIN, 12));
			panel_1.add(lblStuffName);
			
			JLabel lblNewLabel_1_2_2 = new JLabel("John John John");
			lblNewLabel_1_2_2.setForeground(Color.BLACK);
			lblNewLabel_1_2_2.setFont(new Font("Arial", Font.PLAIN, 12));
			lblNewLabel_1_2_2.setBounds(23, 29, 97, 21);
			panel_1.add(lblNewLabel_1_2_2);
			
			JLabel lblTrainning = new JLabel("Orgnized Trainning");
			lblTrainning.setForeground(SystemColor.controlShadow);
			lblTrainning.setFont(new Font("Arial", Font.PLAIN, 12));
			lblTrainning.setBounds(131, 11, 115, 14);
			panel_1.add(lblTrainning);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(130, 29, 275, 46);

			
			JTextPane txtpnwindowbuildertable_1 = new JTextPane();
			txtpnwindowbuildertable_1.setBackground(SystemColor.control);

			txtpnwindowbuildertable_1.setText("La Flora, o vero Il natal de' fiori (Flora, or The Birth of Flowers) is an opera in a prologue and five acts composed by Marco da Gagliano and Jacopo Peri to a libretto by Andrea Salvadori. It was first performed on 14 October 1628 at the Teatro Mediceo in Florence to celebrate the marriage of Margherita de' Medici and Odoardo Farnese, Duke of Parma.[1] Based on the story of Chloris and Zephyrus in Book V of Ovid's Fasti, Salvadori's libretto contains many allegorical references to the transfer of political power, the beauty of Tuscany,");
			txtpnwindowbuildertable_1.setFont(new Font("Arial", Font.PLAIN, 12));
			txtpnwindowbuildertable_1.setEditable(false);
			scrollPane_1.setVerticalScrollBar(new JScrollBar() {
			    public void setValue(int value) {
			        super.setValue(value);
			    } 
			});
			scrollPane_1.add(txtpnwindowbuildertable_1);
			JButton btnSubmit = new JButton("Submit");
			btnSubmit.setBackground(new Color(255, 255, 255));
			btnSubmit.setFont(new Font("Arial", Font.PLAIN, 12));
			btnSubmit.setBounds(159, 85, 85, 23);
			panel_1.add(btnSubmit);
			
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(45, 138, 415, 81);
			
			
			JTextPane txtpnwindowbuildertable = new JTextPane();

			txtpnwindowbuildertable.setFont(new Font("Arial", Font.PLAIN, 12));
			txtpnwindowbuildertable.setEditable(false);
			txtpnwindowbuildertable.setText("La Flora, o vero Il natal de' fiori (Flora, or The Birth of Flowers) is an opera in a prologue and five acts composed by Marco da Gagliano and Jacopo Peri to a libretto by Andrea Salvadori. It was first performed on 14 October 1628 at the Teatro Mediceo in Florence to celebrate the marriage of Margherita de' Medici and Odoardo Farnese, Duke of Parma.[1] Based on the story of Chloris and Zephyrus in Book V of Ovid's Fasti, Salvadori's libretto contains many allegorical references to the transfer of political power, the beauty of Tuscany,");
			scrollPane.setVerticalScrollBar(new JScrollBar() {
			    /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void setValue(int value) {
			        super.setValue(value);
			    } 
			});
			scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
			scrollPane.getHorizontalScrollBar().setBackground(Color.BLACK);

			scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			    @Override
			    protected void configureScrollBarColors() {
			        this.thumbColor = Color.white;
			    }
			});
			scrollPane.add(txtpnwindowbuildertable);
			
			JLabel lblTeachingRequest = new JLabel("Teaching request");
			lblTeachingRequest.setBounds(45, 226, 134, 14);
			classDetailPanel.add(lblTeachingRequest);
			lblTeachingRequest.setForeground(Color.BLACK);
			lblTeachingRequest.setFont(new Font("Arial", Font.PLAIN, 12));
			panel_1.add(scrollPane_1);
			classDetailPanel.add(scrollPane);
			
		}
		

		public void displayClassDetailPanel() {
			centerPage.show(centerPanel, "classDetailPanel");
			View.this.refresh();
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
