//Sabareesh Vishwanathan
//112585006
//R03

public class Bigram {

    private char first, second;

    /**
     * Empty default constructor
     */
    public Bigram()
    {

    }

    /**
     * Bigram constructor initializes first and second characters of Bigram
     * @param firstChar
     * @param secondChar
     */
    public Bigram(char firstChar, char secondChar)
    {
        first = firstChar;
        second = secondChar;
    }

    /**
     * Getter method for first character in Bigram
     * @return first
     */
    public char getFirst() {
        return first;
    }

    /**
     * Getter method for second character in Bigram
     * @return second
     */
    public char getSecond() {
        return second;
    }

    /**
     * Setter method for first character in Bigram
     * @param first
     */
    public void setFirst(char first) {
        this.first = first;
    }

    /**
     * Setter method for second character in Bigram
     * @param second
     */
    public void setSecond(char second) {
        this.second = second;
    }

    /**
     * toString method
     * @return first, second, characters as string
     */
    public String toString()
    {
        return "" + first + second;
    }
}
