package ControllerPackage;
import ModelPackage.Model;
import GUIPackage.View;

public class CDController extends Controller {

	public CDController(Model model, View view) {
		// TODO Auto-generated constructor stub
		super(model, view);	
	}
	
	public void defaultStage() {
	}
	
	public void createClass() {
		String class1 = "Name:CS, English, Math, French";
		String class2 = "Name:Programming, JAVA, Math, French";
		String class3 = "Name:Playlol, LOL";
		this.model.createClass(class1);
		this.model.createClass(class2);
		this.model.createClass(class3);
		
	}
	
	
}
