package Engine.Physics;

import Engine.Entities.DynamicEntity;
import Engine.Entities.Entity;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Moteur physique
 */

public class PhysicsEngine {

    private Pair<Boolean, Entity>[][] collisionArray;

    public PhysicsEngine(){}

    public void initializeCollisionArray(ArrayList<Entity> entities, int width, int height){
        collisionArray = new Pair[width][height];

        // Initialisation du tableau des collisions comme en étant vide partout
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                collisionArray[i][j] = new Pair(false, null);
            }
        }

        // On définit toutes les cases occupés par des entités
        for(Entity entity : entities) {
            int positionX = entity.getPosition().get(0);
            int positionY = entity.getPosition().get(1);

            for(int i = positionX; i < positionX + entity.getDimensions().get(0); i++) {
                for(int j = positionY; j < positionY + entity.getDimensions().get(1); j++) {
                    collisionArray[i][j] = new Pair(true, entity);
                }
            }
        }
    }

    /**
     * Mise à jour de la vitesse sur l'axe des abscisses
     * @param speed vitesse de l'entité qui se déplace
     * @param entity l'entité dynamique
     */
    public void setSpeedX(Integer speed, DynamicEntity entity){ entity.setSpeedX(speed); }

    /**
     * Mise à jour de la vitesse sur l'axe des ordonnées
     * @param speed vitesse de l'entité qui se déplace
     * @param entity l'entité dynamique
     */
    public void setSpeedY(Integer speed, DynamicEntity entity){ entity.setSpeedY(speed); }

    /**
     * Vérifie si une entité dynamique est en mouvement
     *
     * @param entity L'entité dynamique à consulter
     * @return True si elle est en mouvement, False sinon
     */
    public boolean isMoving(DynamicEntity entity) {
        return entity.getSpeedX() != 0 || entity.getSpeedY() != 0;
    }

    /**
     * Mise à jour des coordonnées de l'entité dynamique grâce aux données liées à sa vitesse
     *
     * @param entity l'entité dynamique
     */
    public void updateCoordinates(DynamicEntity entity){
        // On efface l'ancienne position
        deleteOldPosCollisionArray(entity);

        List list = new ArrayList<Integer>();
        list.add(entity.getPosition().get(0) + entity.getSpeedX());
        list.add(entity.getPosition().get(1) + entity.getSpeedY());

        // On met-à-jour la nouvelle position
        updateNewPosCollisionArray(entity);
        entity.setPosition(list);
    }

    /**
     * Vérifié les collisions
     *
     * @param entity L'entité à vérifier
     * @return Renvoi une Pair comprenant deux éléments :
     *  - True s'il y a une collision, False sinon
     *  - L'entité avec laquelle une collision s'est produite, null s'il n'y a pas de collision ou que c'est avec une bordure
     */
    public Pair<Boolean, Entity> checkCollision(DynamicEntity entity) {
        int startPositionX = entity.getPosition().get(0);
        int startPositionY = entity.getPosition().get(1);
        int endPositionX = startPositionX + entity.getDimensions().get(0);
        int endPositionY = startPositionY + entity.getDimensions().get(1);

        // On définit les extrémités de la boucle en terme de coordonnée x
        if(entity.getSpeedX() > 0) {
            startPositionX += entity.getDimensions().get(0);
            endPositionX += entity.getSpeedX();
        }
        else if(entity.getSpeedX() < 0) {
            endPositionX = startPositionX;
            startPositionX += entity.getSpeedX();
        }

        // On définit les extrémités de la boucle en terme de coordonnée y
        if(entity.getSpeedY() > 0) {
            startPositionY += entity.getDimensions().get(1);
            endPositionY += entity.getSpeedY();
        }
        else if(entity.getSpeedY() < 0) {
            endPositionY = startPositionY;
            startPositionY += entity.getSpeedY();
        }

        // On parcours chaque pixel concerné par le déplacement
        for(int x = startPositionX; x < endPositionX; x++) {
            for(int y = startPositionY; y < endPositionY; y++) {
                // Si le pixel sort de la fenêtre
                if(x >= collisionArray.length || x < 0 || y >= collisionArray[0].length || y < 0) {
                    // On considère une collision avec une bordure
                    return new Pair<>(true, null);
                }
                // Sinon, on regarde si le pixel convoité est déjà occupé par une
                if(collisionArray[x][y].getKey().equals(true)){
                    // On considère une collision avec une entité
                    return new Pair<>(true, collisionArray[x][y].getValue());
                }
            }
        }

        // Si on sort de la boucle, alors aucune collision n'a eu lieu
        return new Pair<>(false, null);
    }

    /**
     * Définit comme "occupé" les positions qui n'étaient précédemment pas occupés par une entité mais qui le seront à la suite de son déplacement
     * On évite ainsi de regarder à nouveau les pixels du tableau qui ne changent pas
     *
     * @param entity L'entité concernée par la mise-à-jour
     */
    private void updateNewPosCollisionArray(DynamicEntity entity) {
        int startPositionX = entity.getPosition().get(0);
        int startPositionY = entity.getPosition().get(1);
        int endPositionX = startPositionX + entity.getDimensions().get(0);
        int endPositionY = startPositionY + entity.getDimensions().get(1);

        // On définit les extrémités de la boucle en terme de coordonnée x
        if(entity.getSpeedX() > 0) {
            startPositionX += entity.getDimensions().get(0);
            endPositionX += entity.getSpeedX();
        }
        else if(entity.getSpeedX() < 0) {
            endPositionX = startPositionX;
            startPositionX += entity.getSpeedX();
        }

        // On définit les extrémités de la boucle en terme de coordonnée y
        if(entity.getSpeedY() > 0) {
            startPositionY += entity.getDimensions().get(1);
            endPositionY += entity.getSpeedY();
        }
        else if(entity.getSpeedY() < 0) {
            endPositionY = startPositionY;
            startPositionY += entity.getSpeedY();
        }

        for(int x = startPositionX; x < endPositionX; x++) {
            for(int y = startPositionY; y < endPositionY; y++) {
                collisionArray[x][y] = new Pair(true, entity);
            }
        }
    }

    /**
     * Définit comme "non-occupé" les positions qui étaient précédemment occupés par une entité mais qui ne le seront plus à la suite de son déplacement
     * On évite ainsi de regarder à nouveau les pixels du tableau qui ne changent pas
     *
     * @param entity L'entité concernée par la mise-à-jour
     */
    private void deleteOldPosCollisionArray(DynamicEntity entity) {
        int startPositionX = entity.getPosition().get(0);
        int startPositionY = entity.getPosition().get(1);
        int endPositionX = startPositionX + entity.getDimensions().get(0);
        int endPositionY = startPositionY + entity.getDimensions().get(1);

        // On définit les extrémités de la boucle en terme de coordonnée x
        if(entity.getSpeedX() > 0) endPositionX = startPositionX + entity.getSpeedX();
        else if(entity.getSpeedX() < 0) startPositionX = endPositionX + entity.getSpeedX();

        // On définit les extrémités de la boucle en terme de coordonnée y
        if(entity.getSpeedY() > 0) endPositionY = startPositionY + entity.getSpeedY();
        else if(entity.getSpeedY() < 0) startPositionY = endPositionY + entity.getSpeedY();

        for(int x = startPositionX; x < endPositionX; x++) {
            for(int y = startPositionY; y < endPositionY; y++) {
                collisionArray[x][y] = new Pair(false, null);
            }
        }
    }

    public void deleteEntityCollisionArray(Entity entity){
        int startPositionX = entity.getPosition().get(0);
        int startPositionY = entity.getPosition().get(1);
        int endPositionX = startPositionX + entity.getDimensions().get(0);
        int endPositionY = startPositionY + entity.getDimensions().get(1);

        for(int x = startPositionX; x < endPositionX; x++) {
            for(int y = startPositionY; y < endPositionY; y++) {
                collisionArray[x][y] = new Pair(false, null);
            }
        }
    }
}
