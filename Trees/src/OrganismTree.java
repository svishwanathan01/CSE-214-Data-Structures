//Sabareesh Vishwanathan
//112585006
//R03

public class OrganismTree {
    private OrganismNode root;
    private OrganismNode cursor;
    private String preOrderString;
    private String printOrganismString;
    private String printFoodChainString;
    int level;

    /**
     * Default Constructor
     */
    public OrganismTree()
    {
        root = null;
        cursor = null;
        preOrderString = "";
        printOrganismString = "";
        printFoodChainString = "";
        level = 0;
    }

    /**
     * Getter method for cursor
     * @return cursor
     */
    public OrganismNode getCursor() {
        return cursor;
    }

    /**
     * Setter method for cursor
     * @param cursor
     */
    public void setCursor(OrganismNode cursor) {
        this.cursor = cursor;
    }

    /**
     * Setter method for root of tree
     * @param root
     */
    public void setRoot(OrganismNode root) {
        this.root = root;
    }

    /**
     * Getter method for root of tree
     * @return root
     */
    public OrganismNode getRoot() {
        return root;
    }

    /**
     * Default constructor
     * @param apexPredator
     */
    public OrganismTree(OrganismNode apexPredator)
    {
        root = apexPredator;
        cursor = apexPredator;
    }

    /**
     * Resets cursor to root
     */
    public void cursorReset()
    {
        cursor = root;
    }

    /**
     * Moves cursor to given name and throws exception if the object given isn't found
     * @param name
     * @throws IllegalArgumentException
     */
    public void moveCursor(String name) throws IllegalArgumentException
    {
        try
        {
            OrganismNode atCursor;
            OrganismNode leftNode;
            OrganismNode middleNode;
            OrganismNode rightNode;
            atCursor = getCursor();
            leftNode = atCursor.getLeft();
            middleNode = atCursor.getMiddle();
            rightNode = atCursor.getRight();

            if(leftNode.getName().equals(name)) {
                cursor = cursor.getLeft();
                System.out.println("Cursor has successfully been moved to " + cursor.getName());
            }
            else if(middleNode.getName().equals(name))
            {
                cursor = cursor.getMiddle();
                System.out.println("Cursor has successfully been moved to " + cursor.getName());
            }
            else if(rightNode.getName().equals(name))
            {
                cursor = cursor.getRight();
                System.out.println("Cursor has successfully been moved to " + cursor.getName());
            }
        }
        catch(NullPointerException e)
        {
           System.out.println("Error: Cannot move to given child");
        }
    }

    /**
     * Lists prey of node at cursor
     * @return listOfPrey
     * @throws IsPlantException
     */
    public String listPrey() throws IsPlantException
    {
        OrganismNode atCursor;
        OrganismNode leftNode;
        OrganismNode middleNode;
        OrganismNode rightNode;
        atCursor = getCursor();
        String listOfPrey = "";

        if(atCursor.isPlant() == true)
        {
            throw new IsPlantException("Plants can't have prey!");
        }

        listOfPrey = atCursor.getName() + " -> ";
        leftNode = atCursor.getLeft();
        middleNode = atCursor.getMiddle();
        rightNode = atCursor.getRight();

        if(leftNode != null)
        {
            listOfPrey += leftNode.getName() + ", ";
        }
        if(middleNode != null)
        {
            listOfPrey += middleNode.getName() + ", ";
        }
        if(rightNode != null)
        {
            listOfPrey += rightNode.getName() + ", ";
        }
        listOfPrey = listOfPrey.replaceAll(", $","");
        System.out.println(listOfPrey);
        return listOfPrey;
    }

    /**
     * Lists food chain for given node from root to given node
     * @return printFoodChainString
     */
    public String listFoodChain()
    {
        printFoodChainString = "";
        foodChainHelper(root, cursor);
        printFoodChainString = printFoodChainString.replaceAll(" -> $","");
        System.out.println(printFoodChainString);
        return printFoodChainString;
    }

    /**
     * Helper method for listFoodChain
     * @param root
     * @param cursor
     * @return boolean value
     */
    public boolean foodChainHelper(OrganismNode root, OrganismNode cursor)
    {
        if(root == null)
        {
            return false;
        }
        OrganismNode left = root.getLeft();
        OrganismNode middle = root.getMiddle();
        OrganismNode right = root.getRight();
        String name = root.getName();

        if(root == cursor)
        {
            printFoodChainString = name + " -> " + printFoodChainString;
            return true;
//            foodChainString(name);
        }
        if(foodChainHelper(left, cursor))
        {
            printFoodChainString = name + " -> " + printFoodChainString;
            return true;
//            foodChainString(name);
        }
        if(foodChainHelper(middle, cursor))
        {
            printFoodChainString = name + " -> " + printFoodChainString;
            return true;
//            foodChainString(name);
        }
        if(foodChainHelper(right, cursor))
        {
            printFoodChainString = name + " -> " + printFoodChainString;
            return true;
//            foodChainString(name);
        }
        return false;
    }

    /**
     * helper method for list food chain
     * @param name
     * @return boolean value
     */
    public boolean foodChainString(String name)
    {
        printFoodChainString = name + " -> " + printFoodChainString;
        return true;
    }

