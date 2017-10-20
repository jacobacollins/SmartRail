import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Vincent on 10/20/2017.
 */
public class RTrack implements Tracks
{
  private Rectangle rTrack = new Rectangle();
  private Color trackColor;
  private String track;
  public RTrack(Color trackColor, String track)
  {
    this.track = track;
    this.trackColor = trackColor;
    rTrack.setX(25);
    rTrack.setY(25);
    rTrack.setFill(trackColor);
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
