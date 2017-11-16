package sample;
/**
 * @author Jacob Collins, Vincent Crespin
 * The TrackLayout class build the back end structure of TrackObject that becomes our Track System.
 */
public class TrackLayout
{
    //Track Object array to be filled
    private TrackObject[][] lanes;

    /**
     * Constructor for Track Layout
     * @param lanes the array of Track Object
     */
    public TrackLayout(TrackObject[][] lanes)
    {

        this.lanes = lanes;
        multipleLanes(lanes);
        setNeighbors(lanes);

    }

    /**
     * utility function to print out the track layout
     */
    private void printLayout()
    {
        for (TrackObject[] t : lanes)
        {
            for (TrackObject r : t)
            {
                System.out.print(" " + r.getID() + " ");

            }
            System.out.println();
        }
    }

    /**
     * Method that makes multiple lane track object array
     * @param lanes track object array
     */
    public void multipleLanes(TrackObject[][] lanes)
    {

        //fills tracks
        for (int i = 0; i < lanes.length; i++)
        {
            for (int j = 0; j < lanes[i].length; j++)
            {
                Track tr = new Track(false);
                lanes[i][j] = tr;


            }
        }

            //fills stations
            for (int k = 0; k < lanes.length; k++)
            {

                for (int l = 0; l < lanes[k].length; l++)
                {
                    lanes[k][0] = new Station(false, String.valueOf((char) (k + 65)));
                    lanes[k][lanes[k].length - 1] = new Station(false, String.valueOf((char) (k + 85)));

                }

            }

            //makes switches
            lanes[0][5] = new Switches(true, "drs");
            lanes[1][2] = new Switches(true, "urs");
            lanes[1][7] = new Switches(true, "drs");
            lanes[2][4] = new Switches(true, "uls");

            //fills lights
            lanes[0][2] = new Light(false);
            lanes[1][4] = new Light(false);
            lanes[2][6] = new Light(false);

        }

    /**
     * Sets the neighbors of each of the Track Objects in the array
     * @param lanes Track Object array
     */
    private void setNeighbors(TrackObject[][] lanes)
    {
        //loops over array and sets the neighbors with the Track Object function
        for (int i = 0; i < lanes.length; i++)
        {
            for (int j = 0; j < lanes[i].length; j++)
            {

                lanes[i][j].setNeighbors(lanes, i, j);
            }
        }
    }

}
