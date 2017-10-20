import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Vincent on 10/20/2017.
 */
public class LTrack implements Tracks
{
  private Rectangle lTrack = new Rectangle();
  private Color trackColor;
  private String track;
  public LTrack(Color trackColor, String track)
  {
    this.track = track;
    this.trackColor = trackColor;
    lTrack.setX(25);
    lTrack.setY(25);
    lTrack.setFill(trackColor);
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
