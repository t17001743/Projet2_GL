package Engine;

import java.util.LinkedList;
import java.util.List;

/**
 * Entités qui se déplacent (Pac-man, les fantômes, etc.)
 */
public abstract class DynamicEntity implements Entity {

    private List<Double> speed;  //vitesse de l'entité
    private List<Double> position;  //coordonnées de l'image
    private List<Double> dimensions;  //hauteur * largeur de l'image
    private String fileName;  //nom de l'image à afficher depuis le dossier courant (.jpg, .png)

    public DynamicEntity(List<Double> speed, List<Double> position, List<Double> dimensions, String fileName) {
        this.speed = speed;
        this.position = position;
        this.dimensions = dimensions;
        this.fileName = fileName;
    }

    public DynamicEntity(){
        LinkedList<Double> speed = new LinkedList<>();
        speed.add(0.0);
        speed.add(0.0);

        LinkedList<Double> pos = new LinkedList<>();
        speed.add(100.0);
        speed.add(100.0);

        this.speed = speed;
        this.position = pos;
        this.dimensions = null;
        fileName = null;
    }

    /**
     * @return la vitesse de l'entité sur l'axe des abscisses
     */
    public double getSpeedX(){
        return this.speed.get(0);
    }

    /**
     * @return la vitesse de l'entité sur l'axe des ordonnées
     */
    public double getSpeedY(){
        return this.speed.get(1);
    }

    /**
     * @return la vitesse de l'entité sur les deux axes (x, y)
     */
    public List<Double> getSpeed(){
        return this.speed;
    }

    /**
     * Changer la vitesse de l'entité sur l'axe des abscisses
     * @param speed vitesse de l'entité
     */
    public void setSpeedX(Double speed){
        this.speed.set(0, speed);
    }

    /**
     * Changer la vitesse de l'entité sur l'axe des ordonnées
     * @param speed vitesse de l'entité
     */
    public void setSpeedY(Double speed){
        this.speed.set(1, speed);
    }

    public List<Double> getPosition(){
        return this.position;
    }

    public void setPosition(List<Double> position){
        this.position = position;
    }

    public String getImage(){
        return this.fileName;
    }

    public void setImage(String fileName){
        this.fileName = fileName;
    }

    public List<Double> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<Double> dimensions) {
        this.dimensions = dimensions;
    }

}