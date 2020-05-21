//Sabareesh Vishwanathan
//112585006
//R03

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class FollowingComparator implements Comparator<User> {
    @Override
    /**
     * Compare method in following comparator.
     * Returns a value given two users, then takes the following count, implemented in printAllUsers method
     */
    public int compare(User o1, User o2) {
        User u1 = (User) o1;
        User u2 = (User) o2;

        if(u1.getFollowing() == u2.getFollowing())
            return 0;
        else if(u1.getFollowing() < u2.getFollowing())
            return 1;
        else
            return -1;
    }
}
