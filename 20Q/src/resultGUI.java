import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setBounds(0, 0, 384, 288);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel titleLbl = new JLabel("20 Questions:");
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titleLbl.setBounds(10, 11, 358, 14);
		contentPane.add(titleLbl);
		JLabel openingLbl = new JLabel("Is the object you are thinking of");
		openingLbl.setHorizontalAlignment(SwingConstants.CENTER);
		openingLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		openingLbl.setBounds(10, 36, 358, 14);
		contentPane.add(openingLbl);
		JLabel bigLbl = new JLabel(current.data);
		bigLbl.setHorizontalAlignment(SwingConstants.CENTER);
		bigLbl.setFont(new Font("Tahoma", Font.PLAIN, 36));
		bigLbl.setBounds(10, 61, 358, 44);
		contentPane.add(bigLbl);
		JPanel panel = new JPanel();
		panel.setBounds(10, 157, 358, 95);
		contentPane.add(panel);
		panel.setLayout(null);
		JButton yesBtn = new JButton("Yes");
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
		yesBtn.setBounds(85, 19, 89, 23);
		panel.add(yesBtn);
		JButton noBtn = new JButton("No");
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
		noBtn.setBounds(184, 19, 89, 23);
		panel.add(noBtn);
		JButton undoBtn = new JButton("Undo");
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
		undoBtn.setBounds(135, 53, 89, 23);
		panel.add(undoBtn);
	}
}
