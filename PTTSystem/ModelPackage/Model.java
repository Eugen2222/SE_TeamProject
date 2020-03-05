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
	
	private int selectedSem = 0;
	private int newClassID = 0;
	protected List<CDClass> classList; 
	protected List<Account> accountList;
	protected List<Semester> semesterList;
	
	 //the defined name to search and write table in the text file.
	private final String semesterTableTitle = "<SEMESTER TABLE>"; 
	private final String accountTableTitle = "<USER ACCOUNT TABLE>";
	private final String courseTableTitle = "<CD CLASS TABLE>";
	
	
	private String currentUserID = "";
	private String currentUserName = "";
	private ManageFile mf;	//inner class to deal with text file
	//the list store all the queries instruction regarding to foreign keys in the class table.
	private HashMap<String, String[]> classListFKDataHeader = new HashMap<String, String[]>();
	private List<List<T>> dataBase; // a database contains three type of object list 
	
	
	
	
	public Model() {
		semesterList = new LinkedList<Semester>();
		accountList =  new LinkedList<Account>();
		classList = new LinkedList<CDClass>();
		
		//Set up queries name and the related foreign keys in CDClass.
		//This tells model to reach foreign key data in CDClass.  
		String[] s = new String[] {"teacher", "Name"};
		String[] s1 = new String[] {"classDirector", "Name"};
		classListFKDataHeader.put("ClassDirectorName", s1);
		classListFKDataHeader.put("TeacherName", s);
		dataBase = new LinkedList<List<T>>();
		mf = new ManageFile();
		//inner class read text file
		mf.readFile();
		// initialise classID for creating a course.
		newClassID = (this.classList.isEmpty()) ?  1 : 
		(Integer.parseInt(classList.get(classList.size()-1).getID()) + 1);

	}
	
	
	//provide current user to view
		public String[] getUser() {
			String [] s = new String[2];
			s[0] = currentUserID;
			s[1] = currentUserName;
			return s;
		}
	
	//verify user and return the user's type
	public int verify(String [] input) {
		String id = input[0];
		String pw = input[1];
		
		//loop for account list to search account
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
		this.selectedSem=num;
		return true;
	}
	
	//provide all available semester in database
	public int[] getSemester() {
		int [] sem = new int [semesterList.size()];
		int i = 0;
		for(Semester s :semesterList)
		{
			sem[i] = s.getSemester();
			i++;
		}
		return sem;
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
	
	
	//set up teacher variable in CDClass and point to the account 
	public void assignCourseTeacher(String[]s) {
		findCourse(s[0]).assignTeacher(s, accountList, this.currentUserID);
	}
	
	public void submitTeachingRequest(String[]s) {
		findCourse(s[0]).submitTeachingRequest();
		
	}

	public void approveTeachingRequest(String[]s) {
		findCourse(s[0]).approveTeachingRequest(this.currentUserID);
	}
	
	public void declineAssignedTeacher(String[]s) {
		findCourse(s[0]).declineAssignedTeacher();
	}
	
	public void declineTeachingRequest(String[]s) {
		findCourse(s[0]).declineTeachingRequest();
	}
	
	
	//provide system data to view if user want to create a course
	public String[] getCreateClassInfom() {
		String []s =new String[2]; 
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
		LocalDateTime now = LocalDateTime.now();  
		s[0] = String.format("%04d", newClassID);
		s[1] = dtf.format(now);
		newClassID++; //to avoid providing the same ID to multiple users.
		return s;
	}
	
	//receive class data from view and
	//set up class direction pointing to Account in CDClass
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
		System.out.print("\nSuccessfully created a class");
	}
	
	
	//filter course in the selective semester
	public List<CDClass> getSelectedSemesterClass(){
		List<CDClass> table1 = new LinkedList<>();
		for(CDClass c : classList) {
			if(c.getSemester().equals(Integer.toString(this.selectedSem))) {
				table1.add(c);
			}
		}
		return table1;
	}

	//provide staff table to view
	public String[][] getStaffListTable(String [] query) {
		if(this.accountList.isEmpty()) {
			return null;
		}else {
			List<String[]> table1 = new LinkedList<String[]>();
			for(int i=0 ; i < accountList.size() ; i++) {
				//get the query data of each account in the list 
				table1.add(getRowData(query,(T)this.accountList.get(i)));				
			}
			String [][] table2 = table1.toArray(new String[ table1.size()][]);
			return table2;
		}
	}
	
	//provide course table to view
	//if input a director id, filter the courses created by this class director.
	public String[][] getClassListTable(String [] query, String DirectorID) {	
		if(this.classList.isEmpty()) {
			return null;
		}else {
			List<String[]> table1 = new LinkedList<String[]>();
			List<CDClass> list = getSelectedSemesterClass();
			for(CDClass c : list) {
				//get the query data of each CDClass in the list 
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
	
	// provide the data of a single class 
	public  String[] getClass(String id,String [] query) {
		return getRowData(query, findCourse(id)) ;
	}
	
	
	//a method frequently used in model
	//receive a query String array from view,
	//and get the corresponding data in the object by a hashmap
	public String[] getRowData(String []query, PopulatedData a) {
		List<String> row = new LinkedList<String>();
		for(int j = 0 ; j< query.length ; j ++) {	
			if(a.getTableHeaderList().containsKey(query[j])){
				row.add(a.getElement(query[j]));
				
				//if it is a query for a data in the foreign key data
				//reach the foreign key data
			}else if(classListFKDataHeader.containsKey(query[j])){
				row.add(getFKData(query[j],a));
			}else {
				//if a unknown query is inputed
				row.add(null);
				System.out.println("error query: "+ query[j]);
			}
		}
		String [] row2 = row.toArray(new String[ row.size()]);
		return row2;
	}
	
	
	//a method frequently used in model
	//receive a query String from view,
	//and get the corresponding data via hashmaps in the Model class and data classes
	public String getFKData(String fkWord, PopulatedData OData) {
		String fkObjectKey = classListFKDataHeader.get(fkWord)[0];
		// if the data contains a foreign key header
		if(OData.getFKList().containsKey(fkObjectKey)) {
			//get the foreign key object
			PopulatedData FKData = OData.getFKList().get(fkObjectKey);
			//if the foreign key object exists 
			//(maybe a class has not been assigned a teacher)
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
				
				//read the SQL tables in a line order and create an object list
				semesterList= createObjectList(s, Semester.class, semesterTableTitle);
				//put the list into the database. 
				dataBase.add((List<T>) semesterList);
				accountList= createObjectList(s, Account.class, accountTableTitle);
				dataBase.add((List<T>) accountList);
				//the class list need to use account list in the dataBase to set up 
				//its foreign keys' references
				classList= createObjectList(s, CDClass.class, courseTableTitle);
				//at the beginning of the semester, there maybe have no course in database
				if(classList==null) {
					classList = new LinkedList<CDClass>();
				}
				dataBase.add((List<T>) classList);
				
				//print out data loaded in the database
				for(List<T> l : dataBase) {
					System.out.println(l);
				}
				
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
				//transfer all data lists into a string
				String data = produceTable(dataBase);
				bw.write(data);
				bw.close();
				System.out.println("Data has been saved successfully!");
			} catch (IOException e) {
				System.out.println("Exception occurred:");
				e.printStackTrace();
			}
		}

		// build an data list according to a given class
		private <T extends PopulatedData> List<T> createObjectList(Scanner s, Class<T> T, String key) throws Exception {
			//if there is no line
			if(!s.hasNext()) {
				System.out.println("Can't find the "+key+" table");
				return null;
			}
			//load data
			String line = s.nextLine();
			//a table stores a lists of parameters for each object from the file 
			List<List<String>> table = new ArrayList<List<String>>() ;
			//find the data table's title
			while (!line.contentEquals(key)&&s.hasNextLine()) {
				line = s.nextLine();
			}
			//if below table's title there is no line
			if(!s.hasNextLine()) {
				throw new Exception("Can't find the "+key+" table");
			}
			line = s.nextLine(); //below headers
			line = s.nextLine(); //below line
			//get the row data with "," and capture the data between each pair of quotes
			while (s.hasNextLine()) {
				line = s.nextLine();
				if (!line.contains(",")) {
					break;
				} else {
					List<String> itemsList = getWordInQuotes(line);				
					table.add(itemsList);
				
				}
			}
			if(table.isEmpty())System.out.println("Can't find any data of the "+ key +" table");
			//Create object
			List<T> list = new LinkedList<>();
			
			//loop all the parameters in the list
			for(List<String> a : table) {
				Constructor<T> constructor;
				try {
					//find the constructor with (list, list) parameters
					constructor = (Constructor<T>) T.getConstructor(new Class[]{List.class, List.class});
					//create an object by input the parameters
					T object = constructor.newInstance(new Object[]{a, dataBase});
					//add the new object to the list
					list.add(object);
					//set up the table title for each object for saving data.
					object.setTableTitle(key);
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
		
		//save lists of data into one string
		public <T extends PopulatedData> String produceTable(List<List<T>> dataList) {
			String database = "";
			for(List<T> subList : dataList) {
				if(subList!=null&&!subList.isEmpty()) {
					
					//the table tile area
					database += "-------------------------------------------------"+"\n";
					database += subList.get(0).getTableTitle()+"\n";
					database += subList.get(0).getTableHeader()+"\n";
					database += "-------------------------------------------------"+"\n";
					//the data area
					for(T e : subList) {
						String row =  "";
						for (String s : e.getRawData()) {
							//each element in a row will be separated by "" and,
							row += encodeString(s) + ", ";
						}
						//move to next line
						database += row +"\n";
					}
				}
			}
			return database;
		}
	}
	
	// put a pair of quote between a element
	private String encodeString(String s) {
		return  "\""+ s + "\"";
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