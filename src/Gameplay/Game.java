package Gameplay;

import Engine.Entity;
import Engine.GraphicsEngine;
import Engine.PhysicsEngine;
import com.sun.prism.Graphics;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Game extends Thread {
    /*Canvas canvas;
    GraphicsContext gc;
    Scene theScene;


    @Override
    public void start(Stage primaryStage) throws Exception{
        ExternalThread externalThread = new ExternalThread(this);
        externalThread.start();*/
        /*
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 512, 512));
        primaryStage.show();
        */
        /*Group root = new Group();
        this.theScene = new Scene( root );
        primaryStage.setScene( theScene );

        this.canvas = new Canvas( 512, 512 );
        root.getChildren().add( canvas );

        this.gc = canvas.getGraphicsContext2D();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void setFillColor(Color color){
        theScene.setFill(color);
    }

    public void createRectangle(int x, int y, int l, int h){
        gc.setFill(Color.PAPAYAWHIP);
        gc.fillOval(x,y,l, h);
    }

    public void clearImage(){
        gc.clearRect(0,0,512,512 );
    }

    public void background(Color color){
        gc.setFill(color);
        gc.fillRect(0,0,512,512);
    }*/




    //instancie Pacman et Controller

    private ArrayList<Entity> entities;
    private PhysicsEngine physicsEngine;
    private GraphicsEngine graphicsEngine;
    private Stage stage;

    public void init(){

    }

    public void run(){
        //on efface l'ancienne image
        graphicsEngine.clearFrame();
        //on ajoute chaque élément à la scène graphique
        try {
            for(int i=0; i < entities.size(); i++){
                graphicsEngine.drawImage(entities.get(i).getImage(), entities.get(i).getPosition(), entities.get(i).getDimensions());
            }
            //le moteur affiche la nouvelle image
            graphicsEngine.start(stage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}