import java.io.File;
import java.io.IOException;

public class FileUpdaterApi {
	
	private Finder finder;
	private FileChanger fileChanger;
	private File[] files = File.listRoots();
		
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

	public void findDirectoryByName(String directoryName) {	
		finder.setDirectoryName(directoryName);
		System.out.println("Szukam wskazanego folderu. To moze potrwac chwile.");
		for(File f : files){
			finder.parseAllFiles(f.getPath());
		}
		System.out.println("Zakonczono wyszukiwanie folderu.");
	}
	
	public boolean findAllFilesByType(String fileType) {
		finder.setFileType(fileType);
		System.out.println("Szukam plikow o wskazanym typie.");
		for(int i =0; i<finder.getDirectoryPaths().size();i++) {
			finder.fileFinder(finder.getDirectoryPaths().get(i));
		}
		if(!finder.getTheFilePaths().isEmpty()) {
			System.out.println("Znaleziono " + finder.getTheFilePaths().size() + " plik�w o podanym typie");
			return true;
		}		
		else return false;		
	}
	
	public boolean swopTheBytes(byte[] userPattern1, byte [] userPattern2) {
		for (int i=0; i<finder.getTheFilePaths().size(); i++) {
			try {
				fileChanger.fileReader(finder.getTheFilePaths().get(i), userPattern1, userPattern2);
				System.out.println("Zmiana w pliku " + finder.getTheFilePaths().get(i) + "zosta�a dokonana");
			}catch (IOException ioe){
				System.out.println("Nie mo�na dokonac zmian w pliku " + finder.getTheFilePaths().get(i) + ioe);
			}
		}
		return true;
	}
	
	
}
