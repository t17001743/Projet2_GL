package Engine;

public class PhysicsEngine {

    /**
     * Mise à jour de la vitesse sur l'axe des abscisses
     * @param speed vitesse de l'entité qui se déplace
     * @param entity l'entité dynamique
     */
    public void setSpeedX(double speed, DynamicEntity entity){
        setSpeedX(speed, entity);
    }

    /**
     * Mise à jour de la vitesse sur l'axe des ordonnées
     * @param speed vitesse de l'entité qui se déplace
     * @param entity l'entité dynamique
     */
    public void setSpeedY(double speed, DynamicEntity entity){
        setSpeedY(speed, entity);
    }

    /**
     * Mise à jour des coordonnées de l'entité dynamique grâce aux données liées à sa vitesse
     * @param entity l'entité dynamique
     */
    public void updateCoordinates(DynamicEntity entity){
        entity.setPosition(entity.getSpeed());
    }
}
