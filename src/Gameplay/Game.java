package Gameplay;

import Engine.*;
import Engine.Entities.DynamicEntity;
import Engine.Entities.Entity;
import Engine.Graphics.GraphicsEngine;
import Engine.Graphics.Text;
import Engine.Physics.PhysicsEngine;
import Gameplay.Entities.*;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * La classe s'occupant de la création du jeu
 */
public class Game extends CoreApplication {

    private ArrayList<Entity> entities;  // La liste des entités du jeu, à la fois statique et dynamique
    private PhysicsEngine physicsEngine;  // Moteur physique
    private GraphicsEngine graphicsEngine;  // Moteur graphique
    private Integer width;
    private Integer height;

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
        levelCreator();
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
        this.physicsEngine = new PhysicsEngine(entities, width, height);
        this.graphicsEngine = new GraphicsEngine(primaryStage);

        // Création de la fenêtre de jeu
        this.graphicsEngine.create2DWindow("Pac-Man", width, height);

        // Instancie la classe des événements sur les entités créés
        Controller controller = new Controller((DynamicEntity) entities.get(0), physicsEngine, 10);

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

        graphicsEngine.drawBackground(Color.BLACK); // On colorie le fond

        Text text = new Text("PACMAN 1 2 3 4 5 6 7", 50, 30);
        graphicsEngine.setColor(Color.WHITE);
        graphicsEngine.setFontAndSize("Arial", 25);

        // Pour chaque entité
        for (int i = 0; i < entities.size(); i++) {

            // Pour chaque entité dynamique
            if (entities.get(i).getClass().getSuperclass() == DynamicEntity.class) {
                DynamicEntity dynamicEntity = (DynamicEntity) entities.get(i);

                // Si l'entité n'a pas de vitesse, il n'est pas nécessaire de vérifier quoi que ce soit
                if (physicsEngine.isMoving(dynamicEntity)) {
                    // On vérifie s'il y a une collsion
                    Pair<Boolean, Entity> collidedEntity = physicsEngine.checkCollision(dynamicEntity);
                    // S'il n'y a pas de collisions
                    if(collidedEntity.getKey().equals(false)) {
                        // On met à jour ses coordonnées
                        physicsEngine.updateCoordinates(dynamicEntity);
                    }
                    // Sinon
                    else {
                        // On gère la collision
                        collisionHandler(dynamicEntity, collidedEntity.getValue());
                    }
                }
            }

            try {
                // On dessine toutes les entités dans le cas où on a bien le chemin de l'image correspondante
                graphicsEngine.drawEntity(entities.get(i));
                graphicsEngine.drawText(text);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Méthode utilisé pour gérer les collisions
     *
     * @param dynamicEntity L'entité responsable de la collision
     * @param collidedEntity L'entité avec laquelle la collision s'est produite
     */
    private void collisionHandler(DynamicEntity dynamicEntity, Entity collidedEntity) {
        // Si c'est Pac-Man
        if (dynamicEntity.getClass().equals(PacMan.class)) {
            if(collidedEntity == null) {
                // On ajuste la vitesse à 0
                physicsEngine.setSpeedX(0, dynamicEntity);
                physicsEngine.setSpeedY(0, dynamicEntity);
                return;
            }
            // Avec un mur
            else if(collidedEntity.getClass().equals(Wall.class)) {
                // On ajuste la vitesse à 0
                physicsEngine.setSpeedX(0, dynamicEntity);
                physicsEngine.setSpeedY(0, dynamicEntity);
                return;
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
    private void createEntity(Integer speedX, Integer speedY, Integer positionX, Integer positionY, Integer dimensionX, Integer dimensionY, String fileName, Object entityClass) {
        List<Integer> speed = new ArrayList<>();
        speed.add(speedX);
        speed.add(speedY);

        List<Integer> position = new ArrayList<>();
        position.add(positionX);
        position.add(positionY);

        List<Integer> dimensions = new ArrayList<>();
        dimensions.add(dimensionX);
        dimensions.add(dimensionY);

        // Si nous voulons créer un Pac-Man
        if(entityClass == PacMan.class) {
            PacMan pacman = new PacMan(speed, position, dimensions, fileName);
            entities.add(pacman);
        }

        // Si nous voulons créer un mur
        if(entityClass == Wall.class) {
            Wall wall = new Wall(position, dimensions, fileName);
            entities.add(wall);
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

    /**
     * Cette méthode permnet de charger un niveau
     * @param filePath Chemin d'accès du fichier txt contenant le niveau
     */
    private void levelLoader(String filePath){
        try {
            Scanner scanner = new Scanner(new File(filePath));
            width = scanner.nextInt();
            height = scanner.nextInt();

            String wallFile = scanner.next();
            while (scanner.hasNextLine()) {
                int posX, posY, dimX, dimY;
                posX = scanner.nextInt();
                posY = scanner.nextInt();
                dimX = scanner.nextInt();
                dimY = scanner.nextInt();

                createEntity(null, null, posX, posY, dimX, dimY, wallFile, Wall.class);
            }
        }catch(Exception e) {e.printStackTrace();}
    }

    /**
     * Création d'un niveau et des entités qui le compose
     */
    private void levelCreator(){
        // Création de la liste d'entités
        entities = new ArrayList<Entity>();
        // Création des entités dynamiques et de leurs caractéristiques (vitesse, position dans la scène, dimensions)
        createEntity(0, 0, 100, 100, 50, 50, "src/Gameplay/Images/pacman.png", PacMan.class);
        // Chargement de la zone de jeu du niveau
        levelLoader("src/Gameplay/Levels/gameZone.txt");
    }

}