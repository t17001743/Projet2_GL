package Gameplay.Entities;

import Engine.Entities.DynamicEntity;

import java.util.*;

/**
 * Gestion du personnage Pac-Man
 */
public class PacMan extends DynamicEntity {
    public PacMan(List<Integer> speed, List<Integer> position, List<Integer> dimensions, String fileName){
        super(speed, position, dimensions, fileName);
    }
}
