package DefaultPackage;

import ModelPackage.Model;
import ControllerPackage.*;
import GUIPackage.View;

public class test {

	public test() {}

	public static void main (String[] args) {
		View view = new View();
		Model model = new Model();
		String [] testUser = view.askLogin();
		CDController controller = new CDController(model, view);
		//controller.selectSemesterStage();
		controller.createClass();
		String[][] t = model.getClassDetialList();
		for(int i = 0 ; i<t.length;i++) {
			for(int j = 0 ; j <t[i].length ; j++) {
				System.out.print(t[i][j]+ ", ");
			}
		System.out.println("");
		}
	}

}
