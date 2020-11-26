package Gameplay;

import Engine.Entities.DynamicEntity;
import Engine.Physics.PhysicsEngine;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Ecoute des événements du clavier pour les mouvements des entités du jeu
 */
public class Controller {

    //Pac-Man indice 0
    private DynamicEntity entity;
    private PhysicsEngine physicsEngine;
    private EventHandler<KeyEvent> eventHandler;
    private Integer speed;

    public Controller(DynamicEntity entity, PhysicsEngine physicsEngine, Integer speed){
        this.entity = entity;
        this.physicsEngine = physicsEngine;
        this.speed = speed;
        initEventHandler();
    }

    /**
     * Gestion des événements haut/bas/gauche/droite sur les flèches du clavier par l'utilisateur
     */
    public void initEventHandler(){
        this.eventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()){
                    //flèche du haut
                    case UP:
                        physicsEngine.setSpeedX(0, entity);
                        physicsEngine.setSpeedY(-speed, entity);
                        break;

                    //flèche du bas
                    case DOWN:
                        physicsEngine.setSpeedX(0, entity);
                        physicsEngine.setSpeedY(speed, entity);
                        break;

                    //flèche de gauche
                    case LEFT:
                        physicsEngine.setSpeedX(-speed, entity);
                        physicsEngine.setSpeedY(0, entity);
                        break;

                    //flèche de droite
                    case RIGHT:
                        physicsEngine.setSpeedX(speed, entity);
                        physicsEngine.setSpeedY(0, entity);
                        break;

                    default : break;
                }
                event.consume();
            }
        };
    }

    public EventHandler getEventHandler(){ return this.eventHandler;}

}
