import java.awt.EventQueue;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class waiter {
	protected static double secs;
	public static waitGUI waitFrame;
	public static boolean nextVisible;
	
	public static void Wait(final double secs) {
		waiter.secs = secs;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					waitFrame = new waitGUI();
					waitFrame.setVisible(true);
					new Thread(wait).start();
				}
				catch(LoadFailException e) {
					JOptionPane.showMessageDialog(null,
							"Image failed to load.", "No Connection",
							JOptionPane.WARNING_MESSAGE);
					startNext();
				}
			}
		});
	}
	
	synchronized static public void startNext() {
		if(nextVisible)
			return;
		new StartGUI().setVisible(true);
		waitFrame.setVisible(false);
		nextVisible = true;
	}
	public static Runnable wait = new Runnable() {
		public void run() {
			try {
				Thread.sleep((long) secs * 1000);
				Scanner sc = new Scanner(objectList.objects);
				sc.useDelimiter("[,]");
				while(sc.hasNext()) {
					waitFrame.loadingObj.setText(sc.next().toLowerCase());
					Thread.sleep(50);
				}
				sc.close();
			}
			catch(InterruptedException e) {}
			finally {
				waitFrame.setVisible(false);
				startNext();
			}
		}
	};
}