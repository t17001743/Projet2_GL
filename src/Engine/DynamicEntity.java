package Engine;

import java.util.List;

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

    public double getSpeedX(){
        return this.speed.get(0);
    }

    public double getSpeedY(){
        return this.speed.get(1);
    }

    public List<Double> getSpeed(){
        return this.speed;
    }

    public void setSpeedX(Double speed){
        this.speed.set(0, speed);
    }

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
