package Engine.Entities;

import java.util.List;

/**
 * Les entités du jeu (Pac-Man, les fantômes, fruits, murs du labyrinthe, etc.)
 */
public interface Entity {

    /**
     * @return la position de l'entité (une liste de coordonnées (x,y))
     */
    List<Integer> getPosition();

    /**
     * @return l'image représentant l'entité
     */
    String getImage();

    /**
     * @return les dimensions hauteur * largeur de l'image
     */
    public List<Integer> getDimensions();

    /**
     * Changer la position de l'entité
     * @param position les nouvelles coordonnées de l'entité
     */
    void setPosition(List<Integer> position);


    /**
     * Changer l'image représentant l'entité
     * @param fileName le nom du fichier contenant l'image (en .png, .jpg)
     */
    void setImage(String fileName);

    /**
     * Changer les dimensions de l'image
     * @param dimensions les dimensions hauteur * largeur de l'image
     */
    void setDimensions(List<Integer> dimensions);
}
