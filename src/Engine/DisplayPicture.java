/*package Engine;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

//La classe DisplayPicture permet d'afficher une image sur la scène via JavaFX.
public class DisplayPicture extends Application {

    private Scene scene;
    private String imageName;
    private double x;
    private double y;
    private double size;
    private double ratio;

    /**
     * Le constructeur permet de récupérer les données nécessaire à l'affichage de l'image
     *
     * @param scene La scène sur laquelle on souhaite ajouter l'image
     * @param imageName La chaîne de caractère correspondante au nom de l'image à afficher
     * @param x La coordonnée x de la position où l'image doit être afficher
     * @param y La coordonnée y de la position où l'image doit être afficher
     * @param size La taille de l'image à afficher
     * @param ratio Le ratio de l'image à afficher

    public DisplayPicture(Scene scene, String imageName, double x, double y, double size, double ratio) {
        this.scene = scene;
        this.imageName = imageName;
        this.x = x;
        this.y = y;
        this.size = size;
        this.ratio = ratio;
    }

    /**
     * La méthode éxécuter pour afficher l'image sur la scène
     *
     * @param stage
     * @throws Exception

    //@Override
    public void start(Stage stage) throws Exception {
        // On créer une instance d’Image contenant le nom de l’image à afficher
        Image image = new Image("Images/" + imageName, size, size, true, true);

        // On implémente l'image dans un noeud graphique
        ImageView imageView = new ImageView(image);

        // Implantation du nœud contenant l’image dans la scène existante du jeu
        //scene.getRoot();

        // On affiche la scène
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }
}*/