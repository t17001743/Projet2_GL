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
 * La classe s'occupant de la création du jeu
 */
public class Game extends CoreApplication {

    private ArrayList<Entity> entities;  // La liste des entités du jeu, à la fois statique et dynamique
    private PhysicsEngine physicsEngine;  // Moteur physique
    private GraphicsEngine graphicsEngine;  // Moteur graphique

    /**
     * Méthode nécessaire pour le lancement du programme
     * S'occupe de lancer l'application
     *
     * @param args Les arguments fournis en entrée, non nécessaire dans notre cas
     */
    public static void main(String[] args){
        launch(args);
    }

    /**
     * Initialisation du jeu, création des éléments intrinsèque à ce dernier, exécutée en premier lors du lancement de la méthode launch()
     * et avant la méthode start() !
     */
    public void init() {
        // Création de la liste d'entités
        entities = new ArrayList<Entity>();

        // Création des entités dynamiques et de leurs caractéristiques (vitesse, position dans la scène, dimensions)
        createEntity(0.0, 0.0, 100.0, 100.0, 50.0, 50.0, "src/Gameplay/Images/pacman.png", PacMan.class);

    }

    /**
     * Lancement de l'application et création de ces éléments graphique
     *
     * @param primaryStage La stage correspondant à l'application
     */
    @Override
    public void start(Stage primaryStage) {

        // La boucle de jeu est appelé environ 60 fois par seconde
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameLoop();
            }
        };

        // Création des moteurs
        this.physicsEngine = new PhysicsEngine();
        this.graphicsEngine = new GraphicsEngine(primaryStage);

        // Création de la fenêtre de jeu
        this.graphicsEngine.create2DWindow("Pac-Man", 500, 500);

        // Instancie la classe des événements sur les entités créés
        Controller controller = new Controller((DynamicEntity) entities.get(0), physicsEngine);

        // Lie les événements clavier à la scène par le biais
        this.graphicsEngine.getScene().setOnKeyPressed(controller.getEventHandler());

        // Lancement de la boucle principale du jeu
        animationTimer.start();
    }

    /**
     * Boucle principale
     *
     * Elle s'occupe de gérer l'affichage de manière cohérente :
     *  - Elle efface toute la scène (de type Scene)
     *  - Elle affiche le fond
     *  - Elle affiche ensuite chaque élément un à un dans le bon ordre !
     */
    public void gameLoop(){
        graphicsEngine.clearFrame(); // On efface l'affichage de la fenêtre

        graphicsEngine.drawBackground(Color.BLACK); // On colorie le background

        // On affiche les entités
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

    /**
     * Méthode qui s'occupe de créer une entité en fonction des paramètres données
     * Dans le cas d'une entité statique, la vitesse sur l'axe des x et l'axe des y est définie comme null
     *
     * @param speedX Vitesse sur l'axe des x
     * @param speedY Vitesse sur l'axe des y
     * @param positionX Position initiale x
     * @param positionY Position initiale y
     * @param dimensionX Dimension en x
     * @param dimensionY Dimension en y
     * @param entityClass Le class correspondant au type d'entité créé
     */
    private void createEntity(Double speedX, Double speedY, Double positionX, Double positionY, Double dimensionX, Double dimensionY, String fileName, Object entityClass) {
        List<Double> speed = new ArrayList<>();
        speed.add(speedX);
        speed.add(speedY);

        List<Double> position = new ArrayList<>();
        position.add(positionX);
        position.add(positionY);

        List<Double> dimensions = new ArrayList<>();
        dimensions.add(dimensionX);
        dimensions.add(dimensionY);

        // Si nous voulons créer un Pac-Man
        if(entityClass == PacMan.class) {
            PacMan pacman = new PacMan(speed, position, dimensions, fileName);
            entities.add(pacman);
        }
    }

    /**
     * Getter
     *
     * @return La liste des entités du jeu
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

}