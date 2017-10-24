public class TrackObject {

    private String ID;
    private boolean isOccupied;


public TrackObject(String ID, boolean isOccupied){

    this.ID = ID;
    this.isOccupied = isOccupied;

}

public String getID(){
    return ID;
}

public boolean getisOccupied() {
        return isOccupied;
    }

    public void setisOccupied(boolean isOccupied){
    this.isOccupied = isOccupied;
    }




}
