package Engine;

import java.util.List;

public abstract class DynamicEntity implements Entity {

    private List<Double> speed;
    private List<Double> position;
    private String fileName;

    public Double getSpeedX(){
        return this.speed.get(0);
    }

    public Double getSpeedY(){
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

}
