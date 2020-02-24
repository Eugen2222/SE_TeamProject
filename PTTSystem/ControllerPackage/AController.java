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

	}
	
}
