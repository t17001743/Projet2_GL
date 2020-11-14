package Engine;

import Gameplay.Controller;
import Gameplay.Game;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Couche graphique du moteur
 */
public class GraphicsEngine extends Application {

    private Scene scene;  //scène existante utilisée pour l'affichage du jeu
    private Stage stage;  //la fenêtre de l'application
    private GraphicsContext context;  //pour modifier le canvas

    /**
     * Ajout d'une image à la scène
     * @param entity l'entité à dessiner
     * @throws FileNotFoundException si le programme ne trouve pas le fichier
     */
    public void drawImage(Entity entity) throws FileNotFoundException{
        // On crée une instance d’Image contenant le nom de l’image à afficher
        Image image = new Image(new FileInputStream("src/Gameplay/Images/" + entity.getImage()));

        // On implémente l'image dans un noeud graphique
        ImageView imageView = new ImageView(image);

        //position de l'image dans la scène
        imageView.setX(entity.getPosition().get(0));
        imageView.setY(entity.getPosition().get(1));

        //hauteur et largeur de l'image
        imageView.setFitHeight(entity.getDimensions().get(0));
        imageView.setFitWidth(entity.getDimensions().get(1));

        //ratio de l'image
        imageView.setPreserveRatio(true);

        //racine ("noeud graphique") de l'image
        Group root = new Group(imageView);

        Canvas canvas = new Canvas(512, 512);
        root.getChildren().add(canvas);

        //Creating a scene object
        scene = new Scene(root, 1200, 800, Color.BLACK);

        context = canvas.getGraphicsContext2D();
    }

    /**
     * Lancement du thread du moteur graphique
     * @param stage la fenêtre de l'application
     * @throws FileNotFoundException si le fichier de l'image n'a pas été trouvé
     */
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        Game game = new Game();
        game.start();

        List<Double> coordonnees = new ArrayList<>();
        coordonnees.add(50.0);
        coordonnees.add(25.0);

        List<Double> dimensions = new ArrayList<>();
        dimensions.add(50.0);
        dimensions.add(50.0);


        stage.setTitle("Pac-Man");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    /**
     * Effacement de l'ancienne image
     */
    public void clearFrame(){
        context.clearRect(0, 0, scene.getWidth(), scene.getHeight());
    }

    /**
     * Programme principal
     * @param args
     */
    public static void main(String args[]){
        launch(args);
    }
}
