
/**
 * @author Jacob Collins, Vincent Crespin
 * This TrackObject is what all of the components are derived from.
 */

public class TrackObject {

    //this will be the id of the component such as light, track, etc.
    private String ID;
    //isOccupied is for reserving routes and visited replaces a boolean copy of the structure for DFS.
    private boolean isOccupied, visited;
    //appropriate neighbors for each TrackObject
    private TrackObject leftNeighbor, rightNeighbor, bottomNeighbor, topNeighbor;


    /**
     *
     * @param ID String representing the specific type of track object e.g. track, light, station, etc
     * @param isOccupied The boolean representing if the track is reserved for a route or not.
     */
    public TrackObject(String ID, boolean isOccupied)
    {

        this.ID = ID;
        this.isOccupied = isOccupied;
        this.visited = false;

    }

    /**
     * This method sets the function for each of the tracks in the structure based on their position in an array
     * @param layout the array holding all of the TrackObjects
     * @param row row position in array
     * @param col column position in array
     */
    public void setNeighbors(TrackObject[][] layout, int row, int col)
    {

        //makes sure we don't hit a null position
        if (layout[row][col].getID() != null && row < layout.length && row >= 0 && col < layout[row].length && col >= 0)
        {
            //sets right neighbor
            if (col < layout[row].length - 1 && layout[row][col + 1].getID() != null)
            {
                rightNeighbor = layout[row][col + 1];
            }
            //sets left neighbor
            if (col > 0 && layout[row][col - 1].getID() != null)
            {
                leftNeighbor = layout[row][col - 1];
            }
            //sets bottom neighbors
            if (layout[row][col].getID().equals("drs") && layout[row + 1][col + 1] != null)
            {
                bottomNeighbor = layout[row + 1][col + 1];
            }
            //sets top neighbor
            if (layout[row][col].getID().equals("urs") && layout[row - 1][col + 1] != null)
            {
                topNeighbor = layout[row - 1][col + 1];
            }
            //sets top neighbor specific to another switch
            if (layout[row][col].getID().equals("uls") && layout[row - 1][col - 1] != null)
            {
                topNeighbor = layout[row - 1][col - 1];
            }


        }
    }

    /**
     * Getter for left neighbor
     * @return returns left neighbor
     */
    public TrackObject getLeftNeighbor() {
        return leftNeighbor;
    }

    /**
     * Getter for right neighbor
     * @return right neighbor track object
     */
    public TrackObject getRightNeighbor() {
        return rightNeighbor;
    }

    /**
     * getter for top neighbor
     * @return top neighbor track object
     */
    public TrackObject getTopNeighbor() {
        return topNeighbor;
    }

    /**
     * getter for bottom neighbor track object
     * @return bottom neighbor track object
     */
    public TrackObject getBottomNeighbor() {
        return bottomNeighbor;
    }

    /**
     * getter for track object id
     * @return string id for track object
     */
    public String getID() {
        return ID;
    }

    /**
     * Getter for returning boolean to see if Track Object is occupied
     * @return boolean value based on isOccupied field of Track Object
     */
    public boolean isOccupied()
    {
        return isOccupied;
    }

    /**
     * getter for visited boolean of track object which is used for DFS
     * @return visited boolean
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * Setter for occupied boolean
     * @param occupied boolean value for occupied field of Track Object
     */
    public void setOccupied(boolean occupied)
    {
        this.isOccupied = occupied;
    }

    /**
     * setter for visited boolean, useful for DFS
     * @param visited boolean value to set visited field
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }


}
