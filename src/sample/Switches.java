package sample;
public class Switches extends TrackObject {
    public boolean isOn() {
        return on;
    }

    private boolean blocked, on;
    private String TrackID;

    public Switches(boolean blocked, String TrackID,boolean on) {
        super(TrackID, blocked);

        this.TrackID = TrackID;
        this.blocked = blocked;
        this.on = on;
    }
}

