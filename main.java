import java.util.Scanner;
public class main {
	public static void printDeck(Cards[] deck) {
		for(int i = 0; i < deck.length; i++) {
			System.out.print("[ " + deck[i].getNumber() + " ");
			System.out.print(deck[i].getColour() + " ]");
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
	public static void printSpace(int x) {
		for(int i = 0; i<x; i++) {
			System.out.println();
		}
	}  
	public static void menuDesign() {
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println("------------------------------------------------BLUEJACK-------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println();
		System.out.println("					-1----- START GAME -");
		System.out.println("					-2-----   OPTIONS  -");
		System.out.println("					-3-----    EXIT    -");
		System.out.println();
		System.out.println();
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
		Cards[] newDeck = new Cards[30];
		
		Games Games = new Games();
		Bot Bot = new Bot();
		
		
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
		for(int i = 0; i < computerTable.length; i++) {
			computerTable[i] = new Cards("0","0");
		}
		for(int i = 0; i < playerTable.length; i++) {
			playerTable[i] = new Cards("0","0");
		}
		//..........................GAME STRACTURE ................................................

		
		int totalPointsCPU = 0;
		int totalPointsPlayer = 0;
		int totalRounds = 0;
		int cardPosition = 0;
		int mainMenu = -1;
		int optionsMenu = -1;
		String playerName = "";
		String whoWon = "";
		boolean totalGame = false;
		boolean nameMenu = true;
		boolean savingMenu = true;
		Cards.shuffle(deck);
		Cards.deal(deck,computerDeck,playerDeck,newDeck);
		Cards.dealSigned(sdcoloured,signeddeck,computerDeck,playerDeck);
		Cards.dealHand(computerDeck,computerHand,playerDeck,playerHand);
		
		while(true) {
			printSpace(30);
			menuDesign();
			System.out.print("Choose: ");
			mainMenu = sc.nextInt();
			if(mainMenu == 1) {
				totalGame = true;
				printSpace(30);
				break;
			}else if(mainMenu == 2) {
				System.out.println("---------------------------------------------------------------------------------------------------------");
				Games.readFile();
				while(true) {
					System.out.print("Press 1 to return: ");
					optionsMenu = sc.nextInt();
					if(optionsMenu == 1) {
						break;
					}
				}
				printSpace(30);
				continue;
			}else if(mainMenu == 3) {
				nameMenu = false;
				savingMenu = false;
				System.out.println("You are leaving the game...");
				break;
			}else {
				System.out.println("Your output is wrong please try again.");
			}
		}
		while(nameMenu) {
			System.out.print("Enter your name: ");
			playerName = sc.nextLine();
			if (playerName != "") {
				printSpace(30);
				break;
			}
		}
		
		while(totalGame) {
			
			Cards.shuffle(newDeck);
			for(int i = 0; i < computerTable.length; i++) {
				computerTable[i] = new Cards("0","0");
			}
			for(int i = 0; i < playerTable.length; i++) {
				playerTable[i] = new Cards("0","0");
			}
			
			int gameRoundPlayer = 0;
			int gameRoundCPU = 0;
			int gameMenu = -1;
			int chooseSpecial = -1;
			boolean gameContinue = true;
			
			
			printGame(computerHand,computerTable,playerTable,playerHand);
			System.out.println("The game begins...");
			while(gameContinue) {
				if(cardPosition == 30) {
					cardPosition = 0;
					Cards.shuffle(newDeck);
				}
				int currentPointCPU = Cards.getTablePoint(computerTable);
				int currentPointPlayer = Cards.getTablePoint(playerTable);
				System.out.println("-1--> Continue the game.");
				System.out.println("-2--> Stop game.");
				System.out.println("-3--> Stop game and use special card.");
				System.out.print("---Select one: ");
				gameMenu = sc.nextInt();
				if(gameMenu == 1) {
					if(Integer.parseInt(playerTable[8].getNumber()) != 0) {
						System.out.println("You cannot draw any more cards.");
						break;
					}
					Cards.addCardTable(newDeck,playerTable,gameRoundPlayer,cardPosition);
					cardPosition++;
					Cards.addCardTable(newDeck,computerTable,gameRoundCPU,cardPosition);
					cardPosition++;
					System.out.println("CPU pulling card...");
					
				}else if(gameMenu == 2) {
					System.out.println("CPU pulling last card...");
					gameContinue = false;
				}else if(gameMenu == 3) {
					if(Integer.parseInt(playerTable[8].getNumber()) != 0) {
						System.out.println("You cannot add a special card.");
						break;
					}
					while(true) {
						System.out.println("What rank card do you want to play? (1,2,3,4)");
						System.out.print("Enter the number: ");
						chooseSpecial = sc.nextInt();
						if(chooseSpecial != 1 && chooseSpecial != 2 && chooseSpecial != 3 && chooseSpecial != 4) {
							continue;
						}else {
							break;
						}
					}
					Cards.addCardTable(playerHand,playerTable,gameRoundPlayer,chooseSpecial-1);
					gameRoundPlayer+=1;
					Cards.updateHand(playerHand,chooseSpecial-1);
					gameContinue = false;
				}else {
					System.out.println("You enter wrong number. Try again.");
					continue;
				}
				printSpace(30);
				printGame(computerHand,computerTable,playerTable,playerHand);
				gameRoundPlayer += 1;
				gameRoundCPU += 1;
			}
			
			int pointCPU = Cards.getTablePoint(computerTable);
			int pointPlayer = Cards.getTablePoint(playerTable);
			totalRounds++;
			System.out.println("---------------------------------------------------------------------------------------------------------");
			System.out.println("CPU points: " + Cards.getTablePoint(computerTable));
			System.out.println("Player points: " + Cards.getTablePoint(playerTable));
			if(pointPlayer > 20 && pointCPU > 20) {				
				System.out.println("Draw");
			}else if(pointPlayer > 20 && pointCPU < 20) {
				System.out.println("CPU won");
				totalPointsCPU++;
			}else if(pointPlayer < 20 && pointCPU > 20) {
				System.out.println("Player won");
				totalPointsPlayer++;
			}else if(pointPlayer < 20 && pointCPU < 20) {
				if(pointPlayer > pointCPU) {
					System.out.println("Player won");
					totalPointsPlayer++;
				}else {
					System.out.println("CPU won");
					totalPointsCPU++;
				}
			}else if(pointPlayer == 20) {
				System.out.println("Player won");
				totalPointsPlayer++;
			}else if(pointCPU == 20) {
				System.out.println("CPU won");
				totalPointsCPU++;
			}
			System.out.println("---------------------------------------------------------------------------------------------------------");
			System.out.println("CPU total points: " + totalPointsCPU);
			System.out.println("Player total points: " + totalPointsPlayer);
			System.out.println("Total round played: " + totalRounds);
			System.out.println("---------------------------------------------------------------------------------------------------------");
			int con = -1;
			if(totalPointsPlayer == 3) {
				System.out.println("GAME WINNER PLAYER");
				whoWon = "Player";
				break;
			}else if(totalPointsCPU == 3) {
				System.out.println("GAME WINNER CPU");
				whoWon = "CPU";
				break;
			}else {
				while(true) {
					System.out.print("Press 1 to continue: ");
					con = sc.nextInt();
					if(con == 1) {
						break;
					}	
				}
				
			}
			printSpace(30);
		}
		int svg = -1;
		while(savingMenu) {
			System.out.println("Save game?");
			System.out.println("1-Yes");
			System.out.println("2-No");
			System.out.print("Choose: ");
			svg = sc.nextInt();
			if(svg == 1) {
				System.out.print("Game saving.");
				Games.writeFile(playerName,whoWon,totalPointsPlayer,totalPointsCPU,totalRounds);
				Games.fileDeleteRename();
				break;
			} else if (svg == 2) {
				System.out.println("Game not saved.");
				break;
			} else {
				System.out.println("You enter wrong output. Try again.");
			}
		}
		
	}
}