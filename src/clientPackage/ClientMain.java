package clientPackage;

import commonPackage.Bac;
import java.io.*;
//import java.net.*;
import java.util.Scanner;

public class ClientMain {

	public static void main(String[] args) throws IOException {
		
		Bac bacOut = new Bac();
		Bac bacIn = new Bac();
		boolean quit = false;

	
  		// demande de l'adresse du host � l'utilisateur
		System.out.println("adresse du host :");
		Scanner sc2 = new Scanner(System.in);
		String host = sc2.nextLine();
		//sc2.close();

		
		// demande du num�ro de port � l'utilisatreur
		System.out.println("numero de port :");
		Scanner sc1 = new Scanner(System.in);
		int port = sc1.nextInt();
		//sc1.close();
		
		ClientView monclient = new ClientView(host, port);
		
		while(!quit){
			bacIn = monclient.instanceClient.listen();
			monclient.affiche(bacIn);
			/*if(bacIn.message == "you are dead!"){
				quit = true;
				System.out.println(quit);
			}*/
			if(bacIn.monTour){
				bacOut.action = monclient.getClientInput();
				monclient.instanceClient.sendRequest(bacOut);
			}
		}
		System.out.println("vous avez quitter la partie.");
		monclient.finish();
	}

}
