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
    gc.fillRect(position*55, 150, 50,50);
    gc.setFill(new Color(1,1,1,1));
    gc.fillText("A", position*55, 165);
  }
  public void trackDisplay(int position)
  {
    gc.setFill(new Color(0,0,0, 1));
    gc.fillRect(position*55, 190, 50,10);
  }

  public void lightDisplay(int position)
  {
    gc.setFill(new Color(0,0,0, 1));
    gc.fillRect(position*55, 190, 50,10);
    gc.fillRect(position*55, 140, 20, 50);
    gc.setFill(new Color(0, 1, 0, 1));
    gc.fillOval(position*55,140, 20,20);
  }

  public void drDisplay()
  {
    gc.setFill(new Color(1, 0, 0, 1));
    gc.fillRect(140, 165, 25, 10);
  }

  public void dlDisplay()
  {
    gc.setFill(new Color(0, 0, 1, 1));
    gc.fillRect(140, 165, 25, 10);
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
