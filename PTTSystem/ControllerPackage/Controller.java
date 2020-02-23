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
		view.loginPanel.setVisible(false);
		view.remove(view.loginPanel);
		System.out.println(this);
		selectSemesterPage();
	}
	
	public void selectSemesterPage() {
		view.semester.buildSemesterPanel();
		view.add(view.semester.getSemesterPanel());
		view.semesterBN.addActionListener(this);
		view.semester.diplayLatestSemester(model.getlatestSem());
		view.semesterPanel.setVisible(true);
		view.pList.add(view.semester.getSemesterPanel());
	}
	
	public void defaultPage() {
	
	}
	

	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == view.semesterBN) {
			int semester = view.semester.getSelecetedSemester();

			if(model.selectSemester(semester)) {
				view.cleanAllPanel();
				this.defaultPage();
			}
		}
		
		
		if(e.getSource() == view.courseList) {
			view.courseList.setBackground(Color.white);
			view.courseList.setBorder(BorderFactory.createLineBorder(Color.white));
			view.teacherList.setBackground(view.blue);
			view.teacherList.setBorder(BorderFactory.createLineBorder(view.blue));
			view.requestList.setBackground(view.blue);
			view.requestList.setBorder(BorderFactory.createLineBorder(view.blue));
		}
		
		
		
		
		
		
		if(e.getSource() == view.courseList) {
			view.courseList.setBackground(Color.white);
			view.courseList.setBorder(BorderFactory.createLineBorder(Color.white));
			view.teacherList.setBackground(view.blue);
			view.teacherList.setBorder(BorderFactory.createLineBorder(view.blue));
			view.requestList.setBackground(view.blue);
			view.requestList.setBorder(BorderFactory.createLineBorder(view.blue));
		}

		if(e.getSource() == view.teacherList) {
			view.teacherList.setBackground(Color.white);
			view.teacherList.setBorder(BorderFactory.createLineBorder(Color.white));
			view.courseList.setBackground(view.blue);
			view.courseList.setBorder(BorderFactory.createLineBorder(view.blue));
			view.requestList.setBackground(view.blue);
			view.requestList.setBorder(BorderFactory.createLineBorder(view.blue));
			
		}
		
		if(e.getSource() == view.requestList) {
			view.requestList.setBackground(Color.white);
			view.requestList.setBorder(BorderFactory.createLineBorder(Color.white));
			view.teacherList.setBackground(view.blue);
			view.teacherList.setBorder(BorderFactory.createLineBorder(view.blue));
			view.courseList.setBackground(view.blue);
			view.courseList.setBorder(BorderFactory.createLineBorder(view.blue));
			
		}
		

	
	}

	
	
}
