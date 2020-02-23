package ModelPackage;
import java.util.*;
public class CDClass {
	private int classID;
	private String name;
	private List<String> requirementList = new ArrayList<String>(); 
	//for easy to add an edit function, we use an arraylist
	private int semester;
	private int classDirectorID;
	private String classDirectorName;
	private List<Integer> teachingRequestListID = new LinkedList<Integer>();
	
	public CDClass(List<String> s) {
		this.semester = Integer.parseInt(s.get(0));
		this.classID = Integer.parseInt(s.get(1));
		this.name = s.get(2);
		setRequirement(s.get(3));
		this.classDirectorID = Integer.parseInt(s.get(4));
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
	
	//setup FK 
	public void setClassDirectorName(String s) {
		this.classDirectorName = s;
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
