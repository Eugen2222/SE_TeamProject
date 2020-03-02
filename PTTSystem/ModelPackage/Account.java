package ModelPackage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Account extends PopulatedData{
	private String PW = "";
	private String type = "";
	private String name = "";	


	public <T extends PopulatedData>  Account(List<String> rowData, List<List<T>> database) {
		super(rowData, database);
		this.ID = rowData.get(0);
		this.PW = rowData.get(1);
		this.type = rowData.get(2);
		this.name = rowData.get(3);
		tableHeaderList.put("ID",0);
		tableHeaderList.put("Password" ,1);		
		tableHeaderList.put("Type",2);
		tableHeaderList.put("Name",3);		
		this.dataList.add(this.ID );
		this.dataList.add(this.PW );
		this.dataList.add(this.getTypeName() );
		this.dataList.add(this.name);
		tableHeader = "ID, Password, Type, Name";
	}
	
	public String toString() {
		return (ID +", "+ PW +", "+ type +", "+ name);
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getID() {
		return this.ID;
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
			return dataList.get(tableHeaderList.get(s));
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
	

	
}
	
