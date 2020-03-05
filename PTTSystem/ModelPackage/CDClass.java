package ModelPackage;
import java.util.*;

public class CDClass extends PopulatedData{
	private String classID;
	private String name;
	
	private String requirement = "";
	
	private String semester;
	private String classDirectorID;//FK
	public 	String teachingStatus;
	private String teacherID;//FK
	private String training;
	private String date;
	private String adminID;
	private String PDID;
	


	private Account classDirector;
	private Account teacher;
	
	//in order to store data in a row the next line symbol should be transfered to other symbol
	//when read data from a row, the special symbol should be recovered to \n
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
		this.date = rowData.get(8);
		this.adminID = rowData.get(9);
		this.PDID = rowData.get(10);
		
		//put each element with a key for query data
		tableHeaderList.put("Semester",0);
		tableHeaderList.put("ClassID",1);
		tableHeaderList.put("Name",2);
		tableHeaderList.put("Requirements",3);
		tableHeaderList.put("TeacherStatus",4);
		tableHeaderList.put("ClassDirectorID",5);
		tableHeaderList.put("TeacherID",6);
		tableHeaderList.put("Trainning",7);
		tableHeaderList.put("Date",8);		
		tableHeaderList.put("AdminID",9);	
		tableHeaderList.put("PDID",10);	
		tableHeader = "Semester, ClassID, Name, Requirements, "
				+ "TeacherStatus, ClassDirectorID, TeacherID, Trainning, Date"
				+ ", AdminID, PDID";
		setupFK(database);
		updateDataList();

		
		
		this.FKList.put("teacher",  this.teacher);	//FK classDirectorID;
		this.FKList.put("classDirector", this.classDirector);	//FK teacherID;
	}
	
	public <T extends PopulatedData> void setupFK(List<List<T>> database) {
		for(List<T> list : database) {
			if(list.get(0) instanceof Account) {
				setDC(list);
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
	
	
	public <T extends PopulatedData> void assignTeacher(String[] s, List<T> list, String adminID) {
		this.teacherID=s[1];
		this.training=s[2];
		this.adminID = adminID;
		setTeacher(list);
		this.setTeacherStatus("Assigned");	
		updateDataList();
	}
	
	public void submitTeachingRequest() {
		this.setTeacherStatus("Submitted");	
	}
	
	public void approveTeachingRequest(String PDID) {
		this.PDID = PDID;
		this.setTeacherStatus("Approved");
		updateDataList();
	}
	
	public void declineAssignedTeacher() {	
		this.setTeacherStatus("Pending");
	}
	
	public void declineTeachingRequest() {	
		this.setTeacherStatus("Assigned");
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
	
	
	
	
	public void updateDataList() {
		dataList.clear();
		dataList.add(this.semester);
		dataList.add(this.classID);		
		dataList.add(this.name);		
		dataList.add(this.requirement);	
		dataList.add(this.teachingStatus);	
		dataList.add(this.classDirectorID);	
		dataList.add(this.teacherID);		
		dataList.add(this.training);	
		dataList.add(this.date);	
		dataList.add(this.adminID);
		dataList.add(this.PDID);
		
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
		s.add(this.teachingStatus);
		s.add(this.classDirectorID);
		s.add(""+this.teacherID);
		//in order to store data in a row the next line symbol should be transfered to other symbol
		s.add(this.training.replaceAll("\\n", "\\$n\\$"));
		s.add(this.date);
		s.add(this.adminID);
		s.add(this.PDID);
		return s;
	}
	
	
	public void setTeacherStatus(String s) {
		if(s.equals("Pending")||s.equals("")||s==null) {
			this.teachingStatus = "Pending";
		}
		if(s.equals("Assigned")) {
			this.teachingStatus = "Assigned";
		}
		if(s.equals("Submitted")) {
			this.teachingStatus = "Submitted";
		}
		if(s.equals("Approved")) {
			this.teachingStatus = "Approved";
		}
		updateDataList();
	}

	
	public String getName() {
		return this.name;
	}

}
