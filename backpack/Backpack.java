package backpack;

/**
 * Program Name:    Backpack.java
 *<p>
 * Purpose:         The purpose of this program is to have a place for
 * 					ArenaCharacters and world objects to store Items.
 *<p>
 * @version         0.0
 *<p>
 * Created:         March 06, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import java.util.ArrayList;

import arenaEnum.itemInfo.ItemType;
import arenaPerson.ArenaPerson;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import item.*;
import item.armor.Armor;
import item.useable.Useable;
import item.weapon.Weapon;


public class Backpack{
    /**
     *
    */
	
	// Maximum storage for each backpack slot. (U)seable; (W)eapon; (A)rmor
	public final int U_MAX = 10;
	public final int W_MAX = 10;
	public final int A_MAX = 10;
	
	// Used to store items of class Item and sublcasses of Item.
	private ArrayList<Item> useable;
	private ArrayList<Item> weapon;
	private ArrayList<Item> armor;
	private ArrayList<ArrayList<Item>> allItems;

    // The index of the Item being manipulated.
    private int itemSlot;

    // Will be used to store an Item being manipulated.
    private Item tempItem;

    // Will be used to check the validity of certain manipulations.
    private ItemType currLooking;
    
    // The character that the backpack belongs to (optional)
    private ArenaPerson self;

//CONSTRUCTORS---------------------------------------------------------------------

    public Backpack() {
        /**
         *
        */
    	
    	useable = new ArrayList<Item>();
    	weapon = new ArrayList<Item>();
    	armor = new ArrayList<Item>();
    }
    
	public Backpack(ArenaPerson self) {
	    /**
	     *
	    */
	
    	useable = new ArrayList<Item>();
    	weapon = new ArrayList<Item>();
    	armor = new ArrayList<Item>();
    	allItems = new ArrayList<ArrayList<Item>>();
    	allItems.add(weapon);
    	allItems.add(armor);
    	allItems.add(useable);
	    
	    this.self = self;
	}

//SETTERS--------------------------------------------------------------------------
	
    public void getSelf(ArenaPerson self) {
        /**
         *
        */

        this.self = self;
    }

    public void look(ItemType itemType) {
        /**
         * Will set the current item type that is being manipulated.
        */

        switch (itemType) {
        	case ItemType.USEABLE:
        		currLooking = itemType;
        		break;
            case ItemType.WEAPON:
                currLooking = itemType;
                break;
            case ItemType.ARMOR:
            	currLooking = itemType;
                break;
        }
    }

    public void setItemSlot(int itemSlot) {
        /**
         * Setter for field: itemSlot
        */

        this.itemSlot = itemSlot;
    }

    public void setTempItem(Item tempItem) {
        /**
         * Setter for field: tempItem
        */

        this.tempItem = tempItem;
    }

//GETTERS--------------------------------------------------------------------------
    
    public ItemType lookingAt() {
    	/**
    	 * Will return the current item type that is being manipulated.
    	*/
    	
    	return currLooking;
    }
    
    public ArrayList<Item> getUseable() {
        /**
         * Getter for field: useable
        */

        return useable;
    }

    public ArrayList<Item> getWeapon() {
        /**
         * Getter for field: weapon
        */

        return weapon;
    }
    
    public ArrayList<Item> getArmor() {
        /**
         * Getter for field: armo
        */

        return armor;
    }
    
    public ArrayList<ArrayList<Item>> getAllItems() {
    	/*
    	 * 
    	*/
    	
    	return allItems;
    }

    public int getItemSlot() {
        /**
         * Getter for field: itemSlot
        */

        return itemSlot;
    }

    public Item getTempItem() {
        /**
         * Getter for field: tempItem
        */

        return tempItem;
    }

