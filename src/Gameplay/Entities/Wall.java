package Gameplay.Entities;

import Engine.Entities.StaticEntity;

import java.util.List;

/**
 * Gestion d'un mur
 */
public class Wall extends StaticEntity {
    public Wall(List<Integer> position, List<Integer> dimensions, String fileName) {
        super(position, dimensions, fileName);
    }
}
