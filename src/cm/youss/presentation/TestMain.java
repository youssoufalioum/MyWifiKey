package cm.youss.presentation;

import java.io.File;
import java.nio.file.Path;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File file = new File("key.txt");
		String chemin=file.getAbsolutePath();
		System.out.println(chemin);  // utulisation de la commande find de l'invite de commande pour trouver une chaine de caractere: find "Contenu de la cl" "E:\Mes projets JSE\MyWifiKey\key.txt"
		
	}

}
