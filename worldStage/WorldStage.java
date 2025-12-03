package worldStage;

import java.util.ArrayList;

import arenaPerson.ArenaPerson;
import collision.CollisionBox;
import item.Item;
import arenaEnum.itemInfo.ItemType;
import item.useable.Useable;
import item.weapon.Weapon;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import movement.PlayerMovement;
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
	public static WorldData DATA;
	public static IntegerProperty onPersonDeath;

	private Group background;
	private Group worldSpace;
	private Overlay overlay;
	
	private InventoryEvent iEvent;
	private InventoryMenu iMenu;
	private StatEvent sEvent;
	private StatMenu sMenu;
	
	private ChangeListener<? super Number> removeSprite = new ChangeListener<Number>() {
		public void changed(
				ObservableValue<? extends Number> idx,
				Number oldVal,
				Number newVal
		){
			/*
			 * 
			*/
			
			getWorldSpace().getChildren().remove(DATA.charSprites.get(newVal.intValue()).getSpriteGroup());
			System.out.println(DATA.persons.get(newVal.intValue()).getName() + " - DIED ");
			DATA.removePerson(newVal.intValue());
		}
	};

//CONSTRUCTORS---------------------------------------------------------------------

    public WorldStage(Main window) {
        /**
         * Default Constructor for class
        */
    	
    	this.window = window;
    	
    	background = new Group();
    	worldSpace = new Group();
    	overlay = new Overlay();
    	
    	iMenu = new InventoryMenu();
    	sMenu = new StatMenu();
    	
    	background.setCache(true);
    	worldSpace.setCache(true);
    	
    	onPersonDeath = new SimpleIntegerProperty(-1);
    	onPersonDeath.addListener(removeSprite);
    }
    
    public void testInfo() {
    	/*
    	 * 
    	*/
    	
    }

//SETTERS--------------------------------------------------------------------------
    
    public void setBackground(Group background) {
    	/**
    	 * Setter for field: background
    	*/
    	
    	this.background = background;
    	this.background.setCache(true);
    }
    
    public void setWorldSpace(Group worldSpace) {
    	/**
    	 * Setter for field: worldSpace
    	*/
    	
    	this.worldSpace = worldSpace;
    	this.worldSpace.setCache(true);
    }
    
    public void setOverlay(Overlay overlay) {
    	/**
    	 * Setter for field: overlay
    	*/
    	
    	this.overlay = overlay;
    	this.overlay.getOverlayGroup().setCache(true);
    }
    
    public void setIEvent() {
    	/*
    	 * 
    	*/
    	
    	iEvent = new InventoryEvent(this, iMenu, DATA.persons.get(0));
    }
    
    public void setSEvent() {
    	/*
    	 * 
    	*/
    	
    	sEvent = new StatEvent(this, sMenu, DATA.persons.get(0));
    }

//GETTERS--------------------------------------------------------------------------
    
    public Main getWindow() {
    	/**
    	 * 
    	*/
    	
    	return window;
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
    
    public IntegerProperty onPersonDeath() {
    	/*
    	 * 
    	*/
    	
    	return onPersonDeath;
    }
    
//MENUS----------------------------------------------------------------------------
    
    public void dispInvMenu() {
    	/*
    	 * 
    	*/
    	
    	DATA.pause();
    	
    	// If menu is already there, remove it.
    	if (getWorldSpace().getChildren().contains(iMenu.getMenu())) {
    		getWorldSpace().getChildren().remove(iMenu.getMenu());
    		DATA.play();
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
		iMenu.resetEqMenu(DATA.persons.get(0).equipSlot().getEquipped());
		
    }
    
    public void dispStatMenu() {
    	/*
    	 * 
    	*/
    	
    	DATA.pause();
    	
    	// If menu is already there, remove it and return.
    	if (getWorldSpace().getChildren().contains(sMenu.getMenu())) {
			sEvent.keepShowing(false);
			DATA.persons.get(0).data().setAttrMins();
			DATA.persons.get(0).data().updateStats();
    		getWorldSpace().getChildren().remove(sMenu.getMenu());
    		DATA.play();
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
		sMenu.setAttrDisp(DATA.persons.get(0).data().getAllAttr());
		sMenu.setStatDisp(DATA.persons.get(0).data().getAllStats());
		sMenu.setLvlDisp(DATA.persons.get(0).lvl().getInfo());
		
		// If skillPoints available, show inc/dec Buttons and keepShowing until 
		// closed.
		if (DATA.persons.get(0).lvl().getSkillPoints() > 0) {
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
		if (DATA.persons.get(0).lvl().isLvlUpReady()) {
			sMenu.getLvlDisp().setLvlUpBtn();
			sMenu.getLvlDisp().getLvlUpBtn().setOnAction(sEvent.getLvlBtnOnActn());
		}
    }
    
//ON-LAUNCH------------------------------------------------------------------------
    
    public abstract void onLaunch();
    
    public abstract void runPersonStates();

}