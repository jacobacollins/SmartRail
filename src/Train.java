import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Vincent on 10/15/2017.
 */
public class Train
{
  private Rectangle train = new Rectangle();
  private int position;
  private Color trainColor;
  private boolean moving = false;
  public Train(int position, Color traincolor)
  {
    this.trainColor = traincolor;
    train.setX(25);
    train.setY(25);
    train.setFill(traincolor);
    this.position = position;
  }

  public void moveRight()
  {
    position++;
  }

  public void moveLeft()
  {
    position--;
  }

  public void setMoving(boolean moving)
  {
    this.moving = moving;
  }

  public boolean isMoving() {
    return moving;
  }


}
