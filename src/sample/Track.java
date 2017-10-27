package sample;
import java.util.ArrayList;

public class Track extends TrackObject {

    private boolean blocked;
    private static final String TrackID = "track";
    private TrackObject leftNeighbor, rightNeighbor;

public Track(boolean blocked){

    super(TrackID, blocked);
    this.blocked = blocked;



}









}
