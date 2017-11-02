package sample;
public class TrackObject {

    private String ID;
    private boolean isOccupied, visited;
    private TrackObject leftNeighbor, rightNeighbor, bottomNeighbor, topNeighbor;


    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public TrackObject(String ID, boolean isOccupied) {

        this.ID = ID;
        this.isOccupied = isOccupied;
        this.visited = false;

    }

    public boolean isOccupied() {
        return isOccupied;
    }


    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public String getID() {
        return ID;
    }


    public void blockNeighbors(TrackObject[][] layout, int row, int col) {

    }

    public TrackObject getLeftNeighbor() {
        return leftNeighbor;
    }

    public TrackObject getRightNeighbor() {
        return rightNeighbor;
    }

    public TrackObject getTopNeighbor() {
        return topNeighbor;
    }

    public TrackObject getBottomNeighbor() {
        return bottomNeighbor;
    }

    public void setNeighbors(TrackObject[][] layout, int row, int col) {
        if (layout[row][col].getID() != null && row < layout.length && row >= 0 && col < layout[row].length && col >= 0
                ) {
            if (col < layout[row].length - 1 && layout[row][col + 1].getID() != null) {


                rightNeighbor = layout[row][col + 1];
            }
            if (col > 0 && layout[row][col - 1].getID() != null) {


                leftNeighbor = layout[row][col - 1];
            }
            if (layout[row][col].getID().equals("drs") && layout[row + 1][col + 1] != null) {

                bottomNeighbor = layout[row + 1][col + 1];
            }
            if (layout[row][col].getID().equals("urs") && layout[row - 1][col + 1] != null) {

                topNeighbor = layout[row - 1][col + 1];
            }
            if (layout[row][col].getID().equals("uls") && layout[row - 1][col - 1] != null) {

                topNeighbor = layout[row - 1][col - 1];
            }


        }
    }

}
