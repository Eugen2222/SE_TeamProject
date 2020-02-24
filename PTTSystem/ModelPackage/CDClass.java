package ModelPackage;
import java.util.*;
public class CDClass  implements Populated{
	private int classID;
	private String name;
	private List<String> requirementList = new ArrayList<String>(); 
	//for easy to add an edit function, we use an arraylist
	private String semester;
	private String classDirectorID;
	private String classDirectorName;
	private List<Integer> teachingRequestListID = new LinkedList<Integer>();
	private String tableTitle = "";
	
	public CDClass(List<String> s) {
		this.semester = s.get(0);
		this.classID = Integer.parseInt(s.get(1));
		this.name = s.get(2);
		setRequirement(s.get(3));
		this.classDirectorID = s.get(4);
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
	
	public List<String> getData() {
		List<String> s = new LinkedList<String>();
		s.add(this.semester);
		s.add(Integer.toString(this.classID));
		s.add(this.name);
		s.add(this.getRequirement());
		s.add(this.classDirectorID);
		return s;
	}
	
	public String getTableHeader() {
		String s = "Semester, ClassID, Name, Requirements, ClassDirectorID";
		return s;
	}
	
	
	public String getTableTitle() {
		return tableTitle;
	}
	
	public void setTableTitle(String s) {
		this.tableTitle = s;
	}
	
}
