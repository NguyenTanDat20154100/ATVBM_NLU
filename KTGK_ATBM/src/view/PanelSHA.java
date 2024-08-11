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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PanelSHA extends JPanel {
	private Hash hash = new Hash();
	private JComboBox comboBox;
	
	public PanelSHA() {
		setSize(680, 406);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(0, 0, 680, 80);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SHA");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(300, 11, 96, 58);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 81, 680, 325);
		add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(31, 31, 619, 105);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(64, 128, 128));
		panel_3.setBounds(0, 0, 619, 37);
		panel_2.add(panel_3);
		
		JLabel lblNewLabel_1 = new JLabel("Plain Text");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_3.add(lblNewLabel_1);
		
		JTextArea textAreaPlain = new JTextArea();
		textAreaPlain.setBounds(0, 37, 609, 68);
		panel_2.add(textAreaPlain);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBounds(31, 147, 619, 105);
		panel_1.add(panel_2_1);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBackground(new Color(64, 128, 128));
		panel_3_1.setBounds(0, 0, 619, 37);
		panel_2_1.add(panel_3_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Cipher Text");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_3_1.add(lblNewLabel_1_1);
		
		JTextArea textAreaCipher = new JTextArea();
		textAreaCipher.setBounds(0, 37, 609, 68);
		panel_2_1.add(textAreaCipher);
		
		JButton btnEncrypt = new JButton("Mã Hóa");
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onEncrypt();
			}

			private void onEncrypt() {
				if (textAreaPlain.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "Nothing to encrypt");
				} else {
					String encryptText = hash.hash(textAreaPlain.getText(),getSelected());
					textAreaCipher.setText(encryptText);
				}
				
			}

			public String getSelected() {
				String[] types = { "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512" };
				return types[comboBox.getSelectedIndex()];
			}
		});
		btnEncrypt.setForeground(new Color(64, 128, 128));
		btnEncrypt.setBounds(561, 263, 89, 35);
		panel_1.add(btnEncrypt);
		
		JButton chooseFile = new JButton("Chọn File");
		chooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFile();
			}

			private void chooseFile() {
				File choose = FileController.chooseFile();
				if (choose != null) {
					String fileContent = FileController.readTextFile(choose.getAbsolutePath());
					textAreaPlain.setText(fileContent);
				}
				
			}
		});
		chooseFile.setForeground(new Color(64, 128, 128));
		chooseFile.setBounds(439, 263, 89, 35);
		panel_1.add(chooseFile);
		
		JLabel lblNewLabel_2 = new JLabel("Loại SHA:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(31, 263, 89, 35);
		panel_1.add(lblNewLabel_2);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512"}));
		comboBox.setBounds(130, 269, 98, 22);
		panel_1.add(comboBox);
	}
}
