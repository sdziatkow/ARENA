package arenaCharacter;

/**
 * Program Name:    Player.java
 *<p>
 * Purpose:         The purpose of this program is to create a playable character
 * 					for ARENA.
 *<p>
 * @version         0.0
 *<p>
 * Created:         March 05, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import animate.Animate;
import animate.PlayerAnimate;
import arenaCharacter.Stat.StatType;
import movement.Movement;
import movement.PlayerMovement;
import sprite.charSprite.CharacterSprite;
import sprite.charSprite.PlayerSprite;
import window.Controller;
import worldStage.WorldStage;

public class Player extends ArenaCharacter{
    /**
     * class Player:
     *  The only playable character in ARENA. Controlled by the user.
    */
	
	private WorldStage stage;
	private CharacterSprite charSprite;
	private Movement movement;
	private Animate animate;

//CONSTRUCTORS---------------------------------------------------------------------
	
    public Player() {
        /**
         * Default Constructor for class
        */
    	
    	super(CharType.PLAYER, CharClass.BRUTE);
    	
    	charSprite = new PlayerSprite();
    	movement = new PlayerMovement(this);
    	animate = new 
    			PlayerAnimate((PlayerSprite)charSprite, (PlayerMovement)movement);
    	
    	setSprite(charSprite);
    	setMvmnt(movement);
    	setAnim(animate);
    	
    }

    public Player(CharClass charClass, WorldStage stage) {
        /**
         * Constructor for class
        */
    	
    	super(CharType.PLAYER, charClass);
    	
    	setStage(stage);
    	
    	charSprite = new PlayerSprite();
    	movement = new PlayerMovement(this);
    	animate = new 
    			PlayerAnimate(charSprite, movement);
    	
    	setSprite(charSprite);
    	setMvmnt(movement);
    	setAnim(animate);
    }
    
//GETTERS--------------------------------------------------------------------------
    
    public Controller getCntrl() {
    	/**
    	 * 
    	*/
    	
    	return getStage().getWindow().getController();
    }
    
//ACTION---------------------------------------------------------------------------
    
    public void attk() {
    	/*
    	 * 
    	*/
    	
    	if (getCntrl().getAttkDown()) {
    		setCharState(State.ATTK);
    		getCntrl().setAttkDown(false);
    		
    		if (!getSprite().getSpriteGroup().getChildren().contains(getSprite().getHitBox().getColBox())) {
    			
    			switch (getMvmnt().getDir()) {
    			case 'w':
    				getSprite().getHitBox().setBounds(new double[] {3.0, 1.0, 14.0, 7.0});
    				break;
    			case 'a':
    				getSprite().getHitBox().setBounds(new double[] {-5.0, 12.0, 7.0, 12.0});
    				break;
    			case 's':
    				getSprite().getHitBox().setBounds(new double[] {4.0, 24.0, 14.0, 7.0});
    				break;
    			case 'd':
    				getSprite().getHitBox().setBounds(new double[] {16.0, 12.0, 7.0, 12.0});
    				break;
    			}
    			
    			getSprite().getSpriteGroup().getChildren().add(getSprite().getHitBox().getColBox());
    			
    			if (getSprite().getHitBox().getBounds().intersects(getStage().getNpc()[0].getSprite().getHurtBox().getBounds())) {
    				getStage().getNpc()[0].stat(StatType.HP).dmg(10.0);
    			}
    		}
    		
    		System.out.println(getStage().getNpc()[0].stat(StatType.HP).getVal());
    	}
    	
 
    	
    }
    

}
