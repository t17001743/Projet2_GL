package Engine.Entities;

import java.util.LinkedList;
import java.util.List;

/**
 * Entités qui se déplacent (Pac-man, les fantômes, etc.)
 */
public abstract class DynamicEntity implements Entity {

    private List<Integer> speed;  //vitesse de l'entité
    private List<Integer> position;  //coordonnées de l'image
    private List<Integer> dimensions;  //hauteur * largeur de l'image
    private String fileName;  //nom de l'image à afficher depuis le dossier courant (.jpg, .png)

    public DynamicEntity(List<Integer> speed, List<Integer> position, List<Integer> dimensions, String fileName) {
        this.speed = speed;
        this.position = position;
        this.dimensions = dimensions;
        this.fileName = fileName;
    }

    public DynamicEntity(){
        LinkedList<Integer> speed = new LinkedList<>();
        speed.add(0);
        speed.add(0);

        LinkedList<Integer> pos = new LinkedList<>();
        speed.add(100);
        speed.add(100);

        this.speed = speed;
        this.position = pos;
        this.dimensions = null;
        fileName = null;
    }

    /**
     * @return la vitesse de l'entité sur l'axe des abscisses
     */
    public Integer getSpeedX(){
        return this.speed.get(0);
    }

    /**
     * @return la vitesse de l'entité sur l'axe des ordonnées
     */
    public Integer getSpeedY(){
        return this.speed.get(1);
    }

    /**
     * @return la vitesse de l'entité sur les deux axes (x, y)
     */
    public List<Integer> getSpeed(){
        return this.speed;
    }

    /**
     * Changer la vitesse de l'entité sur l'axe des abscisses
     * @param speed vitesse de l'entité
     */
    public void setSpeedX(Integer speed){
        this.speed.set(0, speed);
    }

    /**
     * Changer la vitesse de l'entité sur l'axe des ordonnées
     * @param speed vitesse de l'entité
     */
    public void setSpeedY(Integer speed){
        this.speed.set(1, speed);
    }

    public List<Integer> getPosition(){
        return this.position;
    }

    public void setPosition(List<Integer> position){
        this.position = position;
    }

    public String getImage(){
        return this.fileName;
    }

    public void setImage(String fileName){
        this.fileName = fileName;
    }

    public List<Integer> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Integer> dimensions) {
        this.dimensions = dimensions;
    }

}