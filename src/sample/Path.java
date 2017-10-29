package sample;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Vincent on 10/20/2017.
 */
public class Path
{
  private List<Track> path = new LinkedList<>();

  public void addToPath(Track track)
  {
    path.add(track);
  }

  public void removePath(Track track)
  {
    path.remove(track);
  }

  public List<Track> getPath()
  {
    return path;
  }
}
