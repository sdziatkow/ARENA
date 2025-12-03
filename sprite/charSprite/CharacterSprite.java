package sprite.charSprite;

/**
 * Program Name:    CharacterSprite.java
 *<p>
 * Purpose:         The purpose of this program is to create a system that all
 * 					ArenaCharacter sprites can use to be easily managed.
 *<p>
 * @version         0.0
 *<p>
 * Created:         May 19, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import collision.CollisionBox;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import movement.Movement.Going;
import sprite.weaponSprite.WeaponSprite;
import ui.overlay.StatBar;

public abstract class CharacterSprite{
    /**
     *
    */
	
	// Will store entire character PNG file.
	private Image spriteSheet;
	
	// Will store sprites for each mvmnt direction.
	private int mvFramesPerDir;
	private int attkFramesPerDir;
	private WritableImage[][] mvFrames;
	private WritableImage[][] attkFrames;
	
	// Will store sub image of playerSheet.
	private ImageView spriteView;
	private Group     spriteGroup;
	
	private CollisionBox worldBox;
	private CollisionBox hurtBox;
	private CollisionBox hitBox;
	private CollisionBox detectBox;
	private CollisionBox checkBox;
	private StatBar hpBar;
	
	// Will store spriteView and worldBox.getColBox() -> Rectangle
	private StackPane charPane;
	
	private WeaponSprite weaponSprite;
	
	// Will be binded to given Animate frameCount val.
	private ObservableIntegerValue mvFrameCount;
	private ObservableIntegerValue attkFrameCount;
	private ObservableStringValue dir;
	
	// Will be binded to given Movement isMv val.
	private ObservableBooleanValue isMoving;
	
	private BooleanProperty isHitBoxPlaced;
	
	// When a frameCount has changed, switch the sprite image to the corresponding 
	// dirCount -n(orth), e(ast), s(outh), w(est).
	private ChangeListener<? super Number> mvFrameChange = new ChangeListener<Number>() {
		public void changed(
				ObservableValue<? extends Number> cnt, 
				Number oldVal,
				Number newVal
		){
			
			int nextDir;
			if (dir.get().equals("N")) {
				nextDir = 0;
			}
			else if (dir.get().equals("E")) {
				nextDir = 1;
			}
			else if (dir.get().equals("S")) {
				nextDir = 2;
			}
			else if (dir.get().equals("W")) {
				nextDir = 3;
			}
			else {
				nextDir = 0;
			}
			
			if (isMoving.get()) {
				setSpriteImg(getMvFrames(nextDir)[oldVal.intValue()]);
			}
			else {
				setSpriteImg(getMvFrames(nextDir)[0]);
			}
		}
	};
	
	private ChangeListener<? super Number> attkFrameChange = new ChangeListener<Number>() {
		public void changed(
				ObservableValue<? extends Number> cnt, 
				Number oldVal,
				Number newVal
				){
			
			int nextDir;
			if (dir.get().equals("N")) {
				nextDir = 0;
			}
			else if (dir.get().equals("E")) {
				nextDir = 1;
			}
			else if (dir.get().equals("S")) {
				nextDir = 2;
			}
			else if (dir.get().equals("W")) {
				nextDir = 3;
			}
			else {
				nextDir = 0;
			}
			
			setSpriteImg(getAttkFrames(nextDir)[oldVal.intValue()]);
			getWeaponSprite().setSpriteImg(getWeaponSprite().getFrames(nextDir)[oldVal.intValue()]);
			placeHitBox(dir.get());
			
			if (newVal.intValue() ==  0) {
				getCharPane().getChildren().remove(getWeaponSprite().getSpriteView());
			}
		}
	};
	

//CONSTRUCTORS---------------------------------------------------------------------

    public CharacterSprite(int mvFramesPerDir, int attkFramesPerDir) {
        /**
         * Default Constructor for class
        */
    	
    	this.mvFramesPerDir = mvFramesPerDir;
    	this.attkFramesPerDir = attkFramesPerDir;
    	
    	mvFrames = new WritableImage[4][mvFramesPerDir];
    	attkFrames = new WritableImage[4][attkFramesPerDir];
    	
    	weaponSprite = new WeaponSprite();
    	
    	charPane = new StackPane();
    	charPane.setCache(true);

    	isHitBoxPlaced = new SimpleBooleanProperty(false);
    	
		hpBar = new StatBar();
		hpBar.getBar().setStyle(
				"-fx-accent: maroon; "
				+ "-fx-min-width: 32px; "
				+ "-fx-max-width: 32px; "
				+ "-fx-min-height: 10px; "
				+ "-fx-max-height: 10px;"
		);
    }
    
