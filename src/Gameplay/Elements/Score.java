package Gameplay.Elements;

import Engine.Graphics.Elements.Text;

/**
 * Classe utilisé pour le score
 * Elle étend la classe text pour l'affichage textuel
 */
public class Score extends Text {

    int score;

    public Score(int score, int positionX, int positionY) {
        super("Score = " + score, positionX, positionY);
    }

    public void setScore(int score) {
        this.score = score;
        this.setText("Score = " + score);
    }

    public int getScore() {
        return score;
    }
}
