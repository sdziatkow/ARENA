package item;

import java.util.ArrayList;
import arenaEnum.itemInfo.ItemType;
import arenaPerson.ArenaPerson;

/**
 * Program Name:    Item.java
 *<p>
 * Purpose:         The purpose of this program is to create a base class for all
 * 					ARENA items.
 *<p>
 * @version         0.0
 *<p>
 * Created:         March 06, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

public abstract class Item{
    /**
     *
    */

    // Constant containing the type of the item (WEAPON, ARMOR, MISC)
    private ItemType itemType;

    // String containing the name of the item.
    private String name;
    
    // Array list containing info for display.
    private ArrayList<String> info;
    
    // The character that has this item equipped.
    private ArenaPerson self;

//CONSTRUCTORS---------------------------------------------------------------------
    
    public Item(ItemType itemType) {
    	
        this.itemType = itemType;
        this.info = new ArrayList<String>();
        this.self = null;
    }

//SETTERS--------------------------------------------------------------------------
    
    public abstract void setInfo();
    
    public void setName(String name) {
    	/**
    	 * 
    	*/
    	
    	this.name = name;
    }
    
    public void setSelf(ArenaPerson self) {
    	/*
    	 * 
    	*/
    	
    	this.self = self;
    }
    
//GETTERS--------------------------------------------------------------------------
    
    public ItemType getItemType() {
    	/**
    	 * 
    	*/
    	
    	return itemType;
    }
    
    public String getName() {
    	/**
    	 * 
    	*/
    	
    	return name;
    }
    
    public ArrayList<String> getInfo() {
    	/**
    	 * Name will always be at index zero.
    	 * Starting at 1 will be key (1) : value (2).
    	 * 
    	 * @return: An ArrayList of information for display.
    	*/
    	
    	return info;
    }
    
    public ArenaPerson getSelf() {
    	/*
    	 * 
    	*/
    	
    	return self;
    }

}

