import java.util.ArrayList;

import org.junit.Test;

public class FinderToStringTest {

	@Test
	public void testToString() {
		//When
		Finder finder = new Finder();
		ArrayList<String> testPaths = new ArrayList<>();
		testPaths.add("Scie¿ka jedne");
		testPaths.add("Scie¿ka dwa");
		//Then
		String testString = finder.toString(testPaths);
		
		//Given
		
		System.out.println(testString);
	}
	
}
