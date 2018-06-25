import org.junit.Assert;
import org.junit.Test;

public class FileUpdaterTestSuite {
	
	/*@Test
	public void findDirectoryByNameTest() {
		
		//Given
		FileUpdaterApi fUApi = new FileUpdaterApi();
		String directoryName = "FolderTestowy";	
		
		//When
		fUApi.findDirectoryByName(directoryName);
		
		//Then
		System.out.println(fUApi.getFinder().getDirectoryPaths().get(0).toString());
		Assert.assertEquals(1,fUApi.getFinder().getDirectoryPaths().size());
		      
	}*/

	@Test
	public void testFinderRunner() {
		//Given
		FileUpdaterApi fUApi = new FileUpdaterApi();
		String discName = "e";
		String directoryName = "PodFolderTestowy";
		String fileType = ".txt";
		
		//When
		fUApi.finderRunner(discName, directoryName, fileType);
		
		//Then
		System.out.println(fUApi.getFinder().getTheFilePaths().get(0).toString());
		Assert.assertEquals(1,fUApi.getFinder().getTheFilePaths().size());
		
	}
	
}
