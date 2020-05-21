//Sabareesh Vishwanathan
//112585006
//R03
import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class FollowGraphDriver implements Serializable{
    /**
     * Main method
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        Scanner myScanner = new Scanner(System.in);
        boolean check = true;
        FollowGraph graph = new FollowGraph();
        try {
            FileInputStream file = new FileInputStream("library.obj");
            ObjectInputStream inStream = new ObjectInputStream(file);
            FollowGraph library;

            library = (FollowGraph) inStream.readObject();
        } catch (Exception e) {
            System.out.println("Library will be created.");
            }

        while(check)
        {
            System.out.println("(U) Add User\n(C) Add Connection\n(AU) Load all Users\n(AC) Load all Connections\n(P) Print all Users\n(L) Print all Loops\n(RU) Remove User\n(RC) Remove Connection\n(SP) Find Shortest Path\n(AP) Find All Paths\n(Q) Quit\n");
            System.out.print("Enter a selection: ");
            String selection = myScanner.nextLine();
            selection = selection.toUpperCase();

            if(selection.equals("U"))
            {
                String a = "";
                System.out.print("Please enter a name: ");
                a = myScanner.nextLine();
                System.out.println();
                graph.addUser(a);
            }
            else if(selection.equals("C"))
            {
                String source = "", dest = "";
                System.out.print("\nPlease enter the source of the connection to add: ");
                source = myScanner.nextLine();
                System.out.print("\nPlease enter the dest of the connection to add: ");
                dest = myScanner.nextLine();
                graph.addConnection(source, dest);
            }
            else if(selection.equals("AU"))
            {
                String file = "";
                System.out.print("Enter the file name: ");
                file = myScanner.nextLine();
                graph.loadAllUsers(file);
                System.out.println("\n");
            }
            else if(selection.equals("AC"))
            {
                String file = "";
                System.out.print("Enter the file name: ");
                file = myScanner.nextLine();
                graph.loadAllConnections(file);
                System.out.println("\n");
            }
            else if(selection.equals("P"))
            {
                boolean checkP = true;
                String pString = "";
                while(checkP)
                {
                    System.out.println("(SA) Sort Users by Name\n(SB) Sort Users by Number of Followers\n(SC) Sort Users by Number of Following\n(Q) Quit");
                    pString = myScanner.nextLine();
                    pString = pString.toUpperCase();
                    if(pString.equals("SA"))
                    {
                        graph.printAllUsers(new NameComparator());
                    }
                    else if(pString.equals("SB"))
                    {
                        graph.printAllUsers(new FollowersComparator());
                    }
                    else if(pString.equals("SC"))
                    {
                        graph.printAllUsers(new FollowingComparator());
                    }
                    else if(pString.equals("Q"))
                    {
                        checkP = false;
                        break;
                    }
                    else
                    {
                        System.out.println("Invalid selection. Please try again!");
                    }
                }
            }
            else if(selection.equals("L"))
            {
                System.out.println("\n:(\n");
            }
            else if(selection.equals("RU"))
            {
                System.out.println("\n:(\n");
            }
            else if(selection.equals("RC"))
            {
                String source = "", dest = "";
                System.out.print("\nPlease enter the source of the connection to add: ");
                source = myScanner.nextLine();
                System.out.print("\nPlease enter the dest of the connection to add: ");
                dest = myScanner.nextLine();
                graph.removeConnection(source, dest);
            }
            else if(selection.equals("SP"))
            {
                String source = "", dest = "";
                System.out.print("\nPlease enter the desired source: ");
                source = myScanner.nextLine();
                System.out.print("\nPlease enter the desired destination: ");
                dest = myScanner.nextLine();
                System.out.println(graph.shortestPath(source, dest));
            }
            else if(selection.equals("AP"))
            {
                System.out.println("\n:(\n");
            }
            else if(selection.equals("Q"))
            {
                try
                {
                    check = false;
                    FileOutputStream file = new FileOutputStream("library.obj");
                    ObjectOutputStream outStream = new ObjectOutputStream(file);
                    FollowGraph library = new FollowGraph(/*Constructor Parameters*/);

                    outStream.writeObject(library);
                }
                catch(Exception e)
                {
                    System.out.println("Library");
                }

            }
            else
            {
                System.out.println("Invalid selection, please try again!");
            }

        }

    }
}
