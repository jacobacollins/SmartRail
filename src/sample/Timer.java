package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * @author Jacob Collins, Vincent Crespin
 * chages the light color
 */
public class Timer
{
  TrackObject[][] trackObjects;
  boolean moving;

  /**
   * constructor
   * @param trackObjects 2d array of track objects
   */
  public Timer(TrackObject[][] trackObjects)
  {
    this.trackObjects = trackObjects;
    moving = true;
  }

  /**
   * changes the light from read to green and green to red
   * @param trackObjects 2d array of track objects.
   * @return if the light is red or green by boolean value
   */
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