//SETTERS--------------------------------------------------------------------------
    
    public void setSpriteSheet(Image spriteSheet) {
    	/**
    	 * 
    	*/
    	
    	this.spriteSheet = spriteSheet;
    }
    
    public void setMvFrames(int dir, WritableImage[] frames) {
    	/*
    	 * 
    	*/
    	
    	mvFrames[dir] = frames;
    }
    
    public void setAttkFrames(int dir, WritableImage[] frames) {
    	/*
    	 * 
    	*/
    	
    	attkFrames[dir] = frames;
    }
    
	public void setSpriteView(ImageView spriteView) {
		/**
		 * This method will set the current Image of ImageView currSprite to 
		 * sprite.
		 * 
		 * @param sprite: A WritableImage object containing a player sprite.
		*/
		
		this.spriteView = spriteView;
		
		this.spriteView.setCache(true);
		
	}
    
	public void setSpriteImg(WritableImage img) {
		/**
		 * This method will set the current Image of ImageView currSprite to 
		 * sprite.
		 * 
		 * @param sprite: A WritableImage object containing a player sprite.
		*/
		
		spriteView.setImage(img);
		
	}
	
	public void setWorldBox(CollisionBox worldBox) {
		/**
		 * 
		*/
		
		this.worldBox = worldBox;
	}
	
	public void setHurtBox(CollisionBox hurtBox) {
		/*
		 * 
		*/
		
		this.hurtBox = hurtBox;
	}
	
	public void setHitBox(CollisionBox hitBox) {
		/*
		 * 
		*/
		
		this.hitBox = hitBox;
	}
	
	public void setDetectBox(CollisionBox detectBox) {
		/*
		 * 
		*/
		
		this.detectBox = detectBox;
	}
	
	public void setCheckBox(CollisionBox checkBox) {
		/*
		 * 
		*/
		
		this.checkBox = checkBox;
	}
	
	public void setSpriteGroup(Group spriteGroup) {
		/**
		 * 
		*/
		
		this.spriteGroup = spriteGroup;
		
		this.spriteGroup.setCache(true);
	}
	
	public void setWeaponSprite(WeaponSprite sprite) {
		/*
		 * 
		*/
		
		this.weaponSprite = sprite;
	}
	
	public void setFrameCounts(IntegerProperty mvCnt, IntegerProperty attkCnt) {
		/*
		 * 
		*/
		
		mvFrameCount = mvCnt;
		attkFrameCount = attkCnt;
		
		mvFrameCount.addListener(mvFrameChange);
		attkFrameCount.addListener(attkFrameChange);
	}
	
	public void setIsMoving(BooleanProperty isMv) {
		/*
		 * 
		*/
		
		isMoving = isMv;
	}
	
	public void setDir(StringProperty dir) {
		/*
		 * 
		*/
		
		this.dir = dir;
	}

//GETTERS--------------------------------------------------------------------------
	
	public int getMvFramesPerDir() {
		/**
		 * 
		*/
		
		return mvFramesPerDir;
	}
	
	public int getAttkFramesPerDir() {
		/**
		 * 
		*/
		
		return attkFramesPerDir;
	}
	
	public WritableImage[] getMvFrames(int dir) {
		/*
		 * 
		*/
		
		return mvFrames[dir];
	}
	
	public WritableImage[] getAttkFrames(int dir) {
		/*
		 * 
		*/
		
		return attkFrames[dir];
	}
    
	public Image getSpriteSheet() {
		/**
		 * This method will return an Image object of the entire player sprite 
		 * sheet.
		*/
		
		return spriteSheet;
	}
	
	public ImageView getSpriteView() {
		/**
		 * This method will return an ImageView object containing the an Image o
		 * object of the current sprite.
		*/
		
		return spriteView;
	}
	
	public CollisionBox getWorldBox() {
		/**
		 * 
		*/
		
		return worldBox;
	}
	
	public CollisionBox getHurtBox() {
		/*
		 * 
		*/
		
		return hurtBox;
	}
	
	public CollisionBox getHitBox() {
		/*
		 * 
		*/
		
		return hitBox;
	}
	
	public CollisionBox getDetectBox() {
		/*
		 * 
		*/
		
		return detectBox;
	}
	
	public CollisionBox getCheckBox() {
		/*
		 * 
		*/
		
		return checkBox;
	}
	
	public StatBar getHpBar() {
		/*
		 * 
		*/
		
		return hpBar;
	}
	
	public StackPane getCharPane() {
		/**
		 * 
		*/
		
		return charPane;
	}
	
	public Group getSpriteGroup() {
		/**
		 * This method will return a group containing ImageView currSprite.
		*/
		
		return spriteGroup;
	}
	
	public WeaponSprite getWeaponSprite() {
		/*
		 * 
		*/
		
		return weaponSprite;
	}
	
	public BooleanProperty isHitBoxPlaced() {
		/*
		 * 
		*/
		
		return isHitBoxPlaced;
	}
	
//HIT-BOX-PLACEMENT----------------------------------------------------------------
	
	public void placeHitBox(String dir) {
		/*
		 * 
		*/
		
		if (!getSpriteGroup().getChildren().contains(getHitBox().getColBox())) {
			getHitBox().getColBox().setTranslateX(getWeaponSprite().getHitBoxTranslate(dir)[0]);
			getHitBox().getColBox().setTranslateY(getWeaponSprite().getHitBoxTranslate(dir)[1]);
			getSpriteGroup().getChildren().add(getHitBox().getColBox());
			isHitBoxPlaced.set(true);
		}
		else {
			getHitBox().getColBox().setTranslateX(0);
			getHitBox().getColBox().setTranslateY(0);
			getSpriteGroup().getChildren().remove(getHitBox().getColBox());
			isHitBoxPlaced.set(false);
		}
		
		if (!getCharPane().getChildren().contains(getWeaponSprite().getSpriteView())) {
			getCharPane().getChildren().add(getWeaponSprite().getSpriteView());
			
			if (dir.equals("N")) {
				getCharPane().getChildren().getFirst().setViewOrder(1);
				getCharPane().getChildren().getLast().setViewOrder(0);
			}
			else {
				getCharPane().getChildren().getFirst().setViewOrder(0);
				getCharPane().getChildren().getLast().setViewOrder(1);
			}
		}
		
		
	}
}