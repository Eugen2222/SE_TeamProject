package ModelPackage;
import java.util.*;
public class Class {
	private int classID;
	private String name;
	private List<String> requirementList = new ArrayList<String>(); 
	//for easy to add an edit function, we use an arraylist
	private int semester;
	private int classDirectorID;
	private String classDirectorName;
	private List<Integer> teachingRequestListID = new LinkedList<Integer>();
	
	public Class(int semester, int classID, String name, String requirement, int classDirectorID, String classDirectorName) {
		this.classID = classID;
		this.name = name;
		this.semester = semester;
		this.classDirectorID = classDirectorID;
		this.classDirectorName = classDirectorName;
		setRequirement(requirement);
	}
	
	public boolean setRequirement(String s) {
		if(!hasRequirement()) {
			requirementList = new ArrayList<String>(Arrays.asList(s.split("\\s*,\\s*")));
			return true;
		}
		return false;
	}
	
	public boolean hasRequirement() {
		return (!requirementList.isEmpty()) ? true : false;
	}
	
	
	//setup FK 
	public void addTeachingRequestID(int id) {
		teachingRequestListID.add(id);
	}
	
	
	public String [] getSummary() {
		String []s = new String[6];
		s[0] = ""+this.classID;
		s[1] = this.name;
		s[2] = this.getRequirement();
		s[3] = ""+this.classDirectorID;
		s[4] = this.classDirectorName;
		s[5] = ""+this.semester;
		return s;
	}
	
	private String getRequirement() {
		String str="";
		if(hasRequirement()) {
			for(int i = 0 ; i < this.requirementList.size()-1 ; i++) {
				str += this.requirementList.get(i) + ", ";
			}
			str += this.requirementList.get(this.requirementList.size()-1);
			return str;
		}else {
			return str="No Requirement";
		}
	}
	
	public int getID() {
		return this.classID;
	}
	
	
}
