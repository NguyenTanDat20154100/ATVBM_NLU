package view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import fileControll.FileController;
import model.DES;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.awt.event.ActionEvent;

public class PanelDES extends JPanel {
	private JTextField ipKeyEn;
	private JTextField ipKeyDe;
	private DES des = new DES();
	private byte[] byteData;

	/**
	 * Create the panel.
	 */
	public PanelDES() {
		setSize(680, 406);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(0, 0, 680, 80);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DES");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 35));
		lblNewLabel.setBounds(296, 24, 103, 33);
		panel.add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 86, 660, 309);
		add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Encrypt", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Key:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 11, 49, 20);
		panel_1.add(lblNewLabel_1);
		
		ipKeyEn = new JTextField();
		ipKeyEn.setBounds(55, 13, 154, 20);
		panel_1.add(ipKeyEn);
		ipKeyEn.setColumns(10);
		
		JButton btnCreateKey = new JButton("Tạo key");
		btnCreateKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					des.createKey();
					noteKey();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private void noteKey() {
				ipKeyEn.setText(des.exportKey());
				
			}
		});
		btnCreateKey.setBounds(228, 12, 89, 23);
		panel_1.add(btnCreateKey);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 55, 414, 88);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(192, 192, 192));
		panel_4.setBounds(0, 0, 414, 26);
		panel_3.add(panel_4);
		
		JLabel lblNewLabel_2 = new JLabel("Plain Text");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_4.add(lblNewLabel_2);
		
		JTextArea textAreaPlainEn = new JTextArea();
		textAreaPlainEn.setBounds(0, 29, 414, 59);
		panel_3.add(textAreaPlainEn);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBounds(10, 170, 414, 88);
		panel_1.add(panel_3_1);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBackground(Color.LIGHT_GRAY);
		panel_4_1.setBounds(0, 0, 414, 26);
		panel_3_1.add(panel_4_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Cipher Text");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_4_1.add(lblNewLabel_2_1);
		
		JTextArea textAreaCipherEn = new JTextArea();
		textAreaCipherEn.setBounds(0, 29, 414, 59);
		panel_3_1.add(textAreaCipherEn);
		
		JButton chooseFileEn = new JButton("Chọn File");
		chooseFileEn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseFile();
			}

			private void chooseFile() {
				File choose = FileController.chooseFile();
				if (choose != null) {
					String[] fileNameSplit = choose.getName().split("\\.");
					if (fileNameSplit[fileNameSplit.length - 1].equals("txt")) {
						String fileContent = FileController.readTextFile(choose.getAbsolutePath());
						textAreaPlainEn.setText(fileContent.trim());
					} else {
//						byteData = FileController.readByteFile(choose.getAbsolutePath());
						textAreaPlainEn.setText("Encrypt file: " + choose.getAbsolutePath());
					}
				}
				
			}
		});
		chooseFileEn.setBounds(490, 117, 89, 33);
		panel_1.add(chooseFileEn);
		
		JButton btnEncrypt = new JButton("Mã hóa");
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onEncrypt();
			}

			private void onEncrypt() {
			    try {
			        String plainText = textAreaPlainEn.getText().trim();

			        if (plainText.isEmpty()) {
			            JOptionPane.showMessageDialog(null, "Không có gì để mã hóa");
			            return;
			        }

			        if (des.getKey() == null) {
			            JOptionPane.showMessageDialog(null, "Thêm hoặc tạo khóa");
			            return;
			        }

			        if (plainText.startsWith("Encrypt file: ")) {
			            // Mã hóa tệp tin
			            JFileChooser fileChooser = new JFileChooser();
			            int returnValue = fileChooser.showSaveDialog(null);
			            
			            if (returnValue == JFileChooser.APPROVE_OPTION) {
			                File selectedFile = fileChooser.getSelectedFile();
			                String destFile = selectedFile.getAbsolutePath();
			                
			                des.encryptFile(plainText.substring("Encrypt file: ".length()), destFile);
			                textAreaCipherEn.setText("Mã hóa File thành công" + "\n"
			                		+ "Đã lưu vào: " + destFile);
			            }
			        } else {
			            // Mã hóa chuỗi
			            String encrypt = des.encryptToBase64(plainText);
			            textAreaCipherEn.setText(encrypt);
			        }
			    } catch (Exception e) {
			        JOptionPane.showMessageDialog(null, "Không thể mã hóa, vui lòng thử lại");
			    }
			}


		});
		btnEncrypt.setBounds(490, 191, 89, 33);
		panel_1.add(btnEncrypt);
		
		JButton btnSaveKey = new JButton("Lưu key");
		btnSaveKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveKey();
			}

			private void saveKey() {
				if (des.getKey() == null) {
					JOptionPane.showMessageDialog(null, "Bạn chưa tạo key!!!");
				} else {
					FileController.saveObjectToFile(des.exportKey());
				}
				
			}
		});
		btnSaveKey.setBounds(349, 12, 89, 23);
		panel_1.add(btnSaveKey);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Decrypt", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Key:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 11, 49, 20);
		panel_2.add(lblNewLabel_1_1);
		
		ipKeyDe = new JTextField();
		ipKeyDe.setColumns(10);
		ipKeyDe.setBounds(55, 13, 154, 20);
		panel_2.add(ipKeyDe);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setLayout(null);
		panel_3_2.setBounds(10, 56, 414, 88);
		panel_2.add(panel_3_2);
		
		JPanel panel_4_2 = new JPanel();
		panel_4_2.setBackground(Color.LIGHT_GRAY);
		panel_4_2.setBounds(0, 0, 414, 26);
		panel_3_2.add(panel_4_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Cipher Text");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_4_2.add(lblNewLabel_2_2);
		
		JTextArea textAreaCipherDe = new JTextArea();
		textAreaCipherDe.setBounds(0, 29, 414, 59);
		panel_3_2.add(textAreaCipherDe);
		
		JPanel panel_3_1_1 = new JPanel();
		panel_3_1_1.setLayout(null);
		panel_3_1_1.setBounds(10, 171, 414, 88);
		panel_2.add(panel_3_1_1);
		
		JPanel panel_4_1_1 = new JPanel();
		panel_4_1_1.setBackground(Color.LIGHT_GRAY);
		panel_4_1_1.setBounds(0, 0, 414, 26);
		panel_3_1_1.add(panel_4_1_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Plain Text");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_4_1_1.add(lblNewLabel_2_1_1);
		
		JTextArea textAreaPlainDe = new JTextArea();
		textAreaPlainDe.setBounds(0, 29, 414, 59);
		panel_3_1_1.add(textAreaPlainDe);
		
		JButton chooseFileDe = new JButton("Chọn File");
		chooseFileDe.addActionListener(new ActionListener() {
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
			                textAreaCipherDe.setText(fileContent.trim());
			            } else {
			                try {
			                    byteData = FileController.readBytesFromFile(selectedFile);
			                    textAreaCipherDe.setText("Decrypt file: " + selectedFile.getAbsolutePath());
			                } catch (IOException e) {
			                    JOptionPane.showMessageDialog(null, "Lỗi không đọc được file");
			                }
			            }
			        } 
			    }
			}


		});
		chooseFileDe.setBounds(486, 121, 89, 33);
		panel_2.add(chooseFileDe);
		
		JButton btnDecrypt = new JButton("Giải mã");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onDecrypt();
			}

			private void onDecrypt() {
			    try {
			        String cipherText = textAreaCipherDe.getText().trim();

			        if (cipherText.isEmpty()) {
			            JOptionPane.showMessageDialog(null, "Không có gì để giải mã");
			            return;
			        }

			        if (des.getKey() == null) {
			            JOptionPane.showMessageDialog(null, "Nhập key trước khi giải mã");
			            return;
			        }

			        if (cipherText.startsWith("Decrypt file: ")) {
			            // Giải mã tệp tin
			            JFileChooser fileChooser = new JFileChooser();
			            int returnValue = fileChooser.showSaveDialog(null);
			            
			            if (returnValue == JFileChooser.APPROVE_OPTION) {
			                File selectedFile = fileChooser.getSelectedFile();
			                String destFile = selectedFile.getAbsolutePath();
			                
			                des.decryptFile(cipherText.substring("Decrypt file: ".length()), destFile);
			                textAreaPlainDe.setText("Mã hóa File thành công" + "\n"
			                		+ "Đã lưu vào: " + destFile);
			            }
			        } else {
			            // Giải mã chuỗi
			            String decrypt = des.decryptFromBase64(cipherText);
			            textAreaPlainDe.setText(decrypt);
			        }
			    } catch (Exception e) {
			        JOptionPane.showMessageDialog(null, "Không thể giải mã, vui lòng thử lại");
			    }
			}

		});
		btnDecrypt.setBounds(486, 189, 89, 33);
		panel_2.add(btnDecrypt);
		
		JButton choosekeyDe = new JButton("Chọn Key");
		choosekeyDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseKey();
			}

			private void chooseKey() {
			    File choose = FileController.chooseFile();
			    if (choose != null) {
			        try {
			            String keyString = (String) FileController.readObjectFile(choose.getAbsolutePath());
			            byte[] keyBytes = Base64.getDecoder().decode(keyString);
			            SecretKey key = new SecretKeySpec(keyBytes, "DES");
			            des.setKey(key);
			            noteKey();
			        } catch (ClassNotFoundException | IOException e) {
			            e.printStackTrace();
			        }
			    }
			}

			private void noteKey() {
				ipKeyDe.setText(des.exportKey());
				
			}
		});
		choosekeyDe.setBounds(240, 12, 89, 23);
		panel_2.add(choosekeyDe);
	}
}
