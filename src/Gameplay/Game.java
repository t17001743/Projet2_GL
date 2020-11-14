package Gameplay;

import Engine.*;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Game extends Thread {

    private ArrayList<Entity> entities;
    private DynamicEntity character;
    private PhysicsEngine physicsEngine;
    private GraphicsEngine graphicsEngine;
    private Stage stage;

    public Game(GraphicsEngine graphicsEngine) {
        entities = new ArrayList<Entity>();
        this.graphicsEngine = graphicsEngine;

    }

    public void init(){
        //création des entités dynamiques
        List<Double> speed = new ArrayList<>();
        speed.add(0.0);
        speed.add(0.0);
        List<Double> position = new ArrayList<>();
        position.add(600.0);
        position.add(400.0);
        List<Double> dimensions = new ArrayList<>();
        dimensions.add(50.0);
        dimensions.add(50.0);
        String fileName = "pacman.png";
        PacMan pacman = new PacMan(speed, position, dimensions, fileName);
        this.character = pacman;
        entities.add(pacman);
        //création des entités statiques
        StaticEntity staticEntity;

        //initialisation du moteur physique
        physicsEngine = new PhysicsEngine();

        //mouvement des entités
        Controller controller = new Controller(character, physicsEngine);
        controller.initEventHandler();
    }

    public void run(){
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        init();
        System.out.println("Initialisation effectuée");
        //on efface l'ancienne image
        graphicsEngine.clearFrame();
        System.out.println("Frame cleared");
        //on ajoute chaque élément à la scène graphique
        try {
            for(int i=0; i < entities.size(); i++){
                System.out.println(entities.get(i));
                graphicsEngine.drawImage(entities.get(i));
                System.out.println("image affichée");
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