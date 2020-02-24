package ModelPackage;

import java.util.List;

public interface Populated {
	public List<String> getData();
	public String getTableTitle();
	public String getTableHeader();
	public void setTableTitle(String s);
	public String getFKID();
	public String getPKID();
	public String getName();
}
