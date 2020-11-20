package Engine.Entities;

import Engine.Entities.Entity;

import java.util.List;

/**
 * Entités qui ne bougent pas (par exemple : murs du labyrinthe)
 */
public abstract class StaticEntity implements Entity {

    private List<Double> position;  //coordonnées de l'image
    private List<Double> dimensions;  //hauteur * largeur de l'image
    private String fileName;  //nom de l'image à afficher depuis le dossier courant (.jpg, .png)

    public StaticEntity(List<Double> position, List<Double> dimensions, String fileName) {
        this.position = position;
        this.dimensions = dimensions;
        this.fileName = fileName;
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
