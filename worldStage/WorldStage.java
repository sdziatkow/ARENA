package worldStage;

/**
 * Program Name:    WorldStage.java
 *<p>
 * Purpose:         The purpose of this program is to create a system for all 
 * 					stages to use to build a world for ARENA.
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 18, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import arenaCharacter.ArenaCharacter;
import collision.CollisionBox;
import item.Item;
import javafx.scene.Group;
import window.Main;

public abstract class WorldStage{
    /**
     *
    */
	
	private Main window;
	
	private ArenaCharacter player;
	private ArenaCharacter[] npcs;
	private Item[] items;
	
	
	private Group background;
	private Group worldSpace;

//CONSTRUCTORS---------------------------------------------------------------------

    public WorldStage(Main window, int totalNpcs, int totalItems) {
        /**
         * Default Constructor for class
        */
    	
    	this.window = window;
    	this.npcs = new ArenaCharacter[totalNpcs];
    	this.items = new Item[totalItems];
    	
    	background = new Group();
    	worldSpace = new Group();
    	
    	background.setCache(true);
    	worldSpace.setCache(true);
    }

//SETTERS--------------------------------------------------------------------------

    public void setPlayer(ArenaCharacter player) {
        /**
         * Setter for field: player
        */
    	
    	this.player = player;
    }
    
    public void setNpc(int index, ArenaCharacter npc) {
        /**
         * Setter for field: npcs[index]
        */
    	
    	this.npcs[index] = npc;
    }
    
    public void setItem(int index, Item item) {
    	/**
    	 * Setter for field: items[index]
    	*/
    	
    	this.items[index] = item;
    }
    
    public void setBackground(Group background) {
    	/**
    	 * Setter for field: background
    	*/
    	
    	this.background = background;
    	background.setCache(true);
    }
    
    public void setWorldSpace(Group arenaChars) {
    	/**
    	 * Setter for field: arenaChars
    	*/
    	
    	this.worldSpace = arenaChars;
    	worldSpace.setCache(true);
    }

//GETTERS--------------------------------------------------------------------------
    
    public Main getWindow() {
    	/**
    	 * 
    	*/
    	
    	return window;
    }

    public ArenaCharacter getPlayer() {
        /**
         * Getter for field: player
        */
    	
    	return player;
    }
    
    public ArenaCharacter[] getNpc() {
    	/**
    	 * Getter for field: npcs
    	*/
    	
    	return npcs;
    }
    
    public Item[] getItem() {
    	/**
    	 * Getter for field: items
    	*/
    	
    	return items;
    }
    
    public Group getBackground() {
    	/**
    	 * Getter for field: background
    	*/
    	
    	return background;
    }
    
    public Group getWorldSpace() {
    	/**
    	 * Getter for field: arenaChars
    	*/
    	
    	return worldSpace;
    }
    
    public abstract CollisionBox[] getWorldBoxes();
    public abstract CollisionBox[] getHurtBoxes();
    public abstract CollisionBox[] getHitBoxes();
    
//ON-LAUNCH------------------------------------------------------------------------
    
    public abstract void onLaunch();
    public abstract void updateViewOrder();
    public abstract void runPlayerStates();
    public abstract void runNpcStates();
    public abstract void pause();
    public abstract void save();

}