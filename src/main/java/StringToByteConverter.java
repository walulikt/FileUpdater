import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class StringToByteConverter {
	private byte[] userArray1=null;
	
	public byte[] getUserArray1() {
		return userArray1;
	}

	public byte[] convert (String input){
		ArrayList<Byte> bList = new ArrayList<>();
		String[] inputArray = input.split(",");
		byte[] byteArray = new byte[inputArray.length];
		for (int i=0; i<inputArray.length;i++) {
			bList.add(i,Byte.valueOf(inputArray[i]));
		}
		userArray1 = new byte[bList.size()];
		for(int i=0; i<bList.size(); i++) {
			userArray1[i] = bList.get(i);
		}
		return this.userArray1;
	}
	
}
