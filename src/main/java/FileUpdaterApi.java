import java.io.File;
import java.io.IOException;

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

	public boolean findDirectoryByName(String directoryName) {
		File[] files = File.listRoots();
		finder.setDirectoryName(directoryName);
		System.out.println("Szukam wskazanego folderu. To moze potrwac chwile.");
		for(File f : files){
			finder.parseAllFiles(f.getPath());
		}
		if(!finder.getDirectoryPaths().isEmpty()) {
			System.out.println("Zakonczono wyszukiwanie folderu." + '\n'+"Znaleziono folderów o podanej nazwie: "+ finder.getDirectoryPaths().size());
			return true;
		}else {
			System.out.println("Nie znaleziono ¿adnego folderu o nazwie " + directoryName + ". Wprowadz dane ponownie");
			return false;
		}
	}
	
	public boolean findAllFilesByType(String fileType) {
		finder.setFileType(fileType);
		System.out.println("Szukam plikow o wskazanym typie.");
		for(int i =0; i<finder.getDirectoryPaths().size();i++) {
			finder.fileFinder(finder.getDirectoryPaths().get(i));
		}
		if(!finder.getTheFilePaths().isEmpty()) {
			System.out.println("Znaleziono " + finder.getTheFilePaths().size() + " plikow o podanym typie");
			return true;
		}		
		else {
			System.out.println("Nie znaleziono plików typu: " + fileType + " we wskazanym folderze."); 
			return false;		
		}
	}
	
	public boolean swopTheBytes(byte[] userPattern1, byte [] userPattern2) {
		for (int i=0; i<finder.getTheFilePaths().size(); i++) {
			try {
				if(fileChanger.fileReader(finder.getTheFilePaths().get(i), userPattern1, userPattern2)) {
					System.out.println("Zmiana w pliku " + finder.getTheFilePaths().get(i) + " zosta³a dokonana");
				} else System.out.println("Zmiana w pliku" + finder.getTheFilePaths().get(i) + " nie zosta³a dokonana");
				
			}catch (IOException ioe){
				System.out.println("Nie mozna dokonac zmian w pliku " + finder.getTheFilePaths().get(i) + ioe);
			}
		}
		return true;
	}
	
	
}
