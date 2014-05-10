import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class QuestionGUI extends JFrame {
	private JPanel			contentPane;
	private questionNode	current;
	private JTextArea		questionTxt;
	
	/**
	 * Create the frame.
	 */
	public QuestionGUI() {
		current = questionTree.head;
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
		questionTxt = new JTextArea();
		questionTxt.setWrapStyleWord(true);
		questionTxt.setLineWrap(true);
		questionTxt.setRows(2);
		questionTxt.setText("What is it?");
		questionTxt.setBorder(null);
		questionTxt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		questionTxt.setEditable(false);
		questionTxt.setBackground(UIManager.getColor("Label.background"));
		questionTxt.setBounds(20, 36, 348, 110);
		contentPane.add(questionTxt);
		questionTxt.setColumns(10);
		if(current.isQuestion())
			questionTxt.setText(current.data);
		else
			moveOn(current.data);
		JPanel panel = new JPanel();
		panel.setBounds(10, 157, 358, 95);
		contentPane.add(panel);
		panel.setLayout(null);
		JButton yesBtn = new JButton("Yes");
		yesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current = current.yes;
				if(current.isQuestion())
					questionTxt.setText(current.data);
				else
					moveOn(current.data);
			}
		});
		yesBtn.setBounds(85, 36, 89, 23);
		panel.add(yesBtn);
		JButton noBtn = new JButton("No");
		noBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current = current.no;
				if(current.isQuestion())
					questionTxt.setText(current.data);
				else
					moveOn(current.data);
			}
		});
		noBtn.setBounds(184, 36, 89, 23);
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
	
	void moveOn(final String data) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(false);
					resultGUI frame = new resultGUI(data);
					frame.setVisible(true);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
