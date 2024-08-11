package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Vigenere {
	public String getSctLanguage() {
		return sctLanguage;
	}

	public void setSctLanguage(String sctLanguage) {
		this.sctLanguage = sctLanguage;
	}

	public String sctLanguage = "English";
	
	//mã hóa và giải mã tiếng Việt
	private static final String CHAR_SET = "AĂÂBCDĐEÊGHIKLMNOÔƠPQRSTUƯVXYaăâbcdđeêghiklmnoôơpqrstuưvxy";

    public static String encryptVN(String data, String key) {
        StringBuilder encryptedText = new StringBuilder();
        int keyLength = key.length();
        int dataLength = data.length();

        for (int i = 0; i < dataLength; i++) {
            char plainChar = data.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            int keyIndex = CHAR_SET.indexOf(keyChar);
            if (keyIndex == -1) {
                encryptedText.append(plainChar); 
                continue;
            }

            int dataIndex = CHAR_SET.indexOf(plainChar);
            if (dataIndex == -1) {
                encryptedText.append(plainChar); 
                continue;
            }

            int encryptedIndex = (dataIndex + keyIndex) % CHAR_SET.length();
            char encryptedChar = CHAR_SET.charAt(encryptedIndex);

            encryptedText.append(encryptedChar);
        }

        return encryptedText.toString();
    }

    public static String decryptVN(String data, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyLength = key.length();
        int dataLength = data.length();

        for (int i = 0; i < dataLength; i++) {
            char encryptedChar = data.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            int keyIndex = CHAR_SET.indexOf(keyChar);
            if (keyIndex == -1) {
                decryptedText.append(encryptedChar);
                continue;
            }

            int encryptedIndex = CHAR_SET.indexOf(encryptedChar);
            if (encryptedIndex == -1) {
                decryptedText.append(encryptedChar); 
                continue;
            }

            int dataIndex = (encryptedIndex - keyIndex + CHAR_SET.length()) % CHAR_SET.length();
            char decryptedChar = CHAR_SET.charAt(dataIndex);

            decryptedText.append(decryptedChar);
        }

        return decryptedText.toString();
    }
    
    //mã hóa và giải mã tiếng Anh
    public String encryptEn(String str, String key) {
        StringBuilder cipher_text = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char plainChar = str.charAt(i);
            char keyChar = key.charAt(i % key.length());

            if (Character.isLetter(plainChar) && Character.isLetter(keyChar)) {
                int plainCharOffset = Character.isUpperCase(plainChar) ? 'A' : 'a';
                int keyCharOffset = Character.isUpperCase(keyChar) ? 'A' : 'a';

                int encryptedChar = ((plainChar - plainCharOffset + keyChar - keyCharOffset) % 26) + plainCharOffset;
                cipher_text.append((char) encryptedChar);
            } else {
                // If the character is not a letter, keep it unchanged.
                cipher_text.append(plainChar);
            }
        }

        return cipher_text.toString();
    }

    public String decryptEn(String cipher_text, String key) {
        StringBuilder orig_text = new StringBuilder();

        for (int i = 0; i < cipher_text.length(); i++) {
            char encryptedChar = cipher_text.charAt(i);
            char keyChar = key.charAt(i % key.length());

            if (Character.isLetter(encryptedChar) && Character.isLetter(keyChar)) {
                int encryptedCharOffset = Character.isUpperCase(encryptedChar) ? 'A' : 'a';
                int keyCharOffset = Character.isUpperCase(keyChar) ? 'A' : 'a';

                int originalChar = ((encryptedChar - encryptedCharOffset - (keyChar - keyCharOffset) + 26) % 26) + encryptedCharOffset;
                orig_text.append((char) originalChar);
            } else {
                // If the character is not a letter, keep it unchanged.
                orig_text.append(encryptedChar);
            }
        }

        return orig_text.toString();
    }
    
    public void encryptFile(String key, String inputFilePath, String outputFilePath) {
        try {
            byte[] data = Files.readAllBytes(Paths.get(inputFilePath));
            String plainText = new String(data);
            String cipherText = encryptEn(plainText, key);
            Files.write(Paths.get(outputFilePath), cipherText.getBytes());

            System.out.println("Vigenere encrypted");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void decryptFile(String key, String inputFilePath, String outputFilePath) {
        try {
            byte[] data = Files.readAllBytes(Paths.get(inputFilePath));
            String encryptedText = new String(data);
            String decryptedText = decryptEn(encryptedText, key);

            Files.write(Paths.get(outputFilePath), decryptedText.getBytes());

            System.out.println("Vigenere decrypted");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//    	Vigenere vigenere = new Vigenere();
//    	String str = "Xin chào";
//        String key = "giàu";
//
//      
//        String cipher_text = vigenere.encryptVN(str, key);
//
//        System.out.println("Plaintext: " + str);
//        System.out.println("Generated Key: " + key);
//        System.out.println("Ciphertext: " + cipher_text);
//
//        String decrypted_text = vigenere.decryptVN(cipher_text, key);
//        System.out.println("Decrypted Text: " + decrypted_text);
//        
    	// Khởi tạo key và Vigenere
        String key = "KEY";
        Vigenere vigenere = new Vigenere();

        // Mã hóa tệp tin
        String inputFilePath = "C:\\Users\\LAPTOP\\Documents\\tandat.rar";
        String outputFilePath = "C:\\Users\\LAPTOP\\Documents\\tandat1.rar";
        vigenere.encryptFile(key, inputFilePath, outputFilePath);
        System.out.println("File đã được mã hóa và lưu tại: " + outputFilePath);

        // Giải mã tệp tin
        vigenere.decryptFile(key,outputFilePath,"C:\\Users\\LAPTOP\\Documents\\tandat2.rar");
        System.out.println("File đã được giải mã và lưu tại: " + outputFilePath);
    }
}
