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
	private Scanner fReader;
	private Scanner checkReader;
	private static PrintWriter fWriter;
	private static File file;
	private static URL url;
	
	questionTree(File fileIn) {
		file = fileIn;
		if(file == null)
			file = new File("bigquestion.q20");
		try {
			fReader = new Scanner(file);
			checkReader = new Scanner(file);
			checkAndCreate(head);
			fReader.close();
		}
		catch(FileNotFoundException e) {
			checkReader.close();
			fReader.close();
			JOptionPane
					.showMessageDialog(
							null,
							"File not found. ¯\\_(ツ)_/¯\nFind your .q20 file or a compatible text file.\nIf you can't find it, a read-only copy will be fetched from the internet.",
							"Error", JOptionPane.ERROR_MESSAGE);
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			fc.setFileFilter(new FileNameExtensionFilter(
					"20 Questions Files", "q20", "txt"));
			fc.setAcceptAllFileFilterUsed(false);
			int returnVal = fc.showOpenDialog(null);
			if(returnVal == JFileChooser.CANCEL_OPTION) {
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
					JOptionPane.showMessageDialog(null, "I/O Exception.",
							"Error", JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
			}
			if(returnVal == JFileChooser.APPROVE_OPTION)
				new questionTree(fc.getSelectedFile());
		}
	}
	
	public questionTree(InputStream openStream) {
		file = null;
		fReader = new Scanner(openStream);
		checkReader = new Scanner(openStream);
		create(null, head);
		fReader.close();
	}
	
	private void checkAndCreate(questionNode head) {
		if(checkReader.hasNextLine()) {
			String firstLine = checkReader.nextLine();
			if(firstLine.equals("Q:") || firstLine.equals("A:")) {
				checkReader.close();
				create(null, head);
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
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fc.setFileFilter(new FileNameExtensionFilter("20 Questions Files",
				"q20", "txt"));
		fc.setAcceptAllFileFilterUsed(false);
		int returnVal = fc.showOpenDialog(null);
		if(returnVal == JFileChooser.CANCEL_OPTION) {
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
				JOptionPane.showMessageDialog(null, "I/O Exception.",
						"Error", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
		}
		if(returnVal == JFileChooser.APPROVE_OPTION)
			new questionTree(fc.getSelectedFile());
	}
	
	private questionNode create(questionNode previous, questionNode current) {
		try {
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
		catch(NoSuchElementException e) {
			return null;
		}
	}
	
	public static void write() {
		if(file == null) {
			JOptionPane.showMessageDialog(null,
					"Read-only file was imported. Can not write.",
					"Read-Only file", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		try {
			fWriter = new PrintWriter(file, "UTF-8");
			writeOut(head);
			fWriter.close();
		}
		catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"File not found. ¯\\_(ツ)_/¯\nCan not write.", "Error",
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
