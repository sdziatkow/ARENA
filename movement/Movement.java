package movement;

/**
 * Program Name:    Movement.java
 *<p>
 * Purpose:         The purpose of this program is to create a system that all 
 * 					moving world objects can use to move on the screen.
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
import arenaEnum.ColType;
import collision.CollisionBox;
import java.util.ArrayList;
import javafx.geometry.Bounds;
import worldStage.WorldData;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ChangeListener;

public abstract class Movement{
    /**
     *
    */
	
	private ObservableDoubleValue maxRate;
	private double maxMvRate;
	private double mvRate;
	private double incMvRate;
	private double dx;
	private double dy;
	private Going dir;
	
	private int colBoxIndex;
	private double colBounce;
	
	boolean contained = false;
	
	// Will be binded to corresponding JFX Group translateX/Y property.
	private DoubleProperty[] pos;
	
	// Will be binded to corresponding Animate dir property.
	private StringProperty lstnDir;
	
	// Will be binded to corresponding CharacterSprite isMoving property.
	private BooleanProperty isMv;
	private BooleanProperty isAttk;
	
	// Will be binded to ArenaPerson and represents the idx of ArenaPerson that 
	// was hit.
	private IntegerProperty personHit;
	
	private ObservableBooleanValue isHitBoxPlaced;
	
	// Listener for isAttk when set to false.
	private ChangeListener<? super Boolean> onAttkEnd = new ChangeListener<Boolean>() {
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
				isMv.set(false);
			}
			else {
				personHit.set(-1);
			}
		}
	};
	
	// To detect a hit. Listener for isHitBoxPlaced when true.
	private ChangeListener<? super Boolean> onHitBoxPlaced = new ChangeListener<Boolean>() {
		public void changed(
				ObservableValue<? extends Boolean> attk,
				Boolean oldVal,
				Boolean newVal
		){
			/*
			 * TRIGGERED WHEN HITBOX IS PLACED IN CharacterSprite. 
			*/
			if (newVal.booleanValue()) {
				ArrayList<CollisionBox> hurtBoxes = WorldData.getBoxesWithinRange(ColType.HURTBOX, getColBoxIndex(), false);
				CollisionBox hitBox = WorldData.getBoxes(ColType.HITBOX).get(getColBoxIndex());
				if (hurtBoxes == null || hitBox == null) {
					return;
				}
				
				CollisionBox currHurtBox;
				if (hitBox != null) {
					for (int b = 0; b < hurtBoxes.size(); ++b) {
						
						currHurtBox = hurtBoxes.get(b);
						if (hitBox.getBounds().intersects(currHurtBox.getBounds())) {
							personHit.set(WorldData.getBoxes(ColType.HURTBOX).indexOf(currHurtBox));
							setMvRate(getMvRate() * 0.01);
						}
					}
				}
			}
		}
	};
	
	// Update mvRate when ArenaPerson increases speed stat.
	private ChangeListener<? super Number> onMaxRateChange = new ChangeListener<Number>() {
		public void changed(
				ObservableValue<? extends Number> rate,
				Number oldVal,
				Number newVal
		){
			/*
			 * TRIGGERED WHEN PERSONS SPEED STAT CHANGES.
			*/
			
			setMaxMvRate(newVal.doubleValue());
	    	incMvRate = getMaxMvRate() / (5.0 + (newVal.doubleValue() * 4.0));
		}
	};


//CONSTRUCTORS---------------------------------------------------------------------

    public Movement() {
        /**
         * Default Constructor for class Movement.
        */
    	
    	maxRate = new SimpleDoubleProperty(1.25);
    	maxMvRate = maxRate.get();
    	incMvRate = getMaxMvRate() / (5.0 + (getMaxMvRate() * 4.0));
    	mvRate = incMvRate;
    	dx = 0.0;
    	dy = 0.0;
    	colBounce = 0.1;
    	
    	dir = Going.S;
    	lstnDir = new SimpleStringProperty();
    	
    	pos = new DoubleProperty[] {new SimpleDoubleProperty(), new SimpleDoubleProperty()};
    	pos[0].set(0.0);
    	pos[1].set(0.0);
    	
    	isMv = new SimpleBooleanProperty(false);
    	isAttk = new SimpleBooleanProperty(false);
    	isAttk.addListener(onAttkEnd);
    	
    	personHit = new SimpleIntegerProperty(0);
    }
    
