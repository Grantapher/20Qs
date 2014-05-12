import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class questionTree {
	public static questionNode	head;
	private Scanner				fReader;
	private static PrintWriter	fWriter;
	private static File			file;
	
	questionTree(File file) {
		try {
			questionTree.file = file;
			fReader = new Scanner(file);
			head = create(null, head);
			fReader.close();
		}
		catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(
					null,
					new String("Could not find the file:\n"
							+ System.getProperty("user.dir")
							+ file.getPath())
							+ "\nFind your question file.", "Error!",
					JOptionPane.ERROR_MESSAGE);
			JFileChooser fc = new JFileChooser(
					System.getProperty("user.dir"));
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setFileFilter(new FileNameExtensionFilter("20 Questions File",
					"q20"));
			int returnVal = fc.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				new questionTree(fc.getSelectedFile());
			}
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
			fWriter = new PrintWriter(file, "UTF-8");
			writeOut(head);
			fWriter.close();
		}
		catch(FileNotFoundException e) {}
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
