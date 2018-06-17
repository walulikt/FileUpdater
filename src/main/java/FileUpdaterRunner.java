
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
			boolean arrayConverted=false;
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
						fileType=MF.getFileTypeField().getText();
						userArray1=MF.getBayteArray1Field().getText();
						byteArray2=MF.getBayteArray2Field().getText();
						
							try {
								byteArray1 =conv.convert(userArray1);
								arrayConverted=true;
							} catch (NumberFormatException ex) {
								System.out.println(ex.toString()+'\n'+"Podany ci¹g zawiera b³êdny format lub wychodzi poza zakres wartoœci bajta. Wprowadz ponownie: ");

								arrayConverted=false;
							}
						
						arrayConverted=false;
						while(!arrayConverted) {
							try {
								userArray2 =conv.convert(byteArray2);
								arrayConverted=true;
							} catch (NumberFormatException exe) {
								System.out.println(exe.toString()+'\n'+"Podany ci¹g zawiera b³êdny format lub wychodzi poza zakres wartoœci bajta. Wprowadz ponownie: ");
								
								arrayConverted=false;
							}
						}
						if(fUApi.findDirectoryByName(directoryName)) {
							if (fUApi.findAllFilesByType(fileType)) {
								fUApi.swopTheBytes(byteArray1, userArray2);
								JOptionPane.showMessageDialog(null, "Eggs are not supposed to be green.");
								
							}
						}
					}
				});
			};	
	
		});
	}
}