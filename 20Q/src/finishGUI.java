import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

@SuppressWarnings("serial")
public class finishGUI extends JFrame {
	private JPanel contentPane;
	private Random ran = new Random();
	int x, y;
	
	/**
	 * Create the frame.
	 * 
	 * @param correct changes text based on if the game got the answer correct.
	 * 
	 */
	public finishGUI(boolean correct) {
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
		final JLabel titleLbl = new JLabel("20 Questions:");
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titleLbl.setBounds(10, 11, getWidth() - 36, 14);
		contentPane.add(titleLbl);
		final JLabel openingLbl = new JLabel(correct ? "WOOHOO! I got it!"
				: "Thank you! I'll try harder next time!");
		openingLbl.setHorizontalAlignment(SwingConstants.CENTER);
		openingLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		openingLbl.setBounds(10, 36, getWidth() - 36, 14);
		contentPane.add(openingLbl);
		final JLabel againLbl = new JLabel("Want to play again?");
		againLbl.setHorizontalAlignment(SwingConstants.CENTER);
		againLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		againLbl.setBounds(10, 61, getWidth() - 36, 14);
		contentPane.add(againLbl);
		final JPanel pan = new JPanel();
		pan.setBounds(10, 86, getWidth() - 36, getHeight() - 132);
		contentPane.add(pan);
		pan.setLayout(null);
		final JButton yesBtn = new JButton("Yes");
		yesBtn.addActionListener(new ActionListener() {		//yes button brings the game back to the startGUI
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
		yesBtn.setBounds(pan.getWidth() / 2 - 94,
				(pan.getHeight() - 23) / 2, 89, 23);
		pan.add(yesBtn);
		final JButton noBtn = new JButton("No");
		noBtn.addActionListener(new ActionListener() {		//no button is the same as the Exit button in StartGUI
			public void actionPerformed(ActionEvent e) {
				noBtn.setText("Nice try");
				x = ran.nextInt(pan.getWidth() - noBtn.getWidth());
				y = ran.nextInt(pan.getHeight() - noBtn.getHeight());
				noBtn.setBounds(x, y, noBtn.getWidth(), noBtn.getHeight());
				revalidate();
				repaint();
			}
		});
		noBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				x = ran.nextInt(pan.getWidth() - noBtn.getWidth());
				y = ran.nextInt(pan.getHeight() - noBtn.getHeight());
				noBtn.setBounds(x, y, noBtn.getWidth(), noBtn.getHeight());
				revalidate();
				repaint();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				noBtn.setText("Nice try");
				x = ran.nextInt(pan.getWidth() - noBtn.getWidth());
				y = ran.nextInt(pan.getHeight() - noBtn.getHeight());
				noBtn.setBounds(x, y, noBtn.getWidth(), noBtn.getHeight());
				revalidate();
				repaint();
			}
		});
		noBtn.setBounds(pan.getWidth() / 2 + 5,
				(pan.getHeight() - 23) / 2, 89, 23);
		pan.add(noBtn);
		setMinimumSize(new Dimension(230, 200));
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				titleLbl.setBounds(10, 11, getWidth() - 36, 14);
				openingLbl.setBounds(10, 36, getWidth() - 36, 14);
				againLbl.setBounds(10, 61, getWidth() - 36, 14);
				pan.setBounds(10, 86, getWidth() - 36, getHeight() - 132);
				yesBtn.setBounds(pan.getWidth() / 2 - 94,
						(pan.getHeight() - 23) / 2, 89, 23);
				noBtn.setBounds(pan.getWidth() / 2 + 5,
						(pan.getHeight() - 23) / 2, 89, 23);
			}
		});
	}
}
