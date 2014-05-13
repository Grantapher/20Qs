import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class waitGUI extends JFrame {
	private JPanel contentPane;
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
		ImageIcon loadGif = null;
		try {
			loadGif = new ImageIcon(new URL("http://i.imgur.com/sDxFWx5.gif"));
		}
		catch(MalformedURLException e) {
							.showMessageDialog(
							null,
							"Couldn't find the gif. :/",
							"Error", JOptionPane.ERROR_MESSAGE);

			loadingObj.setForeground(Color.BLACK);
			object.setForeground(Color.BLACK);
		}
		JLabel label = new JLabel(loadGif);
		label.setForeground(Color.BLACK);
		label.setBounds(0, 0, 318, 322);
		layeredPane.add(label, 1);
		layeredPane.add(object, 0);
		layeredPane.add(loadingObj, 0);
		contentPane.add(layeredPane);
	}
}