package ControllerPackage;
import java.awt.event.ActionEvent;

import ViewPackage.View;
import ModelPackage.Model;

public class AController extends Controller {
	private String classId;
	public AController(Model model, View view, LoginController logc) {
		super(model, view, logc);
		// TODO Auto-generated constructor stub
	}

	public void initialise() {

		view.bar.buildABar(model.getUser()[0], model.getUser()[1]);
		
		super.initialise();
		view.frame.buildFramePanel(view.barPanel);
		view.frame.displayFramePanel();
		view.main.selectTeacherPage.buildSelectTeacherPanel();
		//setup all available pages
		//add all action listeners
		view.main.courseDetailPage.assignTeacherBN.addActionListener(this);
		view.main.courseDetailPage.courseDetailCBN.addActionListener(this);
		view.main.courseDetailPage.courseDetailSBN.addActionListener(this);
		view.logoutBN.addActionListener(this.logC);
		view.classListBN.addActionListener(this);	
		view.requestListBN.addActionListener(this);	
	}
	
	
	
	public void displayCoursePage(String classId){
	       view.main.courseDetailPage.displayAdminsMode(model.getClass(classId, view.main.courseDetailPage.getQuery()));
	       this.classId=classId;
	}
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource()==view.main.courseDetailPage.assignTeacherBN) {
			view.main.selectTeacherPage.displaySelectTeacherPanel(model.getStaffListTable(
					view.main.selectTeacherPage.getQuery()), 
					model.getSelectedSem());
		}
		else if(e.getSource()==view.main.courseDetailPage.courseDetailSBN) {
			model.assignCourseTeacher(view.main.courseDetailPage.getAssignTeacher());
			back();
		}
		
		else if(e.getSource()==view.main.courseDetailPage.courseDetailCBN) {
			back();
		}
		
		
	}
}
