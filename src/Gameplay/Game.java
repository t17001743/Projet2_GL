package Gameplay;

import Engine.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Boucle principale du jeu
 */
public class Game extends Thread {

    private ArrayList<Entity> entities;  //les entités du jeu
    private DynamicEntity character;  //le personnage (ici Pac-man)
    private PhysicsEngine physicsEngine;  //moteur physique
    private GraphicsEngine graphicsEngine;  //moteur graphique

    public Game(GraphicsEngine graphicsEngine) {
        this.entities = new ArrayList<Entity>();
        this.graphicsEngine = graphicsEngine;
    }

    /**
     * Initialisation du jeu
     */
    public void init(){
        //création des entités dynamiques et de leurs caractéristiques (vitesse, position dans la scène, dimensions)
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

    /**
     * @return la liste des entités du jeu
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * @return le personnage
     */
    public DynamicEntity getCharacter() {
        return character;
    }

    /**
     * Boucle principale
     */
    public void run(){
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //initialisation du jeu
        init();

        while(true){
            System.out.println("Initialisation effectuée");
            //on efface l'ancienne image
            graphicsEngine.clearFrame();
            System.out.println("Frame cleared");
            //on ajoute chaque élément à la scène graphique
            try {
                for(int i=0; i < entities.size(); i++){
                    System.out.println(entities.get(i));
                    //le moteur affiche la nouvelle image
                    graphicsEngine.drawImage(entities.get(i));
                    System.out.println("image affichée");
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }



    }
}