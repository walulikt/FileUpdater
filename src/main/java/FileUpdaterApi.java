import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUpdaterApi {
	
	private Finder finder;
	private FileChanger fileChanger;
	
		
	public FileUpdaterApi() {
		finder = new Finder();
		fileChanger = new FileChanger();
	}
	
	public Finder getFinder() {
		return finder;
	}

	public FileChanger getFileChanger() {
		return fileChanger;
	}
	
	public boolean finderRunner(String discName, String directoryName, String fileType) {
		if(finder.findDirectoryByName(directoryName, discName)) {
			if(finder.findAllFilesByType(fileType)) {
				return true;
			} else return false;
		}else return false;
	}
	
	public boolean swopTheBytes(byte[] userPattern1, byte [] userPattern2) {
		for (int i=0; i<finder.getTheFilePaths().size(); i++) {
			try {
				if(fileChanger.fileReader(finder.getTheFilePaths().get(i), userPattern1, userPattern2)) {
					System.out.println("Zmiana w pliku " + finder.getTheFilePaths().get(i) + " zosta³a dokonana");
				} else System.out.println("Zmiana w pliku " + finder.getTheFilePaths().get(i) + " nie zosta³a dokonana");
				
			}catch (IOException ioe){
				System.out.println("Nie mozna dokonac zmian w pliku " + finder.getTheFilePaths().get(i) + ioe);
			}
		}
		return true;
	}
	
	
}
