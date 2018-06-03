import java.util.Arrays;
import java.util.Scanner;

public class FileUpdaterRunner {
	
	public static void main(String[] args) {
		FileUpdaterApi fUApi = new FileUpdaterApi();
		StringToByteConverter conv = new StringToByteConverter();
		
		Scanner sc = new Scanner (System.in);
		boolean endRunner = false;
		System.out.println("Witam programisto. Wlasnie uruchomiles program, który zmieni Twoj swiat. \n "
				+ "Teraz bêdziesz mogl podac nazwe folderu w ktorym sa pliki o rozszerzeniu, które Cie interesuje "
				+ "a na koniec zamienic wskazany ciag bajtów w tych plikach na taki jaki wskazesz.");
		
		while(!endRunner) {
			System.out.println("Podaj nazwê szukanego folderu: ");
			String directoryName = sc.nextLine();
				
			System.out.println("Podaj format plikow w ktorych chcesz dokonac zmiany:");
			String fileType = sc.nextLine();
				
			System.out.println("Podaj ciag bajtow, ktore chcesz zmienic oddzielone przecinkiem (przyk³ad: 2,50,60,87):");
			String byteArray1 = sc.next();
			byte [] userArray1 =conv.convert(byteArray1);
			sc.nextLine();
		
			System.out.println("Podaj ciag bajtow, ktore chcesz wprowadzic:oddzielone przecinkiem (przyk³ad: 2,50,60,87)");
			String byteArray2 = sc.next();
			byte [] userArray2 =conv.convert(byteArray2);
			sc.nextLine();
		
			fUApi.findDirectoryByName(directoryName);
			if (fUApi.findAllFilesByType(fileType)) {
				fUApi.swopTheBytes(userArray1, userArray2);
			}
			
			System.out.println("Koniec sesji. Jesli chcesz zakonczycz program wcisnij 'n', jesli nie to wcisnij cokolwiek.");
			String userInput = sc.nextLine();
			if(userInput.equals("n")) {
				System.out.println("Dziekuje za skorzystanie z programu. Zapraszam ponownie");
				endRunner=true;
			}
		}
		sc.close();
	}
}
