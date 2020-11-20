package Engine;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;


public abstract class WindowGraphics {

    Stage stage;
    Scene scene;
    GraphicsContext context;

    public void create2DWindow(String title, int width, int height){
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);

        root.getChildren().add(canvas);

        this.scene = new Scene(root);
        this.stage.setScene(this.scene);
        this.context = canvas.getGraphicsContext2D();

        System.out.println("\t\t" + context);

        stage.setTitle(title);
        stage.sizeToScene();
        this.stage.show();
    }

    public Scene getScene() {
        return scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public GraphicsContext getContext() {
        return context;
    }

}
