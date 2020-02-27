package DefaultPackage;
import ModelPackage.Model;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ControllerPackage.LoginController;
import GUIPackage.View;

public class Run {
	static int  i = 1;
	public static void main (String[] args) {
		LoginController start = new LoginController();
		s(i);
		System.out.println(i);
	}
	

	
	public  static void s(int y)
	{
		y++;
	}
	
	
}
