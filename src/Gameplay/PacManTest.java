package Gameplay;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static org.junit.jupiter.api.Assertions.*;

class PacManTest {

    PacMan pacMan;

    @BeforeEach
    void init(){
        List<Double> position = new ArrayList<>();
        position.add(0.0);
        position.add(1.0);
        List<Double> speed = new ArrayList<>();
        speed.add(6.0);
        speed.add(-6.0);
        List<Double> dimensions = new ArrayList<>();
        dimensions.add(40.0);
        dimensions.add(60.0);
        this.pacMan = new PacMan(speed, position, dimensions, "trucmuche.png");
    }

    @Test
    void getSpeedX() {
        assertEquals(6.0, this.pacMan.getSpeedX());
    }

    @Test
    void getSpeedY() {
        assertEquals(-6.0, this.pacMan.getSpeedY());
    }

    @Test
    void setSpeedX() {
        this.pacMan.setSpeedX(4.0);
        assertEquals(4.0, this.pacMan.getSpeedX());
    }

    @Test
    void setSpeedY() {
        this.pacMan.setSpeedY(-8.0);
        assertEquals(-8.0, this.pacMan.getSpeedY());
    }

    @Test
    void getPosition(){
        double[] actual = this.pacMan.getPosition().stream().mapToDouble(Double::doubleValue).toArray();
        double[] expected = new double[]{0.0, 1.0};
        assertArrayEquals(expected, actual);
    }

    @Test
    void setPosition() {
        double[] newPos = new double[]{3.0, 7.0};
        List<Double> newPosition = DoubleStream.of(newPos).boxed().collect(Collectors.toList());
        this.pacMan.setPosition(newPosition);
        double[] position = this.pacMan.getPosition().stream().mapToDouble(Double::doubleValue).toArray();
        assertArrayEquals(newPos, position);
    }

    @Test
    void getImage() {
        String expected = "trucmuche.png";
        assertEquals(expected, this.pacMan.getImage());
    }

    @Test
    void setImage(){
        String newName = "trucmuche2electricboogaloo.png";
        this.pacMan.setImage(newName);
        assertEquals(newName, this.pacMan.getImage());
    }

    @Test
    void getDimensions() {
        double[] actual = this.pacMan.getDimensions().stream().mapToDouble(Double::doubleValue).toArray();
        double[] expected = new double[]{40.0, 60.0};
        assertArrayEquals(expected, actual);
    }

    @Test
    void setDimensions() {
        double[] newDim = new double[]{3.0, 7.0};
        List<Double> newDimensions = DoubleStream.of(newDim).boxed().collect(Collectors.toList());
        this.pacMan.setDimensions(newDimensions);
        double[] dimensions = this.pacMan.getDimensions().stream().mapToDouble(Double::doubleValue).toArray();
        assertArrayEquals(newDim, dimensions);
    }
}