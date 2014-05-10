import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class questionTree { //not finished
	public questionNode	head;
	public questionNode current;
	private Scanner		fr;
	private PrintWriter	fw;
	
	questionTree(String filepath) { //not finished
		try {
			fr = new Scanner(new File(filepath));
			fw = new PrintWriter(new File(filepath));
		}
		catch(FileNotFoundException e) {
			System.out.println("File not Found!");
			System.exit(0);
		}
	}
	
	private void add(){
		
	}
	
	public void writeOut(){//not finished
		if(current.isQuestion())
			fw.println("Q:\n" + current.data);
		else
			fw.println("A:\n" + current.data);
	}
}
