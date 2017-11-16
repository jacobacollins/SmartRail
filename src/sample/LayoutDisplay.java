package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/**
 * Created by Vincent on 10/27/2017.
 */
public class LayoutDisplay
{
  private Image dLTrack = new Image("DLTrack2.png");
  private Image dRTrack = new Image("DRTrack.png");
  private Image track = new Image("Track.png");
  private Image lightOff = new Image("LightOff.png");
  private Image lightOn = new Image("LightOn.png");
  private Canvas canvas;
  private GraphicsContext gc;
  TrackObject[][] trackObjects;
  public LayoutDisplay(Canvas canvas)
  {
    this.canvas = canvas;
    gc = canvas.getGraphicsContext2D();
  }

  public void stationDisplay(int pX, int pY, String ID)
  {
    gc.setFill(new Color(0,0,0, 1));
    gc.fillRect(pX*55, pY*75, 50,50);
    gc.setFill(new Color(1,1,1,1));
    gc.setFont(new Font("Courier New", 25));
    gc.fillText(String.valueOf((ID)), pX*55+15, pY*75+30);
  }
  public void trackDisplay(int pX, int pY)
  {
    gc.drawImage(track, pX*55, pY*75+40, 50, 10);
    //gc.setFill(new Color(0,0,0, 1));
    //gc.fillRect(pX*55, pY*75+40, 50,10);
  }

  public void lightDisplay(int pX, int pY, boolean red)
  {
    //gc.setFill(new Color(0,0,0, 1));
    //gc.fillRect(pX*55, pY*75, 20, 50);
    //gc.setFill(new Color(0,0,0, 1));
    //gc.fillRect(pX*55, pY*75 + 40, 50,10);
    //gc.fillRect(pX*55, pY*75, 20, 50);
  if(red){
    //gc.setFill(new Color(1, 0, 0, 1));
    gc.drawImage(lightOff, pX*55-20, pY*75, 50, 50);
  }
  else if(!red){
    //gc.setFill(new Color(0, 1, 0, 1));
    gc.drawImage(lightOn, pX*55-20, pY*75, 50, 50);
  }
    gc.drawImage(track, pX*55, pY*75+40, 50, 10);
    //gc.fillOval(pX*55,pY*75, 20,20);
  }

  public void drDisplay(int pX, int pY)
  {
    gc.setFill(new Color(1, 0, 0, 1));
    gc.fillRect(pX*55, pY*75 + 40, 50,10);
    gc.drawImage(track, pX*55, pY*75+40, 50, 10);
    gc.drawImage(dRTrack, pX*55+20, pY*75+60, 50, 50);
    //gc.rotate(45);
    //gc.fillRect(pX*55, -(pY*35 + 175), 75,10);
    //gc.rotate(-45);
  }

  public void dlDisplay(int pX, int pY)
  {
    gc.setFill(new Color(0, 0, 1, 1));
    gc.fillRect(pX*55, pY*75 + 40, 50,10);
    gc.drawImage(track, pX*55, pY*75+40, 50, 10);
    gc.drawImage(dLTrack, pX*55-45, pY*75+50, 50, 50);
  }

  public void urDisplay(int pX, int pY)
  {
    gc.setFill(new Color(0, 1, 0, 1));
    gc.fillRect(pX*55, pY*75 + 40, 50,10);
    gc.drawImage(track, pX*55, pY*75+40, 50, 10);
    gc.drawImage(dLTrack, pX*55+20, pY*75-20, 50, 50);
  }

  public void ulDisplay(int pX, int pY)
  {
    gc.setFill(new Color(1, 0, 1, 1));
    gc.fillRect(pX*55, pY*75 + 40, 50,10);
    gc.drawImage(track, pX*55, pY*75+40, 50, 10);
    gc.drawImage(dRTrack, pX*55-15, pY*75-20, 50, 50);
  }

  public void tracksDisplay(TrackObject[][] trackObjects)
  {
    int pX = 0;
    int pY = 0;
    for(int i = 0; i < trackObjects.length; i++)
    {
      for(int j = 0; j < trackObjects[i].length; j++)
      {
        if(trackObjects[i][j].getID().equals("track"))
        {
          trackDisplay(pX, pY);
          pX++;
        }
        else if(trackObjects[i][j].getID().equals("light"))
        {
          if (trackObjects[i][j].isOccupied()) {
            lightDisplay(pX, pY, true);
            pX++;
          } else if (!trackObjects[i][j].isOccupied()) {
            lightDisplay(pX, pY, false);
            pX++;
          }
        }
        else if(trackObjects[i][j].getID().equals("drs"))
        {
          drDisplay(pX, pY);
          pX++;
        }
        else if(trackObjects[i][j].getID().equals("dls"))
        {
          dlDisplay(pX, pY);
          pX++;
        }
        else if(trackObjects[i][j].getID().equals("urs"))
        {
          urDisplay(pX, pY);
          pX++;
        }
        else if(trackObjects[i][j].getID().equals("uls"))
        {
          ulDisplay(pX, pY);
          pX++;
        }
        else
        {
          stationDisplay(pX, pY, trackObjects[i][j].getID());
          pX++;
        }
      }
      pY++;
      pX = 0;
    }
  }
}