package ControllerPackage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ModelPackage.Model;
import ControllerPackage.*;
import ViewPackage.View;

public class LoginController implements ActionListener{
	View view;
	Model model;
	public LoginController(){	
		view = new View();
		model = new Model();
		initilise();
	}
		
	public void initilise() {
		view.initialise();
		view.login.buildLoginPanel();
		view.login.displayLoginPanel();
		view.loginBN.addActionListener(this);
	}
		
	public void actionPerformed(ActionEvent e) {
		System.out.println("controller");
		if(e.getSource() == view.loginBN) {
			String [] testUser = view.login.getAccount();
			if(testUser!=null) {
				int usertype = model.verify(testUser); //for testing only return 1 Class director
				if(usertype==-1) {
					view.login.cleanLogin();
				}
				if(usertype == 1) {
					CDController controller = new CDController(model, view, this);
				}
				if(usertype == 2) {
					AController controller = new AController(model, view, this);
				}
				if(usertype == 3) {
					PDController controller = new PDController(model, view, this);
				} 
			}
		}	
		if(e.getSource() == view.logoutBN) {
			if(view.login.logOutCheck()==0) {
				initilise();
				model.save();
//				view.login.cleanLogin();
			}	
		}
	}	
}

