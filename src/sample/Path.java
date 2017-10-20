import java.util.List;

/**
 * Created by Vincent on 10/20/2017.
 */
public class Path
{
  private List<Tracks> path = new LinkedList<>();

  public void addToPath(Tracks track)
  {
    path.add(track);
  }

  public void removePath(Tracks track)
  {
    path.remove(track);
  }

  public List<Tracks> getPath()
  {
    return path;
  }
}
