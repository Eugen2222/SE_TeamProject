package ModelPackage;
import java.util.*;

public class CDClass extends PopulatedData{
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
//	private List<Integer> teachingRequestListID = new LinkedList<Integer>();

	private Account classDirector;
	private Account teacher;
	
	public <T extends PopulatedData> CDClass(List<String> rowData, List<List<T>> database) {
		super(rowData, database);
		this.semester = rowData.get(0);
		this.classID = rowData.get(1);
		this.name = rowData.get(2);
		setRequirement(rowData.get(3));
		this.setTeacherStatus(rowData.get(4));
		this.classDirectorID = rowData.get(5);
		this.teacherID = rowData.get(6);
		this.training = rowData.get(7).replaceAll("\\$n\\$","\n");
		if(rowData.size()>8) {
			this.date = rowData.get(8);
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
		setupFK(database);
		updateDataList();

		
		
		this.FKList.put("teacher",  this.teacher);	//FK classDirectorID;
		this.FKList.put("classDirector", this.classDirector);	//FK teacherID;
	}
	
	public <T extends PopulatedData> void setupFK(List<List<T>> database) {
		for(List<T> list : database) {
			if(list.get(0) instanceof Account) {
				setDC(list);
				System.out.println(this.classDirector.getName());
				if(this.teacherID!=""||this.teacherID!=null) {
					setTeacher(list);
				}
				break;
			}
		}
	}
	
	
	public <T extends PopulatedData> void setDC(List<T> list) {
		if(list.get(0) instanceof Account) {
			for(T a : list) {
				if(a.getPKID().equals(this.classDirectorID)) {
					this.classDirector = (Account) a;
					break;
				}
			}
		}else {
			System.out.println("Error FK table");
		}
		
	}
	
	public<T extends PopulatedData> void setTeacher(List<T> list) {
		for(T a : list) {
			if(a.getPKID().equals(this.teacherID)) {
				this.teacher = (Account) a;
				break;
			}
		}
		
	}
	
	
	
	public HashMap<String, PopulatedData> getFKList() {
		return this.FKList;
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
			updateDataList();
			return true;
		}
	}
	
	public String getSemester() {

		return this.semester;
	}
	
	
	public <T extends PopulatedData> void assignTeacher(String[] s, List<T> list) {
		this.teacherID=s[1];
		this.training=s[2];
		setTeacher(list);
		this.setTeacherStatus("Assigned");	
		updateDataList();
	}
	
	public void updateDataList() {
		dataList.clear();
		dataList.add(this.semester);
		dataList.add(this.classID);		
		dataList.add(this.name);		
		dataList.add(this.requirement);	
		dataList.add(this.teacherStatus);	
		dataList.add(this.classDirectorID);	
		dataList.add(this.teacherID);		
		dataList.add(this.training);	
		dataList.add(this.date);	
		
	}
//	//setup FK 
//	public void addTeachingRequestID(int id) {
//		teachingRequestListID.add(id);
//	}
//	

//	
//	public  List<Object> getData(List<Integer> listOfHeaderIndex ) {
//		List<Object> s = new ArrayList<Object>() ;
//		for(Integer i : listOfHeaderIndex) {
//			s.add(dataList.get(i));
//		}
//		return s;
//	}
//	
	public  String getElement(String s) {
		if(tableHeaderList.containsKey(s)){
			return dataList.get(tableHeaderList.get(s)).toString();
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
		updateDataList();
	}

	
	public String getName() {
		return this.name;
	}

}
