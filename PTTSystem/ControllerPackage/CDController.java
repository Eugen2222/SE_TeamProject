package ControllerPackage;
import ModelPackage.Model;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DefaultPackage.Run;
import GUIPackage.View;

public class CDController extends Controller implements ActionListener{
	
	public CDController(Model model, View view, LoginController logC) {
		// TODO Auto-generated constructor stub
		super(model, view, logC);	
		
	}
	
	public void defaultPage() {
		view.bar.buildCDBar(model.getUser()[0], model.getUser()[1]);
		view.frame.buildMainPanel();
		view.framePanel.add(view.barPanel, BorderLayout.WEST);
		view.barPanel.setVisible(true);
		view.logoutBN.addActionListener(this.logC);
		System.out.println("add"+view.logoutBN.getActionListeners()[0]);
		view.main.buildCreateClassPanel();
		view.framePanel.add(view.createClassPanel, BorderLayout.CENTER);
		view.add(view.framePanel);
		view.pList.add(view.framePanel);
	}
	
	public void createClass() {
		String class1 = "\"CS\", \"English, Math, French\"";
		String class2 = "\"Programming\", \"JAVA, Math, French\"";
		String class3 = "\"Playlol\", \"LOL\"";
		this.model.createClass(class1);
		this.model.createClass(class2);
		this.model.createClass(class3);
		
	}
	
	public void setAllInvisibla() {
		
		
		
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
	}
}
