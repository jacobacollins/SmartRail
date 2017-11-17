package sample;
/**
 * @author Jacob Collins, Vincent Crespin
 * This class builds the Path that the train will follow along its route
 */

import java.util.ArrayList;

public class Path {

    private TrackObject firstTrack;
    private String destination;
    private int counter = 0;
    private ArrayList<TrackObject> path = new ArrayList<>();

    /**
     * Default Constructor which takes the track to start from, the destination to go to, and the direction of movement
     * @param currentTrack Track to start the route on
     * @param destination Where the train is going
     * @param direction the direction 1, -1 the train is moving
     */
    public Path(TrackObject currentTrack, String destination, int direction) {
        TrackObject currentTrack1 = currentTrack;
        this.destination = destination;
        firstTrack = currentTrack;

        //if moving right
        if (direction == 1)
        {
            buildRightPath(currentTrack, destination);
        }
        else
        {
            //if moving left
            buildLeftPath(currentTrack, destination);
        }

    }


    /**
     * Builds the Right Path if a train resides at a left station
     * @param currentTrack The track to start building the path from
     * @param destination Where the train is going
     */
    private void buildRightPath(TrackObject currentTrack, String destination)
    {
        //checks boolean value of track
        if (!currentTrack.getRightNeighbor().isVisited())
        {
            //adds lights and tracks
            if (currentTrack.getRightNeighbor().getID().equals("track") || currentTrack.getRightNeighbor().getID().equals("light"))
            {
                addToPath(currentTrack);
                currentTrack = currentTrack.getRightNeighbor();
                buildRightPath(currentTrack, destination);
            }
            //adds switches
            else if (currentTrack.getRightNeighbor().getID().equals("urs")
                    || currentTrack.getRightNeighbor().getID().equals("drs")
                    || currentTrack.getRightNeighbor().getID().equals("uls")
                    || currentTrack.getRightNeighbor().getID().equals("dls"))
            {

                //urs case
                switch (currentTrack.getRightNeighbor().getID())
                {
                    case "urs":

                        if (!currentTrack.getRightNeighbor().getTopNeighbor().isVisited())
                        {
                            addToPath(currentTrack);
                            addToPath(currentTrack.getRightNeighbor());
                            currentTrack = currentTrack.getRightNeighbor().getTopNeighbor();
                            buildRightPath(currentTrack, destination);
                        }
                        else
                            {
                            addToPath(currentTrack);
                            currentTrack = currentTrack.getRightNeighbor();
                            buildRightPath(currentTrack, destination);
                        }

                        break;
                        //drs case
                    case "drs":
                        if (!currentTrack.getRightNeighbor().getBottomNeighbor().isVisited())
                        {
                            addToPath(currentTrack);
                            addToPath(currentTrack.getRightNeighbor());
                            currentTrack = currentTrack.getRightNeighbor().getBottomNeighbor();
                            buildRightPath(currentTrack, destination);
                        } else {
//
                            addToPath(currentTrack);
                            currentTrack = currentTrack.getRightNeighbor();
                            buildRightPath(currentTrack, destination);

                        }
                        break;
                        //dls case
                    case "dls":
                        addToPath(currentTrack);
                        currentTrack = currentTrack.getRightNeighbor();
                        buildRightPath(currentTrack, destination);
                        break;
                    case "uls":
                        //uls case
                        addToPath(currentTrack);
                        currentTrack = currentTrack.getRightNeighbor();
                        buildRightPath(currentTrack, destination);
                        break;

                }

            }
            //if the path reaches the destination then we have made it and we return from the function
            else if (currentTrack.getRightNeighbor().getID().equals(destination))
            {
                addToPath(currentTrack);
                addToPath(currentTrack.getRightNeighbor());
                return;
            }
            //if the end of the path doesn't equal the destination we will set the visited boolean to true and DFS
            else if (!currentTrack.getRightNeighbor().getID().equals(destination))
            {
                addToPath(currentTrack);
                addToPath(currentTrack.getRightNeighbor());
                currentTrack.setVisited(true);
                path.clear();
                buildRightPath(firstTrack, destination);
                return;
            }
        }
        else
        {
            //this counter is to make sure we aren't infinitely running the function
            if (counter > 50) {
                counter = 0;
                //clears the booleans so we aren't locked up when we try to enter another destination
                clearRightTracks(currentTrack);
                return;

            }
            currentTrack.setVisited(true);
            path.clear();
            counter++;
            buildRightPath(firstTrack, destination);
        }
    }

