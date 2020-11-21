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
     * @param entity l'entité dynamique
     */
    public void updateCoordinates(DynamicEntity entity){
        if(!checkCollision(entity)) {
            // Si l'entité n'a pas de vitesse, il n'est pas nécessaire de la déplacer
            if(entity.getSpeedX() != 0 || entity.getSpeedY() != 0) {
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
     * Définit comme "occupé" les positions qui nétaient précédemment pas occupés par une entité mais qui le seront à la suite de son déplacement
     * On évite ainsi de regarder à nouveau les pixels du tableau qui ne changent pas
     *
     * @param entity L'entité concernée par la mise-à-jour
     */
    private void updateNewPosCollisionArray(DynamicEntity entity) {
        int startPositionX = entity.getPosition().get(0);
        int startPositionY = entity.getPosition().get(1);
        int endPositionX = startPositionX + 1;
        int endPositionY = startPositionY + 1;

        // On définit les extrémités de la boucle en terme de coordonnée x
        if(entity.getSpeedX() > 0) {
            startPositionX += entity.getDimensions().get(0);
            endPositionX = startPositionX + entity.getSpeedX();
        }
        else if(entity.getSpeedX() < 0) {
            endPositionX = startPositionX;
            startPositionX += entity.getSpeedX();
        }

        // On définit les extrémités de la boucle en terme de coordonnée y
        if(entity.getSpeedY() > 0) {
            startPositionY += entity.getDimensions().get(1);
            endPositionY = startPositionY + entity.getSpeedY();
        }
        else if(entity.getSpeedY() < 0) {
            endPositionY = startPositionY;
            startPositionY += entity.getSpeedY();
        }

        System.out.println("ADD");
        System.out.println("startPositionX = " + startPositionX);
        System.out.println("endPositionX = " + endPositionX);
        System.out.println("startPositionY = " + startPositionY);
        System.out.println("endPositionY = " + endPositionY);

        for(int i = startPositionX; i < endPositionX; i++) {
            for(int j = startPositionY; j < endPositionY; j++) {
                collisionArray[i][j] = new Pair(true, entity);
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
        int endPositionX = startPositionX + 1;
        int endPositionY = startPositionY + 1;

        // On définit les extrémités de la boucle en terme de coordonnée x
        if(entity.getSpeedX() > 0) endPositionX = startPositionX + entity.getSpeedX();
        else if(entity.getSpeedX() < 0) {
            endPositionX = startPositionX + entity.getDimensions().get(0);
            startPositionX = endPositionX + entity.getSpeedX();
        }

        // On définit les extrémités de la boucle en terme de coordonnée y
        if(entity.getSpeedY() > 0) endPositionY = startPositionY + entity.getSpeedY();
        else if(entity.getSpeedY() < 0) {
            endPositionY = startPositionY + entity.getDimensions().get(1);
            startPositionY = endPositionY + entity.getSpeedY();
        }

        System.out.println("DELETE");
        System.out.println("startPositionX = " + startPositionX);
        System.out.println("endPositionX = " + endPositionX);
        System.out.println("startPositionY = " + startPositionY);
        System.out.println("endPositionY = " + endPositionY);


        for(int i = startPositionX; i < endPositionX; i++) {
            for(int j = startPositionY; j < endPositionY; j++) {
                collisionArray[i][j] = new Pair(false, null);
            }
        }
    }

    /**
     * Vérifié si le pixel convoité par une entité est déjà occupé
     *
     * @param entity L'entité en question
     * @return Vrai si la nouvelle position est occupée, faux sinon
     */
    private boolean checkCollision(DynamicEntity entity) {
        // On récupère les coordonnées correspondantes à la position que l'on souhaite occuper
        int newPositionX = entity.getPosition().get(0) + entity.getSpeedX();
        int newPositionY = entity.getPosition().get(1) + entity.getSpeedY();

        // On ajoute la taille de l'objet en fonction de la direction empruntée
        if(entity.getSpeedX() > 0) newPositionX += entity.getDimensions().get(0);
        if(entity.getSpeedY() > 0) newPositionY += entity.getDimensions().get(1);

        // Si le pixel sort de la fenêtre
        if(newPositionX >= collisionArray.length || newPositionX < 0 || newPositionY >= collisionArray[0].length || newPositionY < 0) {
            return true;
        }

        // Sinon, regarde si le pixel convoité est déjà occupé
        return collisionArray[newPositionX][newPositionY].getKey().equals(true);
    }

}
