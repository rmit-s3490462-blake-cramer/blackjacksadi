//Packed in client.gui
package guiComponent;

//Imports
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
import java.util.Currency;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import client.ChatListener;
import client.Global;
import client.Launch;
import client.gui.controller.GameInterfaceController;
import client.gui.controller.GameListener;
import client.gui.jcomponents.*;

@SuppressWarnings("serial")
public class GameInterface extends JFrame {

        //Initialization JButtons
        private JButton _buttonQuitGame;
        private JButton _buttonOptionSplit;
        private JButton _buttonOptionStand;
        private JButton _buttonOptionHit;
        private JButton _buttonOptionDouble;
        private JLabel  _userHandIcon;
        private JLabel  _userTimeIcon;
        private JLabel  _userNameText;
        private JLabel  _saldoInfo;
        private JLabel  _handInfo;
        private JLabel  _handGameInfo;
        private JLabel  _moneyInfo;
        private JLabel  _timeInfo;
        private JTextArea _gameInformation;
        private JTextArea _resultArea = new JTextArea();
      
        JPanel[] card = new JPanel[0];
        JPanel[] cardD = new JPanel[0];
        JPanel[] card1 = new JPanel[0];
        JPanel[] card2 = new JPanel[0];
        JPanel[] card3 = new JPanel[0];
        JPanel[] card4 = new JPanel[0];
        
        private int counter = 0;
    	
        //JPANELS
        JPanel QuitGameButton;
        JPanel SplitButton;
        JPanel StandButton;
        JPanel HitButton;
        JPanel DoubleButton;
        JPanel UserIcon;
        JPanel UserName;
        JPanel SaldoInfo;
        JPanel HandIcon;
        JPanel HandInfo;
        JPanel HandGameInfo;
        JPanel ClockIcon;
        JPanel TimeInfo;
        MyPanel UserOverview;
        
        
        //User info     
        private String _userName;
        private String _handAmounts;
        private String _gameAmounts;
        
                
        //Game info
        private String gameInformationText = "Rounds : 1";
             
         //Amount of players
        private int _amountOfPlayers;

        //Clock info
	   private String time;
        
       private GameInterfaceController _controller;
       
       //playerCards
       private ArrayList<playerCards> playerCards = new ArrayList<playerCards>();
        