    /**
     * This function builds the Left Track from a right oriented train
     * @param currentTrack The track to start the search from
     * @param destination The place we would like the train to end up
     */
    private void buildLeftPath(TrackObject currentTrack, String destination) {

        //so that we aren't iterating over the same track twice
        if (!currentTrack.getLeftNeighbor().isVisited())
        {
            //adds tracks and lights
            if (currentTrack.getLeftNeighbor().getID().equals("track") || currentTrack.getLeftNeighbor().getID().equals("light"))
            {
                addToPath(currentTrack);
                currentTrack = currentTrack.getLeftNeighbor();
                buildLeftPath(currentTrack, destination);
            }
            //adds switches
            else if (currentTrack.getLeftNeighbor().getID().equals("urs")
                    || currentTrack.getLeftNeighbor().getID().equals("drs")
                    || currentTrack.getLeftNeighbor().getID().equals("uls")
                    || currentTrack.getLeftNeighbor().getID().equals("dls"))
            {
                //based on the track id
                switch (currentTrack.getLeftNeighbor().getID())
                {
                    //urs case
                    case "urs":
                        addToPath(currentTrack);
                        currentTrack = currentTrack.getLeftNeighbor();
                        buildLeftPath(currentTrack, destination);
                        break;
                    //drs case
                    case "drs":
                        addToPath(currentTrack);
                        currentTrack = currentTrack.getLeftNeighbor();
                        buildLeftPath(currentTrack, destination);
                        break;
                    //dls case
                    case "dls":
                        if (!currentTrack.getLeftNeighbor().getBottomNeighbor().isVisited()) {
                            addToPath(currentTrack);
                            addToPath(currentTrack.getLeftNeighbor());
                            currentTrack = currentTrack.getLeftNeighbor().getBottomNeighbor();
                            buildLeftPath(currentTrack, destination);
                        } else {
                            addToPath(currentTrack);
                            currentTrack = currentTrack.getLeftNeighbor();
                            buildLeftPath(currentTrack, destination);

                        }
                        break;
                    //uls case
                    case "uls":
                        if (!currentTrack.getLeftNeighbor().getTopNeighbor().isVisited())
                        {
                            addToPath(currentTrack);
                            addToPath(currentTrack.getLeftNeighbor());
                            currentTrack = currentTrack.getLeftNeighbor().getTopNeighbor();
                            buildLeftPath(currentTrack, destination);

                        } else {
                            addToPath(currentTrack);
                            addToPath(currentTrack.getLeftNeighbor());
                            currentTrack = currentTrack.getLeftNeighbor();
                            buildLeftPath(currentTrack, destination);


                        }
                        break;

                }
            }
            //if the end of the path equals the destination we return the path
            else if (currentTrack.getLeftNeighbor().getID().equals(destination))
            {
                addToPath(currentTrack);
                addToPath(currentTrack.getLeftNeighbor());
                return;
            }
            //if the end of the path doesn't equal the destination then we return set visited the track and start DFS
            else if (!currentTrack.getLeftNeighbor().getID().equals(destination))
            {
                addToPath(currentTrack);
                addToPath(currentTrack.getLeftNeighbor());
                currentTrack.setVisited(true);
                path.clear();
                buildLeftPath(firstTrack, destination);
                return;
            }
        }
        else
        {
            //the counter is so we don't run the function infinitely
            if (counter > 50) {
                counter = 0;
                clearLeftTracks(currentTrack);
                path.clear();
                return;

            }

            currentTrack.setVisited(true);
            path.clear();
            counter++;
            buildLeftPath(firstTrack, destination);

        }

    }

    /**
     * Clears the tracks moving in a left direction so that a DFS is possible
     * @param currentTrack The track to start clearing from, which is where the train resides
     */
    private void clearLeftTracks(TrackObject currentTrack)
    {
        while (!currentTrack.getLeftNeighbor().getID().equals(null))
        {
            //clears lights and tracks
            if (currentTrack.getLeftNeighbor().getID().equals("track") || currentTrack.getLeftNeighbor().getID().equals("light"))
            {
                currentTrack.setVisited(false);
                currentTrack.getLeftNeighbor().setVisited(false);
                currentTrack = currentTrack.getLeftNeighbor();
                clearLeftTracks(currentTrack);
            }
            //clears switches
            else if (currentTrack.getLeftNeighbor().getID().equals("urs")
                    || currentTrack.getLeftNeighbor().getID().equals("drs")
                    || currentTrack.getLeftNeighbor().getID().equals("uls")
                    || currentTrack.getLeftNeighbor().getID().equals("dls"))
            {

                switch (currentTrack.getLeftNeighbor().getID())
                {
                    //urs case
                    case "urs":
                        currentTrack.getLeftNeighbor().setVisited(false);
                        currentTrack.getLeftNeighbor().getTopNeighbor().setVisited(false);
                        currentTrack = currentTrack.getLeftNeighbor();
                        clearLeftTracks(currentTrack);
                        break;
                   //drs case
                    case "drs":
                        currentTrack.getLeftNeighbor().setVisited(false);
                        currentTrack.getLeftNeighbor().getBottomNeighbor().setVisited(false);
                        currentTrack = currentTrack.getLeftNeighbor();
                        clearLeftTracks(currentTrack);
                        break;
                    //dls case
                    case "dls":
                        //no dls switches
                        break;
                    //uls case
                    case "uls":
                        if (!currentTrack.getLeftNeighbor().getLeftNeighbor().isVisited())
                        {
                            currentTrack.getLeftNeighbor().setVisited(false);
                            currentTrack.getLeftNeighbor().getTopNeighbor().setVisited(false);
                            currentTrack = currentTrack.getLeftNeighbor().getTopNeighbor();
                            clearLeftTracks(currentTrack);
                        }
                        else
                        {
                            currentTrack.getLeftNeighbor().setVisited(false);
                            currentTrack = currentTrack.getLeftNeighbor();

                            clearLeftTracks(currentTrack);

                        }
                        break;

                }
            }
            //clears if its destination
            else if (currentTrack.getLeftNeighbor().getID().equals(destination))
            {
                currentTrack.getLeftNeighbor().setVisited(false);
                return;
            }
            //clears if its not destination
            else if (!currentTrack.getLeftNeighbor().getID().equals(destination))
            {
                currentTrack.getLeftNeighbor().setVisited(false);
                return;
            }
        }
    }

