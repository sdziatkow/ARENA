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

import java.util.ArrayList;

import arenaEnum.personInfo.CharClass;
import arenaEnum.personInfo.CharState;
import arenaEnum.personStats.StatType;
import arenaPerson.ArenaPerson;
import arenaPerson.ArenaPlayer;
import arenaPerson.npc.NPCElm;
import arenaPerson.npc.NPCWilhelm;
import collision.CollisionBox;
import item.armor.ClothClothes;
import item.armor.SteelArmor;
import item.useable.StatPot;
import item.weapon.CrystalSceptre;
import item.weapon.SteelRapier;
import sprite.GroundTileSet;
import sprite.StoneTower;
import sprite.charSprite.PlayerSprite;
import window.Main;
import movement.Movement;
import movement.PlayerMovement;

public class StageOne extends WorldStage{
    /**
     *
    */
	
	private GroundTileSet groundTileSet;

//CONSTRUCTORS---------------------------------------------------------------------

    public StageOne(Main window) {
        /**
         * Default Constructor for class
        */
    	
    	super(window);
    	DATA = new StageOneData();
    	
    	// Grass background.
    	groundTileSet = new GroundTileSet();
    	setBackground(groundTileSet.getTilePane());
    }
    
    public void onLaunch() {
    	/**
    	 * 
    	*/
    	
    	// Add all sprites and overlay.
    	for (int s = 0; s < DATA.persons.size(); ++s) {
    		
    		if (s < DATA.staticSprites.size()) {
    			getWorldSpace().getChildren().add(DATA.staticSprites.get(s).getGroup());
    		}
    		getWorldSpace().getChildren().add(DATA.charSprites.get(s).getSpriteGroup());
    	}
    	getWorldSpace().getChildren().add(getOverlay().getOverlayGroup());
    	
    	// Add all to grassBackground
    	getBackground().getChildren().add(getWorldSpace());
    	
    	getOverlay().getStatBars()[0].getBar().progressProperty().bind(DATA.persons.get(0).arenaStat(StatType.HP).dispVal());
    	getOverlay().getStatBars()[1].getBar().progressProperty().bind(DATA.persons.get(0).arenaStat(StatType.MP).dispVal());
    	getOverlay().getStatBars()[2].getBar().progressProperty().bind(DATA.persons.get(0).arenaStat(StatType.SP).dispVal());
    	getOverlay().getStatBars()[3].getBar().progressProperty().bind(DATA.persons.get(0).lvl().dispVal());
    	getOverlay().getOverlayGroup().translateXProperty().bind(DATA.mvmnts.get(0).getPos(0));
    	getOverlay().getOverlayGroup().translateYProperty().bind(DATA.mvmnts.get(0).getPos(1));
		getOverlay().getOverlayGroup().setLayoutX(-(getWindow().getStage().getWindow().getRoot().getWidth() / 2));
		getOverlay().getOverlayGroup().setLayoutY(-(getWindow().getStage().getWindow().getRoot().getHeight() / 2));
    	
    	getOverlay().getOverlayGroup().setViewOrder(1);
    	

    	
    	setIEvent();
    	setSEvent();
    	
    	ArenaPerson.setOnDeath(onPersonDeath());
    }
    
    public void runPersonStates() {
    	/**
    	 * 
    	*/
    	
    	//player.stateMachine();
    	for (int i = 0; i < DATA.persons.size(); ++i) {

    		if (DATA.mvmnts.get(i) != null) {
	    		switch (DATA.persons.get(i).getCharState()) {
	    		case REST:
	    			break;
	    		case MOVE:
	    			DATA.mvmnts.get(i).move();
	    			break;
	    		case ATTK:
	    			DATA.mvmnts.get(i).move();
	    			break;
	    		}
    		}
    	}
    }
    	
    	
}