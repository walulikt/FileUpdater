import java.io.File;
import java.util.ArrayList;

public class Finder {
	
	private String directoryName = null;
	private String userFileType = null;
	private ArrayList<String> directoryPaths = new ArrayList<>();
	private ArrayList<String> theFilePaths = new ArrayList<>();

	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}

	public void setFileType(String fileType) {
		this.userFileType = fileType;
	}
	
	public ArrayList<String> getDirectoryPaths() {
		return directoryPaths;
	}
	
	public ArrayList<String> getTheFilePaths() {
		return theFilePaths;
	}
	
	
	public boolean findDirectoryByName(String directoryName, String discName) {
		this.directoryName=directoryName;
		File[] files=null;
		File file=null;
		if(discName==null) files = File.listRoots();
		else {
			file= new File(discName.toUpperCase()+"://");
			files = file.listFiles();
		}
		System.out.println("Szukam wskazanego folderu. To moze potrwac chwile.");
		for(File f : files){
			if(f.getName().equals(directoryName)) {
				directoryPaths.add(f.getAbsolutePath());	
			}
			parseAllFiles(f.getPath());
		}
		if(!directoryPaths.isEmpty()) {
			System.out.println("Zakonczono wyszukiwanie folderu." + '\n'+"Znaleziono folderów o podanej nazwie: "+ directoryPaths.size());
			return true;
		}else {
			System.out.println("Nie znaleziono ¿adnego folderu o nazwie " + directoryName + ". Wprowadz dane ponownie");
			return false;
		}
	}
	
	private void parseAllFiles(String parentDirectory){
        File[] filesInDirectory = new File(parentDirectory).listFiles();
        if (filesInDirectory!=null) {
        	for(File f : filesInDirectory){
        		if(f.isDirectory()){
        			parseAllFiles(f.getAbsolutePath());
        			if(f.getName().equals(directoryName)) {
        				directoryPaths.add(f.getAbsolutePath());	
        			}
        		} else continue;	
        	System.out.println("Sprawdzam -> " + f);             
        	}
        }        
	}
	
	public boolean findAllFilesByType(String fileType) {
		this.userFileType=fileType;
		for(int i =0; i<directoryPaths.size();i++) {
			fileFinder(directoryPaths.get(i));
		}
		if(!theFilePaths.isEmpty()) {
			System.out.println("Znaleziono " + theFilePaths.size() + " plikow o podanym typie");
			for(int i=0; i<theFilePaths.size(); i++) {
				System.out.println((i+1)+". "+theFilePaths.get(i).toString());
			}
			return true;
		}		
		else {
			System.out.println("Nie znaleziono plików typu: " + fileType + " we wskazanym folderze."); 
			return false;		
		}
	}
	
	private void fileFinder (String thePaths) {
			File[] theTypeFiles = new File(thePaths).listFiles();
			if (theTypeFiles !=null) {
				for(File f: theTypeFiles) {
					if(f.isDirectory()) {
						fileFinder(f.getAbsolutePath());
					} else {
						String fileType = f.getName();
						fileType = fileType.substring(fileType.indexOf("."));
						if (fileType.equals(userFileType)) {
							theFilePaths.add(f.getAbsolutePath());
						}
					}
				}
			}
	}
	
	
}
