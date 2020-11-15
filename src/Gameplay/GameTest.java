package Gameplay;

import Engine.GraphicsEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Jeu de tests pour la classe Game
 */
class GameTest {

    @Test
    void init() {
        GraphicsEngine graphicsEngine = new GraphicsEngine();
        Game game = new Game(graphicsEngine);
        game.init();
        assertEquals(game.getEntities().get(0), game.getCharacter());
        double[] expectedPosition = new double[]{600.0, 400.0};
        double[] expectedSpeed = new double[]{0.0, 0.0};
        double[] expectedDimensions = new double[]{50.0, 50.0};
        String expectedFileName = "pacman.png";
        double[] actualPosition = game.getCharacter().getPosition().stream().mapToDouble(Double::doubleValue).toArray();
        double[] actualSpeed = game.getCharacter().getSpeed().stream().mapToDouble(Double::doubleValue).toArray();
        double[] actualDimensions = game.getCharacter().getDimensions().stream().mapToDouble(Double::doubleValue).toArray();
        String actualFileName = game.getCharacter().getImage();
        assertEquals(expectedFileName, actualFileName);
        assertArrayEquals(expectedPosition, actualPosition);
        assertArrayEquals(expectedDimensions, actualDimensions);
        assertArrayEquals(expectedSpeed, actualSpeed);
    }
}