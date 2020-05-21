//Sabareesh Vishwanathan
//112585006
//R03

public class OrganismNode {
    private String name;

    private boolean isPlant;
    private boolean isHerbivore;
    private boolean isCarnivore;

    private OrganismNode left;
    private OrganismNode middle;
    private OrganismNode right;

    /**
     * Default constructor
     */
    public OrganismNode()
    {
        name = "";
        isPlant = false;
        isHerbivore = false;
        isCarnivore = false;
        left = null;
        middle = null;
        right = null;
    }

    /**
     * Getter method for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns true or false if object is carnivore
     * @return isCarnivore
     */
    public boolean isCarnivore() {
        return isCarnivore;
    }

    /**
     * Returns true or false if object is herbivore
     * @return isHerbivore
     */
    public boolean isHerbivore() {
        return isHerbivore;
    }

    /**
     * Returns true or false if object is a plant
     * @return isPlant
     */
    public boolean isPlant() {
        return isPlant;
    }

    /**
     * Sets value to either true or false based on given object
     * @param herbivore
     */
    public void setHerbivore(boolean herbivore) {
        isHerbivore = herbivore;
    }

    /**
     * Sets value to either true or false based on given object
     * @param plant
     */
    public void setPlant(boolean plant) {
        isPlant = plant;
    }

    /**
     * Sets value to either true or false based on given object
     * @param carnivore
     */
    public void setCarnivore(boolean carnivore) {
        isCarnivore = carnivore;
    }

    /**
     * Returns left tree node of cursor
     * @return left
     */
    public OrganismNode getLeft() {
        return left;
    }

    /**
     * Returns middle tree node of cursor
     * @return middle
     */
    public OrganismNode getMiddle() {
        return middle;
    }

    /**
     * Returns right tree node of cursor
     * @return right
     */
    public OrganismNode getRight() {
        return right;
    }

    /**
     * Sets left node of given node
     * @param left1
     */
    public void setLeft(OrganismNode left1) {
        left = left1;
        if (left == null)
            System.out.println("Did not work");
    }

    /**
     * Sets middle node of given node
     * @param middle1
     */
    public void setMiddle(OrganismNode middle1) {
        middle = middle1;
    }

    /**
     * Sets name node of given node
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets right node of given node
     * @param right1
     */
    public void setRight(OrganismNode right1) {
        right = right1;
    }

    /**
     * addPrey function adds given node to either left, middle, or right of cursor
     * @param preyNode
     * @throws PositionNotAvailableException
     * @throws IsPlantException
     * @throws DietMismatchException
     */
    public void addPrey(OrganismNode preyNode) throws PositionNotAvailableException, IsPlantException, DietMismatchException {
        if (isPlant()) {
            throw new IsPlantException("ERROR: The cursor is at a plant node. Plants cannot be predators.");
        }

        if (isHerbivore()) {
            if (!preyNode.isPlant())
            {
                throw new DietMismatchException("ERROR: This prey cannot be added as it does not match the diet of the predator.");
            }
        }
        else if (isPlant())
        {
            if(preyNode.isCarnivore())
            {
                throw new DietMismatchException("ERROR: This prey cannot be added as it does not match the diet of the predator.");
            }
            else if(preyNode.isHerbivore())
            {
                throw new DietMismatchException("ERROR: This prey cannot be added as it does not match the diet of the predator.");
            }
        }

        if(getLeft() == null)
        {
            left = preyNode;
        }
        else if(getMiddle() == null)
        {
            middle = preyNode;
        }
        else if(getRight() == null)
        {
            right = preyNode;
        }
        else
        {
            throw new PositionNotAvailableException("ERROR: There is no more room for more prey for this predator.");
        }
    }


}

