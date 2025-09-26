package window;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sprite.charSprite.CharacterSprite;
import worldStage.StageOne;
import worldStage.WorldStage;

public class Main extends Application {
	/**
	 * 
	*/
	
	private WorldStage currStage;
	
	private Scene  scene;
	
	private Controller controller;
	private EventHandler<KeyEvent> onPress;
	private EventHandler<KeyEvent> onRelease;
	private EventHandler<MouseEvent> onMouseClick;
	Timer mvTimer;
	
	
	public void init() {
		/**
		 * Runs on program launch.
		*/
		
		currStage = new StageOne(this);
		
	}
	
    public void start(Stage stage) throws IOException {
    	/**
    	 * Runs after init() 
    	*/
		
		// Create new scene with
		scene = new Scene(currStage.getBackground(), 500, 500);
		
		// Create new Controller object and map keys.
		controller = new Controller();
		
		onPress = new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				
				String eventType = event.getEventType().getName();
				KeyCode key = event.getCode();
				
				switch (key) {
				case W:
				case A:
				case S:
				case D:
					controller.playerMovement(eventType, key);
					break;
				default:
					break;
				
				}
				
			}
		};
		
		onRelease = new EventHandler<KeyEvent>() {
			
			public void handle(KeyEvent event) {
				
				String eventType = event.getEventType().getName();
				KeyCode key = event.getCode();
				
				switch (key) {
				case W:
				case A:
				case S:
				case D:
					controller.playerMovement(eventType, key);
					break;
				case V:
					for (int box = 0; box < currStage.getWorldBoxes().length; ++box) {
						
						if (currStage.getWorldBoxes()[box].getColBox().getOpacity() != 0) {
							currStage.getWorldBoxes()[box].getColBox().setOpacity(0);
						}
						else {
							currStage.getWorldBoxes()[box].getColBox().setOpacity(100);
						}
					}
					break;
				default:
					break;
				}
			}
		};
		
		onMouseClick = new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent event) {
				
				MouseButton button; 
				button = event.getButton();
				controller.mbPressed(button);
			}
		};
		
		
		
		scene.setOnKeyPressed(onPress);
		scene.setOnKeyReleased(onRelease);
		scene.setOnMouseClicked(onMouseClick);
		
        stage.setTitle("ARENA");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        
        
        mvTimer = new Timer();
        mvTimer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				Platform.runLater(() -> currStage.runPMvmnt());
			}
        	
        }, 0, 32);
        
        mvTimer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				Platform.runLater(() -> currStage.runNpcMvmnt());
				
			}
        	
        }, 0, 750);
        
        mvTimer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				Platform.runLater(() -> currStage.runAnim());
			}
        	
        }, 0, 128);
        
        
        currStage.onLaunch();
        
    }

//GETTERS--------------------------------------------------------------------------  
    
    public Controller getController() {
    	/**
    	 * 
    	*/
    	
    	return controller;
    }
    
    public WorldStage getStage() {
    	/**
    	 * 
    	*/

    	return currStage;
    }
    
//MAIN-----------------------------------------------------------------------------
    
    public static void main(String args[]) {
    	/**
    	 * This program will call init() - > start()  
    	*/
    	
    	launch(args);
    }
}