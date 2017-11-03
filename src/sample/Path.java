package sample;
import java.util.ArrayList;

/**
 * Created by Vincent on 10/20/2017.
 */

public class Path
{

    private TrackObject currentTrack, firstTrack;
    private String destination;

    public Path(TrackObject currentTrack, String destination){
      this.currentTrack = currentTrack;
      this.destination = destination;
      firstTrack = currentTrack;

      buildPath(currentTrack, destination);
  }

  private ArrayList<TrackObject> path = new ArrayList<>();

  public void addToPath(TrackObject track)
  {
    path.add(track);
  }

  public void removePath(Track track)
  {
    path.clear();
  }

  private void buildPath(TrackObject currentTrack, String destination) {


      if (!currentTrack.getRightNeighbor().isVisited()) {
          if (currentTrack.getRightNeighbor().getID().equals("track") || currentTrack.getRightNeighbor().getID().equals("light")) {
              addToPath(currentTrack);
              System.out.println("added track or light");

              currentTrack = currentTrack.getRightNeighbor();
              buildPath(currentTrack, destination);
          } else if (currentTrack.getRightNeighbor().getID().equals("urs")
                  || currentTrack.getRightNeighbor().getID().equals("drs")
                  || currentTrack.getRightNeighbor().getID().equals("uls")
                  || currentTrack.getRightNeighbor().getID().equals("dls")) {

              switch (currentTrack.getRightNeighbor().getID()) {
                  case "urs":
                      break;
                  case "drs":
                      System.out.println("hello");

                      if (!currentTrack.getRightNeighbor().getBottomNeighbor().isVisited()) {
                          System.out.println("hello again");
                          addToPath(currentTrack);
                          addToPath(currentTrack.getRightNeighbor());
                          currentTrack = currentTrack.getRightNeighbor().getBottomNeighbor();
                          buildPath(currentTrack, destination);
                      } else {
                          System.out.println(currentTrack.getRightNeighbor().getBottomNeighbor().isVisited());

                          addToPath(currentTrack);
                          currentTrack = currentTrack.getRightNeighbor();
                          buildPath(currentTrack, destination);

                      }
                      break;
                  case "dls":
                      break;
                  case "uls":
                      break;

              }
          } else if (currentTrack.getRightNeighbor().getID().equals(destination)) {
              addToPath(currentTrack);
              addToPath(currentTrack.getRightNeighbor());
              return;
          } else if(!currentTrack.getRightNeighbor().getID().equals(destination)) {

          addToPath(currentTrack);
          addToPath(currentTrack.getRightNeighbor());
          currentTrack.setVisited(true);
//              path.clear();
//              buildPath(firstTrack, destination);
          return;
      }
      } else {
          currentTrack.setVisited(true);
          path.clear();
          buildPath(firstTrack, destination);
      }
  }



  public ArrayList<TrackObject> getPath()
  {
    return path;
  }
}
