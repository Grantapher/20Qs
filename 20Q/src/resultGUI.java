import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@SuppressWarnings("serial")
public class resultGUI extends JFrame {
	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 * 
	 * @param current
	 */
	public resultGUI(final questionNode current) {
		setTitle("20 Questions");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Main.exitCheck(getContentPane());
			}
		});
		setMinimumSize(new Dimension(223, 219));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(0, 0, 384, 288);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final JLabel titleLbl = new JLabel("20 Questions:");
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(titleLbl);
		final JLabel openingLbl = new JLabel(
				"Is the object you are thinking of");
		openingLbl.setHorizontalAlignment(SwingConstants.CENTER);
		openingLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(openingLbl);
		final JLabel bigLbl = new JLabel(current.data);
		bigLbl.setHorizontalAlignment(SwingConstants.CENTER);
		bigLbl.setFont(new Font("Tahoma", Font.PLAIN, 36));
		contentPane.add(bigLbl);
		final JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		final JButton yesBtn = new JButton("Yes");
		yesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							finishGUI frame = new finishGUI(true);
							frame.setVisible(true);
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		panel.add(yesBtn);
		final JButton noBtn = new JButton("No");
		noBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							addGUI frame = new addGUI(current.data);
							frame.setVisible(true);
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		panel.add(noBtn);
		final JButton undoBtn = new JButton("Undo");
		undoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							QuestionGUI frame = new QuestionGUI(
									current.undo);
							frame.setVisible(true);
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		panel.add(undoBtn);
		titleLbl.setBounds(10, 11, getWidth() - 36, 14);
		openingLbl.setBounds(10, 36, getWidth() - 36, 14);
		bigLbl.setBounds(10, 61, getWidth() - 36, 44);
		panel.setBounds(10, 115, getWidth() - 36,
				getHeight() - bigLbl.getY() - bigLbl.getHeight() - 56);
		yesBtn.setBounds(panel.getWidth() / 2 - 94,
				panel.getHeight() / 2 - 28, 89, 23);
		noBtn.setBounds(yesBtn.getX() + yesBtn.getWidth() + 10,
				yesBtn.getY(), 89, 23);
		undoBtn.setBounds((panel.getWidth() - 89) / 2, yesBtn.getY() + 33,
				89, 23);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				titleLbl.setBounds(10, 11, getWidth() - 36, 14);
				openingLbl.setBounds(10, 36, getWidth() - 36, 14);
				bigLbl.setBounds(10, 61, getWidth() - 36, 44);
				panel.setBounds(10, 115, getWidth() - 36, getHeight()
						- bigLbl.getY() - bigLbl.getHeight() - 56);
				yesBtn.setBounds(panel.getWidth() / 2 - 94,
						panel.getHeight() / 2 - 28, 89, 23);
				noBtn.setBounds(yesBtn.getX() + yesBtn.getWidth() + 10,
						yesBtn.getY(), 89, 23);
				undoBtn.setBounds((panel.getWidth() - 89) / 2,
						yesBtn.getY() + 33, 89, 23);
			}
		});
	}
}
