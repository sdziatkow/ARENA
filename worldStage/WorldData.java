package worldStage;

/**
 * Program Name:    WorldData.java
 *<p>
 * Purpose:         The purpose of this program is to create an object that
 *                  manages all data present in ARENA on any given WorldStage.
 *<p>
 * @version         0.0
 *<p>
 * Created:         November 24, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import java.util.ArrayList;

import animate.Animate;
import arenaEnum.ColType;
import arenaEnum.personInfo.CharState;
import arenaEnum.personStats.StatType;
import arenaPerson.ArenaPerson;
import arenaPerson.ArenaPlayer;
import arenaPerson.npc.NPCElm;
import arenaPerson.npc.NPCWilhelm;
import backpack.Backpack;
import backpack.EquipSlots;
import collision.CollisionBox;
import movement.Movement;
import movement.NpcMovement;
import movement.PlayerMovement;
import sprite.StaticSprite;
import sprite.charSprite.CharacterSprite;
import sprite.charSprite.ElmSprite;
import sprite.charSprite.EnemyTestSprite;
import sprite.charSprite.PlayerSprite;

public class WorldData {
	/*
	 * 
	*/
	
	// Primitive data.
	public static ArrayList<ArenaPerson> persons;
	public static ArrayList<Backpack> bkpks;
	public static ArrayList<EquipSlots> eqSlots; 
	
	// JFX data linked to above data.
	public static ArrayList<Movement> mvmnts;
	public static ArrayList<CharacterSprite> charSprites;
	public static ArrayList<StaticSprite> staticSprites;
	public static ArrayList<Animate> anims;
	
	// CollisionBox data.
	public static ArrayList<ArrayList<CollisionBox>> colBoxes;
	public static ArrayList<CollisionBox> viewOrder;
	
	
//CONSTRUCTORS---------------------------------------------------------------------
	
	public WorldData() {
		/*
		 * 
		*/
		
		persons = new ArrayList<ArenaPerson>();
		bkpks = new ArrayList<Backpack>();
		eqSlots = new ArrayList<EquipSlots>();
		
		mvmnts = new ArrayList<Movement>();
		charSprites = new ArrayList<CharacterSprite>();
		staticSprites = new ArrayList<StaticSprite>();
		anims = new ArrayList<Animate>();
		
		viewOrder = new ArrayList<CollisionBox>();
		
		// Add 5 empty arrayLists
		// 0 = worldBox | 1 = hurtBox | 2 = checkBox | 3 = hitBox | 4 = detectBox
		colBoxes = new ArrayList<ArrayList<CollisionBox>>();
		for (int i = 0; i < 5; ++i) {
			colBoxes.add(new ArrayList<CollisionBox>());
		}
	}
	
	public void init() {
		/*
		 * 
		*/
		
		// For each ArenaPerson
		for (int p = 0; p < persons.size(); ++p) {
			
			// Add corresponding backpack and equipSlot.
			bkpks.add(new Backpack());
			eqSlots.add(new EquipSlots());
			
			// Add each CollisionBox from each corresponding sprite to colBoxes.
			colBoxes.get(0).add(charSprites.get(p).getWorldBox());
			colBoxes.get(1).add(charSprites.get(p).getHurtBox());
			colBoxes.get(2).add(charSprites.get(p).getCheckBox());
			colBoxes.get(3).add(charSprites.get(p).getHitBox());
			colBoxes.get(4).add(charSprites.get(p).getDetectBox());
			viewOrder.add(colBoxes.get(0).get(p));
			
			// Add corresponding Movement (player will always be at idx 0)
			if (p == 0) {
				mvmnts.add(new PlayerMovement());
			}
			else {
				mvmnts.add(new NpcMovement());
				charSprites.get(p).getHpBar().getBar().progressProperty().bind(persons.get(p).arenaStat(StatType.HP).dispVal());
			}
			mvmnts.get(p).setMaxRate(persons.get(p).data().speedProperty());
			mvmnts.get(p).setColBoxIndex(p);
			mvmnts.get(p).setIsHitBoxPlaced(charSprites.get(p).isHitBoxPlaced());
			
			// Add corresponding Animate with Movement StringProperty lstnDir.
			anims.add(new Animate(4));
			anims.get(p).setIsMoving(mvmnts.get(p).isMv());
			anims.get(p).setIsAttk(mvmnts.get(p).isAttk());
			persons.get(p).setIsAttk(mvmnts.get(p).isAttk());
			persons.get(p).setPersonHit(mvmnts.get(p).personHit());
			
			// Bind corresponding Sprite translate properties to Movement pos.
			charSprites.get(p).getSpriteGroup().translateXProperty().bind(mvmnts.get(p).getPos(0));
			charSprites.get(p).getSpriteGroup().translateYProperty().bind(mvmnts.get(p).getPos(1));
			
			// Bind corresponding Animate frameCount to Sprite.
			charSprites.get(p).setFrameCounts(
					anims.get(p).getMvCount(), 
					anims.get(p).getAttkCount()
			);
			
			// Bind corresponding Movement isMv and lstnDir to Sprite.
			charSprites.get(p).setIsMoving(mvmnts.get(p).isMv());
			charSprites.get(p).setDir(mvmnts.get(p).lstnDir());
		}
		
		// For each StaticSprite.
		for (int s = 0; s < staticSprites.size(); ++s) {
			
			// Add its worldBox to colBoxes and viewOrder.
			colBoxes.get(0).add(staticSprites.get(s).getWorldBox());
			viewOrder.add(colBoxes.get(0).get(colBoxes.get(0).size() - 1));
		}
	}
	
//SETTERS--------------------------------------------------------------------------
//GETTERS--------------------------------------------------------------------------
	
//GAMEPLAY-------------------------------------------------------------------------
	
	public void pause() {
		/*
		 * Set all persons CharState to REST.
		*/
		
		for (int p = 0; p < persons.size(); ++p) {
			persons.get(p).setCharState(CharState.REST);
		}
	}
	
	public void play() {
		/*
		 * Set all persons CharState to MOVE.
		*/
		
		for (int p = 0; p < persons.size(); ++p) {
			persons.get(p).setCharState(CharState.MOVE);
		}
	}
	
    public void updateViewOrder() {
    	/*
    	 *  
    	*/
    	
    	// Create array list of all world chars and objs
    	
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
    		
	    	for (int point = 0; point < viewOrder.size() - 1; ++point) {
	    		midy1 = viewOrder.get(point).getMidY();
	    		midy2 = viewOrder.get(point + 1).getMidY();
	    		
	    		if (midy1 < midy2) {
	    			sorted = false;
	    			tempBox1 = viewOrder.get(point);
	    			tempBox2 = viewOrder.get(point + 1);
	    			viewOrder.set(point, tempBox2);
	    			viewOrder.set(point + 1, tempBox1);
	    		}
	    	}
    	}
    	
    	for (int point = 0; point < viewOrder.size(); ++point) {
			tempBox1 = viewOrder.get(point);
			tempBox1.getColBox().getParent().setViewOrder(point + 2);
    	}
    	
    }
	
//READ/WRITE-----------------------------------------------------------------------
	
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
    
    public void removePerson(int idx) {
    	/*
    	 * 
    	*/
		
		mvmnts.set(idx, null);
		
		charSprites.set(idx, null);
		
		anims.set(idx, null);
		
		for (int b = 0; b < colBoxes.size(); ++b) {
			Movement.colBoxes.get(b).set(idx, null);
			colBoxes.get(b).set(idx, null);
		}
		
    }

}
