package Engine;

import java.util.List;

public interface Entity {

    public List<Double> getPosition();

    public void setPosition(List<Double> position);

    public String getImage();

    public void setImage(String fileName);
}
