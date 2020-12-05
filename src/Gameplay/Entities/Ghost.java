package Gameplay.Entities;

import Engine.Entities.DynamicEntity;

import java.util.List;

public class Ghost extends DynamicEntity {

    public Ghost(List<Integer> speed, List<Integer> position, List<Integer> dimensions, List<String> fileName){
        super(speed, position, dimensions, fileName);
    }

    @Override
    public void setImage(String fileName) {

    }

}
