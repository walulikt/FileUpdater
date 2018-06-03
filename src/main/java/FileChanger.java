import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileChanger {
	private byte[] tempArray;
	
	public boolean fileReader (String pathBinFile,byte[] userArray1, byte[] userArray2 ) throws IOException {
		File f = new File(pathBinFile);
		DataInputStream dis = null;
		DataOutputStream dos = null;
		byte [] b = null;
		if(f.canRead()) {
			try {
				dis = new DataInputStream(new FileInputStream(f)); 
				b = new byte[(int)f.length()];
				dis.read(b);
				System.out.println("Dane z pliku " + f.getName() + " wczytane.");
			} catch (FileNotFoundException e) {
				System.out.println("File not found" + e);
			} catch (IOException ioe) {
				System.out.println("Exception while reading file " + ioe);
			} finally {
				try {
					if (dis != null) {
					dis.close();
					} 
				}catch (IOException ioe) {
					System.out.println("Error while closing input stream: " + ioe);
				}
			}
		} else { 
			System.out.println("Plik nie jest do odczytu");
			return false;
		}
		
		int indexNumber = byteArrayComparator(b,userArray1);
		if (indexNumber==(-1)) {
			System.out.println("W pliku " + f.getName() + " nie znaleziono ciagu bajtów zgodnym z podanym przez uzytkownika");
			return false;
		}
		
		if (f.canWrite()) {
			try {
				dos = new DataOutputStream(new FileOutputStream(f));
				dos.write(arraySwap(indexNumber,b,userArray1,userArray2));
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
		} else {
			System.out.println("Plik jest niedostepny na zapis");
			return false;
		}
			
		return true;
	}
	
	private int byteArrayComparator ( byte[] fileArray, byte[] userArray1) {
		int[] failure = computeFailure(userArray1);

        int j = 0;
        if (fileArray.length == 0) return -1;

        for (int i = 0; i < fileArray.length; i++) {
            while (j > 0 && userArray1[j] != fileArray[i]) {
                j = failure[j - 1];
            }
            if (userArray1[j] == fileArray[i]) { j++; }
            if (j == userArray1.length) {
                return i - userArray1.length + 1;
            }
        }
        return -1;
    }
	
	private int[] computeFailure(byte[] userArray1) {
        int[] failure = new int[userArray1.length];

        int j = 0;
        for (int i = 1; i < userArray1.length; i++) {
            while (j > 0 && userArray1[j] != userArray1[i]) {
                j = failure[j - 1];
            }
            if (userArray1[j] == userArray1[i]) {
                j++;
            }
            failure[i] = j;
        }

        return failure;
    }

	private byte[] arraySwap(int indexNumber, byte[] fileArray,byte[] userArray1, byte []userArray2) {
		int arrIndexNumber=0;
		if(userArray1.length==userArray2.length) {
			for (int i=indexNumber; i<userArray2.length; i++) {
				fileArray[i] = userArray2[arrIndexNumber];
				arrIndexNumber++;
			}
		} else if(userArray1.length>userArray2.length) {
			tempArray = new byte[fileArray.length-(userArray1.length-userArray2.length)];
			for(int i =0; i<tempArray.length;i++) {
				if (i>=indexNumber && i<(indexNumber+userArray2.length)) {
					tempArray[i]=userArray2[arrIndexNumber];
					arrIndexNumber++;
				} else if(i>=indexNumber+userArray2.length){
					tempArray[i]=fileArray[fileArray.length-(i-(userArray1.length-userArray2.length))];
				} else {
					tempArray[i]=fileArray[i];
				}
			}
		} else if(userArray1.length<userArray2.length) {
			tempArray = new byte[fileArray.length+(userArray2.length-userArray1.length)];
			for(int i =0; i<tempArray.length;i++) {
				if (i>=indexNumber && i<(indexNumber+userArray2.length)) {
					tempArray[i]=userArray2[arrIndexNumber];
					arrIndexNumber++;
				} else if(i>=indexNumber+userArray2.length){
					tempArray[i]=fileArray[fileArray.length-(i+(userArray2.length-userArray1.length))];
				} else {
					tempArray[i]=fileArray[i];
				}
			}
		}
		return tempArray;
	}
}
