import java.util.ArrayList;

public class StringToByteConverter {
	private byte[] userArray1=null;
	
	public byte[] getUserArray1() {
		return userArray1;
	}

	public byte[] convert (String input){
		ArrayList<Byte> bList = new ArrayList<>();
		String[] inputArray = input.split("");
		for(String ss : inputArray) {
			byte[] bArr = ss.getBytes();
			for(Byte b : bArr) {
				bList.add(b);
			}
		}
		userArray1 = new byte[bList.size()];
		for(int i=0; i<bList.size(); i++) {
			userArray1[i] = bList.get(i);
		}
		return this.userArray1;
	}
	
}
