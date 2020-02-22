package ModelPackage;
import java.util.*;

public class Model {
	
	//Semester
	private int latestSem = 5;
	private int selectedSem = 0;
	
	LinkedList<Class> classList = new LinkedList<Class>();
	
	private int currentUserID = 1010102;
	private String currentUserName = "hey";
	
	
	//verify user and return the user's type
	public int verify(String [] input) {
		String id = input[0];
		String pw = input[1];
		//if(id == id){
		
			//if(pw ==)
		return 1;
	}
	
	
	
	public void selectSemester(int num) {
		this.selectedSem = num;
	}
	
	public int getlatestSem() {
		return this.latestSem;
	}
	
	public void createClass(String s) {
		int classID = 0;
		classID = (this.classList.isEmpty()) ?  1 : classList.getLast().getID() + 1;
		
		String [] str = s.split("\\s*,\\s*");
		String [] temp= str[0].split("Name:");
		String name = temp[1];
		String requirements = "";
		for(int i = 1 ; i< str.length ; i++) {
			requirements += str[i]+",";
		}
		classList.add(new Class(selectedSem, classID, name, requirements, currentUserID, currentUserName));
	}
	
	public String[][] getClassDetialList() {
		if(this.classList.isEmpty()) {
			return null;
		}else {
			String [][] tem = new String [classList.size()][];
			for(int i=0 ; i < classList.size() ; i++) {
				tem[i] = this.classList.get(i).getSummary();
			}
			return tem;
		}
		
	}
	
}