//FLAGS----------------------------------------------------------------------------
    
    public boolean hasItem() {
    	/**
    	 * MUST BE LOOKING AT ITEM-TYPE | TEMP-ITEM MUST BE SET
    	*/
    	
    	// Will be set dependent on lookingAt() -> ItemType
    	ArrayList<Item> tempSlot;
    	int size;
    	
    	boolean inBp;
    	
    	// Set tempSlot to manipulate and size to iterate through it.
    	switch (lookingAt()) {
    	case USEABLE:
    		tempSlot = getUseable();
    		size = getUseable().size();
    		break;
    	case WEAPON:
    		tempSlot = getWeapon();
    		size = getWeapon().size();
    		break;
    	case ARMOR:
    		tempSlot = getArmor();
    		size = getArmor().size();
    		break;
    	default:
    		tempSlot = null;
    		size = 0;
    	}
    	
    	// Iterate through tempSlot.
    	inBp = false;
        for (int item = 0; item < size && !inBp; ++item) {
            	
            	// If the pre-set tempItem's name is equal to the previously 
            	// checked item.
                if (getTempItem().getName().equals(tempSlot.get(item).getName())){
                	inBp = true;
                }
        }
    	
    	return inBp;
    }
    
    public boolean isFull() {
    	/**
    	 * 
    	*/
    	
    	boolean full;
    	
    	// Check if the size of the ArrayList dependent on lookingAt() is 
    	// greater than or equal to its respective MAX value.
    	switch (lookingAt()) {
    	
    	case USEABLE:
    		full = getUseable().size() >= U_MAX;
    		break;
    		
    	case WEAPON:
    		full = getWeapon().size() >= W_MAX;
    		break;
    		
    	case ARMOR:
    		full = getArmor().size() >= A_MAX;
    		break;
    		
    	default:
    		full = false;
    	}
    	
    	return full;
    }
    
    public boolean isSameType() {
    	/**
    	 * 
    	*/
    	
    	boolean sameType;
    	
    	// If ItemType of tempItem is equal to the ItemType being looked at.
    	sameType = getTempItem().getItemType().equals(lookingAt());
    	
    	return sameType;
    }

//MANIPULATE-BP--------------------------------------------------------------------

    public void clearTemp() {
        /**
         * This method will set field tempItem to null.
        */

        setTempItem(null);
        setItemSlot(0);
    }
    
    private int indexOfItem() {
    	/**
    	 * This method will return the index of the element in ArrayList that is 
    	 * equal to the currently set tempItem
    	*/
    	
    	// Will be determined by lookingAt()
    	ArrayList<Item> tempSlot;
    	
    	// Used to iterate through tempSlot
    	boolean itemFound;
    	int size;
    	int index;
    	
    	// Set tempSlot and size dependent on lookingAt() -> ItemType
    	switch (lookingAt()) {
    	case USEABLE:
    		tempSlot = getUseable();
    		size = getUseable().size();
    		break;
    	case WEAPON:
    		tempSlot = getWeapon();
    		size = getWeapon().size();
    		break;
    	case ARMOR:
    		tempSlot = getArmor();
    		size = getArmor().size();
    		break;
    	default:
    		tempSlot = null;
    		size = 0;
    	}
    	
    	// Iterate through tempSlot.
    	itemFound = false;
    	index = -1;
        for (int item = 0; item < size && !itemFound; ++item) {
            	
        	// If the pre-set tempItem's name is equal to the previously 
        	// checked item.
            if (getTempItem().getName().equals(tempSlot.get(item).getName())) {
            	index = item;
            	itemFound = true;
            }
        }
    	
    	return index;
    }

    private boolean isValidAdd() {
        /**
         * This method will return true if field bpSlot is:
         * (not full AND of the same type as field tempItem) AND
         * (does not already have item)
        */
    	
    	boolean valid;

        valid = ( !(isFull()) && isSameType() ) && !(hasItem());
      
        return valid;
    }

    public Item grabItem() {
        /**
         * Returns the currently selected item at itemSlot of current bpSlot
         * ITEM-SLOT MUST BE SET
        */

    	// Determined by lookingAt()
        Item item;
        
        // Set item dependent on lookingAt() -> ItemTypes
        switch (lookingAt()) {
        case USEABLE:
        	item = getUseable().get(getItemSlot());
        	break;
        case WEAPON:
        	item = getWeapon().get(getItemSlot());
        	break;
        case ARMOR:
        	item = getArmor().get(getItemSlot());
        	break;
        default:
        	item = null;
        }
        
        return item;
    }
    
    public Item grabItemByName(String name) {
    	/**
    	 * @return: Item that has field name equal to the given String. 
    	*/

    	ArrayList<Item> currList;
    	for (int t = 0; t < getAllItems().size(); ++t) {
    		
    		currList = getAllItems().get(t);
    		for (int i = 0; i < currList.size(); ++i) {
    			if (currList.get(i).getName().equals(name)) {
    				return currList.get(i);
    			}
    		}
    	}
    	
    	return null;
    }
    
    public void addItem(Item item) {
        /**
         *
        */
    	
    	// Set tempItem to the given item and look at its ItemType
    	setTempItem(item);
    	look(getTempItem().getItemType());
    	
        
        switch (lookingAt()) {
        
        case USEABLE:
        	
        	// If item exists in ArrayList useable, addExistingItem()
        	if (hasItem()) {
        		addExistingItem();
        	}
        	
        	// Otherwise add it to ArrayList useable if it is a valid add
        	else if (isValidAdd()) {
        		
        		// Set Useable's self field to the bp's self field
        		getTempItem().setSelf(self);
        		getUseable().add((Useable)getTempItem());
        	}
        	break;
        	
        case WEAPON:
        	
        	// If valid add, add the item to ArrayList weapon
        	if (isValidAdd()) {
        		getWeapon().add((Weapon)getTempItem());
        	}
        	break;
        	
        case ARMOR:
        	
        	// Same as WEAPON
        	if (isValidAdd()) {
        		getArmor().add((Armor)getTempItem());
        	}
        	break;
        	
        default:
        }
        
        clearTemp();
    }
    
    private void addExistingItem() {
    	/**
    	 * Should only be used for ItemType.USEABLE
    	*/
    	
    	// Inc amnt of Useable by amnt of tempItem Useable
    	( (Useable) getUseable().get(indexOfItem()) ).incAmnt(
    										( (Useable) getTempItem() ).getAmnt()
    									    )
    	;
    	
    }
    
    public void removeItem(Item item) {
        /**
         * This method will remove an item given the item itself.
        */
    	
    	// Will be determined by lookingAt()
    	ArrayList<Item> tempSlot;
    	
    	// Set tempItem and look at its ItemType
        setTempItem(item);
        look(getTempItem().getItemType());
        
        // If item is in bp
        if (hasItem()) {
        	
            // Set tempSlot dependent on lookingAt() -> ItemType
            // Remove the item and trim ArrayList to size
		    switch (lookingAt()) {
		    
		    case USEABLE:
		    	removeExistingItem();
		    	break;
		    	
		    case WEAPON:
		    	tempSlot = getWeapon();
			    tempSlot.remove(indexOfItem());
			    tempSlot.trimToSize();
		    	break;
		    	
		    case ARMOR:
		    	tempSlot = getArmor();
			    tempSlot.remove(indexOfItem());
			    tempSlot.trimToSize();
		    	break;
		    	
		    default:
		    	tempSlot = null;
		    }
		   
        }
    }
    
    public void removeItem(ItemType type, int itemSlot) {
        /**
         * This method will remove an item given ItemType and index(itemSlot).
        */
    	
    	// Look at given ItemType and set itemSlot
        look(type);
        setItemSlot(itemSlot);
        
        // Grab the item and set it to tempItem.
        setTempItem(grabItem());
        
        // Call removeItem(Item) to remove the item.
        removeItem(getTempItem());
        
    }
    
    private void removeExistingItem() {
    	/**
    	 * SHOULD ONLY BE USED WITH ItemType.USEABLE 
    	*/
    	
    	// Dec amnt of Useable in bp by one
    	((Useable)getUseable().get(indexOfItem())).decAmnt();
    	
    	// If amnt is now zero, remove from ArrayList and trim to size.
    	if ( ((Useable)getUseable().get(indexOfItem())).getAmnt() < 1 ) {
    		getUseable().remove(indexOfItem());
    		getUseable().trimToSize();
    	}
    	
    }
    
