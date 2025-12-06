package worldStage;

/**
 * Program Name:    StageOne.java
 *<p>
 * Purpose:         The purpose of this program is to create a specific stage 
 * 					for the world of ARENA.
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 18, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/


import arenaEnum.personStats.StatType;
import sprite.GroundTileSet;
import ui.Menus;

public class StageOne extends WorldStage{
    /**
     *
    */
	
	public final static WorldData DATA = new StageOneData();
	public static final Menus MENU = new Menus();

//CONSTRUCTORS---------------------------------------------------------------------

    public StageOne() {
        /**
         * Default Constructor for class
        */
    	
    	super();
    	
    	// Grass background.
    	setBackground(new GroundTileSet().getTilePane());
    }
    
    public void onLaunch() {
    	/**
    	 * 
    	*/
    	
    	// Add all sprites and OVERLAY.
    	for (int s = 0; s < WorldData.persons.size(); ++s) {
    		
    		if (s < WorldData.staticSprites.size()) {
    			getWorldSpace().getChildren().add(WorldData.staticSprites.get(s).getGroup());
    		}
    		getWorldSpace().getChildren().add(WorldData.charSprites.get(s).getSpriteGroup());
    	}
    	getWorldSpace().getChildren().add(OVERLAY.getOverlayGroup());
    	getWorldSpace().getChildren().add(Menus.MENU_SPACE);
    	
    	// Add all to grassBackground
    	getBackground().getChildren().add(getWorldSpace());
    	
    	OVERLAY.getStatBars()[0].getBar().progressProperty().bind(WorldData.persons.get(0).arenaStat(StatType.HP).dispVal());
    	OVERLAY.getStatBars()[1].getBar().progressProperty().bind(WorldData.persons.get(0).arenaStat(StatType.MP).dispVal());
    	OVERLAY.getStatBars()[2].getBar().progressProperty().bind(WorldData.persons.get(0).arenaStat(StatType.SP).dispVal());
    	OVERLAY.getStatBars()[3].getBar().progressProperty().bind(WorldData.persons.get(0).lvl().dispVal());
    	OVERLAY.getOverlayGroup().translateXProperty().bind(WorldData.mvmnts.get(0).getPos(0));
    	OVERLAY.getOverlayGroup().translateYProperty().bind(WorldData.mvmnts.get(0).getPos(1));
    	
    	OVERLAY.getOverlayGroup().setViewOrder(1);
    }
    	
    	
}