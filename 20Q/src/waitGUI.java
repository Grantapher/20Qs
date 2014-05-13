import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class waitGUI extends JFrame {
	private JPanel contentPane;
	private static ImageIcon loadGif = new ImageIcon("src/load.gif");
	public JLabel loadingObj;
	private JLayeredPane layeredPane;
	private JLabel object;
	
	/**
	 * Create the frame.
	 * 
	 * @param secs
	 */
	public waitGUI() {
		layeredPane = new JLayeredPane();
		layeredPane.setBorder(null);
		layeredPane.setBounds(0, 0, 318, 322);
		setTitle("20 Questions");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 324, 347);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel label = new JLabel(loadGif);
		label.setForeground(Color.BLACK);
		label.setBounds(0, 0, 318, 322);
		layeredPane.add(label, 1);
		loadingObj = new JLabel();
		loadingObj.setHorizontalAlignment(SwingConstants.CENTER);
		loadingObj.setVerticalAlignment(SwingConstants.CENTER);
		loadingObj.setFont(new Font("Tahoma", Font.PLAIN, 18));
		loadingObj.setForeground(Color.WHITE);
		loadingObj.setBounds(0, 28, 318, 294);
		object = new JLabel("Loading objects...");
		object.setHorizontalAlignment(SwingConstants.CENTER);
		object.setVerticalAlignment(SwingConstants.CENTER);
		object.setFont(new Font("Tahoma", Font.PLAIN, 18));
		object.setForeground(Color.WHITE);
		object.setBounds(0, 0, 318, 294);
		layeredPane.add(object, 0);
		layeredPane.add(loadingObj, 0);
		contentPane.add(layeredPane);
	}
}