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
	
	public void initialise() {
			
		
		view.barPanel=null;
		view.bar.buildCDBar(model.getUser()[0], model.getUser()[1]);
		
		super.initialise();	
		
		
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
		view.logoutBN.addActionListener(this.logC);
		view.classListBN.addActionListener(this);	
		view.requestListBN.addActionListener(this);	
		view.myClassListBN.addActionListener(this);


	}
	
	
	public void defaultPage(){
		displayMyCourseListPage();
		
	}
	
	
	public void displayMyCourseListPage() {
		view.main.listPage.displayMyCourseListPanel(view.main.listPage.getHeader(), 
				model.getClassListTable(classListTableQuery,model.getUser()[0]));
		this.displayPage = "myCourseListPage";

	}

	public void displayCoursePage(String classId){
	       view.main.courseDetailPage.displayCDMode(
	    		   model.getClass(classId, view.main.courseDetailPage.getQuery()), model.getUser()[0]);
	}
	
	
	public void back() {
		super.back();
		if(this.displayPage.equals("myCourseListPage")) {
			displayMyCourseListPage();

		}
	}
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource()==view.createClassBN) {
			view.main.createClassPage.updatePage(model.getCreateClassInfom());
			view.main.createClassPage.displayCreateClassPanel();
			view.main.createClassPage.cleanText();

		}
		
		
		else if(e.getSource() == view.createClassOKBN) {
			String class1 = view.main.createClassPage.getCreateClassString();

			if(class1==null) {

			}else{
				this.model.createClass(class1);
				this.model.save();
				back();
			}
		}
		
		else if(e.getSource()==view.createClassCBN) {
			view.main.createClassPage.cleanText();
			back();
		}
		else if(e.getSource()==view.main.courseDetailPage.courseDetailSBN) {
			model.submitTeachingRequest(view.main.courseDetailPage.getClassInform());

			displayMyCourseListPage();
		}
		
		else if(e.getSource()==view.main.courseDetailPage.courseDetailCBN) {

			back();
		}
		else if(e.getSource()==view.myClassListBN) {
			displayMyCourseListPage();
		}
		else if(e.getSource()==view.main.courseDetailPage.courseDetailWBN) {
			if(view.main.courseDetailPage.withdrawCheck()==0) {
				model.withdrawAssignedTeacher(view.main.courseDetailPage.getClassInform());
				back();
			}
		}
		
		
	}
}
