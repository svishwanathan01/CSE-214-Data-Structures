//Sabareesh Vishwanathan
//112585006
//R03

import java.util.Scanner;

public class FoodPyramid {
    /**
     * Main method for food pyramid
     * @param args
     * @throws IsPlantException
     * @throws DietMismatchException
     * @throws PositionNotAvailableException
     */
    public static void main(String[] args) throws IsPlantException, DietMismatchException, PositionNotAvailableException {
        boolean check = true;
        Scanner myScanner = new Scanner(System.in);
        String apexType = "";
        String apexName = "";
        OrganismNode apexPredator  = new OrganismNode();
        System.out.print("What is the name of the apex predator?: ");
        apexName = myScanner.nextLine();
        apexName = apexName.toLowerCase();
        apexPredator.setName(apexName);
        System.out.print("Is the organism an herbivore / a carnivore / an omnivore? (H / C / O): ");
        apexType =  myScanner.nextLine();
        apexType = apexType.toLowerCase();
        OrganismTree organismTree = new OrganismTree(apexPredator);

        System.out.println("\nConstructing food pyramid .  .  . \n");

        while(check == true)
        {
            System.out.println("Menu: \n\n(PC) - Create New Plant Child\n(AC) - Create New Animal Child\n(RC) - Remove Child\n(P) - Print Out Cursor's Prey\n(C) - Print Out Food Chain\n(F) - Print Out Food Pyramid at Cursor\n(LP) - List All Plants Supporting Cursor\n(R) - Reset Cursor to Root\n(M) - Move Cursor to Child\n(Q) - Quit\n");
            System.out.println("Please select an option: ");
            String option = myScanner.nextLine();
            option = option.toLowerCase();

            if(option.equals("pc"))
            {
                String plantName = "";
                System.out.println("What is the name of the organism?");
                plantName = myScanner.nextLine();
                plantName = plantName.toLowerCase();
                organismTree.addPlantChild(plantName);
                //System.out.println(plantName + " has successfully been added as prey for the " + organismTree.getCursor());
            }
            else if(option.equals("ac"))
            {
                String animalName = "";
                String orgType = "";
                System.out.print("What is the name of the organism?: ");
                animalName = myScanner.nextLine();
                animalName = animalName.toLowerCase();
                System.out.print("\nIs the organism an herbivore / a carnivore / an omnivore? (H / C / O): ");
                orgType = myScanner.nextLine();
                orgType = orgType.toLowerCase();
                System.out.println();
                if(orgType.equals("h"))
                {
                    organismTree.addAnimalChild(animalName, true, false);
                }
                else if(orgType.equals("c"))
                {
                    organismTree.addAnimalChild(animalName, false, true);
                }
                else if(orgType.equals("o"))
                {
                    organismTree.addAnimalChild(animalName, false, false);
                }
                else
                {
                    System.out.println("No such animal type!");
                }

            }
            else if(option.equals("rc"))
            {
                String removeName = "";
                System.out.print("What is the name of the organism to be removed?: ");
                removeName = myScanner.nextLine();
                removeName = removeName.toLowerCase();
                System.out.println("\n");
                organismTree.removeChild(removeName);
            }
            else if(option.equals("p"))
            {
                organismTree.listPrey();
            }
            else if(option.equals("c"))
            {
                organismTree.listFoodChain();
            }
            else if(option.equals("f"))
            {
                organismTree.printOrganismTree();
            }
            else if(option.equals("lp"))
            {
                organismTree.listAllPlants();
            }
            else if(option.equals("r"))
            {
                organismTree.cursorReset();
                System.out.println("Cursor successfully reset to root!");
            }
            else if(option.equals("m"))
            {
                try{
                    String cursorString = "";
                    System.out.print("Move to?: ");
                    cursorString = myScanner.nextLine();
                    System.out.println();
                    organismTree.moveCursor(cursorString);
                }
                catch(IllegalArgumentException e)
                {
                    System.out.println("ERROR: Cannot move to given prey name.");
                }
            }
            else if(option.equals("q"))
            {
                check = false;
            }
            else
            {
                System.out.println("Option not valid, choose a different option from the menu!");
            }
        }
    }
}

