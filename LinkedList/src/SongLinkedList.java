//Sabareesh Vishwanathan
//112585006
//R03

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.lang.Math;

public class SongLinkedList {

    AudioInputStream AIS;
    String fName;
    SongNode head;
    SongNode tail;
    SongNode cursor;
    int size;
    int count;
    String temp;

    public SongLinkedList()
    {
        head = null;
        tail = null;
        cursor = null;
        size = 0;
        count = 0;
    }

    public void play(String name) throws IllegalArgumentException
    {
        fName = name.concat(".wav");
        SongNode a = null;


        if(head == null)
            throw new IllegalArgumentException ("No songs in the playlist");
        else
            a = head;

        filePlayer(fName, a, name);

        /*
        for(int i = 0; i < size; i++) {
            if (a.getData().getName().equals(name)) {
                a = cursor;
                filePlayer(fName, a, name);*/
    }

    public void filePlayer(String fileName, SongNode b, String name) {
        try
        {
            for (int i = 0; i < size; i++) {
                if (b.getData().getName().equals(name)) {
                    AIS = AudioSystem.getAudioInputStream(new File(fileName));
                    Clip c = AudioSystem.getClip();
                    c.open(AIS);
                    c.start();
                }
                else if(b.getNext() == null)
                    throw new IllegalArgumentException ("No such song");
                else
                    b = b.getNext();
            }
        }

        catch(IOException exc)
        {
            exc.printStackTrace();
        }

        catch(UnsupportedAudioFileException exc)
        {
            exc.printStackTrace();
        }
        catch (LineUnavailableException exc)
        {
            exc.printStackTrace();
        }
    }


    public void cursorForwards()
    {
        if(size == 1)
            System.out.println("Cursor is cannot be moved forward");
        if (cursor.equals(tail))
            return;
        else if (cursor == null)
            return;
        else
        {
            cursor = cursor.getNext();
        }

    }


    public void cursorBackwards()
    {
        if(size == 1)
            System.out.println("Cursor is cannot be moved backward");
        if(cursor.equals(head))
            return;
        else if(cursor == null)
            return;
        else {
            cursor = cursor.getPrev();
        }
    }

    public void insertAfterCursor(Song newSong)
    {
        SongNode a = null;
        SongNode b = new SongNode(null, null, newSong);
        if(size == 0)
        {
            head = b;
            tail = b;
            cursor = b;
        }
        if(newSong == null)
            throw new IllegalArgumentException("There is no new song");

        if(cursor == tail)
        {
            tail = b;
            cursor.setNext(b);
            cursor.getNext().setPrev(cursor);
        }

        if(cursor != tail)
        {
            a = cursor.getNext();
            cursor.setNext(b);
            b.setPrev(cursor);
            b.setNext(a);
            a.setPrev(b);
        }
        size++;

        System.out.println(newSong.getName() + " by " + newSong.getArtist() + " is added to your playlist\n");

    }

    public Song removeCursor()
    {
        Song s = cursor.getData();
        if(head != null && tail != null) {
            head.setPrev(tail);
            tail.setNext(head);
            cursor.setData(head.getData());
            head.setData(cursor.getData());
        }
        else
            cursor = null;

        size--;
        return cursor.getData();
    }

    public int getSize()
    {
        return size;
    }

    public Song random()
    {
        int random = (int) (Math.random() * size + 1);
        SongNode a = null;
        String b;
        cursor = head;

        for(int i = 0; i < random; i ++)
        {
            cursorForwards();
            a = cursor;
        }

        b = a.getData().getName();
        play(b);
        return a.getData();
    }

    public void shuffle()
    {
        SongNode temp = cursor;
        cursor = head;
        for(int i = 0; i < size; i++)
        {
            System.out.println(String.format("%-25s%-25s%-25s%-10d", cursor.getData().getName(), cursor.getData().getArtist(), cursor.getData().getAlbum(), cursor.getData().getLength()));
            cursorForwards();
        }
    }

    public void printPlayerList()
    {
        SongNode temp = cursor;
        cursor = head;
        for(int i = 0; i < size; i++)
        {
            System.out.println(String.format("%-25s%-25s%-25s%-10d", cursor.getData().getName(), cursor.getData().getArtist(), cursor.getData().getAlbum(), cursor.getData().getLength()));
            cursorForwards();
        }
        cursor = temp;
        System.out.println("Cursor is at: " + cursor.getData().getName());

    }

    public void deleteAll()
    {
        head = null;
        tail = null;
        cursor = null;
        size = 0;
        /*
        while(head != tail)
        {
            head = null;
            head = head.getNext();
        }

        tail = null;*/
    }

    public String toString()
    {
        System.out.println(String.format("%-25s%-25s%-25s%-10s", "Song", "Artist", "Album", "Length (s)"));
        for(int i = 0; i < 100; i++)
        {
            System.out.print("-");
        }
        System.out.println("");
        if(size > 0)
        {
            printPlayerList();
        }
        return "";
    }


}
