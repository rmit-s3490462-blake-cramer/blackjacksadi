
package client;

import java.io.IOException;




//Gamelistener
public class GameListener {
	
	/**
	 * GameInterfaceController
	 */
	private GameInterfaceController controller;
	
	/**
	 * Constructor
	 * @param g
	 */
	public GameListener(GameInterfaceController g){
		controller = g;
	}
	
	/**
	 * Parses an incoming line
	 * 
	 * @param s Incoming line
	 */
	/*public void parseIncoming(String s) {
		String incoming = s;
		//if(incoming.startsWith("req")) {
			String[] response = incoming.split(",");
			if(response[1].equals("options")) {
				System.out.println(incoming);
				this.setOptions(response);
			} else if(response[1].equals("cards")){
				this.setCards(response);
			} else if(response[1].equals("quit")){
				this.quit();
			} else if(response[1].equals("roundcount")){
				this.roundcount(Integer.parseInt(response[2]));
			}else if(response[1].equals("syncstats")) {
				try {
					Launch.getMasterserverConnection().write(incoming);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}*/
	
	private void quit() {
		controller.getInterface().quitRoom();
		
	}

	private void roundcount(int parseInt) {
		controller.roundcount(parseInt);
		
	}

	/**
	 * Sets options a player can perform
	 * 
	 * @param response
	 */
	private void setOptions(String[] response) {
		String option = "";
		boolean split = false;
		boolean stand = false;
		boolean hit = false;
		
		for(int i = 2 ; i < response.length ; i++) {
			option = response[i];
			
			if(option.equals("split")) {
				split = true;
			} else if(option.equals("stand")) {
				stand = true;
			} else if(option.equals("hit")) {
				hit = true;
			}
		}
		
		controller.setOptions(split, hit, stand);
	}
	
	private void setCards(String response[]) {
		int a = 0;
		for(int i = 2 ; i <= (response.length - 2) ; i += 2) {
			
			String username = response[i];
			String card = response[i + 1];
			String cards[] = card.split("!");
			
			
			if(username.equals(Launch.getUsername())){
					
				controller.getInterface().setCardsUser(cards);
				controller.getInterface().setPlayerHandAmounts("Hand " + cards.length + " of  7");
				
			}else if(username.equals("Dealer")){
				controller.getInterface().setCardsDealer(cards);
			}else{
				try {
					controller.getInterface().setCardsPlayer(cards, username, a);
				} catch (Exception e) {
					e.printStackTrace();
				}
				a++;
			}
			
		}

	}

}
