package backpack;

/**
 * Program Name:    EquipSlots.java
 *<p>
 * Purpose:         The purpose of this program is to create a place for
 * 					ArenaCharacters to use certain items from Backpack.
 *<p>
 * @version         0.0
 *<p>
 * Created:         March 06, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import arenaCharacter.*;
import item.*;
import item.Item.ItemType;
import item.weapon.*;

public class EquipSlots{
    /**
     * class EquipSlots:
     *  Used by class ArenaCharacter in conjunction with class Backpack. Items
     *  can be equipped from an ArenaCharacter's backpack field. Equipped items
     *  can alter the stats of ArenaCharacters and can be activley used in
     *  different ways.
     *
    */

    private ArenaCharacter self;
    private Backpack backpack;

    // Default weapon is hands.
    private Weapon defaultWeapon = new Martial();
    private Weapon weapon;

//CONSTRUCTORS---------------------------------------------------------------------
    
    public EquipSlots() {
        /**
         * Default Constructor for class
        */

        this.self = new Player();
        this.backpack = new Backpack();
        
        this.weapon = defaultWeapon;

        
    }

    public EquipSlots(ArenaCharacter self, Backpack backpack) {
        /**
         * Default Constructor for class
        */

        this.self = self;
        this.backpack = backpack;

        this.weapon = defaultWeapon;

        setWeapon(this.weapon);

    }

//SETTERS--------------------------------------------------------------------------

    public void setWeapon(Weapon weapon) {
        /**
         * Setter for field: weapon
        */

        // Set the field self of current weapon to null.
        this.weapon.setSelf(null);

        // Set the current weapon to the received weapon.
        this.weapon = weapon;

        // Set the (possibly) new weapon's self field to the EquipSlots' self
        // field.
        this.weapon.setSelf(getSelf());
    }

//GETTERS--------------------------------------------------------------------------

    public Weapon getDefaultWeapon() {
        /**
         * Getter for field: weapon
        */

        return defaultWeapon;
    }

    public Weapon getWeapon() {
        /**
         * Getter for field: weapon
        */

        return weapon;
    }

    public ArenaCharacter getSelf() {
        /**
         *
        */

        return self;
    }

    public Backpack bp() {
        /**
         * Getter for field: backpack
        */

        return backpack;
    }

//MANIPULATION---------------------------------------------------------------------

    private boolean isEquipped(Item item) {
        /**
         * This method will return true if Item item is currently equipped.
        */

        boolean equipped;

        // Equipped is only true if the received item's name is equal to the
        // currently equipped item.
        if (item.getName().equals(getWeapon().getName())) {
            equipped = true;
        }
        else {
            equipped = false;
        }

        return equipped;
    }

    public void equipWeapon(int itemSlot) {
        /**
         *
        */

        bp().look(ItemType.WEAPON);
        
        // pre-select the item slot to get the item from.
        bp().setItemSlot(itemSlot);


        // Check if item is not equipped.
        if ( !(isEquipped(bp().grabItem())) ) {

            // cast bp.grabItem as a Weapon in order to set it as equipped
            // weapon properly. Finally set the self of the Weapon to
            // the ArenaCharacter that has the EquipSlots
            setWeapon((Weapon)(bp().grabItem()));
            getWeapon().setSelf(getSelf());
        }
    }

    public void unEquipWeapon() {
        /**
         *
        */

        setWeapon(getDefaultWeapon());
    }

//INFO-----------------------------------------------------------------------------
    
    public String toString() {
    	/**
    	 * 
    	*/
    	
    	String equipStr;
    	
    	equipStr = "----------------------v-equipped-v-------\n";
    	equipStr += String.format("WEAPON: %s\n", getWeapon().getName());
    	
    	return equipStr;
    }

}
