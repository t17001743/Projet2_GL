package Engine;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 * Classe qui possède les éléments correspondants à la création d'une fenêtre
 */
public abstract class WindowCreator {

    Stage stage;
    Scene scene;
    GraphicsContext context;

    /**
     * Création d'une fenêtre en fonction des paramètres fourni
     *
     * @param title Nom de la fenêtre
     * @param width Longueur de la fenêtre
     * @param height Hauteur de la fenêtre
     */
    public void create2DWindow(String title, int width, int height){
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);

        root.getChildren().add(canvas);

        this.scene = new Scene(root);
        this.stage.setScene(this.scene);
        this.context = canvas.getGraphicsContext2D();

        stage.setTitle(title);
        stage.sizeToScene();
        this.stage.show();
    }

    /**
     * Setter
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Getter
     *
     * @return La scene lié à la fenêtre
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Getter
     *
     * @return La stage lié à la fenêtre
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Getter
     *
     * @return Le contexte graphique lié à la fenêtre
     */
    public GraphicsContext getContext() {
        return context;
    }

}
