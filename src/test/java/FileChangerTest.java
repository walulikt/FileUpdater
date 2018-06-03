import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class FileChangerTest {
	@Test
	
	public void fileReaderTest() {
		//Given
		FileChanger changer = new FileChanger();
		String pathBinFile = "E:\\FolderTestowy\\PodFolderTestowy\\";
		byte[] userArray1 = new byte[]{1,23,94};
		byte[] userArray2 = new byte[]{2,46,96};
		
		File file = null;
	      try {
	         file = File.createTempFile("tmp", ".bin", new File(pathBinFile));
	        
	         System.out.println("File path: "+file.getAbsolutePath());
	         
	         file.deleteOnExit();
	         
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	     
	      DataOutputStream dos = null;
	      try {
	    	  dos = new DataOutputStream(new FileOutputStream(file));
	    	  dos.write(userArray1);
	      } catch (IOException ioe) {
	    	  System.out.println("Exception while writing file " + ioe);
	      } finally {
	    	  try {
	    		  if(dos!=null) {
	    			  dos.close();
	    		  } 
	    	  } catch (IOException ioe) {
	    		  System.out.println("Error while closing output stream: " + ioe);
	    	  }
	      }
		
		//When
	      boolean check=false;
	      try {
			check = changer.fileReader(file.getAbsolutePath(), userArray1, userArray2);
	      } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	      }
	      
	    //Then
	      
	      Assert.assertTrue(check);
	}

}
