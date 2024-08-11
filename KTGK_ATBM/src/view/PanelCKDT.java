package view;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;

import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.CKDT;
import model.Hash;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelCKDT extends JPanel {
	private JTextField textFieldPrivateKey;
	private JTextField textFieldPublicKey;
	private JTextField textFieldInput;
	static int bitleg = 256;
	private JFileChooser chooser = new JFileChooser();
	private Hash hash = new Hash();
	private CKDT ckdt = new CKDT();
	private JButton buttonSign;
	private JButton buttonCheck;
	private JButton itemSavePrivateKey;
	private JButton itemSavePublicKey,itemSaveDataSigned;

	
	public PanelCKDT() {
		setSize(680, 406);
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(0, 0, 680, 80);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Chữ Ký Điện Tử");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(187, 11, 277, 58);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Private key: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(10, 91, 87, 21);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Public Key: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 123, 76, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Input:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(311, 95, 50, 14);
		add(lblNewLabel_3);
		
		textFieldPrivateKey = new JTextField();
		textFieldPrivateKey.setEnabled(false);
		textFieldPrivateKey.setBounds(86, 91, 199, 20);
		add(textFieldPrivateKey);
		textFieldPrivateKey.setColumns(10);
		
		textFieldPublicKey = new JTextField();
		textFieldPublicKey.setEnabled(false);
		textFieldPublicKey.setColumns(10);
		textFieldPublicKey.setBounds(86, 121, 199, 20);
		add(textFieldPublicKey);
		
		textFieldInput = new JTextField();
		textFieldInput.setColumns(10);
		textFieldInput.setBounds(354, 91, 199, 20);
		add(textFieldInput);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 158, 414, 88);
		add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 414, 26);
		panel_2.setBackground(new Color(192, 192, 192));
		panel_1.add(panel_2);
		
		JLabel lblNewLabel_4 = new JLabel("PlainText");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_2.add(lblNewLabel_4);
		
		JTextArea textAreaInput = new JTextArea();
		textAreaInput.setBounds(0, 26, 414, 62);
		panel_1.add(textAreaInput);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(10, 257, 414, 88);
		add(panel_1_1);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(Color.LIGHT_GRAY);
		panel_2_1.setBounds(0, 0, 414, 26);
		panel_1_1.add(panel_2_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("CipherText");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_2_1.add(lblNewLabel_4_1);
		
		JTextArea textAreaOutput = new JTextArea();
		textAreaOutput.setBounds(0, 26, 414, 62);
		panel_1_1.add(textAreaOutput);
		
		JButton btnNewButton = new JButton("Tạo key");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ckdt.KeyRSA(bitleg);
		    	textFieldPrivateKey.setText(ckdt.getD().toString());
		    	textFieldPublicKey.setText(ckdt.getE().toString());
		    	ckdt.setD(new BigInteger(textFieldPrivateKey.getText()));
		    	ckdt.setE(new BigInteger(textFieldPublicKey.getText()));
		    	buttonSign.setEnabled(true);
		    	buttonCheck.setEnabled(true);
		    	itemSavePublicKey.setEnabled(true);
		    	itemSavePrivateKey.setEnabled(true);
			}
		});
		btnNewButton.setBounds(303, 120, 89, 23);
		add(btnNewButton);
		
		itemSavePrivateKey = new JButton("Lưu Privete Key");
		itemSavePrivateKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigInteger d1 =new BigInteger(textFieldPrivateKey.getText());
		    	
		    	try {
		    		OutputWrite(getSaveLocation(), d1, "privatekey.txt");
		    	}catch(FileNotFoundException ex) {}
			}
		});
		itemSavePrivateKey.setBounds(402, 120, 119, 23);
		add(itemSavePrivateKey);
		
		itemSavePublicKey = new JButton("Lưu Public Key");
		itemSavePublicKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				savePublicKey();
			}

			private void savePublicKey() {
				BigInteger e1 =new BigInteger(textFieldPublicKey.getText());
		    	try {
		    		OutputWrite(getSaveLocation(), e1, "publickey.txt");
		    	}catch(FileNotFoundException ex) {
		    		
		    	}
				
			}
		});
		itemSavePublicKey.setBounds(531, 120, 107, 23);
		add(itemSavePublicKey);
		
		JButton btnEn = new JButton("Mã hóa");
		btnEn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filename = textFieldInput.getText();
		    	filename = filename.replace('\\', '/');
		    	if("".equals(filename)) {
		    		JOptionPane.showMessageDialog(null, "NO INPUT DATA", "Notification",JOptionPane.ERROR_MESSAGE);
		    	}else {
		    		try {
		    			BigInteger sha1 = new BigInteger(hash.md(filename).abs().toString());
		    			textAreaInput.setText(sha1.toString());
		    		}catch(Exception ex) {}
		    	}
		    	buttonCheck.setEnabled(true);
		    	buttonSign.setEnabled(true);
			}
		});
		btnEn.setBounds(20, 356, 89, 23);
		add(btnEn);
		
		buttonSign = new JButton("Ký");
		buttonSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ckdt.setD(new BigInteger(textFieldPrivateKey.getText()));
		    	System.out.println(""+ckdt.getD());
		    	BigInteger sha2 = new BigInteger(textAreaInput.getText());
		    	textAreaOutput.setText(ckdt.encrypt(sha2).toString());
		    	itemSaveDataSigned.setEnabled(true);
		    	JOptionPane.showMessageDialog(null, "You signed up successfully...!");
			}
		});
		buttonSign.setBounds(127, 356, 50, 23);
		add(buttonSign);
		
		buttonCheck = new JButton("Kiểm tra");
		buttonCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textAreaInput.getText().equals(textAreaOutput.getText())) {
		            JOptionPane.showMessageDialog(null, "The data is not changed", "Notification", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            JOptionPane.showMessageDialog(null, "Data has been changed", "Notification", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		buttonCheck.setBounds(196, 356, 89, 23);
		add(buttonCheck);
		
		//lấy publickey 
		JButton btnNewButton_6 = new JButton("Lấy Public key");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					choosePublicKey();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private void choosePublicKey() throws IOException {
				FileReader fr = new FileReader(getOpenLocation());
		    	BufferedReader br = new BufferedReader(fr);
		    	String s = null, str;
				while((str = br.readLine())!=null) {
		    		s = s + " " + str;
		    	}
				String[] st = s.split(" ");
		    	br.close();
		    	fr.close();
		    	textFieldPublicKey.setText(st[1]);
			}
		});
		btnNewButton_6.setBounds(434, 158, 132, 23);
		add(btnNewButton_6);
		
		JButton btnNewButton_6_1 = new JButton("Lấy Private key");
		btnNewButton_6_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					choosePrivateKey();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private void choosePrivateKey() throws IOException {
				FileReader fr = new FileReader(getOpenLocation());
		    	BufferedReader br = new BufferedReader(fr);
		    	String s = null, str;
				while((str = br.readLine())!=null) {
		    		s = s + " " + str;
		    	}
				String[] st = s.split(" ");
		    	br.close();
		    	fr.close();
		    	textFieldPrivateKey.setText(st[1]);
			}
		});
		btnNewButton_6_1.setBounds(434, 207, 132, 23);
		add(btnNewButton_6_1);
		
		//Lấy file
		JButton btnNewButton_6_2 = new JButton("Lấy file");
		btnNewButton_6_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputFile();
			}

			private void inputFile() {
				JFileChooser chooser = new JFileChooser();
		    	FileNameExtensionFilter filter = new FileNameExtensionFilter("pdf", "docx", "doc", "exe", "xls", "xlsx", "jpg", "png", "mp3", "mp4", "flv", "ico", "txt");
		    	chooser.setFileFilter(filter);
		    	int returnVal = chooser.showOpenDialog(null);
		    	if(returnVal == JFileChooser.APPROVE_OPTION) {
		    		File file = chooser.getSelectedFile();
		    		String attach = file.toString();
		    		textFieldInput.setText(attach);
		    	}
		    	btnEn.setEnabled(true);
				
			}
		});
		btnNewButton_6_2.setBounds(434, 252, 132, 23);
		add(btnNewButton_6_2);
		
		//Lấy file đã ký
		itemSaveDataSigned = new JButton("Lấy file đã ký");
		itemSaveDataSigned.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					inputDataSigned();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			private void inputDataSigned() throws IOException {
				FileReader fr = new FileReader(getOpenLocation());
				BufferedReader br = new BufferedReader(fr);
				String str = br.readLine();
				br.close();
				fr.close();
		    	textAreaInput.setText(str);
			}
		});
		itemSaveDataSigned.setBounds(434, 297, 132, 23);
		add(itemSaveDataSigned);
		
		//Lưu file đã ký
		JButton btnNewButton_6_4 = new JButton("Lưu file đã key");
		btnNewButton_6_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BigInteger bigInteger = new BigInteger(textAreaOutput.getText());
		        try {
		            OutputWrite(getSaveLocation(), bigInteger, "DigitalSignature.txt");
		        } catch (FileNotFoundException ex) {}
			}
		});
		btnNewButton_6_4.setBounds(303, 356, 132, 23);
		add(btnNewButton_6_4);
		
		//reset
		JButton btnReset = new JButton("Làm mới");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onReset();
			}

			private void onReset() {
				textFieldPrivateKey.setText("");
		    	textFieldPublicKey.setText("");
		    	textFieldInput.setText("");
			}
		});
		btnReset.setBounds(477, 356, 89, 23);
		add(btnReset);
		
		JButton btnDe = new JButton("Giải mã");
		btnDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onDecrypt();
			}

			private void onDecrypt() {
				ckdt.setE(new BigInteger(textFieldPublicKey.getText()));
		    	System.out.println(""+ckdt.getE());
		    	BigInteger dsrsa = new BigInteger(textAreaInput.getText().toString(),10);
		        textAreaOutput.setText(ckdt.decrypt(dsrsa).toString());
				
			}
		});
		btnDe.setBounds(576, 356, 89, 23);
		add(btnDe);
	}
	public void OutputWrite (File saveLocation, BigInteger EncryptCodes, String name)
			throws FileNotFoundException{
				PrintWriter file = new PrintWriter(new FileOutputStream(new File(saveLocation, name)));
				file.println(EncryptCodes);
				
				file.close();
			}
	
	
	public File getSaveLocation() {
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		if(chooser.showSaveDialog(chooser) == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		}else return null;
	}
	public File getOpenLocation() {
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		if(chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		}else return null;
	}
}
