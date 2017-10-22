
public class TrackLayout {



    public TrackLayout(Track[][] lanes){
        multipleLanes(lanes);
        //singleLane(lanes);

        }



    }

    public void multipleLanes(Track[][] lanes){
        for(int i = 0; i < lanes.length; i++){
            for(int j = 0; j < lanes[i].length; j++){
                lanes[i][j] = new Track(false, null);
            }


for(int k = 0; k < lanes.length; k++) {

    for (int l = 0; l < lanes[k].length; l++) {
        lanes[k][0] = new Track(false, String.valueOf((char) (k + 65)));
        lanes[k][lanes[k].length - 1] = new Track(false, String.valueOf((char) (k + 85)));

    }

}


        }
    }

    public void singleLane(Track[][] lanes){
        for(int i = 0; i < lanes.length; i++) {
            for (int j = 0; j < lanes[i].length; j++) {
                lanes[i][j] = new Track(false, null);
            }

        }
        lanes[0][0] = new Track(false, String.valueOf((char)(65)));
        lanes[0][lanes.length] = new Track(false, String.valueOf((char)(66)));

        }



}
