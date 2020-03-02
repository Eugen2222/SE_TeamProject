package ModelPackage;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public interface Populated {
	public List<String> getRawData();

	public String getTableTitle();
	public HashMap<String, Integer>  getTableHeaderList();
	public String  getTableHeader();
	public void setTableTitle(String s);
	public String getFKID();
	public  HashMap<String, Populated> getFKList();
	public String getPKID();

	public String getName();
	public String getElement(String s);
}
