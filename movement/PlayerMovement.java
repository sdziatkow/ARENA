package movement;

/**
 * Program Name:    PlayerMovement.java
 *<p>
 * Purpose:         The purpose of this program is to create a system for the
 * 					playable character to move on the screen.
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 20, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import arenaEnum.Going;
import window.Controller;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ChangeListener;

public class PlayerMovement extends Movement{
    /**
     *
    */
	
	private Controller cntrl;
	private ObservableDoubleValue mouseX;
	private ObservableDoubleValue mouseY;
	
	
	// Listener for isAttk when set to false.
	private ChangeListener<? super Boolean> switchCntrl = new ChangeListener<Boolean>() {
		public void changed(
				ObservableValue<? extends Boolean> attk,
				Boolean oldVal,
				Boolean newVal
		){
			/*
			 * THIS IS NEEDED TO RESET THE VALUE OF PERSON HIT SO NEXT TIME IF IT
			 * IS SET TO SAME PERSON HIT IT WILL TRIGGER NEW PERSON HIT 
			 * SEE ARENA-PERSON 
			*/
			
			if (!newVal.booleanValue()) {
				cntrl.setAttkDown(false);
			}
		}
	};
	
	// Listener for isAttk when set to false.
	private ChangeListener<? super Boolean> onAttk = new ChangeListener<Boolean>() {
		public void changed(
				ObservableValue<? extends Boolean> attk,
				Boolean oldVal,
				Boolean newVal
		){
			/*
			 * THIS IS NEEDED TO RESET THE VALUE OF PERSON HIT SO NEXT TIME IF IT
			 * IS SET TO SAME PERSON HIT IT WILL TRIGGER NEW PERSON HIT 
			 * SEE ARENA-PERSON 
			*/
			
			if (newVal.booleanValue()) {
				
		    	double distDiffY = getPos(1).get() - mouseY.get();
		    	double distDiffX = getPos(0).get() - mouseX.get();
		    	double absDistX  = Math.abs(Math.abs(getPos(0).get()) - Math.abs(mouseX.get()));
		    	double absDistY  = Math.abs(Math.abs(getPos(1).get()) - Math.abs(mouseY.get()));
				
				if (distDiffX > 0.0 && absDistX > absDistY) {
					forceDir(Going.W);
				}
				else if (distDiffX < 0.0 && absDistX > absDistY) {
					forceDir(Going.E);
				}
				else if (distDiffY > 0.0 && absDistY > absDistX) {
					forceDir(Going.N);
				}
				else if (distDiffY < 0.0 && absDistY > absDistX) {
					forceDir(Going.S);
				}
			}
		}
	};
	


//CONSTRUCTORS---------------------------------------------------------------------

    public PlayerMovement() {
        /**
         * Default Constructor for class PlayerMovement.
        */
    	
    	super();
    	setColBounce(0.001);
    }
    
//SETTERS--------------------------------------------------------------------------
    
    public void setCntrl(Controller cntrl) {
    	/*
    	 * 
    	*/
    	
    	this.cntrl = cntrl;
    	isHitBoxPlaced().addListener(switchCntrl);
    	isAttk().addListener(switchCntrl);
    	isAttk().addListener(onAttk);
    }
    
    public void setMousePos(DoubleProperty mouseX, DoubleProperty mouseY) {
    	/*
    	 * 
    	*/
    	
    	this.mouseX = mouseX;
    	this.mouseY = mouseY;
    }
    
//GETTERS--------------------------------------------------------------------------
    
//MOVEMENT-------------------------------------------------------------------------
    
    public void move() {
    	/**
    	 * 
    	*/
    	
		// Player does not move when no keys are not pressed.
		if (
			!cntrl.getWDown() && !cntrl.getSDown()
			&&
			!cntrl.getADown() && !cntrl.getDDown()
		) {
			setMvRate(getMvRate() - (getIncMvRate() * 1.2));
			setDx( getMvRate() * getNormalX() * Math.signum(getDx()));
			setDy( getMvRate() * getNormalY() * Math.signum(getDy()));
			isMv().set(false);
		}
		else if (!isAttk().get()){
			isMv().set(true);
		}
		
		// Set dx, dy dependent on which keys are pressed down.
		if (cntrl.getWDown() && cntrl.getADown()) {
			setDx( -getMvRate() * getNormalX() );
			setDy( -getMvRate() * getNormalY() );
		}
		else if (cntrl.getWDown() && cntrl.getDDown()) {
			setDx(  getMvRate() * getNormalX() );
			setDy( -getMvRate() * getNormalY() );
		}
		else if (cntrl.getSDown() && cntrl.getADown()) {
			setDx( -getMvRate() * getNormalX() );
			setDy(  getMvRate() * getNormalY() );
		}
		else if (cntrl.getSDown() && cntrl.getDDown()) {
			setDx( getMvRate() * getNormalX() );
			setDy( getMvRate() * getNormalY() );
		}
		else if (cntrl.getWDown()) {
			setDx(0.0);
			setDy( -getMvRate() );
		}
		else if (cntrl.getADown()) {
			setDx( -getMvRate() );
			setDy(0.0);
		}
		else if (cntrl.getSDown()) {
			setDx(0.0);
			setDy( getMvRate() );
		}
		else if (cntrl.getDDown()) {
			setDx( getMvRate() );
			setDy(0.0);
		}
		
    	if (cntrl.getAttkDown() && !isAttk().get()) {
			cntrl.setAttkDown(false);
    		isMv().set(false);
    		isAttk().set(true);
    	}
		
		setDir();
		
		checkCollision();
		
		translate();
    	
    	
    }
}