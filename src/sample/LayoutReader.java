package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LayoutReader
{

  public TrackObject[][] getLanes() {
    return lanes;
  }

  private TrackObject[][] lanes;
  public LayoutReader() throws IOException
  {
    readingInLayout();
    printLayout();
    setNeighbors();
  }

  private void printLayout() {
    for (TrackObject[] t : lanes) {
      for (TrackObject r : t) {
        System.out.print(" " + r.getID() + " ");

      }
      System.out.println();
    }
  }
  public void readingInLayout() throws IOException
  {
    BufferedReader in = null;
    try
    {
      in = new BufferedReader (new FileReader("src/LayoutFile.txt"));
      String line;
      int i = 0;
      int j = 0;
      int r;
      int c;
      line = in.readLine();
      r = Integer.parseInt(line);
      line = in.readLine();
      c = Integer.parseInt(line);
      lanes = new TrackObject[r][c];
      while((line = in.readLine()) != null)
      {
        String[] input = line.split(" ");
        while(j < input.length)
        {
          if(input[j].equals("track"))
          {
            lanes[i][j] = new Track(false);
            j++;
          }
          else if(input[j].equals("light"))
          {
            lanes[i][j] = new Light(false);
            j++;
          }
          else if(input[j].equals("drs"))
          {
            lanes[i][j] = new Switches(true, "drs", false);
            j++;
          }
          else if(input[j].equals("dls"))
          {
            lanes[i][j] = new Switches(true, "dls", false);
            j++;
          }
          else if(input[j].equals("urs"))
          {
            lanes[i][j] = new Switches(true, "urs", false);
            j++;
          }
          else if(input[j].equals("uls"))
          {
            lanes[i][j] = new Switches(true, "uls", false);
            j++;
          }
          else
          {

            lanes[i][j] = new Station(false, input[j]);
            j++;
          }
        }
        j = 0;
        i++;
      }

    } finally {
      if(in != null) {
        in. close ();
      }
    }
  }

  private void setNeighbors() {
    for (int i = 0; i < lanes.length; i++) {
      for (int j = 0; j < lanes[i].length; j++) {

        lanes[i][j].setNeighbors(lanes, i, j);
      }
    }
  }
}
