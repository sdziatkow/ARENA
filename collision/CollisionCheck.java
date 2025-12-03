package collision;

import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CollisionCheck {
	/*
	 * 
	*/
	
	private BooleanProperty isCol;
	private ArrayList<CollisionBox> currColliding;
	
	public CollisionCheck() {
		/*
		 * 
		*/
		
		isCol = new SimpleBooleanProperty();
		isCol.set(false);
	}
	
	public void setIsCol(boolean val) {
		/*
		 * 
		*/
		
		isCol.set(val);
	}
	
	public BooleanProperty isColliding() {
		/*
		 * 
		*/
		
		return isCol;
	}
	
	public void checkForCol(int idx, CollisionBox box, ArrayList<CollisionBox> allBox) {
		/*
		 * 
		*/
		
		CollisionBox checkBox;
		for (int b = 0; b < allBox.size(); ++b) {
			
			checkBox = allBox.get(b);
			if (box.getBounds().intersects(checkBox.getBounds()) && b != idx) {
				setIsCol(true);
			}
		}
	}
	
	public void checkForNoCol(int idx, CollisionBox box, ArrayList<CollisionBox> allBox) {
		/*
		 * 
		*/
		
		boolean noCol = true;
		CollisionBox checkBox;
		for (int b = 0; b < allBox.size(); ++b) {
			
			checkBox = allBox.get(b);
			if (box.getBounds().intersects(checkBox.getBounds()) && b != idx) {
				noCol = false;
			}
		}
		
		setIsCol(!noCol);
	}
}
