package view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import fileControll.FileController;
import model.RSA;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.Base64;
import java.awt.event.ActionEvent;

public class PanelRSA extends JPanel {
	private JTextField ipPublicKey;
	private JLabel ipPrivateKeyEn;
	private JComboBox comboBox;
	private RSA rsa = new RSA();

	
	public PanelRSA() {
		setSize(680, 406);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(0, 0, 680, 80);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("RSA");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(303, 21, 88, 37);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 80, 680, 327);
		add(panel_1);
		panel_1.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 670, 316);
		panel_1.add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Encrypt", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Public Key");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 11, 68, 14);
		panel_2.add(lblNewLabel_1);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1024", "2048", "4096"}));
		comboBox.setBounds(80, 8, 68, 22);
		panel_2.add(comboBox);
		
		ipPublicKey = new JTextField();
		ipPublicKey.setBounds(158, 9, 203, 20);
		panel_2.add(ipPublicKey);
		ipPublicKey.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Private Key");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(371, 11, 68, 14);
		panel_2.add(lblNewLabel_1_1);
		
		ipPrivateKeyEn = new JLabel("ipPrivate");
		ipPrivateKeyEn.setBounds(449, 9, 203, 20);
		panel_2.add(ipPrivateKeyEn);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 55, 414, 88);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(192, 192, 192));
		panel_5.setBounds(0, 0, 414, 26);
		panel_4.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Plain Text");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(179, 10, 60, 14);
		panel_5.add(lblNewLabel_2);
		
		JTextArea textAreaPlainEn = new JTextArea();
		textAreaPlainEn.setBounds(0, 25, 414, 63);
		panel_4.add(textAreaPlainEn);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setLayout(null);
		panel_4_1.setBounds(10, 177, 414, 88);
		panel_2.add(panel_4_1);
		
		JPanel panel_5_1 = new JPanel();
		panel_5_1.setLayout(null);
		panel_5_1.setBackground(Color.LIGHT_GRAY);
		panel_5_1.setBounds(0, 0, 414, 26);
		panel_4_1.add(panel_5_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Cipher Text");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(179, 10, 77, 14);
		panel_5_1.add(lblNewLabel_2_1);
		
		JTextArea textAreaCipherEn = new JTextArea();
		textAreaCipherEn.setBounds(0, 25, 414, 63);
		panel_4_1.add(textAreaCipherEn);
		
		JButton btnCreateKey = new JButton("Tạo Key");
		btnCreateKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createKey();
			}

			private void createKey() {
				int[] keyLenghts = { 1024, 2048, 4096 };
				int keyLenght = keyLenghts[comboBox.getSelectedIndex()];
				rsa.genKey(keyLenght);
				String publicKey = Base64.getEncoder().encodeToString(rsa.getPublicKey().getEncoded());
				String privateKey = Base64.getEncoder().encodeToString(rsa.getPrivateKey().getEncoded());
				ipPublicKey.setText(publicKey);
				ipPrivateKeyEn.setText(privateKey);
			}		
		});
		btnCreateKey.setBounds(459, 55, 89, 40);
		panel_2.add(btnCreateKey);
		
		JButton btnSaveKey = new JButton("Lưu Key");
		btnSaveKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveKey();
			}

			private void saveKey() {
				Key key = rsa.getPrivateKey();
				FileController.saveObjectToFile(key);
			}
		});
		btnSaveKey.setBounds(459, 144, 89, 40);
		panel_2.add(btnSaveKey);
		
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
				if (rsa.getPrivateKey() == null) {
					JOptionPane.showMessageDialog(null, "Thêm hoặc tạo khóa mới");
				} else {
					String encrypt = rsa.encrypt(textAreaPlainEn.getText());
					textAreaCipherEn.setText(encrypt);
					}
				}
		});
		btnEncrypt.setBounds(459, 225, 89, 40);
		panel_2.add(btnEncrypt);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Decrypt", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Private Key");
		lblNewLabel_1_1_1.setBounds(20, 13, 68, 16);
		lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		panel_3.add(lblNewLabel_1_1_1);
		
		JTextField ipPrivateKeyDe = new JTextField();
		ipPrivateKeyDe.setBounds(93, 11, 542, 20);
		ipPrivateKeyDe.setColumns(10);
		panel_3.add(ipPrivateKeyDe);
		
		JPanel panel_4_2 = new JPanel();
		panel_4_2.setLayout(null);
		panel_4_2.setBounds(20, 58, 414, 88);
		panel_3.add(panel_4_2);
		
		JPanel panel_5_2 = new JPanel();
		panel_5_2.setLayout(null);
		panel_5_2.setBackground(Color.LIGHT_GRAY);
		panel_5_2.setBounds(0, 0, 414, 26);
		panel_4_2.add(panel_5_2);
		
		JLabel lblNewLabel_2_2 = new JLabel("Cipher Text");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_2.setBounds(179, 10, 69, 14);
		panel_5_2.add(lblNewLabel_2_2);
		
		JTextArea textAreaCipherDe = new JTextArea();
		textAreaCipherDe.setBounds(0, 25, 414, 63);
		panel_4_2.add(textAreaCipherDe);
		
		JPanel panel_4_3 = new JPanel();
		panel_4_3.setLayout(null);
		panel_4_3.setBounds(20, 189, 414, 88);
		panel_3.add(panel_4_3);
		
		JPanel panel_5_3 = new JPanel();
		panel_5_3.setLayout(null);
		panel_5_3.setBackground(Color.LIGHT_GRAY);
		panel_5_3.setBounds(0, 0, 414, 26);
		panel_4_3.add(panel_5_3);
		
		JLabel lblNewLabel_2_3 = new JLabel("Plain Text");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2_3.setBounds(179, 10, 60, 14);
		panel_5_3.add(lblNewLabel_2_3);
		
		JTextArea textAreaPlainDe = new JTextArea();
		textAreaPlainDe.setBounds(0, 25, 414, 63);
		panel_4_3.add(textAreaPlainDe);
		
		JButton btnChooseKey = new JButton("Chọn Key");
		btnChooseKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseKey();
			}

			private void chooseKey() {
				File choose = FileController.chooseFile();
				if (choose != null) {
					try {
						Key key = (Key) FileController.readObjectFile(choose.getAbsolutePath());
						if (key.getAlgorithm().equalsIgnoreCase("RSA")) {
							String baseKey = Base64.getEncoder().encodeToString(key.getEncoded());
							ipPrivateKeyDe.setText(baseKey);
						}
					} catch (ClassNotFoundException | IOException e) {}
				}
				
			}
		});
		btnChooseKey.setBounds(487, 58, 107, 40);
		panel_3.add(btnChooseKey);
		
		JButton btnDecrypt = new JButton("Giải Mã");
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					onDecrypt();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private void onDecrypt() throws Exception {
				if (rsa.getPrivateKey() == null) {
					JOptionPane.showMessageDialog(null, "Thêm hoặc tạo key mới");
				} else {
					String cipherText = textAreaCipherDe.getText();
						textAreaPlainDe.setText(rsa.dencrypt(cipherText));
					}
				}
		});
		btnDecrypt.setBounds(487, 148, 107, 40);
		panel_3.add(btnDecrypt);
	}
}
