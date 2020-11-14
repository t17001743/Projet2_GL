package Gameplay;

import Engine.DynamicEntity;
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
        for(Entity entity : entities){
            eventHandler = new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    switch(event.getCode()){
                        //flèche du haut
                        case UP:
                            physicsEngine.setSpeedX(0, (DynamicEntity)entity);
                            physicsEngine.setSpeedY(-1, (DynamicEntity)entity);
                        break;
                        //flèche du bas
                        case DOWN:
                            physicsEngine.setSpeedX(0, (DynamicEntity)entity);
                            physicsEngine.setSpeedY(1, (DynamicEntity)entity);
                        break;
                        //flèche de gauche
                        case LEFT:
                            physicsEngine.setSpeedX(-1, (DynamicEntity)entity);
                            physicsEngine.setSpeedY(0, (DynamicEntity)entity);
                        break;
                        //flèche de droite
                        case RIGHT:
                            physicsEngine.setSpeedX(1, (DynamicEntity)entity);
                            physicsEngine.setSpeedY(0, (DynamicEntity)entity);
                        break;
                        default : break;
                    }
                    event.consume();
                }
            };
        }
    }
}
