package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Ceaser {
	public String getSctLanguage() {
		return sctLanguage;
	}

	public void setSctLanguage(String sctLanguage) {
		this.sctLanguage = sctLanguage;
	}

	public String sctLanguage = "English";
	private static final String CHAR_SET = "AĂÂBCDĐEÊGHIKLMNOÔƠPQRSTUƯVXYaăâbcdđeêghiklmnoôơpqrstuưvxy";
	
	public static String encrypt_En(int key, String text) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            if (Character.isLetter(currentChar)) {
                char base = Character.isLowerCase(currentChar) ? 'a' : 'A';
                encryptedText.append((char) (((currentChar - base + key) % 26) + base));
            } else {
                encryptedText.append(currentChar);
            }
        }
        return encryptedText.toString();
    }

    public static String decrypt_En(int key, String text) {
        return encrypt_En(26 - key, text);
    }

    public static String encryptVN(int key, String data) {
        StringBuffer cipherText = new StringBuffer();
        for (char c : data.toCharArray()) {
            int index = CHAR_SET.indexOf(c);
            if (index == -1) {
                cipherText.append(c);
            } else {
                index = (index + key) % CHAR_SET.length();
                cipherText.append(CHAR_SET.charAt(index));
            }
        }
        return cipherText.toString();
    }

    public static String decryptVN(int key, String data) {
    	StringBuffer plainText = new StringBuffer();

        for (char c : data.toCharArray()) {
            int index = CHAR_SET.indexOf(c);

            if (index == -1) {
                plainText.append(c);
            } else {
                index = (index - key + CHAR_SET.length()) % CHAR_SET.length();
                plainText.append(CHAR_SET.charAt(index));
            }
        }

        return plainText.toString();
    }
    
    public static void encryptFile(int key, String inputFilePath, String outputFilePath) {
        try {
            byte[] data = Files.readAllBytes(Paths.get(inputFilePath));
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte) (data[i] + key);
            }
            Files.write(Paths.get(outputFilePath), data);

            System.out.println("Caesar encrypted");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decryptFile(int key, String inputFilePath, String outputFilePath) {
        try {
            byte[] data = Files.readAllBytes(Paths.get(inputFilePath));
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte) (data[i] - key);
            }
            Files.write(Paths.get(outputFilePath), data);

            System.out.println("Caesar decrypted");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {

        int key = 3;
        String data = "hiều địa phương đã khởi tố cả cán bộ là Phó Chủ tịch Ủy ban nhân dân tỉnh, Ủy viên Ban Thường vụ Tỉnh ủy, Giám đốc, Phó Giám đốc Sở, Chủ tịch Ủy ban Nhân dân cấp quận, huyện như Cao Bằng, Hòa Bình, Quảng Ninh, Hà Nam, Thanh Hóa, Đà Nẵng, Bà Rịa-Vũng Tàu...\r\n"
        		+ "\r\n"
        		+ "Những kết quả đạt được nêu trên tiếp tục khẳng định quyết tâm của Đảng, Nhà nước trong đấu tranh phòng, chống tham nhũng, tiêu cực, được dư luận xã hội và nhân dân đồng tình, ủng hộ, đánh giá cao.\r\n"
        		+ "\r\n"
        		+ "Việc ra mắt Cuốn sách “Kiên quyết, kiên trì đấu tranh phòng, chống tham nhũng, tiêu cực, góp phần xây dựng Đảng và Nhà nước ta ngày càng trong sạch, vững mạnh” của đồng chí Tổng Bí thư, Trưởng ban Chỉ đạo (ngày 02/02/2023) được đông đảo cán bộ, đảng viên và nhân dân rất hoan nghênh, quan tâm, đón nhận và đánh giá cao, coi đây là cuốn “cẩm nang” về công tác phòng, chống tham nhũng, tiêu cực.\r\n"
        		+ "\r\n"
        		+ "Việc quán triệt, nghiên cứu, học tập, phổ biến nội dung và những giá trị cốt lõi của Cuốn sách đã tạo thành một đợt sinh hoạt chính trị tư tưởng sâu rộng trong cả hệ thống chính trị.\r\n"
        		+ "\r\n"
        		+ "Cuốn sách đã giúp cán bộ, đảng viên và các tầng lớp nhân dân, bạn bè quốc tế hiểu rõ, hiểu đúng về bản chất cuộc đấu tranh phòng, chống tham nhũng, tiêu cực ở Việt Nam.\r\n"
        		+ "\r\n"
        		+ "Không đùn đẩy, né tránh\r\n"
        		+ "\r\n"
        		+ "Phát biểu kết luận cuộc họp, Tổng Bí thư Nguyễn Phú Trọng nhấn mạnh vừa qua các cơ quan chức năng đã phối hợp ngày càng hiệu quả, thống nhất cao trong hành động, có kết quả thiết thực, nhân dân theo dõi và bày tỏ đồng tình.\r\n"
        		+ "\r\n"
        		+ "";
        String maHoa = encryptVN(key, data);
        System.out.println("Mã Hóa: " + maHoa);
        
        System.out.println("");
        
        String giaiMa = decryptVN(key, maHoa);
        System.out.println("Kết quả: " + giaiMa);
        
//        String text = "Hello everyone";
//        String maHoa = encrypt_En(key, text);
//        String giaiMa = decrypt_En(key, maHoa);
//        
//        System.out.println("mã Hóa: " + maHoa + "/n");
//        System.out.println("Giải mã: " + giaiMa);
//        
//        String inputFilePath = "C:\\Users\\LAPTOP\\Documents\\tandat.rar";
//        String outputFilePath = "C:\\Users\\LAPTOP\\Documents\\tandat1.rar";
//
//        // Mã hóa file
//        encryptFile(key, inputFilePath, outputFilePath);
//        System.out.println("File đã được mã hóa và lưu tại: " + outputFilePath);
//
//        // Giải mã file
//        
//        String decryptedFilePath = "C:\\Users\\LAPTOP\\Documents\\tandat2.rar";
//        decryptFile(key, "C:\\Users\\LAPTOP\\Documents\\tandat1.rar", "C:\\Users\\LAPTOP\\Documents\\tandat2.rar");
//        System.out.println("File đã được giải mã và lưu tại: " + decryptedFilePath);
    }
}
