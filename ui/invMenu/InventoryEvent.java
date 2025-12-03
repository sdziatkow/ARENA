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

import worldStage.WorldStage;
import arenaPerson.ArenaPerson;
import item.Item;
import arenaEnum.itemInfo.ItemType;
import item.useable.Useable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class InventoryEvent {
	/*
	 * 
	*/
	
	private int currTypeIdx;
	private ObservableList<String> currItemList;
	private int currItemIdx;
	private Item currItem;
	
	private ChangeListener<String> bpTypeLstn;
	private ChangeListener<String> bpItemLstn;
	private EventHandler<ActionEvent> eqBtnOnActn;
	private EventHandler<ActionEvent> useBtnOnActn;
	
	
	
//CONSTRUCTORS---------------------------------------------------------------------
	
	public InventoryEvent() {
		/*
		 * 
		*/
	}
	
	public InventoryEvent(
			WorldStage stage, 
			InventoryMenu menu, 
			ArenaPerson player
	) {
		/*
		 * 
		*/
	
		this.currTypeIdx = -1;
		this.currItemList = null;
		this.currItemIdx = -1;
		this.currItem = null;
		
		
		
		bpTypeLstn = new ChangeListener<String>() {
			/*
			 * Listener for when bpView list item is selected.
			 * Display the proper backpack contents depending on which item
			 * was selected ALL | WEAPON | ARMOR | USEABLE
			*/
			
			public void changed(
				ObservableValue<? extends String> e, 
				String oldVal, 
				String newVal
				){
				/*
				 * 
				*/
				
				// Will be filled based on what was selected with item names.
				currItemList = FXCollections.observableArrayList();
				
				// If the new selection is nothing, then select zero and set
				// newVal as empty String to get default case.
				if (newVal == null) {
					menu.getBpMenu().getBpList().getSelectionModel().select(0);
					newVal = "";
				}
				
				// Iterate through backpack and add all item names into
				// currItemList to create and add a new listView.
				switch (newVal) {
				case "ALL":
					currTypeIdx = 0;
					for (int w = 0; w < player.bp().getWeapon().size(); ++w) {
						currItemList.add(player.bp().getWeapon().get(w).getName());
					}
					for (int a = 0; a < player.bp().getArmor().size(); ++a) {
						currItemList.add(player.bp().getArmor().get(a).getName());
					}
					for (int u = 0; u < player.bp().getUseable().size(); ++u) {
						currItemList.add(player.bp().getUseable().get(u).getName());
					}
				break;
				case "WEAPON":
					currTypeIdx = 1;
					for (int w = 0; w < player.bp().getWeapon().size(); ++w) {
						currItemList.add(player.bp().getWeapon().get(w).getName());
					}
					break;
				case "ARMOR":
					currTypeIdx = 2;
					for (int a = 0; a < player.bp().getArmor().size(); ++a) {
						currItemList.add(player.bp().getArmor().get(a).getName());
					}
					break;
				case "USEABLE":
					currTypeIdx = 3;
					for (int u = 0; u < player.bp().getUseable().size(); ++u) {
						currItemList.add(player.bp().getUseable().get(u).getName());
					}
					break;
				default:
					currTypeIdx = 0;
					break;
				}
				
				// Reset the bpMenu leaving a new bpMenu object with only bpList added to it.
				menu.resetBpMenu();
				
				// Set the selected cell to be the one just selected and then add this event listener to listen for next change.
				menu.getBpMenu().getBpList().getSelectionModel().select(currTypeIdx);
				menu.getBpMenu().getBpList().getSelectionModel().selectedItemProperty().addListener(bpTypeLstn);
				
				// Create new itemList with currItemList, add listener for next change, and select previously selected items.
				menu.getBpMenu().setItemList(currItemList);
				menu.getBpMenu().getItemList().getSelectionModel().selectedItemProperty().addListener(bpItemLstn);
				if (currItemIdx != -1) {
					menu.getBpMenu().getItemList().getSelectionModel().select(currItemIdx);
				}
			}
			
		
		};
		
		bpItemLstn = new ChangeListener<String>() {
			/*
			 * 
			*/
			public void changed(
				ObservableValue<? extends String> e, 
				String oldVal,
				String newVal
				){
				/*
				 * This method will display the selected item's information along
				 * with buttons to act on the item.
				*/
				
				// Reset the bpMenu leaving a new bpMenu object with only bpList added to it.
				menu.resetBpMenu();
				
				// Select previously selected bpList cell and add listener to wait for next change.
				menu.getBpMenu().getBpList().getSelectionModel().select(currTypeIdx);
				menu.getBpMenu().getBpList().getSelectionModel().selectedItemProperty().addListener(bpTypeLstn);
				
				// Create new itemList with previously generated itemList.
				menu.getBpMenu().setItemList(currItemList);
				
				// If a new cell was selected on change.
				if (newVal != null) {
					
					// Set the idx of the newly selected cell and select the cell on itemList.
					currItemIdx = currItemList.indexOf(newVal);
					menu.getBpMenu().getItemList().getSelectionModel().select(currItemIdx);
					
					// Get the Item Object associated with the cell.
					currItem = player.bp().grabItemByName(newVal);
					
					// If item exists.
					if (currItem != null) {
						
						// Update the item's info and display it next to itemList.
						currItem.setInfo();
						menu.getBpMenu().setItemDisp(currItem.getItemType(), currItem.getInfo());
						
						// If item is useable, then add the use button under info.
						if (currItem.getItemType().equals(ItemType.USEABLE)) {
							menu.getBpMenu().setUseButton();
							menu.getBpMenu().getUseButton().setOnAction(useBtnOnActn);
						}
						
						// Display (un-)equip button.
						if (player.equipSlot().isEquipped(currItem)) {
							menu.getBpMenu().setEqButton("UN-EQUIP");
						}
						else {
							menu.getBpMenu().setEqButton("EQUIP");
						}
						menu.getBpMenu().getEqButton().setOnAction(eqBtnOnActn);
					}
				}
				else { // If no new item is selected, reset currItemIdx.
					currItemIdx = -1;
				}
				
				// Add listener for next change.
				menu.getBpMenu().getItemList().getSelectionModel().selectedItemProperty().addListener(bpItemLstn);
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
				
				// (Un-)equip item.
				if (!player.equipSlot().isEquipped(currItem)) {
					player.equipSlot().equipItem(currItem);
				}
				else {
					player.equipSlot().unEquipItem(currItem.getItemType());
				}
				
				// Reset menu.
				menu.resetBpMenu();
				menu.getBpMenu().getBpList().getSelectionModel().selectedItemProperty().addListener(bpTypeLstn);
				menu.getBpMenu().getBpList().getSelectionModel().select(currTypeIdx);
				
				menu.resetEqMenu(player.equipSlot().getEquipped());
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
				
				((Useable)player.bp().grabItemByName(currItem.getName())).use();
				
				// If all of Item is used then reset currItemIdx.
				if (((Useable)currItem).getAmnt() < 1) {
					currItemIdx = -1;
				}
				
				// Reset menu.
				menu.resetBpMenu();
				menu.getBpMenu().getBpList().getSelectionModel().selectedItemProperty().addListener(bpTypeLstn);
				menu.getBpMenu().getBpList().getSelectionModel().select(currTypeIdx);
				
				menu.resetEqMenu(player.equipSlot().getEquipped());
			}
		};
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public void resetVals() {
		/*
		 * 
		*/
		
		this.currTypeIdx = -1;
		this.currItemList = null;
		this.currItemIdx = -1;
		this.currItem = null;
	}
	
//GETTERS--------------------------------------------------------------------------
	
	public ChangeListener<String> getBpTypeLstn() {
		/*
		 * 
		*/
		
		return bpTypeLstn;
	}
	
	public ChangeListener<String> getBpItemLstn() {
		/*
		 * 
		*/
		
		return bpItemLstn;
	}
	
	public EventHandler<ActionEvent> getEqBtnOnActn() {
		/*
		 * 
		*/
		
		return eqBtnOnActn;
	}
	
	public EventHandler<ActionEvent> getUseBtnOnActn() {
		/*
		 * 
		*/
		
		return useBtnOnActn;
	}

}
