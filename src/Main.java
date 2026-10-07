import charData.GameChar;
import control.ArenaObject;
import control.Controller;
import control.handlers.StatChangeHandler;
import control.runtimeTrackers.WorldTracker;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import menus.Menus;
import spriteData.behavior.boxes.Movable;
import worldState.WorldState;
import control.runtimeTrackers.spriteData.SpriteTracker;
import java.util.Timer;
import java.util.TimerTask;

import static control.runtimeTrackers.WorldEntity.GAME_CHAR;
import static control.runtimeTrackers.spriteData.SpriteType.MOVABLE;

public class Main extends Application {

    /** Runs before start **/
    public void init() {
        WorldState.stageWorld("testWorld");
        WorldState.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {Controller.onKeyDown(keyEvent.getCode());}
        });
        WorldState.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) { Controller.onKeyRelease(keyEvent.getCode());}
        });
        WorldState.scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {Controller.onMouseBtnPressed(mouseEvent);}
        });
        WorldState.scene.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {Controller.onMouseBtnReleased(mouseEvent);}
        });
    }

    @Override
    public void start(Stage stage) {
        stage.setScene(WorldState.scene);

        // Ensures that Menu overlay and camera adjust when the window size changes.
        stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                Menus.overlay.setLayoutX(-stage.getWidth() / 2.25);
                Menus.overlay.setLayoutY(-stage.getHeight() / 2.25);
            }
        });
        stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                Menus.overlay.setLayoutX(-stage.getWidth() / 2.25);
                Menus.overlay.setLayoutY(-stage.getHeight() / 2.25);
            }
        });

        stage.setTitle("WASTE");
        stage.setWidth(1000.0);
        stage.setHeight(800.0);
        stage.show();

    //TESTING------------------------------------------------------------------------------------------------------------
        ((Movable)SpriteTracker.get(MOVABLE, WorldState.playerID)).setMaxSpeed(1.5);
        ((GameChar)WorldTracker.get(GAME_CHAR, WorldState.playerID)).lvl().incAttrPoints(100);
        //((GameChar)WorldTracker.get(GAME_CHAR, WorldState.playerID)).attr().skillUp(Attr.AGILITY, 100);
        StatChangeHandler.updateStatsFromAttr(WorldState.playerID);
        WorldTracker.getAll(GAME_CHAR).forEach((ArenaObject g) -> {
            ((GameChar)g).stats().healVitals(50);
        });


    //MAIN-LOOP----------------------------------------------------------------------------------------------------------
        Timer gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(WorldState::run);
            }
        }, 0, 32);
    }

    public static void main(String[] args) {
        Application.launch(args);
        Platform.exit();
        System.exit(0);
    }
}