//SETTERS--------------------------------------------------------------------------
    
    public void setMaxRate(DoubleProperty speedProperty) {
    	/*
    	 * 
    	*/
    	
    	maxRate = speedProperty;
    	maxRate.addListener(onMaxRateChange);
    }
    
    public void setMaxMvRate(double rate) {
    	/*
    	 * 
    	*/
    	
    	maxMvRate = rate;
    }

    public void setMvRate(double rate) {
        /**
         * Setter for field: mvRate
        */
    	
    	if (mvRate > getMaxMvRate()) {
    		mvRate = getMaxMvRate();
    	}
    	else if (mvRate < 0.0000) {
    		mvRate = 0.0000;
    	}
    	else {
    		mvRate = rate;
    	}
    }
    
    public void setDx(double dx) {
        /**
         * Setter for field: dx
        */
    	
    	this.dx = dx;
    }
    
    public void setDy(double dy) {
        /**
         * Setter for field: dy
        */

    	this.dy = dy;
    }
    
    public void setDir() {
        /**
         * Setter for field: direction
        */
    	
    	if (!isHitBoxPlaced().get() && !isAttk().get()) {
	    	double absDx = Math.abs(getDx());
	    	double absDy = Math.abs(getDy());
    	
			if (getDx() > 0.0 && getDy() < 0.0) {
				
				if (absDy > absDx) {
					dir = Going.NE;
				}
				else {
					dir = Going.EN;
				}
			}
			else if (getDx() < 0.0 && getDy() < 0.0) {
				
				if (absDy > absDx) {
					dir = Going.NW;
				}
				else {
					dir = Going.WN;
				}
			}
			
			else if (getDx() > 0.0 && getDy() > 0.0) {
				
				if (absDy > absDx) {
					dir = Going.SE;
				}
				else {
					dir = Going.ES;
				}
			}
			else if (getDx() < 0.0 && getDy() > 0.0) {
				
				if (absDy > absDx) {
					dir = Going.SW;
				}
				else {
					dir = Going.WS;
				}
			}
			
			else if (getDy() < 0.0) {
				dir = Going.N;
			}
			else if (getDx() > 0.0) {
				dir = Going.E;
			}
			else if (getDy() > 0.0) {
				dir = Going.S;
			}
			else if (getDx() < 0.0) {
				dir = Going.W;
			}
			lstnDir.set(getSimpleDir().toString());
    	}

    }
    
    public void forceDir(Going dir) {
    	/*
    	 * 
    	*/
    	
    	this.dir = dir;
    	lstnDir.set(getSimpleDir().toString());
    }
    
    public void setColBoxIndex(int colBoxIndex) {
        /**
         * Setter for field:
        */

    	this.colBoxIndex = colBoxIndex;
    }
    
    public void setColBounce(double colBounce) {
        /**
         * Setter for field:
        */

    	this.colBounce = colBounce;
    }
    
    public void setPos(double x, double y) {
    	/*
    	 * 
    	*/
    	
    	pos[0].set(x);
    	pos[1].set(y);
    }
    
    public void setIsHitBoxPlaced(BooleanProperty placed) {
    	/*
    	 * 
    	*/
    	
    	isHitBoxPlaced = placed;
    	isHitBoxPlaced.addListener(onHitBoxPlaced);
    }

//GETTERS--------------------------------------------------------------------------
    
    public ObservableDoubleValue getMaxRate() {
    	/*
    	 * 
    	*/
    	
    	return maxRate;
    }
    
    public double getMaxMvRate() {
    	/*
    	 * 
    	*/
    	
    	return maxMvRate;
    }

    public double getMvRate() {
        /**
         * Getter for field: mvRate
        */

    	return mvRate;
    }
    
    public double getIncMvRate() {
    	/*
    	 * 
    	*/
    	
    	return incMvRate;
    }
    
    public double getDx() {
        /**
         * Getter for field: dx
        */
    	
    	return dx;
    }
    
    public double getDy() {
        /**
         * Getter for field: dy
        */
    	
    	return dy;
    }
    
    public Going getDir() {
        /**
         * Getter for field: direction
        */
    	
    	return dir;
    }
    
    public StringProperty lstnDir() {
    	/*
    	 * 
    	*/
    	
    	return lstnDir;
    }
    
    public Going getSimpleDir() {
        /**
         * Getter for field: direction
        */
    	
    	if (dir == Going.NE || dir == Going.NW) {
    		dir = Going.N;
    	}
    	else if (dir == Going.EN || dir == Going.ES) {
    		dir = Going.E;
    	}
    	else if (dir == Going.SE || dir == Going.SW) {
    		dir = Going.S;
    	}
    	else if (dir == Going.WN || dir == Going.WS) {
    		dir = Going.W;
    	}
    	
    	return dir;
    }
    
	public double getNormalX() {
		/**
		 * This method will return the correct value for dx when the player is 
		 * moving diagonally.
		*/
		
		double normalX;
		
		normalX = 1.0;
		if (getSpeed() > 0) {
			
			if (mvRate / getSpeed() > 0) {
				
				normalX = mvRate / getSpeed();
			}
		}
		
		return normalX;
	}
	
	public double getNormalY() {
		/**
		 * This method will return the correct value for dx when the player is 
		 * moving diagonally.
		*/
		
		double normalY;
		
		normalY = 1.0;
		if (getSpeed() > 0) {
			
			if (mvRate / getSpeed() > 0) {
				
				normalY = mvRate / getSpeed();
			}
		}
		
		return normalY;
	}
	
	public double getSpeed() {
		/**
		 * This method will return the value at which the player is moving 
		 * diagonally.
		*/
		
		double speed;
		
		speed = Math.sqrt( Math.pow(mvRate, 2) + Math.pow(mvRate, 2) );
	
		if (!(speed > 0 || speed < 0)) {
			speed = 1.0;
		}
		
		return speed;
	}
	
    public int getColBoxIndex() {
        /**
         * Getter for field:
        */
    	
    	return colBoxIndex;

    }
    
    public double getColBounce() {
        /**
         * Getter for field:
        */
    	
    	return colBounce;

    }
    
    public boolean getContained() {
        /**
         * Getter for field:
        */

    	return contained;
    }
    
    public DoubleProperty getPos(int axis) {
        /**
         * Getter for field: dy
        */
    	
    	return pos[axis];
    }
    
    public BooleanProperty isMv() {
    	/*
    	 * 
    	*/
    	
    	return isMv;
    }
    
    public BooleanProperty isAttk() {
    	/*
    	 * 
    	*/
    	
    	return isAttk;
    }
    
    public ObservableBooleanValue isHitBoxPlaced() {
    	/*
    	 * 
    	*/
    	
    	return isHitBoxPlaced;
    }
    
    public IntegerProperty personHit() {
    	/*
    	 * 
    	*/
    	
    	return personHit;
    }
    
