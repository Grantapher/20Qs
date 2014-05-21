import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class questionTree {
	public static questionNode head;
	private Scanner fReader;	//used to iterate through file
	private Scanner checkReader;	//used to check Format of inputted file
	private static PrintWriter fWriter;	//used to write to file
	private static File file;	//used to store inputted file
	private static URL url;		//used to fetch a file from the internet if neccessary
	
	questionTree(File inFile) {	//accepts file from Main, or recursively
		file = inFile;
		try {
			fReader = new Scanner(file);
			checkReader = new Scanner(file);
			checkAndCreate();
			fReader.close();
		}
		catch(FileNotFoundException e) {
			if(checkReader != null)
				checkReader.close();
			if(fReader != null)
				fReader.close();
			JOptionPane
					.showMessageDialog(
							null,
							"Find your .q20 file or a compatible text file.\nIf you can't find it, a read-only copy will be fetched from the internet.",
							"q20 File not Found",
							JOptionPane.WARNING_MESSAGE);
			fileChoose();
		}
	}
	
	public questionTree(InputStream openStream) {	//creates questionTree from internet
		file = null;
		fReader = new Scanner(openStream);
		checkReader = new Scanner(openStream);
		checkAndCreate();
		fReader.close();
	}
	
	private void checkAndCreate() {		//checks validity of q20/txt file
		if(checkReader.hasNextLine()) {
			String firstLine = checkReader.nextLine();
			if(firstLine.equals("Q:") || firstLine.equals("A:")) {	//if first line is not Q or A, file is not valid
				checkReader.close();
				head = create(null, head);	//if file is valid, create tree
				return;
			}
		}
		checkReader.close();
		fReader.close();
		JOptionPane
				.showMessageDialog(
						null,
						"File is invalid. \nFind a valid .q20 file or a compatible text file.\nIf you can't find one, a read-only copy will be fetched from the internet.",
						"Error", JOptionPane.ERROR_MESSAGE);
		fileChoose();
	}
	
	private questionNode create(questionNode previous, questionNode current) {	//recursive tree building method
		try {
			if(!fReader.hasNextLine())
				return null;
			if(fReader.nextLine().equals("Q:")) {	//creates new questionNode for a question and it's children
				current = new questionNode(fReader.nextLine(), previous);
				current.yes = create(current, current.yes);
				current.no = create(current, current.no);
				return current;
			} else {	//questionNode for an Answer
				return new questionNode(fReader.nextLine(), previous);
			}
		}
		catch(NoSuchElementException e) {	//if there is no next line, returns null
			return null;
		}
	}
	
	public static void write() {	//writes the file back out if it's been modified
		if(file == null) {
			JOptionPane.showMessageDialog(null,
					"Read-only file was imported. Can not write.",
					"Read-Only file", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		try {
			fWriter = new PrintWriter(file, "UTF-8");
			writeOut(head);	//recursive write method
			fWriter.close();
		}
		catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"File not found.\nCan not write.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		catch(UnsupportedEncodingException e) {
			JOptionPane
					.showMessageDialog(
							null,
							"Unsupported Encoding charset \"UTF-8\"\nCan not write.",
							"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private static void writeOut(questionNode current) {//recursive write method, pre-order traversal
		if(current == null)	//un-recurse
			return;
		if(current.isQuestion()) {
			current.cleanUpQuestion();
			fWriter.println("Q:\n" + current.data);//write current
			writeOut(current.yes);	//write left
			writeOut(current.no);	//write right
		} else {
			current.cleanUpAnswer();
			fWriter.println("A:\n" + current.data);	//write an answer
		}
	}
	
	public static void add(String object, String question, boolean answer) {	//adds new question and answer to tree
		questionNode current = QuestionGUI.current;
		current.no = new questionNode(answer ? current.data : object,
				current);
		current.yes = new questionNode(answer ? object : current.data,
				current);
		current.data = question;
	}
	
	private void fileChoose() {
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fc.setFileFilter(new FileNameExtensionFilter("20 Questions Files",
				"q20", "txt"));
		fc.setAcceptAllFileFilterUsed(false);
		int returnVal = fc.showOpenDialog(null);
		if(returnVal == JFileChooser.CANCEL_OPTION) {	//tries to fetch a file from the internet, exits if it is unable to
			try {
				url = new URL(
						"https://docs.google.com/uc?authuser=0&id=0ByEZo2nCK6IySVdFb3FWODUxRW8&export=download");
				new questionTree(url.openStream());
			}
			catch(MalformedURLException e1) {
				JOptionPane.showMessageDialog(null,
						"URL didn't work. ¯\\_(ツ)_/¯", "Error",
						JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
			catch(IOException e1) {
				JOptionPane.showMessageDialog(null,
						"I/O Exception. Check your internet connection.",
						"Error", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
		if(returnVal == JFileChooser.APPROVE_OPTION)
			new questionTree(fc.getSelectedFile());	//creates a new questionTree with selected file
	}
}
