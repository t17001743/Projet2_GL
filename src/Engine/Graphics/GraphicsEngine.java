package Engine.Graphics;

import Engine.Entities.Entity;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Classe correspondante au moteur graphique
 *
 * Elle a étend le créateur de fenêtre pour permettre l'affichage
 * Puis s'occupe de modifier cette fenêtre
 */
public class GraphicsEngine extends WindowCreator {

    private Scene scene;  // Scène existante utilisée pour l'affichage du jeu
    private GraphicsContext context;  // Pour modifier le canvas

    /**
     * Le constructeur
     *
     * @param stage La stage lié à l'application exécuté
     */
    public GraphicsEngine(Stage stage){
        setStage(stage);
    }

    /**
     * Demande la création de la fenêtre et récupère la scène et le contexte graphique
     *
     * @param title Nom de la fenêtre
     * @param width Longueur de la fenêtre
     * @param height Hauteur de la fenêtre
     */
    @Override
    public void create2DWindow(String title, int width, int height) {
        super.create2DWindow(title, width, height);

        this.scene = getScene();
        this.context = getContext();
    }

    /**
     * Ajout d'une image à la scène
     *
     * @param entity L'entité à dessiner
     * @throws FileNotFoundException Si le programme ne trouve pas l'image associée à l'entité
     */
    public void drawEntity(Entity entity) throws FileNotFoundException{
        FileInputStream file = new FileInputStream(entity.getImage());
        Image image = new Image(file);

        context.drawImage(image, entity.getPosition().get(0), entity.getPosition().get(1), 50, 50);
        context.fill();
    }

    /**
     * Colorie le fond de la scène
     *
     * @param color La couleur désirée
     */
    public void drawBackground(Color color){
        context.setFill(color);
        context.fillRect(0, 0, context.getCanvas().getWidth(), context.getCanvas().getHeight());
    }

    /**
     * Efface le contenu de la scène
     */
    public void clearFrame(){
        context.clearRect(0, 0, this.context.getCanvas().getWidth(), this.context.getCanvas().getHeight());
    }
}
