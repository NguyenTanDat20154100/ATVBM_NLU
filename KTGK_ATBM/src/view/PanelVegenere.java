package view;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fileControll.FileController;
import model.Vigenere;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.SecureRandom;
import java.util.Base64;

public class PanelVegenere extends JPanel {
	private JTextField ipKey_1, ipKey_2;
	private JComboBox comboBox;
	private Vigenere vigenere = new Vigenere();


	/**
	 * Create the panel.
	 */
	public PanelVegenere() {
		setSize(680, 406);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(0, 0, 680, 80);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Vegenere");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(253, 11, 179, 58);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 80, 680, 326);
		add(panel_1);
		panel_1.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 660, 304);
		panel_1.add(tabbedPane);
		
		JPanel enPanel = new JPanel();
		tabbedPane.addTab("Encrypt", null, enPanel, null);
		enPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Key:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 11, 49, 20);
		enPanel.add(lblNewLabel_1);
		
		ipKey_1 = new JTextField();
		ipKey_1.setBounds(55, 13, 154, 20);
		enPanel.add(ipKey_1);
		ipKey_1.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"64", "128", "256"}));
		comboBox.setBounds(219, 12, 55, 22);
		enPanel.add(comboBox);
		
		JButton btnNewButton = new JButton("Tạo key");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sizeK = Integer.parseInt(getSelectedComboBoxValue());
                ipKey_1.setText(generateRandomString(sizeK));
			}

			private String generateRandomString(int sizeK) {
				int byteSize = sizeK / 8;
		        byte[] randomBytes = new byte[byteSize];
		        SecureRandom secureRandom = new SecureRandom();
		        secureRandom.nextBytes(randomBytes);
		        return Base64.getEncoder().encodeToString(randomBytes);
			}

			private String getSelectedComboBoxValue() {
				 String selectedValue = (String) comboBox.getSelectedItem();
			        return selectedValue;			}
		});
		btnNewButton.setBounds(284, 12, 89, 23);
		enPanel.add(btnNewButton);
		
		ButtonGroup btnGroups = new ButtonGroup();
		
		JRadioButton checkEnglish = new JRadioButton("English", true);
		checkEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vigenere.setSctLanguage(checkEnglish.getText());
			}
		});
		checkEnglish.setFont(new Font("Tahoma", Font.BOLD, 16));
		checkEnglish.setBounds(379, 12, 111, 23);
		btnGroups.add(checkEnglish);
		enPanel.add(checkEnglish);
		
		
		JRadioButton checkVN = new JRadioButton("VietNamese");
		checkVN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vigenere.setSctLanguage(checkVN.getText());
			}
		});
		checkVN.setFont(new Font("Tahoma", Font.BOLD, 16));
		checkVN.setBounds(492, 12, 137, 23);
		btnGroups.add(checkVN);
		enPanel.add(checkVN);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 55, 414, 88);
		enPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(192, 192, 192));
		panel_3.setBounds(0, 0, 414, 26);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Plain Text");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_2.setBounds(166, 0, 63, 21);
		panel_3.add(lblNewLabel_2);
		
		JTextArea plainText = new JTextArea();
		plainText.setBounds(0, 29, 414, 59);
		panel_2.add(plainText);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBounds(10, 166, 414, 88);
		enPanel.add(panel_2_1);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBackground(Color.LIGHT_GRAY);
		panel_3_1.setBounds(0, 0, 414, 26);
		panel_2_1.add(panel_3_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Cipher Text");
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(167, 0, 83, 21);
		panel_3_1.add(lblNewLabel_2_1);
		
		JTextArea cipherText = new JTextArea();
		cipherText.setBounds(0, 29, 414, 59);
		panel_2_1.add(cipherText);
		
		JButton btnNewButton_1 = new JButton("Chọn File");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFile();
			}

			private void chooseFile() {
				File choose = FileController.chooseFile();
				if (choose != null) {
					String[] fileNameSplit = choose.getName().split("\\.");
					if (fileNameSplit[fileNameSplit.length - 1].equals("txt")) {
						String fileContent = FileController.readTextFile(choose.getAbsolutePath());
						plainText.setText(fileContent.trim());
					} else {
						
						plainText.setText("Encrypt file: " + choose.getAbsolutePath());
					}
				}
				
			}
		});
		btnNewButton_1.setBounds(486, 55, 89, 33);
		enPanel.add(btnNewButton_1);
		
		JButton btnNewButton_1_2 = new JButton("Mã hóa");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = ipKey_1.getText();
				
				String text = plainText.getText();
				if (text.startsWith("Encrypt file:")) {
		            // Mã hóa tệp tin
		            JFileChooser fileChooser = new JFileChooser();
		            int returnValue = fileChooser.showSaveDialog(null);

		            if (returnValue == JFileChooser.APPROVE_OPTION) {
		                File selectedFile = fileChooser.getSelectedFile();
		                String destFile = selectedFile.getAbsolutePath();

		                vigenere.encryptFile(key, text.substring("Encrypt file: ".length()), destFile);
		                cipherText.setText("Mã hóa File thành công" + "\n"
		                        + "Đã lưu vào: " + destFile);
		            }
		        } else {
		        	
		        	if(vigenere.getSctLanguage().equals("English")) {
		        		
		        		cipherText.setText(vigenere.encryptEn(text, key));
		        		
		        	}else if(vigenere.getSctLanguage().equals("VietNamese")) {
		        		cipherText.setText(vigenere.encryptVN(text, key));
		        	}
		        }
			}
		});
		btnNewButton_1_2.setBounds(486, 188, 89, 33);
		enPanel.add(btnNewButton_1_2);
		
		JPanel dePanel = new JPanel();
		tabbedPane.addTab("Decrypt", null, dePanel, null);
		dePanel.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Key:");
		lblNewLabel_1_1.setBounds(10, 11, 49, 20);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		dePanel.add(lblNewLabel_1_1);
		
		ipKey_2 = new JTextField();
		ipKey_2.setBounds(55, 13, 154, 20);
		ipKey_2.setColumns(10);
		dePanel.add(ipKey_2);
		
		ButtonGroup btnGroups_1 = new ButtonGroup();
		
		JRadioButton checkEnglish_1 = new JRadioButton("English");
		checkEnglish_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vigenere.setSctLanguage(checkEnglish_1.getText());
			}
		});
		checkEnglish_1.setBounds(379, 12, 111, 23);
		checkEnglish_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGroups_1.add(checkEnglish_1);
		dePanel.add(checkEnglish_1);
		
		JRadioButton checkVN_1 = new JRadioButton("VietNamese");
		checkVN_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vigenere.setSctLanguage(checkVN_1.getText());
			}
		});
		checkVN_1.setBounds(492, 12, 137, 23);
		checkVN_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGroups_1.add(checkVN_1);
		dePanel.add(checkVN_1);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setLayout(null);
		panel_2_2.setBounds(10, 55, 414, 88);
		dePanel.add(panel_2_2);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setLayout(null);
		panel_3_2.setBackground(Color.LIGHT_GRAY);
		panel_3_2.setBounds(0, 0, 414, 26);
		panel_2_2.add(panel_3_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Cipher Text");
		lblNewLabel_2_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_2_2.setBounds(166, 0, 74, 21);
		panel_3_2.add(lblNewLabel_2_2);
		
		JTextArea cipherText_De = new JTextArea();
		cipherText_De.setBounds(0, 29, 414, 59);
		panel_2_2.add(cipherText_De);
		
		JPanel panel_2_2_1 = new JPanel();
		panel_2_2_1.setLayout(null);
		panel_2_2_1.setBounds(10, 166, 414, 88);
		dePanel.add(panel_2_2_1);
		
		JPanel panel_3_2_1 = new JPanel();
		panel_3_2_1.setLayout(null);
		panel_3_2_1.setBackground(Color.LIGHT_GRAY);
		panel_3_2_1.setBounds(0, 0, 414, 26);
		panel_2_2_1.add(panel_3_2_1);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Plain Text");
		lblNewLabel_2_2_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_2_2_1.setBounds(174, 0, 74, 21);
		panel_3_2_1.add(lblNewLabel_2_2_1);
		
		JTextArea plainText_De = new JTextArea();
		plainText_De.setBounds(0, 29, 414, 59);
		panel_2_2_1.add(plainText_De);
		
		JButton btnNewButton_1_3 = new JButton("Chọn File");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFile();
			}

			private void chooseFile() {
				File selectedFile = FileController.chooseFile();

			    if (selectedFile != null) {
			        String fileName = selectedFile.getName();
			        int lastDotIndex = fileName.lastIndexOf(".");

			        if (lastDotIndex != -1) {
			            String fileExtension = fileName.substring(lastDotIndex + 1).toLowerCase();

			            if ("txt".equals(fileExtension)) {
			                String fileContent = FileController.readTextFile(selectedFile.getAbsolutePath());
			                cipherText_De.setText(fileContent.trim());
			            } else {
			            	cipherText_De.setText("Decrypt file: " + selectedFile.getAbsolutePath());
			            }
			        } 
			    }
				
			}
		});
		btnNewButton_1_3.setBounds(486, 55, 89, 33);
		dePanel.add(btnNewButton_1_3);
		
		JButton btnNewButton_1_2_1 = new JButton("Giải mã");
		btnNewButton_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String inputText = ipKey_2.getText();
                String textAreaText = cipherText_De.getText();
				if (!checkEnglish_1.isSelected() && !checkVN_1.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn ít nhất một trong hai CheckBox", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
				String key = ipKey_2.getText();
				String text = cipherText_De.getText();
				if (text.startsWith("Decrypt file: ")) {
		            // Giải mã tệp tin
		            JFileChooser fileChooser = new JFileChooser();
		            int returnValue = fileChooser.showSaveDialog(null);
		            
		            if (returnValue == JFileChooser.APPROVE_OPTION) {
		                File selectedFile = fileChooser.getSelectedFile();
		                String destFile = selectedFile.getAbsolutePath();
		                
		                vigenere.decryptFile(key, text.substring("Decrypt file: ".length()), destFile);
		                plainText_De.setText("Mã hóa File thành công" + "\n"
		                		+ "Đã lưu vào: " + destFile);
		            }
		        } else {
		        	
		        	if(vigenere.getSctLanguage().equals("English")) {
		        		
		        		plainText_De.setText(vigenere.decryptEn(text, key));
		        		
		        	}else if(vigenere.getSctLanguage().equals("VietNamese")) {
		        		plainText_De.setText(vigenere.decryptVN(text, key));
		        	}
		        }
			}
		});
		btnNewButton_1_2_1.setBounds(486, 189, 89, 33);
		dePanel.add(btnNewButton_1_2_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 226, 5, 22);
		panel_1.add(textArea);
		
		
	}
}
