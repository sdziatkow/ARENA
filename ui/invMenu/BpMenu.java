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
import item.Item.ItemType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class BpMenu {
	
	private GridPane main;
	
	private Label bpLabel;
	private ObservableList<String> bpOptions;
	private ListView<String> bpList;
	private ListView<String> itemList;
	private GridPane itemDisp;
	private Button eqButton;
	private Button useButton;
	
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
		
		main.add(bpLabel, 0, 0, 5, 1);
		main.add(bpList, 0, 1, 5, 1);
	}
	
//SETTERS--------------------------------------------------------------------------
	
	public void setItemList(ObservableList<String> options) {
		/*
		 * This method will display a ListView containting options.
		*/
		
		itemList = new ListView<String>(options);
		
		itemList.getStyleClass().add("bp-list");
		itemList.getStyleClass().add("item-list");
		main.add(itemList, 0, 2, 2, 1);
	}
	
	public void setItemDisp(ItemType type, ArrayList<String> info) {
		/*
		 * This method will display all information of currently selected itemList
		 * item.
		*/
		
		itemDisp = new GridPane();
		
		for (int i = 0; i < info.size(); ++i) {
				
			Label infoLabel = new Label(info.get(i));
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

		}
		
		main.add(itemDisp, 2, 2, 3, 1);
	}
	
	public void setEqButton(String text) {
		/*
		 * This method will add an equip button in last row spanning all columns
		 * of itemDisp.
		*/
		
		if (itemDisp.getChildren().contains(getEqButton())) {
			itemDisp.getChildren().remove(getEqButton());
		}
		
		eqButton = new Button();
		eqButton.setText(text);
		eqButton.getStyleClass().add("button");
		itemDisp.add(eqButton, 0, itemDisp.getRowCount() + 1, 2, 1);
	}
	
	public void setUseButton() {
		/*
		 * This method will add an equip button in last row spanning all columns
		 * of itemDisp.
		*/
		
		if (itemDisp.getChildren().contains(getUseButton())) {
			itemDisp.getChildren().remove(getUseButton());
		}
		
		useButton = new Button();
		useButton.setText("USE");
		useButton.getStyleClass().add("button");
		itemDisp.add(useButton, 0, itemDisp.getRowCount() + 1, 2, 1);
	}
	
//GETTERS--------------------------------------------------------------------------
	
	public ListView<String> getBpList() {
		/*
		 * 
		*/
		
		return bpList;
	}
	
	public ListView<String> getItemList() {
		/*
		 * 
		*/
		
		return itemList;
	}
	
	public GridPane getItemDisp() {
		/*
		 * 
		*/
		
		return itemDisp;
	}
	
	public Button getEqButton() {
		/*
		 * 
		*/
		
		return eqButton;
	}
	
	public Button getUseButton() {
		/*
		 * 
		*/
		
		return useButton;
	}
	
	public GridPane getMain() {
		/*
		 * 
		*/
		
		return main;
	}

}
