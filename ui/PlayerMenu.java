package ui;

/**
 * Program Name:    PlayerMenu.java
 *<p>
 * Purpose:         The purpose of this program is to create a visual
 * 					representation of an ArenaCharacter's CharState
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 12, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import item.Item.ItemType;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class PlayerMenu {
	/*
	 * 
	*/
	
	private Group menu;
	private GridPane main;
	private ListView<ItemType> bpView;
	
	public PlayerMenu() {
		/*
		 * 
		*/
		
		menu = new Group();
		main = new GridPane();
		
		bpView = new ListView<ItemType>();
		bpView.getItems().addAll(ItemType.WEAPON, ItemType.ARMOR, ItemType.USEABLE);
		
		main.add(bpView, 0, 0);
		menu.getChildren().add(main);
	}
	
	public Group getMenu() {
		/*
		 * 
		*/
		
		return menu;
	}

}
