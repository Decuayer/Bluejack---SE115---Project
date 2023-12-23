import java.util.Scanner;
public class main {
	public static void printDeck(Cards[] deck) {
		for(int i = 0; i < deck.length; i++) {
			System.out.print("[ " + deck[i].getNumber() + " ");
			System.out.print(deck[i].getColour() + " ]");
		}
		System.out.println();
	}
	//..........................PRINT POINT FUNCTION................................................
	public static void printPoints(int cpu, int player) {
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println("CPU points: " + cpu);
		System.out.println("Player points: " + player);
		System.out.println("---------------------------------------------------------------------------------------------------------");

	}
	//..........................PRINT GAME DESIGN FUNCTION................................................
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
	//..........................PRINT SPACE FUNCTION................................................
	public static void printSpace(int x) {
		for(int i = 0; i<x; i++) {
			System.out.println();
		}
	}
	//..........................PRINT GAME MENU FUNCTION................................................
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
		Cards[] computerSide = new Cards[4];
		Cards[] newDeck = new Cards[30];
		
		Games Games = new Games();
		Bot Bot = new Bot();
		
		
		int c = 0;
		int d = 0;
		
		
		//..........................CREATING CARD DECKS................................................
		//---MAIN DECK---
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
		//---SPECIAL DECK---
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
		//---SIGN CARD COLOUR STRUCTURE---
		for(int i = 0; i < signeddeck.length; i++) {
			signeddeck[i] = new Cards(signedcard[i], "Null");
		}
		//---COMPUTER TABLE STRUCTURE---
		for(int i = 0; i < computerTable.length; i++) {
			computerTable[i] = new Cards("0","0");
		}
		//---PLAYER TABLE STRUCTURE---
		for(int i = 0; i < playerTable.length; i++) {
			playerTable[i] = new Cards("0","0");
		}
		//---HIDE COMPUTER HAND---
		for(int i = 0; i < computerSide.length; i++) {
			computerSide[i] = new Cards("X", "X");
		}	
		//..........................GAME STRUCTURE................................................

		
		int totalPointsCPU = 0;
		int totalPointsPlayer = 0;
		int totalRounds = 0;
		int cardPosition = 0;
		String mainMenu = "";
		String optionsMenu = "";
		String playerName = "";
		String whoWon = "";
		boolean totalGame = false;
		boolean nameMenu = true;
		boolean savingMenu = true;
		Cards.shuffle(deck);
		Cards.deal(deck,computerDeck,playerDeck,newDeck);
		Cards.dealSigned(sdcoloured,signeddeck,computerDeck,playerDeck);
		Cards.dealHand(computerDeck,computerHand,playerDeck,playerHand);
		
		printSpace(30);
		//..........................MAIN MENU STRUCTURE................................................
		while(true) {
			menuDesign();
			System.out.print("Choose: ");
			mainMenu = sc.nextLine();
			if(mainMenu.equals("1")) {
				totalGame = true;
				printSpace(30);
				break;
			}else if(mainMenu.equals("2")) {
				printSpace(30);
				System.out.println("---------------------------------------------------------------------------------------------------------");
				Games.readFile();
				while(true) {
					System.out.print("Press 1 to return: ");
					optionsMenu = sc.nextLine();
					if(optionsMenu.equals("1")) {
						printSpace(30);
						break;
					}
				}
				continue;
			}else if(mainMenu.equals("3")) {
				nameMenu = false;
				savingMenu = false;
				System.out.println("You are leaving the game...");
				break;
			}else {
				printSpace(30);
				System.out.println("Your input is wrong please try again.");
			}
		}
		//..........................ENTER NAME................................................
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

			String gameMenu = "";
			String chooseSpecial = "";
			boolean gameContinue = true;
			boolean roundContinue = true;
			boolean playerTurn = true;
			boolean playerControl = true;
			boolean playerStand = false;
			boolean playerSpecialCard = false;
			boolean computerTurn = true;
			boolean computerStand = false;
			boolean computerBlue = false;
			
			
			
