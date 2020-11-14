package Gameplay;

import Engine.Entity;
import Engine.GraphicsEngine;
import Engine.PhysicsEngine;
import Engine.StaticEntity;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Game extends Thread {

    private ArrayList<Entity> entities;
    private PhysicsEngine physicsEngine;
    private GraphicsEngine graphicsEngine;
    private Stage stage;

    public Game() {
    }

    public void init(){
        //création des entités dynamiques
        PacMan pacman = new PacMan();
        entities.add(pacman);

        //création des entités statiques
        StaticEntity staticEntity;

        //initialisation du moteur physique
        physicsEngine = new PhysicsEngine();

        //initialisation du moteur graphique
        graphicsEngine = new GraphicsEngine();

        //mouvement des entités
        Controller controller = new Controller(entities, physicsEngine);
        controller.initEventHandler();
    }

    public void run(){
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        init();
        //on efface l'ancienne image
        graphicsEngine.clearFrame();
        //on ajoute chaque élément à la scène graphique
        try {
            for(int i=0; i < entities.size(); i++){
                graphicsEngine.drawImage(entities.get(i).getImage(), entities.get(i).getPosition(), entities.get(i).getDimensions());
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //le moteur affiche la nouvelle image
            graphicsEngine.start(stage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}