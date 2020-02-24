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
		view.main.buildClassListPanel(model.getClassDetailHeader(), model.getClassDetialList() );
		view.mainPanel.add(view.classListPanel);
		view.framePanel.add(view.mainPanel, BorderLayout.CENTER);
		view.add(view.framePanel);
		view.pList.add(view.framePanel);
		view.createClassBN.setVisible(true);
		view.createClassBN.addActionListener(this);
	}
	
	public void createClassPage() {
		view.main.buildCreateClassPanel();
		view.main.cleanMainArea();
		view.mainPanel.add(view.createClassPanel, BorderLayout.CENTER);
		view.createClassPanel.setVisible(true);
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
				this.model.save();
			}
		}
		
		if(e.getSource() == view.createClassBN) {
			System.out.println("yo");
			createClassPage();
		}
	}
}
