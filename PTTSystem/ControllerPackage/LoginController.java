package ControllerPackage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ModelPackage.Model;
import ControllerPackage.*;
import ViewPackage.View;

public class LoginController implements ActionListener{
	View view;
	Model model;
	Controller controller;
	public LoginController(){	
		view = new View();
		model = new Model();
		view.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
               model.save();
               System.exit(0);//cierra aplicacion
            }
        });
		initilise();
	}
		
	public void initilise() {
		view.initialise();
		controller=null;	
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
				
				if(usertype == 1) {
					controller = new CDController(model, view, this);
				}
				else if(usertype == 2) {
					controller = new AController(model, view, this);
				}
				else if(usertype == 3) {
					controller = new PDController(model, view, this);
				}else {
					view.login.cleanLogin();
				}
			}
		}	
		if(e.getSource() == view.logoutBN) {
			if(view.login.logOutCheck()==0) {
				initilise();
				model.save();
			}else {
				view.bar.BNListenerList.get(view.logoutBN.getText()).press=false;
				view.bar.BNListenerList.get(view.logoutBN.getText()).setDefault();
			}
		}
	}	
}

