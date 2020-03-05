package ModelPackage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


// this abstract class enable model to save data, create object and query data
//without write different methods
public abstract class PopulatedData {
	//a fk reference hashmap corresponding to query
	protected HashMap<String, PopulatedData> FKList = new HashMap<String, PopulatedData>();
	//a element hashmap corresponding to the key
	//the key and String data are separated since String is immutable
	protected HashMap<String, Integer> tableHeaderList = new HashMap<String, Integer>();
	protected String tableTitle;
	//a key list corresponding to query	
	protected List<String> dataList = new LinkedList<String>();
	protected String tableHeader;
	protected String ID;
	public <T extends PopulatedData> PopulatedData(List<String> s, List<List<T>> database){
		
	}
	//provide raw data to be stored in text file
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
	
	//provide query data
	public String getElement(String s)
	{
		return null;
	}
}
