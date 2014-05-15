import java.awt.EventQueue;
import java.util.Scanner;

public class waiter {
	protected static double secs;
	public static waitGUI waitFrame = new waitGUI();
	private static final class Lock {}
	final static Object hold = new Lock();
	
	public static void Wait(final double secs) {
		waiter.secs = secs;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					waitFrame.setVisible(true);
					new Thread(wait).start();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
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
				new StartGUI().setVisible(true);
			}
		}
	};
	public static Runnable paint = new Runnable() {
		public void run() {
			waitFrame.repaint();
		}
	};
}