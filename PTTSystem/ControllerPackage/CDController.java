package ControllerPackage;
import ModelPackage.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ViewPackage.View;
public class CDController extends Controller implements ActionListener{
	
	public CDController(Model model, View view, LoginController logC) {
		// TODO Auto-generated constructor stub
		super(model, view, logC);	
		
		
	}
	
	public void initialisePage() {
		super.initialisePage();		
		
		view.barPanel=null;
		view.bar.buildCDBar(model.getUser()[0], model.getUser()[1]);
		view.logoutBN.addActionListener(this.logC);
		view.classListBN.addActionListener(this);	
		view.frame.buildFramePanel(view.barPanel);
		view.frame.displayFramePanel();
		view.main.buildCreateClassPanel();

		//add all action listeners
		view.createClassBN.setVisible(true);
		view.createClassBN.addActionListener(this);
		view.createClassOKBN.addActionListener(this);
		view.createClassCBN.addActionListener(this);

		view.main.courseDetailPage.courseDetailWBN.addActionListener(this);
		view.main.courseDetailPage.courseDetailCBN.addActionListener(this);
		view.main.courseDetailPage.courseDetailSBN.addActionListener(this);
	}
	
	
	
	

	public void selectedCourseStage(String classId){
	       view.main.courseDetailPage.displayDCMode(model.getClass(classId));
	}
	
	

	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource()==view.createClassBN) {
			view.main.displayCreateClassPanel();
		}
		
		
		else if(e.getSource() == view.createClassOKBN) {
			String class1 = view.main.getCreateClassString();

			if(class1==null) {
				view.main.cleanCreateClassText();
			}else{
				this.model.createClass(class1);
				this.model.save();
				view.main.displayClassListPanel(model.getClassListTableHeader(), model.getClassListTable());
			}
		}
		
		else if(e.getSource()==view.createClassCBN) {
			displayCourseListPage();
		}
		else if(e.getSource()==view.main.courseDetailPage.courseDetailSBN) {
			model.submitTeachingRequest(view.main.courseDetailPage.getClassInform());
			displayCourseListPage();
		}
		
		else if(e.getSource()==view.main.courseDetailPage.courseDetailCBN) {
			displayCourseListPage();
		}
		else if(e.getSource()==view.main.courseDetailPage.courseDetailWBN) {
			if(view.main.courseDetailPage.withdrawCheck()==0) {
				model.withdrawAssignedTeacher(view.main.courseDetailPage.getClassInform());
				displayCourseListPage();
			}
		}
		
	}
}
