package item;

/**
 * Program Name:    Item.java
 *<p>
 * Purpose:         The purpose of this program is to
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

	public enum ItemType {
		/**
		 * 
		*/
		
		USEABLE,
		WEAPON,
		ARMOR;
	}
    // Constant containing the type of the item (WEAPON, ARMOR, MISC)
    private ItemType itemType;

    // String containing the name of the item.
    private String name;

//CONSTRUCTORS---------------------------------------------------------------------
    
    public Item(ItemType itemType) {
    	
        this.itemType = itemType;
    }

//SETTERS--------------------------------------------------------------------------
    
    public void setName(String name) {
    	/**
    	 * 
    	*/
    	
    	this.name = name;
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

}

