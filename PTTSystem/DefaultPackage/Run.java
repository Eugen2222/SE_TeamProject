package DefaultPackage;
import ModelPackage.Model;
import ControllerPackage.*;
import GUIPackage.View;

public class Run {
	public static void main (String[] args) {
		
		View view = new View();

		Model model = new Model();
		String [] testUser = view.askLogin();
		int usertype = model.verify(testUser); //for testing only return 1 Class director
		if(usertype == 1) {
			CDController controller = new CDController(model, view);
		}
		if(usertype == 2) {
			AController controller = new AController(model, view);
		}
		if(usertype == 3) {
			PDController controller = new PDController(model, view);
		}
	}
}
