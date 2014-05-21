import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@SuppressWarnings("serial")
public class addGUI extends JFrame {
	private JPanel contentPane;
	private JLabel editLbl;
	private static JTextField objectTxt;
	private static JTextField questionTxt;
	private static JRadioButton yesBtn;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private boolean answer;
	
	/**
	 * Create the frame.
	 */
	public addGUI(final String lastData) {
		answer = true;
		setTitle("20 Questions");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Main.exitCheck(getContentPane());
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(0, 0, 384, 288);
		setMinimumSize(new Dimension(166, 286));
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final JLabel titleLbl = new JLabel("20 Questions:");
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(titleLbl);
		final JLabel awkLbl = new JLabel("Oh... awkward.");
		awkLbl.setHorizontalAlignment(SwingConstants.CENTER);
		awkLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(awkLbl);
		final JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);
		final JLabel objectLbl = new JLabel("What was the object?");
		objectLbl.setHorizontalAlignment(SwingConstants.CENTER);
		objectLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(objectLbl);
		final JLabel openingLbl = new JLabel(
				"What is a question that would help me pick between ");
		openingLbl.setHorizontalAlignment(SwingConstants.CENTER);
		openingLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(openingLbl);
		editLbl = new JLabel("an ... and a " + lastData + "?");
		editLbl.setHorizontalAlignment(SwingConstants.CENTER);
		editLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(editLbl);
		objectTxt = new JTextField();
		objectTxt.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(objectTxt);
		objectTxt.setColumns(10);
		questionTxt = new JTextField();
		questionTxt.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(questionTxt);
		questionTxt.setColumns(10);
		final JButton finishBtn = new JButton("Next");
		finishBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(objectTxt.getText().length() > 0 && questionTxt	//if a field is blank, don't add a Q&A if user doesn't want to
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
				if(JOptionPane		//confirms they are adding a compatible Q&A to the game
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
				yesBtn.setEnabled(false);
				questionTree.add(objectTxt.getText(),	//adds Q&A to the tree
						questionTxt.getText(), answer);
				questionTree.write();
				setVisible(false);
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
			}
		});
		getRootPane().setDefaultButton(finishBtn);
		yesBtn = new JRadioButton("Yes");
		yesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				answer = true;
			}
		});
		final JLabel ansLbl = new JLabel("Answer:");
		ansLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel.add(ansLbl);
		yesBtn.setSelected(true);
		buttonGroup.add(yesBtn);
		panel.add(yesBtn);
		final JRadioButton noBtn = new JRadioButton("No");
		noBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				answer = false;
			}
		});
		buttonGroup.add(noBtn);
		panel.add(noBtn);
		panel.add(finishBtn);
		titleLbl.setBounds(10, 10, getWidth() - 36, 14);
		awkLbl.setBounds(10, 34, getWidth() - 36, 14);
		panel.setBounds(10, 48, getWidth() - 36, getHeight() - 96);
		objectLbl.setBounds(10, 10, panel.getWidth() - 20, 14);
		objectTxt.setBounds(0, 36, panel.getWidth(), 20);
		openingLbl.setBounds(10, 67, panel.getWidth() - 20, 14);
		editLbl.setBounds(10, 92, panel.getWidth() - 20, 14);
		questionTxt.setBounds(0, 117, panel.getWidth(), 20);
		ansLbl.setBounds((panel.getWidth() - 131) / 2, 145, 45, 14);
		yesBtn.setBounds(ansLbl.getX() + ansLbl.getWidth() + 6,
				ansLbl.getY(), 42, 14);
		noBtn.setBounds(yesBtn.getX() + yesBtn.getWidth(), ansLbl.getY(),
				38, 14);
		finishBtn.setBounds(panel.getWidth() - 89, panel.getHeight() - 23,
				89, 23);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				titleLbl.setBounds(10, 10, getWidth() - 36, 14);
				awkLbl.setBounds(10, 34, getWidth() - 36, 14);
				panel.setBounds(10, 48, getWidth() - 36, getHeight() - 94);
				objectLbl.setBounds(10, 10, panel.getWidth() - 20, 14);
				objectTxt.setBounds(0, 36, panel.getWidth(), 20);
				openingLbl.setBounds(10, 67, panel.getWidth() - 20, 14);
				editLbl.setBounds(10, 92, panel.getWidth() - 20, 14);
				questionTxt.setBounds(0, 117, panel.getWidth(), 20);
				ansLbl.setBounds((panel.getWidth() - 131) / 2, 145, 45, 14);
				yesBtn.setBounds(ansLbl.getX() + ansLbl.getWidth() + 6,
						ansLbl.getY(), 42, 14);
				noBtn.setBounds(yesBtn.getX() + yesBtn.getWidth(),
						ansLbl.getY(), 38, 14);
				finishBtn.setBounds(panel.getWidth() - 89,
						panel.getHeight() - 23, 89, 23);
			}
		});
	}
}
