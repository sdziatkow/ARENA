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
import arenaCharacter.ArenaCharacter.State;
import arenaCharacter.Player;
import arenaCharacter.npc.Wilhelm;
import collision.CollisionBox;
import javafx.scene.Group;
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
	private final int totalWorldObj = 3;
	
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
    	getArenaChars().getChildren().add(player.getSprite().getSpriteGroup());
    	getArenaChars().getChildren().add(wilhelm.getSprite().getSpriteGroup());
    	
    	// World Objects
    	towerObj1 = new StoneTower();
    	towerObj2 = new StoneTower();
    	towerObj3 = new StoneTower();
    	
    	// ALL THINGS MUST BE IN ONE GROUP IN ORDER TO ORGANIZE VIEW ORDER ! ! !
    	getArenaChars().getChildren().add(towerObj1.getObjGroup());
    	getArenaChars().getChildren().add(towerObj2.getObjGroup());
    	getArenaChars().getChildren().add(towerObj3.getObjGroup());
//    	getWorldObjects().getChildren().add(towerObj1.getObjGroup());
//    	getWorldObjects().getChildren().add(towerObj2.getObjGroup());
//    	getWorldObjects().getChildren().add(towerObj3.getObjGroup());
    	
    	// Add all to grassBackground
    	getBackground().getChildren().add(getArenaChars());
    	getBackground().getChildren().add(getWorldObjects());
    	
    	
    	setPlayer(player);
    	setNpc(0, wilhelm);
    }
    
    public StoneTower[] getTowers() {
    	/*
    	 * 
    	*/
    	
    	StoneTower[] towers;
    	towers = new StoneTower[] { towerObj1, towerObj2, towerObj3 };
    	
    	return towers;
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
    
    public void runPMvmnt() {
    	/**
    	 * 
    	*/
    	
    	if (getPlayer().getCharState().equals(State.MOVE)) {
    		getPlayer().getMvmnt().move();
    	}
    	
    	getPlayer().attk();
    	updateViewOrder();
    }
    
    public void runNpcMvmnt() {
    	
    	for (int i = 0; i < TOTAL_WORLD_NPCS; ++i) {
    		getNpc()[i].getMvmnt().move();
    	}
    	
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
    	Group tempPane1;
    	Group tempPane2;
    	
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
    
    public void runNpcCol() {
    	/**
    	 * 
    	*/
    	
//    	for (int i = 0; i < TOTAL_WORLD_NPCS; ++i) {
//    		getNpc()[i].getMvmnt().checkCollision();
//    	}
    }
    
    public void runAnim() {
    	/**
    	 * 
    	*/
    	
    	if (getPlayer().getCharState().equals(State.MOVE)) {
    		getPlayer().getAnim().animate();
    	}
    	else if (getPlayer().getCharState().equals(State.ATTK)) {
    		getPlayer().getAnim().animateAttk();
    		//getPlayer().setCharState(State.MOVE);
    	}
    	
    	for (int i = 0; i < TOTAL_WORLD_NPCS; ++i) {
    		getNpc()[i].getAnim().animate();
    	}
    }
    
    
    public void runPAttk() {
    	/*
    	 * 
    	*/
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
}