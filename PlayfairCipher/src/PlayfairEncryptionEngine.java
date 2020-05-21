//Sabareesh Vishwanathan
//112585006
//R03

import java.util.Scanner;

public class PlayfairEncryptionEngine {
    public static void main(String[] args)
    {
        boolean check = true;
        KeyTable keyTable = new KeyTable();
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter key phrase: ");
        String keyPhrase = myScanner.nextLine();
        keyPhrase = keyPhrase.toUpperCase();
        keyPhrase = keyPhrase.replaceAll("\\s", "");
        keyPhrase = keyPhrase.replaceAll("[^a-zA-Z0-9\\s+]", "");
        keyPhrase = keyPhrase.replaceAll("\\d", "");
        keyTable = keyTable.buildFromString(keyPhrase);
        System.out.println("Generation success!\n");

        while(check == true)
        {
            System.out.println("Menu: \n(CK) - Change key\n(PK) - Print Key\n(EN) - Encrypt\n(DE) - Decrypt\n(Q) - Quit\n\nPlease select an option: ");
            String option = myScanner.nextLine();
            option = option.toLowerCase();
            if(option.equals("pk"))
            {
                System.out.println(keyTable.toString());
            }
            else if(option.equals("ck"))
            {
                System.out.println("Enter key phrase: ");
                String newKeyPhrase = myScanner.nextLine();
                newKeyPhrase = newKeyPhrase.toUpperCase();
                newKeyPhrase = newKeyPhrase.replaceAll("\\s", "");
                newKeyPhrase = newKeyPhrase.replaceAll("[^a-zA-Z0-9\\s+]", "");
                newKeyPhrase = newKeyPhrase.replaceAll("\\d", "");
                keyTable = keyTable.buildFromString(newKeyPhrase);
                System.out.println("Generation success!\n");
            }
            else if(option.equals("q"))
            {
                System.out.println("\nProgram terminating....");
                check = false;
            }
            else if(option.equals("en"))
            {
                Phrase phrase = new Phrase();
                System.out.println("Please enter a phrase to encrypt: ");
                String encryptPhrase = myScanner.nextLine();
                encryptPhrase = encryptPhrase.toUpperCase();
                encryptPhrase = encryptPhrase.replaceAll("\\s", "");
                encryptPhrase = encryptPhrase.replaceAll("[^a-zA-Z0-9\\s+]", "");
                encryptPhrase = encryptPhrase.replaceAll("\\d", "");
                phrase = Phrase.buildPhraseFromStringForEnc(encryptPhrase);
                System.out.println("Encrypted text is: " + phrase.encrypt(keyTable).toString());
            }
            else if(option.equals("de"))
            {
                Phrase phrase = new Phrase();
                System.out.println("Please enter a phrase to decrypt: ");
                String decryptPhrase = myScanner.nextLine();
                decryptPhrase = decryptPhrase.toUpperCase();
                decryptPhrase = decryptPhrase.replaceAll("\\s", "");
                decryptPhrase = decryptPhrase.replaceAll("[^a-zA-Z0-9\\s+]", "");
                decryptPhrase = decryptPhrase.replaceAll("\\d", "");
                phrase = Phrase.buildPhraseFromStringForEnc(decryptPhrase);
                System.out.println("Decrypted text is: " + phrase.decrypt(keyTable).toString());
            }
            else
            {
                System.out.println("Option not valid, choose a different option from the menu!");
            }
        }




    }
}
