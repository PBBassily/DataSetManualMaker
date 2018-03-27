package descriptor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Engine {

	ArrayList<String> imagesNames;
	int imageIndex;
	public Engine() {
		
		loadImagesNames();
	
		imageIndex = 0;
	}

	private void loadImagesNames() {
		imagesNames = new ArrayList<String>();

		File folder = new File("renamed_data");

		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				imagesNames.add(file.getName());
 			}
		}
	}

	public String getImageName() {
		return imagesNames.get(imageIndex);
	}

	public String getNewImage() {

		imageIndex++;
		
		if(imageIndex == imagesNames.size()) {
			imageIndex--;
			return null;
		}
		
		return getImageName();
	}
	
	public String getNewImage(int index) {

		imageIndex=index;
		
		if(imageIndex == imagesNames.size()) {
			imageIndex--;
			return null;
		}
		
		return getImageName();
	}

}
