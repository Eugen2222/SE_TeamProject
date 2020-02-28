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
		view.main.createClassPage.buildCreateClassPanel();

		//add all action listeners
		view.createClassBN.setVisible(true);
		view.createClassBN.addActionListener(this);
		view.createClassOKBN.addActionListener(this);
		view.createClassCBN.addActionListener(this);

		view.main.courseDetailPage.courseDetailWBN.addActionListener(this);
		view.main.courseDetailPage.courseDetailCBN.addActionListener(this);
		view.main.courseDetailPage.courseDetailSBN.addActionListener(this);
		view.myClassListBN.addActionListener(this);
	}
	
	
	
	

	public void selectedCourseStage(String classId){
	       view.main.courseDetailPage.displayDCMode(
	    		   model.getClass(classId, view.main.courseDetailPage.getQuery()), model.getUser()[0]);
	}
	
	

	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource()==view.createClassBN) {
			view.main.createClassPage.updatePage(model.getCreateClassInfom());
			view.main.createClassPage.displayCreateClassPanel();
		}
		
		
		else if(e.getSource() == view.createClassOKBN) {
			String class1 = view.main.createClassPage.getCreateClassString();

			if(class1==null) {
				view.main.createClassPage.cleanCreateClassText();
			}else{
				this.model.createClass(class1);
				this.model.save();
				displayCourseListPage();
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
		else if(e.getSource()==view.myClassListBN) {
			view.main.displayClassListPanel(classListTableQuery, 
					model.getClassListTable(classListTableQuery,model.getUser()[0]));
		}
		else if(e.getSource()==view.main.courseDetailPage.courseDetailWBN) {
			if(view.main.courseDetailPage.withdrawCheck()==0) {
				System.out.println(view.main.courseDetailPage.getClassInform()[0]);
				model.withdrawAssignedTeacher(view.main.courseDetailPage.getClassInform());
				displayCourseListPage();
			}
		}
		
	}
}
