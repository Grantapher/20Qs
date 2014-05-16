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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

@SuppressWarnings("serial")
public class StartGUI extends JFrame {
	private JPanel contentPane;
	private Random ran = new Random();
	int x, y;
	
	/**
	 * Create the frame.
	 */
	public StartGUI() {
		setTitle("20 Questions");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 384, 288);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		final JPanel labelPan = new JPanel();
		contentPane.add(labelPan);
		labelPan.setLayout(null);
		labelPan.setBounds(10, 10, getWidth() - 36, getHeight() - 56);
		final JLabel titleLbl = new JLabel("20 Questions:");
		titleLbl.setBounds(0, 0, labelPan.getWidth(), 22);
		labelPan.add(titleLbl);
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		final JLabel instructionLbl = new JLabel(
				"Think of an object, and I will try and guess it.");
		instructionLbl.setBounds(0, titleLbl.getHeight() + 10,
				labelPan.getWidth(), 14);
		labelPan.add(instructionLbl);
		instructionLbl.setHorizontalAlignment(SwingConstants.CENTER);
		instructionLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		final JLabel readyLbl = new JLabel("Ready?");
		readyLbl.setBounds(0, instructionLbl.getHeight() + 10
				+ instructionLbl.getY(), labelPan.getWidth(), 14);
		labelPan.add(readyLbl);
		readyLbl.setHorizontalAlignment(SwingConstants.CENTER);
		readyLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labelPan.setBounds(10, 10, getWidth() - 36, readyLbl.getHeight()
				+ readyLbl.getY() + 10);
		final JPanel btnPan = new JPanel();
		btnPan.setBounds(10, labelPan.getHeight() + 20, getWidth() - 36,
				getHeight() - 65 - labelPan.getHeight());
		contentPane.add(btnPan);
		btnPan.setLayout(null);
		final JButton startBtn = new JButton("Ready");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							QuestionGUI frame = new QuestionGUI(
									questionTree.head);
							frame.setVisible(true);
						}
						catch(Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		startBtn.setBounds((btnPan.getWidth() - 89) / 2,
				(btnPan.getHeight()) / 2 - 27, 89, 23);
		btnPan.add(startBtn);
		final JButton exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitBtn.setText("Nice try");
				x = ran.nextInt(btnPan.getWidth() - exitBtn.getWidth());
				y = ran.nextInt(btnPan.getHeight() - exitBtn.getHeight());
				exitBtn.setBounds(x, y, exitBtn.getWidth(),
						exitBtn.getHeight());
				revalidate();
				repaint();
			}
		});
		exitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				x = ran.nextInt(btnPan.getWidth() - exitBtn.getWidth());
				y = ran.nextInt(btnPan.getHeight() - exitBtn.getHeight());
				exitBtn.setBounds(x, y, exitBtn.getWidth(),
						exitBtn.getHeight());
				revalidate();
				repaint();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				exitBtn.setText("Nice try");
				x = ran.nextInt(btnPan.getWidth() - exitBtn.getWidth());
				y = ran.nextInt(btnPan.getHeight() - exitBtn.getHeight());
				exitBtn.setBounds(x, y, exitBtn.getWidth(),
						exitBtn.getHeight());
				revalidate();
				repaint();
			}
		});
		exitBtn.setBounds((btnPan.getWidth() - 89) / 2,
				btnPan.getHeight() / 2 + 3, 89, 23);
		btnPan.add(exitBtn);
		setMinimumSize(new Dimension(128, 200));
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				labelPan.setBounds(10, 10, getWidth() - 36,
						getHeight() - 56);
				titleLbl.setBounds(0, 0, labelPan.getWidth(), 22);
				readyLbl.setBounds(0, instructionLbl.getHeight() + 10
						+ instructionLbl.getY(), labelPan.getWidth(), 14);
				instructionLbl.setBounds(0, titleLbl.getHeight() + 10,
						labelPan.getWidth(), 14);
				labelPan.setBounds(10, 10, getWidth() - 36,
						readyLbl.getHeight() + readyLbl.getY() + 10);
				btnPan.setBounds(10, labelPan.getHeight() + 20,
						getWidth() - 36,
						getHeight() - 65 - labelPan.getHeight());
				startBtn.setBounds((btnPan.getWidth() - 89) / 2,
						(btnPan.getHeight()) / 2 - 27, 89, 23);
				exitBtn.setBounds((btnPan.getWidth() - 89) / 2,
						btnPan.getHeight() / 2 + 3, 89, 23);
			}
		});
	}
}
