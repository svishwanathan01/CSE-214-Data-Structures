//Sabareesh Vishwanathan
//112585006
//R03

public class Song {
    //initialize
    String name;
    String artist;
    String album;
    String fName;
    int length;

    public Song()
    {
        name = null;
        artist = null;
        album = null;
    }


    public Song(String songName, String songArtist, String songAlbum, int len) {
        //initialize constructors
        name = songName;
        artist = songArtist;
        album = songAlbum;
        length = len;

    }

    public String getAlbum()
    {
        return album;
    }

    public String getArtist()
    {
        return artist;
    }

    public String getName()
    {
        return name;
    }

    public int getLength()
    {
        return length;
    }

    public void setLength(int length)
    {
        this.length = length;
    }

    public void setAlbum(String album)
    {
        this.album = album;
    }

    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public boolean equals(Song other)
    {
        if(this.length == other.getLength() && this.album.equals(other.getAlbum()) && this.artist.equals(other.getArtist()) && this.name.equals(other.getName()))
            return true;
        return false;
    }

}
