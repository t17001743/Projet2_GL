package Engine;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
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
    //private Stage stage;
    private GraphicsContext context;

    //public GraphicsEngine(Stage stage){
      //  this.stage = stage;
    //}

    public GraphicsEngine() {
    }

    public void drawImage(String fileName, List<Double> position){
        // On créer une instance d’Image contenant le nom de l’image à afficher
        Image image = new Image("Images/" + fileName);

        // On implémente l'image dans un noeud graphique
        ImageView imageView = new ImageView(image);

        //Setting the position of the image
        imageView.setX(position.get(0));
        imageView.setY(position.get(1));

        //setting the fit height and width of the image view
        imageView.setFitHeight(455);
        imageView.setFitWidth(500);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        //Creating a Group object
        Group root = new Group(imageView);

        //Creating a scene object
        scene = new Scene(root, 600, 500);

        // Implantation du nœud contenant l’image dans la scène existante du jeu
        //scene.getRoot();
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        /*List<Double> coordonnees = new ArrayList<>();
        coordonnees.add(50.0);
        coordonnees.add(25.0);

        drawImage("pacman.png", coordonnees);

        stage.setTitle("Pac-Man");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();*/

        // On créer une instance d’Image contenant le nom de l’image à afficher
        Image image = new Image(new FileInputStream("src/Gameplay/Images/pacman.png"));

        // On implémente l'image dans un noeud graphique
        ImageView imageView = new ImageView(image);

        //Setting the position of the image
        imageView.setX(50);
        imageView.setY(50);

        //setting the fit height and width of the image view
        imageView.setFitHeight(455);
        imageView.setFitWidth(500);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        //Creating a Group object
        Group root = new Group(imageView);

        //Creating a scene object
        scene = new Scene(root, 600, 500);

        stage.setTitle("Pac-Man");
        stage.setScene(scene);
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
    /*public static void main(String args[]){
        launch(args);
    }*/
}
