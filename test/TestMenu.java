package test;
/**
 * Program Name:    TestMenu.java
 *<p>
 * Purpose:         The purpose of this program is to
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 07, YYY
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import arenaCharacter.*;
import arenaCharacter.ArenaCharacter.CharClass;
import arenaCharacter.Stat.StatType;
import backpack.*;
import item.*;
import item.Item.ItemType;
import item.useable.StatPot;
import item.useable.Useable;
import item.weapon.BronzeHandAxe;
import item.weapon.CrystalSceptre;
import item.weapon.SteelGreatAxe;
import item.weapon.SteelRapier;
import item.weapon.WoodenBow;

import java.io.StringWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;

public class TestMenu{
    /**
     * CHANGE ALL PRINT-STREAMS TO NORMAL STRINGS EVERYWHERE
    */
	
		public static ArenaCharacter chooseClass(int userIn) {
			/**
			 * 
			*/
			
			ArenaCharacter player;
			
			switch (userIn) {
			
			case 0:
				player = new Player(CharClass.BRUTE);
				player.bp().addItem(new SteelGreatAxe());
				player.bp().addItem(new StatPot(1, StatType.HP, 50.0));
				break;
				
			case 1:
				player = new Player(CharClass.BARBARIAN);
				player.bp().addItem(new BronzeHandAxe());
				player.bp().addItem(new StatPot(1, StatType.HP, 50.0));
				break;
				
			case 2:
				player = new Player(CharClass.DUELIST);
				player.bp().addItem(new SteelRapier());
				player.bp().addItem(new StatPot(1, StatType.SP, 50.0));
				break;
				
			case 3:
				player = new Player(CharClass.RANGER);
				player.bp().addItem(new WoodenBow());
				player.bp().addItem(new StatPot(1, StatType.SP, 50.0));
				break;
				
			case 4:
				player = new Player(CharClass.MONK);
				player.bp().addItem(new StatPot(1, StatType.MP, 50.0));
				player.bp().addItem(new StatPot(1, StatType.SP, 50.0));
				break;
				
			case 5:
				player = new Player(CharClass.MAGE);
				player.bp().addItem(new CrystalSceptre());
				player.bp().addItem(new StatPot(1, StatType.MP, 50.0));
				break;
				
			default:
				player = new Player();
				break;
			}
			
			return player;
		}
		
		public static void mainMenu(
				ArenaCharacter player,
				Scanner scnr
				)
		{
			/**
			 * 
			*/
			
			String menu;
			
			boolean stillLooking;
			int userChoice;
			
	    	menu = ("      -----MAIN-MENU-----\n");
	    	menu += ("0  - View player info\n");
	    	menu += ("1  - Manage items\n");
	    	menu += ("2  - Quit\n");
	    	menu += ("Choose an option: \n");
	    	
	    	System.out.println(menu);
	    	userChoice = scnr.nextInt();
	    	
	    	stillLooking = true;
	    	while (stillLooking) {
	    		
	    		switch (userChoice) {
	    		
	    		case 0:
	    			playerInfoMenu(player, scnr);
	    			break;
	    			
	    		case 1:
	    			itemMenu(player, scnr);
	    			break;
	    			
	    		case 2:
	    			stillLooking = false;
	    			break;
	    			
	    		default:
	    			break;
	    		}
	    		
	    		if (stillLooking) {
			    	System.out.println(menu);
			    	userChoice = scnr.nextInt();
	    		}
	    		
	    	}
	    	
		}
		
		public static void playerInfoMenu(
				ArenaCharacter player,
				Scanner scnr
				) 
		{
			/**
			 * 
			*/
			
			String menu;
			
			boolean stillLooking;
			int userChoice;
			
	    	menu = ("      -----PLAYER-INFO-----\n");
	    	menu += ("0  - All Info\n");
	    	menu += ("1  - General info\n");
	    	menu += ("2  - Attribute info\n");
	    	menu += ("3  - Stat info\n");
	    	menu += ("4  - Backpack info\n");
	    	menu += ("5  - Equip info\n");
	    	menu += ("6  - Back to main menu\n");
	    	menu += ("Choose an option: \n");
	    	
	    	System.out.println(menu);
	    	userChoice = scnr.nextInt();
	    	
	    	stillLooking = true;
	    	while (stillLooking) {
	    		
	    		switch (userChoice) {
	    		
	    		case 0:
	    			System.out.println(player.allInfoString());
	    			break;
	    			
	    		case 1:
	    			System.out.println(player.generalInfoString());
	    			break;
	    			
	    		case 2:
	    			System.out.println(player.attrString());
	    			break;
	    			
	    		case 3:
	    			System.out.println(player.hpMpSpString());
	    			break;
	    			
	    		case 4:
	    			System.out.println(player.bp().toString());
	    			break;
	    			
	    		case 5:
	    			System.out.println(player.equipSlot().toString());
	    			break;
	    			
	    		case 6:
	    			stillLooking = false;
	    			break;
	    			
	    		default:
	    			break;
	    		}
	    		
	    		if (stillLooking) {
			    	System.out.println(menu);
			    	userChoice = scnr.nextInt();
	    		}
	    		
	    	}
	    	
			
		}

		public static void itemMenu(
				ArenaCharacter player,
				Scanner scnr
				) 
		{
			/**
			 * 
			*/
			
			String menu;
			String line = 
	    	"--------------------------------------------------------------------"	
	    	;
			
			boolean stillLooking;
			int userChoice;
			
	    	menu = ("      -----ITEM-MENU-----\n");
	    	menu += player.bp().toString();
	    	menu += player.equipSlot().toString();
	    	menu += line + "\n";
	    	menu += ("0  - Use item\n");
	    	menu += ("1  - Equip item\n");
	    	menu += ("2  - Remove item\n");
	    	menu += ("3  - Back to main menu\n");
	    	menu += ("Choose an option: \n");
	    	
	    	System.out.println(menu);
	    	userChoice = scnr.nextInt();
	    	
	    	stillLooking = true;
	    	while (stillLooking) {
	    		
	    		switch (userChoice) {
	    		
	    		// Use item
	    		case 0:
	    			
	    			// Display all useables and label them for use.
	    			for (int i = 0; i < player.bp().getUseable().size(); ++i) {
	    				System.out.printf("%d.) %s - x%d\n", 
    						i, 
    						player.bp().getUseable().get(i).getName(),
    						((Useable)player.bp().getUseable().get(i)).getAmnt()
    						)
	    				;
	    			}
	    			System.out.printf("%d.) %s\n", 
	    					player.bp().getUseable().size(),
	    					"CANCEL"
	    					)
	    			;
	    			
	    			// Get user input and use correct item.
	    			System.out.println("Enter item to use: ");
	    			userChoice = scnr.nextInt();
	    			
	    			player.bp().look(ItemType.USEABLE);
	    			player.bp().setItemSlot(userChoice);
	    			if (!(userChoice >= player.bp().getUseable().size())) {
	    				((Useable)player.bp().grabItem()).use();
	    			}
	    			break;
	    		
	    		// Equip item
	    		case 1:
	    			System.out.printf("%d.) %s\n",
	    					0,
	    					player.bp().getWeapon().get(0).getName()
	    					)
	    			;
	    			System.out.printf("%d.) %s\n", 
	    					player.bp().getWeapon().size(),
	    					"CANCEL"
	    					)
	    			;
	    			System.out.println("Enter item to equip: ");
	    			userChoice = scnr.nextInt();
	    			
	    			if (userChoice == 0) {
	    				player.equipSlot().equipWeapon(0);
	    			}
	    			
	    			break;
	    		
	    		// Remove item.
	    		case 2:
	    			System.out.println(player.attrString());
	    			break;
	    			
	    		case 3:
	    			stillLooking = false;
	    			break;
	    			
	    		default:
	    			break;
	    		}
	    		
	    		if (stillLooking) {
	    			String tempMenu;
	    			String startMenu;
	    			String endMenu;
	    			String midMenu;
	    			
	    			tempMenu = menu;
	    			startMenu = tempMenu.substring(0, 25) + "\n";
	    			midMenu = (player.bp().toString() + 
	    					player.equipSlot().toString() +
	    					line + "\n"
	    					)
	    			;
	    			endMenu = tempMenu.substring(menu.indexOf('0'));
	    			
	    			tempMenu = startMenu + midMenu + endMenu;

			    	System.out.println(tempMenu);
			    	userChoice = scnr.nextInt();
	    		}
	    		
	    	}
	    	
			
		}

    public static void main(String [] args) throws IOException{
        /**
         *
        */
    	
    	// Used for getting input from user.
    	Scanner scnr = new Scanner(System.in);
    	String userIn;
    	int    userChoice;
    	
    	// Used for displaying info.
    	String introTxt;
    	
    	// Disp intro.
    	introTxt =  String.format("%8s %3s", CharClass.BRUTE, "|");
    	introTxt += String.format("%10s %1s", CharClass.BARBARIAN, "|");
    	introTxt += String.format("%9s %2s", CharClass.DUELIST, "|");
    	introTxt += String.format("%8s %3s", CharClass.RANGER, "|");
    	introTxt += String.format("%7s %4s", CharClass.MONK, "|");
    	introTxt += String.format("%7s %4s\n", CharClass.MAGE, "|");
    	
    	for (int i = 0; i < 6; ++i) {
    		introTxt += String.format("%6d %5s", i, "|");
    	}
    	introTxt += "\n";
    	introTxt += (
    	"------------------------------------------------------------------------"
    	+ "\n");
    	introTxt += ("Choose a class: \n");
    	System.out.println(introTxt);
    	
    	// Create new player.
    	userChoice = scnr.nextInt();
    	ArenaCharacter p1 = new Player();
    	
    	if (userChoice > -1 && userChoice < 6) {
    		p1 = chooseClass(userChoice);
    	}
    	
    	// Enter player name.
    	System.out.println();
    	System.out.println("Enter name: ");
    	userIn = scnr.next();
    	p1.setName(userIn);
    	
    	// Enter Menu.
    	p1.stat(StatType.HP).dmg(37.3256);
    	mainMenu(p1, scnr);
    	
    	scnr.close();
    	
    	
    }

}
