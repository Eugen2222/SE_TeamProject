package ModelPackage;
import java.util.*;
public class CDClass  implements Populated{
	private String classID;
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
		this.classID = s.get(1);
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
		if(s.equals("") || s==null) {
				return false;
		}else {
			this.requirement = s;
			return true;
		}
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
	
	
	public  List<String> getSummary() {
		List<String> s = new ArrayList<String>() ;
		s.add(""+this.semester);
		s.add(""+this.classID);
		s.add(this.name);
		s.add(this.requirement);
		s.add(this.teacherStatus);
		s.add(""+this.classDirectorID);
		for(String ste:s) {
			System.out.println(this.requirement+"s");
		}
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
	
	public String getID() {
		return this.classID;
	}
	
	public String getPKID() {
		return this.classID;
	}
	
	public List<String> getData() {
		List<String> s = new LinkedList<String>();
		s.add(this.semester);
		s.add(this.classID);
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
	
	
	//FK
	public String getFKID() {
		return this.classDirectorID;
	}
	
	
	public void setTeacherStatus(String s) {
		if(s.equals("Pending")||s.equals("")||s==null) {
			this.teacherStatus = "Pending";
		}
		if(s.equals("Assigned")) {
			this.teacherStatus = "Assigned";
		}
		if(s.equals("Submited")) {
			this.teacherStatus = "Submited";
		}
		if(s.equals("Approved")) {
			this.teacherStatus = "Approved";
		}
		
	}

	
	public String getName() {
		return this.name;
	}
	
}
