package ModelPackage;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CDClass  implements Populated{
	private String classID;
	private String name;
//	private List<String> requirementList = new ArrayList<String>(); 
//	//for easy to add an edit function, we use an arraylist
	
	private String requirement = "";
	
	private String semester;
	private String classDirectorID;//FK
	private String classDirectorName;
	public 	String teacherStatus;
	private String teacherID;//FK
	private String training;
	private Date date;
	private List<Integer> teachingRequestListID = new LinkedList<Integer>();
	private String tableTitle;
	private HashMap<String, Integer> tableHeaderList = new HashMap<String, Integer>();
	private List<Object> rowData = new LinkedList<Object>();
	private List<Object> FKList = new LinkedList<Object>();
	private String tableHeader = "";
	public CDClass(List<String> s) {
		this.semester = s.get(0);
		this.classID = s.get(1);
		this.name = s.get(2);
		setRequirement(s.get(3));
		this.setTeacherStatus(s.get(4));
		this.classDirectorID = s.get(5);
		this.teacherID = s.get(6);
		this.training = s.get(7).replaceAll("\\$n\\$","\n");	
		tableHeaderList.put("Semester",0);
		tableHeaderList.put("ClassID",1);
		tableHeaderList.put("Name",2);
		tableHeaderList.put("Requirements",3);
		tableHeaderList.put("TeacherStatus",4);
		tableHeaderList.put("ClassDirectorID",5);
		tableHeaderList.put("TeacherID",6);
		tableHeaderList.put("Trainning",7);
		tableHeaderList.put("Date",8);		
		tableHeader = "Semester, ClassID, Name, Requirements, "
				+ "TeacherStatus, ClassDirectorID, TeacherID, Trainning, Date";
		updateRowData();
		
		this.FKList.add(classDirectorID);	//FK classDirectorID;
		this.FKList.add(teacherID);	//FK teacherID;
	}
	

	
	public List<Object> getFKList() {
		return FKList;
	}
	
	public String getTableHeader() {
		return tableHeader;
	}
	
	
	
	
	public void submitTeachingRequest() {
		this.setTeacherStatus("Submited");		
	}
	
	public void approveTeachingRequest() {
		this.setTeacherStatus("Approved");
	}
	
	public void withdrawAssignedTeacher() {	
		this.setTeacherStatus("Pending");
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
		}else{

			s=s.replaceAll("\\$n\\$","\n");
			this.requirement = s;
			updateRowData();
			return true;
		}
	}
	
	public String getSemester() {

		return this.semester;
	}
	
	
	public void assignTeacher(String[] s) {
		this.teacherID=s[1];
		this.training=s[2];
		this.setTeacherStatus("Assigned");	
		updateRowData();
	}
	
	public void updateRowData() {
		rowData.clear();
		rowData.add(this.semester);
		rowData.add(this.classID);		
		rowData.add(this.name);		
		rowData.add(this.requirement);	
		rowData.add(this.teacherStatus);	
		rowData.add(this.classDirectorID);	
		rowData.add(this.teacherID);		
		rowData.add(this.training);	
		
	}
	//setup FK 
	public void addTeachingRequestID(int id) {
		teachingRequestListID.add(id);
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
	
	
	public  List<Object> getData(List<Integer> listOfHeaderIndex ) {
		List<Object> s = new ArrayList<Object>() ;
		for(Integer i : listOfHeaderIndex) {
			s.add(rowData.get(i));
		}
		return s;
	}
	
	public  String getElement(String s) {
		if(tableHeaderList.containsKey(s)){
			return rowData.get(tableHeaderList.get(s)).toString();
		}
		return null;
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
	
	public List<String> getRawData() {
		List<String> s = new LinkedList<String>();
		s.add(this.semester);
		s.add(this.classID);
		s.add(this.name);
		s.add(this.requirement.replaceAll("\\n", "\\$n\\$"));
		s.add(this.teacherStatus);
		s.add(this.classDirectorID);
		s.add(""+this.teacherID);
		s.add(this.training.replaceAll("\\n", "\\$n\\$"));
		return s;
	}
	
	public HashMap<String, Integer> getTableHeaderList() {
		return tableHeaderList;
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
		if(s.equals("Submitted")) {
			this.teacherStatus = "Submitted";
		}
		if(s.equals("Approved")) {
			this.teacherStatus = "Approved";
		}
		updateRowData();
	}

	
	public String getName() {
		return this.name;
	}
	
}
