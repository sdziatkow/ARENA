package ui.invMenu;

/**
 * Program Name:    InventoryEvent.java
 *<p>
 * Purpose:         The purpose of this program is to create, apply, and manage
 * 					UI events for InventoryMenu.
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 19, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import worldStage.WorldData;
import item.Item;
import item.useable.Useable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import ui.Menus;

public class InventoryEvent {
	/*
	 * 
	*/
	
	public static ChangeListener<Number> bpTypeLstn;
	public static ChangeListener<Number> bpItemLstn;
	public static EventHandler<ActionEvent> eqBtnOnActn;
	public static EventHandler<ActionEvent> useBtnOnActn;
	
	
	
//CONSTRUCTORS---------------------------------------------------------------------
	
	public InventoryEvent() {
		/*
		 * 
		*/

		bpTypeLstn = new ChangeListener<Number>() {
			/*
			 * Listener for when bpView list item is selected.
			 * Display the proper backpack contents depending on which item
			 * was selected ALL | WEAPON | ARMOR | USEABLE
			*/
			
			public void changed(
				ObservableValue<? extends Number> e, 
				Number oldVal, 
				Number newVal
				){
				/*
				 * 
				*/
				
				BpMenu.setItemList();
			}
		};
		
		bpItemLstn = new ChangeListener<Number>() {
			/*
			 * 
			*/
			public void changed(
					ObservableValue<? extends Number> e, 
					Number oldVal, 
					Number newVal
			){
				/*
				 * This method will display the selected item's information along
				 * with buttons to act on the item.
				*/
				
				BpMenu.clearItemInfo();
				BpMenu.setItemInfo();
			}
		};
		
		eqBtnOnActn = new EventHandler<ActionEvent>() {
			/*
			 * FOR EQUIP BUTTON DISPLAYED UNDER ITEM INFO.
			*/
			
			public void handle(ActionEvent e) {
				/*
				 * This method will equip the selected item and reset the menu to
				 * display accurate information. 
				*/
				
				String currSelect = BpMenu.itemList.selectionModelProperty().get().getSelectedItem();
				Item currItem = WorldData.bkpks.get(Menus.ID.get()).grabItemByName(currSelect);
				
				// (Un-)equip item.
				if (BpMenu.eqButton.getText().equals("EQUIP")) {
					WorldData.eqSlots.get(Menus.ID.get()).equipItem(currItem);
				}
				else if (BpMenu.eqButton.getText().equals("UN-EQUIP")) {
					WorldData.eqSlots.get(Menus.ID.get()).unEquipItem(currItem.getItemType());
				}
				
				BpMenu.setItemInfo();
				EquipMenu.clearEq();
				EquipMenu.setEq();
			}
		};
		
		useBtnOnActn = new EventHandler<ActionEvent>() {
			/*
			 * FOR USE-BUTTON DISPLAYED UNDER ITEM INFO.
			*/
			
			public void handle(ActionEvent e) {
				/*
				 * This method will call the use() method on the currently
				 * selected useable.
				*/
				
				String currSelect = BpMenu.itemList.selectionModelProperty().get().getSelectedItem();
				Item currItem = WorldData.bkpks.get(Menus.ID.get()).grabItemByName(currSelect);
				
				((Useable)WorldData.bkpks.get(Menus.ID.get()).grabItemByName(currItem.getName())).use();
				
				BpMenu.clearItemInfo();
				if (((Useable)currItem).getAmnt() < 1) {
					BpMenu.setItemList();
				}
				else {
					BpMenu.setItemInfo();
				}
				
				EquipMenu.clearEq();
				EquipMenu.setEq();
			}
		};
	}

}
