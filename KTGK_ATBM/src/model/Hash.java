package model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.SecureRandom;



public class Hash {
	private static int BUFFER_SIZE = 32 * 1024;
	public static String SHA_1 = "SHA-1";
	public static String SHA_224 = "SHA-224";
	public static String SHA_256 = "SHA-256";
	public static String SHA_384 = "SHA-384";
	public static String SHA_512_224 = "SHA-512/224";
	
	private String name;
	private MessageDigest md;
	public Hash(String name) {
		// TODO Auto-generated constructor stub
	}

	public Hash() {
		// TODO Auto-generated constructor stub
	}

	public String checkSum(String input) {
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			
			return number.toString(16);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public String hashFile(String path) throws Exception{
		MessageDigest digest = MessageDigest.getInstance("MD5");
		InputStream is = new BufferedInputStream(new FileInputStream(path));
		DigestInputStream dis = new DigestInputStream(is, digest);
		
		byte[] buffer = new byte[1024];
		int read;
		do {
			read = dis.read(buffer);
		} while (read != -1);
		BigInteger number = new BigInteger(1, dis.getMessageDigest().digest());
		
		return number.toString(16);
	}
	
	//SHA
	public String hash(String input) {return hash(input, SHA_256);}
	
	public String hash(String input, String algorithms) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithms);
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashText = number.toString(16);
			
			return hashText;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "";
	}
	public static Hash getIntance(String name) {
		Hash hasing = new Hash(name);
		return hasing;
	}
	
	// tính giá trị băm 
	public BigInteger md(String f) throws Exception {
	    BufferedInputStream file = new BufferedInputStream(new FileInputStream(f));
	    md = MessageDigest.getInstance("SHA-1");
	    DigestInputStream in = new DigestInputStream(file, md);
	    //đọc và băm dự liệu
	    int i;
	    byte[] buffer = new byte[BUFFER_SIZE];
	    do {
	      i = in.read(buffer, 0, BUFFER_SIZE);
	    } while (i == BUFFER_SIZE);
	    md = in.getMessageDigest();
	    in.close();

	    return new BigInteger(md.digest());
	  }
	
	public static void main(String[] args) {
		Hash hash = new Hash();

        // Test checkSum function, mã hóa checkSum()
        String inputForCheckSum = "HelloWorld";
        String checksumResult = hash.checkSum(inputForCheckSum);
        System.out.println("Checksum result for \"" + inputForCheckSum + "\": " + checksumResult);

        // Test hashFile function (mã hóa một file)
        String filePath = "path/to/your/file.txt";
        try {
            String fileHashResult = hash.hashFile(filePath);
            System.out.println("Hash result for file \"" + filePath + "\": " + fileHashResult);
        } catch (Exception e) {
            System.out.println("Error while hashing file: " + e.getMessage());
        }

        // Test hash functions
        String inputForHash = "HelloWorld";
        String hashResultSHA256 = hash.hash(inputForHash, Hash.SHA_256);
        System.out.println("SHA-256 hash result for \"" + inputForHash + "\": " + hashResultSHA256);

        String hashResultSHA1 = hash.hash(inputForHash, Hash.SHA_1);
        System.out.println("SHA-1 hash result for \"" + inputForHash + "\": " + hashResultSHA1);

        String hashResultMD5 = hash.hash(inputForHash, "MD5");
        System.out.println("MD5 hash result for \"" + inputForHash + "\": " + hashResultMD5);
    }

		
}
