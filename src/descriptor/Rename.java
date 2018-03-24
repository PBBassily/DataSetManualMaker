package descriptor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Rename {

	public static void main(String[] args) {

		File folder = new File("data");
		File[] listOfFiles = folder.listFiles();
		Path fileToMovePath;
		Path targetPath;
		int i =1;
		for (File file : listOfFiles) {
			if (file.isFile()) {
				String number = String.format("%05d", i);	
				fileToMovePath = Paths.get("data/"+file.getName());
				targetPath = Paths.get("renamed_data/image_" + number + ".jpg");
				try {
					Files.copy(fileToMovePath, targetPath);
				} catch (IOException e) {
					e.printStackTrace();
				}
				i++;
			}
		}
		
		System.out.println(i);

		
	}

}
