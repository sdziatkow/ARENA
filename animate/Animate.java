package animate;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;
import movement.Movement.Going;

/**
 * Program Name:    Animate.java
 *<p>
 * Purpose:         The purpose of this program is to have a general system for 
 * 					handling animations of all types.
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 20, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import sprite.charSprite.CharacterSprite;

public class Animate{
    /**
     *
    */
	
	public static final int BASE_MV_FRAME_RATE = 128;
	public static final int BASE_ATTK_FRAME_RATE = (int)(BASE_MV_FRAME_RATE * 2);

	private int mvFramePerDir;
	private int attkFramePerDir;
	
	// Timelines hold the keyframes
	private Timeline mvAnim;
	private Timeline attkAnim;
	private KeyFrame mvFrame;
	private KeyFrame attkFrame;
	
	// Associated with Timeline or KeyFrame.
	private EventHandler<ActionEvent> onMvFrameFinish;
	private EventHandler<ActionEvent> onAttkFrameFinish;
	private EventHandler<ActionEvent> onAttkAnimFinish;
	
	// Will be binded by Movement isAttk
	private BooleanProperty isMoving;
	private BooleanProperty isAttk;
	
	// Will be binded to corresponding Sprite
	private IntegerProperty mvFrameCount;
	private IntegerProperty attkFrameCount;
	
	// Listener for isAttk when set to false.
	private ChangeListener<? super Boolean> onMove = new ChangeListener<Boolean>() {
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
				mvAnim.play();
			}
			else {
				mvAnim.pause();
				incMvFrameCount();
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
				attkAnim.play();
			}
		}
	};

//CONSTRUCTORS---------------------------------------------------------------------

    public Animate(int maxFramesPerDir) {
        /**
         * Default Constructor for class
        */
    	
    	mvFramePerDir = maxFramesPerDir;
    	attkFramePerDir = maxFramesPerDir;
    	mvFrameCount = new SimpleIntegerProperty(0);
    	attkFrameCount = new SimpleIntegerProperty(0);
    	
    	// Create event handler for when a movement frame is finished.
    	onMvFrameFinish = new EventHandler<ActionEvent>() {
    		/*
    		 * Call the animate method each time a frame ends.
    		*/
    		
    		public void handle(ActionEvent e) {
    			
    			incMvFrameCount();
    		}
    	};
    	
    	// Create event handler for when an attk frame is finished.
    	onAttkFrameFinish = new EventHandler<ActionEvent>() {
    		/*
    		 * Call the animate method each time a frame ends.
    		*/
    		
    		public void handle(ActionEvent e) {
    			
    			incAttkFrameCount();
    		}
    	};
    	onAttkAnimFinish = new EventHandler<ActionEvent>() {
    		/*
    		 * 
    		*/
    		
    		public void handle(ActionEvent e) {
    			isAttk.set(false);
    			incMvFrameCount();
    		}
    	};
    	
    	mvFrame = new KeyFrame(new Duration(BASE_MV_FRAME_RATE), onMvFrameFinish);
    	attkFrame = new KeyFrame(new Duration(BASE_ATTK_FRAME_RATE), onAttkFrameFinish);
    	
    	
    	mvAnim = new Timeline(mvFrame);
    	mvAnim.setCycleCount(Timeline.INDEFINITE);
    	
    	attkAnim = new Timeline(attkFrame);
    	attkAnim.setCycleCount(4);
    	attkAnim.setOnFinished(onAttkAnimFinish);
    }

//SETTERS--------------------------------------------------------------------------
    
    public void setFramesPerDir(int mvFramePerDir, int attkFramePerDir) {
    	/*
    	 * 
    	*/
    	
    	this.mvFramePerDir = mvFramePerDir;
    	this.attkFramePerDir = attkFramePerDir;
    }
    
    public void setIsMoving(BooleanProperty isMv) {
    	/*
    	 * 
    	*/
    	
    	isMoving = isMv;
    	isMoving.addListener(onMove);
    }
    
    public void setIsAttk(BooleanProperty isAttk) {
    	/*
    	 * 
    	*/
    	
    	this.isAttk = isAttk;
    	this.isAttk.addListener(onAttk);
    }

    public void incMvFrameCount() {
        /**
         * Setter for field:
        */
    	
    	int newVal = mvFrameCount.get() + 1;
    	
    	if (newVal >= mvFramePerDir) {
    		mvFrameCount.set(0);
    	}
    	else {
        	mvFrameCount.set(newVal);
    	}

    }
    
    public void incAttkFrameCount() {
    	/**
    	 * 
    	*/
    	
    	int newVal = attkFrameCount.get() + 1;
    	
    	if (newVal >= attkFramePerDir) {
    		attkFrameCount.set(0);
    	}
    	else {
        	attkFrameCount.set(newVal);
    	}
    }

//GETTERS--------------------------------------------------------------------------
    
    public IntegerProperty getMvCount() {
    	/*
    	 * 
    	*/
    	
    	return mvFrameCount;
    }
    
    public IntegerProperty getAttkCount() {
    	/*
    	 * 
    	*/
    	
    	return attkFrameCount;
    }

//
    
//    public void play() {
//    	
//    	if (!isAttk.get()) {
//    		
//	    	if (!mvAnim.getStatus().equals(Animation.Status.RUNNING)) {
//	    		mvAnim.play();
//	    	}
//    	}
//    	else if (!attkAnim.getStatus().equals(Animation.Status.RUNNING)) {
//    		mvAnim.pause();
//    		attkAnim.play();
//    	}
//    }
}