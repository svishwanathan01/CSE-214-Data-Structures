//Sabareesh Vishwanathan
//112585006
//R03

import java.util.Scanner;

public class Player
{
    public static void main(String[] args)
    {
        SongLinkedList songPlaylist = new SongLinkedList();
        boolean check = true;
        while(check == true)
        {
            Scanner myScanner = new Scanner(System.in);
            System.out.println("Menu: ");
            System.out.println("(A)\tAdd Song to Playlist\n(F)\tGo to Next Song\n(B)\tGo to previous song\n(R)\tRemove Song from Playlist\n(L)\tPlay a Song\n(S)\tShuffle Playlists");
            System.out.println("(C)\tClear the Playlist\n(Z)\tRandom Song\n(P)\tPrint Playlist\n(T)\tGet the total amount of songs in the playlist\n(Q)\tExit the playlist\n\n");
            System.out.println("Enter an option: ");
            String userInput = myScanner.nextLine();
            userInput = userInput.toLowerCase();

            if(userInput.equals("a"))
            {
                System.out.print("\nEnter song title: ");
                String songTitle = myScanner.nextLine();
                System.out.print("\nEnter artist(s) of the song: ");
                String songArtist = myScanner.nextLine();
                System.out.print("\nEnter album: ");
                String songAlbum = myScanner.nextLine();
                System.out.print("\nEnter length (in seconds): ");
                int songLength = myScanner.nextInt();

                songPlaylist.insertAfterCursor(new Song(songTitle, songArtist, songAlbum, songLength));
            }
            else if(userInput.equals("f"))
            {
                songPlaylist.cursorForwards();
                System.out.println("Cursor moved to next song. \n");
            }

            else if(userInput.equals("b"))
            {
                songPlaylist.cursorBackwards();
                System.out.println("Cursor moved to previous song. \n");

            }

            else if(userInput.equals("r"))
            {
                songPlaylist.removeCursor();
            }

            else if(userInput.equals("l"))
            {
                System.out.println("Enter name of song to play: ");
                String songInput = myScanner.nextLine();
                songPlaylist.play(songInput);
            }

            else if(userInput.equals("c"))
            {
                songPlaylist.deleteAll();
                System.out.println("Playlist cleared");
            }

            else if(userInput.equals("s"))
            {
                songPlaylist.shuffle();
                System.out.println("Playlist shuffled");
            }

            else if(userInput.equals("z"))
            {
                songPlaylist.random();
            }

            else if(userInput.equals("p"))
            {
                songPlaylist.toString();
            }

            else if(userInput.equals("t"))
            {
                System.out.println("Number of songs: " + songPlaylist.getSize());
            }

            else if(userInput.equals("q"))
            {
                System.out.println("Project terminated");
                check = false;
            }

            else
            {
                check = true;
            }
        }

    }
}
