import java.util.Scanner;

public class FileUpdaterRunner {
	
	public static void main(String[] args) {
		FileUpdaterApi fUApi = new FileUpdaterApi();
		StringToByteConverter conv = new StringToByteConverter();
		Scanner sc = new Scanner (System.in);
		boolean endRunner = false;
		
		System.out.println("Witam programisto. Wlasnie uruchomiles program, kt�ry zmieni Twoj swiat. \n "
				+ "Teraz b�dziesz mogl podac nazwe folderu w ktorym sa pliki o rozszerzeniu, kt�re Cie interesuje "
				+ "a na koniec zamienic wskazany ciag bajt�w w tych plikach na taki jaki wskazesz.");
		
		while(!endRunner) {
			boolean arrayConverted=false;
			System.out.println("Podaj nazw� szukanego folderu: ");
			String directoryName = sc.nextLine();
			while(directoryName.isEmpty()) {
				System.out.println("Musisz wprowadzic nazw� folderu: ");
				directoryName = sc.nextLine();
			} 
				
			System.out.println("Podaj format plikow w ktorych chcesz dokonac zmiany:");
			String fileType = sc.nextLine();
			while(fileType.isEmpty()||fileType.charAt(0)!='.') {
				System.out.println("Musisz wprowadzic typ szukanego pliku (np.'.bit'): ");
				fileType = sc.nextLine();
			} 
				
			System.out.println("Podaj ciag bajtow, ktore chcesz zmienic w pliku (przyk�ad: 2 50 60 87):");
			String byteArray1 = sc.nextLine();
			byte [] userArray1 = null;
			while(byteArray1.isEmpty()) {
				System.out.println("Ci�g bajt�w nie mo�e by� pusty. Wprowadz ci�g bajt�w");
				byteArray1 = sc.nextLine();
			}
			while(!arrayConverted) {
				try {
					userArray1 =conv.convert(byteArray1);
					arrayConverted=true;
				} catch (NumberFormatException e) {
					System.out.println(e.toString()+'\n'+"Podany ci�g zawiera b��dny format lub wychodzi poza zakres warto�ci bajta. Wprowadz ponownie: ");
					byteArray1 = sc.nextLine();
					while(byteArray1.isEmpty()) {
						System.out.println("Ci�g bajt�w nie mo�e by� pusty. Wprowadz ci�g bajt�w");
						byteArray1 = sc.nextLine();
					}
					arrayConverted=false;
				}
			}
		
			System.out.println("Podaj ciag bajtow, ktore chcesz wprowadzic do pliku (przyk�ad: 2 50 60 87):");
			String byteArray2 = sc.nextLine();
			byte [] userArray2 =null;
			while(byteArray1.isEmpty()) {
				System.out.println("Ci�g bajt�w nie mo�e by� pusty. Wprowadz ci�g bajt�w");
				byteArray1 = sc.nextLine();
			}
			arrayConverted=false;
			while(!arrayConverted) {
				try {
					userArray2 =conv.convert(byteArray2);
					arrayConverted=true;
				} catch (NumberFormatException e) {
					System.out.println(e.toString()+'\n'+"Podany ci�g zawiera b��dny format lub wychodzi poza zakres warto�ci bajta. Wprowadz ponownie: ");
					byteArray2 = sc.nextLine();
					while(byteArray1.isEmpty()) {
						System.out.println("Ci�g bajt�w nie mo�e by� pusty. Wprowadz ci�g bajt�w");
						byteArray1 = sc.nextLine();
					}
					arrayConverted=false;
				}
			}
		
			if(fUApi.findDirectoryByName(directoryName)) {
				if (fUApi.findAllFilesByType(fileType)) {
					fUApi.swopTheBytes(userArray1, userArray2);
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
