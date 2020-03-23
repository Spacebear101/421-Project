import java.util.ArrayList;
import java.util.Iterator;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		ArrayList <String> conn = new ArrayList <String>();
		ArrayList <String> all = new ArrayList <String>();
		
		conn.add("a");
		conn.add("b");
		conn.add("d");
		all.add("d");
		all.add("e");
		//if (all.contains(conn)){
		Iterator connIt = conn.iterator();
		while (connIt.hasNext()) {
			Object temp = connIt.next();
			if (all.contains(temp)) {
				System.out.println(temp.toString());
			}//end of if statement
		}//end of while loop
		*/
		
		String test = "a";
		int num = ((int) test.charAt(0)-97) ;
		System.out.println(num);
	}

}