    /**
     * Clears tracks moving in a right direction so that a DFS is possible
     * @param currentTrack The track to start clearing from, which is where the train resides
     */
    private void clearRightTracks(TrackObject currentTrack)
    {
        while (!currentTrack.getRightNeighbor().getID().equals(null))
        {
            //clears track and lights
            if (currentTrack.getRightNeighbor().getID().equals("track") || currentTrack.getRightNeighbor().getID().equals("light"))
            {
                currentTrack.setVisited(false);
                currentTrack.getRightNeighbor().setVisited(false);
                currentTrack = currentTrack.getRightNeighbor();
                clearRightTracks(currentTrack);
            }
            //clears switches and switch neighbors
            else if (currentTrack.getRightNeighbor().getID().equals("urs")
                    || currentTrack.getRightNeighbor().getID().equals("drs")
                    || currentTrack.getRightNeighbor().getID().equals("uls")
                    || currentTrack.getRightNeighbor().getID().equals("dls"))
            {

                switch (currentTrack.getRightNeighbor().getID())
                {
                    //clears urs and neighbors
                    case "urs":
                        if (!currentTrack.getRightNeighbor().getTopNeighbor().isVisited())
                        {
                            currentTrack.setVisited(false);
                            currentTrack.getRightNeighbor().setVisited(false);
                            currentTrack = currentTrack.getRightNeighbor();

                            clearRightTracks(currentTrack);
                        }
                        else
                            {

                            currentTrack.getRightNeighbor().setVisited(false);
                            currentTrack.getRightNeighbor().getTopNeighbor().setVisited(false);
                            currentTrack.setVisited(false);
                            TrackObject temp = currentTrack;

                            while (temp.getRightNeighbor().getRightNeighbor() != null)
                            {
                                temp.getRightNeighbor().setVisited(false);
                                temp = temp.getRightNeighbor();
                            }

                            currentTrack = currentTrack.getRightNeighbor().getTopNeighbor();
                            clearRightTracks(currentTrack);

                        }
                        break;
                        //clears drs and neighbors
                    case "drs":

                        if (!currentTrack.getRightNeighbor().getBottomNeighbor().isVisited())
                        {
                            currentTrack.getRightNeighbor().getBottomNeighbor().setVisited(false);
                            TrackObject temp = currentTrack;

                            while (temp.getRightNeighbor().getRightNeighbor() != null)
                            {
                                temp.getRightNeighbor().setVisited(false);
                                temp = temp.getRightNeighbor();
                            }
//
                            currentTrack = currentTrack.getRightNeighbor();
                            clearRightTracks(currentTrack);
                        }
                        else
                        {
                            currentTrack.getRightNeighbor().setVisited(false);
                            currentTrack.getRightNeighbor().getBottomNeighbor().setVisited(false);
                            currentTrack = currentTrack.getRightNeighbor().getBottomNeighbor();
                            clearRightTracks(currentTrack);
                        }


                        break;
                        //dls case
                    case "dls":
                    //no dls switches
                        break;
                    case "uls":
                        //uls case
                        currentTrack.getRightNeighbor().setVisited(false);
                        currentTrack.getRightNeighbor().getTopNeighbor().setVisited(false);
                        currentTrack = currentTrack.getRightNeighbor();
                        clearRightTracks(currentTrack);
                        break;

                }
            }
            //if it equals destination clear it
            else if (currentTrack.getRightNeighbor().getID().equals(destination))
            {
                currentTrack.getRightNeighbor().setVisited(false);
                return;
            }
            //if it doesn't equal destination, clear it
            else if (!currentTrack.getRightNeighbor().getID().equals(destination))
            {
                currentTrack.getRightNeighbor().setVisited(false);
                currentTrack.setVisited(false);
                return;
            }
        }

    }

    /**
     * Utility function that makes adding a track look less messy
     * @param track Track to be added
     */
    private void addToPath(TrackObject track)
    {
        path.add(track);
    }

    /**
     * Getter to return the path
     * @return Arraylist of Track Object
     */
    public ArrayList<TrackObject> getPath() {
        return path;
    }


}
