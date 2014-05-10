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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class addGUI extends JFrame {
	private JPanel		contentPane;
	private JTextField	objectTxt;
	private JTextField	questionTxt;
	
	/**
	 * Create the frame.
	 */
	public addGUI(final String lastData) {
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
		openingLbl.setBounds(10, 87, 338, 14);
		panel.add(openingLbl);
		final JLabel editLbl = new JLabel("an <above> and a " + lastData + "?");
		editLbl.setHorizontalAlignment(SwingConstants.CENTER);
		editLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		editLbl.setBounds(10, 112, 338, 14);
		panel.add(editLbl);
		objectTxt = new JTextField();
		objectTxt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String temp = objectTxt.getText();
				char first = 0;
				if(!temp.isEmpty())
					first = temp.charAt(0);
				editLbl.setText("a" + (vowelCheck(first) ? "n " : " ")
						+ temp + " and a " + lastData + "?");
			}
		});
		objectTxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String temp = objectTxt.getText();
				char first = 0;
				if(!temp.isEmpty())
					first = temp.charAt(0);
				editLbl.setText("a" + (vowelCheck(first) ? "n " : " ")
						+ temp + " and a " + lastData + "?");
			}
		});
		objectTxt.setHorizontalAlignment(SwingConstants.CENTER);
		objectTxt.setBounds(0, 36, 358, 20);
		panel.add(objectTxt);
		objectTxt.setColumns(10);
		questionTxt = new JTextField();
		questionTxt.setHorizontalAlignment(SwingConstants.CENTER);
		questionTxt.setBounds(0, 137, 358, 20);
		panel.add(questionTxt);
		questionTxt.setColumns(10);
		JButton finishBtn = new JButton("Next");
		finishBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		finishBtn.setBounds(269, 168, 89, 23);
		panel.add(finishBtn);
	}
	
	boolean vowelCheck(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}
}
