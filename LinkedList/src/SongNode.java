//Sabareesh Vishwanathan
//112585006
//R03

public class SongNode {
    SongNode prev;
    SongNode next;
    Song data;

    public SongNode(SongNode previous, SongNode after, Song dataLoc)
    {
        prev = previous;
        next = after;
        data = dataLoc;
    }

    public Song getData()
    {
        return data;
    }

    public SongNode getNext()
    {
        return next;
    }

    public SongNode getPrev()
    {
        return prev;
    }

    public void setData(Song data)
    {
        this.data = data;
    }

    public void setNext(SongNode next)
    {
        this.next = next;
    }

    public void setPrev(SongNode prev)
    {
        this.prev = prev;
    }

}
