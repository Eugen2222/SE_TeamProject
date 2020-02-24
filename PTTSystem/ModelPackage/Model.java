package ModelPackage;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


import java.lang.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Model {
	
	//Semester
	private int latestSem = 5;
	private int selectedSem = 0;
	
	private List<CDClass> classList = new ArrayList<CDClass>();
	protected List<Account> accountList =  new ArrayList<Account>();
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
	
	public void createClass(String s) {
		int classID = 0;
		classID = (this.classList.isEmpty()) ?  1 : classList.get(classList.size()-1).getID() + 1;
		List<String> tem = this.getWordInQuotes(s);
		String className = tem.get(0);
		String classRequirements = tem.get(1);
		
		List<String> cls= new LinkedList<String>();
		cls.add(Integer.toString(selectedSem));
		cls.add(Integer.toString(classID));
		cls.add(className);
		cls.add(classRequirements);
		cls.add(currentUserID);
		cls.add("1");
		CDClass tClass = new CDClass(cls);
		tClass.setClassDirectorName(currentUserName);
		classList.add(tClass);
		for(int i=0 ; i < tClass.getSummary().length ; i++) {
			System.out.print(tClass.getSummary()[i]+ ", ");
		}
		System.out.print("\nSuccessfully created a class");
	}
	
	
	public String[] getClassDetailHeader() {
		String [] header = {"ID", "Name", "Requirements", "DirectorID", "DirectorName", "Semester"};
		return header;
	}
	public String[][] getClassDetialList() {
		if(this.classList.isEmpty()) {
			return null;
		}else {
			String [][] tem = new String [classList.size()][];
			for(int i=0 ; i < tem.length ; i++) {
				tem[i] = this.classList.get(i).getSummary();
			}
			return tem;
		}
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
		
		public <T extends Populated> void save() {
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
//					System.out.println(T.getConstructor(new Class[]{List.class}));
//					System.out.print(i);
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
			for(T t: list) {
				System.out.println(t);
			}
			
			return list;

			
		}
		
		private String encodeString(String s) {
			if(s!=null) {
				return (!s.equals("")) ? "\""+ s + "\"" : "";
			}
			return null;
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
					for (String s : e.getData()) {
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