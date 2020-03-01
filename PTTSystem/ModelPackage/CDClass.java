package ModelPackage;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CDClass <T extends Populated > implements Populated{
	private String classID;
	private String name;
//	private List<String> requirementList = new ArrayList<String>(); 
//	//for easy to add an edit function, we use an arraylist
	
	private String requirement = "";
	
	private String semester;
	private String classDirectorID;//FK
	public 	String teacherStatus;
	private String teacherID;//FK
	private String training;
	private String date;
	private List<Integer> teachingRequestListID = new LinkedList<Integer>();
	private String tableTitle;
	private Account classDirector;
	private Account teacher;
	private HashMap<String, Integer> tableHeaderList = new HashMap<String, Integer>();
	private List<Object> rowData = new LinkedList<Object>();
	private HashMap<String, T> FKHeaderList = new HashMap<String, T>();
	private String tableHeader = "";
	public CDClass(List<String> s, List<T> fkList) {
		this.semester = s.get(0);
		this.classID = s.get(1);
		this.name = s.get(2);
		setRequirement(s.get(3));
		this.setTeacherStatus(s.get(4));
		this.classDirectorID = s.get(5);
		this.teacherID = s.get(6);
		this.training = s.get(7).replaceAll("\\$n\\$","\n");
		if(s.size()>8) {
			this.date = s.get(8);
		}else {
			date="";
		}
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
		setupFK(fkList);
		updateRowData();

		
		
		this.FKHeaderList.put("teacher", (T) this.teacher);	//FK classDirectorID;
		this.FKHeaderList.put("classDirector", (T) this.classDirector);	//FK teacherID;
	}
	
	public <T extends Populated > void setupFK(List <T> list) {
		if(list.get(0) instanceof Account) {
				setDC((List<Account>)list);
				System.out.println(this.classDirector.getName());
				if(this.teacherID!=""||this.teacherID!=null) {
					setTeacher((List<Account>)list);
				}
		}
	}
	
	
	public void setDC(List <Account> list) {
		for(Account a : list) {
			if(a.getPKID().equals(this.classDirectorID)) {
				this.classDirector = a;
				break;
			}
		}
		
	}
	
	public void setTeacher(List <Account> list) {
		for(Account a : list) {
			if(a.getPKID().equals(this.teacherID)) {
				this.teacher = a;
				break;
			}
		}
		
	}
	
	
	
	public HashMap<String, T> getFKList() {
		return this.FKHeaderList;
	}
	
	public String getTableHeader() {
		return tableHeader;
	}
	
	
	public String getTypeName() {
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
	
	
	public <T extends Populated >void assignTeacher(String[] s, List<T> list) {
		this.teacherID=s[1];
		this.training=s[2];
		setTeacher((List<Account>)list);
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
		rowData.add(this.date);	
		
	}
	//setup FK 
	public void addTeachingRequestID(int id) {
		teachingRequestListID.add(id);
	}
	

	
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
		s.add(this.date);
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
