package Gameplay.Entities;

import Engine.Entities.Entity;
import Engine.Entities.StaticEntity;

import java.util.List;

/**
 * Gestion d'un mur
 */
public class PacGum extends StaticEntity {
    public PacGum(List<Integer> position, List<Integer> dimensions, String fileName) {
        super(position, dimensions, fileName);
    }
}
