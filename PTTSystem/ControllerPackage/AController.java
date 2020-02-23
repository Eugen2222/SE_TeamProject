package ControllerPackage;
import java.awt.BorderLayout;

import GUIPackage.View;
import ModelPackage.Model;

public class AController extends Controller {

	public AController(Model model, View view, LoginController logc) {
		super(model, view, logc);
		// TODO Auto-generated constructor stub
	}

	public void defaultPage() {
		view.bar.buildABar(model.getUser()[0], model.getUser()[1]);
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
