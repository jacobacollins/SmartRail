package sample;
public class Switches extends TrackObject {

    private boolean blocked, on;
    private String TrackID;

    public Switches(boolean blocked, String TrackID, boolean on) {
        super(TrackID, blocked);
        this.blocked = blocked;
        this.TrackID = TrackID;
        this.on = on;
    }
}

