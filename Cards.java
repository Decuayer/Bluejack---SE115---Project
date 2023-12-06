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
	public void showNumber(Cards[] deck) {
		return;
	}
}