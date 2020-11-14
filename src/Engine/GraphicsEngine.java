package Engine;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private GraphicsContext context;

    //public GraphicsEngine(Stage stage){
      //  this.stage = stage;
    //}

    public GraphicsEngine() {
    }

    public void drawImage(String fileName, List<Double> position, List<Double> dimensions) throws FileNotFoundException{
        // On crée une instance d’Image contenant le nom de l’image à afficher
        Image image = new Image(new FileInputStream("src/Gameplay/Images/" + fileName));

        // On implémente l'image dans un noeud graphique
        ImageView imageView = new ImageView(image);

        //position de l'image dans la scène
        imageView.setX(position.get(0));
        imageView.setY(position.get(1));

        //hauteur et largeur de l'image
        imageView.setFitHeight(dimensions.get(0));
        imageView.setFitWidth(dimensions.get(1));

        //ratio de l'image
        imageView.setPreserveRatio(true);

        //racine ("noeud graphique") de l'image
        Group root = new Group(imageView);

        //Creating a scene object
        scene = new Scene(root, 1200, 800);

                /*Group root = new Group();
        this.theScene = new Scene( root );
        primaryStage.setScene( theScene );

        this.canvas = new Canvas( 512, 512 );
        root.getChildren().add( canvas );

        this.gc = canvas.getGraphicsContext2D();
        primaryStage.show();*/
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        List<Double> coordonnees = new ArrayList<>();
        coordonnees.add(50.0);
        coordonnees.add(25.0);

        List<Double> dimensions = new ArrayList<>();
        dimensions.add(50.0);
        dimensions.add(50.0);

        drawImage("pacman.png", coordonnees, dimensions);

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
