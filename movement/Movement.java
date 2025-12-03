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

import collision.CollisionBox;
import collision.CollisionCheck;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.util.Duration;
import sprite.charSprite.CharacterSprite;
import worldStage.WorldStage;
import java.util.ArrayList;
import animate.Animate;
import arenaEnum.ColType;

public abstract class Movement{
    /**
     *
    */
	
	public enum Going {
		/*
		 * Associated with dx/dy 
		*/
		N,
		E,
		S,
		W,
		
		NE,
		NW,
		
		EN,
		ES,
		
		SE,
		SW,
		
		WN,
		WS
	}
	
	private final int BASE_ANIM_FRAME_RATE = 128;
	
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
	public static ArrayList<ArrayList<CollisionBox>> colBoxes;
	
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
				ArrayList<CollisionBox> hurtBoxes = colBoxes.get(1);
				CollisionBox hitBox = colBoxes.get(3).get(getColBoxIndex());
				CollisionBox currHurtBox;
				
				
				if (hitBox != null) {
					for (int b = 0; b < hurtBoxes.size(); ++b) {
						
						currHurtBox = hurtBoxes.get(b);
						if (b != getColBoxIndex() && currHurtBox != null) {
							
							if (hitBox.getBounds().intersects(currHurtBox.getBounds())) {
								personHit.set(b);
								setMvRate(getMvRate() * 0.01);
							}
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
	    	incMvRate = getMaxMvRate() / (4.0 + (newVal.doubleValue() * 2.0));
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
    
    public static void setColBoxes(ArrayList<ArrayList<CollisionBox>> boxes) {
    	/*
    	 * 
    	*/
    	
    	colBoxes = boxes;
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
    
    public ArrayList<CollisionBox> getBoxes(ColType boxType) {
    	/**
    	 * This method will return the ArrayList in colBoxes that matches the
    	 * given type.
    	 * @see WorldData.java for ColType idx in colBoxes.
    	*/
    	
    	ArrayList<CollisionBox> boxes;
    	
    	switch (boxType) {
    	case WORLDBOX:
    		boxes = colBoxes.get(0);
    		break;
    	case HURTBOX:
    		boxes = colBoxes.get(1);
    		break;
    	case CHECKBOX:
    		boxes = colBoxes.get(2);
    		break;
    	case DETECTBOX:
    		boxes = colBoxes.get(4);
    		break;
    	default:
    		boxes = null;
    		break;
    	}
    	
    	return boxes;
    }
    
    public CollisionBox getClosestBox(ColType boxType) {
    	/*
    	 * This method will return the CollisionBox of the given type that is
    	 * closest to its worldBox.
    	*/
    	
    	// All worldBoxes boxes and myBox at colBoxIndex.
    	ArrayList<CollisionBox> boxes = getBoxes(boxType);
    	CollisionBox myBox = getBoxes(ColType.WORLDBOX).get(getColBoxIndex());
    	CollisionBox otherBox;
    	
    	// Checking by midY
    	double myX = myBox.getMidX();
    	double myY = myBox.getMidY();
    	double otherX;
    	double otherY;
    	
    	// Absolute value of myX - otherX
    	double diffX;
    	double diffY;
    	
    	// Will store lowest value of diffX + diffY.
    	double closestDiff = 1000;
    	int closestIdx = 0;
    	
    	// For each worldBox
    	for (int b = 0; b < boxes.size(); ++b) {
    		
    		otherBox = boxes.get(b);
    		if (b != getColBoxIndex() && otherBox != null) {
    			
    			// Get mid x/y vals for otherBox
	    		otherX = otherBox.getMidX();
	    		otherY = otherBox.getMidY();
	    		
	    		// Calculate difference between points.
	    		diffX = Math.abs(Math.abs(myX) - Math.abs(otherX));
	    		diffY = Math.abs(Math.abs(myY) - Math.abs(otherY));
	    		if (diffX + diffY < closestDiff) {
	    			closestDiff = diffX + diffY;
	    			closestIdx = b;
	    		}
    		}
    	}
    	
    	return boxes.get(closestIdx);
    }
    
    public ArrayList<CollisionBox> getBoxesWithinRange(ColType boxType, boolean useCheckBox) {
    	/*
    	 * This method will return all CollisionBoxes of given type in range 50.
    	 * If useCheckBox is true, will check range from checkBox bounds instead 
    	 * of worldBoxBounds.
    	*/
    	
    	final int BOX_LIMIT = 4;
    	final double RANGE = 50;
    	ArrayList<CollisionBox> withinRange;
    	
    	// All worldBoxes boxes and myBox at colBoxIndex.
    	ArrayList<CollisionBox> boxes = getBoxes(boxType);
    	CollisionBox myBox;
    	if (useCheckBox) {
        	myBox = getBoxes(ColType.CHECKBOX).get(getColBoxIndex());
    	}
    	else {
    		myBox = getBoxes(ColType.WORLDBOX).get(getColBoxIndex());
    	}
    	CollisionBox otherBox;
    	
    	// Checking by midY
    	double myX = myBox.getMidX();
    	double myY = myBox.getMidY();
    	double otherX;
    	double otherY;
    	
    	// Absolute value of myX - otherX
    	double diffX;
    	double diffY;
    	
    	// For each worldBox
    	withinRange = new ArrayList();
    	for (int b = 0; b < boxes.size() && withinRange.size() < BOX_LIMIT; ++b) {
    		
    		otherBox = boxes.get(b);
    		if (b != getColBoxIndex() && otherBox != null) {
    			
    			// Get mid x/y vals for otherBox
	    		otherX = otherBox.getMidX();
	    		otherY = otherBox.getMidY();
	    		
	    		// Calculate difference between points.
	    		diffX = Math.abs(Math.abs(myX) - Math.abs(otherX));
	    		diffY = Math.abs(Math.abs(myY) - Math.abs(otherY));
	    		if (diffX + diffY < RANGE) {
	    			withinRange.add(otherBox);
	    		}
    		}
    	}
    	
    	if (withinRange.isEmpty()) {
    		return null;
    	}
    	else {
    		return withinRange;
    	}
    }
    
	public void checkCollision() {
		/**
		 * This method will determine where two boxes intersect and nullify or 
		 * reverse dx/dy accordingly.
		 * 
		 * WILL ONLY CHECK FOR BOXES WITHIN RANGE.
		*/
		
		ArrayList<CollisionBox> boxes = getBoxesWithinRange(ColType.WORLDBOX, false);
		
		if (boxes == null) {
			return;
		}
		
		// Character's current worldBox bounds.
		CollisionBox charBox = getBoxes(ColType.WORLDBOX).get(getColBoxIndex());
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