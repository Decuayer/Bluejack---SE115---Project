SE 115 Project Description


Introduction:
Bluejack is a card game played by two people. It is similar to Blackjack, but with different
types of cards, which will be described shortly. Please read this description carefully, it is
written to answer all of your questions.
The implementation of your Bluejack game will be played by two players: the computer
versus the human player.


The Game:
Bluejack is played by three decks of cards. The game deck is made up of four sets of cards
that range between 1 and 10. The sets are colored blue, yellow, red, and green. The other two
decks are called player decks which will have 10 cards each. They are formed as follows:
  ● The game deck is shuffled.
  ● Repeat the following 5 times:
    ○ The card at the top is given to the computer,
    ○ The card at the bottom is given to the user.

After this first step, both player decks have 5 cards. The remaining five cards are randomly
generated as follows:
  ● Three cards with random colors and values between 1 to 6, with an additional sign,
that is either a plus (+) or a minus (-). These cards are either positive or negative,  
depending on the random sign.
  ● The remaining two cards have an 80 percent chance of being a signed card. However,
if the user is lucky (20 percent!), then:
    ○ One card can be a flip (+/-) card. Flip cards change the sign of the last played
card.
    ○ One card can be a double (x2) card. Double cards double the value of the last
played card.
    ○ Flip cards and double cards do not have a color, they only change the values or
the sign of the last played card.

The chance to get one flip and one double card is 0.2*0.2 = 0.04: that is 4 percent!

Now that the player decks are ready, only 4 of these cards are randomly picked as the hand of
the player.

The purpose of the game is to get a score of 20 using cards from the main and player decks.

The cards in the hands of the players can be used when necessary. The first player to win
three sets is the winner. However, if one of the players uses all blue cards to get a score of 20,
they automatically win the game. Hence, the name Bluejack.

Since the computer deals the hands, the player starts the game. The game is played as
follows:
  ● The current player asks for a card from the game deck to be placed on their board.
  ● The player can either end their turn, or depending on the sum of the cards in their
board can choose to stand, or play one of the cards in their hand. Playing a card also
ends the turn.
  ● If the player chooses to stand, it cannot draw any more cards from the deck, and waits
for the opponent to be done.
  ● The player who is closest to but not over 20 wins the set.
  ● If a player is over 20 once their turn is over, then the player automatically loses the
set. This is called a bust.

The board for each player can hold up to 9 cards.

A player wins the game in the following cases.
  ● Once both players stand, the player closest to but not over 20 wins the set.
  ● The player wins if the other player busts.
  ● If the player’s board is full (that is: 9 cards are placed on it) and their sum is less than
or equal to 20, then the player wins.

It is possible that two players stand at the same score. In such a case the game is tied, and no
player wins the set.
