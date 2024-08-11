package view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.JTextField;

import model.Ceaser;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import fileControll.FileController;

import java.awt.Button;
import java.awt.Panel;
import java.awt.Label;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.awt.event.ActionEvent;

public class PanelCaeser extends JPanel {
	private JTextField ipKey_1;
	private JTextField ipKey_2;
	private Ceaser ceaser = new Ceaser();
	

	/**
	 * Create the panel.
	 */
	public PanelCaeser() {
		setSize(680, 406);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 680, 80);
		add(panel);
		
		JLabel lblNewLabel = new JLabel("Ceaser");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
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
		
		JLabel lblNewLabel_1 = new JLabel("Key:");
		lblNewLabel_1.setBounds(10, 11, 49, 20);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		ipKey_1 = new JTextField();
		ipKey_1.setBounds(55, 13, 154, 20);
		ipKey_1.setColumns(10);
		enPanel.setLayout(null);
		enPanel.add(lblNewLabel_1);
		enPanel.add(ipKey_1);
		
		ButtonGroup btnGroups = new ButtonGroup();
		
		JRadioButton checkVN = new JRadioButton("VietNamese");
		checkVN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ceaser.setSctLanguage(checkVN.getText());
			}
		});
		checkVN.setFont(new Font("Tahoma", Font.BOLD, 16));
		checkVN.setBounds(495, 12, 137, 23);
		enPanel.add(checkVN);
		btnGroups.add(checkVN);
		
		JRadioButton checkEnglist = new JRadioButton("English", true);
		checkEnglist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ceaser.setSctLanguage(checkEnglist.getText());
			}
		});
		checkEnglist.setFont(new Font("Tahoma", Font.BOLD, 16));
		checkEnglist.setForeground(Color.BLACK);
		checkEnglist.setBounds(379, 12, 111, 23);
		enPanel.add(checkEnglist);
		btnGroups.add(checkEnglist);
		
		Panel panel_2 = new Panel();
		panel_2.setBounds(10, 55, 414, 88);
		enPanel.add(panel_2);
		panel_2.setLayout(null);
		
		Panel panel_3 = new Panel();
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_3.setBounds(0, 0, 414, 26);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		Label label = new Label("Plain Text");
		label.setFont(new Font("Dialog", Font.BOLD, 12));
		label.setBounds(156, 0, 95, 21);
		panel_3.add(label);
		
		JTextArea plainText = new JTextArea();
		plainText.setBounds(0, 29, 414, 59);
		panel_2.add(plainText);
		
		Panel panel_2_1 = new Panel();
		panel_2_1.setLayout(null);
		panel_2_1.setBounds(10, 161, 414, 88);
		enPanel.add(panel_2_1);
		
		Panel panel_3_1 = new Panel();
		panel_3_1.setLayout(null);
		panel_3_1.setBackground(Color.LIGHT_GRAY);
		panel_3_1.setBounds(0, 0, 414, 26);
		panel_2_1.add(panel_3_1);
		
		Label label_1 = new Label("Cipher Text");
		label_1.setFont(new Font("Dialog", Font.BOLD, 12));
		label_1.setBounds(154, 0, 114, 21);
		panel_3_1.add(label_1);
		
		JTextArea cipherText = new JTextArea();
		cipherText.setBounds(0, 29, 414, 59);
		panel_2_1.add(cipherText);
		
		JButton btnFile = new JButton("Chọn File");
		btnFile.addActionListener(new ActionListener() {
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
		btnFile.setBounds(486, 55, 89, 33);
		enPanel.add(btnFile);
		
		JButton btnEncrypt = new JButton("Mã hóa");
		btnEncrypt.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String str = ipKey_1.getText();
		        int key = Integer.parseInt(str);
		        String text = plainText.getText();
		        Ceaser ceaser = new Ceaser();  // Create an instance of the Ceaser class

		        if (text.startsWith("Encrypt file:")) {
		            // Mã hóa tệp tin
		            JFileChooser fileChooser = new JFileChooser();
		            int data = fileChooser.showSaveDialog(null);

		            if (data == JFileChooser.APPROVE_OPTION) {
		                File selectedFile = fileChooser.getSelectedFile();
		                String destFile = selectedFile.getAbsolutePath();

		                ceaser.encryptFile(key, text.substring("Encrypt file: ".length()), destFile);
		                cipherText.setText("Mã hóa File thành công" + "\n"
		                        + "Đã lưu vào: " + destFile);
		            }
		        } else {
		            if (ceaser.getSctLanguage().equals("English")) {
		                cipherText.setText(ceaser.encrypt_En(key, text));
		            } else if (ceaser.getSctLanguage().equals("VietNamese")) {
		                cipherText.setText(ceaser.encryptVN(key, text));
		            }
		        }
		    }
		});

		btnEncrypt.setBounds(486, 188, 89, 33);
		enPanel.add(btnEncrypt);
		
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
		JRadioButton enCheck_2 = new JRadioButton("English");
		enCheck_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ceaser.setSctLanguage("English");
			}
		});
		enCheck_2.setBounds(379, 12, 111, 23);
		enCheck_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGroups_1.add(enCheck_2);
		dePanel.add(enCheck_2);
		
		JRadioButton vnCheck_2 = new JRadioButton("VietNamese");
		vnCheck_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ceaser.setSctLanguage("VietNamese");
			}
		});
		vnCheck_2.setBounds(495, 12, 137, 23);
		vnCheck_2.setForeground(Color.BLACK);
		vnCheck_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnGroups_1.add(vnCheck_2);
		dePanel.add(vnCheck_2);
		
		Panel panel_2_2 = new Panel();
		panel_2_2.setLayout(null);
		panel_2_2.setBounds(10, 55, 414, 88);
		dePanel.add(panel_2_2);
		
		Panel panel_3_2 = new Panel();
		panel_3_2.setLayout(null);
		panel_3_2.setBackground(Color.LIGHT_GRAY);
		panel_3_2.setBounds(0, 0, 414, 26);
		panel_2_2.add(panel_3_2);
		
		Label label_2 = new Label("CipherText");
		label_2.setFont(new Font("Dialog", Font.BOLD, 12));
		label_2.setBounds(144, 0, 117, 21);
		panel_3_2.add(label_2);
		
		JTextArea cipherTextDe = new JTextArea();
		cipherTextDe.setBounds(0, 29, 414, 59);
		panel_2_2.add(cipherTextDe);
		
		Panel panel_2_1_1 = new Panel();
		panel_2_1_1.setLayout(null);
		panel_2_1_1.setBounds(10, 161, 414, 88);
		dePanel.add(panel_2_1_1);
		
		Panel panel_3_1_1 = new Panel();
		panel_3_1_1.setLayout(null);
		panel_3_1_1.setBackground(Color.LIGHT_GRAY);
		panel_3_1_1.setBounds(0, 0, 414, 26);
		panel_2_1_1.add(panel_3_1_1);
		
		Label label_1_1 = new Label("Plain Text");
		label_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		label_1_1.setBounds(146, 0, 114, 26);
		panel_3_1_1.add(label_1_1);
		
		JTextArea plainTextDe = new JTextArea();
		plainTextDe.setBounds(0, 29, 414, 59);
		panel_2_1_1.add(plainTextDe);
		
		JButton btnFile_1 = new JButton("Chọn File");
		btnFile_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					chooseFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private void chooseFile() throws IOException {
				File selectedFile = FileController.chooseFile();

			    if (selectedFile != null) {
			        String fileName = selectedFile.getName();
			        int lastDotIndex = fileName.lastIndexOf(".");

			        if (lastDotIndex != -1) {
			            String fileExtension = fileName.substring(lastDotIndex + 1).toLowerCase();

			            if ("txt".equals(fileExtension)) {
			                String fileContent = FileController.readTextFile(selectedFile.getAbsolutePath());
			                cipherTextDe.setText(fileContent.trim());
			            } else {
			                cipherTextDe.setText("Decrypt file: " + selectedFile.getAbsolutePath());
			            }
			        } 
			    }
			}
		});
		btnFile_1.setBounds(486, 55, 89, 33);
		dePanel.add(btnFile_1);
		
		JButton btnEncrypt_1 = new JButton("Giải mã");
		btnEncrypt_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = ipKey_2.getText();
				int key = Integer.parseInt(str);
				String text = cipherTextDe.getText();
				 if (text.startsWith("Decrypt file: ")) {
			            // Giải mã tệp tin
			            JFileChooser fileChooser = new JFileChooser();
			            int data = fileChooser.showSaveDialog(null);
			            
			            if (data == JFileChooser.APPROVE_OPTION) {
			                File selectedFile = fileChooser.getSelectedFile();
			                String destFile = selectedFile.getAbsolutePath();
			                
			                ceaser.decryptFile(key, text.substring("Decrypt file: ".length()), destFile);
			                plainTextDe.setText("Mã hóa File thành công" + "\n"
			                		+ "Đã lưu vào: " + destFile);
			            }
			        } else {
			        	
			        	if(ceaser.getSctLanguage().equals("English")) {
			        		
			        		plainTextDe.setText(ceaser.decrypt_En(key, text));
			        		
			        	}else if(ceaser.getSctLanguage().equals("VietNamese")) {
			        		plainTextDe.setText(ceaser.decryptVN(key, cipherTextDe.getText()));
			        	}
			        }
			}
		});
		btnEncrypt_1.setBounds(486, 188, 89, 33);
		dePanel.add(btnEncrypt_1);
		
		
	}
}
