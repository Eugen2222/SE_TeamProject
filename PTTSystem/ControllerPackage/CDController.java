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
		createClass();
		view.add(view.framePanel);
		view.pList.add(view.framePanel);
	}
	
	public void createClass() {
		view.main.buildCreateClassPanel();
		view.framePanel.add(view.createClassPanel, BorderLayout.CENTER);
		view.createClassOKBN.addActionListener(this);
		
		
	}
	
	public void setAllInvisibla() {
		
		
		
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource() == view.createClassOKBN) {
			String class1 = view.main.getCreateClassString();
			if(class1==null) {
				view.main.cleanCreateClassText();
			}else{
				this.model.createClass(class1);
			}
			
			String class2 = "\"Programming\", \"JAVA, Math, French\"";
			String class3 = "\"Playlol\", \"LOL\"";

		
		}
	}
}
