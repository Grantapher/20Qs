import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class finishGUI extends JFrame {
	private JPanel	contentPane;
	
	/**
	 * Create the frame.
	 */
	public finishGUI(boolean correct) {
		setTitle("20 Questions");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(JOptionPane.showConfirmDialog(getContentPane(),
						"Are you sure you want to exit?", "Exit",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
					System.exit(0);
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
		JLabel openingLbl = new JLabel(correct ? "WOOHOO! I got it!"
				: "Thank you! I'll try harder next time!");
		openingLbl.setHorizontalAlignment(SwingConstants.CENTER);
		openingLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		openingLbl.setBounds(10, 36, 358, 14);
		contentPane.add(openingLbl);
		JPanel panel = new JPanel();
		panel.setBounds(10, 86, 358, 166);
		contentPane.add(panel);
		panel.setLayout(null);
		JButton yesBtn = new JButton("Yes");
		yesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							StartGUI frame = new StartGUI();
							frame.setVisible(true);
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		yesBtn.setBounds(85, 71, 89, 23);
		panel.add(yesBtn);
		JButton noBtn = new JButton("No");
		noBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		noBtn.setBounds(184, 71, 89, 23);
		panel.add(noBtn);
		JLabel lblWantToPlay = new JLabel("Want to play again?");
		lblWantToPlay.setHorizontalAlignment(SwingConstants.CENTER);
		lblWantToPlay.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblWantToPlay.setBounds(10, 61, 358, 14);
		contentPane.add(lblWantToPlay);
	}
}
