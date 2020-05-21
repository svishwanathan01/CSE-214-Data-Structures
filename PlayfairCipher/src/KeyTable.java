//Sabareesh Vishwanathan
//112585006
//R03

import java.util.ArrayList;

public class KeyTable
{
    private static ArrayList<Character> alphabet = new ArrayList<Character>();
    private char[][] key;

    /**
     * Initializes new character array
     */
    public KeyTable()
    {
        key = new char[5][5];
    }

    /**
     * Getter method for key character array
     * @return elements in key array
     */
    public char[][] getKey() {
        return key;
    }

    /**
     * Takes in parameter keyphrase
     * Keyphrase is made all uppercase, and has Js replaces with Is
     * @param keyphrase
     * @return keyTable, an object which contains 5x5 array of characters
     */
    public static KeyTable buildFromString(String keyphrase) {
        String str = keyphrase;
        str = str.toUpperCase();
        str = str.replaceAll("J", "I");
        KeyTable keyTable = new KeyTable();
        char[][] charKeyTable = new char[5][5];

        String alpha = "abcdefghiklmnopqrstuvwxyz";
        alpha = alpha.toUpperCase();
        for (char c : alpha.toCharArray()) {
            alphabet.add(c);
        }

        str = str + alpha;
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            if(!result.contains(String.valueOf(str.charAt(i)))) {
                result += String.valueOf(str.charAt(i));
            }
        }
        str = result;

        int temp = 0;
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                charKeyTable[i][j] = str.charAt(temp);
                temp++;
            }
        }
        keyTable.setKey(charKeyTable);
        return keyTable;
    }

    /**
     * Setter method for key character array
     * @param key
     */
    public void setKey(char[][] key) {
        this.key = key;
    }

    /**
     * Finds the row of a particular character
     * @param c
     * @return i, the row in which the character is found
     * @throws IllegalArgumentException, if no such character exists
     */
    public int findRow(char c) throws IllegalArgumentException
    {
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                if(key[i][j] == c)
                {
                    return i;
                }
            }

        }
        throw new IllegalArgumentException("No such character");
    }

    /**
     * Finds column of a particular character
     * @param c
     * @return j, the column in which the character is found
     * @throws IllegalArgumentException, if no such character exists
     */
    public int findCol(char c) throws IllegalArgumentException
    {
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                if(key[i][j] == c)
                {
                    return j;
                }
            }

        }
        throw new IllegalArgumentException("No such character");
    }

    /**
     * toString method
     * @return key character array as a string
     */
    public String toString()
    {
        String temp = "";
        for(int i = 0; i < key.length; i++)
        {
            for(int j = 0; j < key[i].length; j++)
            {
                temp = temp + key[i][j] + "\t";
            }
            temp = temp + "\n";
        }

        return temp;
    }

}
