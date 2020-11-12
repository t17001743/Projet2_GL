//package Engine;

import Gameplay.Game;
import javafx.scene.paint.Color;

/*public class ExternalThread extends Thread {

    Game game;

    public ExternalThread(Game game) {
        this.game = game;
    }

    public void run(){

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.setFillColor(Color.CHOCOLATE);

        for (int i = 256; i < 512 ; i++){
            game.clearImage();
            game.background(Color.CHOCOLATE);
            game.setFillColor(Color.BLUE);
            game.createRectangle(i,i,30,30);

            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }
}*/