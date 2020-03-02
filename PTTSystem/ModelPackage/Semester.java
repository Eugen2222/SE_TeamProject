package ModelPackage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Semester implements Populated {
	private int semester;
	private Date beginningDate;
	private Date endDate;
	private String pattern = "yyyy/MM/dd";
	private String tableHeader = "";
	private String tableTitle;
	private HashMap<String, Integer> tableHeaderList = new HashMap<String, Integer>();

	// Create an instance of SimpleDateFormat used for formatting 
	// the string representation of date according to the chosen pattern
	DateFormat df = new SimpleDateFormat(pattern);
	public <T extends Populated> Semester(List<String> list, List<T> fkList) {
		this.semester = Integer.parseInt(list.get(0));
		try {
			this.beginningDate = df.parse(list.get(1));
			this.endDate = df.parse(list.get(2));
			
			
			
			
			tableHeaderList.put("Semester",0);
			tableHeaderList.put("BeginningDate",1);
			tableHeaderList.put("EndDate",2);
			
			tableHeader = "Semester, BeginningDate, EndDate, ";

			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// TODO Auto-generated constructor stub
	}
	public int getSemester() {
		
		return this.semester;
	}
	@Override
	public List<String> getRawData() {
		// TODO Auto-generated method stub
		List<String> s = new LinkedList<String>();
		s.add(Integer.toString(this.semester));
		s.add(df.format(this.beginningDate));
		s.add(df.format(this.endDate));
		return s;
	}

	@Override
	public String getTableTitle() {
		// TODO Auto-generated method stub
		return tableTitle;
	}

	@Override
	public HashMap<String, Integer> getTableHeaderList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableHeader() {
		
		return tableHeader;
	}

	@Override
	public void setTableTitle(String s) {
		// TODO Auto-generated method stub
		this.tableTitle= s;
	}

	@Override
	public String getFKID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Populated> getFKList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPKID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getElement(String s) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString(){
		String s ="";
		s += Integer.toString(this.semester) + ", ";
		s += df.format(this.beginningDate)+ ", ";
		s += df.format(this.endDate);
		return s;
	}
}
