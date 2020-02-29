package ControllerPackage;
import ViewPackage.View;
import ModelPackage.Model;
import java.awt.event.ActionEvent;

public class PDController extends Controller {

	public PDController(Model model, View view, LoginController logc) {
		super(model, view, logc);
		// TODO Auto-generated constructor stub
	}
	
	public void initialise() {

		view.bar.buildPDBar(model.getUser()[0], model.getUser()[1]);
		super.initialise();
		view.classListBN.addActionListener(this);	
		view.logoutBN.addActionListener(this.logC);
		view.frame.buildFramePanel(view.barPanel);
		view.frame.displayFramePanel();
		
		//setup all available pages
		//add all action listeners
		view.main.courseDetailPage.courseDetailWBN.addActionListener(this);
		view.main.courseDetailPage.courseDetailCBN.addActionListener(this);
		view.main.courseDetailPage.courseDetailSBN.addActionListener(this);
		view.requestListBN.addActionListener(this);
		displayTeachingRequestListPage();
	}
	
	public void defaultPage() {
	
	}
	
	public void displayCoursePage(String classId){
	       view.main.courseDetailPage.displayPDMode(
	    		   model.getClass(classId, view.main.courseDetailPage.getQuery()));
	}
	
	
//	public void displayTeachingRequestListPage() {
//		view.main.displayTeachingRequestListPanel(classListTableQuery, 
//				model.getClassListTable(classListTableQuery,null));
//		
//	}
//	
	
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if(e.getSource()==view.main.courseDetailPage.courseDetailSBN) {
			model.approveTeachingRequest(view.main.courseDetailPage.getClassInform());
			back();
		}
		
		if(e.getSource()==view.main.courseDetailPage.courseDetailCBN) {
			back();
		}
		if(e.getSource()==view.main.courseDetailPage.courseDetailWBN) {
			if(view.main.courseDetailPage.withdrawCheck()==0) {
				model.withdrawTeachingRequest(view.main.courseDetailPage.getClassInform());
				back();
			}
			
		}
		
	}
}
