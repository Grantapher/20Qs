import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

@SuppressWarnings("serial")
public class addGUI extends JFrame {
	private JPanel				contentPane;
	private String				lastData;
	private JLabel				editLbl;
	private static JTextField	objectTxt;
	private static JTextField	questionTxt;
	private final ButtonGroup	buttonGroup	= new ButtonGroup();
	private boolean				answer;
	
	/**
	 * Create the frame.
	 */
	public addGUI(final String lastData) {
		answer = true;
		this.lastData = lastData;
		setTitle("20 Questions");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if(JOptionPane.showConfirmDialog(getContentPane(),
						"Are you sure you want to exit?", "Exit",
						JOptionPane.YES_NO_CANCEL_OPTION,
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
		JLabel awkLbl = new JLabel("Oh... awkward.");
		awkLbl.setHorizontalAlignment(SwingConstants.CENTER);
		awkLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		awkLbl.setBounds(10, 36, 358, 14);
		contentPane.add(awkLbl);
		JPanel panel = new JPanel();
		panel.setBounds(10, 61, 358, 191);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel objectLbl = new JLabel("What was the object?");
		objectLbl.setHorizontalAlignment(SwingConstants.CENTER);
		objectLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		objectLbl.setBounds(10, 11, 338, 14);
		panel.add(objectLbl);
		JLabel openingLbl = new JLabel(
				"What is a question that would help me pick between ");
		openingLbl.setHorizontalAlignment(SwingConstants.CENTER);
		openingLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		openingLbl.setBounds(10, 67, 338, 14);
		panel.add(openingLbl);
		editLbl = new JLabel("an ... and a " + lastData + "?");
		editLbl.setHorizontalAlignment(SwingConstants.CENTER);
		editLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		editLbl.setBounds(10, 92, 338, 14);
		panel.add(editLbl);
		objectTxt = new JTextField();
		objectTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				lengthLimit();
				textUpdate();
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				lengthLimit();
				textUpdate();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				lengthLimit();
				textUpdate();
			}
		});
		objectTxt.setHorizontalAlignment(SwingConstants.CENTER);
		objectTxt.setBounds(0, 36, 358, 20);
		panel.add(objectTxt);
		objectTxt.setColumns(10);
		questionTxt = new JTextField();
		questionTxt.setHorizontalAlignment(SwingConstants.CENTER);
		questionTxt.setBounds(0, 117, 358, 20);
		panel.add(questionTxt);
		questionTxt.setColumns(10);
		JButton finishBtn = new JButton("Next");
		finishBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(objectTxt.getText().length() > 0 && questionTxt
						.getText().length() > 0)) {
					if(JOptionPane
							.showConfirmDialog(
									getContentPane(),
									"You have left a field or two blank, do you not want to add anything to the game?",
									"Confirm",
									JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION)
						return;
					else
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									finishGUI frame = new finishGUI(false);
									frame.setVisible(true);
								}
								catch(Exception e) {
									e.printStackTrace();
								}
							}
						});
					setVisible(false);
					return;
				}
				if(JOptionPane
						.showConfirmDialog(getContentPane(),
								"If you answer yes to the question: \""
										+ questionTxt.getText()
										+ "\" does it lead to \""
										+ (answer ? objectTxt.getText()
												: lastData) + "?\"",
								"Confirm",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.QUESTION_MESSAGE) != JOptionPane.YES_OPTION)
					return;
				questionTree.add(objectTxt.getText(),
						questionTxt.getText(), answer);
				questionTree.write();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							finishGUI frame = new finishGUI(false);
							frame.setVisible(true);
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
				});
				setVisible(false);
			}
		});
		getRootPane().setDefaultButton(finishBtn);
		JRadioButton yesBtn = new JRadioButton("Yes");
		yesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				answer = true;
			}
		});
		yesBtn.setSelected(true);
		buttonGroup.add(yesBtn);
		yesBtn.setBounds(159, 142, 45, 23);
		panel.add(yesBtn);
		JRadioButton noBtn = new JRadioButton("No");
		noBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				answer = false;
			}
		});
		buttonGroup.add(noBtn);
		noBtn.setBounds(206, 142, 45, 23);
		panel.add(noBtn);
		finishBtn.setBounds(269, 168, 89, 23);
		panel.add(finishBtn);
		JLabel ansLbl = new JLabel("Answer:");
		ansLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		ansLbl.setBounds(108, 145, 45, 14);
		panel.add(ansLbl);
	}
	
	protected void lengthLimit() {
		if(objectTxt.getText().length() > 17) {
			objectTxt.setText(objectTxt.getText().substring(0, 17));
		}
	}
	
	boolean vowelCheck(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}
	
	void textUpdate() {
		String temp = objectTxt.getText();
		char first = 0;
		if(!temp.isEmpty())
			first = temp.charAt(0);
		editLbl.setText("a" + (vowelCheck(first) ? "n " : " ") + temp
				+ " and a " + lastData + "?");
	}
}
