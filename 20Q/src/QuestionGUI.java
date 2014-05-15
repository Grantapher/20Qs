import javax.swing.JFrame;
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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@SuppressWarnings("serial")
public class QuestionGUI extends JFrame {
	private JPanel contentPane;
	public static questionNode current;
	private JTextArea questionTxt;
	
	/**
	 * Create the frame.
	 */
	public QuestionGUI(questionNode currentIn) {
		current = currentIn;
		setTitle("20 Questions");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Main.exitCheck(getContentPane());
			}
		});
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(0, 0, 384, 288);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final JPanel upPan = new JPanel();
		upPan.setLayout(null);
		final JLabel titleLbl = new JLabel("20 Questions:");
		titleLbl.setBounds(0, 0, getWidth(), 14);
		upPan.add(titleLbl);
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		questionTxt = new JTextArea();
		questionTxt.setBounds(0, titleLbl.getHeight() + titleLbl.getY()
				+ 10, upPan.getWidth(), 1);
		upPan.add(questionTxt);
		upPan.setBounds(10, 10, getWidth() - 36, 1);
		questionTxt.setWrapStyleWord(true);
		questionTxt.setLineWrap(true);
		questionTxt.setRows(2);
		questionTxt.setText("What is it?");
		questionTxt.setBorder(null);
		questionTxt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		questionTxt.setEditable(false);
		questionTxt.setBackground(UIManager.getColor("Label.background"));
		questionTxt.setColumns(10);
		contentPane.add(upPan);
		final JPanel btnPan = new JPanel();
		btnPan.setBounds(10, 1, getWidth() - 36, 1);
		contentPane.add(btnPan);
		btnPan.setLayout(null);
		final JButton yesBtn = new JButton("Yes");
		yesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current = current.yes;
				questionCheck();
			}
		});
		yesBtn.setBounds(btnPan.getWidth() / 2 - 94, 10, 89, 23);
		btnPan.add(yesBtn);
		final JButton noBtn = new JButton("No");
		noBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current = current.no;
				questionCheck();
			}
		});
		noBtn.setBounds(yesBtn.getX() + yesBtn.getWidth() + 10,
				yesBtn.getY(), 89, 23);
		btnPan.add(noBtn);
		final JButton exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.exitCheck(getContentPane());
			}
		});
		btnPan.add(exitBtn);
		final JButton undoButton = new JButton("Undo");
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(current.undo == null)
					return;
				current = current.undo;
				questionTxt.setText(current.data);
			}
		});
		btnPan.add(undoButton);
		undoButton.setBounds((btnPan.getWidth() - 89) / 2,
				yesBtn.getY() + 33, 89, 23);
		btnPan.setBounds(10, getHeight() - 145, getWidth() - 36, 99);
		exitBtn.setBounds(btnPan.getWidth() - 89, btnPan.getHeight() - 23,
				89, 23);
		upPan.setBounds(10, 10, getWidth() - 36, btnPan.getY() - 20);
		questionTxt.setBounds(0, titleLbl.getHeight() + titleLbl.getY()
				+ 10, upPan.getWidth(),
				upPan.getHeight() - titleLbl.getY() - titleLbl.getHeight()
						- 10);
		if(current.isQuestion())
			questionTxt.setText(current.data);
		else
			moveOn();
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				titleLbl.setBounds(0, 0, getWidth(), 14);
				questionTxt.setBounds(0,
						titleLbl.getHeight() + titleLbl.getY() + 10,
						upPan.getWidth(), 1);
				upPan.setBounds(10, 10, getWidth() - 36, 1);
				btnPan.setBounds(10, 1, getWidth() - 36, 1);
				yesBtn.setBounds(btnPan.getWidth() / 2 - 94, 10, 89, 23);
				noBtn.setBounds(yesBtn.getX() + yesBtn.getWidth() + 10,
						yesBtn.getY(), 89, 23);
				undoButton.setBounds((btnPan.getWidth() - 89) / 2,
						yesBtn.getY() + 33, 89, 23);
				btnPan.setBounds(10, getHeight() - 145, getWidth() - 36,
						99);
				exitBtn.setBounds(btnPan.getWidth() - 89,
						btnPan.getHeight() - 23, 89, 23);
				upPan.setBounds(10, 10, getWidth() - 36,
						btnPan.getY() - 20);
				questionTxt.setBounds(
						0,
						titleLbl.getHeight() + titleLbl.getY() + 10,
						upPan.getWidth(),
						upPan.getHeight() - titleLbl.getY()
								- titleLbl.getHeight() - 10);
			}
		});
	}
	
	protected void questionCheck() {
		if(current.isQuestion())
			questionTxt.setText(current.data);
		else
			moveOn();
	}
	
	void moveOn() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setVisible(false);
					resultGUI frame = new resultGUI(current);
					frame.setVisible(true);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
