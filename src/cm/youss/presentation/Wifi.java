package cm.youss.presentation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;

import javax.swing.JOptionPane;

public class Wifi {
	
	
	private String key;
	private List<String> lignes;
	
	
	
		public List<String> getLignes() {
		return lignes;
	}


		public Wifi() {
		super();
		// TODO Auto-generated constructor stub
		}
		
		

		public void genererKeyFile(String nomWifi) {
			
		File fichier= new File("wifi.cmd");
		File cheminFile = new File("key.txt");
		Process execute;
		
		
		try {
			
			//créer un fichier vide
			boolean bool=fichier.createNewFile();
			System.out.println(bool);
			
			//Ecrire dans le fichier .cmd
			FileWriter fileWriter = new FileWriter(fichier);
			char guillemet = '"';
			fileWriter.write("netsh wlan show profiles "+guillemet+nomWifi+guillemet+" key=clear>>key.txt\n");
			String chemin = cheminFile.getAbsolutePath();
			fileWriter.write("find "+guillemet+"Contenu de la cl"+guillemet+" "+guillemet+chemin+guillemet+">>filtre.txt");
			fileWriter.flush();
			System.out.println(fileWriter);
			fileWriter.close();
			
			//Executer le fichier .cmd
			execute = new ProcessBuilder("wifi.cmd").inheritIO().start();
			System.out.println(execute);
	
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
		
		
		public String readKey() {
			
			//lire le fichier generer par la commande (clé)
			
			Path path = FileSystems.getDefault().getPath("filtre.txt"); //"E:\\Mes projets JSE\\MyWifiKey", 
		List<String> ligne;
		try {
			ligne = Files.readAllLines(path,StandardCharsets.ISO_8859_1);
			System.out.println(ligne.size());
			String cle = ligne.get(2);
			System.out.println(cle);
			
			 String input1 = cle;
		     Scanner sc = new Scanner(input1);
		     sc.findInLine(": (.*)");
		     MatchResult result = sc.match();
		     for (int i=1; i<=result.groupCount(); i++) { 
		    			// System.out.println("Mot de passe WiFi: "+result.group(i));
		    	 key=result.group(i);
		    		     sc.close();
		     }
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}catch (IndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(null, "Ce Profil est introuvable sur le système");
		}
		
		return key;
			
		}
		
		
		
		public void genererUsersFile() {
			
			File fichier= new File("wifilist.cmd");
			Process execute;
			
			
			try {
				
				//créer un fichier vide
				boolean bool=fichier.createNewFile();
				
				//Ecrire dans le fichier .cmd
				FileWriter fileWriter = new FileWriter(fichier);
				fileWriter.write("netsh wlan show profiles>>usersprofils.txt");
				fileWriter.flush();
				fileWriter.close();
				
				//Executer le fichier .cmd
				execute = new ProcessBuilder("wifilist.cmd").inheritIO().start();
		
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		}
		
		
	
		
		
		public void readUsersProfils() {
			
			//lire le fichier generer par la commande (usersprofils)
			
		Path path1 = FileSystems.getDefault().getPath("usersprofils.txt"); //"E:\\Mes projets JSE\\MyWifiKey", 
		
		List<String> ligne1;
		
		try {
			
			ligne1 = Files.readAllLines(path1,StandardCharsets.ISO_8859_1);
			
			for(int i=9 ;i<ligne1.size() ; i++) {
				
			String tablist= ligne1.get(i);
			//System.out.println(tablist);
		    Scanner scn = new Scanner(tablist);
		    scn.findInLine(": (.*)");
		    MatchResult result = scn.match();
		    
		    for (int j=1; j<=result.groupCount(); j++) { 
		    	
		    	System.out.println("Profils: "+result.group(j));
		    	
		    	//creation du fichier List de profils pour inserer la liste filtrer
		    	File cheminProfilsFile = new File("listProfils.txt");
		    	boolean bool=cheminProfilsFile.createNewFile();
		    	//insertion
		    	FileWriter ProfilFileWriter = new FileWriter(cheminProfilsFile);
		    	String alaLigne="\n";
		    	ProfilFileWriter.write(result.group(j)+alaLigne);
		    	//System.out.println(ProfilFileWriter);
		    	ProfilFileWriter.flush();
		    	ProfilFileWriter.close();
		    	
		    	
		    	
		    	//System.out.println(lignes);
		    	//listProfil
		    	scn.close();
		    	
		     }
			}
				
		} catch (IOException e) {

			e.printStackTrace();
		}catch (IllegalStateException e) {

			System.out.println("Illegal Exception");
		}
		
		
		}
		
		public void lireAllLines() {
			
			Path cheminListProfil = FileSystems.getDefault().getPath("listProfils.txt");
			try {
				lignes = Files.readAllLines(cheminListProfil,StandardCharsets.ISO_8859_1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		

}
