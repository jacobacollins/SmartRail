import java.util.ArrayList;

public class Track extends TrackObject {

    private boolean blocked;
    private static final String TrackID = "track";
    private TrackObject leftNeighbor, rightNeighbor;

public Track(boolean blocked){

    super(TrackID, blocked);



}




    public void blockNeighbors(Object track, ArrayList trackLayout, int position) {

    }

    public String getTrackID(){
    return TrackID;
    }

//    public void setNeighbors(Track[][] tracklayout, int row, int col) {
//        if(tracklayout[row][col] != null ){
//            rightNeighbor = neighbors.get(position + 1);
//        }
//
//        if(neighbors.get(position - 1) != null){
//            leftNeighbor = neighbors.get(position -1);
//        }
//        System.out.println("null in get Neighbors");
//
//    }






}
