package Engine.Graphics;

import Engine.Entity;
import Engine.WindowGraphics;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Couche graphique du moteur
 */
public class DrawGraphics2D extends WindowGraphics {

    private Scene scene;  //scène existante utilisée pour l'affichage du jeu
    private GraphicsContext context;  //pour modifier le canvas

    public DrawGraphics2D(Scene scene, GraphicsContext context, Stage stage){
        this.scene = scene;
        this.context = context;
    }

    public DrawGraphics2D(Stage stage){
        setStage(stage);
    }

    @Override
    public void create2DWindow(String title, int width, int height) {
        super.create2DWindow(title, width, height);

        this.scene = getScene();
        this.context = getContext();
    }

    /**
     * Ajout d'une image à la scène
     * @param entity l'entité à dessiner
     * @throws FileNotFoundException si le programme ne trouve pas le fichier
     */
    public void drawEntity(Entity entity) throws FileNotFoundException{
        System.out.println("drawEntity()");

        FileInputStream file = new FileInputStream(entity.getImage());
        Image image = new Image(file);

        System.out.println("\t\t" + context);

        context.drawImage(image, entity.getPosition().get(0), entity.getPosition().get(1), 50, 50);
        context.fill();
    }

    public void drawBackground(Color color){
        System.out.println("drawBackground()");

        context.setFill(color);
        context.fillRect(0, 0, context.getCanvas().getWidth(), context.getCanvas().getHeight());
    }

    /**
     * Effacement de l'ancienne image
     */
    public void clearFrame(){
        System.out.println("start clearing" + context);
        context.clearRect(0, 0, this.context.getCanvas().getWidth(), this.context.getCanvas().getHeight());
    }


}
