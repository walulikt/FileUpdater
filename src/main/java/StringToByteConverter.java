
public class StringToByteConverter {
	private byte[] byteArray=null;
	
	public byte[] getUserArray1() {
		return byteArray;
	}

	public byte[] convert (String input) {
		String[] inputArray = input.split(" ");
		byteArray = new byte[inputArray.length];
		for (int i=0; i<inputArray.length;i++) {
			byteArray[i]=Byte.parseByte(inputArray[i]);
		}		
		return byteArray;
	}
	
}
