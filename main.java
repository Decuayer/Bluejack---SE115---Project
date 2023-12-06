import java.util.Scanner;
public class main {
	public static void printDeck(Cards[] deck) {
		for(int i = 0; i < deck.length; i++) {
			System.out.print(deck[i].number + " ");
			System.out.println(deck[i].colour);
		}
	}
	public static void main(String[] args) {
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
		
		int c = 0;
		int d = 0;
		
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
		
		Cards.shuffle(deck);
		Cards.deal(deck,computerDeck,playerDeck);
		Cards.dealsigned(sdcoloured,signeddeck,computerDeck,playerDeck);
		printDeck(computerDeck);
		System.out.println("----");
		printDeck(playerDeck);

		
	}
}