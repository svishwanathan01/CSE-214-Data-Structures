//Sabareesh Vishwanathan
//112585006
//R03
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.*;

public class FollowGraph implements Serializable {
    private ArrayList<User> users;
    public static final int MAX_USERS = 100;
    private boolean[][] connections;

    /**
     * Getter method for users Array List
     * @return users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Getter method for connections 2D Array
     * @return connections
     */
    public boolean[][] getConnections() {
        return connections;
    }

    /**
     * Setter method for connections 2D Array
     * @param connections
     */
    public void setConnections(boolean[][] connections) {
        this.connections = connections;
    }

    /**
     * Setter method for users Array List
     * @param users
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * Constructor method for FollowGraph
     */
    public FollowGraph()
    {
        users = new ArrayList<User>();
        connections = new boolean[MAX_USERS][MAX_USERS];
    }

    /**
     * Adds user given the user name.
     * Checks if user already exists in system first before adding user.
     * @param userName
     */
    public void addUser(String userName)
    {
        boolean check = false;
//        if(users.contains(userName))
//            check = true;
        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getUser().equals(userName))
                check = true;
        }
        if(check == false)
        {
            users.add(new User(userName));
            System.out.println(userName + " has been added");
        }

    }

    /**
     * Adds connection given two different usernames.
     * Checks if both users exist before adding connection.
     * @param userFrom
     * @param userTo
     */
    public void addConnection(String userFrom, String userTo)
    {
        boolean checkFrom = false, checkTo = false;
        int indexFrom = 0, indexTo = 0;

        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getUser().equals(userFrom))
            {
                checkFrom = true;
            }
            else if (users.get(i).getUser().equals(userTo))
            {
                checkTo = true;
            }
        }

        indexFrom = indexOfUser(userFrom);
        indexTo = indexOfUser(userTo);
        if(checkFrom == true && checkTo == true)
        {
            if(connections[indexFrom][indexTo] == true)
            {
                System.out.println(userFrom + ", " + userTo + " already exists.");
            }
            else
            {
                connections[indexFrom][indexTo] = true;
                users.get(indexFrom).setFollowing(users.get(indexFrom).getFollowing() + 1);
                users.get(indexTo).setFollowers(users.get(indexTo).getFollowers() + 1);
                System.out.println(userFrom + ", " + userTo + " has been added.");
            }
        }
        else
        {
            System.out.println("\nNo such user in system, cannot add connection, please try again.\n");
        }

    }

    /**
     * Removes connection given two different usernames.
     * Checks if both users exist before removing connection.
     * @param userFrom
     * @param userTo
     */
    public void removeConnection(String userFrom, String userTo)
    {
        boolean checkFrom = false, checkTo = false;
        int indexFrom = 0, indexTo = 0;

        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getUser().equals(userFrom))
                checkFrom = true;
            else if (users.get(i).getUser().equals(userTo))
                checkTo = true;
        }

        indexFrom = indexOfUser(userFrom);
        indexTo = indexOfUser(userTo);

        if(checkFrom == true && checkTo == true)
        {
            connections[indexFrom][indexTo] = false;
            users.get(indexFrom).setFollowing(users.get(indexFrom).getFollowing() - 1);
            users.get(indexTo).setFollowers(users.get(indexTo).getFollowers() - 1);
        }
        else
        {
            System.out.println("\nNo such user found, cannot remove connection, please try again.\n");
        }
    }

    /**
     * Returns the shortestPath for two given users using Floyd-Warshall algorithm
     * Uses helper method floydWarshall
     * @param userFrom
     * @param userTo
     * @return floydWarshall(indexFrom, indexTo)
     * @throws IllegalArgumentException
     */
    public String shortestPath(String userFrom, String userTo) throws IllegalArgumentException
    {
        int indexFrom = -1, indexTo = -1;
        indexFrom = indexOfUser(userFrom);
        indexTo = indexOfUser(userTo);

        if(indexFrom == -1 || indexTo == -1)
            throw new IllegalArgumentException("Given user(s) doesn't exist.");

        return floydWarshall(indexFrom, indexTo);
    }

    /**
     * Helper method for shortestPath method.
     * Takes two ints, sets 2D arrays dist and next as described by algorithm, and uses standard algorithm implementation
     * Uses helper method path to return shortest path
     * @param from
     * @param to
     * @return
     */
    private String floydWarshall(int from, int to)
    {
        int size = users.size();
        double[][] dist = new double[users.size()][users.size()];
        int[][] next = new int[users.size()][users.size()];

        for(int i = 0; i < users.size(); i++)
        {
            for(int j = 0; j < users.size(); j++)
            {
                if(connections[i][j] == true)
                {
                    dist[i][j] = 1;
                }
                else {
                    dist[i][j] = Double.POSITIVE_INFINITY;
                }
                 next[i][j] = j;
            }
        }
        for(int k = 1; k < users.size(); k++)
        {
            for(int i = 1; i < users.size(); i++)
            {
                for(int j = 1; j < users.size(); j++)
                {

                    if(dist[i][k] + dist[k][j] < dist[i][j])
                    {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
        return path(dist, next, from, to);
    }

    /**
     * Helper method for floydWarshall method
     * Uses the path procedure to return the shortest path
     * @param dist
     * @param next
     * @param from
     * @param to
     * @return path
     */
    public String path(double[][] dist, int[][] next, int from, int to)
    {
        String path = "";
        if(next[from][to] == 0)
            return "";

        path = users.get(from).getUser();

        while(from != to)
        {
            from = next[from][to];
            path += " -> " + users.get(from).getUser();
        }
        return path;
    }

//    public ArrayList<String> allPaths(String userFrom, String userTo)
//    {
//        ArrayList<String> allPaths = new ArrayList<String>();
//        ArrayList<String> temp = new ArrayList<String>();
//
//    }

    /**
     * Prints all users in system given a comparator (FollowersComparator, FollowingComparator, or NameComparator)
     * Prints in formatted table
     * @param comp
     */
    public void printAllUsers(Comparator comp) {
        ArrayList<User> temp = new ArrayList();
        for(User u : users){
            temp.add(u);
        }
        Collections.sort(temp, comp);
        System.out.println("Users: ");
        System.out.format("%-25s%-25s%-25s\n","User Name", "Number of Followers", "Number Following");
        for(int j = 0; j < 70; j++)
        {
            System.out.print("-");
        }
        for (int i = 0; i < temp.size(); i++)
            System.out.format("\n%-33s%-25s%-1s",temp.get(i).getUser(), temp.get(i).getFollowers(), temp.get(i).getFollowing());
        System.out.println("\n");
    }

    /**
     * Prints all followers for a given user
     * @param userName
     */
    public void printAllFollowers(String userName)
    {
        try
        {
            int index = indexOfUser(userName);
            String temp = "";
            String followers = "";
            for(int i = 0; i < connections[index].length; i++)
            {
                if(connections[index][i] == true)
                {
                    temp = "";
                    temp = users.get(i).getUser();
                    followers += temp + ", ";
                }
            }
            System.out.println(followers);
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("\nNo such user found, please try again.");
        }

    }

    /**
     * Prints all following a particular user
     * @param userName
     */
    public void printAllFollowing(String userName)
    {
        try
        {
            int index = indexOfUser(userName);
            String temp = "";
            String following = "";
            for(int i = 0; i < connections.length; i++)
            {
                if(connections[i][index] == true)
                {
                    temp = "";
                    temp = users.get(i).getUser();
                    following += temp + ", ";
                }
            }
            System.out.println(following);
        }
        catch(IllegalArgumentException e)
        {
            System.out.println("\nNo such user found, please try again.");
        }

    }

//    public ArrayList<String> findAllLoops()
//    {
//
//    }

    /**
     * Loads all users from a text file
     * Throws exception if a particular file doesn't exist in the directory
     * @param filename
     * @throws FileNotFoundException
     */
    public void loadAllUsers(String filename) throws FileNotFoundException {
        try
        {
            File file = new File(filename);
            Scanner stdin = new Scanner(file);
            int index = 0;
            System.out.println();
            while(stdin.hasNextLine())
            {
                String data = stdin.nextLine();
                addUser(data);
                index++;
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("\nERROR: No such file name found.");
        }


    }

    /**
     * Loads all connections from a text file
     * Throws exception if a particular file doesn't exist in the directory
     * @param filename
     * @throws FileNotFoundException
     */
    public void loadAllConnections(String filename) throws FileNotFoundException {
        try
        {
            File file = new File(filename);
            Scanner stdin = new Scanner(file);
            int index = 0;

            while(stdin.hasNextLine())
            {
                String data = "", from = "", to = "";
                data = stdin.nextLine();
                int comma = data.indexOf(",");
                from = data.substring(0, comma);
                to = data.substring(comma+2);
                addConnection(from, to);
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("\nERROR: No such file name found.");
        }

    }

    /**
     * Helper method used to find an index of a given user
     * @param name
     * @return i, -1 (if not found)
     */
    public int indexOfUser(String name)
    {
        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).getUser().equals(name))
                return i;
        }
        return -1;
    }
}
