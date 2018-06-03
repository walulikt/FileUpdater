import java.io.File;


import org.junit.Test;

public class FileUpdaterTestSuite {
	
	@Test
	public void pathFinderTest() {
		Finder pF = new Finder();
				
		File[] files = File.listRoots();		
        for(File f : files){
            pF.parseAllFiles(f.getPath());
        }
        /*for(String i: pF.paths) {
        	System.out.println("Znaleziono docelowy folder: " + i.toString());
        }
        ArrayList<String> pathToFiles = pF.paths;
        for(String k: pathToFiles) {
        	pF.fileFinder(k);
        }
        
        System.out.println("Iloœæ znalezionych plików" + pF.theFiles.size() + ". Nazwa pierwszego pliku z jego sciezka: " + pF.theFiles.get(0));
        */
	}

}
