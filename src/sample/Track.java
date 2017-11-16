package sample;
/**
 * @author Jacob Collins, Vincent Crespin
 * Track class that will be used for every generic track
 */

public class Track extends TrackObject
{

    //boolean variable to see if the track is blocked
    private boolean blocked;
    //final track id
    private static final String TrackID = "track";

    /**
     * Track Constructor that calls the super class
     * @param blocked boolean value for track.
     */
    public Track(boolean blocked)
    {

    //call to the super class
        super(TrackID, blocked);

    }

}
