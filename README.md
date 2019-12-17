# blackjack game
## package : domain.card
### class : Card
variable
- private final Symbol symbol
- private final Type type
	
method
- public Card (Symbol symbol, Type type)
- public Card (Symbol symbol, Type type)
- public boolean equals (Object o)
- public int hashCode()
- public String toString()

### class : CardFactory
method
- public static List<Card> create()
- private static void createByType()
	
### enum : Type
variable
- { SPADE, DIAMOND, HEART, CLUB }

### class : Symbol
variable
- { ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
		 SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10)}
- private int score
	
method
- Symbol(int score)
- public int getScore()
	
## package : domain.user
### class : Dealer
variable
- private final List<Card> cards  = new ArrayList<>()
	
method
- public Dealer()
- public void addCard(Card card)

### class : Player
variable
- private final String name
- privare final double bettingMoney
- private final List<Card> cards = new ArrayList<>()
	
method
- public Player(String name, double bettingMoney)
- public void addCard(Card card)

## pacakge : domain
### class: BlackJackGame
vairiable
- private List<String> playerNames
- private int numberOfPlayers
- private List<Player> players

method
- public void setPlayerNames()
- public void setNumberOfPlayers()
- public void setPlayers()
- public int getNumberOfPlayers()
- public String getPlayerNameByIndex(int index)
- public void run()