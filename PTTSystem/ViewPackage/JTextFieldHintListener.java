package ViewPackage;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;
import javax.swing.JTextField; 

public class JTextFieldHintListener implements FocusListener{	
	private String hintText;	
	private JTextField textField=null;	
	private JTextArea TA=null;
	
	
	public JTextFieldHintListener(JTextField jTextField,String hintText) {		
		this.textField = jTextField;		
		this.hintText = hintText;		
		jTextField.setText(hintText);  
	} 	
	
	
	public JTextFieldHintListener(JTextArea TA,String hintText) {		
		this.TA = TA;		
		this.hintText = hintText;
		TA.setText(hintText);  
	} 	
	
	
	
	
	@Override	
	public void focusGained(FocusEvent e) {		
		if(textField==null) {
			String temp = TA.getText();
			if(temp.equals(hintText)) {			
				TA.setText("");			
				TA.setForeground(new Color (20,20,20));		
			}		
		}else {
			String temp = textField.getText();
			if(temp.equals(hintText)) {			
				textField.setText("");			
				textField.setForeground(new Color (20,20,20));		
			}	
		}
	} 	
	
	@Override	
	public void focusLost(FocusEvent e) {	
		if(textField==null&&TA!=null) {
			String temp = TA.getText();		
			if(temp.equals("")) {			
				TA.setForeground(new Color (20,20,20));			
				TA.setText(hintText);
			}
		}else {
			String temp = textField.getText();		
			if(temp.equals("")) {			
			textField.setForeground(new Color (20,20,20));			
			textField.setText(hintText);		
			}			
		}		
	} 
	
	
}
