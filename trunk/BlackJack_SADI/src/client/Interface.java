package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import client.BackgroundimagePanel;
import client.TimeCounter;
import client.GameInterfaceController;
//import client.gui.controller.GameListener;
//import client.gui.jcomponents.MyPanel;
import client.playerCards;



@SuppressWarnings("serial")
public class Interface extends JFrame {
	
	public static void main(String[] args){
		 Interface frame = new Interface();
		 frame.setLocation(165, 80);
         frame.setResizable(false);
         frame.setSize(950,635);
         frame.setVisible(true);
         frame.setAmountOfPlayers(6);
        
	}
	//Initialization JButtons
    private JButton buttonQuitGame;
    private JButton buttonToGameRoom;
    private JButton buttonOptionSplit;
    private JButton buttonOptionStand;
    private JButton buttonOptionHit;
    private JLabel  userHandIcon;
    private JLabel  userTimeIcon;
    private JLabel  userNameText;
    private JLabel  handInfo;
    private JLabel  handGameInfo;
    private JLabel  timeInfo;
    private JTextArea gameInformation;
    private JTextArea resultArea = new JTextArea();
    
    JPanel[] card = new JPanel[0];
    JPanel[] cardD = new JPanel[0];
    JPanel[] card1 = new JPanel[0];
    JPanel[] card2 = new JPanel[0];
    JPanel[] card3 = new JPanel[0];
    JPanel[] card4 = new JPanel[0];
    
    JPanel QuitGameButton;
    JPanel ToGameRoomButton;
    JPanel SplitButton;
    JPanel StandButton;
    JPanel HitButton;
    JPanel UserIcon;
    JPanel UserName;
    JPanel HandIcon;
    JPanel HandInfo;
    JPanel HandGameInfo;
    JPanel ClockIcon;
    JPanel TimeInfo;
    //MyPanel UserOverview;
        
    //User info     
    private String userName;
    private String handAmounts;
    private String gameAmounts;
   
            
    //Game info
    private String gameInformationText = "Rounds : 1";
   
    
    //Amount of players
    private int amountOfPlayers;

    //Clock info
   private String time;
   
   private ArrayList<playerCards> playerCards = new ArrayList<playerCards>();
   
   private GameInterfaceController controller;
   
   public Interface() {
   		controller = new GameInterfaceController(this);
   		try {
			displayInterface();
		} catch (Exception e) {
			e.printStackTrace();
		}
   }
   
   
   /**
    * @throws Exception
    * draws the windows
    */
   
