import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class questionTree {
	public static questionNode head;
	private Scanner fReader;
	private static PrintWriter fWriter;
	private static File file;
	private static URL url;
	
	questionTree(File file) {
		try {
			questionTree.file = file;
			fReader = new Scanner(file);
			head = create(null, head);
			fReader.close();
		}
		catch(FileNotFoundException e) {
			JOptionPane
					.showMessageDialog(
							null,
							"Find your .q20 question file or a read-only file will be fetched from the internet.",
							"File Open", JOptionPane.INFORMATION_MESSAGE);
			JFileChooser fc = new JFileChooser(
					System.getProperty("user.dir"));
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setFileFilter(new FileNameExtensionFilter(
					"20 Questions File", "q20"));
			fc.setAcceptAllFileFilterUsed(false);
			int returnVal = fc.showOpenDialog(null);
			if(returnVal == JFileChooser.CANCEL_OPTION)
				try {
					url = new URL(
							"https://docs.google.com/uc?authuser=0&id=0ByEZo2nCK6IySVdFb3FWODUxRW8&export=download");
					new questionTree(url.openStream());
				}
				catch(MalformedURLException e1) {
					JOptionPane.showMessageDialog(null,
							"URL didn't work :/", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
				catch(IOException e1) {
					JOptionPane.showMessageDialog(null, "I/O Exception./",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			if(returnVal == JFileChooser.APPROVE_OPTION)
				new questionTree(fc.getSelectedFile());
		}
	}
	
	public questionTree(InputStream in) {
		fReader = new Scanner(in);
		head = create(null, head);
		fReader.close();
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
		catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"File didn't work. Trying the URL next", "Error",
					JOptionPane.ERROR_MESSAGE);
			try {
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				fWriter = new PrintWriter(new OutputStreamWriter(
						connection.getOutputStream()));
				writeOut(head);
				fWriter.close();
			}
			catch(IOException e1) {
				JOptionPane.showMessageDialog(null,
						"URL didn't work. ¯\\_(ツ)_/¯", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(UnsupportedEncodingException e) {}
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
