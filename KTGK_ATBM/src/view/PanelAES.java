package view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.crypto.SecretKey;
import javax.swing.DefaultComboBoxModel;

import fileControll.FileController;
import model.AES;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;
import java.awt.event.ActionEvent;

public class PanelAES extends JPanel {
	private JLabel ipKeyEn, ipKeyDe, report;
	private JComboBox comboBox;
	private AES aes = new AES();
	private byte[] byteData;
	private JTextArea textAreaPlainEn, textAreaPlainDe, textAreaCipherDe;
	/**
	 * Create the panel.
	 */
	public PanelAES() {
		setSize(680, 406);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(0, 0, 680, 80);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("AES");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(296, 0, 102, 80);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 80, 680, 326);
		add(panel_1);
		panel_1.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 660, 304);
		panel_1.add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Encrypt", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Key size:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 11, 109, 20);
		panel_2.add(lblNewLabel_1_1);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"128", "192", "256"}));
		comboBox.setBounds(89, 11, 61, 22);
		panel_2.add(comboBox);
		
		JButton btnCreateKey = new JButton("Tạo key");
		btnCreateKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onCreateKey();
			}
		});
		btnCreateKey.setBounds(324, 12, 89, 23);
		panel_2.add(btnCreateKey);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 55, 414, 88);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(0, 0, 414, 26);
		panel_5.setBackground(new Color(192, 192, 192));
		panel_4.add(panel_5);
		
		JLabel lblNewLabel_2 = new JLabel("Plain Text");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_5.add(lblNewLabel_2);
		
		textAreaPlainEn = new JTextArea();
		textAreaPlainEn.setBounds(0, 29, 414, 59);
		panel_4.add(textAreaPlainEn);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setLayout(null);
		panel_4_1.setBounds(10, 177, 414, 88);
		panel_2.add(panel_4_1);
		
		JPanel panel_5_1 = new JPanel();
		panel_5_1.setBackground(Color.LIGHT_GRAY);
		panel_5_1.setBounds(0, 0, 414, 26);
		panel_4_1.add(panel_5_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Cipher Text");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_5_1.add(lblNewLabel_2_1);
		
		JTextArea textAreaCipherEn = new JTextArea();
		textAreaCipherEn.setBounds(0, 29, 414, 59);
		panel_4_1.add(textAreaCipherEn);
		
		JButton btnChooseFileEn = new JButton("Chọn File");
		btnChooseFileEn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFile();
			}

		});
		btnChooseFileEn.setBounds(434, 55, 89, 33);
		panel_2.add(btnChooseFileEn);
		
		JButton btnNewButton_3 = new JButton("Mã hóa");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onEncrypt();
			}

			private void onEncrypt() {
				try {
					if (textAreaPlainEn.getText().trim().length() == 0) {
						JOptionPane.showMessageDialog(null, "Không có gì để mã hóa");
					} else {
						if (aes.getSecretKey() == null) {
							JOptionPane.showMessageDialog(null, "Thêm hoặc tạo khóa");
						} else {
							String plainText = textAreaPlainEn.getText();
							if (plainText.indexOf("Encrypt file: ") == 0) {
								byte[] encrypt = aes.encrypt(byteData);
								byteData = encrypt;
								textAreaCipherEn.setText("Mã hóa File thành công, hãy lưu lại file!!!");
							} else {
								String encrypt = aes.encrypt(plainText);
								textAreaCipherEn.setText(encrypt);
							}
						}
					}
				} catch (Exception e) {
					
				}
				
			}
		});
		btnNewButton_3.setBounds(434, 188, 89, 33);
		panel_2.add(btnNewButton_3);
		
		ipKeyEn = new JLabel("");
		ipKeyEn.setFont(new Font("Tahoma", Font.BOLD, 14));
		ipKeyEn.setBounds(160, 13, 154, 20);
		panel_2.add(ipKeyEn);
		
		JButton saveKey = new JButton("Lưu key");
		saveKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveKey();
			}

			private void saveKey() {
				if (aes.getSecretKey() == null) {
					JOptionPane.showMessageDialog(null, "Bạn chưa tạo key!!!");
				} else {
					FileController.saveObjectToFile(aes.getSecretKey());
				}
			}
		});
		saveKey.setBounds(443, 12, 89, 23);
		panel_2.add(saveKey);
		
		JButton btnSaveFileEn = new JButton("Lưu File");
		btnSaveFileEn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}

			private void saveFile() {
				String cipherText = textAreaCipherEn.getText();
				if (cipherText.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "Chưa có file để lưu!!!");
				} else {
					String saveContent = "";
					if (textAreaCipherEn.equals("Mã hóa File thành công, hãy lưu lại file!!!")) {
						saveContent = Base64.getEncoder().encodeToString(byteData);
					} else {
						saveContent = cipherText;
					}
					FileController.onSaveText(saveContent);
				}
				
			}
		});
		btnSaveFileEn.setBounds(434, 119, 89, 33);
		panel_2.add(btnSaveFileEn);
		
		report = new JLabel("");
		report.setFont(new Font("Tahoma", Font.PLAIN, 14));
		report.setForeground(new Color(0, 0, 255));
		report.setBounds(12, 150, 414, 20);
		panel_2.add(report);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Decrypt", null, panel_3, null);
		panel_3.setLayout(null);
		
		JPanel panel_4_2 = new JPanel();
		panel_4_2.setLayout(null);
		panel_4_2.setBounds(10, 55, 414, 88);
		panel_3.add(panel_4_2);
		
		JPanel panel_5_2 = new JPanel();
		panel_5_2.setBackground(Color.LIGHT_GRAY);
		panel_5_2.setBounds(0, 0, 414, 26);
		panel_4_2.add(panel_5_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Cipher Text");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_5_2.add(lblNewLabel_2_2);
		
		textAreaCipherDe = new JTextArea();
		textAreaCipherDe.setBounds(0, 29, 414, 59);
		panel_4_2.add(textAreaCipherDe);
		
		JPanel panel_4_1_1 = new JPanel();
		panel_4_1_1.setLayout(null);
		panel_4_1_1.setBounds(10, 177, 414, 88);
		panel_3.add(panel_4_1_1);
		
		JPanel panel_5_1_1 = new JPanel();
		panel_5_1_1.setBackground(Color.LIGHT_GRAY);
		panel_5_1_1.setBounds(0, 0, 414, 26);
		panel_4_1_1.add(panel_5_1_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Plain Text");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_5_1_1.add(lblNewLabel_2_1_1);
		
		textAreaPlainDe = new JTextArea();
		textAreaPlainDe.setBounds(0, 29, 414, 59);
		panel_4_1_1.add(textAreaPlainDe);
		
		JButton chooseFileDe = new JButton("Chọn File");
		chooseFileDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFileDe();
			}

			public void chooseFileDe() {
			    File selectedFile = FileController.chooseFile();

			    if (selectedFile != null) {
			        String fileName = selectedFile.getName();
			        int lastDotIndex = fileName.lastIndexOf(".");

			        if (lastDotIndex != -1) {
			            String fileExtension = fileName.substring(lastDotIndex + 1).toLowerCase();

			            if ("txt".equals(fileExtension)) {
			                String fileContent = FileController.readTextFile(selectedFile.getAbsolutePath());
			                textAreaCipherDe.setText(fileContent.trim());
			            } else {
			                try {
			                    byteData = FileController.readBytesFromFile(selectedFile);
			                    textAreaCipherDe.setText("Decrypt file: " + selectedFile.getAbsolutePath());
			                } catch (IOException e) {
			                    JOptionPane.showMessageDialog(null, "Lỗi không đọc được file");
			                }
			            }
			        } else {
			        }
			    }
			}

		});
		chooseFileDe.setBounds(486, 55, 89, 33);
		panel_3.add(chooseFileDe);
		
		JButton btnDecrypt = new JButton("Giải mã");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onDecrypt();
			}

			public void onDecrypt() {
				try {
					if (textAreaCipherDe.getText().trim().length() == 0) {
						JOptionPane.showMessageDialog(null, "Không có nội dung giải mã");
					} else {
						if (aes.getSecretKey() == null) {
							JOptionPane.showMessageDialog(null, "Import key before decrypt");
						} else {
							String cipherText = textAreaCipherDe.getText();
							if (cipherText.indexOf("Decrypt file: ") == 0) {
								byte[] decrypt = aes.decrypt(byteData);
								byteData = decrypt;
								textAreaPlainDe.setText("Giải mã thành công, choose save text to save the result");
							} else {
								byteData = aes.decrypt(Base64.getDecoder().decode(cipherText));
								String decrypt = aes.decrypt(cipherText);
								textAreaPlainDe.setText(decrypt);
							}
						}
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Can't decrypt, try again");
				}
			}

		}
		);
		btnDecrypt.setBounds(486, 188, 89, 33);
		panel_3.add(btnDecrypt);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Key :");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(10, 11, 109, 20);
		panel_3.add(lblNewLabel_1_1_1);
		
		JButton btnChooseKey = new JButton("Chọn key");
		btnChooseKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseKey();
			}

			public void chooseKey() {
			    File selectedFile = FileController.chooseFile();
			    if (selectedFile != null) {
			        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(selectedFile))) {
			            Object object = objectInputStream.readObject();
			            if (object instanceof SecretKey) {
			                SecretKey secretKey = (SecretKey) object;
			                if ("AES".equalsIgnoreCase(secretKey.getAlgorithm())) {
			                    aes.setSecretKey(secretKey);
			                    notifyKeyIsReady();
			                } else {
			                    JOptionPane.showMessageDialog(null, "Key bị lỗi");
			                }
			            } else {
			                JOptionPane.showMessageDialog(null, "Key bị lỗi");
			            }
			        } catch (ClassNotFoundException | IOException e) {
			        }
			    }
			}

			private void notifyKeyIsReady() {
				ipKeyDe.setText("Thêm key thành công!!!");
			}

		});
		btnChooseKey.setBounds(335, 12, 89, 23);
		panel_3.add(btnChooseKey);
		
		ipKeyDe = new JLabel("");
		ipKeyDe.setFont(new Font("Tahoma", Font.BOLD, 14));
		ipKeyDe.setBounds(62, 11, 263, 20);
		panel_3.add(ipKeyDe);
		
		JButton btnSaveFileDe = new JButton("Lưu File");
		btnSaveFileDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFileDe();
			}

			private void saveFileDe() {
				String plainText = textAreaPlainDe.getText();
				if (plainText.trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "Không có gì để lưu");
				} else {
					FileController.saveBytesToFile(byteData);
				}
			}
		});
		btnSaveFileDe.setBounds(486, 122, 89, 33);
		panel_3.add(btnSaveFileDe);
	}
	
	public void onCreateKey() {
		int[] keyLenghts = { 128, 192, 256 };
		int keyLenght = keyLenghts[comboBox.getSelectedIndex()];
		aes.createKey(keyLenght);
		keyIsReady();
	}

	//Xác nhận đã tạo key thành công
	private void keyIsReady() {
		// TODO Auto-generated method stub
		ipKeyEn.setText("Key đã được tạo!!!");
	}
	
	public void chooseFile() {
	    File selectedFileOrDirectory = FileController.chooseFile();
	    
	    if (selectedFileOrDirectory != null) {
	        if (selectedFileOrDirectory.isFile()) {
	            String fileName = selectedFileOrDirectory.getName().toLowerCase();
	            if (fileName.endsWith(".txt") || fileName.endsWith(".zip") || fileName.endsWith(".rar")) {
	                if (fileName.endsWith(".txt")) {
	                    String fileContent = FileController.readTextFile(selectedFileOrDirectory.getAbsolutePath());
	                    textAreaPlainEn.setText(fileContent.trim());
	                } else {
	                    byteData = FileController.readByteFile(selectedFileOrDirectory.getAbsolutePath());
	                    textAreaPlainEn.setText("Encrypt file: " + selectedFileOrDirectory.getAbsolutePath());
	                }
	            } else {
	                JOptionPane.showMessageDialog(null, "Unsupported file format.", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } else if (selectedFileOrDirectory.isDirectory()) {
	        }
	    }
	}
}
