import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
public class QuestionGUI extends JFrame {
	private JPanel	contentPane;
	
	/**
	 * Create the frame.
	 */
	public QuestionGUI() {
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
		JLabel questionLbl = new JLabel("Is it an animal?");
		questionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		questionLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		questionLbl.setBounds(10, 36, 358, 14);
		contentPane.add(questionLbl);
		JPanel panel = new JPanel();
		panel.setBounds(10, 61, 358, 191);
		contentPane.add(panel);
		panel.setLayout(null);
		JButton yesBtn = new JButton("Yes");
		yesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {}
		});
		yesBtn.setBounds(85, 78, 89, 23);
		panel.add(yesBtn);
		JButton noBtn = new JButton("No");
		noBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							resultGUI frame = new resultGUI();
							frame.setVisible(true);
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		noBtn.setBounds(184, 78, 89, 23);
		panel.add(noBtn);
		JButton exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(getContentPane(),
						"Are you sure you want to exit?", "Exit",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		exitBtn.setBounds(269, 168, 89, 23);
		panel.add(exitBtn);
	}
}
