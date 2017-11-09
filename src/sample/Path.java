package sample;


import java.util.ArrayList;

/**
 * Created by Vincent on 10/20/2017.
 */

public class Path {

    private TrackObject firstTrack;
    private String destination;
    private int counter = 0;
    private boolean valid = true;

    public Path(TrackObject currentTrack, String destination, int direction) {
        TrackObject currentTrack1 = currentTrack;
        this.destination = destination;
        firstTrack = currentTrack;


        if (direction == 1) {
            buildRightPath(currentTrack, destination);
        } else {
            buildLeftPath(currentTrack, destination);
        }

    }

    public ArrayList<TrackObject> getPath() {
        return path;
    }

    private ArrayList<TrackObject> path = new ArrayList<>();

    public void addToPath(TrackObject track) {
        path.add(track);
    }


    private void buildRightPath(TrackObject currentTrack, String destination) {
        System.out.println("Current track: " + currentTrack.getID() + " next track:" + currentTrack.getRightNeighbor().getID() + " " + currentTrack.getRightNeighbor().isVisited());


        if (!currentTrack.getRightNeighbor().isVisited()) {

            if (currentTrack.getRightNeighbor().getID().equals("track") || currentTrack.getRightNeighbor().getID().equals("light")) {
                addToPath(currentTrack);
//

                currentTrack = currentTrack.getRightNeighbor();
                buildRightPath(currentTrack, destination);
            } else if (currentTrack.getRightNeighbor().getID().equals("urs")
                    || currentTrack.getRightNeighbor().getID().equals("drs")
                    || currentTrack.getRightNeighbor().getID().equals("uls")
                    || currentTrack.getRightNeighbor().getID().equals("dls")) {

                switch (currentTrack.getRightNeighbor().getID()) {
                    case "urs":
                        if (counter > 1) {
                            currentTrack.getRightNeighbor().setOccupied(true);
                            --counter;
                        }
                        if (!currentTrack.getRightNeighbor().getTopNeighbor().isVisited()) {


                            addToPath(currentTrack);
                            addToPath(currentTrack.getRightNeighbor());
//                            currentTrack.getRightNeighbor().getTopNeighbor().setVisited(true);
                            currentTrack = currentTrack.getRightNeighbor().getTopNeighbor();

                            buildRightPath(currentTrack, destination);
                        } else {


                            addToPath(currentTrack);
                            currentTrack = currentTrack.getRightNeighbor();
                            buildRightPath(currentTrack, destination);

                        }

                        break;
                    case "drs":

                        System.out.println("drs: " + currentTrack.getRightNeighbor().getID() + " bottom neighbor " + currentTrack.getRightNeighbor().getBottomNeighbor().isVisited());
                        if (!currentTrack.getRightNeighbor().getBottomNeighbor().isVisited()) {
                            addToPath(currentTrack);
                            addToPath(currentTrack.getRightNeighbor());
                            currentTrack = currentTrack.getRightNeighbor().getBottomNeighbor();
                            System.out.println("WE should make it in here for v");

                            buildRightPath(currentTrack, destination);
                        } else {

//                            if (counter > 1) {
//                                currentTrack.getRightNeighbor().setVisited(false);
//                                currentTrack.getRightNeighbor().getBottomNeighbor().setVisited(false);
//                                --counter;
//                            }
                            addToPath(currentTrack);
                            currentTrack = currentTrack.getRightNeighbor();
                            buildRightPath(currentTrack, destination);

                        }
                        break;
                    case "dls":
                        addToPath(currentTrack);
                        currentTrack = currentTrack.getRightNeighbor();
                        buildRightPath(currentTrack, destination);
                        break;
                    case "uls":
                        addToPath(currentTrack);
                        currentTrack = currentTrack.getRightNeighbor();
                        buildRightPath(currentTrack, destination);
                        break;

                }
            } else if (currentTrack.getRightNeighbor().getID().equals(destination)) {
                addToPath(currentTrack);
                addToPath(currentTrack.getRightNeighbor());
                return;
            } else if (!currentTrack.getRightNeighbor().getID().equals(destination)) {

                addToPath(currentTrack);
                addToPath(currentTrack.getRightNeighbor());
                currentTrack.setVisited(true);
                path.clear();
                buildRightPath(firstTrack, destination);
                return;
            }
        } else {
            if (counter > 50) {
                counter = 0;
                System.out.println("stop");
//                Platform.runLater(() -> this.destination = new ConductorScreen(Thread.currentThread()).getDestination());
                clearRightTracks(currentTrack);
                return;

            }
            currentTrack.setVisited(true);
            path.clear();
            counter++;
            buildRightPath(firstTrack, destination);
            System.out.println(counter);
        }
    }



