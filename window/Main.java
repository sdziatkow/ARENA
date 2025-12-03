package window;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import animate.Animate;
import item.Item;
import arenaEnum.itemInfo.ItemType;
import item.useable.Useable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.ParallelCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import movement.PlayerMovement;
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
	
	DoubleProperty mouseX;
	DoubleProperty mouseY;
	
	
	public void init() {
		/**
		 * Runs on program launch.
		*/
		
		cam = new ParallelCamera();
		cam.setCache(true);
		controller = new Controller();
		currStage = new StageOne(this);
		
		mouseX = new SimpleDoubleProperty();
		mouseY = new SimpleDoubleProperty();
		
	}
	
    public void start(Stage s) throws IOException {
    	/**
    	 * Runs after init() 
    	*/
		
		// Create new scene with
    	stage = s;
		scene = new Scene(currStage.getBackground(), 3000, 3000, true);
		scene.setCamera(cam);
		
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
					controller.toggleInvMenu();
					currStage.dispInvMenu();
					break;
				case Q:
					
					Item useable = currStage.DATA.persons.get(0).equipSlot().getEquipped(ItemType.USEABLE);
					if (useable != null && !controller.showingInvMenu() && !controller.showingStatMenu()) {
						((Useable)currStage.DATA.persons.get(0).equipSlot().getEquipped(ItemType.USEABLE)).use();
					}
					break;
					
				case U:
					controller.toggleStatMenu();
					currStage.dispStatMenu();
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
					for (int box = 0; box < currStage.DATA.colBoxes.get(0).size(); ++box) {
						
						if (currStage.DATA.colBoxes.get(0).get(box) != null) {
							if (currStage.DATA.colBoxes.get(0).get(box).getColBox().getOpacity() != 0) {
								currStage.DATA.colBoxes.get(0).get(box).getColBox().setOpacity(0);
							}
							else {
								currStage.DATA.colBoxes.get(0).get(box).getColBox().setOpacity(100);
							}
						}
					}
					
					for (int box = 0; box < currStage.DATA.colBoxes.get(1).size(); ++box) {
						
						if (currStage.DATA.colBoxes.get(1).get(box) != null) {
							if (currStage.DATA.colBoxes.get(1).get(box).getColBox().getOpacity() != 0) {
								currStage.DATA.colBoxes.get(1).get(box).getColBox().setOpacity(0);
							}
							else {
								currStage.DATA.colBoxes.get(1).get(box).getColBox().setOpacity(100);
							}
						}
					}
					
					for (int box = 0; box < currStage.DATA.colBoxes.get(2).size(); ++box) {
						
						if (currStage.DATA.colBoxes.get(2).get(box) != null) {
							if (currStage.DATA.colBoxes.get(2).get(box).getColBox().getOpacity() != 0) {
								currStage.DATA.colBoxes.get(2).get(box).getColBox().setOpacity(0);
							}
							else {
								currStage.DATA.colBoxes.get(2).get(box).getColBox().setOpacity(100);
							}
						}
					}
					
					for (int box = 0; box < currStage.DATA.colBoxes.get(3).size(); ++box) {
						
						if (currStage.DATA.colBoxes.get(3).get(box) != null) {
							if (currStage.DATA.colBoxes.get(3).get(box).getColBox().getOpacity() != 0) {
								currStage.DATA.colBoxes.get(3).get(box).getColBox().setOpacity(0);
							}
							else {
								currStage.DATA.colBoxes.get(3).get(box).getColBox().setOpacity(100);
							}
						}
					}
					
					for (int box = 0; box < currStage.DATA.colBoxes.get(4).size(); ++box) {
						
						if (currStage.DATA.colBoxes.get(4).get(box) != null) {
							if (currStage.DATA.colBoxes.get(4).get(box).getColBox().getOpacity() != 0) {
								currStage.DATA.colBoxes.get(4).get(box).getColBox().setOpacity(0);
							}
							else {
								currStage.DATA.colBoxes.get(4).get(box).getColBox().setOpacity(100);
							}
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
				mouseX.set(event.getX());
				mouseY.set(event.getY());
				button = event.getButton();
				controller.mbPressed(button);
			}
		};
		
		
		
		scene.setOnKeyPressed(onPress);
		scene.setOnKeyReleased(onRelease);
		scene.setOnMouseClicked(onMouseClick);
		
        stage.setTitle("ARENA");
        stage.setScene(scene);
        stage.setWidth(1440);
        stage.setHeight(500);
        stage.show();
        
		((PlayerMovement)currStage.DATA.mvmnts.get(0)).setCntrl(controller);
		((PlayerMovement)currStage.DATA.mvmnts.get(0)).setMousePos(mouseX, mouseY);
        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				Platform.runLater(() -> currStage.DATA.updateViewOrder());
				Platform.runLater(() -> currStage.runPersonStates());
			}
        	
        }, 0, 32);
        
        gameTimer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				Platform.runLater(() -> currStage.testInfo());
			}
        	
        }, 0, 5000);
        
		currStage.onLaunch();
		currStage.getOverlay().getOverlayGroup().getChildren().add(cam);
        
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
    	
    	Application.launch(args);
    	
    	//TODO: save the game.
    	
    	Platform.exit();
    	System.exit(0);
    }
}