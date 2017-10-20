import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Vincent on 10/20/2017.
 */
public class Station implements Tracks
{
  private Rectangle station = new Rectangle();
  private Color trackColor;
  private String track;
  public Station(Color trackColor, String track)
  {
    this.track = track;
    this.trackColor = trackColor;
    station.setX(25);
    station.setY(25);
    station.setFill(trackColor);
  }
  @Override
  public char getStartPoint() {
    return 0;
  }

  @Override
  public char getDest() {
    return 0;
  }

  @Override
  public void checkRight() {

  }

  @Override
  public void checkLeft() {

  }

  @Override
  public void setColor() {

  }

  @Override
  public void setString() {

  }
}