			System.out.println("The game begins...");
			while(gameContinue) {
				int gameRoundPlayer = 0;
				int gameRoundCPU = 0;
				//..........................HAND SIDES RESET................................................
				for(int i = 0; i < computerTable.length; i++) {
					computerTable[i] = new Cards("0","0");
				}
				for(int i = 0; i < playerTable.length; i++) {
					playerTable[i] = new Cards("0","0");
				}
				playerStand = false;
				computerStand = false;
				//..........................ROUND START................................................
				while(roundContinue) {
					if(cardPosition == 30) {
						cardPosition = 0;
						Cards.shuffle(newDeck);
					}
					playerTurn = true;
					playerControl = true;
					computerTurn = true;
					//..........................PLAYER TURN START................................................
					while(playerTurn) {
						if(playerStand) {
							playerTurn = false;
							break;
						}
						playerSpecialCard = false;
						printPoints(Cards.getTablePoint(computerTable),Cards.getTablePoint(playerTable));
						printGame(computerSide,computerTable,playerTable,playerHand);
						System.out.println("-1--> Draw card.");
						System.out.println("-2--> Stand.");
						System.out.print("---Choose: ");
						gameMenu = sc.nextLine();
						printSpace(30);
						if(gameMenu.equals("1")) {
							if(Integer.parseInt(playerTable[8].getNumber()) != 0) {
								System.out.println("You cannot draw more cards.");
								playerTurn = false;
								playerStand = true;
								break;
							}
							Cards.addCardTable(newDeck,playerTable,gameRoundPlayer,cardPosition);
							cardPosition++;
							gameRoundPlayer++;
							System.out.println("Player draw card.");
							
							String playerControlChoose = "";
							while(playerControl) {
								if(playerSpecialCard) {
									playerControl = false;
									playerTurn = false;
									break;
								}
								printPoints(Cards.getTablePoint(computerTable),Cards.getTablePoint(playerTable));
								printGame(computerSide,computerTable,playerTable,playerHand);
								System.out.println("-1--> End turn.");
								System.out.println("-2--> Choose special card.");
								System.out.print("---Choose: ");
								playerControlChoose = sc.nextLine();
								printSpace(30);
								if(playerControlChoose.equals("1")) {
									playerControl = false;
									playerTurn = false;
									break;
								}else if(playerControlChoose.equals("2")) {
									if(Integer.parseInt(playerTable[8].getNumber()) != 0) {
										System.out.println("You cannot use more special cards.");
										playerTurn = false;
										playerControl = false;
										playerStand = true;
										break;
									}
									while(true) {
										printPoints(Cards.getTablePoint(computerTable),Cards.getTablePoint(playerTable));
										printGame(computerSide,computerTable,playerTable,playerHand);
										System.out.println("What rank card do you want to play? (1,2,3,4)");
										System.out.print("Enter the number: ");
										chooseSpecial = sc.nextLine();
										printSpace(30);
										if(!chooseSpecial.equals("1") && !chooseSpecial.equals("2") && !chooseSpecial.equals("3") && !chooseSpecial.equals("4")) {
											System.out.println("You enter wrong input. Try again.");
											continue;
										}else {
											int specialCardGet;
											if(playerHand[Integer.parseInt(chooseSpecial)-1].getNumber() == "2x" || playerHand[Integer.parseInt(chooseSpecial)-1].getNumber() == "+/-") {
												specialCardGet = 1;
											}else {
												specialCardGet = Integer.parseInt(playerHand[Integer.parseInt(chooseSpecial)-1].getNumber());
											}
											if(specialCardGet == 0) {
												System.out.println("The row you selected is empty. Try again.");
												continue;
											}else {
												playerTurn = false;
												playerControl = false;
												break;
											}
										}
									}
									Cards.addCardTable(playerHand,playerTable,gameRoundPlayer,Integer.parseInt(chooseSpecial)-1);
									gameRoundPlayer+=1;
									Cards.updateHand(playerHand,Integer.parseInt(chooseSpecial)-1);
									playerTurn = false;
									playerControl = false;
									playerSpecialCard = true;
								} else {
									System.out.println("You enter wrong input. Try again.");
									continue;
								}		
							}
						}else if(gameMenu.equals("2")) {
							System.out.println("Player stand.");
							playerStand = true;
							playerTurn = false;
							break;
						}else {
							System.out.println("You enter wrong input. Try again.");
							continue;
						}
					}
					if(cardPosition == 30) {
						cardPosition = 0;
						Cards.shuffle(newDeck);
					}
					//..........................COMPUTER TURN START................................................
					while(computerTurn) {
						if(computerStand) {
							computerTurn = false;
							break;
						}
						printPoints(Cards.getTablePoint(computerTable),Cards.getTablePoint(playerTable));
						printGame(computerSide,computerTable,playerTable,playerHand);
						printSpace(30);
						int cpuReturn = Bot.botReturn(Cards.getTablePoint(playerTable), Cards.getTablePoint(computerTable), computerHand, computerTable);
						// 0 -> Stand
						// 1,2,3,4 -> Special Cards
						// 5 -> All Blue
						// 6 -> Draw Card
						if(cpuReturn == 5) {
							System.out.println("CPU's all cards blue.");
							totalPointsCPU = 3;
							computerTurn = false;
							computerStand = true;
							computerBlue = true;
							break;
						}else if(cpuReturn == 1 || cpuReturn == 2 || cpuReturn == 3 || cpuReturn == 4) {
							if(Integer.parseInt(computerTable[8].getNumber()) != 0) {
								computerStand = true;
								break;
							}
							System.out.println("CPU used special card.");
							Cards.addCardTable(computerHand,computerTable,gameRoundCPU,cpuReturn-1);
							Cards.updateHand(computerHand,cpuReturn-1);
							Cards.updateHand(computerSide,cpuReturn-1);
							gameRoundCPU++;
							computerTurn = false;
						}else if(cpuReturn == 0) {
							System.out.println("CPU stand.");
							computerStand = true;
							computerTurn = false;
						}else if(cpuReturn == 6) {
							if(Integer.parseInt(computerTable[8].getNumber()) != 0) {
								computerStand = true;
								break;
							}
							System.out.println("CPU draws card.");
							Cards.addCardTable(newDeck,computerTable,gameRoundCPU,cardPosition);
							cardPosition++;
							gameRoundCPU += 1;
							computerTurn = false;
						}
					}
					if(computerStand && playerStand) {
						roundContinue = false;
						break;
					}
				}
				printPoints(Cards.getTablePoint(computerTable),Cards.getTablePoint(playerTable));
				printGame(computerSide,computerTable,playerTable,playerHand);
				int pointCPU = Cards.getTablePoint(computerTable);
				int pointPlayer = Cards.getTablePoint(playerTable);
				totalRounds++;
				//..........................ROUND POINT START................................................
				if(computerBlue) {
					roundContinue = false;
					gameContinue = false;
					break;
				}
				if(pointPlayer > 20 && pointCPU > 20) {				
					System.out.println("CPU and Player bust. Draw!");
					roundContinue = true;
				}else if(pointPlayer > 20 && pointCPU < 20) {
					System.out.println("Player bust. CPU won!");
					totalPointsCPU++;
					roundContinue = true;
				}else if(pointPlayer < 20 && pointCPU > 20) {
					System.out.println("CPU bust. Player won!");
					totalPointsPlayer++;
					roundContinue = true;
				}else if(pointPlayer < 20 && pointCPU < 20) {
					if(pointPlayer > pointCPU) {
						System.out.println("Player won!");
						totalPointsPlayer++;
						roundContinue = true;
					}else if(pointPlayer < pointCPU) {
						System.out.println("CPU won!");
						totalPointsCPU++;
						roundContinue = true;
					}else {
						System.out.println("Draw!");
						roundContinue = true;
					}
				}else if(pointPlayer == 20 && pointCPU == 20) {
					System.out.println("Draw!");
					roundContinue = true;
				}else if(pointPlayer == 20) {
					if(Cards.getTableColour(playerTable)){
						System.out.println("All cards are blue. Player win total game.");
						totalPointsPlayer = 3;
						gameContinue = false;
						roundContinue = false;
					} else {
						if(pointCPU > 20) {
							System.out.println("CPU bust. Player won!");
						} else {
							System.out.println("Player won!");
						}
						totalPointsPlayer++;
						roundContinue = true;
					}
				}else if(pointCPU == 20) {
					if(Cards.getTableColour(computerTable)) {
						System.out.println("All cards are blue. Computer win total game.");
						totalPointsCPU = 3;
						roundContinue = false;
						gameContinue = false;
					}else {
						if(pointPlayer > 20) {
							System.out.println("Player bust. CPU won!");

						} else {
							System.out.println("CPU won!");
						}
						totalPointsCPU++;
						roundContinue = true;
					}
				}
				System.out.println("---------------------------------------------------------------------------------------------------------");
				System.out.println("CPU total points: " + totalPointsCPU);
				System.out.println("Player total points: " + totalPointsPlayer);
				System.out.println("Total round played: " + totalRounds);
				System.out.println("---------------------------------------------------------------------------------------------------------");
				String con = "";
				//..........................TOTAL GAME POINT START................................................
				if(totalPointsPlayer == 3 && totalPointsCPU == 3) {
					System.out.println("DRAW");
					whoWon = "Draw";
					gameContinue = false;
					break;
				}else if(totalPointsPlayer == 3) {
					System.out.println("GAME WINNER PLAYER");
					whoWon = "Player";
					gameContinue = false;
					break;
				}else if(totalPointsCPU == 3) {
					System.out.println("GAME WINNER CPU");
					whoWon = "CPU";
					gameContinue = false;
					break;
				}else {
					while(true) {
						System.out.print("Press 1 to continue: ");
						con = sc.nextLine();
						if(con.equals("1")) {
							printSpace(30);
							break;
						}	
					}
				}
			}
			totalGame = false;
		}
		String svg = "";
		//..........................GAME SAVE PART................................................
		while(savingMenu) {
			System.out.println("Save game?");
			System.out.println("1-Yes");
			System.out.println("2-No");
			System.out.print("Choose: ");
			svg = sc.nextLine();
			if(svg.equals("1")) {
				System.out.print("Game saving.");
				Games.writeFile(playerName,whoWon,totalPointsPlayer,totalPointsCPU,totalRounds);
				Games.fileDeleteRename();
				break;
			} else if (svg.equals("2")) {
				System.out.println("Game not saved.");
				break;
			} else {
				System.out.println("You enter wrong input. Try again.");
			}
		}
		
	}
}