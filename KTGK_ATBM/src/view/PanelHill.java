package view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import fileControll.FileController;
import model.Hill;
import model.LanguageAPI.API;

import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class PanelHill extends JPanel {
	private JTextField textField, textField_1;
	private Hill hill = new Hill();
	private JTextArea textArea;
	private JTextArea textArea_1, textArea_2, textArea_2_1;
	private JRadioButton checkEnglish_1;
	private JRadioButton checkVN_1;

	/**
	 * Create the panel.
	 */
	
	
	public PanelHill() {
		setSize(680, 406);
		setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(0, 0, 680, 80);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hill");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(298, 11, 112, 58);
		panel.add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 86, 660, 309);
		add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Encrypt", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Key:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 11, 49, 20);
		panel_1.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(55, 13, 154, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Tạo key");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCreateKey();
			}
		});
		btnNewButton.setBounds(284, 12, 89, 23);
		panel_1.add(btnNewButton);
		
		ButtonGroup btnGroups = new ButtonGroup();
		
		JRadioButton checkEnglish = new JRadioButton("English", true);
		checkEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hill.setAlphabet(API.ENGLISH);
			}
		});
		checkEnglish.setFont(new Font("Tahoma", Font.BOLD, 16));
		checkEnglish.setBounds(379, 12, 111, 23);
		btnGroups.add(checkEnglish);
		panel_1.add(checkEnglish);
		
		JRadioButton checkVN = new JRadioButton("VietNamese");
		checkVN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hill.setAlphabet(API.VIETNAMESE);
			}
		});
		checkVN.setFont(new Font("Tahoma", Font.BOLD, 16));
		checkVN.setBounds(492, 12, 137, 23);
		btnGroups.add(checkVN);
		panel_1.add(checkVN);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 55, 414, 88);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(192, 192, 192));
		panel_4.setBounds(0, 0, 414, 26);
		panel_3.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Plain Text");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(178, 11, 69, 14);
		panel_4.add(lblNewLabel_2);
		
		textArea = new JTextArea();
		textArea.setBounds(0, 29, 414, 59);
		panel_3.add(textArea);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBounds(10, 170, 414, 88);
		panel_1.add(panel_3_1);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setLayout(null);
		panel_4_1.setBackground(Color.LIGHT_GRAY);
		panel_4_1.setBounds(0, 0, 414, 26);
		panel_3_1.add(panel_4_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Cipher Text");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(178, 11, 69, 14);
		panel_4_1.add(lblNewLabel_2_1);
		
		textArea_1 = new JTextArea();
		textArea_1.setBounds(0, 29, 414, 59);
		panel_3_1.add(textArea_1);
		
		JButton btnNewButton_2_1 = new JButton("Lưu key");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onSaveKey();
			}
		});
		btnNewButton_2_1.setBounds(486, 120, 89, 33);
		panel_1.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_2 = new JButton("Mã hóa");
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onEncrypt();
			}
		});
		btnNewButton_2_2.setBounds(486, 188, 89, 33);
		panel_1.add(btnNewButton_2_2);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Decrypt", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Key:");
		lblNewLabel_1_1.setBounds(10, 11, 49, 20);
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 16));
		panel_2.add(lblNewLabel_1_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(55, 13, 154, 20);
		textField_1.setColumns(10);
		panel_2.add(textField_1);
		
		ButtonGroup btnGroups_1 = new ButtonGroup();
		
		checkEnglish_1 = new JRadioButton("English", true);
		checkEnglish_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hill.setAlphabet(API.ENGLISH);
			}
		});
		checkEnglish_1.setBounds(379, 12, 111, 23);
		checkEnglish_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGroups_1.add(checkEnglish_1);
		panel_2.add(checkEnglish_1);
		
		checkVN_1 = new JRadioButton("VietNamese");
		checkVN_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hill.setAlphabet(API.VIETNAMESE);
			}
		});
		checkVN_1.setBounds(492, 12, 137, 23);
		checkVN_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGroups_1.add(checkVN_1);
		panel_2.add(checkVN_1);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setLayout(null);
		panel_3_2.setBounds(10, 55, 414, 88);
		panel_2.add(panel_3_2);
		
		JPanel panel_4_2 = new JPanel();
		panel_4_2.setLayout(null);
		panel_4_2.setBackground(Color.LIGHT_GRAY);
		panel_4_2.setBounds(0, 0, 414, 26);
		panel_3_2.add(panel_4_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Cipher Text");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_2.setBounds(178, 11, 69, 14);
		panel_4_2.add(lblNewLabel_2_2);
		
		textArea_2 = new JTextArea();
		textArea_2.setBounds(0, 29, 414, 59);
		panel_3_2.add(textArea_2);
		
		JPanel panel_3_2_1 = new JPanel();
		panel_3_2_1.setLayout(null);
		panel_3_2_1.setBounds(10, 170, 414, 88);
		panel_2.add(panel_3_2_1);
		
		JPanel panel_4_2_1 = new JPanel();
		panel_4_2_1.setLayout(null);
		panel_4_2_1.setBackground(Color.LIGHT_GRAY);
		panel_4_2_1.setBounds(0, 0, 414, 26);
		panel_3_2_1.add(panel_4_2_1);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Plain Text");
		lblNewLabel_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_2_1.setBounds(178, 11, 69, 14);
		panel_4_2_1.add(lblNewLabel_2_2_1);
		
		textArea_2_1 = new JTextArea();
		textArea_2_1.setBounds(0, 29, 414, 59);
		panel_3_2_1.add(textArea_2_1);
		
		JButton btnNewButton_2_1_1 = new JButton("Chọn key");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseKey();
			}
		});
		btnNewButton_2_1_1.setBounds(486, 120, 89, 33);
		panel_2.add(btnNewButton_2_1_1);
		
		JButton btnNewButton_2_2_1 = new JButton("Giải mã");
		btnNewButton_2_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onDecrypt();
			}
		});
		btnNewButton_2_2_1.setBounds(486, 188, 89, 33);
		panel_2.add(btnNewButton_2_2_1);
	}
	//Lưu khóa
	public void onSaveKey() {
		String saveContent = "hill";
		if (hill.getUseAlphabet() == API.ENGLISH) {
			saveContent += " ENGLISH\n";
		} else {
			saveContent += " VIETNAMESE\n";
		}
		String key = textField.getText().trim();
		if (hill.checkKey(key)) {
			saveContent += key;
			FileController.onSaveText(saveContent);
		} else {
			JOptionPane.showMessageDialog(null, "Invalid key");
		}
	}
	//tạo key
	public void onCreateKey() {
		String key = hill.convertKeyToString(hill.createKey());
		textField.setText(key.trim());
	}
	
	//mã hóa
	public void onEncrypt() {
		if (textArea.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Nothing to encrypt");
		} else {
			String key = textField.getText().trim();
			if (!hill.checkKey(key)) {
				JOptionPane.showMessageDialog(null, "Invalid key, try another key");
			} else {
				String encryptText = hill.encryptWithSpecialChar(this.textArea.getText().trim(), key);
				this.textArea_1.setText(encryptText);
			}
		}
	}
	
	public void chooseKey() {
		File choose = FileController.chooseFile();
		if (choose != null) {
			String keyType = FileController.getKeyType(choose.getAbsolutePath());
			if (keyType.trim().toLowerCase().equals("hill")) {
				String alphabetType = FileController.getKeyAlphabet(choose.getAbsolutePath());
				if (alphabetType.equalsIgnoreCase("ENGLISH")) {
					checkEnglish_1.setSelected(true);
					hill.setAlphabet(API.ENGLISH);
				} else {
					checkVN_1.setSelected(true);
					hill.setAlphabet(API.VIETNAMESE);
				}
				String fileContent = FileController.readContentFile(choose.getAbsolutePath());
				textField_1.setText(fileContent.trim());
			} else {
				JOptionPane.showMessageDialog(null, "This is not hill key");
			}
		}
	}
	//giải mã
	public void onDecrypt() {
		if (textArea_2.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "Nothing to decrypt");
		} else {
			String key = textField_1.getText().trim();
			if (!hill.checkKey(key)) {
				JOptionPane.showMessageDialog(null, "Invalid key");
			} else {
				String decryptText = hill.decryptWithSpecialChar(this.textArea_2.getText().trim(), key);
				this.textArea_2_1.setText(decryptText);
			}
		}
	}
}
