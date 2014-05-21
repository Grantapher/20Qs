import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class waitGUI extends JFrame {//waitGUI is the GUI that shows an image/gif for the heck of it. Can be skipped with Spacebar.
	private JPanel contentPane;
	public JLabel loadingObj;
	private JLayeredPane layeredPane;
	private JLabel object;
	char c;		//holds the char that's pressed
	
	/**
	 * Create the frame.
	 * 
	 * @param secs
	 * @throws ImageLoadFailException
	 */
	public waitGUI() throws ImageLoadFailException {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {	//listens for Spacebar to skip to next GUI
				if(' ' == e.getKeyChar())
					waiter.startNext();
			}
		});
		setResizable(false);
		ImageIcon loadIcon = null;
		JOptionPane
				.showMessageDialog(
						null,
						"Find the loading image.\nIf you can't find it, one will be fetched from the internet.",
						"Image File not Found",
						JOptionPane.WARNING_MESSAGE);
		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
		fc.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg",
				"png", "gif", "bmp"));
		int returnVal = fc.showOpenDialog(null);
		if(returnVal == JFileChooser.CANCEL_OPTION) {	//if user hits cancel, tries to obtain image from internet
			try {
				URL loadURL = new URL("http://i.imgur.com/dZgTHsw.gif");
				loadIcon = new ImageIcon(loadURL);
				setBounds(0, 0, loadIcon.getIconWidth() + 6,
						loadIcon.getIconHeight() + 25);
			}
			catch(MalformedURLException e) {}
		}
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			loadIcon = new ImageIcon(fc.getSelectedFile().getPath());
		}
		layeredPane = new JLayeredPane();
		layeredPane.setBorder(null);
		layeredPane.setBounds(0, 0, loadIcon.getIconWidth(),
				loadIcon.getIconHeight());
		setTitle("20 Questions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(loadIcon.getIconHeight() == -1 || loadIcon.getIconWidth() == -1)
			throw new ImageLoadFailException();	//if Icon is -1 by -1, throw
		setBounds(0, 0, loadIcon.getIconWidth() + 6,
				loadIcon.getIconHeight() + 25);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		loadingObj = new JLabel();
		loadingObj.setHorizontalAlignment(SwingConstants.CENTER);
		loadingObj.setVerticalAlignment(SwingConstants.CENTER);
		loadingObj.setFont(new Font("Tahoma", Font.PLAIN, 18));
		loadingObj.setForeground(Color.BLACK);
		loadingObj.setBounds(0, 28, getWidth(), getHeight() - 28);
		object = new JLabel("Loading objects...");
		object.setHorizontalAlignment(SwingConstants.CENTER);
		object.setVerticalAlignment(SwingConstants.CENTER);
		object.setFont(new Font("Tahoma", Font.PLAIN, 18));
		object.setForeground(Color.BLACK);
		object.setBounds(0, 0, getWidth(), getHeight() - 28);
		if(loadIcon.getIconHeight() != -1) {	//setBounds of label if image exists
			JLabel label = new JLabel(loadIcon);
			label.setBounds(0, 0, loadIcon.getIconWidth(),
					loadIcon.getIconHeight());
			layeredPane.add(label, 2);
		}
		layeredPane.add(object, 0);
		layeredPane.add(loadingObj, 0);
		contentPane.add(layeredPane);
	}
}