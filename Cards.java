import java.util.Random;
public class Cards{
	public String colour;
	public String number;
	
	public Cards(){};
	public Cards(String number, String colour) {
		this.colour = colour;
		this.number = number;
	}
	
	public void shuffle(Cards[] deck) {
		Random r = new Random(System.currentTimeMillis());
		for(int i = 0; i < deck.length; i++) {
			int randomindex = r.nextInt(deck.length);
			Cards temp;
			temp = deck[i];
			deck[i] = deck[randomindex];
			deck[randomindex] = temp;
		}
	}
	public void deal(Cards[] deck, Cards[] computerDeck, Cards[] playerDeck) {
		int decklength = deck.length-1;
		int counter = 0;
		for(int i = 0; i < 5; i++) {
			computerDeck[i] = deck[counter];
			playerDeck[i] = deck[decklength];
			counter++;
			decklength--;
		}
	}
	public void dealsigned(Cards[] sdcoloured, Cards[] signeddeck, Cards[] computerDeck, Cards[] playerDeck) {
		Random r = new Random(System.currentTimeMillis());
		int x;
		int y;
		int z;
		int sdlength = sdcoloured.length;
		int signedlength = signeddeck.length;
		
		for(int i = 5; i < 10; i++) {
			x = r.nextInt(10);
			y = r.nextInt(sdlength);
			z = r.nextInt(signedlength);
			if(x < 8) {
				computerDeck[i] = sdcoloured[y];
			}else {
				computerDeck[i] = signeddeck[z];
			}
		}
		for(int i = 5; i < 10; i++) {
			x = r.nextInt(10);
			y = r.nextInt(sdlength);
			z = r.nextInt(signedlength);
			if(x < 8) {
				playerDeck[i] = sdcoloured[y];
			}else {
				playerDeck[i] = signeddeck[z];
			}
		}
		
	}
}