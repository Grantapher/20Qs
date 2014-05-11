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
			head = create(null, head);
			fReader.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not Found!");
			System.exit(0);
		}
	}
	
	private questionNode create(questionNode previous, questionNode current) {
		if(!fReader.hasNextLine())
			return null;
		if(fReader.nextLine().equals("Q:")) {
			current = new questionNode(fReader.nextLine(), previous);
			current.yes = create(current, current.yes);
			current.no = create(current, current.no);
			return current;
		} else {
			return new questionNode(fReader.nextLine(), previous);
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
		} else {
			current.cleanUpAnswer();
			fWriter.println("A:\n" + current.data);
		}
	}
	
	public static void add(String object, String question, boolean answer) {
		questionNode current = QuestionGUI.current;
		current.no = new questionNode(answer ? current.data : object,
				current);
		current.yes = new questionNode(answer ? object : current.data,
				current);
		current.data = question;
	}
}
