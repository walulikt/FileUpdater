
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {
	
	private JButton btn;
	private TextArea apiDescriptionTextField;
	private TextField directoryTextField;
	private TextField fileTypeField;
	private TextField bayteArray1Field;
	private TextField bayteArray2Field;
	private String description = "Witam programisto. Wlasnie uruchomiles program, kt�ry zmieni Twoj swiat.\n" +  
			"Teraz b�dziesz mogl podac nazwe folderu w ktorym sa pliki o rozszerzeniu,\n"+ 
			"kt�re Cie interesuje, a na koniec zamienic wskazany ciag bajt�w w tych plikach na taki jaki wskazesz.\n"+
			"Po wpisaniu wszystkich danych wci�nij przycis 'Wykonaj Zadanie!' i poczekaj, a� wy�wietli si� informacja o wykonaniu zadania.";
		
	public TextField getDirectoryTextField() {
		return directoryTextField;
	}

	public TextField getFileTypeField() {
		return fileTypeField;
	}

	public TextField getBayteArray1Field() {
		return bayteArray1Field;
	}

	public TextField getBayteArray2Field() {
		return bayteArray2Field;
	}

	public MainFrame() {
		super ("File Updater");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLayout(new GridLayout(6,1));
		
		apiDescriptionTextField = new TextArea();
		apiDescriptionTextField.setText(description);
		apiDescriptionTextField.setEditable(false);
		
		directoryTextField = new TextField();
		directoryTextField.setText("Tutaj wpisz nazw� szukanego katalogu.");
		
		fileTypeField = new TextField();
		fileTypeField.setText("Tutaj wpisz typ szukanego pliku (np. .bat).");
		
		bayteArray1Field=new TextField();
		bayteArray1Field.setText("Podaj ci�g bajt�w, kt�ry chcesz wyszuka�  i zmienic w pliku w formie: 10 22 34 80.");
		
		bayteArray2Field=new TextField();
		bayteArray2Field.setText("Podaj ci�g bajt�w na kt�ry chcesz zamie� wy�ej wymieniony ci�g w formie: 80 34 22 10.");
				
		btn = new JButton("Wykonaj Zadanie!");
		
		add(apiDescriptionTextField);
		add(new JScrollPane(directoryTextField));
		add(new JScrollPane(fileTypeField));
		add(new JScrollPane(bayteArray1Field));
		add(new JScrollPane(bayteArray2Field));
		add(btn);			
	}
	
	public JButton getBtn() {
		return btn;
	}
}