//MOVEMENT-------------------------------------------------------------------------
    
    public abstract void move();
    
    public void translate() {
    	/*
    	 * This method will move the Npc's sprite group on the stage and increment
    	 * its mvRate.
    	*/
    		
    		if (isMv.get()) {
        		setMvRate(getMvRate() + getIncMvRate());
    		}
    		
    		if (isAttk.get()) {
    			setMvRate(getMaxMvRate() * 0.35);
    		}
    		
			setPos(getPos(0).get() + getDx(), getPos(1).get() + getDy());
    		
    }
//COLLISION------------------------------------------------------------------------
	public void checkCollision() {
		/**
		 * This method will determine where two boxes intersect and nullify or 
		 * reverse dx/dy accordingly.
		 * 
		 * WILL ONLY CHECK FOR BOXES WITHIN RANGE.
		*/
		
		ArrayList<CollisionBox> boxes = WorldData.getBoxesWithinRange(ColType.WORLDBOX, getColBoxIndex(), false);
		
		if (boxes == null) {
			return;
		}
		
		// Character's current worldBox bounds.
		CollisionBox charBox = WorldData.getBoxes(ColType.WORLDBOX).get(getColBoxIndex());
		Bounds charBounds = charBox.getBounds();
		
		// Will store the worldBox bounds of the worldBox being checked.
		Bounds checkBounds;
		
		
		for (int b = 0; b < boxes.size(); ++b) {
			
			// Will store the worldBox bounds of the worldBox being checked.
			checkBounds = boxes.get(b).getBounds();
			
			if (charBounds.intersects(checkBounds)) {
				
				// Character's current center, min, and max X/Y values.
				double currX = charBounds.getCenterX();
				double maxX  = charBounds.getMaxX();
				double maxY  = charBounds.getMaxY();
				double minX  = charBounds.getMinX();
				double minY  = charBounds.getMinY();
				
				// Will store the center, min, and max X/Y values of the worldBox being checked.
				double checkX    = checkBounds.getCenterX();
				double checkMaxX = checkBounds.getMaxX();
				double checkMaxY = checkBounds.getMaxY();
				double checkMinX = checkBounds.getMinX();
				double checkMinY = checkBounds.getMinY();
				
				// The modifier that the Character will be pushed back upon colliding with 
				// another worldBox.
				double bounce = getColBounce();
				
				// For intersection purposes allows greater detection range.
				double pad = 3.0;
				
				boolean w;
				boolean a;
				boolean s;
				boolean d;
				
				// Will determine where the intersection is occuring.
				w = (minY + pad) > checkMaxY;                     // up
				a = (currX > checkX && (minX + pad) > checkMaxX); // left
				s = (maxY - pad) < checkMinY;                     // down
				d = currX < checkX && (maxX - pad) < checkMinX;   // right
					
				if (
					(getDx() > 0.0 && d) 
					&&
					(getDy() > 0.0 && s)
					) { // right - down
					setDx(-getDx() * bounce);
					setDy(-getDy() * bounce);
				}
				else if (
						(getDx() > 0.0 && d) 
						&&
						(getDy() < 0.0 && w)
						) { // right - up
					setDx(-getDx() * bounce);
					setDy(-getDy() * bounce);
				}
				else if (
						(getDx() < 0.0 && a)
						&&
						(getDy() > 0.0 && s)
						) { // left - down
					setDx(-getDx() * bounce);
					setDy(-getDy() * bounce);
				}
				else if (
						(getDx() < 0.0 && a)
						&&
						(getDy() < 0.0 && w)
						) { // left - up
					setDx(-getDx() * bounce);
					setDy(-getDy() * bounce);
				}
				else if (getDx() > 0.0 && d) { // right
					setDx(-getDx() * bounce);
				}
				else if (getDx() < 0.0 && a) { //left
					setDx(-getDx() * bounce);
				}
				else if (getDy() < 0.0 && w) { //up
					setDy(-getDy() * bounce);
				}
				else if (getDy() > 0.0 && s) { //down
					setDy(-getDy() * bounce);
				}
			
			}
		}
		
	}
}