package ControllerPackage;
import ModelPackage.Model;
import GUIPackage.View;

public class Controller {
	protected Model model;
	protected View view;	
	
	
	public Controller(Model model, View view) {
		// TODO Auto-generated constructor stub
		this.model = model;
		this.view = view;
		selectSemesterStage();
	}
	
	public void selectSemesterStage() {

		int semester = view.askSelectSemester(model.getlatestSem());
		model.selectSemester(semester);
		commandStage();
	}
	
	public void commandStage() {
		System.out.println("2");
	}
}