       public GameInterface() {
        	_controller = new GameInterfaceController(this);
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
            String bgPath			=       "data/background.png";
            //Game Options
            String quitGamePath     =       "data/quitgame.png";
            
            //Player GameOptions
            String optionSplitPath  =       "data/optionButtons/split.png";
            String optionStandPath  =       "data/optionButtons/stand.png";
            String optionHitPath    =       "data/optionButtons/hit.png";
            String optionDoublePath =       "data/optionButtons/double.png";
        
            //User Oriented Options
            String imageClockPath   =       "data/user/clock.png";
            String imageHandPath    =       "data/user/hand.png";
             String imageUserPath    =       "data/user/usericon.png";
            //Game info
            String roundInfoPath    =       "data/useroverview/roundinfo.png";
            

            
            
            //Initialize width and height of background image
            int w = 950;
            int h = 635;
            
            //Initialize a new JPanel
            QuitGameButton = new JPanel();
            SplitButton = new JPanel();
            StandButton = new JPanel();
            HitButton = new JPanel();
            DoubleButton = new JPanel();
            UserIcon = new JPanel();
            UserName = new JPanel();
            SaldoInfo = new JPanel();
            HandIcon = new JPanel();
            HandInfo = new JPanel();
            HandGameInfo = new JPanel();
            ClockIcon = new JPanel();
            TimeInfo = new JPanel();
            UserOverview = new MyPanel();
            
            
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
            _buttonQuitGame = new MyButton(quitGamePath).getButton();
            _buttonQuitGame.addActionListener(al);
            
            
            //Declaration JButton Split
            _buttonOptionSplit = new MyButton(optionSplitPath).getButton();
            _buttonOptionSplit.addActionListener(al);
            _buttonOptionSplit.addMouseListener(ml);
            
            //Declaration JButton Stand
            _buttonOptionStand = new MyButton(optionStandPath).getButton();
            _buttonOptionStand.addActionListener(al);
            
            //Declaration JButton hit
            _buttonOptionHit = new MyButton(optionHitPath).getButton();
            _buttonOptionHit.addActionListener(al);
            
            //Declaration JButton double
            _buttonOptionDouble = new MyButton(optionDoublePath).getButton();
            _buttonOptionDouble.addActionListener(al);
            
            //Declaration of User info (image/icon)
            JLabel _userAvatar = new JLabel(new ImageIcon(imageUserPath));
            
            //Declaration of Hand info (image/icon)
            _userHandIcon = new JLabel(new ImageIcon(imageHandPath));
            
              
            //Declaration of Time info (image/icon)
            _userTimeIcon = new JLabel(new ImageIcon(imageClockPath));
            
            //Declaration of the Round Information panel
            Image roundInfo = kit.getImage(roundInfoPath);
            BackgroundimagePanel GameInfo = new BackgroundimagePanel(roundInfo);
            
                       
            //Label User name (text)
            _userNameText = new MyLabel(getUsername(), Color.WHITE, 13).getJLabel();
                    
            //JLabel saldo Info (text)
            _saldoInfo = new MyLabel("Saldo: " + currency.getSymbol() + Launch.getSaldo() + ",-", Color.WHITE, 11).getJLabel();
            
            //JLabel Hand info (text)
            _handInfo = new MyLabel(_handAmounts, Color.WHITE, 11).getJLabel();
            
            //JLabel Game info (text)
            _handGameInfo = new MyLabel(_gameAmounts, Color.WHITE, 11).getJLabel();
            
                      
            //JLabel Time info (text)
            _timeInfo = new MyLabel("Time playing: " + time, Color.WHITE, 11).getJLabel();
            
            //JTextArea _resultArea definition
            _resultArea = new MyTextArea(7,18).getTextArea();
            
            //JLabel _gameInformation
            _gameInformation = new MyTextArea(2,20).getTextArea();
            _gameInformation.setFont(new Font(_gameInformation.getFont().getFontName(), _gameInformation.getFont().getStyle(), 10));
            _gameInformation.setText(gameInformationText);
            _gameInformation.setEditable(false);

			//Instantiate a new RoomListener
			GameListener g = new GameListener(_controller);
            
			//ChatListener 
			_cl = new ChatListener(null, Launch.getGameserverConnection(), null,g);
			_cl.setChatPanel(chatPanel);
			chatPanel.setFont(7);
			_cl.start();
			
			//TimeCounter
			TimeCounter tc = new TimeCounter(this);
			tc.start();
            
                                       
            //Define color  ChatMessageFiedbg
            ChatMessageFieldbg.setBackground(new Color(30,30,30));

            ToGameRoomButton.add(_buttonToGameRoom);
            QuitGameButton.add(_buttonQuitGame);
            SplitButton.add(_buttonOptionSplit);
            StandButton.add(_buttonOptionStand);
            HitButton.add(_buttonOptionHit);
            DoubleButton.add(_buttonOptionDouble);
            UserIcon.add(_userAvatar);
            UserName.add(_userNameText);
            SaldoInfo.add(_saldoInfo);
            HandIcon.add(_userHandIcon);
            HandInfo.add(_handInfo);
            HandGameInfo.add(_handGameInfo);
            ClockIcon.add(_userTimeIcon);
            TimeInfo.add(_timeInfo);
            GameInfo.add(_gameInformation);
            
            
            //TODO
       
            
            
            
            //Add panels to contentPane
            add(QuitGameButton);                    //JButton _buttonQuitGame
            add(SplitButton);                               //JButton _buttonOptionSplit
            add(StandButton);                               //JButton _buttonOptionStand
            add(HitButton);                                 //JButton _buttonOptionHit
            add(DoubleButton);                              //JButton _buttonOptionDouble
            add(UserIcon);                                  //JLabel _userAvatar
            add(UserName);                                  //JLabel _userNameText
            add(SaldoInfo);                                 //JLabel _saldoInfo
            add(HandIcon);                                  //JLabel _userHandIcon
            add(HandInfo);                                  //JLabel _handInfo
            add(HandGameInfo);                              //JLabel _handGameInfo
            add(ClockIcon);                                 //JLabel _userTimeIcon
            add(TimeInfo);                                  //JLabel _userTimeIcon
            add(UserOverview);                              //JTextArea/JScrollpane users
            add(GameInfo);                                  //JPanel GameInfo
                       
            //Some panels should be transparant
            QuitGameButton.setOpaque(false);
            SplitButton.setOpaque(false);
            StandButton.setOpaque(false);
            HitButton.setOpaque(false);
            DoubleButton.setOpaque(false);
            UserIcon.setOpaque(false);
            UserName.setOpaque(false);
            SaldoInfo.setOpaque(false);
            HandIcon.setOpaque(false);
            HandInfo.setOpaque(false);
            HandGameInfo.setOpaque(false);
            ClockIcon.setOpaque(false);
            TimeInfo.setOpaque(false);
            UserOverview.setOpaque(false);
            GameInfo.setOpaque(false);
            //ChatMessageFieldbg.setOpaque(false);
            
            //Set boundaries of the OptionButtons
                    //Boundaries JButton Split, JButton Stand, JButton Hit and JButton Double
            QuitGameButton.setBounds(70, 563, 102, 50);
            SplitButton.setBounds(292, 476, 50, 78);
            StandButton.setBounds(344, 478, 51, 78);
            HitButton.setBounds(398, 478, 50, 78);
            DoubleButton.setBounds(450, 474, 50, 78);
            UserIcon.setBounds(330, 6, 51, 75);
            UserName.setBounds(315, 75, 80, 25);
            SaldoInfo.setBounds(300, 92, 120, 25);
            HandIcon.setBounds(440, 23, 48, 55);
            HandInfo.setBounds(405, 78, 110, 25);
            HandGameInfo.setBounds(405, 92, 110, 25);
            ClockIcon.setBounds(660, 23, 48, 55);
            TimeInfo.setBounds(630, 78, 120, 24);
            UserOverview.setBounds(751, 117, 240, 120);
            GameInfo.setBounds(751, 230, 193, 39);
            
            
            
            //Set some properties
            setLocation(165, 80);
            setResizable(false);
            setSize(w,h);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            
           
            this.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent event) {
                	_controller.leave();
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
            _buttonOptionSplit.setEnabled(false);
            _buttonOptionStand.setEnabled(false);
            _buttonOptionHit.setEnabled(false);
            _buttonOptionDouble.setEnabled(false);
		}
		
		
		
