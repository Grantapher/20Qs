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

@SuppressWarnings("serial")
public class waitGUI extends JFrame {
	private JPanel contentPane;
	public JLabel loadingObj;
	private JLayeredPane layeredPane;
	private JLabel object;
	public String str;
	int[] pixels;
	
	/**
	 * Create the frame.
	 * 
	 * @param secs
	 * @throws Exception 
	 */
	public waitGUI() throws Exception {
		setResizable(false);
		ImageIcon loadGif = null;
		File load = new File("");
		if(!load.exists()) {
			JOptionPane
					.showMessageDialog(
							null,
							"File not found. ¯\\_(ツ)_/¯\nFind load.gif.\nIf you can't find it, it will be fetched from the internet.",
							"Error", JOptionPane.ERROR_MESSAGE);
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
			fc.setFileFilter(new FileNameExtensionFilter("Image Files",
					"jpg", "png", "gif", "bmp"));
			int returnVal = fc.showOpenDialog(null);
			if(returnVal == JFileChooser.CANCEL_OPTION) {
				try {
					URL loadURL = new URL("http://i.imgur.com/dZgTHsw.gif");
					loadGif = new ImageIcon(loadURL);
					setBounds(0, 0, loadGif.getIconWidth() + 6,
							loadGif.getIconHeight() + 25);
					if(loadGif.getIconHeight() == -1
							&& loadGif.getIconWidth() == -1)
						throw new Exception();
				}
				catch(MalformedURLException e) {}
			}
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				loadGif = new ImageIcon(fc.getSelectedFile().getPath());
			}
		} else
			loadGif = new ImageIcon(load.getPath());
		layeredPane = new JLayeredPane();
		layeredPane.setBorder(null);
		layeredPane.setBounds(0, 0, loadGif.getIconWidth(),
				loadGif.getIconHeight());
		setTitle("20 Questions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		if(load.exists())
			setBounds(0, 0, loadGif.getIconWidth() + 6,
					loadGif.getIconHeight() + 25);
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
		if(loadGif.getIconHeight() != -1) {
			JLabel label = new JLabel(loadGif);
			label.setBounds(0, 0, loadGif.getIconWidth(),
					loadGif.getIconHeight());
			layeredPane.add(label, 2);
		}
		layeredPane.add(object, 0);
		layeredPane.add(loadingObj, 0);
		contentPane.add(layeredPane);
	}
}