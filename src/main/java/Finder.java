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
	
	public void parseAllFiles(String parentDirectory){
        File[] filesInDirectory = new File(parentDirectory).listFiles();
        if (filesInDirectory!=null) {
        	for(File f : filesInDirectory){
        		if(f.isDirectory()){
        			parseAllFiles(f.getAbsolutePath());
        			if(f.getName().equals(directoryName)) {
        				directoryPaths.add(f.getAbsolutePath());	
        			}
        		} else continue;          
        	}
        }        
	}
	
	public void fileFinder (String thePaths) {
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
	
	public String toString(ArrayList<String> pathsList) {
		StringBuilder strBuilder = new StringBuilder();
		for(int i=0; i<pathsList.size(); i++) {
			System.out.println(pathsList.get(i).toString());
			strBuilder.append(pathsList.get(i).toString() +'\n');
		}
		return strBuilder.toString();
	}
}
