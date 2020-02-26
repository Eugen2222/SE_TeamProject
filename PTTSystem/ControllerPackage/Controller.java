package ControllerPackage;

import java.awt.Color;
import GUIPackage.View;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import GUIPackage.View;
import ModelPackage.Model;
import DefaultPackage.Run;
public class Controller implements ActionListener{
	protected View view;
	protected Model model;
	protected LoginController logC;

	
	public Controller(Model model, View view, LoginController logC) {
		// TODO Auto-generated constructor stub
		this.model = model;
		this.view = view;
		this.logC = logC;
		selectSemesterPage();
		
	}
	
	public void selectSemesterPage() {
		view.semester.buildSemesterPanel();
		view.semesterBN.addActionListener(this);
		view.semester.displayLatestSemester(model.getlatestSem());
		view.semester.displaySemesterPanel();
	}
	

	public void initialisePage() {

		
		//setup all available pages
		view.main.courseDetailPage.buildClassDetailPanel();
		view.main.buildClassListPanel(model.getClassListTableHeader(), model.getClassListTable());

		view.classListTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					int selectedRow = view.classListTable.rowAtPoint(evt.getPoint());
		//		    int selectedCol = view.classListTable.columnAtPoint(evt.getPoint());
				    if (selectedRow >= 0) {
				       String classId = view.classListTable.getValueAt(selectedRow, 1).toString();
				       System.out.println(selectedRow);
				       selectedCourseStage(classId);

				    }
				}
			});
		view.main.displayClassListPanel(model.getClassListTableHeader(), model.getClassListTable());
	}
	
	public void selectedCourseStage(String classId){
	       view.main.courseDetailPage.displayNormalMode(model.getClass(classId));
	}
	
	
	
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == view.semesterBN) {
			int semester = view.semester.getSelecetedSemester();

			if(model.selectSemester(semester)) {
				this.initialisePage();
			}
		}
		
		if(e.getSource() == view.classListBN) {
			view.main.displayClassListPanel(model.getClassListTableHeader(), model.getClassListTable());
		}
		
		
		if(e.getSource() == view.requestList) {
			view.requestList.setBackground(Color.white);
			view.requestList.setBorder(BorderFactory.createLineBorder(Color.white));
			view.teacherList.setBackground(view.blue);
			view.teacherList.setBorder(BorderFactory.createLineBorder(view.blue));
			view.classListBN.setBackground(view.blue);
			view.classListBN.setBorder(BorderFactory.createLineBorder(view.blue));
			
		}
		

	
	}

	
	
}
