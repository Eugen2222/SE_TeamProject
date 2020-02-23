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

public class Model {
	
	//Semester
	private int latestSem = 5;
	private int selectedSem = 0;
	
	private List<CDClass> classList = new ArrayList<CDClass>();
	protected List<Account> accountList =  new ArrayList<Account>();
	private String currentUserID = "";
	private String currentUserName = "";
	
	public String[] getUser() {
		String [] s = new String[2];
		s[0] = currentUserID;
		s[1] = currentUserName;
		return s;
	}
	
	
	public Model() {
		ManageFile mf = new ManageFile();
		mf.readFile();
	}
	
	
	//verify user and return the user's type
	public int verify(String [] input) {
		String id = input[0];
		String pw = input[1];
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
		classID = (this.classList.isEmpty()) ?  1 : classList.get(classList.size()).getID() + 1;
		List<String> tem = this.getWordInQuotes(s);
		String className = tem.get(0);
		String classRequirements = tem.get(1);
		
		List<String> cls= new LinkedList<String>();
		cls.add(Integer.toString(selectedSem));
		cls.add(Integer.toString(classID));
		cls.add(className);
		cls.add(classRequirements);
		cls.add(currentUserID);
		cls.add(currentUserName);
		classList.add(new CDClass(cls));
	}
	
	public String[][] getClassDetialList() {
		if(this.classList.isEmpty()) {
			return null;
		}else {
			String [][] tem = new String [classList.size()][];
			for(int i=0 ; i < classList.size() ; i++) {
				tem[i] = this.classList.get(i).getSummary();
			}
			return tem;
		}
	}
	
	
	private class ManageFile{	
		public void readFile() {
			FileReader fr = null;
			int totalLine = 0;
			try {
				String fN = "data.txt";
				fr = new FileReader(fN);
				Scanner s = new Scanner(fr);
				List<List<String>> accountTable	= getDataTable(s, "<USER ACCOUNT TABLE>");
				List<List<String>> classList = getDataTable(s, "<CLASS TABLE>");
				for(List<String> a : accountTable) {
					Account tem = new Account(a);
					Model.this.accountList.add(tem);
				}
				
				for(List<String> a : classList) {
					CDClass tem = new CDClass(a);

					tem.setClassDirectorName(Model.this.accountList.get(Integer.parseInt(a.get(4))).getName());
					Model.this.classList.add(tem);
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
		
		
		private List<List<String>> getDataTable(Scanner s, String key) throws Exception {
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
			return table;
		}
		
	}
	
	
	private List<String> getWordInQuotes(String s) {
		Pattern p = Pattern.compile("\"([^\"]*)\"");
		Matcher m = p.matcher(s);
		List<String> tem = new ArrayList<String>();
		while (m.find()) {
			System.out.println(m.group(1));
			tem.add(m.group(1));
		}
		return tem;
	}

	
	
}
