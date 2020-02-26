package ModelPackage;

import java.util.List;

public interface Populated {
	public List<String> getRawData();
	public List<String> getData();
	public String getTableTitle();
	public String getTableHeader();
	public void setTableTitle(String s);
	public String getFKID();
	public List<Integer> getIndexOfFKList();
	public String getPKID();
	public String getElement(int index);
	public String getName();
}
