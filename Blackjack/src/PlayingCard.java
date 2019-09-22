public class PlayingCard
{
    private int rank;
    private char suit;

    public PlayingCard(int rank, char suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank()
    {
        return rank;
    }

    public char getSuit()
    {
        return suit;
    }


    public int getValue()
    {
        if(getRank() >= 11)
        {
            return 10;
        }
        else if(getRank() == 1)
        {
            return 11;
        }
        else
        {
            return getRank();
        }
    }

    // returns object in String format: VS (value, suit)
    // ex. 2 of Spades: 2S
    // exception for Ace, Jack, Queen, and King:
    // will display A, J, Q, K for val rather than the int
    public String toString()
    {
        if(rank == 1)
        {
            return "A" + getSuit();
        }
        if(rank == 11)
        {
            return "J" + getSuit();
        }
        if(rank == 12)
        {
            return "Q" + getSuit();
        }
        if(rank == 13)
        {
            return "K" + getSuit();
        }
        else
        {
            return "" + getRank() + getSuit();
        }
    }
}







