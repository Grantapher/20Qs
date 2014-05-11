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

@SuppressWarnings("serial")
public class StartGUI extends JFrame {
	private JPanel	contentPane;
	
	/**
	 * Create the frame.
	 */
	public StartGUI() {
		setTitle("20 Questions");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		JLabel instructionLbl = new JLabel(
				"Think of an object, and I will try and guess it.");
		instructionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		instructionLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instructionLbl.setBounds(10, 36, 358, 14);
		contentPane.add(instructionLbl);
		JLabel readyLbl = new JLabel("Ready?");
		readyLbl.setHorizontalAlignment(SwingConstants.CENTER);
		readyLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		readyLbl.setBounds(10, 61, 358, 14);
		contentPane.add(readyLbl);
		JPanel panel = new JPanel();
		panel.setBounds(10, 86, 358, 166);
		contentPane.add(panel);
		panel.setLayout(null);
		JButton startBtn = new JButton("Start");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							QuestionGUI frame = new QuestionGUI(questionTree.head);
							frame.setVisible(true);
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		startBtn.setBounds(135, 55, 89, 23);
		panel.add(startBtn);
		JButton exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitBtn.setBounds(135, 88, 89, 23);
		panel.add(exitBtn);
	}
}
