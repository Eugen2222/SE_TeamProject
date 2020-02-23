package GUIPackage;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener; 
import javax.swing.JTextField; 

public class JTextFieldHintListener implements FocusListener{	
	private String hintText;	
	private JTextField textField;	
	
	
	public JTextFieldHintListener(JTextField jTextField,String hintText) {		
		this.textField = jTextField;		
		this.hintText = hintText;		
		jTextField.setText(hintText);  
	} 	
		
	
	@Override	
	public void focusGained(FocusEvent e) {		
		String temp = textField.getText();		
		if(temp.equals(hintText)) {			
			textField.setText("");			
			textField.setForeground(Color.BLACK);		
			}			
		} 	
	
	@Override	
	public void focusLost(FocusEvent e) {			
		String temp = textField.getText();		
		if(temp.equals("")) {			
			textField.setForeground(Color.BLACK);			
			textField.setText(hintText);		
		}			
	} 

}
