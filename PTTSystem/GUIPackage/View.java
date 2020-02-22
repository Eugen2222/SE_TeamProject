package GUIPackage;

public class View {
	
	
	
	
	
	public String[] askLogin() {
		String key [] = new String [2];
		
		// set which swing component to be displayed
		
		
		
		
		key[0] = "007";
		key[1] = "hey baby";
		
		return key;
	}
	
	
	public int askSelectSemester(int latestSemester) {
		// set which swing component to be displayed
		//display currentSemester to user
		//continually ask user to input a correct semester
		int input = 0;
		while(input > latestSemester || input < 1) {
			input = 4; //get semester from user
		}
		
		return input;
	}
	
}
