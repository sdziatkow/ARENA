package window;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import worldStage.StageOne;
import worldStage.WorldStage;

public class Main extends Application {
	/**
	 * 
	*/
	
	private WorldStage currStage;
	
	private Stage stage;
	private Scene  scene;
	private ParallelCamera cam;
	
	private Controller controller;
	private EventHandler<KeyEvent> onPress;
	private EventHandler<KeyEvent> onRelease;
	private EventHandler<MouseEvent> onMouseClick;
	private Timer gameTimer;
	
	
	public void init() {
		/**
		 * Runs on program launch.
		*/
		
		cam = new ParallelCamera();
		controller = new Controller();
		currStage = new StageOne(this);
		
	}
	
    public void start(Stage s) throws IOException {
    	/**
    	 * Runs after init() 
    	*/
		
		// Create new scene with
    	stage = s;
		scene = new Scene(currStage.getBackground(), 30000, 30000, true);
		scene.setCamera(cam);
		
		//cam.setClip(currStage.getPlayer().getMvmnt().getSprite().getWorldBox().getColBox());
//		currStage.getOverlay().getOverlayGroup().setTranslateX(currStage.getPlayer().getMvmnt().getSprite().getSpriteGroup().getTranslateX() - (scene.getWidth() / 2));
//		currStage.getOverlay().getOverlayGroup().setTranslateY(currStage.getPlayer().getMvmnt().getSprite().getSpriteGroup().getTranslateY() - (scene.getHeight() / 2));
//		cam.setTranslateX(-scene.getWidth() / 2);
//		cam.setTranslateY(-scene.getHeight() / 2);
		//currStage.getPlayer().getMvmnt().getSprite().getSpriteGroup().getChildren().add(cam);
		
		
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
				case I:
					controller.togglePlayerMenu();
					currStage.dispPlayerMenu();
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
				case V: // Toggle visibility of worldBoxes and hurtBoxes.
					for (int box = 0; box < currStage.getWorldBoxes().length; ++box) {
						
						if (currStage.getWorldBoxes()[box].getColBox().getOpacity() != 0) {
							currStage.getWorldBoxes()[box].getColBox().setOpacity(0);
						}
						else {
							currStage.getWorldBoxes()[box].getColBox().setOpacity(100);
						}
					}
					
					for (int box = 0; box < currStage.getHurtBoxes().length; ++box) {
						
						if (currStage.getHurtBoxes()[box].getColBox().getOpacity() != 0) {
							currStage.getHurtBoxes()[box].getColBox().setOpacity(0);
						}
						else {
							currStage.getHurtBoxes()[box].getColBox().setOpacity(100);
						}
					}
					
					for (int box = 0; box < currStage.getNpc().length; ++box) {
						
						if (currStage.getNpc()[box].getMvmnt().getSprite().getCheckBox().getColBox().getOpacity() != 0) {
							currStage.getNpc()[box].getMvmnt().getSprite().getCheckBox().getColBox().setOpacity(0);
						}
						else {
							currStage.getNpc()[box].getMvmnt().getSprite().getCheckBox().getColBox().setOpacity(100);
						}
						
						if (currStage.getNpc()[box].getMvmnt().getSprite().getDetectBox().getColBox().getOpacity() != 0) {
							currStage.getNpc()[box].getMvmnt().getSprite().getDetectBox().getColBox().setOpacity(0);
						}
						else {
							currStage.getNpc()[box].getMvmnt().getSprite().getDetectBox().getColBox().setOpacity(100);
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
        stage.setWidth(500);
        stage.setHeight(500);
        stage.setMinWidth(0);
        stage.setMinHeight(0);
        stage.setMaxWidth(30000);
        stage.setMaxHeight(30000);
        stage.show();
        
        
        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				Platform.runLater(() -> currStage.runPlayerStates());
			}
        	
        }, 0, 32);
        
        gameTimer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				Platform.runLater(() -> currStage.updateViewOrder());
			}
        	
        }, 0, 32);
        
        gameTimer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				Platform.runLater(() -> currStage.runNpcStates());
				
			}
        	
        }, 0, 32);
        
		currStage.onLaunch();
        
    }

//GETTERS--------------------------------------------------------------------------  
    
    public Stage getRoot() {
    	/*
    	 * 
    	*/
    	
    	return stage;
    }
    
    public Scene getScene() {
    	/*
    	 * 
    	*/
    	
    	return scene;
    }
    
    public ParallelCamera getCam() {
    	/*
    	 * 
    	*/
    	
    	return cam;
    }
    
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
    	
    	//TODO: save the game.
    	
    	Platform.exit();
    	System.exit(0);
    }
}