import java.util.Scanner;

import javax.swing.SwingUtilities;

public class FileUpdaterRunner {
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run () {
				new MainFrame();
			}
		});
		
		
		FileUpdaterApi fUApi = new FileUpdaterApi();
		StringToByteConverter conv = new StringToByteConverter();
		Scanner sc = new Scanner (System.in);
		boolean endRunner = false;
		
		System.out.println("Witam programisto. Wlasnie uruchomiles program, który zmieni Twoj swiat. \n "
				+ "Teraz bêdziesz mogl podac nazwe folderu w ktorym sa pliki o rozszerzeniu, które Cie interesuje "
				+ "a na koniec zamienic wskazany ciag bajtów w tych plikach na taki jaki wskazesz.");
		
		while(!endRunner) {
			boolean arrayConverted=false;
			System.out.println("Podaj nazwê szukanego folderu: ");
			String directoryName = sc.nextLine();
			while(directoryName.isEmpty()) {
				System.out.println("Musisz wprowadzic nazwê folderu: ");
				directoryName = sc.nextLine();
			} 
				
			System.out.println("Podaj format plikow w ktorych chcesz dokonac zmiany:");
			String fileType = sc.nextLine();
			while(fileType.isEmpty()||fileType.charAt(0)!='.') {
				System.out.println("Musisz wprowadzic typ szukanego pliku (np.'.bit'): ");
				fileType = sc.nextLine();
			} 
				
			System.out.println("Podaj ciag bajtow, ktore chcesz zmienic w pliku (przyk³ad: 2 50 60 87):");
			String userArray1 = sc.nextLine();
			byte [] byteArray1 = null;
			while(userArray1.isEmpty()) {
				System.out.println("Ci¹g bajtów nie mo¿e byæ pusty. Wprowadz ci¹g bajtów");
				userArray1 = sc.nextLine();
			}
			while(!arrayConverted) {
				try {
					byteArray1 =conv.convert(userArray1);
					arrayConverted=true;
				} catch (NumberFormatException e) {
					System.out.println(e.toString()+'\n'+"Podany ci¹g zawiera b³êdny format lub wychodzi poza zakres wartoœci bajta. Wprowadz ponownie: ");
					userArray1 = sc.nextLine();
					while(userArray1.isEmpty()) {
						System.out.println("Ci¹g bajtów nie mo¿e byæ pusty. Wprowadz ci¹g bajtów");
						userArray1 = sc.nextLine();
					}
					arrayConverted=false;
				}
			}
		
			System.out.println("Podaj ciag bajtow, ktore chcesz wprowadzic do pliku (przyk³ad: 87 60 50 2):");
			String byteArray2 = sc.nextLine();
			byte [] userArray2 =null;
			while(userArray1.isEmpty()) {
				System.out.println("Ci¹g bajtów nie mo¿e byæ pusty. Wprowadz ci¹g bajtów");
				userArray1 = sc.nextLine();
			}
			arrayConverted=false;
			while(!arrayConverted) {
				try {
					userArray2 =conv.convert(byteArray2);
					arrayConverted=true;
				} catch (NumberFormatException e) {
					System.out.println(e.toString()+'\n'+"Podany ci¹g zawiera b³êdny format lub wychodzi poza zakres wartoœci bajta. Wprowadz ponownie: ");
					byteArray2 = sc.nextLine();
					while(userArray1.isEmpty()) {
						System.out.println("Ci¹g bajtów nie mo¿e byæ pusty. Wprowadz ci¹g bajtów");
						userArray1 = sc.nextLine();
					}
					arrayConverted=false;
				}
			}
		
			if(fUApi.findDirectoryByName(directoryName)) {
				if (fUApi.findAllFilesByType(fileType)) {
					fUApi.swopTheBytes(byteArray1, userArray2);
				}
			}
			
			System.out.println("Koniec sesji. Jesli chcesz zakonczycz program wcisnij 'n', jesli chcesz sprobowac ponownie to wcisnij cokolwiek.");
			String userInput = sc.nextLine();
			if(userInput.equals("n")) {
				System.out.println("Dziekuje za skorzystanie z programu. Zapraszam ponownie");
				endRunner=true;
			}
		}
		sc.close();
	}
}
