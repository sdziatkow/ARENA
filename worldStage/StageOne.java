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
import arenaCharacter.Stat.StatType;
import arenaCharacter.npc.Elm;
import arenaCharacter.npc.Wilhelm;
import collision.CollisionBox;
import item.armor.ClothClothes;
import item.armor.SteelArmor;
import item.useable.StatPot;
import item.weapon.CrystalSceptre;
import item.weapon.SteelRapier;
import sprite.GroundTileSet;
import sprite.StoneTower;
import window.Main;

public class StageOne extends WorldStage{
    /**
     *
    */
	
	public final static int TOTAL_WORLD_NPCS = 2;
	public final static int TOTAL_WORLD_ITEMS = 1;
	
	private GroundTileSet groundTileSet;
	
	private ArenaCharacter player;
	private ArenaCharacter wilhelm;
	private ArenaCharacter elm;
	
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
    	player = new Player(CharClass.RANGER, this, getWindow().getController());
    	wilhelm = new Wilhelm(this);
    	elm = new Elm(this);
    	getWorldSpace().getChildren().add(player.getMvmnt().getSprite().getSpriteGroup());
    	getWorldSpace().getChildren().add(wilhelm.getMvmnt().getSprite().getSpriteGroup());
    	getWorldSpace().getChildren().add(elm.getMvmnt().getSprite().getSpriteGroup());
    	
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
    	setNpc(1, elm);
    	
    	getOverlay().updateStatBars(getPlayer().state(), getPlayer().lvl());
    	getWorldSpace().getChildren().add(getOverlay().getOverlayGroup());
    }
    
    public void onLaunch() {
    	/**
    	 * 
    	*/
    	
    	getPlayer().bp().addItem(new SteelRapier());
    	getPlayer().bp().addItem(new CrystalSceptre());
    	getPlayer().bp().addItem(new SteelArmor());
    	getPlayer().bp().addItem(new StatPot(1, StatType.HP, 20.0));
    	getPlayer().bp().addItem(new StatPot(5, StatType.SP, 20.0));
    	getPlayer().lvl().incXp(100000, false);
    	
    	getOverlay().getOverlayGroup().setViewOrder(1);
    	
    	getPlayer().getMvmnt().setColBoxIndex(0);
    	getNpc()[0].getMvmnt().setColBoxIndex(1);
    	getNpc()[1].getMvmnt().setColBoxIndex(2);
    	
    	// Set to desired position
    	getPlayer().getMvmnt().getSprite().getSpriteGroup().setTranslateX(1100);
    	getPlayer().getMvmnt().getSprite().getSpriteGroup().setTranslateY(1100);
    	
    	getNpc()[0].getMvmnt().getSprite().getSpriteGroup().setTranslateX(1000);
    	getNpc()[0].getMvmnt().getSprite().getSpriteGroup().setTranslateY(1000);
    	
    	getNpc()[1].getMvmnt().getSprite().getSpriteGroup().setTranslateX(1200);
    	getNpc()[1].getMvmnt().getSprite().getSpriteGroup().setTranslateY(1200);
    	
    	towerObj1.getObjGroup().setTranslateX(1000);
    	towerObj1.getObjGroup().setTranslateY(900);
    	
    	towerObj2.getObjGroup().setTranslateX(1100);
    	towerObj2.getObjGroup().setTranslateY(900);
    	
    	towerObj3.getObjGroup().setTranslateX(1200);
    	towerObj3.getObjGroup().setTranslateY(850);
    	
    	setIEvent();
    	setSEvent();
    }
    
    public void updateViewOrder() {
    	/*
    	 *  
    	*/
    	
    	// Create array list of all world chars and objs
    	ArrayList<CollisionBox> boxes = new ArrayList<CollisionBox>();
    	boxes.add(player.getMvmnt().getSprite().getWorldBox());
    	boxes.add(wilhelm.getMvmnt().getSprite().getWorldBox());
    	boxes.add(elm.getMvmnt().getSprite().getWorldBox());
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
			tempBox1.getColBox().getParent().setViewOrder(point + 2);
    	}
    	
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
    	
    	for (int c = 0; c < getAllChars().length; ++c) {
    		getAllChars()[c].setCharState(State.REST);
    	}
    }
    
    public void play() {
    	/*
    	 * 
    	*/
    	
    	for (int c = 0; c < getAllChars().length; ++c) {
    		getAllChars()[c].setCharState(State.MOVE);
    	}
    }
    
    public void save() {
    	/**
    	 * TODO: Save all ArenaCharacter, Item, and WorldObject data on a file.
    	*/
    }
    
    public void load() {
    	/*
    	 * TODO: Load all saved data and apply to stage. 
    	*/
    }
    
    public CollisionBox[] getWorldBoxes() {
    	/**
    	 * 
    	*/
    	
    	worldBoxes = new CollisionBox[]{
    		player.getMvmnt().getSprite().getWorldBox(),
    		wilhelm.getMvmnt().getSprite().getWorldBox(),
    		elm.getMvmnt().getSprite().getWorldBox(),
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
    		player.getMvmnt().getSprite().getHurtBox(),
    		wilhelm.getMvmnt().getSprite().getHurtBox(),
    		elm.getMvmnt().getSprite().getHurtBox(),
    	};
    	
    	return worldBoxes;
    }
    
    public CollisionBox[] getHitBoxes() {
    	/**
    	 * 
    	*/
    	
    	worldBoxes = new CollisionBox[]{
    		player.getMvmnt().getSprite().getHitBox(),
    		wilhelm.getMvmnt().getSprite().getHitBox(),
    	};
    	
    	return worldBoxes;
    }
}