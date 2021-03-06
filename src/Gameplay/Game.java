package Gameplay;

import Engine.*;
import Engine.Entities.DynamicEntity;
import Engine.Entities.Entity;
import Engine.Graphics.Elements.Text;
import Engine.Graphics.GraphicsEngine;
import Engine.Physics.PhysicsEngine;
import Gameplay.Elements.Life;
import Gameplay.Elements.Score;
import Gameplay.Entities.*;
import com.sun.javafx.iio.common.RoughScaler;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * La classe s'occupant de la création du jeu
 */
public class Game extends CoreApplication {

    private ArrayList<Entity> entities;  // La liste des entités du jeu, à la fois statique et dynamique
    private PhysicsEngine physicsEngine;  // Moteur physique
    private GraphicsEngine graphicsEngine;  // Moteur graphique
    private Integer width; // Width de la fenêtre de jeu
    private Integer height; // Height de la fenêtre de jeu
    private Score score; // Le score du joueur
    private PacMan pacman; // Le joueur
    private int pacGumCounter; // Le compteur de Pac-Gommes
    private Life life;
    private AnimationTimer animationTimer;

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
     * Lancement de l'application et création de ces éléments graphique
     *
     * @param primaryStage La stage correspondant à l'application
     */
    @Override
    public void start(Stage primaryStage) {
        // La boucle de jeu est appelé environ 60 fois par seconde
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameLoop();
            }
        };

        this.entities = new ArrayList<>();

        // Création des moteurs
        this.physicsEngine = new PhysicsEngine();
        this.graphicsEngine = new GraphicsEngine(primaryStage);

        // Création du score, du compteur de vies et du joueur
        score = new Score(0, 25, 25);
        life = new Life(1, 325, 25);
        createEntity(0, 0, 120, 100, 30, 30, "src/Gameplay/Images/pacmans/pacmanD.png", PacMan.class);

        levelCreator(); // Création du niveau

        this.graphicsEngine.create2DWindow("Pac-Man", width, height); // Création de la fenêtre de jeu

        // Instancie la classe des événements sur les entités créés
        Controller controller = new Controller(pacman, physicsEngine, 10);

        // Lie les événements clavier à la scène par le biais
        this.graphicsEngine.getScene().setOnKeyPressed(controller.getEventHandler());

        animationTimer.start(); // Lancement de la boucle principale du jeu
    }

    /**
     * Boucle principale
     *
     * Elle s'occupe de gérer l'affichage de manière cohérente :
     *  - 1. Elle efface toute la scène (de type Scene)
     *  - 2. Elle affiche le fond
     *  - 3. Elle affiche le score et le nombre de vies
     *  - 4. Elle affiche chaque entité
     *
     * Elle s'occupe également du mouvement de chaque entité dynamique
     *
     * Enfin, elle gère la fin de niveau ou de jeu de la manière suivante :
     *  - 5. Si le nombre de Pac-Gommes est à 0, le niveau est terminé
     *  - 6. Si le nombre de vies est à 0, la partie est terminée
     */
    private void gameLoop(){
        // 1. On efface toute la scene
        graphicsEngine.clearFrame();

        // 2. On affiche le fond
        graphicsEngine.drawBackground(Color.BLACK);

        // 3. On définit les propriétés du texte et on affiche le score et le nombre de vies
        graphicsEngine.setColor(Color.WHITE);
        graphicsEngine.setFontAndSize("Arial", 25);
        graphicsEngine.drawText(score);
        graphicsEngine.drawText(life);

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
                    if (collidedEntity.getKey().equals(false)) {
                        // On met à jour ses coordonnées
                        physicsEngine.updateCoordinates(dynamicEntity);
                    }
                    // Sinon
                    else {
                        // Sinon on gère la collision
                        collisionHandler(dynamicEntity, collidedEntity.getValue());
                    }
                }
            }
            try {
                // 4. On affiche chaque entité
                graphicsEngine.drawEntity(entities.get(i));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        // 5. Si le nombre de Pac-Gommes est à 0, le niveau est terminé
        if(pacGumCounter <= 0){ levelCreator(); }

        // 6. Si le nombre de vies est à 0, la partie est terminée
        if(checkGameOver()) {
            animationTimer.stop();

            this.graphicsEngine.clearFrame();
            this.graphicsEngine.drawBackground(Color.BLACK);

            this.graphicsEngine.setColor(Color.WHITE);
            this.graphicsEngine.drawText(new Text(score.getText() + "\nGame Over", width/2 - 50, height/2 - 20));
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
            }
            // Avec un Wall
            else if(collidedEntity.getClass().equals(Wall.class)) {
                // On ajuste la vitesse à 0
                physicsEngine.setSpeedX(0, dynamicEntity);
                physicsEngine.setSpeedY(0, dynamicEntity);
            }
            //Avec un PacGum
            else if(collidedEntity.getClass().equals(PacGum.class)){
                incrementScore(1);
                entities.remove(collidedEntity);
                physicsEngine.deleteEntityCollisionArray(collidedEntity);
                pacGumCounter--;

                // Si le score est différent de 0 et est un multiple de 100, on ajoute une vie
                if(score.getScore() % 100 == 0 && score.getScore() != 0) {
                    incrementLifeCounter();
                }
            }
            //Avec un Fruit
            else if(collidedEntity.getClass().equals(Fruit.class)){
                int previousScore = score.getScore();

                incrementScore(50);
                entities.remove(collidedEntity);
                physicsEngine.deleteEntityCollisionArray(collidedEntity);

                // Si le score a changé de centaine, on ajoute une vie
                if(score.getScore() != 0 && score.getScore() % 100 < previousScore % 100) {
                    incrementLifeCounter();
                }
            }
            // Avec un Ghost
            else if(collidedEntity.getClass().equals(Ghost.class)){
                decrementLifeCounter();
                levelCreator();
            }
        }

        // Si c'est un Ghost
        else if (dynamicEntity.getClass().equals(Ghost.class)){
            // Avec un Wall
            if (collidedEntity.getClass().equals(Wall.class) || collidedEntity.getClass().equals(Ghost.class) || collidedEntity.getClass().equals(PacGum.class) || collidedEntity.getClass().equals(Fruit.class)){
                if (new Random().nextInt(2) == 1){ // X axis
                    // il ne faut pas attribuer la même direction qui l'a fait entrer en collision !
                    if (dynamicEntity.getSpeedX() > 0){
                        dynamicEntity.setSpeedX(-3);
                    }
                    else if (dynamicEntity.getSpeedX() < 0) dynamicEntity.setSpeedX(3);
                    else {
                        if (new Random().nextInt(2) == 1) dynamicEntity.setSpeedX(3);
                        else dynamicEntity.setSpeedX(-3);
                    }
                    dynamicEntity.setSpeedY(0);
                }
                else{ // Y axis
                    if (dynamicEntity.getSpeedY() > 0){
                        dynamicEntity.setSpeedY(-3);
                    }
                    else if (dynamicEntity.getSpeedY() < 0) dynamicEntity.setSpeedY(3);
                    else {
                        if (new Random().nextInt(2) == 1) dynamicEntity.setSpeedY(3);
                        else dynamicEntity.setSpeedY(-3);
                    }
                    dynamicEntity.setSpeedX(0);
                }
            }
            // Avec PacMan
            else if(collidedEntity.getClass().equals(PacMan.class)){
                decrementLifeCounter();
                levelCreator();
            }
        }
    }



    /**
     * Méthode qui s'occupe de d'associer à Pac-Man les paramètres données
     *
     * @param speedX Vitesse sur l'axe des x
     * @param speedY Vitesse sur l'axe des y
     * @param positionX Position initiale x
     * @param positionY Position initiale y
     * @param dimensionX Dimension en x
     * @param dimensionY Dimension en y
     */
    private void setPacman(int speedX, int speedY, int positionX, int positionY, int dimensionX, int dimensionY){
        List<Integer> position = new ArrayList<>();
        position.add(positionX);
        position.add(positionY);

        List<Integer> dimensions = new ArrayList<>();
        dimensions.add(dimensionX);
        dimensions.add(dimensionY);

        pacman.setSpeedX(speedX);
        pacman.setSpeedY(speedY);
        pacman.setPosition(position);
        pacman.setDimensions(dimensions);
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

        // Si nous voulons créer un PacMan
        if(entityClass == PacMan.class) {
            List<String> names = new ArrayList<>();
            names.add("src/Gameplay/Images/pacmans/pacmanR.png");
            names.add("src/Gameplay/Images/pacmans/pacmanL.png");
            names.add("src/Gameplay/Images/pacmans/pacmanD.png");
            names.add("src/Gameplay/Images/pacmans/pacmanU.png");

            pacman = new PacMan(speed, position, dimensions, names);
        }

        // Si nous voulons créer un Wall
        if(entityClass == Wall.class) {
            Wall wall = new Wall(position, dimensions, fileName);
            entities.add(wall);
        }

        // Si nous voulons créer un PacGum
        if(entityClass == PacGum.class){
            PacGum pacGum = new PacGum(position, dimensions, fileName);
            entities.add(pacGum);
            pacGumCounter++;
        }

        // Si nous voulons créer un Fruit
        if(entityClass == Fruit.class){
            Fruit fruit = new Fruit(position, dimensions, fileName);
            entities.add(fruit);
        }

        // Si nous voulons créer un Ghost
        if(entityClass == Ghost.class){
            List<String> list = new ArrayList<>();
            list.add(fileName);

            Ghost ghost = new Ghost(speed, position, dimensions, list);

            entities.add(ghost);
        }
    }

    /**
     * Getter
     * @return La liste des entités du jeu
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * Cette méthode permnet de charger un niveau à partir d'un fichier textuel
     *
     * @param filePath Chemin d'accès du fichier .txt contenant le niveau
     */
    private void levelLoader(String filePath){
        try {
            Scanner scanner = new Scanner(new File(filePath));
            width = scanner.nextInt();
            height = scanner.nextInt();

            List<Integer> position = new ArrayList<>();
            position.add(scanner.nextInt());
            position.add(scanner.nextInt());

            pacman.setPosition(position);

            String nameFile = scanner.next();

            int limit= scanner.nextInt();

            for (int i=0; i<limit; i++) { //pr un nb definis de mur
                int posX, posY, dimX, dimY;
                posX = scanner.nextInt();
                posY = scanner.nextInt();
                dimX = scanner.nextInt();
                dimY = scanner.nextInt();

                createEntity(null, null, posX, posY, dimX, dimY, nameFile, Wall.class);
            }

            limit = scanner.nextInt();

            for (int i=0; i<limit; i++){
                int posX, posY, dimX, dimY, speedX, speedY;
                nameFile = scanner.next(); //file

                speedX = scanner.nextInt();
                speedY = scanner.nextInt();
                posX = scanner.nextInt();
                posY = scanner.nextInt();
                dimX = scanner.nextInt();
                dimY = scanner.nextInt();

                createEntity(speedX, speedY, posX, posY, dimX, dimY, nameFile, Ghost.class);
            }

            nameFile = scanner.next();

            limit = scanner.nextInt();

            for (int i=0; i<limit; i++){
                int posX, posY, dimX, dimY;
                posX = scanner.nextInt();
                posY = scanner.nextInt();
                dimX = scanner.nextInt();
                dimY = scanner.nextInt();

                createEntity(null, null, posX, posY, dimX, dimY, nameFile, PacGum.class);
            }

            nameFile = scanner.next();

            limit = scanner.nextInt();

            for (int i=0; i<limit; i++){
                int posX, posY, dimX, dimY;
                posX = scanner.nextInt();
                posY = scanner.nextInt();
                dimX = scanner.nextInt();
                dimY = scanner.nextInt();

                createEntity(null, null, posX, posY, dimX, dimY, nameFile, Fruit.class);
            }

        }catch(Exception e) {e.printStackTrace();}
    }

    /**
     * Création d'un niveau et des entités qui le compose
     */
    private void levelCreator(){
        // On créer la liste d'entités
        entities = new ArrayList<>();

        pacGumCounter = 0; // On initialise le compteur de Pac-Gommes

        // On initialise les propriétés de Pac-Man
        setPacman(0, 0, 120, 100, 30, 30);
        entities.add(pacman);

        // On charge un niveau
        levelLoader("src/Gameplay/Levels/gameZone.txt");

        // On intialise le tableau de collision
        physicsEngine.initializeCollisionArray(entities, width, height);
    }

    private void incrementScore(int value) { score.setScore(score.getScore() + value); }

    private void incrementLifeCounter(){ life.setLifeCounter(life.getLifeCounter() + 1); }

    private void decrementLifeCounter(){ life.setLifeCounter(life.getLifeCounter() - 1); }

    private boolean checkGameOver(){ return life.getLifeCounter() == 0; }
}