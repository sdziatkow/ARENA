package window;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

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
import movement.PlayerMovement;
import ui.Menus;
import ui.invMenu.InventoryMenu;
import ui.statMenu.StatMenu;
import worldStage.StageOne;
import worldStage.WorldData;
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
	
	private DoubleProperty mouseX;
	private DoubleProperty mouseY;
	
	
	public void init() {
		/**
		 * Runs on program launch.
		*/
		
		cam = new ParallelCamera();
		cam.setCache(true);
		
		controller = new Controller();
		currStage = new StageOne();
		
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
				case D: // MOVE.
					controller.playerMovement(eventType, key);
					break;
				case Q: // USE ITEM.
					WorldData.useItem(0);
					break;
				case I: // INVENTORY MENU.
					controller.toggleInvMenu();
					if (StatMenu.main.isVisible()) {
						controller.toggleStatMenu();
					}
					Menus.dispInvMenu();
					break;
				case U: // STAT MENU.
					controller.toggleStatMenu();
					if (InventoryMenu.main.isVisible()) {
						controller.toggleInvMenu();
					}
					Menus.dispStatMenu();
					break;
				case P: // MENU ID.
					Menus.nextID();
					System.out.println("NOW SHOWING: " + Menus.ID.get());
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
					for (int t = 0; t < WorldData.colBoxes.size(); ++t) {
						for (int b = 0; b < WorldData.colBoxes.get(t).size(); ++b) {
						
							if (WorldData.colBoxes.get(t).get(b) != null) {
								if (WorldData.colBoxes.get(t).get(b).getColBox().getOpacity() != 0) {
									WorldData.colBoxes.get(t).get(b).getColBox().setOpacity(0);
								}
								else {
									WorldData.colBoxes.get(t).get(b).getColBox().setOpacity(100);
								}
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
        
		currStage.onLaunch();
		((PlayerMovement)WorldData.mvmnts.get(0)).setCntrl(controller);
		((PlayerMovement)WorldData.mvmnts.get(0)).setMousePos(mouseX, mouseY);
    	WorldStage.OVERLAY.getOverlayGroup().getChildren().add(cam);
    	WorldStage.OVERLAY.getOverlayGroup().setLayoutX(-(stage.getWidth() / 2));
		WorldStage.OVERLAY.getOverlayGroup().setLayoutY(-(stage.getHeight() / 2));
		
        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				Platform.runLater(() -> WorldData.updateViewOrder());
				Platform.runLater(() -> WorldData.runPersonStates());
			}
        	
        }, 0, 32);
        
        gameTimer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				Platform.runLater(() -> currStage.testInfo());
			}
        	
        }, 0, 5000);
    }

//GETTERS--------------------------------------------------------------------------  
    
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