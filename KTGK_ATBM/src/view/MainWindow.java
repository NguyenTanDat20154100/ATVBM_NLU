package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;

public class MainWindow extends JFrame {
	private JPanel mainPanel;
	private JPanel childPanel;
	public static String PATH = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 465);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		// btn Home
		JMenuItem mnHome = new JMenuItem("Home");
		mnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel(new PanelHome());
			}
		});
		mnFile.add(mnHome);
		
		
		
		JMenuItem mnExit = new JMenuItem("Exit");
		mnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] options = { "Yes", "No" };
				int x = JOptionPane.showOptionDialog(null, "Do you want exit?", "Exit", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options, options[1]);
				if (x == 0) {
					System.exit(0);
				}
			}
		});
		mnFile.add(mnExit);
		
		JMenu mnCS = new JMenu("Ceaser");
		menuBar.add(mnCS);
		
		//Sự kiện ItemMenu
		JMenuItem mnCeaser = new JMenuItem("Ceaser");
		mnCeaser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel(new PanelCaeser());
			}
		});
		mnCS.add(mnCeaser);
		
		JMenuItem mnDES = new JMenuItem("DES");
		mnDES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel(new PanelDES());
			}
		});
		mnCS.add(mnDES);
		
		JMenuItem mnHill = new JMenuItem("Hill");
		mnHill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel(new PanelHill());
			}
		});
		mnCS.add(mnHill);
		
		JMenuItem mnVegenere = new JMenuItem("Vegenere");
		mnVegenere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel(new PanelVegenere());
			}
		});
		mnCS.add(mnVegenere);
		
		JMenuItem mnAffine = new JMenuItem("AES");
		mnAffine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel(new PanelAES());
			}
		});
		mnCS.add(mnAffine);
		
		JMenu mnRSA = new JMenu("RSA");
		menuBar.add(mnRSA);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("RSA");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel(new PanelRSA());
			}
		});
		mnRSA.add(mntmNewMenuItem);
		
		JMenu mnHash = new JMenu("HASH");
		menuBar.add(mnHash);
		
		JMenuItem mnMD5 = new JMenuItem("MD5");
		mnMD5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel(new PanelMD5());
			}
		});
		mnHash.add(mnMD5);
		
		JMenuItem mnSHA = new JMenuItem("SHA");
		mnSHA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel(new PanelSHA());
			}
		});
		mnHash.add(mnSHA);
		
		JMenu mnCKDT = new JMenu("Chữ Kí Điện Tử");
		menuBar.add(mnCKDT);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Chữ ký điện tử");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel(new PanelCKDT());
			}
		});
		mnCKDT.add(mntmNewMenuItem_1);
		getContentPane().setLayout(null);
		
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 680, 390);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 680, 406);
		mainPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(MainWindow.class.getResource("/img/welcomeBg.png")));
		lblNewLabel_1.setBounds(0, 111, 680, 280);
		panel_1.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 192));
		panel.setBounds(0, 0, 680, 100);
		panel_1.add(panel);
		
		JLabel lblNewLabel = new JLabel("WELCOME TO MY PROJECT");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panel.add(lblNewLabel);
		
	}
	
	//Show Panel
	private void showPanel(JPanel panel){
//		childPanel = panel;
//		mainPanel.removeAll();
//		mainPanel.add(childPanel);
//		mainPanel.validate();
		childPanel = panel;
	    mainPanel.removeAll();
	    mainPanel.add(childPanel);
	    mainPanel.revalidate(); 
	    mainPanel.repaint(); 
	}
}
