package ModelPackage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Account implements Populated{
	private String ID = "";
	private String PW = "";
	private String type = "";
	private String name = "";	
	private String tableTitle = "";
	private List<String> rowData = new LinkedList<String>();
	private String tableHeader = "";
	private HashMap<String, Integer> tableHeaderList = new HashMap<String, Integer>();
	public Account(List<String> a) {
		this.ID = a.get(0);
		this.PW = a.get(1);
		this.type = a.get(2);
		this.name = a.get(3);
		tableHeaderList.put("ID",0);
		tableHeaderList.put("Password" ,1);		
		tableHeaderList.put("Type",2);
		tableHeaderList.put("Name",3);		
		rowData.add(this.ID );
		rowData.add(this.PW );
		rowData.add(this.getTypeName() );
		rowData.add(this.name);
		tableHeader = "ID, Password, Type, Name";
	}
	
	public String toString() {
		return (ID +", "+ PW +", "+ type +", "+ name);
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPKID() {
		return this.ID;
	}
	
	public String getID() {
		return this.ID;
	}
	
	public String getFKID() {
		return null;
	}
	
	protected String getPW() {
		return this.PW;
	}
	
	protected int getType(){
		if(this.type.equals("CD")) {
			return 1;
		}
		else if(this.type.equals("A")) {
			return 2;
		}
		else if(this.type.equals("PD")) {
			return 3;
		}
		else if(this.type.equals("S")) {
			return 0;
		}
		return -1;
	}
	
	public List<String> getData() {
		List<String> s = new LinkedList<String>();
		s.add(this.ID);
		s.add(this.PW);
		s.add(this.getTypeName());
		s.add(this.name);
		return s;
	}
	
	public List<String> getRawData() {
		List<String> s = new LinkedList<String>();
		s.add(this.ID);
		s.add(this.PW);
		s.add(this.type);
		s.add(this.name);
		return s;
	}
	public  String getElement(String s) {
		if(tableHeaderList.containsKey(s)){
			return rowData.get(tableHeaderList.get(s));
		}
		return null;
	}
	
	public String getTypeName() {
		if(this.type ==null) {
			return null;
		}else {
			if(type.equals("CD")){
				return "Class Director";
			}
			else if(type.equals("A")){
				return "Administrator";
			}
			else if(type.equals("PD")){
				return "PTT Director";
			}else if(type.equals("S")){
				return "Staff";
			}else {
				return "Unknowed";
			}
			
		}
	}
	
	
	public String getTableTitle() {
		return tableTitle;
	}
	
	public void setTableTitle(String s) {
		this.tableTitle = s;
	}

	@Override
	public List<Object> getData(List<Integer> l) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Integer> getTableHeaderList() {
		// TODO Auto-generated method stub
		return tableHeaderList;
	}

	@Override
	public List<Object> getFKList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableHeader() {
		// TODO Auto-generated method stub
		return tableHeader;
	}
}
	