    private void buildLeftPath(TrackObject currentTrack, String destination) {

        System.out.println("Current track: " + currentTrack.getID() + " next track:" + currentTrack.getLeftNeighbor().getID() + " " + currentTrack.getLeftNeighbor().isVisited());

        if (!currentTrack.getLeftNeighbor().isVisited()) {
            if (currentTrack.getLeftNeighbor().getID().equals("track") || currentTrack.getLeftNeighbor().getID().equals("light")) {
                addToPath(currentTrack);


                currentTrack = currentTrack.getLeftNeighbor();
                buildLeftPath(currentTrack, destination);
            } else if (currentTrack.getLeftNeighbor().getID().equals("urs")
                    || currentTrack.getLeftNeighbor().getID().equals("drs")
                    || currentTrack.getLeftNeighbor().getID().equals("uls")
                    || currentTrack.getLeftNeighbor().getID().equals("dls")) {

                switch (currentTrack.getLeftNeighbor().getID()) {
                    case "urs":
                        addToPath(currentTrack);
                        currentTrack = currentTrack.getLeftNeighbor();
                        buildLeftPath(currentTrack, destination);
                        break;
                    case "drs":
                        addToPath(currentTrack);
                        currentTrack = currentTrack.getLeftNeighbor();
                        buildLeftPath(currentTrack, destination);


                        break;
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
                    case "uls":
                        if (!currentTrack.getLeftNeighbor().getTopNeighbor().isVisited()) {
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
            } else if (currentTrack.getLeftNeighbor().getID().equals(destination)) {
                addToPath(currentTrack);
                addToPath(currentTrack.getLeftNeighbor());
                return;
            } else if (!currentTrack.getLeftNeighbor().getID().equals(destination)) {

                addToPath(currentTrack);
                addToPath(currentTrack.getLeftNeighbor());

                for (int i = 0; i < path.size(); i++) {
                    path.get(i).setVisited(false);
                    System.out.print(path.get(i).isVisited() + " ");
                }
                currentTrack.setVisited(true);
                path.clear();
                buildLeftPath(firstTrack, destination);
                return;
            }
        } else {

            if (counter > 50) {
                counter = 0;
                System.out.println("stop");

//              for(int i = 0; i < 9; i++){
//                  currentTrack.setVisited(false);
//                  currentTrack = currentTrack.getLeftNeighbor();
//              }

                clearTracks(currentTrack);

                path.clear();
                valid = false;
                return;

            }

            currentTrack.setVisited(true);
            path.clear();

            counter++;
            buildLeftPath(firstTrack, destination);

        }

    }

    private void clearTracks(TrackObject currentTrack) {
        while (!currentTrack.getLeftNeighbor().getID().equals(null)) {

            if (currentTrack.getLeftNeighbor().getID().equals("track") || currentTrack.getLeftNeighbor().getID().equals("light")) {
                currentTrack.setVisited(false);
                currentTrack.getLeftNeighbor().setVisited(false);


                currentTrack = currentTrack.getLeftNeighbor();
                clearTracks(currentTrack);
            } else if (currentTrack.getLeftNeighbor().getID().equals("urs")
                    || currentTrack.getLeftNeighbor().getID().equals("drs")
                    || currentTrack.getLeftNeighbor().getID().equals("uls")
                    || currentTrack.getLeftNeighbor().getID().equals("dls")) {

                switch (currentTrack.getLeftNeighbor().getID()) {
                    case "urs":
                        currentTrack.getLeftNeighbor().setVisited(false);
                        currentTrack.getLeftNeighbor().getTopNeighbor().setVisited(false);
                        currentTrack = currentTrack.getLeftNeighbor();
                        clearTracks(currentTrack);
                        break;
                    case "drs":

                        currentTrack.getLeftNeighbor().setVisited(false);

                        currentTrack.getLeftNeighbor().getBottomNeighbor().setVisited(false);
                        currentTrack = currentTrack.getLeftNeighbor();
                        clearTracks(currentTrack);


                        break;
                    case "dls":

                        break;
                    case "uls":
                        if (!currentTrack.getLeftNeighbor().getLeftNeighbor().isVisited()) {
                            currentTrack.getLeftNeighbor().setVisited(false);
                            currentTrack.getLeftNeighbor().getTopNeighbor().setVisited(false);
                            currentTrack = currentTrack.getLeftNeighbor().getTopNeighbor();
                            clearTracks(currentTrack);
                        } else {
                            currentTrack.getLeftNeighbor().setVisited(false);
                            currentTrack = currentTrack.getLeftNeighbor();

                            clearTracks(currentTrack);

                        }
                        break;

                }
            } else if (currentTrack.getLeftNeighbor().getID().equals(destination)) {
                currentTrack.getLeftNeighbor().setVisited(false);
                return;
            } else if (!currentTrack.getLeftNeighbor().getID().equals(destination)) {

                currentTrack.getLeftNeighbor().setVisited(false);


                return;
            }
        }
    }

    private void clearRightTracks(TrackObject currentTrack) {
        while (!currentTrack.getRightNeighbor().getID().equals(null)) {
System.out.println("This is the clearing method ");
            if (currentTrack.getRightNeighbor().getID().equals("track") || currentTrack.getRightNeighbor().getID().equals("light")) {
                currentTrack.setVisited(false);
                currentTrack.getRightNeighbor().setVisited(false);


                currentTrack = currentTrack.getRightNeighbor();
                clearRightTracks(currentTrack);
            } else if (currentTrack.getRightNeighbor().getID().equals("urs")
                    || currentTrack.getRightNeighbor().getID().equals("drs")
                    || currentTrack.getRightNeighbor().getID().equals("uls")
                    || currentTrack.getRightNeighbor().getID().equals("dls")) {

                switch (currentTrack.getRightNeighbor().getID()) {
                    case "urs":


                        if (!currentTrack.getRightNeighbor().getTopNeighbor().isVisited()) {


                            currentTrack.setVisited(false);
                            currentTrack.getRightNeighbor().setVisited(false);
                            currentTrack = currentTrack.getRightNeighbor();

                            clearRightTracks(currentTrack);
                        } else {

                            currentTrack.getRightNeighbor().setVisited(false);
                            currentTrack.getRightNeighbor().getTopNeighbor().setVisited(false);
                            currentTrack.setVisited(false);
                            TrackObject temp = currentTrack;

                            while(temp.getRightNeighbor().getRightNeighbor() != null){
                               temp.getRightNeighbor().setVisited(false);
                               temp = temp.getRightNeighbor();
                           }

                            currentTrack = currentTrack.getRightNeighbor().getTopNeighbor();

                            clearRightTracks(currentTrack);

                        }
                        break;
                    case "drs":

                        if(!currentTrack.getRightNeighbor().getBottomNeighbor().isVisited()){
                            currentTrack.getRightNeighbor().getBottomNeighbor().setVisited(false);
                            System.out.println("This is where we made it in the process");
//                            TrackObject temp = currentTrack;
//                            while(temp.getRightNeighbor() != null){
//
//                            }

                            currentTrack.getRightNeighbor().setVisited(false);
                            currentTrack.setVisited(false);
                            currentTrack = currentTrack.getRightNeighbor().getBottomNeighbor();
                            clearRightTracks(currentTrack);
                        } else{
                            currentTrack.setVisited(false);
                            currentTrack.getRightNeighbor().setVisited(false);
                            currentTrack.getRightNeighbor().getBottomNeighbor().setVisited(false);
                            currentTrack = currentTrack.getRightNeighbor().getBottomNeighbor();
                            clearRightTracks(currentTrack);
                        }


                        break;
                    case "dls":

                        break;
                    case "uls":
                        currentTrack.getRightNeighbor().setVisited(false);
                        currentTrack.getRightNeighbor().getTopNeighbor().setVisited(false);
                        currentTrack = currentTrack.getRightNeighbor();
                        clearRightTracks(currentTrack);
                        break;

                }
            } else if (currentTrack.getRightNeighbor().getID().equals(destination)) {
                currentTrack.getRightNeighbor().setVisited(false);
                return;
            } else if (!currentTrack.getRightNeighbor().getID().equals(destination)) {

                currentTrack.getRightNeighbor().setVisited(false);


                return;
            }
        }

    }

}
