import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Vincent on 10/20/2017.
 */
public class STrack implements Tracks
{
  private Rectangle sTrack = new Rectangle();
  private Color trackColor;
  private String track;
  public STrack(Color trackColor, String track)
  {
    this.track = track;
    this.trackColor = trackColor;
    sTrack.setX(25);
    sTrack.setY(25);
    sTrack.setFill(trackColor);
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
