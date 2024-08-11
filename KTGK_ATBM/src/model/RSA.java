package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class RSA {
	private KeyPair keyPair;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	
	public String encrypt(String text) throws Exception{
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] output = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(output);
	}
	
	public String dencrypt(String text) throws Exception{
		
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] output = cipher.doFinal(Base64.getDecoder().decode(text));
		return new String(output,StandardCharsets.UTF_8);
	}
	
	public void EncryptFile(String source, String dest) throws Exception{
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(256);
		byte[] iv = new byte[16];
		IvParameterSpec spec = new IvParameterSpec(iv);
		SecretKey secretKey = keyGen.generateKey();
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(cipher.ENCRYPT_MODE, secretKey, spec);
		
		CipherInputStream inputStream = new CipherInputStream(new BufferedInputStream(new FileInputStream(source)), cipher);
		DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dest)));
		
		String keyString = Base64.getEncoder().encodeToString(secretKey.getEncoded());
		dataOutputStream.writeUTF(encrypt(keyString));
		dataOutputStream.writeLong(new File(source).length());
		dataOutputStream.writeUTF(Base64.getEncoder().encodeToString(iv));
		
		byte[] buff = new byte[1024];
		int i;
		while((i=inputStream.read(buff))!=-1) {
			dataOutputStream.write(buff, 0, i);
		}
		inputStream.close();
		dataOutputStream.flush();
		dataOutputStream.close();
	}
	
	public void DecryptFile(String source, String dest) throws Exception{
		DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(source)));
		String keyString = dis.readUTF();
		long size = dis.readLong();
		byte[] iv = Base64.getDecoder().decode(dis.readUTF());
		
		SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(dencrypt(keyString)), "AES");
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
		CipherInputStream cis = new CipherInputStream(dis, cipher);
		BufferedOutputStream bof = new BufferedOutputStream(new FileOutputStream(dest));
		
		byte[] buff = new byte[1024];
		int i;
		while((i=cis.read(buff))!=-1) {
			bof.write(buff, 0, i);
		}
		cis.close();
		bof.close();
	}
	
	//create Key
	public void genKey(int sizeKey) {
		KeyPairGenerator keyGenerator = null;
		try {
			keyGenerator = KeyPairGenerator.getInstance("RSA");
			keyGenerator.initialize(sizeKey);
			keyPair = keyGenerator.generateKeyPair();
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public KeyPair getKeyPair() {
		return keyPair;
	}

	public void setKeyPair(KeyPair keyPair) {
		this.keyPair = keyPair;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}

	public PrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
	public static void main(String[] args) throws Exception {
		RSA rsa = new RSA();
		rsa.genKey(1024);
//		System.out.println(rsa.encrypt("CNTT"));
//		System.out.println(rsa.dencrypt(rsa.encrypt("CNTT")));
		rsa.EncryptFile("E:\\f8-shop\\Firebase\\Firebase.rar", "E:\\f8-shop\\Firebase\\Firebase1.rar");
		rsa.DecryptFile("E:\\f8-shop\\Firebase\\Firebase1.rar", "E:\\f8-shop\\Firebase\\Firebase3.rar");
	}
}
