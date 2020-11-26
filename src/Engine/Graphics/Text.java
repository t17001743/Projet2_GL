package Engine.Graphics;

/**
 * Permet de représenter un texte dans l'affichage graphique
 */
public class Text {
    private String text;
    private int positionX;
    private int positionY;

    public Text(String text, int positionX, int positionY) {
        this.text = text;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public String getText() {
        return text;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
