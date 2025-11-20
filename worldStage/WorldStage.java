package worldStage;

import java.util.ArrayList;

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
import item.Item.ItemType;
import item.useable.Useable;
import item.weapon.Weapon;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import ui.invMenu.InventoryEvent;
import ui.invMenu.InventoryMenu;
import ui.overlay.Overlay;
import ui.statMenu.StatEvent;
import ui.statMenu.StatMenu;
import window.Main;

public abstract class WorldStage{
    /**
     *
    */
	
	private Main window;
	
	private ArenaCharacter player;
	private ArenaCharacter[] npcs;
	private ArenaCharacter[] allChars;
	private Item[] items;
	
	
	private Group background;
	private Group worldSpace;
	private Overlay overlay;
	
	private InventoryEvent iEvent;
	private InventoryMenu iMenu;
	private StatEvent sEvent;
	private StatMenu sMenu;

//CONSTRUCTORS---------------------------------------------------------------------

    public WorldStage(Main window, int totalNpcs, int totalItems) {
        /**
         * Default Constructor for class
        */
    	
    	this.window = window;
    	this.npcs = new ArenaCharacter[totalNpcs];
    	this.items = new Item[totalItems];
    	this.allChars = new ArenaCharacter[totalNpcs + 1];
    	
    	background = new Group();
    	worldSpace = new Group();
    	overlay = new Overlay();
    	
    	iMenu = new InventoryMenu();
    	sMenu = new StatMenu();
    	
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
    
    private void setAllChars() {
    	/*
    	 * 
    	*/
    	
    	allChars[0] = getPlayer();
    	
    	for (int c = 0; c < getNpc().length; ++c) {
    		allChars[c + 1] = getNpc()[c];
    	}
    	
    	
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
    
    public void setWorldSpace(Group worldSpace) {
    	/**
    	 * Setter for field: worldSpace
    	*/
    	
    	this.worldSpace = worldSpace;
    	worldSpace.setCache(true);
    }
    
    public void setOverlay(Overlay overlay) {
    	/**
    	 * Setter for field: overlay
    	*/
    	
    	this.overlay = overlay;
    }
    
    public void setIEvent() {
    	/*
    	 * 
    	*/
    	
    	iEvent = new InventoryEvent(this, iMenu, getPlayer());
    }
    
    public void setSEvent() {
    	/*
    	 * 
    	*/
    	
    	sEvent = new StatEvent(this, sMenu, getPlayer());
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
    
    public ArenaCharacter[] getAllChars() {
    	/**
    	 * Getter for field: npcs
    	*/
    	
    	setAllChars();
    	
    	return allChars;
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
    
    public Overlay getOverlay() {
    	/**
    	 * Getter for field: overlay
    	*/
    	
    	return overlay;
    }
    
    public InventoryEvent getIEvent() {
    	/*
    	 * 
    	*/
    	
    	return iEvent;
    }
    
    public abstract CollisionBox[] getWorldBoxes();
    public abstract CollisionBox[] getHurtBoxes();
    public abstract CollisionBox[] getHitBoxes();
    
//MENUS----------------------------------------------------------------------------
    
    public void dispInvMenu() {
    	/*
    	 * 
    	*/
    	
    	pause();
    	
    	// If menu is already there, remove it.
    	if (getWorldSpace().getChildren().contains(iMenu.getMenu())) {
    		getWorldSpace().getChildren().remove(iMenu.getMenu());
    		play();
    		return;
    	}
    	
    	if (getWorldSpace().getChildren().contains(sMenu.getMenu())) {
    		getWorldSpace().getChildren().remove(sMenu.getMenu());
    		getWindow().getController().toggleStatMenu();
    	}
    	
    	// Otherwise place the menu on the screen and update it.
    	getWorldSpace().getChildren().add(iMenu.getMenu());
    	iMenu.getMenu().setViewOrder(0);
		iMenu.getMenu().setTranslateX(getOverlay().getOverlayGroup().getTranslateX());
		iMenu.getMenu().setTranslateY(getOverlay().getOverlayGroup().getTranslateY());
		iMenu.getMenu().setLayoutX(-(getWindow().getRoot().getWidth() / 2));
		iMenu.getMenu().setLayoutY(-(getWindow().getRoot().getHeight() / 2));
		
		iMenu.resetBpMenu();
		iMenu.getBpMenu().getBpList().getSelectionModel().selectedItemProperty().addListener(iEvent.getBpTypeLstn());
		
		iMenu.getBpMenu().getBpList().getSelectionModel().select(0);
		iMenu.resetEqMenu(getPlayer().equipSlot().getEquipped());
		
    }
    
    public void dispStatMenu() {
    	/*
    	 * 
    	*/
    	
    	pause();
    	
    	// If menu is already there, remove it and return.
    	if (getWorldSpace().getChildren().contains(sMenu.getMenu())) {
			sEvent.keepShowing(false);
			getPlayer().getStats().setAttrMins();
    		getWorldSpace().getChildren().remove(sMenu.getMenu());
    		play();
    		return;
    	}
    	
    	// If invMenu is there, remove it.
    	if (getWorldSpace().getChildren().contains(iMenu.getMenu())) {
    		getWorldSpace().getChildren().remove(iMenu.getMenu());
    		getWindow().getController().toggleInvMenu();
    	}
    	
    	// Otherwise place the menu on the screen relative to Player.
    	getWorldSpace().getChildren().add(sMenu.getMenu());
    	sMenu.getMenu().setViewOrder(0);
		sMenu.getMenu().setTranslateX(getOverlay().getOverlayGroup().getTranslateX());
		sMenu.getMenu().setTranslateY(getOverlay().getOverlayGroup().getTranslateY());
		sMenu.getMenu().setLayoutX(-(getWindow().getRoot().getWidth() / 2));
		sMenu.getMenu().setLayoutY(-(getWindow().getRoot().getHeight() / 2));
		
		// Reset all Displays.
		sMenu.setAttrDisp(getPlayer().getStats().getAllAttr());
		sMenu.setStatDisp(getPlayer().getStats().getAllStats());
		sMenu.setLvlDisp(getPlayer().lvl().getInfo());
		
		// If skillPoints available, show inc/dec Buttons and keepShowing until 
		// closed.
		if (getPlayer().lvl().getSkillPoints() > 0) {
			sMenu.getAttrDisp().setButtons();
			
			ArrayList<Button> currSet;
			for (int s = 0; s < sMenu.getAttrDisp().getButtons().size(); ++s) {
				currSet = sMenu.getAttrDisp().getButtons().get(s);
				
				for (int b = 0; b < currSet.size(); ++b) {
					currSet.get(b).setOnAction(sEvent.getAttrBtnOnActn());
				}
			}
			
			sEvent.keepShowing(true);
		}
		
		// If level up is ready show lvlUp Button.
		if (getPlayer().lvl().isLvlUpReady()) {
			sMenu.getLvlDisp().setLvlUpBtn();
			sMenu.getLvlDisp().getLvlUpBtn().setOnAction(sEvent.getLvlBtnOnActn());
		}
    }
    
//ON-LAUNCH------------------------------------------------------------------------
    
    public abstract void onLaunch();
    
    public abstract void updateViewOrder();
    public abstract void runPlayerStates();
    public abstract void runNpcStates();
    public abstract void pause();
    public abstract void play();
    public abstract void save();

}