import java.awt.EventQueue;

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
		catch(ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(StartGUI.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch(InstantiationException ex) {
			java.util.logging.Logger.getLogger(StartGUI.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch(IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(StartGUI.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		catch(javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(StartGUI.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					questionTree tree = new questionTree("C:/Users/Grant/Documents/GitHub/20Qs/20Q/src/bigquestion.txt");
					StartGUI frame = new StartGUI();
					frame.setVisible(true);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
