//Sabareesh Vishwanathan
//112585006
//R03

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.NoSuchElementException;

public class Phrase extends  LinkedList<Bigram>{

    int size;

    /**
     * Default constructor, initializes size
     */
    public Phrase()
    {
        size = 0;
    }

    /**
     * Enqueue method adds bigram to the queue
     * Increments size
     * @param b
     */
    public void enqueue(Bigram b)
    {
        add(b);
        size++;
    }

    /**
     * Dequeue method removes bigram from queue
     * Decrements size
     * @return removed, removed bigram
     */
    public Bigram dequeue()
    {
        Bigram removed = remove();
        size--;
        return removed;
    }

    /**
     * Gets the first bigram in queue
     * @return getFirst(), first bigram in queue
     * @throws IllegalArgumentException, if element isn't found
     */
    public Bigram peek()
    {
        if(isEmpty() == false)
        {
            return getFirst();
        }
        throw new IllegalArgumentException("No such element");
    }

    /**
     * Gets the size of queue
     * @return size
     */
    public int size()
    {
        return size;
    }

    /**
     * Checks if queue is empty
     * @return false if not empty, true if empty
     */
    public boolean isEmpty()
    {
        if(size > 0)
        {
            return false;
        }
        return true;
    }

    /**
     * Gets a string and turns it into bigrams
     * Bigrams are enqueued into a queue which is returned
     * @param s
     * @return queuePhrase, queue of bigrams of String s
     */
    public static Phrase buildPhraseFromStringForEnc(String s)
    {
        ArrayList<String> queueArray = new ArrayList<String>();
        Phrase queuePhrase = new Phrase();
        String temp = "";

        s = s.toUpperCase();
        s = s.replaceAll("\\s+","");
        s = s.replaceAll("J","I");

        for(int i = 0; i < s.length(); i++)
        {
            queueArray.add(s.substring(i, i+1));
        }
        int x = 0;
        while(x+1 < queueArray.size())
        {
            if(queueArray.get(x).equals(queueArray.get(x+1)))
            {
                queueArray.add(x+1, "X");
                x+=2;
            }
            else
            {
                x+=2;
            }
        }

        if(queueArray.size() % 2 == 1)
        {
            queueArray.add("X");
        }

        for(int i = 0; i < queueArray.size(); i++)
        {
            temp = temp + queueArray.get(i);
        }

        for(int i = 0; i < temp.length(); i+=2)
        {
            Bigram b = new Bigram(temp.charAt(i),temp.charAt(i+1));
            queuePhrase.enqueue(b);
        }
        return queuePhrase;
    }

    /**
     * Method encrypts keyTable object and returns it as a phrase
     * @param key
     * @return phrase
     */
    public Phrase encrypt(KeyTable key)
    {
        Bigram bigram;
        char first, second;
        int bigramRow1, bigramRow2, bigramCol1, bigramCol2;
        Phrase phrase = new Phrase();
        char[][] phraseTable = key.getKey();

        if(key == null)
        {
            throw new IllegalArgumentException("Key is null");
        }

        while(isEmpty() == false)
        {
            bigram = dequeue();
            first = bigram.getFirst();
            second = bigram.getSecond();
            bigramRow1 = key.findRow(first);
            bigramRow2 = key.findRow(second);
            bigramCol1 = key.findCol(first);
            bigramCol2 = key.findCol(second);

            if(bigramRow1 == bigramRow2)
            {
                bigram.setFirst(phraseTable[bigramRow1][(bigramCol1 + 1) % 5]);
                bigram.setSecond(phraseTable[bigramRow2][(bigramCol2 + 1) % 5]);

            }
            else if(bigramCol1 == bigramCol2)
            {
               bigram.setFirst((phraseTable[(bigramRow1 + 1) % 5][bigramCol1]));
               bigram.setSecond((phraseTable[(bigramRow2 + 1) % 5][bigramCol2]));
            }
            else
            {
                bigram.setFirst(phraseTable[bigramRow1][bigramCol2]);
                bigram.setSecond(phraseTable[bigramRow2][bigramCol1]);
            }
            phrase.enqueue(bigram);
        }

        return phrase;
    }

    /**
     * Method decrypts encrypted phrase and returns it as a phrase
     * @param key
     * @return phrase
     */
    public Phrase decrypt(KeyTable key)
    {
        Bigram bigram;
        char first, second;
        int bigramRow1, bigramRow2, bigramCol1, bigramCol2;
        Phrase phrase = new Phrase();
        char[][] phraseTable = key.getKey();

        if(key == null)
        {
            throw new IllegalArgumentException("Key is null");
        }

        while(isEmpty() == false)
        {
            bigram = dequeue();
            first = bigram.getFirst();
            second = bigram.getSecond();
            bigramRow1 = key.findRow(first);
            bigramRow2 = key.findRow(second);
            bigramCol1 = key.findCol(first);
            bigramCol2 = key.findCol(second);

            if(bigramRow1 == bigramRow2)
            {
                bigram.setFirst(phraseTable[bigramRow1][(bigramCol1 + 4) % 5]);
                bigram.setSecond(phraseTable[bigramRow2][(bigramCol2 - 1) % 5]);
            }
            else if(bigramCol1 == bigramCol2)
            {
                bigram.setFirst((phraseTable[(bigramRow1 + 4) % 5][bigramCol1]));
                bigram.setSecond((phraseTable[(bigramRow2 + 4) % 5][bigramCol2]));
            }
            else
            {
                bigram.setFirst(phraseTable[bigramRow1][bigramCol2]);
                bigram.setSecond(phraseTable[bigramRow2][bigramCol1]);
            }
            phrase.enqueue(bigram);
        }
        return phrase;
    }

    /**
     * toString method
     * @return temp, phrases either encrypted or decrypted
     */
    public String toString()
    {
        String temp = "";
        while(size > 0)
        {
            Bigram a = dequeue();
            temp = temp + a;
        }
        return temp;
    }

}
