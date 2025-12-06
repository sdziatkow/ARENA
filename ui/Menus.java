package ui;

import ui.invMenu.BpMenu;
import ui.invMenu.EquipMenu;
import ui.invMenu.InventoryEvent;
import ui.invMenu.InventoryMenu;
import ui.statMenu.AttrDisplay;
import ui.statMenu.LevelDisplay;
import ui.statMenu.StatDisplay;
import ui.statMenu.StatEvent;
import ui.statMenu.StatMenu;
import worldStage.WorldData;
import worldStage.WorldStage;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Node;

public class Menus {
	/*
	 * 
	*/
	
	// When ID.get() changes, reset all menus
	public static final ChangeListener<? super Number> onDataChange = new 
	ChangeListener<Number>() {
	public void changed
		(ObservableValue<? extends Number> id, Number oldVal, Number newVal) 
		{
			resetMenus();
		}
	};
	
	public static final Group MENU_SPACE = new Group();
	public static final IntegerProperty ID = new SimpleIntegerProperty(0);
	
	public Menus() {
		/*
		 * 
		*/
		
		ID.addListener(onDataChange);
		
		new InventoryEvent();
		new InventoryMenu();
		
		new StatEvent();
		new StatMenu();
		
		MENU_SPACE.setViewOrder(0);
		MENU_SPACE.translateXProperty().bind
			(WorldStage.OVERLAY.getOverlayGroup().translateXProperty()
		);
		MENU_SPACE.translateYProperty().bind
			(WorldStage.OVERLAY.getOverlayGroup().translateYProperty()
		);
		MENU_SPACE.layoutXProperty().bind
			(WorldStage.OVERLAY.getOverlayGroup().layoutXProperty()
		);
		MENU_SPACE.layoutYProperty().bind
			(WorldStage.OVERLAY.getOverlayGroup().layoutYProperty()
		);
		
		MENU_SPACE.getChildren().add(InventoryMenu.main);
		MENU_SPACE.getChildren().add(StatMenu.main);

		hide(InventoryMenu.main);
		hide(StatMenu.main);
		show(MENU_SPACE);
	}
	
//CONTROL--------------------------------------------------------------------------
	
	public static void show(Node menuItem) {
		/*
		 * 
		*/
		
		menuItem.setVisible(true);
		menuItem.setDisable(false);
	}
	
	public static void hide(Node menuItem) {
		/*
		 * 
		*/
		
		menuItem.setVisible(false);
		menuItem.setDisable(true);
	}
	
	public static void resetMenus() {
		/*
		 * 
		*/
		
		dispInvMenu();
		if (InventoryMenu.main.isVisible()) {
			dispInvMenu();
		}
		
		dispStatMenu();
		if (StatMenu.main.isVisible()) {
			dispStatMenu();
		}
	}
	
	public static void nextID() {
		/*
		 * 
		*/
		
		if (ID.get() < WorldData.persons.size() - 1) {
			ID.set(ID.get() + 1);
		}
		else {
			ID.set(0);
		}
	}
	
//MENUS----------------------------------------------------------------------------
	
	public static void dispInvMenu() {
		/*
		 * 
		*/
	
		WorldData.pause();
		
		// If menu is already there, remove it and return.
		if (InventoryMenu.main.isVisible()) {
			hide(InventoryMenu.main);
			BpMenu.clearItemInfo();
			BpMenu.itemList.selectionModelProperty().get().clearSelection();
			WorldData.play();
			return;
		}
		
		// If statMenu is there, remove it.
		if (StatMenu.main.isVisible()) {
			hide(StatMenu.main);
		}
		
		// Update eq and show.
		EquipMenu.clearEq();
		EquipMenu.setEq();
		
		show(InventoryMenu.main);
	}

    
    public static void dispStatMenu() {
    	/*
    	 * 
    	*/
    	
    	WorldData.pause();
    	
    	// If menu is already there, remove it and return.
    	if (StatMenu.main.isVisible()) {
			WorldData.persons.get(ID.get()).data().setAttrMins();
			WorldData.persons.get(ID.get()).data().updateStats();
    		hide(StatMenu.main);
    		WorldData.play();
    		return;
    	}
    	
    	// If invMenu is there, remove it.
    	if (InventoryMenu.main.isVisible()) {
    		hide(InventoryMenu.main);
    	}
    	
    	// Update vals.
		AttrDisplay.setAttrVals();
		StatDisplay.setStatVals();
		LevelDisplay.setLvlVals();
    	
    	// Place the menu on the screen relative to Player.
    	show(StatMenu.main);
		
		// If skillPoints available, show inc/dec Buttons and keepShowing until 
		// closed.
		if (WorldData.persons.get(ID.get()).lvl().getSkillPoints() > 0) {
			AttrDisplay.showButtons();
		}
		else {
			AttrDisplay.hideButtons();
		}
		
		// If level up is ready show lvlUp Button.
		if (WorldData.persons.get(ID.get()).lvl().isLvlUpReady()) {
			show(LevelDisplay.lvlUpBtn);
		}
		else {
			hide(LevelDisplay.lvlUpBtn);
		}
    }

}
