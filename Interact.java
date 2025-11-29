import java.util.HashMap;
import java.util.Scanner;

/**
 * Write a description of class Interact here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Interact {
    private Game game;
    private Player player;
    private Art art;
    public boolean phonecharged;
    private boolean keypadtrue = false;
    private boolean getkey2 = false;
    public boolean wingame;
    //below are the different functions for the different items that can be interacted 

    public Interact(Game game, Player player) {
        this.game = game;
        this.player = player;
    }

    public void opendoor() {
        boolean keytrue = player.checkifkey();
        String roomname = game.getroomname();
        if (roomname .equals("living"))
        {
            if (keytrue && getkey2) 
            {
                art.image2();
                System.out.println("Succesfully, you have both keys, are alive and in front of the main door.\n Who would have thought?\nYou stand outside the American Gardens Building. You have escaped.");
                System.out.println("\n***************You have won the game, thanks for playing****************");
                System.out.println("\n\n\n\n\nbut will you truly ever escape????");
                wingame=true;
            } 
            else if (keytrue && !getkey2) 
            {
                System.out.println("You have the primary key, but if you didn't plan on dying.. I'd hope you remember you need 2 keys.\nSo go find the other one.");
                wingame=false;
            }
            else if (!keytrue && getkey2)
            {
                System.out.println("You have the secondary key, but no primary, so maybe (definitely) get that and come back");
                wingame=false;
            }
            else if (!keytrue && !getkey2)
            {
                System.out.println("You've got exactly.. 0 keys, get the damn keys first");
                wingame=false;
            }
        }
        else
        {
            if (!keytrue && !getkey2)
            {
                System.out.println("Wrong room, no keys, this is a bit pathetic");
                wingame=false;
            }
            else
            {
                System.out.println("You are not even near the main door, find the main door to use your keys.");
                wingame=false;
            }
        }
    }
    
    public void chargephone()
    {
        boolean phonetrue = player.checkifphone();
        boolean chargertrue = player.checkifcharger();
        phonecharged = false;
        if (phonetrue && chargertrue)
        {
            System.out.println("The phone has been charged.");
            phonecharged = true;
            return;
        }
        else if(phonetrue && !chargertrue)
        {
            System.out.println("You do not have the charger, find charger to use it.");
            phonecharged = false;
        }
        else if(!phonetrue && chargertrue)
        {
            System.out.println("You do not have the phone, find the phone to charge it.");
            phonecharged = false;
        }
    }
    
    public void getsecondkey()
    {
        boolean checkifkeypad = player.checkifkeypad();
        boolean keytrue = player.checkifkey();
        String roomname = game.getroomname();
        if (!getkey2)
                if (roomname.equals( "closet"))
                {
                    if (!checkifkeypad && !keytrue)
                    {
                        System.out.println("The safe might hold the 'key' to getting out of this apartment, but the keypad seems to be missing...\nfind the keypad to use the safe and it doesn't take a genius to know you also need the pin.\nAlso the door works on 2 locks... so 2 keys.");
                        getkey2 = false;
                        return;
                    }
                    else if (!checkifkeypad && keytrue)
                    {
                        System.out.println("Okay, so you've got one of the keys... If you paid attention you'll know you need 2.\n IDK but isnt it blaringly obvoius to check out the safe?\n Find the godamn keypad and come back to use it");
                        getkey2 = false;
                        return;
                    }
                    else if (checkifkeypad && !keytrue)
                    {
                        System.out.println("Okay, amazing (not really), you've got the keypad, and lets hope you also got the pincode.\n Although.. 2 keys... '2' 'KEYS'\nSo get this key, and get the other key\nUse the keypad to open the safe.");
                        getkey2 = false;
                        return;
                    }
                    else
                    {
                        System.out.println("Use the keypad to open the safe");
                        return;
                    }
                }
        else
                {
                    System.out.println("The Safe is open, and you have the key.");
                }
    }        
    
    public void usekeypad()
    {
        boolean checkifkeypad = player.checkifkeypad();
        boolean keytrue = player.checkifkey();
        String roomname = game.getroomname();
        Scanner scanner = new Scanner(System.in);
        if (roomname .equals( "closet")) 
        {
            if (checkifkeypad)
            {
                System.out.println("Time to finally get the safe open, hope you've got the pincode with you.");
                System.out.println("Enter 4 digit pin code:  ");
                String safecode = scanner.nextLine();
                if (safecode .equals ("6969"))
                {
                    System.out.println("Pin correct");
                    getkey2 = true;
                    if (keytrue)
                    {
                        System.out.println("You now have both keys, time to make a run for the door.");
                    }
                    else if (!keytrue)
                    {
                        System.out.println("You now posses the secondary key, time to find the primary key to finally get the fuck out.");
                    }
                    else
                    {
                        return;
                    }
                }
                else
                {
                    System.out.println("Beep. Beep.");
                    System.out.println("Wrong bloody code, time to reasses how badly you wanna live.");
                    getkey2 = false;
                    if (keytrue)
                    {
                        System.out.println("Dont loose hope (just something people say). Atleast you have one key");
                    }
                    else if (!keytrue)
                    {
                        System.out.println("Wow, 0 keys, ICL this is a straight up absolute homicide inducing screw up on your part.\nThis isn't somewhere you want to hangout, find the keys... GTFO.");
                    }
                    else
                    {
                        return;
                    }
                }
            }
        }
    }
    
    public void usephone()
    {
        boolean phonetrue = player.checkifphone();
        Scanner scanner = new Scanner(System.in); 
        if (phonetrue && phonecharged)
        {
            System.out.println("The phone is locked, a 6 digit password is required, try to find this password.");
            System.out.println("Enter password: ");
            String password = scanner.nextLine();
            int tries = 4;
            if (password .equals("130804") && tries > 0)
            {
                System.out.println("The phone has been unlocked, quick, dial 999");
                System.out.println("Beep Beep Beep.......");
                System.out.println("The phone does not connect");
                System.out.println("Although, you find the safe's password, 6969, not very origional");
                return;
            }
            else if (tries > 0)
            {
                System.out.println("You have entered the wrong password.");
                tries--;
                System.out.println("You have " + tries + " more tries");
                return;
            }
            else if (tries == 0)
            {
                System.out.println("The phone is permanatly locked, too many wrong attempts");
                return;
            }
        }
        else
        {
            System.out.println("Charge the phone to use it");
            return;
        }
    }
    
    public void usediary()
    {
        System.out.println("My name is Patrick Bateman. I’m 27 years old. I believe in taking care of myself,\nand a balanced diet and a rigorous exercise routine.\n"+
        "There is an idea of a Patrick Bateman. Some kind of abstraction.\n"+
        "But there is no real me. Only an entity. Something illusory. And though I\n"+
        "can hide my cold gaze, and you can shake my hand and feel flesh\n"+
        "gripping yours, and maybe you can even sense our lifestyles are\n"+
        "probably comparable, I simply am not there.\n\n"+"Today I changed my phone's password to my birthday....\nI am beginning to forget things, i saved the safe's password on the phone today.\nI left the other key in the study.");
    }
    public void talktoallen()
    {
        System.out.println("Hi this is allen");
    }
    
    public void talktochristy()
    {
        System.out.println("Hi this is christy");
    }
    
    public void seeid()
    {
        System.out.println("The name and picture are scrated out, the address reads:\n'11th floor, 55 West 81st Street\nMore importantly, a birthday, 13.08.04....");
        return;
    }
}

