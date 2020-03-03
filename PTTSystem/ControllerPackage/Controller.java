package ControllerPackage;
import ViewPackage.View;
import java.util.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ModelPackage.Model;
public abstract class Controller implements ActionListener{
	protected View view;
	protected Model model;
	protected LoginController logC;
	protected String []classListTableQuery;
	protected String displayPage = "";
	
	
	
	public Controller(Model model, View view, LoginController logC) {
		// TODO Auto-generated constructor stub
		this.model = model;
		this.view = view;
		this.logC = logC;
		String []tem = {"ClassID","Name","TeacherStatus","ClassDirectorID", "TeacherID", "Date"};
		classListTableQuery = tem;
		displayPage= "";
		selectSemesterPage();
		
	}
	
	public void selectSemesterPage() {
		view.semester.buildSemesterPanel(model.getlatestSem());
		view.semesterBN.addActionListener(this);
		view.semester.displayLatestSemester();
		view.semester.displaySemesterPanel();
	}
	

	public void initialise() {
		//setup all available pages
		view.main.courseDetailPage.buildClassDetailPanel();
		view.main.listPage.buildClassListPanel(null, null);

		view.classListTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					int selectedRow = view.classListTable.rowAtPoint(evt.getPoint());
		//		    int selectedCol = view.classListTable.columnAtPoint(evt.getPoint());
				    if (selectedRow >= 0) {
				       String classId = view.classListTable.getValueAt(selectedRow, 0).toString();
				       displayCoursePage(classId);

				    }
				}
		});
		
		view.main.courseDetailPage.normalPageBBN.addActionListener(this);
		defaultPage();
	}
	
	public void defaultPage() {
		displayCourseListPage();

	}
	
	
	public void displayCoursePage(String classId){
	       view.main.courseDetailPage.displayNormalMode(
	    		   model.getClass(classId, view.main.courseDetailPage.getQuery()));
//			displayPage = "CoursePage";
	}
	
	public void displayCourseListPage() {
		view.main.listPage.displayClassListPanel(view.main.listPage.getHeader(), 
				model.getClassListTable(classListTableQuery,null));
		displayPage = "CourseListPage";

	}
	
	public void displayTeachingRequestListPage() {
		view.main.listPage.displayTeachingRequestListPanel(view.main.listPage.getHeader(), 
				model.getClassListTable(classListTableQuery,null));
		displayPage = "TeachingRequestListPage";

	}
	
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == view.semesterBN) {
			int semester = view.semester.getSelecetedSemester();
			if(model.selectSemester(semester)) {
				this.initialise();
			}else {
				view.semester.displayLatestSemester();
			}
		}
		
		if(e.getSource() == view.classListBN) {
			displayCourseListPage();
		}
		
		if(e.getSource() == view.main.courseDetailPage.normalPageBBN) {
			back();
		}
		if(e.getSource()==view.requestListBN) {
			displayTeachingRequestListPage();

		}
	
	}

	public void back() {
		if(this.displayPage.equals("TeachingRequestListPage")) {
			displayTeachingRequestListPage();


		}
		else if(this.displayPage.equals("CourseListPage")) {
			displayCourseListPage();

		}
	}
	
}
