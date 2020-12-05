package Gameplay.Elements;

import Engine.Graphics.Elements.Text;

public class Life extends Text {

    int lifeCounter;

    public Life(int lifeCounter, int positionX, int positionY) {
        super("Lives left = " + lifeCounter, positionX, positionY);
        this.lifeCounter = lifeCounter;
    }

    public int getLifeCounter(){ return this.lifeCounter; }

    public void setLifeCounter(int lifeCounter){
        this.lifeCounter = lifeCounter;
        this.setText("Lives left = " + lifeCounter);
    }

}
