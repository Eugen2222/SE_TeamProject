package ModelPackage;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


import java.lang.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public  class Model <T extends PopulatedData>{
	
	//Semester
	private int latestSem = 0;
	private int selectedSem = 0;
	private int newClassID = 0;
	protected List<CDClass> classList;
	protected List<Account> accountList;
	protected List<Semester> semesterList;
	private final String semesterTableTitle = "<SEMESTER TABLE>";
	private final String accountTableTitle = "<USER ACCOUNT TABLE>";
	private final String courseTableTitle = "<CD CLASS TABLE>";
	private String currentUserID = "";
	private String currentUserName = "";
	private ManageFile mf;
	private HashMap<String, String[]> classListFKDataHeader = new HashMap<String, String[]>();
	private List<List<T>> dataBase;
	
	public String[] getUser() {
		String [] s = new String[2];
		s[0] = currentUserID;
		s[1] = currentUserName;
		return s;
	}
	
	
	public Model() {
		semesterList = new LinkedList<Semester>();
		accountList =  new LinkedList<Account>();
		classList = new LinkedList<CDClass>();

		String[] s = new String[] {"teacher", "Name"};
		String[] s1 = new String[] {"classDirector", "Name"};
		classListFKDataHeader.put("ClassDirectorName", s1);
		classListFKDataHeader.put("TeacherName", s);
		dataBase = new LinkedList<List<T>>();
		mf = new ManageFile();
		mf.readFile();
		try {

			newClassID = (this.classList.isEmpty()) ?  1 : 
				(Integer.parseInt(classList.get(classList.size()-1).getID()) + 1);
			
			for(Semester sems : semesterList) {
				latestSem=sems.getSemester();
			}

		}catch(ClassCastException e) {
			e.printStackTrace();
		}
	}
	
	
	//verify user and return the user's type
	public int verify(String [] input) {
		String id = input[0];
		String pw = input[1];
		System.out.println(id+", ");
		for(Account a : accountList) {
			if(id.equals(a.getID())){
				if(pw.equals(a.getPW())) {
					
					this.currentUserID = id;
					this.currentUserName = a.getName();
					return a.getType();
				}
			}
		}
		return -1;
	}
		

	
	public boolean selectSemester(int num) {
		if(num>this.latestSem||num<1) {
			return false;
		}else {
			this.selectedSem=num;

			return true;
		}
	}
	
	public int getlatestSem() {
		return this.latestSem;
	}
	
	public String getSelectedSem() {
		return Integer.toString(this.selectedSem);
	}
	
	
	
	public CDClass findCourse(String CourseID) {
		for(CDClass c : classList) {
			if(c.getID().equals(CourseID)) {
				return c;
			}
		}
		return null;
	}
	
	
	
	public void assignCourseTeacher(String[]s) {
		findCourse(s[0]).assignTeacher(s, accountList, this.currentUserID);
	}
	
	public void submitTeachingRequest(String[]s) {
		findCourse(s[0]).submitTeachingRequest();
		
	}

	public void approveTeachingRequest(String[]s) {
		findCourse(s[0]).approveTeachingRequest(this.currentUserID);
	}
	
	public void withdrawAssignedTeacher(String[]s) {
		findCourse(s[0]).withdrawAssignedTeacher();
	}
	
	public void withdrawTeachingRequest(String[]s) {
		findCourse(s[0]).withdrawTeachingRequest();
	}
	
	
	
	public String[] getCreateClassInfom() {
		String []s =new String[2]; 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		LocalDateTime now = LocalDateTime.now();  
		s[0] = String.format("%04d", newClassID);
		s[1] = dtf.format(now);
		newClassID++; //to avoid providing the same ID to multiple users.
		return s;
	}
	
	public <T extends PopulatedData>  void createClass(String s) {
		List<String> tem = this.getWordInQuotes(s);
		String classID = tem.get(0);
		String className = tem.get(1);
		String classRequirements = tem.get(2);
		
		List<String> cls= new LinkedList<String>();
		cls.add(Integer.toString(selectedSem));
		cls.add(classID);
		cls.add(className);
		cls.add(classRequirements);
		cls.add("Pending");
		cls.add(currentUserID);
		cls.add("");
		cls.add("");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		LocalDateTime now = LocalDateTime.now();  
		cls.add(dtf.format(now));
		cls.add("");
		cls.add("");
		CDClass tClass = new CDClass(cls, dataBase);
		classList.add(tClass);
		tClass.setTableTitle(courseTableTitle);
		System.out.print(dataBase.get(2).get(0).ID);
		System.out.print("\nSuccessfully created a class");
	}
	
	public List<CDClass> getSelectedSemesterClass(){
		List<CDClass> table1 = new LinkedList<>();
		for(CDClass c : classList) {
			if(c.getSemester().equals(Integer.toString(this.selectedSem))) {
				table1.add(c);
			}
		}
		return table1;
	}

	
	public String[][] getStaffListTable(String [] query) {
		if(this.accountList.isEmpty()) {
			return null;
		}else {
			List<String[]> table1 = new LinkedList<String[]>();
			for(int i=0 ; i < accountList.size() ; i++) {
				table1.add(getRowData(query,(T)this.accountList.get(i)));				
			}
			String [][] table2 = table1.toArray(new String[ table1.size()][]);
			return table2;
		}
	}
	
	
	public String[][] getClassListTable(String [] query, String DirectorID) {	
		if(this.classList.isEmpty()) {
			return null;
		}else {
			List<String[]> table1 = new LinkedList<String[]>();
			List<CDClass> list = getSelectedSemesterClass();
			for(CDClass c : list) {
				if(DirectorID == null) {
					table1.add(getRowData(query,c));
				}else if(c.getElement("ClassDirectorID").equals(DirectorID)) {
					table1.add(getRowData(query,c));
				}
			}		
		String [][] table2 = table1.toArray(new String[ table1.size()][]);
		return table2;
		}
	}
	

	public  String[] getClass(String id,String [] query) {
		return getRowData(query, findCourse(id)) ;
	}
	
	
	
	public String[] getRowData(String []query, PopulatedData a) {
		List<String> row = new LinkedList<String>();
		for(int j = 0 ; j< query.length ; j ++) {	
			if(a.getTableHeaderList().containsKey(query[j])){
				row.add(a.getElement(query[j]));
			}else if(classListFKDataHeader.containsKey(query[j])){
				row.add(getFKData(query[j],a));
			}else {
				row.add(null);
				System.out.println("error query: "+ query[j]);
			}
		}
		String [] row2 = row.toArray(new String[ row.size()]);
		return row2;
	}
	
	public String getFKData(String fkWord, PopulatedData OData) {
		String fkObjectKey = classListFKDataHeader.get(fkWord)[0];
		if(OData.getFKList().containsKey(fkObjectKey)) {
			PopulatedData FKData = OData.getFKList().get(fkObjectKey);
			if(FKData!=null) {
				String data = FKData.getElement(classListFKDataHeader.get(fkWord)[1]);
				return data;
			}
		}
		return null;
	}
	
		
	
	
	private class ManageFile{	
		public void readFile() {
			FileReader fr = null;
			try {
				String fN = "data.txt";
				fr = new FileReader(fN);
				Scanner s = new Scanner(fr);
				semesterList= createObjectList(s, Semester.class, semesterTableTitle);
				dataBase.add((List<T>) semesterList);
				accountList= createObjectList(s, Account.class, accountTableTitle);
				dataBase.add((List<T>) accountList);
				classList= createObjectList(s, CDClass.class, courseTableTitle);
				if(classList==null) {
					classList = new LinkedList<CDClass>();
				}
				dataBase.add((List<T>) classList);
				
				for(List<T> l : dataBase) {
					System.out.println(l);
				}
//				for(List<String> a : accountTable) {
//					Account tem = new Account(a);
//					Model.this.accountList.add(tem);
//				}
				
				
//				for(List<String> a : classList) {
//					CDClass tem = new CDClass(a);
//					tem.setClassDirectorName(Model.this.accountList.get(Integer.parseInt(a.get(4))).getName());
//					Model.this.classList.add(tem);
//				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public <T extends PopulatedData>void save() {
			try {
				File file = new File("data.txt");
				if (!file.exists()) {
					file.createNewFile();
				} else {
					file.delete();
					file.createNewFile();

				}
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
//				List<List<T>> dataList = new ArrayList<List<T>>();
//				dataList.add((List<T>)Model.this.semesterList);
//				dataList.add((List<T>)Model.this.accountList);
//				dataList.add((List<T>)Model.this.classList);
				String data = produceTable(dataBase);
				bw.write(data);
				bw.close();
				System.out.println("Data has been saved successfully!");
			} catch (IOException e) {
				System.out.println("Exception occurred:");
				e.printStackTrace();
			}
		}

		private <T extends PopulatedData> List<T> createObjectList(Scanner s, Class<T> T, String key) throws Exception {
			if(!s.hasNext()) {
				System.out.println("Can't find the "+key+" table");
				return null;
			}
			//load data
			String line = s.nextLine();
			List<List<String>> table = new ArrayList<List<String>>() ;

			while (!line.contentEquals(key)&&s.hasNextLine()) {
				line = s.nextLine();
			}
			if(!s.hasNextLine()) {
				throw new Exception("Can't find the "+key+" table");
			}
			line = s.nextLine(); //below headers
			line = s.nextLine(); //below line
			while (s.hasNextLine()) {
				line = s.nextLine();
				if (!line.contains(",")) {
					break;
				} else {
					List<String> itemsList = getWordInQuotes(line);				
					table.add(itemsList);
				
				}
			}
			if(table.isEmpty()) throw new Exception("Can't find any data of the "+ key +" table");
			//Create object
			List<T> list = new LinkedList<>();
			for(List<String> a : table) {
				Constructor<T> constructor;
				try {

					constructor = (Constructor<T>) T.getConstructor(new Class[]{List.class, List.class});
					T object = constructor.newInstance(new Object[]{a, dataBase});
					list.add(object);
					object.setTableTitle(key);
					System.out.println(object);
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
			
			return list;

			
		}
		
		private String encodeString(String s) {
			return  "\""+ s + "\"";
		}
		
	
		
		
		
		public <T extends PopulatedData> String produceTable(List<List<T>> dataList) {
			String database = "";
			for(List<T> subList : dataList) {
				database += "-------------------------------------------------"+"\n";
				database += subList.get(0).getTableTitle()+"\n";
				database += subList.get(0).getTableHeader()+"\n";
				database += "-------------------------------------------------"+"\n";
				for(T e : subList) {
					String row =  "";
					for (String s : e.getRawData()) {
						row += encodeString(s) + ", ";
					}
					database += row +"\n";
				}
			}
			return database;
		}
	}
	
	public void save() {
		this.mf.save();
	}
	
	
	private List<String> getWordInQuotes(String s) {
		Pattern p = Pattern.compile("\"([^\"]*)\"");
		Matcher m = p.matcher(s);
		List<String> tem = new ArrayList<String>();
		while (m.find()) {
			tem.add(m.group(1));
		}
		return tem;
	}
}