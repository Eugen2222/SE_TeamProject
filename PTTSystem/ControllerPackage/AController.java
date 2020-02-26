package ControllerPackage;
import java.awt.BorderLayout;

import GUIPackage.View;
import ModelPackage.Model;

public class AController extends Controller {

	public AController(Model model, View view, LoginController logc) {
		super(model, view, logc);
		// TODO Auto-generated constructor stub
	}

	public void initialisePage() {
		super.initialisePage();
		view.bar.buildABar(model.getUser()[0], model.getUser()[1]);
		view.logoutBN.addActionListener(this.logC);
		view.classListBN.addActionListener(this);	
		view.frame.buildFramePanel(view.barPanel);
		view.frame.displayFramePanel();
		//setup all available pages
		//add all action listeners
		
	}
	
	
	public void selectedCourseStage(String classId){
	       view.main.courseDetailPage.displayAdminsMode(model.getClass(classId));
	}
	
	
	
}
