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
import arenaCharacter.ArenaCharacter;
import arenaCharacter.ArenaCharacter.CharClass;
import arenaCharacter.Player;
import arenaCharacter.npc.Wilhelm;
import collision.CollisionBox;
import sprite.GroundTileSet;
import sprite.StoneTower;
import window.Main;

public class StageOne extends WorldStage{
    /**
     *
    */
	
	public final static int TOTAL_WORLD_NPCS = 1;
	public final static int TOTAL_WORLD_ITEMS = 1;
	
	private GroundTileSet groundTileSet;
	
	private ArenaCharacter player;
	private ArenaCharacter wilhelm;
	
	private StoneTower towerObj1;
	private StoneTower towerObj2;
	private StoneTower towerObj3;
	
	private CollisionBox[] worldBoxes;
	

//CONSTRUCTORS---------------------------------------------------------------------

    public StageOne(Main window) {
        /**
         * Default Constructor for class
        */
    	
    	super(window, TOTAL_WORLD_NPCS, TOTAL_WORLD_ITEMS);
    	
    	// Grass background.
    	groundTileSet = new GroundTileSet();
    	setBackground(groundTileSet.getTilePane());
    	
    	// Players / NPCS
    	player = new Player(CharClass.BARBARIAN, this);
    	wilhelm = new Wilhelm(this);
    	getWorldSpace().getChildren().add(player.getSprite().getSpriteGroup());
    	getWorldSpace().getChildren().add(wilhelm.getSprite().getSpriteGroup());
    	
    	// World Objects
    	towerObj1 = new StoneTower();
    	towerObj2 = new StoneTower();
    	towerObj3 = new StoneTower();
    	getWorldSpace().getChildren().add(towerObj1.getObjGroup());
    	getWorldSpace().getChildren().add(towerObj2.getObjGroup());
    	getWorldSpace().getChildren().add(towerObj3.getObjGroup());
    	
    	// Add all to grassBackground
    	getBackground().getChildren().add(getWorldSpace());
    	
    	
    	setPlayer(player);
    	setNpc(0, wilhelm);
    }
    
    public void onLaunch() {
    	/**
    	 * 
    	*/
    	
    	getPlayer().getMvmnt().setColBoxIndex(0);
    	getNpc()[0].getMvmnt().setColBoxIndex(1);
    	
    	// Set to desired position
    	getNpc()[0].getSprite().getSpriteGroup().setTranslateX(150);
    	getNpc()[0].getSprite().getSpriteGroup().setTranslateY(50);
    	
    	towerObj1.getObjGroup().setTranslateX(50);
    	towerObj1.getObjGroup().setTranslateY(50);
    	
    	towerObj2.getObjGroup().setTranslateX(200);
    	towerObj2.getObjGroup().setTranslateY(50);
    	
    	towerObj3.getObjGroup().setTranslateX(300);
    	towerObj3.getObjGroup().setTranslateY(50);
    }
    
    public void updateViewOrder() {
    	/*
    	 *  
    	*/
    	
    	// Create array list of all world chars and objs
    	ArrayList<CollisionBox> boxes = new ArrayList<CollisionBox>();
    	boxes.add(player.getSprite().getWorldBox());
    	boxes.add(wilhelm.getSprite().getWorldBox());
    	boxes.add(towerObj1.getWorldBox());
    	boxes.add(towerObj2.getWorldBox());
    	boxes.add(towerObj3.getWorldBox());
    	
    	// For sorting
    	boolean sorted;
    	double midy1;
    	double midy2;
    	
    	CollisionBox tempBox1;
    	CollisionBox tempBox2;
    	
    	
    	//Sort worldItems y values descending order.
    	sorted = false;
    	while (!sorted) {
    		
    		sorted = true;
    		
	    	for (int point = 0; point < boxes.size() - 1; ++point) {
	    		midy1 = boxes.get(point).getMidY();
	    		midy2 = boxes.get(point + 1).getMidY();
	    		
	    		if (midy1 < midy2) {
	    			sorted = false;
	    			tempBox1 = boxes.get(point);
	    			tempBox2 = boxes.get(point + 1);
	    			boxes.set(point, tempBox2);
	    			boxes.set(point + 1, tempBox1);
	    		}
	    	}
    	}
    	
    	for (int point = 0; point < boxes.size(); ++point) {
			tempBox1 = boxes.get(point);
			tempBox1.getColBox().getParent().getParent().setViewOrder(point);
//			System.out.print(tempBox1.getColBox().getParent().getParent().getViewOrder() + " ( ");
//			System.out.print(tempBox1.getMidY()+ ") | ");
    	}
//    	System.out.println();
    	
    }
    
    public void runPlayerStates() {
    	/**
    	 * 
    	*/
    	
    	player.stateMachine();
    }
    
    public void runNpcStates() {
    	
    	for (int i = 0; i < TOTAL_WORLD_NPCS; ++i) {
    		getNpc()[i].stateMachine();
    	}
    	
    }
    
    public void pause() {
    	/**
    	 * 
    	*/
    }
    
    public CollisionBox[] getWorldBoxes() {
    	/**
    	 * 
    	*/
    	
    	worldBoxes = new CollisionBox[]{
    		player.getSprite().getWorldBox(),
    		wilhelm.getSprite().getWorldBox(),
    		towerObj1.getWorldBox(),
    		towerObj2.getWorldBox(),
    		towerObj3.getWorldBox()
    	};
    	
    	return worldBoxes;
    }
    
    public CollisionBox[] getHurtBoxes() {
    	/**
    	 * 
    	*/
    	
    	worldBoxes = new CollisionBox[]{
    		player.getSprite().getHurtBox(),
    		wilhelm.getSprite().getHurtBox(),
    	};
    	
    	return worldBoxes;
    }
    
    public CollisionBox[] getHitBoxes() {
    	/**
    	 * 
    	*/
    	
    	worldBoxes = new CollisionBox[]{
    		player.getSprite().getHitBox(),
    		wilhelm.getSprite().getHitBox(),
    	};
    	
    	return worldBoxes;
    }
}