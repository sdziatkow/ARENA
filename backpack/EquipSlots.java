package backpack;

import java.util.ArrayList;

import arenaPerson.*;
import item.*;
import arenaEnum.itemInfo.ItemType;
import item.weapon.*;
import item.armor.*;
import item.useable.Useable;

public class EquipSlots{
    /**
     * class EquipSlots:
     *  Used by class ArenaCharacter in conjunction with class Backpack. Items
     *  can be equipped from an ArenaCharacter's backpack field. Equipped items
     *  can alter the stats of ArenaCharacters and can be activley used in
     *  different ways.
     *
    */
	
	private final int WEAPON_SLOT = 0;
	private final int ARMOR_SLOT = 1;
	private final int USEABLE_SLOT = 2;

    private ArenaPerson self;
    
    private ArrayList<Item> equipped;

    // Default items.
    private Weapon defaultWeapon = new Martial();
    private Armor defaultArmor = new ClothClothes();
    private Useable defaultUseable = null;
    

//CONSTRUCTORS---------------------------------------------------------------------
    
    public EquipSlots() {
        /**
         * Default Constructor for class
        */

        this.self = new ArenaPlayer();
        this.equipped = new ArrayList<Item>();
    }

    public EquipSlots(ArenaPerson self) {
        /**
         * Default Constructor for class
        */

        this.self = self;

        equipped = new ArrayList<Item>();
        defaultWeapon.setSelf(getSelf());
        defaultArmor.setSelf(getSelf());
        equipped.add(WEAPON_SLOT, defaultWeapon);
        equipped.add(ARMOR_SLOT, defaultArmor);
        equipped.add(USEABLE_SLOT, defaultUseable);
    }

//GETTERS--------------------------------------------------------------------------

    public ArenaPerson getSelf() {
        /**
         *
        */

        return self;
    }
    
    public ArrayList<Item> getEquipped() {
    	/*
    	 * 
    	*/
    	
    	return equipped;
    }
    
    public Item getEquipped(ItemType type) {
    	/*
    	 * 
    	*/
    	
    	switch (type) {
    	case WEAPON: return equipped.get(WEAPON_SLOT);
    	case ARMOR: return equipped.get(ARMOR_SLOT);
    	case USEABLE: return equipped.get(USEABLE_SLOT);
		default: return null;
    	}
    }

//MANIPULATION---------------------------------------------------------------------

    public boolean isEquipped(Item item) {
        /**
         * This method will return true if Item item is currently equipped.
        */

    	boolean isEq;
    	
        // Equipped is only true if the received item's name is equal to the
        // currently equipped item.
        
    	isEq = false;
        for (int i = 0; i < equipped.size() && !isEq; ++i) {
        	
        	if (equipped.get(i) != null && item != null) {
        		isEq = equipped.get(i).getName().equals(item.getName());
        	}
        }

        return isEq;
    }

    public void equipItem(Item item) {
        /**
         *
        */


        // Check if item is not equipped.
        if ( !isEquipped(item) ) {
        	
        	if (getEquipped(item.getItemType()) != null) {
        		
        		if (!item.getItemType().equals(ItemType.USEABLE)) {
        			getEquipped(item.getItemType()).setSelf(null);
        		}
        	}
        	
        	switch (item.getItemType()) {
        	case WEAPON: getEquipped().set(WEAPON_SLOT, item);
        		break;
        	case ARMOR: getEquipped().set(ARMOR_SLOT, item);
        		break;
        	case USEABLE: getEquipped().set(USEABLE_SLOT, item);
        		break;
    		default:
    			break;
        	}
        	
        	if (getEquipped(item.getItemType()) != null) {
        		getEquipped(item.getItemType()).setSelf(getSelf());
        	}
        }
    }

    public void unEquipItem(ItemType type) {
        /**
         *
        */
    	
    	if (getEquipped(type) != null) {
    		
    		if (!type.equals(ItemType.USEABLE)) {
    			getEquipped(type).setSelf(null);
    		}
    	}
    	
    	switch (type) {
    	case WEAPON: equipped.set(WEAPON_SLOT, defaultWeapon);
    		break;
    	case ARMOR: equipped.set(ARMOR_SLOT, defaultArmor);
    		break;
    	case USEABLE: equipped.set(USEABLE_SLOT, defaultUseable);
    		break;
    	default:
    		break;
    	}
    	
    	if (getEquipped(type) != null) {
        	getEquipped(type).setSelf(getSelf());
    	}
    }

}
