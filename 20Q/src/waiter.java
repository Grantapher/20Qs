import java.awt.EventQueue;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class waiter {
	protected static double secs;	//secs to wait before iterating
	public static waitGUI waitFrame;	//waitGUI to activate
	public static boolean nextVisible;	//nextVisible keeps from multiple StartGUIs from appearing
	
	public static void Wait(final double secs) {
		waiter.secs = secs;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					waitFrame = new waitGUI();
					waitFrame.setVisible(true);
					new Thread(wait).start();
				}
				catch(ImageLoadFailException e) {	//skips straight to the start screen
					JOptionPane.showMessageDialog(null,
							"Image failed to load.", "No Connection",
							JOptionPane.WARNING_MESSAGE);
					startNext();
				}
			}
		});
	}
	
	synchronized static public void startNext() {	//if nextVisible isn't true yet, moves on to startGUI
		if(nextVisible)
			return;
		new StartGUI().setVisible(true);
		waitFrame.setVisible(false);
		nextVisible = true;
	}
	public static Runnable wait = new Runnable() {	//sleeps and iterates through objectList
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
				startNext();
			}
		}
	};
}