import java.awt.Container;
import java.awt.EventQueue;
import java.io.File;
import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		try {			//get Windows look and feel
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
		new questionTree((File) null);
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
	
	public static void exitCheck(Container contentPane) {
		if(JOptionPane.showConfirmDialog(contentPane,
				"Are you sure you want to exit?", "Exit",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
			System.exit(0);
	}
}
