package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class DES {
private SecretKey key;
	
public SecretKey getKey() {
	return key;
}

public void setKey(SecretKey key) {
	this.key = key;
}

	public SecretKey createKey() throws NoSuchAlgorithmException{
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
		keyGenerator.init(56);
		key = keyGenerator.generateKey();
		return key;
	}
	
	public byte[] encrypt(String text) throws Exception{
		if(key == null) return new byte[] {};
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] plaintext = text.getBytes("UTF-8");
		byte[] cipherText = cipher.doFinal(plaintext);
		return cipherText;
	}
	
	//encrypt String and return base64 string
	public String encryptToBase64(String text) throws Exception{
		if(key == null) return "";
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] plaintext = text.getBytes("UTF-8");
		byte[] cipherText = cipher.doFinal(plaintext);
		return Base64.getEncoder().encodeToString(cipherText);
	}
	
	//decrcypt 
	public String decrypt(byte[] text) throws Exception{
		if(key == null) return null;
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] plainText = cipher.doFinal(text);
		String output = new String(plainText,"UTF-8");
		return output;
	}
	
	public String decryptFromBase64(String text) throws Exception{
		if(key == null) return null;
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] plaintext = cipher.doFinal(Base64.getDecoder().decode(text));
		String output = new String(plaintext,"UTF-8");
		return output;
	}
	
	public String exportKey() {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}
	
	//File 
	public void encryptFile(String sourceFile, String destFile) throws Exception{
		if(key == null) throw new FileNotFoundException("Key not Found");
		File file = new File(sourceFile);
		if(file.isFile()) {
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(destFile);
			
			byte[] input = new byte[64];
			int bytesRead;
			
			while((bytesRead = fis.read(input)) != -1) {
				byte[] output = cipher.update(input, 0, bytesRead);
				if(output != null)
					fos.write(output);
			}
			byte[] output = cipher.doFinal();
			if(output != null) {
				fos.write(output);
			}
			fis.close();
			fos.flush();
			fos.close();
			System.out.println("Encrypted");
		}
	}
	public void decryptFile(String sourceFile, String destFile) throws Exception{
		if(key == null) throw new FileNotFoundException("Key not Found");
		File file = new File(sourceFile);
		if(file.isFile()) {
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(destFile);
			
			byte[] input = new byte[64];
			int readByte = 0;
			
			while((readByte = fis.read(input)) != -1) {
				byte[] output = cipher.update(input,0,readByte);
				if(output != null)
					fos.write(output);
			}
			byte[] output = cipher.doFinal();
			if(output != null) {
				fos.write(output);
			}
			fis.close();
			fos.flush();
			fos.close();
			System.out.println("Decrypted");
		}
	}
	
	public static void main(String[] args) throws Exception {
		DES des = new DES();
		des.createKey();
		String out = des.encryptToBase64("Khoa Công nghệ thông tin");
		//lấy key
		System.out.println(des.exportKey());
		//mã hóa
		System.out.println(out);
		//giải mã
		System.out.println(des.decryptFromBase64(out));
		
		des.encryptFile("C:\\Users\\LAPTOP\\eclipse-workspace\\ATVBM\\src\\bt1.zip", "C:\\Users\\LAPTOP\\eclipse-workspace\\ATVBM\\src\\bt2.zip");
		des.decryptFile("C:\\Users\\LAPTOP\\eclipse-workspace\\ATVBM\\src\\bt2.zip", "C:\\Users\\LAPTOP\\eclipse-workspace\\ATVBM\\src\\bt3.zip");
	}
}
