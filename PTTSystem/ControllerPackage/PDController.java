package ControllerPackage;
import GUIPackage.View;
import ModelPackage.Model;

import java.awt.BorderLayout;

import DefaultPackage.Run;
public class PDController extends Controller {

	public PDController(Model model, View view, LoginController logc) {
		super(model, view, logc);
		// TODO Auto-generated constructor stub
	}
	
	public void defaultPage() {
		view.bar.buildPDBar(model.getUser()[0], model.getUser()[1]);
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
}
