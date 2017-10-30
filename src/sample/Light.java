package sample;
public class Light extends TrackObject {
    private boolean on;
    private static final String LightID = "light";

    public Light(boolean red){
        super(LightID, red);
        this.on = red;

    }
}
