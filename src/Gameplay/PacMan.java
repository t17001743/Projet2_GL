package Gameplay;

import Engine.Character;
import Engine.DisplayPicture;

import java.util.List;

public class PacMan implements Character {
    /**
     * Position courante de PacMan, dans la liste, l'indice 0 indique la position sur l'axe x, l'indice 1 la position sur l'axe y
     */
    private List<Double> position;
    /**
     * Vitesse de PacMan, dans la liste, l'indice 0 indique la position sur l'axe x, l'indice 1 la position sur l'axe y
     */
    private List<Double> speed;
    private String path;
    private DisplayPicture displayPicture;
    private boolean stopped;

    void nextPath(){}

    void draw(){}

    /**
     * @return position
     */
    @Override
    public List<Double> getPosition() {
        return  position;
    }

    /**
     * @param newPosition avec en indice 0, position x, et en indice 1 posittion y
     */
    @Override
    public void setPosition(List<Double> newPosition) {
        if(newPosition.size() <2){
            System.out.println("Invalid newPosition in PacMan.java");
            return;
        }
        position.set(0, newPosition.get(0));
        position.set(0, newPosition.get(1));
    }
    /**
     *
     * @return speed
     */
    @Override
    public List<Double> getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(List<Double> newSpeed) {
        speed.set(0, newSpeed.get(0));
        speed.set(1, newSpeed.get(1));
    }
}
