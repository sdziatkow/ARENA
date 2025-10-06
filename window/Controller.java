package window;

/**
 * Program Name:    Controller.java
 *<p>
 * Purpose:         The purpose of this program is to have a place for key-inputs
 * 					to be mapped to functions.
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 13, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

public class Controller{
    /**
     *
    */
	
	private boolean wDown;
	private boolean aDown;
	private boolean sDown;
	private boolean dDown;
	
	private boolean attkDown;


//CONSTRUCTORS---------------------------------------------------------------------

    public Controller() {
        /**
         * Default Constructor for class
        */
    	
    	wDown = false;
    	aDown = false;
    	sDown = false;
    	dDown = false;
    	
    	attkDown = false;
    }

//SETTERS--------------------------------------------------------------------------

    public void set() {
        /**
         * Setter for field:
        */

    }
    
    public void setAttkDown(boolean attkDown) {
        /**
         * Setter for field: attkDown
        */
    	
    	this.attkDown = attkDown;
    }

//GETTERS--------------------------------------------------------------------------
    
    public boolean getWDown() {
    	/**
    	 * 
    	*/
    	
    	return wDown;
    }
    
    public boolean getADown() {
    	/**
    	 * 
    	*/
    	
    	return aDown;
    }
    
    public boolean getSDown() {
    	/**
    	 * 
    	*/
    	
    	return sDown;
    }
    
    public boolean getDDown() {
    	/**
    	 * 
    	*/
    	
    	return dDown;
    }
    
    public boolean getAttkDown() {
    	/**
    	 * 
    	*/
    	
    	return attkDown;
    }
    
//PLAYER-MOVEMENTS-----------------------------------------------------------------
    
    public void playerMovement(String eventType, KeyCode key) {
    	/**
    	 * 
    	*/
    	
		switch (eventType) {
		
		case "KEY_RELEASED":
			pMvmntKeyReleased(key);
			break;
			
		case "KEY_PRESSED":
			pMvmntKeyPressed(key);
			break;
			
		default:
			break;
		}
    }
    
	public void pMvmntKeyReleased(KeyCode key) {
		/**
		 * 
		*/
		
		if (key.equals(KeyCode.W)) {
			wDown = false;
		}
		else if (key.equals(KeyCode.A)) {
			aDown = false;
		}
		else if (key.equals(KeyCode.S)) {
			sDown = false;
		}
		else if (key.equals(KeyCode.D)) {
			dDown = false;
		}

	}
    
	public void pMvmntKeyPressed(KeyCode key) {
		/**
		 * 
		*/
		
		if (key.equals(KeyCode.W)) {
			wDown = true;
		}
		else if (key.equals(KeyCode.A)) {
			aDown = true;
		}
		else if (key.equals(KeyCode.S)) {
			sDown = true;
		}
		else if (key.equals(KeyCode.D)) {
			dDown = true;
		}
		
	}
	
	public void mbPressed(MouseButton button) {
		/*
		 * 
		*/
		
		switch (button) {
		case PRIMARY:
			attkDown = true;
			break;
		default:
			break;
		}
	}

}
