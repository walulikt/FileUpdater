

import java.awt.BorderLayout;
import java.awt.TextField;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainFrame extends JFrame{
	
	//private TextPanel textPanel;
	//private Toolbar toolbar;
	private JButton btn;
	private TextField directoryTextField;
	private TextField fileTypeField;
	
	public MainFrame() {
		super ("File Updater");
		setLayout(new BorderLayout());
		
		btn = new JButton("Wykonaj Zadanie!");
		directoryTextField = new DataField();
		directoryTextField.setText("Folder");
		fileTypeField = new DataField();
		fileTypeField.setText("Typ Pliku");
		fileTypeField.setSize(100, 100);
		//directoryTextField.setText("Tutaj wpisz nazwê szukanego katalogu");
		
		add(btn, BorderLayout.SOUTH);
		add(directoryTextField, BorderLayout.NORTH);
		add(fileTypeField,BorderLayout.CENTER);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);		
	}

	
}
