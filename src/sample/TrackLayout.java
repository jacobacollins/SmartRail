
public class TrackLayout {

private TrackObject[][] lanes;
    public TrackLayout(TrackObject[][] lanes) {

        this.lanes = lanes;
        multipleLanes(lanes);
        //singleLane(lanes);
        printLayout();
        setNeighbors(lanes);

    }

    private void printLayout() {
        for (TrackObject[] t : lanes) {
            for (TrackObject r: t) {
                System.out.print(" " + r.getID() + " ");

            }
            System.out.println();
        }
    }


    public void multipleLanes(TrackObject[][] lanes) {
        for (int i = 0; i < lanes.length; i++) {
            for (int j = 0; j < lanes[i].length; j++) {
                Track tr = new Track(false, "track");
                lanes[i][j] = tr;


            }



            for (int k = 0; k < lanes.length; k++) {

                for (int l = 0; l < lanes[k].length; l++) {
                    lanes[k][0] = new Track(false, String.valueOf((char) (k + 65)));
                    lanes[k][lanes[k].length - 1] = new Track(false, String.valueOf((char) (k + 85)));

                }

            }

            lanes[0][5] = new Switches(false, "drs", false);
            lanes[1][2] = new Switches(false, "urs", false);
            lanes[1][7] = new Switches(false, "drs", false);
            lanes[2][4] = new Switches(false, "uls", false);


        }
    }
    private void setNeighbors(TrackObject[][] lanes){
        for(int i = 0; i < lanes.length; i++){
            for(int j = 0; j < lanes[i].length; j++){

                lanes[i][j].setNeighbors(lanes, i, j);
            }
        }
    }

    public void singleLane(TrackObject[][] lanes) {
        for (int i = 0; i < lanes.length; i++) {
            for (int j = 0; j < lanes[i].length; j++) {
                lanes[i][j] = new Track(false, null);
            }

        }
        lanes[0][0] = new Track(false, String.valueOf((char) (65)));
        lanes[0][lanes.length] = new Track(false, String.valueOf((char) (66)));

    }

        public static void main(String[] args){
        TrackObject[][] layout = new TrackObject[3][9];

        TrackLayout tl = new TrackLayout(layout);

        }
}
