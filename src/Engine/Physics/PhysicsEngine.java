package Engine.Physics;

import Engine.Entities.DynamicEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Moteur physique
 */

public class PhysicsEngine {

    /**
     * Mise à jour de la vitesse sur l'axe des abscisses
     * @param speed vitesse de l'entité qui se déplace
     * @param entity l'entité dynamique
     */
    public void setSpeedX(double speed, DynamicEntity entity){ entity.setSpeedX(speed); }

    /**
     * Mise à jour de la vitesse sur l'axe des ordonnées
     * @param speed vitesse de l'entité qui se déplace
     * @param entity l'entité dynamique
     */
    public void setSpeedY(double speed, DynamicEntity entity){ entity.setSpeedY(speed); }

    /**
     * Mise à jour des coordonnées de l'entité dynamique grâce aux données liées à sa vitesse
     * @param entity l'entité dynamique
     */
    public void updateCoordinates(DynamicEntity entity){
        List list = new ArrayList<Double>();

        list.add(entity.getPosition().get(0) + entity.getSpeedX());
        list.add(entity.getPosition().get(1) + entity.getSpeedY());

        entity.setPosition(list);
    }

}
