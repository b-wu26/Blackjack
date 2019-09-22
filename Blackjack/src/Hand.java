import java.util.ArrayList;

/**
 * The Hand object is an ArrayList of PlayingCards.
 */

public class Hand
{
    private ArrayList<PlayingCard> hand;
    private int hasAce = 0;

    public Hand()
    {
        this.hand = new ArrayList<PlayingCard>();
    }

    public Hand(ArrayList<PlayingCard> hand)
    {
        this.hand = hand;
        for(int i = 0; i < hand.size(); i++)
        {
            if(hand.get(i).getRank() == 1)
            {
                hasAce++;
            }
        }
    }

    // adds a card to the hand, if it's an ace, increases the ace count
    public void addCard(PlayingCard card)
    {
        hand.add(card);
        if(card.getRank() == 1)
        {
            hasAce++;
        }
    }

    public ArrayList<PlayingCard> getHand()
    {
        return hand;
    }

    public PlayingCard getCard(int i)
    {
        return hand.get(i);
    }

    public int getSum()
    {
        int sum = 0;
        // adds up the values of all the cards, keeping in mind that rank 11-13 (J, Q, K)
        // all have a value of 10
        for(int i = 0; i < hand.size(); i++)
        {
            if(hand.get(i).getRank() == 11 || hand.get(i).getRank() == 12 || hand.get(i).getRank() == 13)
            {
                sum += 10;
            }
            else
            {
                sum += hand.get(i).getRank();
            }
        }

        // case for if an A is in the hand, as A can have a value of either 1 or 11.
        {
            for(int i = 0; i < hasAce; i++)
            {
                if(sum + 10 <= 21)
                {
                    sum += 10;
                }
            }
        }


        return sum;
    }

    public void clearHand()
    {
        ArrayList<PlayingCard> empty = new ArrayList<PlayingCard>();
        hand = empty;
        hasAce = 0;
    }

    public String toString()
    {
        String str = "";
        for(int i = 0; i < hand.size(); i++)
        {
            str += hand.get(i).toString() + " ";
        }
        str += "(" + getSum() + ")";
        return str;
    }
}