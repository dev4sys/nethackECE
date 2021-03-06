package clientPackage;
import commonPackage.Bac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import serverPackage.Item;


public class ClientView  {
	private String clientIpnut;
	Client instanceClient;
	Bac ClientBac;
	
	public ClientView(String host, int port){
		instanceClient = new Client(host, port);
		clientIpnut = "";
	}
	

	public String getClientInput() throws IOException{
		String dir;
		boolean runningState =false;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//while (clientIpnut.equalsIgnoreCase("quit") == false) {
		while (runningState == false) {
			System.out.println("your turn !");
			clientIpnut = in.readLine();
			
			if (clientIpnut.matches("z|s|q|d")){
				//direction key event
				dir= this.getDirection(clientIpnut);
				clientIpnut = "move:"+dir;
				runningState =true;
				
			}else if(clientIpnut.contains("use")){
				// use item event
				String itemName = this.getItemToUse(clientIpnut);
 				if(itemName.isEmpty()){
					runningState =false;
 					System.out.println("What item do you want to use?");
 				}else if(this.checkInInventory(itemName)){
  					// check if item is in inventory
  					clientIpnut ="use:"+itemName;
 					runningState =true;
  				}else{
  					runningState =false;
 					System.out.println("No such item in your inventory! Try again");
  				}
			
			}else if(clientIpnut.contains("pick")){
				// pick item event
				//String itemtoAdd = getItemtoPick(clientIpnut);
				clientIpnut ="pick:_";
				// pass to itemtoAdd to an Inventory
				runningState =true;
				
			}else if(clientIpnut.contains("attack")){
				//attack opponent event
				String perso = this.getOpponentToAttack(clientIpnut);
				if(perso.isEmpty()){
					runningState =false;
 					System.out.println("Which direction to attack?");
				}else{
					clientIpnut ="attack:"+perso;
					runningState =true;
				}
			
			}else if(clientIpnut.matches("quit")) {
				//test if player wants to quit
				System.out.println("Good bye! :)");
				clientIpnut="quit:_";
				runningState =true;
				
			}else if(clientIpnut.matches("show")) {
				//test if player wants to quit
				clientIpnut="show:item";
				runningState =false;
				
			}else {
				// otherwise the event is unknown
				System.out.println("Command unknown, please type a correct action!");
				runningState =false;
			}
		}	
		//}
		//in.close();
		return clientIpnut;
	}

	
	private String getDirection(String key){
		/* z: 'up'
		 * q: 'left'
		 * s: 'down'
		 * d: 'right'
		 */
		String touche;
		switch (key){
			case "z" :touche="up";		break;
			case "q" :touche="left";	break;
			case "s" :touche="down";	break;
			case "d" :touche="right";	break;
			default: touche=""; break;
		}
		return touche;
	}
	
	public String getOpponentToAttack(String command){
		String perso = command.substring(command.lastIndexOf("attack")+6);
		perso = perso.trim();
		return perso;
	}
	
	public String getItemToUse(String command){
		String item = command.substring(command.lastIndexOf("use")+3).trim();
		return item;
	}
	
	public String getItemtoPick(String command){
		// need to pass this in the BAC to add to player inventory List
		String item = command.substring(command.lastIndexOf("pick")+5).trim();
		return item;
	}
	
	public void showInventoryList(){
		//need to get the inventory List from BAC
	}
	
	public boolean checkInInventory(String itemName){
		//invetory is retrieved from BAC
		boolean itemState = false;
		//ArrayList<String>inventory = new ArrayList<String>();
		ArrayList<String> inventory = ClientBac.items;
		for (Iterator<String> it = inventory.iterator(); it.hasNext(); ) {
		    String item = it.next();
		    if (item.equals(itemName)) {
		    	itemState = true;
		    /*}else{
		    	itemState = false;*/
		    } 
		}
		return itemState;	
	}
	
	
	public void affiche(Bac bac){
		for (int i = 0; i < 24; i++) {
			System.out.println(bac.affichetab[i]);
		}
		System.out.print("life:" + bac.life);
		System.out.print(" | strength:" + bac.strength);
		System.out.print(" | luck:" + bac.luck);
		System.out.println(" | resistance:" + bac.resistance);
		System.out.println("items : " + bac.items.toString());
		System.out.println(bac.message);
		this.ClientBac = bac;
	}
	
	public void finish(){
		instanceClient.close();
	}
}
