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

    private Pair[][] collisionArray;

    public PhysicsEngine(ArrayList<Entity> entities, Integer width, Integer height) {
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
     * Mise à jour des coordonnées de l'entité dynamique grâce aux données liées à sa vitesse
     * et vérifie s'il n'y a pas une collision
     *
     * Si une entité ne bouge pas, on ne vérifie rien et on ne met rien à jour
     *
     * @param entity l'entité dynamique
     */
    public void updateCoordinates(DynamicEntity entity){
        // Si l'entité n'a pas de vitesse, il n'est pas nécessaire de vérifier quoi que ce soit
        if(entity.getSpeedX() != 0 || entity.getSpeedY() != 0) {
            if(!checkCollision(entity)) {
                // On efface l'ancienne position
                deleteOldPosCollisionArray(entity);

                List list = new ArrayList<Integer>();
                list.add(entity.getPosition().get(0) + entity.getSpeedX());
                list.add(entity.getPosition().get(1) + entity.getSpeedY());

                // On met-à-jour la nouvelle position
                updateNewPosCollisionArray(entity);
                entity.setPosition(list);
            }
        }
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

    /**
     * Vérifié si les pixels convoités par une entité sont déjà occupés
     *
     * @param entity L'entité en question
     * @return Vrai si la nouvelle position est occupée, faux sinon
     */
    private boolean checkCollision(DynamicEntity entity) {
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
                    return true;
                }
                // Sinon, on regarde si le pixel convoité est déjà occupé par une
                else if(collisionArray[x][y].getKey().equals(true)){
                    // On considère une collision avec une entité
                    return true;
                }
            }
        }

        // Si on sort de la boucle, alors aucune collision n'a eu lieu
        return false;
    }

}
