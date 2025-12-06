package ui.invMenu;

/**
 * Program Name:    BpMenu.java
 *<p>
 * Purpose:         The purpose of this program is to create a visual
 * 					representation of an ArenaCharacter's Backpack.
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 14, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import java.util.ArrayList;
import arenaEnum.itemInfo.ItemType;
import item.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import ui.Menus;
import worldStage.WorldData;

public class BpMenu {
	
	public static GridPane main;
	
	public static Label bpLabel;
	public static ObservableList<String> bpOptions;
	public static ListView<String> bpList;
	public static ListView<String> itemList;
	public static GridPane itemDisp;
	public static ArrayList<Label> itemLabels;
	public static Button eqButton;
	public static Button useButton;
	
	public BpMenu() {
		/*
		 * 
		*/
		init();
	}
	
	public void init() {
		/*
		 * 
		*/
		
		main = new GridPane();
		main.getStylesheets().add(
				getClass().getResource("bpMenuStyle.css").toExternalForm());
		main.getStyleClass().add("bp-main");
		
		// ItemType LISTVIEW
		bpLabel = new Label("BACKPACK");
		bpLabel.getStyleClass().add("bp-label");
		bpLabel.getStyleClass().add("bp-header");
		bpOptions = FXCollections.observableArrayList(
				"ALL",
				ItemType.WEAPON.toString(),
				ItemType.ARMOR.toString(),
				ItemType.USEABLE.toString()
		);
		bpList = new ListView<String>(bpOptions);
		bpList.getStyleClass().add("bp-list");
		bpList.getStyleClass().add("bp-type-list");
		bpList.getSelectionModel().selectedIndexProperty().addListener(InventoryEvent.bpTypeLstn);
		
		// Items of selected type in bpView
		itemList = new ListView<String>();
		itemList.getStyleClass().add("bp-list");
		itemList.getStyleClass().add("item-list");
		itemList.getSelectionModel().selectedIndexProperty().addListener(InventoryEvent.bpItemLstn);
		
		main.add(bpLabel, 0, 0, 5, 1);
		main.add(bpList, 0, 1, 5, 1);
		main.add(itemList, 0, 2, 2, 1);
		
		bpList.selectionModelProperty().get().clearAndSelect(0);
		setItemList();
		initItemDisp();
		initUseButton();
		initEqButton();
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public static void initItemDisp() {
		/*
		 * This method will display all information of currently selected itemList
		 * item.
		*/
		
		final int TOTAL_INFO_LABELS = 21;
		itemDisp = new GridPane();
		itemLabels = new ArrayList<Label>();
		
		for (int i = 0; i < TOTAL_INFO_LABELS; ++i) {
				
			Label infoLabel = new Label();
			infoLabel.getStyleClass().add("bp-label");
			
			if (i == 0) {
				
				infoLabel.getStyleClass().add("item-info-name");
				itemDisp.add(infoLabel, 0, i, 3, 1);
			}
			else if (i % 2 == 1) {
				
				infoLabel.getStyleClass().add("item-info-field");
				itemDisp.add(infoLabel, 0, i, 1, 1);
			}
			else {
				infoLabel.getStyleClass().add("item-info-data");
				itemDisp.add(infoLabel, 1, i - 1, 2, 1);
			}
			itemLabels.add(infoLabel);
		}
		
		main.add(itemDisp, 2, 2, 3, 1);
	}
	
	public static void setItemList() {
		/*
		 * This method will display a ListView containting options.
		*/
		
		itemList.getItems().clear();
		
		ArrayList<ArrayList<Item>> allItems = WorldData.bkpks.get(Menus.ID.get()).getAllItems();
		int currSelect = bpList.selectionModelProperty().get().getSelectedIndex();
		
		if (currSelect == 0) {
			for (int t = 0; t < allItems.size(); ++t) {
				for (int i = 0; i < allItems.get(t).size(); ++i) {
					itemList.getItems().add(allItems.get(t).get(i).getName());
				}
			}
		}
		else if (currSelect > 0){
			for (int i = 0; i < allItems.get(currSelect - 1).size(); ++i) {
				itemList.getItems().add(allItems.get(currSelect - 1).get(i).getName());
			}
		}
	}
	
	public static void setItemInfo() {
		/*
		 * 
		*/
		
		String currSelect = itemList.selectionModelProperty().get().getSelectedItem();
		Item currItem = WorldData.bkpks.get(Menus.ID.get()).grabItemByName(currSelect);
		
		if (currItem != null) {
			ArrayList<String> info = currItem.getInfo();
			
			// Set text of all Labels to be the item's info.
			itemLabels.get(0).setText(info.get(0));
			for (int i = 1; i < info.size(); ++i) {
				itemLabels.get(i).setText(info.get(i));
			}
			
			// Check if item's equip status.
			if (WorldData.eqSlots.get(0).isEquipped(currItem)) {
				eqButton.setText("UN-EQUIP");
			}
			else {
				eqButton.setText("EQUIP");
			}
			
			// If useable, show useButton.
			if (currItem.getItemType().equals(ItemType.USEABLE)) {
				Menus.show(useButton);
			}
			Menus.show(eqButton);
		}
	}
	
	public static void clearItemInfo() {
		/*
		 * 
		*/
		
		for (int i = 0; i < itemLabels.size(); ++i) {
			itemLabels.get(i).setText("");
		}
		
		Menus.hide(useButton);
		Menus.hide(eqButton);
	}
	
	public void initEqButton() {
		/*
		 * This method will add an equip button in last row spanning all columns
		 * of itemDisp.
		*/
		
		eqButton = new Button();
		eqButton.getStyleClass().add("button");
		eqButton.setOnAction(InventoryEvent.eqBtnOnActn);
		
		Menus.hide(eqButton);
		itemDisp.add(eqButton, 0, itemDisp.getRowCount() + 1, 2, 1);
	}
	
	public void initUseButton() {
		/*
		 * This method will add an equip button in last row spanning all columns
		 * of itemDisp.
		*/
		
		useButton = new Button();
		useButton.setText("USE");
		useButton.getStyleClass().add("button");
		useButton.setOnAction(InventoryEvent.useBtnOnActn);
		
		Menus.hide(useButton);
		itemDisp.add(useButton, 0, itemDisp.getRowCount() + 1, 2, 1);
	}

}
