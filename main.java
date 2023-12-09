import java.util.Scanner;
public class main {
	public static void printDeck(Cards[] deck) {
		for(int i = 0; i < deck.length; i++) {
			System.out.print("[ " + deck[i].number + " ");
			System.out.print(deck[i].colour + " ]");
		}
		System.out.println();
	}
	public static void printGame(Cards[] computerHand, Cards[] computerTable, Cards[] playerTable, Cards[] playerHand) {
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.print("Computer Hand  :  ");
		printDeck(computerHand);
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.print("Computer Table :  ");
		printDeck(computerTable);
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.print("Player Table   :  ");
		printDeck(playerTable);
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.print("Player Hand    :  ");
		printDeck(playerHand);
		System.out.println("---------------------------------------------------------------------------------------------------------");
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Cards Cards = new Cards(); 
		Cards[] deck = new Cards[40];
		String[] specialcards = {"-6","-5","-4","-3","-2","-1","+1","+2","+3","+4","+5","+6"};
		String[] signedcard = {"+/-","2x"};
		Cards[] sdcoloured = new Cards[48];
		Cards[] signeddeck = new Cards[2];
		Cards[] playerDeck = new Cards[10];
		Cards[] computerDeck = new Cards[10];
		Cards[] playerTable = new Cards[9];
		Cards[] computerTable = new Cards[9];
		Cards[] playerHand = new Cards[4];
		Cards[] computerHand = new Cards[4];
		String[] computerSide = {"X", "X", "X", "X"};
		
		int c = 0;
		int d = 0;
		boolean gameContinue = true;
		
		//..........................CREATING CARD DECKS................................................
		for(int i = 0; i < deck.length; i++) {
			if(i/10 > d) {
				c = 0;
				d++;
			}
			String a = String.valueOf(c+1);
			if(i < 10) {
				deck[i] = new Cards(a, "Blue");
			}else if(i < 20) {
				deck[i] = new Cards(a, "Yellow");
			}else if(i < 30) {
				deck[i] = new Cards(a, "Red");
			}else {
				deck[i] = new Cards(a, "Green");
			}
			c++;
		}
		c = 0;
		d = 0;
		for(int i = 0; i < sdcoloured.length; i++) {
			if(i/12 > d) {
				c = 0;
				d++;
			}
			if(i < 12) {
				sdcoloured[i] = new Cards(specialcards[c], "Blue");
			}else if(i < 24) {
				sdcoloured[i] = new Cards(specialcards[c], "Yellow");
			}else if(i < 36) {
				sdcoloured[i] = new Cards(specialcards[c], "Red");
			}else {
				sdcoloured[i] = new Cards(specialcards[c], "Green");
			}
			c++;
		}
		for(int i = 0; i < signeddeck.length; i++) {
			signeddeck[i] = new Cards(signedcard[i], "Null");
		}
		for(int i = 0; i < computerTable.length; i++) {
			computerTable[i] = new Cards("0","0");
		}
		for(int i = 0; i < playerTable.length; i++) {
			playerTable[i] = new Cards("0","0");
		}
		
		Cards.shuffle(deck);
		Cards.deal(deck,computerDeck,playerDeck);
		Cards.dealSigned(sdcoloured,signeddeck,computerDeck,playerDeck);
		Cards.dealHand(computerDeck,computerHand,playerDeck,playerHand);
		
		int gameRound = 0;
		int gameMenu = -1;
		printGame(computerHand,computerTable,playerTable,playerHand);
		System.out.println("The game begins...");
		while(gameContinue) {
			printGame(computerHand,computerTable,playerTable,playerHand);
			while(true) {
				Cards.addCardTable(playerDeck,playerTable,gameRound);
				System.out.println("-1--> Continue the game.");
				System.out.println("-2--> Stop game.");
				System.out.println("-3--> Stop game and use special card.");
				System.out.print("---Select one: ");
				gameRound = sc.nextInt();
				if(gameRound == 1) {
					System.out.println("CPU pulling card...");
				}else if(gameRound == 2) {
					System.out.println("CPU pulling last card...");
					gameContinue = false;
				}else if(gameRound == 3) {
					System.out.println("Special Card sorgusu");
				}else {
					System.out.println("You enter wrong number. Try again.");
					continue;
				}
				Cards.addCardTable(computerDeck,computerHand,gameRound);
			}
			printGame(computerHand,computerTable,playerTable,playerHand);
			gameContinue = false;
		}
		
	}
}