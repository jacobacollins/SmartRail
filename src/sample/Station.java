package sample;
/**
 * @author Jacob Collins, Vincent Crespin
 * Station class that will be used for every end of the paths.
 */
public class Station extends TrackObject
{
    //fields
    private String StationID;
    private boolean isBlocked;

    /**
     * Constructor for Station that calls the super constructor
     * @param isBlocked boolean to see if a train is at the station
     * @param StationID The String for the Station
     */
    public Station(boolean isBlocked, String StationID)
    {
        super(StationID, isBlocked);

    }
}
