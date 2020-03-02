package ModelPackage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class PopulatedData {
	protected HashMap<String, PopulatedData> FKList = new HashMap<String, PopulatedData>();
	protected HashMap<String, Integer> tableHeaderList = new HashMap<String, Integer>();
	protected String tableTitle;
	protected List<String> dataList = new LinkedList<String>();
	protected String tableHeader;
	protected String ID;
	public <T extends PopulatedData> PopulatedData(List<String> s, List<List<T>> database){
		
	}
	
	public List<String> getRawData(){
		return null;
	}
	public HashMap<String, Integer>  getTableHeaderList(){
		return tableHeaderList;
	}
	public String getTableTitle(){
		return (tableTitle!=null) ? this.tableTitle: null;
	}
	public String  getTableHeader(){
		return tableHeader;
	}
	public void setTableTitle(String s){
		this.tableTitle =s;
	}
	public HashMap<String, PopulatedData> getFKList(){
		return FKList;
	}
	
	public String getPKID(){
		return ID;
	}

	public String getName()
	{
		return null;
	}
	public String getElement(String s)
	{
		return null;
	}
}
