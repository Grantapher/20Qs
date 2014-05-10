import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class questionTree { //not finished
	public static questionNode	head;
	private Scanner				fReader;
	private PrintWriter			fWriter;
	
	questionTree(String filepath) { //not finished
		try {
			fWriter = new PrintWriter(new File(filepath + " new"), "UTF-8");
			fReader = new Scanner(new File(filepath));
			head = add(head);
		}
		catch(FileNotFoundException e) {
			System.out.println("File not Found!");
			System.exit(0);
		}
		catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private questionNode add(questionNode current) {
		if(!fReader.hasNextLine())
			return null;
		if(fReader.nextLine().equals("Q:")) {
			current = new questionNode(fReader.nextLine());
			current.yes = add(current.yes);
			current.no = add(current.no);
			return current;
		} else {
			return new questionNode(fReader.nextLine());
		}
	}
	
	public void writeOut(questionNode current) {//not finished
		if(current == null)
			return;
		if(current.isQuestion()) {
			fWriter.println("Q:\n" + current.data);
			writeOut(current.yes);
			writeOut(current.no);
		} else
			fWriter.println("A:\n" + current.data);
	}
}
