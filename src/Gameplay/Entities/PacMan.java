package Gameplay.Entities;

import Engine.Entities.DynamicEntity;
import Engine.Entities.Entity;
import Gameplay.Elements.Score;

import java.util.*;

/**
 * Gestion du personnage Pac-Man
 */
public class PacMan extends DynamicEntity {
    public PacMan(List<Integer> speed, List<Integer> position, List<Integer> dimensions, List<String> fileName){
        super(speed, position, dimensions, fileName);
    }

    @Override
    public String getImage(){
        int index = 0;

        if (getSpeedX() < 0 ) index = 1;
        else if (getSpeedY() > 0 ) index = 2;
        else if (getSpeedY() < 0) index = 3;

        return super.fileName.get(index);
    }

    @Override
    public void setImage(String fileName) {

    }
}
