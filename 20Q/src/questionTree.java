import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class questionTree {
	public static questionNode	head;
	private Scanner				fReader;
	private static PrintWriter	fWriter;
	private static String		filepath;
	
	questionTree(String filepath) {
		try {
			questionTree.filepath = filepath;
			fReader = new Scanner(new File(filepath));
			head = create(head);
			fReader.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not Found!");
			System.exit(0);
		}
	}
	
	private questionNode create(questionNode current) {
		if(!fReader.hasNextLine())
			return null;
		if(fReader.nextLine().equals("Q:")) {
			current = new questionNode(fReader.nextLine());
			current.yes = create(current.yes);
			current.no = create(current.no);
			return current;
		} else {
			return new questionNode(fReader.nextLine());
		}
	}
	
	public static void write() {
		try {
			fWriter = new PrintWriter(new File(filepath), "UTF-8");
			writeOut(head);
			fWriter.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeOut(questionNode current) {
		if(current == null)
			return;
		if(current.isQuestion()) {
			current.cleanUpQuestion();
			fWriter.println("Q:\n" + current.data);
			writeOut(current.yes);
			writeOut(current.no);
		} else{
			current.cleanUpAnswer();

			fWriter.println("A:\n" + current.data);
	}}
	
	public static void add(String object, String question) {
		questionNode current = QuestionGUI.current;
		current.no = new questionNode(current.data);
		current.yes = new questionNode(object);
		current.data = question;
	}
}
