package sample;
/**
 * @author Jacob Collins, Vincent Crespin
 * Switch class that will be used for every type of Switch
 */
public class Switches extends TrackObject
{

    //fields, there is no static id because there will be several switches
    private boolean blocked;
    private String TrackID;

    /**
     * Generic Switch constructor for each type of switch, calls to the super class in the constructor
     * @param blocked boolean that represents if the switch is on or off
     * @param TrackID String that represents the type of switch e.g. urs, drs, uls, dls
     */
    public Switches(boolean blocked, String TrackID)
    {
        super(TrackID, blocked);

    }
}

