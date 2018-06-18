
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FileUpdaterRunner {
	
	public static void main(String[] args) {
		
		
		SwingUtilities.invokeLater(new Runnable() {
			MainFrame MF;
			FileUpdaterApi fUApi;
			StringToByteConverter conv;	
			String directoryName="";
			String fileType="";
			String userArray1 ="";
			String byteArray2 ="";
			boolean arrayConverted=true;
			byte [] byteArray1 = null;
			byte [] userArray2 =null;
			
			public void run () {
				MF=new MainFrame();
				fUApi= new FileUpdaterApi();
				conv = new StringToByteConverter();
				System.out.println("Aplikacja w³¹czona. Czeka na wprowadzenie i zatwierdzenie danych");
				
				MF.getBtn().addActionListener(new ActionListener () {
					public void actionPerformed(ActionEvent e) {
						directoryName=MF.getDirectoryTextField().getText();
						userArray1=MF.getBayteArray1Field().getText();
						byteArray2=MF.getBayteArray2Field().getText();
						
						if(MF.getDirectoryTextField().getText().length()>10) {
							JOptionPane.showMessageDialog(null, "Zbyt d³uga nazwa folderu. \n Podaj now¹ nazwê szukanego folderu");
						} else if(MF.getDirectoryTextField().getText()==null||
								MF.getDirectoryTextField().getText().isEmpty()){
							JOptionPane.showMessageDialog(null, "Nie wprowadzono nazwy folderu");
						}else {
							if(!MF.getFileTypeField().getText().startsWith(".")||
									MF.getFileTypeField().getText()==null||
									MF.getFileTypeField().getText().isEmpty()) {
								JOptionPane.showMessageDialog(null,"Typ szukanych plików powinien zaczynaæ siê od '.' lub nie wprowadzono danych");
							} else {
								fileType=MF.getFileTypeField().getText();
								if(userArray1!=null&&!userArray1.isEmpty()) {
									try {
										byteArray1 =conv.convert(userArray1);
										arrayConverted=true;
									} catch (NumberFormatException ex) {
										arrayConverted=false;
										JOptionPane.showMessageDialog(null,ex.toString()+'\n'+"Pierwszy ci¹g bajtów zawiera b³êdny format lub wychodzi poza zakres wartoœci bajta. Wprowadz ponownie:");
									}
								} else JOptionPane.showMessageDialog(null,"Ci¹g bajtów nie mo¿e byæ pusty.");
								if(arrayConverted==true) {
									if(userArray1!=null&&!userArray1.isEmpty()) {
										try {
											userArray2 =conv.convert(byteArray2);
											arrayConverted=true;
										} catch (NumberFormatException exc) {
											JOptionPane.showMessageDialog( null,exc.toString()+'\n'+"Drugi ci¹g bajtów zawiera b³êdny format lub wychodzi poza zakres wartoœci bajta. Wprowadz ponownie: ");
											arrayConverted=false;
										}
									} else JOptionPane.showMessageDialog(null,"Ci¹g bajtów nie mo¿e byæ pusty.");
								} 
								if(arrayConverted==true&&fUApi.findDirectoryByName(directoryName)) {
									JOptionPane.showMessageDialog(null, "Program wyszuka³ foldery w nastêpuj¹cych lokalizacjach: \n"+ fUApi.getFinder().toString(fUApi.getFinder().getDirectoryPaths()));
									if (fUApi.findAllFilesByType(fileType)) {
										JOptionPane.showMessageDialog(null, "Program wyszuka³ pliki w nastêpuj¹cych lokalizacjach: \n"+ fUApi.getFinder().toString(fUApi.getFinder().getTheFilePaths()));
										fUApi.swopTheBytes(byteArray1, userArray2);
										JOptionPane.showMessageDialog(null, "Zadanie zakoñczone sukcesem.");
									}
									else {
										JOptionPane.showMessageDialog(null,"Nie odnaleziono plików podanego typu we wskazanym folderze.");
									}
								}else if (arrayConverted==true&&!fUApi.findDirectoryByName(directoryName)) {
									JOptionPane.showMessageDialog(null,"Nie odnaleziono folderu o podanej nazwie.");
								}
							}
						}
					}
				});
			};	
	
		});
	}
}