import java.util.Random;
public class Cards{
	private String colour;
	private String number;
	
	public Cards(){};
	public Cards(String x, String y) {
		colour = y;
		number = x;
	}
	
	public void setNumber(String x) {
		number = x;
	}
	public void setColour(String x) {
		colour = x;
	}
	public String getNumber() {
		return number;
	}
	public String getColour() {
		return colour;
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
	public void deal(Cards[] deck, Cards[] computerDeck, Cards[] playerDeck, Cards[] newDeck) {
		int decklength = deck.length-1;
		int counter = 0;
		for(int i = 0; i < 5; i++) {
			computerDeck[i] = deck[counter];
			playerDeck[i] = deck[decklength];
			counter++;
			decklength--;
		}
		counter = 0;
		for(int i = 5; i<35; i++) {
			newDeck[counter] = deck[i];
			counter++;
		}
	}
	public void dealSigned(Cards[] sdcoloured, Cards[] signeddeck, Cards[] computerDeck, Cards[] playerDeck) {
		Random r = new Random(System.currentTimeMillis());
		int x;
		int y;
		int z;
		int sdlength = sdcoloured.length;
		int signedlength = signeddeck.length;
		
		for(int i = 5; i < 8; i++) {
			y = r.nextInt(sdlength);
			z = r.nextInt(signedlength);
			computerDeck[i] = sdcoloured[y];
		}
		for(int i = 8; i < 10; i++) {
			x = r.nextInt(10);
			y = r.nextInt(sdlength);
			z = r.nextInt(signedlength);
			if(x < 8) {
				computerDeck[i] = sdcoloured[y];
			}else {
				computerDeck[i] = signeddeck[z];
			}
		}	
		for(int i = 5; i < 8; i++) {
			y = r.nextInt(sdlength);
			z = r.nextInt(signedlength);
			playerDeck[i] = sdcoloured[y];
			
		}
		for(int i = 8; i < 10; i++) {
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
	public void dealHand(Cards[] computerDeck, Cards[] computerHand, Cards[] playerDeck, Cards[] playerHand) {
		Random r = new Random(System.currentTimeMillis());
		int x = -1;
		int y = -1;
		int[] arr = {0,0,0,0};
		boolean loopC = true;
		int c = 0;
		while(true) {
			loopC = true;
			if(c == 4) {
				break;
			}
			x = r.nextInt(10);
			for(int i = 0; i<4; i++) {
				if(arr[i] == x) {
					loopC = false;
				}
			}
			if(loopC = false) {
				continue;
			}
			arr[c] = x;
			computerHand[c] = computerDeck[x];
			c++;
		}
		int[] arr2 = {0,0,0,0};
		c = 0;
		while(true) {
			loopC = true;
			if(c == 4) {
				break;
			}
			y = r.nextInt(10);
			for(int i = 0; i<4; i++) {
				if(arr2[i] == y) {
					loopC = false;
				}
			}
			if(loopC = false) {
				continue;
			}
			arr2[c] = y;
			playerHand[c] = playerDeck[y];
			c++;
		}
	}
	public void addCardTable(Cards[] newDeck, Cards[] Table,int position, int cardPosition) {
		Table[position] = newDeck[cardPosition];
	}
	public void updateHand(Cards[] Hand, int position) {
		Hand[position] = new Cards("0","0");
	}
	public int getTablePoint(Cards[] Table) {
		int sum = 0;
		for(int i = 0; i < Table.length; i++) {
			if(Table[i].getNumber() == "+/-") {
			 sum += -2*(Integer.parseInt(Table[i-1].getNumber()));
			}else if(Table[i].getNumber() == "2x") {
				sum += (Integer.parseInt(Table[i-1].getNumber()));
			}else {
				sum+=Integer.parseInt(Table[i].getNumber());
			}
		}
		return sum;
	}
}