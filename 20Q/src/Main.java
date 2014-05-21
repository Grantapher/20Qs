/**
 * 	@author Grant Toepfer, Will Smith, Cameron Heil
 * 
 * 	@description 
 * 	This program plays 20 questions with the user.
 * 	The user must think of an object/thing and the program
 * 	will ask a question about the object/thing the user is 
 * 	thinking of. the user will then have the option to answer
 * 	yes or no. After the user answers, the program will ask another
 * 	question and will eventually make a guess at qhat the object/thing
 * 	is. If the item is guessed correctly, the user will have the option
 * 	of playing again. If the question is guessed incorrectly, the program
 * 	will ask the user to enter the object/thing into a field and to enter
 * 	a question that will lead to that object, thus building a database
 * 	of questions and answers.
 * 	
 *  @class CSE 223
 *  @assignment HW3
 *  @date 5/21/2014
 */
import java.awt.Container;
import java.awt.EventQueue;
import java.io.File;
import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {	//sets look and feel, creates the questionTree, and then moves on to the first GUI
		try {			//get Windows look and feel if possible
			for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info
							.getClassName());
					break;
				}
			}
		}
		catch(ClassNotFoundException ex) {}
		catch(InstantiationException ex) {}
		catch(IllegalAccessException ex) {}
		catch(javax.swing.UnsupportedLookAndFeelException ex) {}
		new questionTree(new File("bigquestion.q20"));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					waiter.Wait(0.5);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void exitCheck(Container contentPane) {	//confirms exit on X button press
		if(JOptionPane.showConfirmDialog(contentPane,
				"Are you sure you want to exit?", "Exit",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
}
