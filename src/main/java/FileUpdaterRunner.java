
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
				System.out.println("Aplikacja w��czona. Czeka na wprowadzenie i zatwierdzenie danych");
				
				MF.getBtn().addActionListener(new ActionListener () {
					public void actionPerformed(ActionEvent e) {
						directoryName=MF.getDirectoryTextField().getText();
						userArray1=MF.getBayteArray1Field().getText();
						byteArray2=MF.getBayteArray2Field().getText();
						
						if(MF.getDirectoryTextField().getText().length()>10) {
							JOptionPane.showMessageDialog(null, "Zbyt d�uga nazwa folderu. \n Podaj now� nazw� szukanego folderu");
						} else if(MF.getDirectoryTextField().getText()==null||
								MF.getDirectoryTextField().getText().isEmpty()){
							JOptionPane.showMessageDialog(null, "Nie wprowadzono nazwy folderu");
						}else {
							if(!MF.getFileTypeField().getText().startsWith(".")||
									MF.getFileTypeField().getText()==null||
									MF.getFileTypeField().getText().isEmpty()) {
								JOptionPane.showMessageDialog(null,"Typ szukanych plik�w powinien zaczyna� si� od '.' lub nie wprowadzono danych");
							} else {
								fileType=MF.getFileTypeField().getText();
								if(userArray1!=null&&!userArray1.isEmpty()) {
									try {
										byteArray1 =conv.convert(userArray1);
										arrayConverted=true;
									} catch (NumberFormatException ex) {
										arrayConverted=false;
										JOptionPane.showMessageDialog(null,ex.toString()+'\n'+"Pierwszy ci�g bajt�w zawiera b��dny format lub wychodzi poza zakres warto�ci bajta. Wprowadz ponownie:");
									}
								} else JOptionPane.showMessageDialog(null,"Ci�g bajt�w nie mo�e by� pusty.");
								if(arrayConverted==true) {
									if(userArray1!=null&&!userArray1.isEmpty()) {
										try {
											userArray2 =conv.convert(byteArray2);
											arrayConverted=true;
										} catch (NumberFormatException exc) {
											JOptionPane.showMessageDialog( null,exc.toString()+'\n'+"Drugi ci�g bajt�w zawiera b��dny format lub wychodzi poza zakres warto�ci bajta. Wprowadz ponownie: ");
											arrayConverted=false;
										}
									} else JOptionPane.showMessageDialog(null,"Ci�g bajt�w nie mo�e by� pusty.");
								} 
								if(arrayConverted==true&&fUApi.findDirectoryByName(directoryName)) {
									JOptionPane.showMessageDialog(null, "Program wyszuka� foldery w nast�puj�cych lokalizacjach: \n"+ fUApi.getFinder().toString(fUApi.getFinder().getDirectoryPaths()));
									if (fUApi.findAllFilesByType(fileType)) {
										JOptionPane.showMessageDialog(null, "Program wyszuka� pliki w nast�puj�cych lokalizacjach: \n"+ fUApi.getFinder().toString(fUApi.getFinder().getTheFilePaths()));
										fUApi.swopTheBytes(byteArray1, userArray2);
										JOptionPane.showMessageDialog(null, "Zadanie zako�czone sukcesem.");
									}
									else {
										JOptionPane.showMessageDialog(null,"Nie odnaleziono plik�w podanego typu we wskazanym folderze.");
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