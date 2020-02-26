package GUIPackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class ClassDetail extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClassDetail frame = new ClassDetail();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClassDetail() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 532, 436);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblAdvanceProgramming = new JLabel("Advance Programming");
		lblAdvanceProgramming.setFont(new Font("Arial", Font.PLAIN, 22));
		lblAdvanceProgramming.setBounds(135, 10, 229, 81);
		panel.add(lblAdvanceProgramming);
		
		JLabel lblNewLabel = new JLabel("ClassID");
		lblNewLabel.setForeground(SystemColor.controlShadow);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(45, 70, 53, 29);
		panel.add(lblNewLabel);
		
		JLabel lblDirector = new JLabel("Director");
		lblDirector.setForeground(SystemColor.controlShadow);
		lblDirector.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDirector.setBounds(108, 70, 53, 29);
		panel.add(lblDirector);
		
		JLabel lblNewLabel_1_1 = new JLabel("ID");
		lblNewLabel_1_1.setForeground(SystemColor.controlShadow);
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(215, 70, 53, 29);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("10");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(45, 91, 53, 29);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("John Wick");
		lblNewLabel_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(108, 91, 97, 29);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("0001");
		lblNewLabel_1_2_1.setForeground(Color.BLACK);
		lblNewLabel_1_2_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_2_1.setBounds(215, 91, 82, 29);
		panel.add(lblNewLabel_1_2_1);
		
		JLabel lblRequirement = new JLabel("Requirement");
		lblRequirement.setForeground(SystemColor.controlShadow);
		lblRequirement.setFont(new Font("Arial", Font.PLAIN, 12));
		lblRequirement.setBounds(45, 118, 89, 21);
		panel.add(lblRequirement);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(45, 250, 415, 118);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblStuffName = new JLabel("Stuff name");
		lblStuffName.setBounds(23, 11, 76, 14);
		lblStuffName.setForeground(SystemColor.controlShadow);
		lblStuffName.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_1.add(lblStuffName);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("John John John");
		lblNewLabel_1_2_2.setForeground(Color.BLACK);
		lblNewLabel_1_2_2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_2_2.setBounds(23, 29, 97, 21);
		panel_1.add(lblNewLabel_1_2_2);
		
		JLabel lblTrainning = new JLabel("Orgnized Trainning");
		lblTrainning.setForeground(SystemColor.controlShadow);
		lblTrainning.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTrainning.setBounds(131, 11, 115, 14);
		panel_1.add(lblTrainning);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(130, 29, 275, 46);
		panel_1.add(scrollPane_1);
		
		JTextPane txtpnwindowbuildertable_1 = new JTextPane();
		txtpnwindowbuildertable_1.setBackground(SystemColor.control);
		scrollPane_1.setViewportView(txtpnwindowbuildertable_1);
		txtpnwindowbuildertable_1.setText("La Flora, o vero Il natal de' fiori (Flora, or The Birth of Flowers) is an opera in a prologue and five acts composed by Marco da Gagliano and Jacopo Peri to a libretto by Andrea Salvadori. It was first performed on 14 October 1628 at the Teatro Mediceo in Florence to celebrate the marriage of Margherita de' Medici and Odoardo Farnese, Duke of Parma.[1] Based on the story of Chloris and Zephyrus in Book V of Ovid's Fasti, Salvadori's libretto contains many allegorical references to the transfer of political power, the beauty of Tuscany,");
		txtpnwindowbuildertable_1.setFont(new Font("Arial", Font.PLAIN, 12));
		txtpnwindowbuildertable_1.setEditable(false);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBackground(new Color(255, 255, 255));
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSubmit.setBounds(159, 85, 85, 23);
		panel_1.add(btnSubmit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 138, 415, 81);
		panel.add(scrollPane);
		
		JTextPane txtpnwindowbuildertable = new JTextPane();
		scrollPane.setViewportView(txtpnwindowbuildertable);
		txtpnwindowbuildertable.setFont(new Font("Arial", Font.PLAIN, 12));
		txtpnwindowbuildertable.setEditable(false);
		txtpnwindowbuildertable.setText("La Flora, o vero Il natal de' fiori (Flora, or The Birth of Flowers) is an opera in a prologue and five acts composed by Marco da Gagliano and Jacopo Peri to a libretto by Andrea Salvadori. It was first performed on 14 October 1628 at the Teatro Mediceo in Florence to celebrate the marriage of Margherita de' Medici and Odoardo Farnese, Duke of Parma.[1] Based on the story of Chloris and Zephyrus in Book V of Ovid's Fasti, Salvadori's libretto contains many allegorical references to the transfer of political power, the beauty of Tuscany,");
		
		JLabel lblTeachingRequest = new JLabel("Teaching request");
		lblTeachingRequest.setBounds(88, 228, 134, 14);
		panel.add(lblTeachingRequest);
		lblTeachingRequest.setForeground(Color.BLACK);
		lblTeachingRequest.setFont(new Font("Arial", Font.PLAIN, 12));
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setForeground(SystemColor.controlShadow);
		lblStatus.setFont(new Font("Arial", Font.PLAIN, 12));
		lblStatus.setBounds(45, 222, 53, 29);
		panel.add(lblStatus);
	}
}
