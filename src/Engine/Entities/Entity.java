package Engine.Entities;

import java.util.List;

/**
 * Les entités du jeu (Pac-Man, les fantômes, fruits, murs du labyrinthe, etc.)
 */
public interface Entity {

    /**
     * @return la position de l'entité (une liste de coordonnées (x,y))
     */
    public List<Double> getPosition();

    /**
     * Changer la position de l'entité
     * @param position les nouvelles coordonnées de l'entité
     */
    public void setPosition(List<Double> position);

    /**
     * @return l'image représentant l'entité
     */
    public String getImage();

    /**
     * Changer l'image représentant l'entité
     * @param fileName le nom du fichier contenant l'image (en .png, .jpg)
     */
    public void setImage(String fileName);

    /**
     * @return les dimensions hauteur * largeur de l'image
     */
    public List<Double> getDimensions();

    /**
     * Changer les dimensions de l'image
     * @param dimensions les dimensions hauteur * largeur de l'image
     */
    public void setDimensions(List<Double> dimensions);
}
