package ModelPackage;

public class Model {
	private int latestSem = 1;
	
	
	
	
	
	//verify user and return the user's type
	public int verify(String [] input) {
		String id = input[0];
		String pw = input[1];
		//if(id == id){
		
			//if(pw ==)
		return 1;
	}
	
	
	
	public boolean selectSemester(int num) {
		return (num > 10 && num < 1) ? false : true;
	}
	
	public int getlatestSem() {
		return this.latestSem;
	}
}