		/**
		 * 
		 * Get and Set Username
		 * @param userName is the username of the player which is taking his share in the game.
		 */
		
		public void setUsername(String userName) {
			_userName = userName;
		}
		
		public String getUsername() {
			return _userName;
		}
		
		
		 * 
		 * @param handAmounts is the amounts of hands the player is currently situated on, e.g. "Hand 4 of 5"
		 */
		
		public void setPlayerHandAmounts(String handAmounts) {
			_handAmounts = handAmounts;
			
		}
		
		public String getPlayerHandAmounts() {
			return _handAmounts;
		}
		
		public void setTime(String t){
			time = t;
			_timeInfo.setText("Time playing: " + t);
			this.validate();
			this.repaint();
		}
		public MyPanel getUserPanel(){
			return UserOverview;
		}
		
		
		/**
		 * 
		 * @param gameAmounts sets the game amount properties, e.g "Game 1 of 1"
		 */
		
		public void setGameAmounts(String gameAmounts) {
			_gameAmounts = gameAmounts;
		}
		
		public String getGameAmounts() {
			return _gameAmounts;
		}
		
		public void disposeScreen() {
			this.dispose();
		}
		
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == _buttonQuitGame) {	
					_controller.leave();//buttonQuitGame
					exitGame();
				} else if(e.getSource() == _buttonToGameRoom) {	
					_cl.stopListenChat();
					_controller.leave();
					Launch.getMainMenu().getRoomFrame().setVisible(true);
					
				} else if(e.getSource() == _buttonOptionSplit) {			//buttonOptionSplit
					GameInterface.this.setAllOptionsDisabled();
					_controller.splitOperation();
				} else if(e.getSource() == _buttonOptionStand) {			//buttonOptionStand
					GameInterface.this.setAllOptionsDisabled();
					_controller.standOperation();
				} else if(e.getSource() == _buttonOptionHit) {				//buttonOptionHit
					GameInterface.this.setAllOptionsDisabled();
					_controller.hitOperation();
				} else if(e.getSource() == _buttonOptionDouble) {			//buttonOptionDouble
					GameInterface.this.setAllOptionsDisabled();
					_controller.doubleOperation();
				
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
		public void setOptions(boolean split, boolean Double, boolean hit, boolean stand) {
			//Set all buttons
            _buttonOptionSplit.setEnabled(split);
            _buttonOptionStand.setEnabled(stand);
            _buttonOptionHit.setEnabled(hit);
            _buttonOptionDouble.setEnabled(Double);
            
            JOptionPane.showMessageDialog(null,
				    "It's your turn.",
				    "Your turn",
				    JOptionPane.QUESTION_MESSAGE);
		}
		
				
		public void setAmountOfPlayers(int amount) {
			_amountOfPlayers = amount;
		}
		
		public int getAmountOfPlayers() {
			return _amountOfPlayers;
		}		
		//Set cards of the dealer, location depends on the amount of cards
		public void setCardsDealer(String[] cardTypes) {
			_handInfo.setText(_handAmounts);
			this.validate();
			JLabel cardDeckName = null;
			for(JPanel p : card1){
				remove(p);
			}
            card1 = new JPanel[cardTypes.length];
            int _amountOfCards = cardTypes.length;
            
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
            
            int _amountOfCards = cardTypes.length;
            
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


		public void invalitBet() {
			this.setAllBetsEnabled();
			JOptionPane.showMessageDialog(null,
				    "Your bet is invalid. \n" +
				    "Please set your bet.",
				    "Bet",
				    JOptionPane.QUESTION_MESSAGE);
			
		}


		public void roundcount(int parseInt) {
			_gameInformation.setText("Rounds : " + parseInt);
			this.validate();
			
		}


		public void quitRoom() {
			_cl.stopListenChat();
			
			JOptionPane.showMessageDialog(null,
				    "Someone has left the game.",
				    "Leave",
				    JOptionPane.QUESTION_MESSAGE);
			Launch.getMainMenu().getRoomFrame().setVisible(true);
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