package Engine.Entities;

import Engine.Entities.Entity;

import java.util.List;

/**
 * Entités qui ne bougent pas (par exemple : murs du labyrinthe)
 */
public abstract class StaticEntity implements Entity {

    private List<Integer> position;  //coordonnées de l'image
    private List<Integer> dimensions;  //hauteur * largeur de l'image
    private String fileName;  //nom de l'image à afficher depuis le dossier courant (.jpg, .png)

    public StaticEntity(List<Integer> position, List<Integer> dimensions, String fileName) {
        this.position = position;
        this.dimensions = dimensions;
        this.fileName = fileName;
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
