package Gameplay;

import Engine.DynamicEntity;

import java.util.List;

public class PacMan extends DynamicEntity {

    DynamicEntity dynamicEntity;

    public PacMan(){
    }

    public List<Double> getPosition(){
        return dynamicEntity.getPosition();
    }

    public void setPosition(List<Double> position){
        dynamicEntity.setPosition(position);
    }

    public String getImage(){
        return dynamicEntity.getImage();
    }

    public void setImage(String fileName){
        dynamicEntity.setImage(fileName);
    }
}
