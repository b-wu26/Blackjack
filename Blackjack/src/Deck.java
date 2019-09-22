import java.util.*;

public class Deck
{
    private ArrayList<PlayingCard> deck;
    private int topCard = 0;

    // initialize the deck with all 52 cards:
    public Deck()
    {
        char[] SUITS = {'C', 'D', 'H', 'S'};
        int[] RANKS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

        ArrayList<PlayingCard> temp = new ArrayList<PlayingCard>();
        for(int i = 0; i < SUITS.length; i++)
        {
            for(int n = 0; n < RANKS.length; n++)
            {
                temp.add(new PlayingCard(RANKS[n], SUITS[i]));
            }
        }

        //System.out.println(temp[0]);

        deck = temp;
    }

    // returns a card of specific index
    public PlayingCard getCard()
    {
        PlayingCard temp = deck.get(topCard);
        topCard++;
        if(topCard == 52)
        {
            shuffle();
            topCard = 0;
            System.out.println("shuffled deck");
        }
        return temp;
    }

    // shuffles the deck of PlayingCards by swapping each card at each index with another card at a random index
    public void shuffle()
    {
        for (int i = 0; i < deck.size(); i++)
        {
            int rand = (int) (Math.random() * (i - 1));
            PlayingCard temp = deck.get(i);
            deck.set(i, deck.get(rand));
            deck.set(rand, temp);
        }
    }

    public String toString()
    {
        String str = "";
        for(int i = 0; i < deck.size(); i++)
        {
            str += deck.get(i).toString() + "\n";
        }
        return str;
    }
}