//DISPLAY--------------------------------------------------------------------------
    
    public String toString() {
    	/**
    	 * A String displaying the contents of the bp.
    	*/
    	
    	StringWriter oSS = new StringWriter();
    	PrintWriter  inSS  = new PrintWriter(oSS);
    	
    	inSS.print("USEABLES: ");
    	for (int useable = 0; useable < getUseable().size(); ++useable) {
    		if (getUseable().get(useable) != null) {
    			inSS.printf("%s -- x%d %s ",
    					getUseable().get(useable).getName(), 
    					( (Useable)getUseable().get(useable) ).getAmnt(),
    					"|"
    					)
    			;
    		}
    	}
    	inSS.println();
    	
    	inSS.print("WEAPONS: ");
    	for (int weapon = 0; weapon < getWeapon().size(); ++weapon) {
    		if (getWeapon().get(weapon) != null) {
    			inSS.print(getWeapon().get(weapon).getName() + " | ");
    		}
    	}
    	inSS.println();
    	
    	inSS.print("Armor: ");
    	for (int armor = 0; armor < getArmor().size(); ++armor) {
    		if (getArmor().get(armor) != null) {
    			inSS.print(getArmor().get(armor).getName() + " | ");
    		}
    	}
    	inSS.println();
    	
    	try {
		    inSS.close();
			oSS.close();
    	}
    	catch (IOException e) {
    		
    	}
    	
    	return oSS.toString();
    }

}
