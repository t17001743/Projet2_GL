package Gameplay.Entities;

import Engine.Entities.DynamicEntity;

import java.util.*;

/**
 * Gestion du personnage Pac-Man
 */
public class PacMan extends DynamicEntity {
    public PacMan(List<Double> speed, List<Double> position, List<Double> dimensions, String fileName){
        super(speed, position, dimensions, fileName);
    }
}
