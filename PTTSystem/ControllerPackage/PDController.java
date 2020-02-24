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
	
	public void initialisePage() {
		view.bar.buildCDBar(model.getUser()[0], model.getUser()[1]);
		view.logoutBN.addActionListener(this.logC);
		view.frame.buildFramePanel(view.barPanel);
		view.frame.displayFramePanel();
		//setup all available pages
		view.main.buildClassListPanel(model.getClassDetailHeader(), model.getClassDetialList());
		//add all action listeners
		
	}
}
