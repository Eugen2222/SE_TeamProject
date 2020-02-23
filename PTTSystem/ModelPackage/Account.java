package ModelPackage;

import java.util.List;

public class Account {
	private String ID = "";
	private String PW = "";
	private String type = "";
	private String name = "";	
	protected Account(List<String> a) {
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
	
	public String getID() {
		return this.ID;
	}
	
	public String getPW() {
		return this.PW;
	}
	
	public int getType(){
		if(this.type.equals("CD")) {
			return 1;
		}
		else if(this.type.equals("A")) {
			return 2;
		}
		else if(this.type.equals("PD")) {
			return 3;
		}
		return -1;
	}
}
