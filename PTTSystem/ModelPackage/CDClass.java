package ModelPackage;
import java.util.*;
public class CDClass  implements Populated{
	private int classID;
	private String name;
//	private List<String> requirementList = new ArrayList<String>(); 
//	//for easy to add an edit function, we use an arraylist
	
	private String requirement = "";
	
	private String semester;
	private String classDirectorID;
	private String classDirectorName;
	private String teacherStatus;
	private List<Integer> teachingRequestListID = new LinkedList<Integer>();
	private String tableTitle = "";
	
	public CDClass(List<String> s) {
		this.semester = s.get(0);
		this.classID = Integer.parseInt(s.get(1));
		this.name = s.get(2);
		setRequirement(s.get(3));
		this.setTeacherStatus(s.get(4));
		this.classDirectorID = s.get(5);
	}
	
//	public boolean setRequirement(String s) {
//		if(!hasRequirement()) {
//			requirementList = new ArrayList<String>(Arrays.asList(s.split("\\s*,\\s*")));
//			return true;
//		}
//		return false;
//	}
//	
//	public boolean hasRequirement() {
//		return (!requirementList.isEmpty()) ? true : false;
//	}
//	
	
	public boolean setRequirement(String s) {
		if(requirement.equals("") || requirement==null) {
				return false;
		}
		this.requirement = s;
		return true;
	}
	
	
	
	
	//setup FK 
	public void addTeachingRequestID(int id) {
		teachingRequestListID.add(id);
	}
	
	//setup FK 
	public void setClassDirectorName(String s) {
		this.classDirectorName = s;
	}
		
	
//	public String [] getSummary() {
//		String []s = new String[6];
//		s[0] = ""+this.classID;
//		s[1] = this.name;
//		s[2] = this.getRequirement();
//		s[3] = ""+this.classDirectorID;
//		s[4] = this.classDirectorName;
//		s[5] = ""+this.semester;
//		return s;
//	}
	
	
	public String [] getSummary() {
		String []s = new String[6];
		s[0] = ""+this.classID;
		s[1] = this.name;
		s[2] = this.requirement;
		s[3] = this.teacherStatus;
		s[4] = ""+this.classDirectorID;
		s[5] = this.classDirectorName;
		s[6] = ""+this.semester;
		return s;
	}
	
	
	
	
//	
//	private String getRequirement() {
//		String str="";
//		if(hasRequirement()) {
//			for(int i = 0 ; i < this.requirementList.size()-1 ; i++) {
//				str += this.requirementList.get(i) + ", ";
//			}
//			str += this.requirementList.get(this.requirementList.size()-1);
//			return str;
//		}else {
//			return str="No Requirement";
//		}
//	}
	
	public int getID() {
		return this.classID;
	}
	
	public List<String> getData() {
		List<String> s = new LinkedList<String>();
		s.add(this.semester);
		s.add(Integer.toString(this.classID));
		s.add(this.name);
		s.add(this.requirement);
		s.add(this.teacherStatus);
		s.add(this.classDirectorID);
		return s;
	}
	
	public String getTableHeader() {
		String s = "Semester, ClassID, Name, Requirements, TeacherStatus, ClassDirectorID";
		return s;
	}
	
	
	public String getTableTitle() {
		return tableTitle;
	}
	
	public void setTableTitle(String s) {
		this.tableTitle = s;
	}
	
	public void setTeacherStatus(String i) {
		if(i.equals("1")) {
			this.teacherStatus = "Pending";
		}
		if(i.equals("2")) {
			this.teacherStatus = "Assigned";
		}
		if(i.equals("3")) {
			this.teacherStatus = "Submited";
		}
		if(i.equals("4")) {
			this.teacherStatus = "Approved";
		}
		
	}
	
}
