package sample;
public class Station extends TrackObject {

    private String StationID;
    private boolean isBlocked;

    public Station(boolean isBlocked, String StationID){
        super(StationID, isBlocked);
    }
}
