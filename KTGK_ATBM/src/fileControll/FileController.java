package fileControll;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.MainWindow;

public class FileController {
	public static void onSaveText(String input) {
		JFileChooser file = new JFileChooser(MainWindow.PATH);
		FileNameExtensionFilter filterExt = new FileNameExtensionFilter(" Zip, Rar, Txt Files", "zip", "rar", "txt");
		file.setFileFilter(filterExt);
		File fileName = new File("fileName.zip");
		file.setSelectedFile(fileName);
		file.setDialogTitle("Chọn nơi lưu file:");
		int x = file.showSaveDialog(null);
		if (x == JFileChooser.APPROVE_OPTION) {
			fileName = file.getSelectedFile();
			if (fileName.exists()) {
				String[] options = { "Có", "Không" };
				int y = JOptionPane.showOptionDialog(null, "File đã tồn tại, bạn có muốn tiếp tục?", "Thay đổi",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
				if (y == 0) {
					saveText(fileName, input);
				}

			} else {
				saveText(fileName, input);
			}
		}
	}
	
	public static File chooseFile() {
		File result = null;
		File source = new File(MainWindow.PATH);
		JFileChooser fileChooser = new JFileChooser(source);
		FileNameExtensionFilter filterExt = new FileNameExtensionFilter(" Zip, Rar, Txt Files", "zip", "rar", "txt");
		fileChooser.setFileFilter(filterExt);
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setDialogTitle("Chọn File");
		int x = fileChooser.showDialog(null, "Chọn");
		if (x == JFileChooser.APPROVE_OPTION) {
			result = fileChooser.getSelectedFile();
		}
		return result;
	}
	
	public static Object readObjectFile(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
		Object result = null;
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
		result = ois.readObject();
		ois.close();
		return result;
	}

	public static String getKeyAlphabet(String path) {
		String result = "";
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
			String line = bufferedReader.readLine();
			result = line;
			bufferedReader.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Không thể đọc file");
		}
		return result.split(" ")[1];
	}
	
	public static String getKeyType(String path) {
		String result = "";
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
			String line = bufferedReader.readLine();
			result = line;
			bufferedReader.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Không thể đọc file");
		}
		return result.split(" ")[0];
	}
	
	public static String readContentFile(String path) {
		String result = "";
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
			String line = bufferedReader.readLine();
			while ((line = bufferedReader.readLine()) != null) {
				result += line + "\n";
			}
			bufferedReader.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Không thể đọc file");
		}
		return result;
	}
	
	private static void saveText(File fileName, String input) {
		try {
			FileWriter fw = new FileWriter(fileName);
			fw.write(input);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void saveObjectToFile(Object objectToSave) {
	    JFileChooser fileChooser = new JFileChooser(MainWindow.PATH);
	    FileNameExtensionFilter filterExt = new FileNameExtensionFilter(" Zip, Rar, Txt Files", "zip", "rar", "txt");
	    fileChooser.setFileFilter(filterExt);
	    File selectedFile = new File("key.txt");
	    fileChooser.setSelectedFile(selectedFile);
	    fileChooser.setDialogTitle("Chọn vị trí để lưu");
	    int userChoice = fileChooser.showSaveDialog(null);

	    if (userChoice == JFileChooser.APPROVE_OPTION) {
	        selectedFile = fileChooser.getSelectedFile();
	        if (selectedFile.exists()) {
	            String[] options = { "Đồng ý", "Không" };
	            int replaceChoice = JOptionPane.showOptionDialog(null, "Key này đã tồn tại. Bạn có muốn thay đổi nó không?", "Thay đổi",
	                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);

	            if (replaceChoice == 0) {
	                saveObjectToFile(selectedFile, objectToSave);
	            }
	        } else {
	            saveObjectToFile(selectedFile, objectToSave);
	        }
	    }
	}

	private static void saveObjectToFile(File file, Object objectToSave) {
	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
	        oos.writeObject(objectToSave);
	        oos.flush();
	        oos.close();
	        JOptionPane.showMessageDialog(null, "Lưu file thành công.", "Thành Công", JOptionPane.INFORMATION_MESSAGE);
	    } catch (IOException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Lỗi, Không thể lưu file.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	    }
	}

	public static String readTextFile(String path) {
	    StringBuilder result = new StringBuilder();
	    try (BufferedReader buffReader = new BufferedReader(new FileReader(path))) {
	        String line;
	        while ((line = buffReader.readLine()) != null) {
	            result.append(line).append("\n");
	        }
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(null, "Không thể đọc file này");
	        e.printStackTrace();
	    }
	    return result.toString();
	}

	public static byte[] readByteFile(String path) {
	    ByteArrayOutputStream byteText = new ByteArrayOutputStream();
	    byte[] buffer = new byte[1024];
	    int bytesRead;

	    try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path))) {
	        while ((bytesRead = bis.read(buffer)) != -1) {
	        	byteText.write(buffer, 0, bytesRead);
	        }
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(null, "Không thể đọc file này");
	        e.printStackTrace();
	    }

	    return byteText.toByteArray();
	}

	public static void saveBytesToFile(byte[] bytes) {
	    JFileChooser fileChooser = new JFileChooser(MainWindow.PATH);
	    FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Zip, Rar, Txt Files", "zip", "rar", "txt");
	    fileChooser.setFileFilter(fileFilter);
	    File selectedFile = new File("fileName.txt");
	    fileChooser.setSelectedFile(selectedFile);
	    fileChooser.setDialogTitle("Chọn nơi lưu file:");
	    int choice = fileChooser.showSaveDialog(null);
	    if (choice == JFileChooser.APPROVE_OPTION) {
	        selectedFile = fileChooser.getSelectedFile();
	        if (selectedFile.exists()) {
	            String[] options = {"Yes", "No"};
	            int decision = JOptionPane.showOptionDialog(null, "File đã tồn tại, bạn có muốn tiếp tục?", "Thay đổi",
	                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
	            if (decision == 0) {
	                writeBytesToFile(selectedFile, bytes);
	            }
	        } else {
	            writeBytesToFile(selectedFile, bytes);
	        }
	    }
	}

	private static void writeBytesToFile(File file, byte[] bytes) {
	    try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
	        fileOutputStream.write(bytes);
	        JOptionPane.showMessageDialog(null, "Lưu file thành công");
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi lưu tệp");
	    }
	}

	public static byte[] readBytesFromFile(File selectedFile) throws IOException {
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(selectedFile));
		         ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
		        byte[] buffer = new byte[1024];
		        int bytesRead;
		        while ((bytesRead = bis.read(buffer)) != -1) {
		            bos.write(buffer, 0, bytesRead);
		        }
		        return bos.toByteArray();
		    }
	}


	
}
