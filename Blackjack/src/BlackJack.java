import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack
{
    private static String spacer = "~~~~~~~~~~~~~~~~~~~";

    public static void main(String[] args)
    {
        play();
    }

    public static void play()
    {
        System.out.println("* Welcome to BlackJack! *");

        // initialize the deck, the player hand and the dealer hand and the balance
        // create a double bet to temporarily hold the player's bet
        Deck deck = new Deck();
        deck.shuffle();

        Hand player = new Hand();
        Hand dealer = new Hand();
        double money = 500.0;
        double bet;
        boolean hit;
        boolean playerWon;

        // game runs in an infinite loop as long as their balance is over 0
        while(money > 0)
        {
            bet = bet(money);

            // initialize both the hands
            player.addCard(deck.getCard());
            player.addCard(deck.getCard());
            dealer.addCard(deck.getCard());
            dealer.addCard(deck.getCard());

            // as long as the player hasn't busted yet, allow the player to choose to hit or stand
            while(player.getSum() <= 21)
            {
                printHands(dealer, player);
                hit = hitOrStand();
                if (hit)
                {
                    // add a card to the player's hand if they hit
                    hit(player, deck);
                    // tell the player that they busted
                    if(player.getSum() > 21)
                    {
                        System.out.println(player.toString());
                        System.out.println("Bust!");
                    }
                }
                else
                {
                    // break from the hit loop and continue with the game
                    break;
                }

            }

            // dealer turn
            System.out.println(spacer);
            System.out.println("Dealer's turn:");
            System.out.println(spacer);
            dealersTurn(dealer, deck);

            // print the final hands of the round
            finalHands(dealer, player);

            // player has to hit enter before console continues so it doesn't show up all at once
            System.out.println("Enter to continue...");
            Scanner ans = new Scanner(System.in);
            ans.nextLine();

            // display whether the player won or lost
            playerWon = determineWinner(dealer, player);

            // return the bet if player won
            if(playerWon)
            {
                money += bet;
                System.out.println("You won!");
            }
            // remove the bet from the player's balance if they lost
            else
            {
                money -= bet;
                System.out.println("You lost!");
            }

            // clear both the hands
            player.clearHand();
            dealer.clearHand();

            System.out.println(spacer);

        }

        // once the player's balance reaches 0 the game is over and asks if they'd like to play again
        System.out.println("You've gone bankrupt!");
        playAgain();
    }

    public static void dealersTurn(Hand dealer, Deck deck)
    {
        System.out.println(dealer.toString());
        while(dealer.getSum() < 17)
        {
            System.out.print("Enter to continue...");
            Scanner ans = new Scanner(System.in);
            ans.nextLine();
            hit(dealer, deck);
            System.out.println(dealer.toString());
        }
        System.out.println("Dealer's turn over.");
        System.out.println(spacer);
    }

    public static boolean determineWinner(Hand dealer, Hand player)
    {
        if(player.getSum() <= 21 && player.getSum() > dealer.getSum() || player.getSum() <= 21 && dealer.getSum() > 21)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * prints both players hands with full visibility and their sums
     *
     * @param dealer the dealer's hand
     * @param player the player's hand
     */
    public static void finalHands(Hand dealer, Hand player)
    {
        System.out.println("Final Hands:");
        System.out.println("Dealer's hand: " + dealer.toString());
        System.out.println("Your hand: " + player.toString());
    }

    /**
     * prints both of the player's hands, hiding one of the dealer's cards.
     *
     * @param dealer the dealer's hand
     * @param player the player's hand
     */
    public static void printHands(Hand dealer, Hand player)
    {
        System.out.println(spacer);
        System.out.println("Dealer's hand: " + dealer.getCard(0) + " XX");
        System.out.println("Your hand: " + player.toString());
    }

    /**
     * takes user input whether they want to hit or stand and reprompts if they give an invalid answer.
     *
     * @return a boolean that returns true if they chose hit, false if they chose stand
     */
    public static boolean hitOrStand()
    {
        System.out.print("Hit or Stand? (H/S): ");
        Scanner ans = new Scanner(System.in);
        String hOrS = ans.nextLine();
        if(hOrS.toLowerCase().equals("h") || hOrS.toLowerCase().equals("hit"))
        {
            return true;
        }
        else if(hOrS.toLowerCase().equals("s") || hOrS.toLowerCase().equals("stand"))
        {
            return false;
        }
        else
        {
            System.out.println("Invalid answer!");
            hitOrStand();
            return false;
        }

    }

    /**
     * adds a card from a deck to a hand
     *
     * @param hand the hand to add a card to
     * @param deck the deck to take a card from
     */
    public static void hit(Hand hand, Deck deck)
    {
        //System.out.println("Hit!");
        hand.addCard(deck.getCard());
    }

    /**
     * this method accepts a bet from the user, as long as it is less than their balance
     * @param balance the user's balance prior to betting
     * @return the user's (valid) bet
     */
    public static double bet(double balance)
    {

        System.out.println("Balance: $" + balance);
        System.out.print("Place a bet: ");
        Scanner ans = new Scanner(System.in);

        // checks to see if input contains a double
        if(ans.hasNextDouble())
        {
            double bet = ans.nextDouble();

            // a valid bet is returned
            if(bet <= balance)
            {
                return bet;
            }
            // bet larger than balance reruns the bet method
            else if(bet > balance)
            {
                System.out.println("Insufficient funds.");
                bet(balance);
            }
        }
        // if input does not contain double, rerun bet method
        else
        {
            System.out.println("Invalid bet!");
            bet(balance);
        }

        // balance is returned for edge case of user betting same amount as balance
        // because for some reason it doesn't work if I don't do this
        // (I still need to figure out why)
        return balance;
    }

    /**
     * asks the user if they want to play again.
     * if they do, starts the play() method over again
     * if not, prints message "Thanks for playing!"
     */
    public static void playAgain()
    {
        Scanner s = new Scanner(System.in);
        System.out.print("Play again? (Y/N): ");
        String ans = s.nextLine();
        if(ans.toLowerCase().equals("n"))
        {
            System.out.println();
            System.out.println(spacer);
            System.out.println("Thanks for playing!");
            System.out.println(spacer);
        }
        else if(ans.toLowerCase().equals("y"))
        {
            System.out.println();
            System.out.println();
            play();
        }
        else
        {
            System.out.println("Invalid answer.");
            playAgain();
        }
    }
}