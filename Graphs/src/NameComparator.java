//Sabareesh Vishwanathan
//112585006
//R03

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class NameComparator implements Comparator<User> {

    @Override
    /**
     * Compare method in name comparator.
     * Returns a value given the name of two users, implemented in printAllUsers method
     */
    public int compare(User o1, User o2) {
        User u1 = (User) o1;
        User u2 = (User) o2;

        return(u1.getUser().compareTo(u2.getUser()));
    }
}
