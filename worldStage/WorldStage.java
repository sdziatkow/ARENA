package worldStage;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import ui.overlay.Overlay;

public abstract class WorldStage{
    /**
     *
    */
	
	public static IntegerProperty onPersonDeath;

	private Group background;
	private Group worldSpace;
	public static final Overlay OVERLAY = new Overlay();
	
	private ChangeListener<? super Number> removeSprite = new ChangeListener<Number>() {
		public void changed(
				ObservableValue<? extends Number> idx,
				Number oldVal,
				Number newVal
		){
			/*
			 * 
			*/
			
			getWorldSpace().getChildren().remove(WorldData.charSprites.get(newVal.intValue()).getSpriteGroup());
			System.out.println(WorldData.persons.get(newVal.intValue()).getName() + " - DIED ");
			WorldData.removePerson(newVal.intValue());
		}
	};

//CONSTRUCTORS---------------------------------------------------------------------

    protected WorldStage() {
        /**
         * Default Constructor for class
        */
    	
    	background = new Group();
    	worldSpace = new Group();
    	OVERLAY.getOverlayGroup().setCache(true);
    	
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
    	//this.background.setCache(true);
    }
    
    public void setWorldSpace(Group worldSpace) {
    	/**
    	 * Setter for field: worldSpace
    	*/
    	
    	this.worldSpace = worldSpace;
    	this.worldSpace.setCache(true);
    }

//GETTERS--------------------------------------------------------------------------
    
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
    
    public IntegerProperty onPersonDeath() {
    	/*
    	 * 
    	*/
    	
    	return onPersonDeath;
    }
    
//ON-LAUNCH------------------------------------------------------------------------
    
    public abstract void onLaunch();

}