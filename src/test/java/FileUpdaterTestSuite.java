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
	public void findAllFilesByTypeTest() {
		//Given
		FileUpdaterApi fUApi = new FileUpdaterApi();
		String fileType = ".txt";
		String directoryPath = "E:\\FolderTestowy";
		fUApi.getFinder().getDirectoryPaths().add(directoryPath);
		//When
		fUApi.findAllFilesByType(fileType);
		
		//Then
		System.out.println(fUApi.getFinder().getTheFilePaths().get(0).toString());
		Assert.assertEquals(1,fUApi.getFinder().getTheFilePaths().size());
		
	}
	
}
