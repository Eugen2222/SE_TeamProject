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
		
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == view.semesterBN) {
			int semester = view.semester.getSelecetedSemester();

			if(model.selectSemester(semester)) {
				this.initialisePage();
			}
		}
		
		
		
		
		
		

		if(e.getSource() == view.teacherList) {
			view.teacherList.setBackground(Color.white);
			view.teacherList.setBorder(BorderFactory.createLineBorder(Color.white));
			view.classListBN.setBackground(view.blue);
			view.classListBN.setBorder(BorderFactory.createLineBorder(view.blue));
			view.requestList.setBackground(view.blue);
			view.requestList.setBorder(BorderFactory.createLineBorder(view.blue));
			
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
