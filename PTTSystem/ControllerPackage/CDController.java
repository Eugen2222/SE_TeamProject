package ControllerPackage;
import ModelPackage.Model;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
	
	public void initialisePage() {
		view.bar.buildCDBar(model.getUser()[0], model.getUser()[1]);
		view.logoutBN.addActionListener(this.logC);
		view.frame.buildFramePanel(view.barPanel);
		view.frame.displayFramePanel();
		//setup all available pages
		view.main.buildCreateClassPanel();
		view.main.buildClassListPanel(model.getClassDetailHeader(), model.getClassDetialList());
		view.main.buildClassDetailPanel();
		//add all action listeners
		view.createClassBN.setVisible(true);
		view.createClassBN.addActionListener(this);
		view.createClassOKBN.addActionListener(this);
		view.createClassCBN.addActionListener(this);
		view.classListBN.addActionListener(this);
		
			view.classListTable.addMouseListener(new java.awt.event.MouseAdapter() {
			    @Override
			    public void mouseClicked(java.awt.event.MouseEvent evt) {
			        int row = view.classListTable.rowAtPoint(evt.getPoint());
			        int col = view.classListTable.columnAtPoint(evt.getPoint());
			        if (row >= 0 && col >= 0) {
			        	view.main.displayClassDetailPanel();
			        }
			    }
			});
//		
		view.main.displayClassListPanel(model.getClassListHeader(), model.getClassDetialList());
	}
	
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource() == view.createClassOKBN) {
			String class1 = view.main.getCreateClassString();
			System.out.println(class1.length());

			if(class1==null) {
				view.main.cleanCreateClassText();
			}else{
				this.model.createClass(class1);
				this.model.save();
				view.main.displayClassListPanel(model.getClassListTableHeader(), model.getClassListTable());
			}
		}
		
		if(e.getSource() == view.classListBN) {
			view.main.displayClassListPanel(model.getClassListTableHeader(), model.getClassListTable()));
		}
		
		if(e.getSource()==view.createClassBN) {
			view.main.displayCreateClassPanel();
		}
		if(e.getSource()==view.createClassCBN) {
			view.main.displayClassListPanel(model.getClassListTableHeader(), model.getClassListTable()));
		}
		
		
		
	}
}
