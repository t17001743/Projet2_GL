package Gameplay;

import Engine.*;
import Engine.Graphics.GraphicsEngine;
import Engine.Physics.PhysicsEngine;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;



/**
 * Boucle principale du jeu
 */
public class Game extends CoreApplication {

    private ArrayList<Entity> entities;  //les entités du jeu
    private PhysicsEngine physicsEngine;  //moteur physique
    private GraphicsEngine graphicsEngine;  //moteur graphique

    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameLoop();
            }
        };

        this.physicsEngine = new PhysicsEngine();
        this.graphicsEngine = new GraphicsEngine(primaryStage);
        this.graphicsEngine.create2DWindow("Pac-Man", 500, 500);

        animationTimer.start();
    }

    /**
     * Initialisation du jeu,
     *
     * Méthod lancée en deuxième priorité
     * et avant la méthode start() !
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

        //Controller controller = new Controller((DynamicEntity) entities.get(0), physicsEngine);
        //this.graphicsEngine.getScene().setOnKeyPressed(controller.getEventHandler());
    }

    /**
     * @return la liste des entités du jeu
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * Boucle principale
     * Elle s'occupe de gérer l'affichage de manière cohérente :
     * Elle efface toute la scène (de type Scene)
     * Elle affiche ensuite chaque élément un à un dans le bon ordre !
     * Le background avant les entités.
     */
    public void gameLoop(){

        graphicsEngine.clearFrame(); //On efface l'affichage de la fenêtre

        graphicsEngine.drawBackground(Color.BLACK); //On colorie le background

        for (int i = 0; i < entities.size(); i++) {

            if (entities.get(i).getClass().getSuperclass() == DynamicEntity.class) // Si on a une entité dynamique
                physicsEngine.updateCoordinates((DynamicEntity) entities.get(i)); // Alors on update ses coordonnées

            try {
                graphicsEngine.drawEntity(entities.get(i)); // On dessine toutes les entités dans le cas où on a bien le chemin de l'image correspondante
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