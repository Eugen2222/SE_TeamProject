package ModelPackage;

import java.util.LinkedList;
import java.util.List;

public class Account implements Populated{
	private String ID = "";
	private String PW = "";
	private String type = "";
	private String name = "";	
	private String tableTitle = "";
	
	public Account(List<String> a) {
		this.ID = a.get(0);
		this.PW = a.get(1);
		this.type = a.get(2);
		this.name = a.get(3);
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
		s.add(this.type);
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
	
	
	
	
	public String getTableHeader() {
		String s = "ID, Password, Type, Name";
		return s;
	}
	
	public String getTableTitle() {
		return tableTitle;
	}
	
	public void setTableTitle(String s) {
		this.tableTitle = s;
	}

	@Override
	public List<Integer> getIndexOfFKList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getElement(int index) {
		// TODO Auto-generated method stub
		return null;
	}
}
