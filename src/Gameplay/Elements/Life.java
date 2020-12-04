package Gameplay.Elements;

import Engine.Graphics.Elements.Text;

public class Life extends Text {

    int lifeCounter;

    public Life(int lifeCounter, int positionX, int positionY) {
        super(String.valueOf(lifeCounter), positionX, positionY);

        this.lifeCounter = lifeCounter;
    }

    public int getLifeCounter(){ return this.lifeCounter; }

    public void setLifeCounter(int lifeCounter){
        this.lifeCounter = lifeCounter;
        super.setText(String.valueOf(lifeCounter));
    }

}
