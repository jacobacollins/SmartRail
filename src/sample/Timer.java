package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Created by Vincent on 11/16/2017.
 */
public class Timer
{
  Timeline timeline;
  TrackObject[][] trackObjects;
  boolean moving;

  public Timer(TrackObject[][] trackObjects)
  {
    this.trackObjects = trackObjects;
    moving = true;
  }
  public boolean lightTimer(TrackObject[][] trackObjects)
  {
    timeline = new Timeline(new KeyFrame(
            Duration.seconds(10),
            ae -> changeLight(trackObjects)));
    timeline.play();

    return moving;
  }

  public boolean changeLight(TrackObject[][] trackObjects)
  {
    for(int i = 0; i < trackObjects.length; i++)
    {
      for(int j = 0; j < trackObjects[i].length; j++)
      {
        if(trackObjects[i][j].getID().equals("light"))
        {
          trackObjects[i][j].setOccupied(!(trackObjects[i][j].isOccupied()));
          moving = !trackObjects[i][j].isOccupied();
        }
      }
    }
    return moving;
  }
}
