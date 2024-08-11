package view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;

import fileControll.FileController;
import model.Hash;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class PanelMD5 extends JPanel {
	private Hash hash = new Hash();
	
	public PanelMD5() {
		setSize(680, 406);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(0, 0, 680, 80);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MD5");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(287, 11, 94, 58);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 84, 680, 322);
		add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBounds(31, 31, 619, 105);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(64, 128, 128));
		panel_3.setBounds(0, 0, 619, 37);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Plain Text");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(272, 11, 80, 18);
		panel_3.add(lblNewLabel_1);
		
		JTextArea textAreaPlain = new JTextArea();
		textAreaPlain.setBounds(0, 37, 619, 68);
		panel_2.add(textAreaPlain);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2_1.setLayout(null);
		panel_2_1.setBounds(31, 147, 619, 105);
		panel_1.add(panel_2_1);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBackground(new Color(64, 128, 128));
		panel_3_1.setBounds(0, 0, 619, 37);
		panel_2_1.add(panel_3_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Cipher Text");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(272, 11, 80, 18);
		panel_3_1.add(lblNewLabel_1_1);
		
		JTextArea textAreaCipher = new JTextArea();
		textAreaCipher.setBounds(0, 37, 619, 68);
		panel_2_1.add(textAreaCipher);
		
		JButton btnChooseFile = new JButton("Chọn File");
		btnChooseFile.setForeground(new Color(64, 128, 128));
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFile();
			}

			private void chooseFile() {
				File choose = FileController.chooseFile();
				if (choose != null) {
					String fileContent = FileController.readTextFile(choose.getAbsolutePath());
					textAreaPlain.setText("Encrypt file: " + fileContent);
				}
			}
		});
		btnChooseFile.setBounds(462, 263, 89, 35);
		panel_1.add(btnChooseFile);
		
		JButton btnEncrypt = new JButton("Mã Hóa");
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					onEncrypt();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private void onEncrypt() throws Exception {
				if (textAreaPlain.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "Không thể mã hóa");
				} else {
					String encryptText = hash.checkSum(textAreaPlain.getText());
					textAreaCipher.setText(encryptText);
				}
			}

		});
		btnEncrypt.setForeground(new Color(64, 128, 128));
		btnEncrypt.setBounds(561, 263, 89, 35);
		panel_1.add(btnEncrypt);
	}
}
