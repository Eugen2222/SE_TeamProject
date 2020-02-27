package ControllerPackage;
import GUIPackage.View;
import ModelPackage.Model;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import DefaultPackage.Run;
public class PDController extends Controller {

	public PDController(Model model, View view, LoginController logc) {
		super(model, view, logc);
		// TODO Auto-generated constructor stub
	}
	
	public void initialisePage() {
		super.initialisePage();
		view.bar.buildPDBar(model.getUser()[0], model.getUser()[1]);
		view.classListBN.addActionListener(this);	
		view.logoutBN.addActionListener(this.logC);
		view.frame.buildFramePanel(view.barPanel);
		view.frame.displayFramePanel();
		
		//setup all available pages
		//add all action listeners
		view.main.courseDetailPage.approveTeachingRequestBN.addActionListener(this);
		
	}
	
	
	public void selectedCourseStage(String classId){
	       view.main.courseDetailPage.displayPDMode(model.getClass(classId));
	}
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource()==view.main.courseDetailPage.approveTeachingRequestBN) {
			model.approveTeachingRequest(view.main.courseDetailPage.getClassInform());
			displayCourseListPage();
		}
	}
}
