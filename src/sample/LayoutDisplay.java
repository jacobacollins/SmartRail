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

  public void stationDisplay(int position, String ID)
  {
    gc.setFill(new Color(0,0,0, 1));
    gc.fillRect(position*30, 50, 25,25);
    gc.setFill(new Color(1,1,1,1));
    gc.fillText("A", position*30, 65);
  }
  public void trackDisplay(int position)
  {
    gc.setFill(new Color(0,0,0, 1));
    gc.fillRect(position*30, 65, 25,10);
  }

  public void lightDisplay(int position)
  {
    gc.setFill(new Color(0,0,0, 1));
    gc.fillRect(position*30, 65, 25,10);
    gc.fillRect(position*30, 50, 10, 25);
    gc.setFill(new Color(0, 1, 0, 1));
    gc.fillOval(position*30,50, 10,10);
  }

  public void drDisplay()
  {
    gc.setFill(new Color(1, 0, 0, 1));
    gc.fillRect(140, 65, 25, 10);
  }

  public void dlDisplay()
  {
    gc.setFill(new Color(0, 0, 1, 1));
    gc.fillRect(140, 65, 25, 10);
  }

  public void tracksDisplay(TrackObject[][] trackObjects)
  {
    int position = 2;
    for(int i = 0; i < trackObjects.length; i++)
    {
      for(int j = 0; j < trackObjects[i].length; j++)
      {
        if(trackObjects[i][j].getID().equals("track"))
        {
          trackDisplay(position);
          position++;
        }
        else if(trackObjects[i][j].getID().equals("light"))
        {
          lightDisplay(position);
          position++;
        }
        else
        {
          stationDisplay(position, trackObjects[i][j].getID());
          position++;
        }
      }
    }
  }
}
