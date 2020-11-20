package Gameplay;

import Engine.*;
import Engine.Graphics.DrawGraphics2D;
import Engine.Physics.PhysicsEngine;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;


/**
 * Boucle principale du jeu
 */
public class Game extends Application {

    private ArrayList<Entity> entities;  //les entités du jeu
    private PhysicsEngine physicsEngine;  //moteur physique
    private DrawGraphics2D graphicsEngine;  //moteur graphique

    @Override
    public void start(Stage primaryStage) {

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                mainLoop();
            }
        };

        System.out.println("start()");

        this.physicsEngine = new PhysicsEngine();

        this.graphicsEngine = new DrawGraphics2D(primaryStage);
        this.graphicsEngine.create2DWindow("Pac-Man", 500, 500);

        animationTimer.start();
    }

    /**
     * Initialisation du jeu
     */
    public void init() throws InterruptedException {
        System.out.println("init()");

        this.entities = new ArrayList<Entity>();

        //création des entités dynamiques et de leurs caractéristiques (vitesse, position dans la scène, dimensions)
        List<Double> speed = new ArrayList<>();
        speed.add(0.0);
        speed.add(0.0);

        List<Double> position = new ArrayList<>();
        position.add(100.0);
        position.add(100.0);

        List<Double> dimensions = new ArrayList<>();
        dimensions.add(50.0);
        dimensions.add(50.0);

        String fileName = "src/Gameplay/Images/pacman.png";

        PacMan pacman = new PacMan(speed, position, dimensions, fileName);
        this.entities.add(pacman);

        //création des entités statiques
        //StaticEntity staticEntity;

        //Controller controller = new Controller((DynamicEntity) entities.get(0), physicsEngine);
        //this.graphicsEngine.getScene().setOnKeyPressed(controller.getEventHandler());

        //while (this.graphicsEngine == null){}

        //mainLoop();
    }


    /**
     * @return la liste des entités du jeu
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * Boucle principale
     */
    public void mainLoop(){

        System.out.println("RUN()" + graphicsEngine);

        graphicsEngine.clearFrame();//on efface l'ancienne image

        graphicsEngine.drawBackground(Color.BLACK);

        for (int i = 0; i < entities.size(); i++) {

            if (entities.get(i).getClass().getSuperclass() == DynamicEntity.class)
                physicsEngine.updateCoordinates((DynamicEntity) entities.get(i));

            try {
                graphicsEngine.drawEntity(entities.get(i));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }


    /** Permise to launch a standalone application.
     * @param args
     */
    public static void main(String[] args){
        System.out.println("main()");
        launch(args);
    }

}