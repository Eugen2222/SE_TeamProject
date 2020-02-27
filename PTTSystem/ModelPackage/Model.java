package ModelPackage;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


import java.lang.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public  class Model <T extends Populated>{
	
	//Semester
	private int latestSem = 5;
	private int selectedSem = 0;
	
	protected List<CDClass> classList;
	protected List<Account> accountList;
	private List<LinkedList<T>> database;
	
	private String currentUserID = "";
	private String currentUserName = "";
	private ManageFile mf;
	
	
	
	public String[] getUser() {
		String [] s = new String[2];
		s[0] = currentUserID;
		s[1] = currentUserName;
		return s;
	}
	
	
	public Model() {

		accountList =  new LinkedList<Account>();
		classList = new LinkedList<CDClass>();
		database = new LinkedList<LinkedList<T>>();
		try {
			database.add((LinkedList<T>) accountList);
			database.add((LinkedList<T>) classList);

		}catch(ClassCastException e) {
			e.printStackTrace();
		}
		
		
		mf = new ManageFile();
		mf.readFile();
		
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
		findCourse(s[0]).assignTeacher(s);
	}
	
	public void submitTeachingRequest(String[]s) {
		findCourse(s[0]).setTeacherStatus("Submitted");
		
	}
	
	public void approveTeachingRequest(String[]s) {
		findCourse(s[0]).setTeacherStatus("Approved");
	}
	
	public void withdrawAssignedTeacher(String[]s) {
		findCourse(s[0]).setTeacherStatus("Pending");
	}
	
	public void withdrawTeachingRequest(String[]s) {
		findCourse(s[0]).setTeacherStatus("Assigned");
	}
	
	
	
	
	
	public void createClass(String s) {
		int classID = 0;
		classID = (this.classList.isEmpty()) ?  1 : (Integer.parseInt(classList.get(classList.size()-1).getID()) + 1);
		List<String> tem = this.getWordInQuotes(s);
		String className = tem.get(0);
		String classRequirements = tem.get(1);
		
		List<String> cls= new LinkedList<String>();
		cls.add(Integer.toString(selectedSem));
		cls.add(Integer.toString(classID));
		cls.add(className);
		cls.add(classRequirements);
		cls.add("Pending");
		cls.add(currentUserID);
		cls.add("");
		cls.add("");
		CDClass tClass = new CDClass(cls);
		tClass.setClassDirectorName(currentUserName);
		classList.add(tClass);
		
		System.out.print("\nSuccessfully created a class");
	}
	
	
	public String[] getClassListTableHeader() {
		String [] header = {"Semester", "ID", "Name", "Requirements", "Teacher Status", "DirectorID", "Director","TeacherID", "Teacher", "Trainning" };
		return header;
	}
	
	public String[] getStaffListTableHeader() {
		String [] header = {"ID", "Type", "Name" };
		return header;
	}
	
	public String[][] getStaffListTable() {
		if(this.accountList.isEmpty()) {
			return null;
		}else {
			String [][] tem = new String [accountList.size()][];
			for(int i=0 ; i < tem.length ; i++) {
				List<String> tem2 = new LinkedList<String>() ;
				tem2 = accountList.get(i).getData();
				tem2.remove(1); //remove password
				tem[i] = tem2.toArray(new String[ tem2.size()]);
			}
			return tem;
		}
	}
	
	
	public String[][] getClassListTable() {
		if(this.classList.isEmpty()) {
			return null;
		}else {
			List<String[]> tem = new LinkedList<String[]>();
			for(int i=0 ; i < classList.size() ; i++) {
				if(this.classList.get(i).getSemester().equals(Integer.toString(this.selectedSem))) {
					tem.add(this.addNamesOfForiegnKeys((T)this.classList.get(i), (List<T>)this.accountList)); //append CD's name;
				}
			}
			String [][] tem2 = tem.toArray(new String[ tem.size()][]);
			return tem2;
		}
	}
	


	
	
	
	public  String[] getClass(String  s) {
		String [] tem=null;
		if(this.classList.isEmpty()) {
			return tem;
		}else {
			for(CDClass c : classList) {
				if(s.equals(c.getPKID())) {
					tem = addNamesOfForiegnKeys((T)c , (List<T>)accountList);
				}
			}
		}
		return tem;
	}
	
	
	
	
	
	
	
	
	public String [] addNamesOfForiegnKeys(T a, List<T> FKTable) {
		
		List<String> tem = a.getData();
		int countForIndexIncrease = 0;
		for(int index : a.getIndexOfFKList()) {
			if(a.getElement(index).equals(null)||a.getElement(index).equals("")||a.getElement(index).equals(" ")) {
				tem.add(index+countForIndexIncrease+1, "");					
				countForIndexIncrease ++;
			}else {				
				for(T fkObj : FKTable) {
						if(a.getElement(index).equals(fkObj.getPKID())) {	
						tem.add(index+countForIndexIncrease+1, fkObj.getName());					
						countForIndexIncrease ++;
						break;
						//insert director's name and teacher's name. 
					}
				}
			}
		}
		
		String[] tem2 = tem.toArray(new String[ tem.size()]);
		return tem2;
	}
	
	private class ManageFile{	
		public void readFile() {
			FileReader fr = null;
			try {
				String fN = "data.txt";
				fr = new FileReader(fN);
				Scanner s = new Scanner(fr);
				accountList= createObjectList(s, Account.class, "<USER ACCOUNT TABLE>");
				classList= createObjectList(s, CDClass.class, "<CD CLASS TABLE>");
				
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
		
		public void save() {
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
				List<List<T>> dataList = new ArrayList<List<T>>();
				dataList.add((List<T>)Model.this.accountList);
				dataList.add((List<T>)Model.this.classList);
				String data = populateTable(dataList);
				bw.write(data);
				bw.close();
				System.out.println("Test log has been created successfully!");
			} catch (IOException e) {
				System.out.println("Exception occurred:");
				e.printStackTrace();
			}
		}

		private <T extends Populated> List<T> createObjectList(Scanner s, Class<T> T, String key) throws Exception {
			
			//load data
			String line = s.nextLine();
			List<List<String>> table = new ArrayList<List<String>>() ;

			while (!line.contentEquals(key)&&s.hasNextLine()) {
				line = s.nextLine();
			}
			if(!s.hasNextLine()) {
				throw new Exception("Can't find the "+key+" table");
			}
			line = s.nextLine(); //below col name
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
			List<T> list = new ArrayList<>();
			for(List<String> a : table) {
				Constructor<T> constructor;
				try {

					constructor = (Constructor<T>) T.getConstructor(new Class[]{List.class});
					T object = constructor.newInstance(new Object[]{a });
					list.add(object);
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
		
		private String encodeString(String s) {
			return  "\""+ s + "\"";
		}
		
	
		
		
		
		public <T extends Populated> String populateTable(List<List<T>> dataList) {
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