    /**
     * Recursively prints organism tree using helper method
     */
    public void printOrganismTree()
    {
        printOrganismString = "";
        preOrderOrgTree(cursor);
    }

    /**
     * Helper method for printOrganismTree
     * @param c
     * @return
     */
    public String preOrderOrgTree(OrganismNode c)
    {
        if(c != null)
        {
            if(c.isPlant())
            {
                for(int i = 0; i < level; i++)
                {
                    System.out.print("\t");
                }
                System.out.println("- " + c.getName());
            }
            else
            {
                for(int i = 0; i < level; i++)
                {
                    System.out.print("\t");
                }
                System.out.println("|- " + c.getName());
            }
        }
        if(c != null)
        {
            level++;
            preOrderOrgTree(c.getLeft());
            level--;
            level++;
            preOrderOrgTree(c.getMiddle());
            level--;
            level++;
            preOrderOrgTree(c.getRight());
            level--;
        }
        return printOrganismString;
    }

    /**
     * Lists all plants from cursor
     * @return temp
     */
    public String listAllPlants()
    {
        String temp = "";
        preOrderString = "";
        temp = preOrderPlant(cursor);
        temp = temp.replaceAll("null", "");
        temp = temp.replaceAll(", $", "");
        System.out.println(temp);
        return temp;
    }

    /**
     * Helper method for listAllPlants
     * @param c
     * @return preOrderString
     */
    public String preOrderPlant(OrganismNode c)
    {
        if(c == null)
        {
            return "";
        }
        if(c.isPlant())
        {
            preOrderString += c.getName() +  ", ";
        }
        preOrderPlant(c.getLeft());
        preOrderPlant(c.getMiddle());
        preOrderPlant(c.getRight());
        return preOrderString;
    }

    /**
     * Adds animal child to a given organism node in tree
     * @param name
     * @param isHerbivore
     * @param isCarnivore
     * @throws IllegalArgumentException
     * @throws PositionNotAvailableException
     * @throws DietMismatchException
     * @throws IsPlantException
     */
    public void addAnimalChild(String name, boolean isHerbivore, boolean isCarnivore) throws IllegalArgumentException, PositionNotAvailableException, DietMismatchException, IsPlantException {
//        if(cursor.getRight() == null)
//        {
//            throw new PositionNotAvailableException("There is no more room for more prey for this predator.");
//        }
        OrganismNode orgNode = new OrganismNode();
        orgNode.setName(name);
        orgNode.setCarnivore(isCarnivore);
        orgNode.setHerbivore(isHerbivore);
        try
        {
            cursor.addPrey(orgNode);
            System.out.println("A(n) " + name + " has successfully been added as prey for the " + cursor.getName() + "!");

        }
        catch (IsPlantException e)
        {
            System.out.println("ERROR: The cursor is at a plant node. Plants cannot be predators. ");
        }
        catch(DietMismatchException e)
        {
            System.out.println("ERROR: This prey cannot be added as it does not match the diet of the predator. ");
        }
        catch(PositionNotAvailableException e)
        {
            System.out.println("ERROR: There is no more room for more prey for this predator.");
        }
    }

    /**
     * Adds Plant child to a given organism node in tree
     * @param name
     * @throws IllegalArgumentException
     * @throws PositionNotAvailableException
     * @throws IsPlantException
     * @throws DietMismatchException
     */
    public void addPlantChild(String name) throws IllegalArgumentException, PositionNotAvailableException, IsPlantException, DietMismatchException {
        OrganismNode orgNode = new OrganismNode();
        orgNode.setName(name);
        orgNode.setPlant(true);
        try
        {
            cursor.addPrey(orgNode);
            System.out.println(name + " has successfully been added as prey for the " + cursor.getName());
        }
        catch (IsPlantException e)
        {
            System.out.println("ERROR: The cursor is at a plant node. Plants cannot be predators. ");
        }
        catch(DietMismatchException e)
        {
            System.out.println("ERROR: This prey cannot be added as it does not match the diet of the predator. ");
        }
        catch(PositionNotAvailableException e)
        {
            System.out.println("ERROR: There is no more room for more prey for this predator.");
        }
    }

    /**
     * Removes child in given node
     * @param name
     * @throws IllegalArgumentException
     */
    public void removeChild(String name) throws IllegalArgumentException
    {
        if(cursor.getLeft().getName().equals(name))
        {
            cursor.setLeft(cursor.getMiddle());
            cursor.setMiddle(cursor.getRight());
            cursor.setRight(null);
            System.out.println("A(n) " + name + " has successfully been removed as prey for the " + cursor.getName());
        }
        else if(cursor.getMiddle().getName().equals(name))
        {
            cursor.setMiddle(cursor.getRight());
            cursor.setRight(null);
            System.out.println("A(n) " + name + " has successfully been removed as prey for the " + cursor.getName());
        }
        else if(cursor.getRight().getName().equals(name))
        {
            cursor.setRight(null);
            System.out.println("A(n) " + name + " has successfully been removed as prey for the " + cursor.getName());
        }
        else
        {
            throw new IllegalArgumentException("No such organism to be removed!");
        }
    }

}
