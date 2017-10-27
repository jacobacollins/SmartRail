package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;

/**
 * Created by Vincent on 10/27/2017.
 */
public class LayoutDisplay
{
  private Canvas canvas;
  private GraphicsContext gc;
  Rectangle rectangle = new Rectangle();
  public LayoutDisplay(Canvas canvas)
  {
    this.canvas = canvas;
    gc = canvas.getGraphicsContext2D();
  }

  public void stationDisplay()
  {
    gc.setFill(new Color(0,0,0, 1));
    gc.fillRect(50, 50, 25,25);
    gc.setFill(new Color(1,1,1,1));
    gc.fillText("A", 60, 70);
  }
  public void trackDisplay()
  {
    gc.setFill(new Color(0,0,0, 1));
    gc.fillRect(80, 65, 25,10);
  }

  public void lightDisplay()
  {
    gc.setFill(new Color(0,0,0, 1));
    gc.fillRect(110, 65, 25,10);
    gc.fillRect(120, 50, 10, 25);
    gc.setFill(new Color(0, 1, 0, 1));
    gc.fillOval(120,50, 10,10);
  }

  public void switchDisplay()
  {
    gc.setFill(new Color(1, 0, 0, 1));
    gc.fillRect(140, 65, 25, 10);
  }
}
