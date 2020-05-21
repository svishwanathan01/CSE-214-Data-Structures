import java.io.Serializable;

//Sabareesh Vishwanathan
//112585006
//R03
public class User implements Serializable {
    String user;
    int indexPos, followers, following;
    static int userCount;

    /**
     * Constructor method for User class
     */
    public User()
    {
        user = "";
        indexPos = 0;
        userCount = 0;
        followers = 0;
        following = 0;
    }

    /**
     * Initializes a new User object
     * @param user
     */
    public User(String user)
    {
        this.user = user;
        indexPos = userCount;
        userCount++;
    }

    /**
     * Getter method for indexPos variable
     * @return indexPos
     */
    public int getIndexPos() {
        return indexPos;
    }

    /**
     * Getter method for userCount variable
     * @return userCount
     */
    public static int getUserCount() {
        return userCount;
    }

    /**
     * Getter method for user (name of user) variable
     * @return user
     */
    public String getUser() {
        return user;
    }

    /**
     * Setter method for indexPos variable of User object
      * @param indexPos
     */
    public void setIndexPos(int indexPos) {
        this.indexPos = indexPos;
    }

    /**
     * Setter method for user variable of User object
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Setter method for userCount variable of User object
     * @param userCount
     */
    public static void setUserCount(int userCount) {
        User.userCount = userCount;
    }

    /**
     * Getter method for followers variable of User object
     * @return followers
     */
    public int getFollowers() {
        return followers;
    }

    /**
     * Getter method for following variable of User object
     * @return following
     */
    public int getFollowing() {
        return following;
    }

    /**
     * Setter method for followers variable of User object
     * @param followers
     */
    public void setFollowers(int followers) {
        this.followers = followers;
    }

    /**
     * Setter method for following variable of User object
     * @param following
     */
    public void setFollowing(int following) {
        this.following = following;
    }
}