   public void displayInterface() throws Exception {
	 //Initialize path to images
     //Image background
	   String bgPath="C:/Users/Sean Yee/Desktop/Assignment1/img/background.png";
	   
	 //Game Options
       String quitGamePath     =       "C:/Users/Sean Yee/Desktop/Assignment1/img/quitgame.png";
       String toGameRoomPath   =       "C:/Users/Sean Yee/Desktop/Assignment1/img/togameroom.png";
       
       //User Oriented Options
       String imageClockPath   =       "C:/Users/Sean Yee/Desktop/Assignment1/img/clock.png";
       String imageHandPath    =       "C:/Users/Sean Yee/Desktop/Assignment1/img/hand.png";
       String imageUserPath    =       "C:/Users/Sean Yee/Desktop/Assignment1/img/usericon.png";
       //Game info
       String roundInfoPath    =       "C:/Users/Sean Yee/Desktop/Assignment1/img/roundinfo.png";
      
       //Initialize width and height of background image
       int w = 950;
       int h = 635;
       
     //Initialize a new JPanel
       QuitGameButton = new JPanel();
       ToGameRoomButton = new JPanel();
       SplitButton = new JPanel();
       StandButton = new JPanel();
       HitButton = new JPanel();
       UserIcon = new JPanel();
       UserName = new JPanel();
       HandIcon = new JPanel();
       HandInfo = new JPanel();
       HandGameInfo = new JPanel();
       ClockIcon = new JPanel();
       TimeInfo = new JPanel();
       //UserOverview = new MyPanel();
       
     //Initialize Toolkit
       Toolkit kit = Toolkit.getDefaultToolkit();
       
       //Get the backgroundimage from path
       Image bg = kit.getImage(bgPath);
       
       //Create a new backgroundimagepanel instance
       BackgroundimagePanel contentPane = new BackgroundimagePanel(bg);
       setContentPane(contentPane);

       //Use absolute positioning for all components in this frame
       setLayout(null);
       
       
     //Declaration JButton Quit Game
       buttonQuitGame = new JButton("Quit Game");
       buttonQuitGame.addActionListener(al);
       
       //Declaration JButton Back to GameRoom
       buttonToGameRoom = new JButton("To Game");
       buttonToGameRoom.addActionListener(al); {
       
       //Declaration JButton Split
       buttonOptionSplit = new JButton("Split");
       buttonOptionSplit.addActionListener(al);
       buttonOptionSplit.addMouseListener(ml);
       
       //Declaration JButton Stand
       buttonOptionStand = new JButton("Stand");
       buttonOptionStand.addActionListener(al);
       
     //Declaration JButton hit
       buttonOptionHit = new JButton("Hit");
       buttonOptionHit.addActionListener(al);
       
       //Declaration of User info (image/icon)
       JLabel userAvatar = new JLabel(new ImageIcon(imageUserPath));
       
       //Declaration of Hand info (image/icon)
       userHandIcon = new JLabel(new ImageIcon(imageHandPath));
       
       //Declaration of Time info (image/icon)
       userTimeIcon = new JLabel(new ImageIcon(imageClockPath));
       
       //Declaration of the Round Information panel
       Image roundInfo = kit.getImage(roundInfoPath);
       BackgroundimagePanel GameInfo = new BackgroundimagePanel(roundInfo);
       
       // Label User name (text)
       userNameText = new JLabel(getUsername());
                
        //JLabel Hand info (text)
       handInfo = new JLabel(handAmounts);
        
        //JLabel Game info (text)
        handGameInfo = new JLabel(gameAmounts);
        
       //JLabel Time info (text)
       timeInfo = new JLabel("Time playing: " + time);
        
        //JTextArea _resultArea definition
        resultArea = new JTextArea();
        
        //JLabel _gameInformation
        gameInformation = new JTextArea(2,20);
        gameInformation.setFont(new Font(gameInformation.getFont().getFontName(),gameInformation.getFont().getStyle(), 10));
        gameInformation.setText(this.gameInformationText);
        gameInformation.setEditable(false);
        
        //Instantiate a new RoomListener
		//GameListener g = new GameListener(_controller);
       
      //TimeCounter
		TimeCounter tc = new TimeCounter(this);
		tc.start();
		
		ToGameRoomButton.add(buttonToGameRoom);
		QuitGameButton.add(buttonQuitGame);
        SplitButton.add(buttonOptionSplit);
        StandButton.add(buttonOptionStand);
        HitButton.add(buttonOptionHit);
        UserIcon.add(userAvatar);
        UserName.add(userNameText);
        HandIcon.add(userHandIcon);
        HandInfo.add(handInfo);
        HandGameInfo.add(handGameInfo);
        ClockIcon.add(userTimeIcon);
        TimeInfo.add(timeInfo);
        GameInfo.add(gameInformation);
        
        add(QuitGameButton);                    //JButton buttonQuitGame
        add(ToGameRoomButton);                  //JButton buttonToGameRoom
        add(SplitButton);                               //JButton buttonOptionSplit
        add(StandButton);                               //JButton buttonOptionStand
        add(HitButton);                                 //JButton _buttonOptionHit
        add(UserIcon);                                  //JLabel userAvatar
        add(UserName);                                  //JLabel userNameText
        add(HandIcon);                                  //JLabel userHandIcon
        add(HandInfo);                                  //JLabel handInfo
        add(HandGameInfo);                              //JLabel handGameInfo
        add(ClockIcon);                                 //JLabel userTimeIcon
        add(TimeInfo);                                  //JLabel userTimeIcon
        //add(UserOverview);                              //JTextArea/JScrollpane users
        add(GameInfo);                                  //JPanel GameInfo
        
        //Some panels should be transparant
        ToGameRoomButton.setOpaque(true);
        QuitGameButton.setOpaque(false);
        SplitButton.setOpaque(false);
        StandButton.setOpaque(false);
        HitButton.setOpaque(false);
        UserIcon.setOpaque(false);
        UserName.setOpaque(false);
        HandIcon.setOpaque(false);
        HandInfo.setOpaque(false);
        HandGameInfo.setOpaque(false);
        ClockIcon.setOpaque(false);
        TimeInfo.setOpaque(false);
        //UserOverview.setOpaque(false);
        GameInfo.setOpaque(false);
 
        ToGameRoomButton.setBounds(188, 557, 192, 55);
        QuitGameButton.setBounds(70, 563, 102, 50);
        SplitButton.setBounds(292, 476, 50, 78);
        StandButton.setBounds(344, 478, 51, 78);
        HitButton.setBounds(398, 478, 50, 78);
        UserIcon.setBounds(330, 6, 51, 75);
        UserName.setBounds(315, 75, 80, 25);
        HandIcon.setBounds(440, 23, 48, 55);
        HandInfo.setBounds(405, 78, 110, 25);
        HandGameInfo.setBounds(405, 92, 110, 25);
        ClockIcon.setBounds(660, 23, 48, 55);
        TimeInfo.setBounds(630, 78, 120, 24);
        //UserOverview.setBounds(751, 117, 240, 120);
        GameInfo.setBounds(751, 230, 193, 39);
        
      //Set some properties
        setLocation(165, 80);
        setResizable(false);
        setSize(w,h);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
            	controller.leave();
                System.exit(0);
            } 
         });
       }
	}
        public void exitGame() {
			
			System.exit(0);
        }
        

		/**
		 * Sets all options to disabled
		 */
		private void setAllOptionsDisabled() {
			//Set all buttons disabled
            buttonOptionSplit.setEnabled(false);
            buttonOptionStand.setEnabled(false);
            buttonOptionHit.setEnabled(false);
            
		}
		/**
		 * 
		 * Get and Set Username
		 * @param userName is the username of the player which is taking his share in the game.
		 */
		
		public void setUsername(String userName) {
			this.userName = userName;
		}
		
		public String getUsername() {
			return this.userName;
		}
		
		/**
		 * 
		 * @param handAmounts is the amounts of hands the player is currently situated on, e.g. "Hand 4 of 5"
		 */
		
		public void setPlayerHandAmounts(String handAmounts) {
			this.handAmounts = handAmounts;
			
		}
		
		public String getPlayerHandAmounts() {
			return this.handAmounts;
		}
		
		public void setTime(String t){
			time = t;
			timeInfo.setText("Time playing: " + t);
			this.validate();
			this.repaint();
		}
		
		/**
		 * 
		 * @param gameAmounts sets the game amount properties, e.g "Game 1 of 1"
		 */
		
		public void setGameAmounts(String gameAmounts) {
			this.gameAmounts = gameAmounts;
		}
		
		public String getGameAmounts() {
			return this.gameAmounts;
		}
		
		public void disposeScreen() {
			this.dispose();
		}
		
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == buttonQuitGame) {	
					controller.leave();//buttonQuitGa
					exitGame();
				} else if(e.getSource() == buttonOptionSplit) {			//buttonOptionSplit
					Interface.this.setAllOptionsDisabled();
					controller.splitOperation();
				} else if(e.getSource() == buttonOptionStand) {			//buttonOptionStand
					Interface.this.setAllOptionsDisabled();
					controller.standOperation();
				} else if(e.getSource() == buttonOptionHit) {				//buttonOptionHit
					Interface.this.setAllOptionsDisabled();
					controller.hitOperation();	
				}
		    }
		};
		
		MouseListener ml = new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		/**
		 * Set available options for client
		 * 
		 * @param split Whether split should be enabled
		 * @param Double Whether double should be enabled
		 * @param hit Whether hit should be enabled
		 * @param stand Whether stand should be enabled
		 */
		public void setOptions(boolean split,boolean hit, boolean stand) {
			//Set all buttons
            buttonOptionSplit.setEnabled(split);
            buttonOptionStand.setEnabled(stand);
            buttonOptionHit.setEnabled(hit);
                        
            JOptionPane.showMessageDialog(null,
				    "It's your turn.",
				    "Your turn",
				    JOptionPane.QUESTION_MESSAGE);
		}
		
		public void setAmountOfPlayers(int amount) {
			amountOfPlayers = amount;
		}
		
		public int getAmountOfPlayers() {
			return amountOfPlayers;
		}	
		
		//Set cards of the dealer, location depends on the amount of cards
		public void setCardsDealer(String[] cardTypes) {
			handInfo.setText(handAmounts);
			this.validate();
			JLabel cardDeckName = null;
			for(JPanel p : card1){
				remove(p);
			}
            card1 = new JPanel[cardTypes.length];
            int amountOfCards = cardTypes.length;
            
            //Reset cards
            for(int i = 0; i<card1.length; i++) {
            	card1[i] = null;
            }
            
            try {
				cardDeckName = new CardDeckLabel("Dealer", Color.WHITE, 12, true).getJLabel();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			CardPicker cp = new CardPicker();
			cp.setCards();
			
			add(cardDeckName);
			cardDeckName.setBounds(405, 230, 100, 25);
			
			for(int i = 0; i < cardTypes.length; i++) {
				if(i == 0){
					card1[i] = cp.getCardBackground();
					add(card1[i]);
					card1[i].setBounds(((390) + (i*30)), 200, 50, 80);
				}else{
				card1[i] = cp.getCard(cardTypes[i]);
				add(card1[i]);
				card1[i].setBounds(((390) + (i*30)), 200, 50, 80);
				}
			}
		}
		
		
		//Set cards of the user, location depends on the amount of cards
		public void setCardsUser(String[] cardTypes) {
			//TODO some finetuning concerning aligning.			
			JLabel cardDeckName = null;
			for(JPanel p : card){
				remove(p);
			}
			
			
            card = new JPanel[cardTypes.length];
            
            
            //Reset cards
            for(int i = 0; i<card.length; i++) {
				card[i] = null;
			}
            
            int amountOfCards = cardTypes.length;
            
            try {
				cardDeckName = new CardDeckLabel(getUsername(), Color.WHITE, 12, false).getJLabel();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			CardPicker cp = new CardPicker();
			cp.setCards();
			for(int i = 0; i < cardTypes.length; i++) {
				card[i] = cp.getCard(cardTypes[i]);
				add(card[i]);
				card[i].setBounds(((325) + (i*30)), 340, 50, 80);
			}
			
			add(cardDeckName);
			cardDeckName.setBounds(345, 420, 100, 20);
			this.validate();
		}
		
		
		//Set cards of the players, location depends on the amount of cards
		public void setCardsPlayer(String[] cardTypes, String userName, int pos) throws Exception {
			//Clean playerCards ArrayList
			playerCards plop = null;
			for(playerCards p : playerCards){
			if(userName.equals(p.getPlayerName())){
				for(JPanel c : p.getCard()){
					remove(c);
				}
			plop = p;
			remove(p.getNameLabel());
			}
			}
			if(plop != null){
				playerCards.remove(plop);
			}
			
			playerCards.add(new playerCards(cardTypes, userName));
			
			CardPicker cp = new CardPicker();
			cp.setCards();
			
			
			
			for(playerCards playerCard : playerCards) {
				if(userName.equals(playerCard.getPlayerName())){
				playerCard.addCard(new JPanel[playerCard.getPlayerCards().length]);
				JPanel[] card = playerCard.getCard();
				playerCard.addNameLabel(new JLabel());
				JLabel cardDeckName = playerCard.getNameLabel();
				

										 
				for(int b = 0; b<playerCard.getPlayerCards().length; b++) {
					card[b] = cp.getCard(playerCard.getPlayerCards()[b]);
					add(card[b]);
				}
				
				if(pos == 0) {
					
					for(int i = 0; i < playerCard.getPlayerCards().length; i++)
						card[i].setBounds(150 + (i*30), 290, 50, 80);
					
					cardDeckName = new CardDeckLabel(playerCard.getPlayerName(), Color.WHITE, 12, false).getJLabel();
					add(cardDeckName);
					cardDeckName.setBounds(165, 370, 100, 20);
					
				//	cardDeckName[0].setBounds(165, 370, 100, 20);
					
				} else if(pos == 1) {
					for(int i = 0; i < playerCard.getPlayerCards().length; i++)
						card[i].setBounds(500 + (i*30), 290, 50, 80);
					
					cardDeckName = new CardDeckLabel(playerCard.getPlayerName(), Color.WHITE, 12, false).getJLabel();
					add(cardDeckName);
					cardDeckName.setBounds(515, 370, 100, 20);
					
				//	cardDeckName[0].setBounds(515, 370, 100, 20);
					
				} else if(pos == 2) {
					for(int i = 0; i < playerCard.getPlayerCards().length; i++)
						card[i].setBounds(80 + (i*30), 190, 50, 80);
					
					cardDeckName = new CardDeckLabel(playerCard.getPlayerName(), Color.WHITE, 12, false).getJLabel();
					add(cardDeckName);
					cardDeckName.setBounds(95,270,100,20);
				//	cardDeckName[0].setBounds(95, 270, 100, 20);
					
				} else if(pos == 3) {
					for(int i = 0; i < playerCard.getPlayerCards().length; i++)
						card[i].setBounds(570 + (i*30), 190, 50, 80);
					
					cardDeckName = new CardDeckLabel(playerCard.getPlayerName(), Color.WHITE, 12, false).getJLabel();
					add(cardDeckName);
					cardDeckName.setBounds(585, 270, 100, 20);
				//	cardDeckName[counter].setBounds(585, 270, 100, 20);
				}
				
				

				
				this.validate();
			}
			}
		}
		
		public void clearPlayerCards() {
			playerCards.clear();
		}
		
		public void roundcount(int parseInt) {
			gameInformation.setText("Rounds : " + parseInt);
			this.validate();
			
		}
		
		public void quitRoom() {
						
			JOptionPane.showMessageDialog(null,
				    "Someone has left the game.",
				    "Leave",
				    JOptionPane.QUESTION_MESSAGE);
			
			disposeScreen();
			
		}
  
}

class playerCards {
	private String[] playerCards;
	private String playerName;
	private JPanel[] card;
	private JLabel name;
	public playerCards() {}
	
	public JLabel getNameLabel() {

		return name;
	}

	public void addNameLabel(JLabel label) {
		name = label;
		
	}

	public JPanel[] getCard() {
		return card;
	}

	public playerCards(String[] playerCards, String playerName) {
		this.setPlayerCards(playerCards);
		this.setPlayerName(playerName);
	}

	public void setPlayerCards(String[] playerCards) {
		this.playerCards = playerCards;
	}

	public String[] getPlayerCards() {
		return playerCards;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerName() {
		return playerName;
	}
	
	public void addCard(JPanel[] a){
		card = a;
	}
	
	
}
