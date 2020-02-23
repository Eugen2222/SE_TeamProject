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
}
