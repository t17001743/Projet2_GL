package Gameplay;

import Engine.Entity;
import Engine.PhysicsEngine;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Controller {

    private ArrayList<Entity> entities;
    private PhysicsEngine physicsEngine;
    private EventHandler<KeyEvent> eventHandler;

    public Controller(ArrayList<Entity> entities, PhysicsEngine physicsEngine){

    }

    public void initEventHandler(){
        eventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN ||
                        event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.LEFT) {
                    //entities.get(0).setPosition();

                    //haut
                    //bas
                    //gauche
                    //droite
                }
                event.consume();
            }
        };
    }
}
