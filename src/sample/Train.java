package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Train extends TrackObject implements Runnable {

    private boolean moving = true;

    private int x = 55;
    private int y = 10;

    private int direction;
    private Canvas canvas;
    private GraphicsContext gc;
    private boolean flag, valid;
    private boolean light = false;
    private TrackObject currentTrack;
    private String TrainID;
    private String temp;


    public void setDestination(String destination) {
        this.destination = destination;
    }

    private volatile String destination;
    private boolean threadRunning = true;
    private Path c;

    public String getDestination() {
        return destination;
    }

    public String getTrainID() {

        return TrainID;
    }

    public Train(String TrainID, Canvas canvas, TrackObject currentTrack, String destination, int direction) {
        super(TrainID, false);

        this.canvas = canvas;
        this.currentTrack = currentTrack;
        gc = canvas.getGraphicsContext2D();
        this.destination = destination;
        light = false;
        this.direction = direction;
        this.TrainID = TrainID;
        temp = destination;


        c = new Path(currentTrack, destination, direction);

        printPath();

    }


    public void setLight(boolean light) {
        this.light = light;
    }


    private void changeCoordinates() {
        if (isLight()) {
            x += direction * 110;
            setLight(false);
        } else if (!isLight()) {
            x += direction * 55;

        }


    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void run() {
        while (!flag) try {
            gc.setFill(Color.RED);
            gc.fillRect(getX(), getY(), 30, 20);
            //System.out.print(currentTrack.getID() + " ");

//System.out.println(c.getPath().get(0).getRightNeighbor().getID());
            Thread.sleep(500);

            if (direction == 1 && !c.getPath().isEmpty() && moving) {
               valid = true;
                if (c.getPath().get(0) != null) {

//                      System.out.println(c.getPath().get(4).getBottomNeighbor().isVisited());

                    if (c.getPath().get(0).getRightNeighbor().getID().equals(destination)) {
                        System.out.println("You made it to your destination");
                        direction *= -1;
                        currentTrack = c.getPath().get(0);

                        c.getPath().get(0).setVisited(false);
                        c.getPath().get(0).getRightNeighbor().setVisited(false);
                        c.getPath().clear();
                        temp = destination;
                        this.moving = false;
                        while (destination != null) {

                            if (temp != destination
//                                        && searchFunction(currentTrack, direction, destination)
                                    ) {

                                c = new Path(currentTrack, destination, direction);

                                printPath();

                                moving = true;
                                break;
                            }
                        }

                    } else if (c.getPath().get(0).getRightNeighbor().getID().equals("track") && moving) {

                        c.getPath().get(0).setVisited(false);
                        c.getPath().get(0).getRightNeighbor().setVisited(false);
                        c.getPath().remove(0);
                        printPath();
                        moveTrain();
                    } else {//check lights
                        checkRightLights();
                        //check switches
                        checkSwitches();
                    }


                }


            } else if (direction == -1 && !c.getPath().isEmpty() && moving) {
               valid = true;
                if (!c.getPath().get(0).getLeftNeighbor().equals(null)) {

//                        System.out.println(c.getPath().get(0).getID());

                    if (c.getPath().get(0).getLeftNeighbor().getID().equals(destination)) {
                        System.out.println("You made it to your destination");
                        direction *= -1;
                        String temp = destination;
                        currentTrack = c.getPath().get(0);
                        c.getPath().get(0).setVisited(false);
                        c.getPath().get(0).getRightNeighbor().setVisited(false);

                        System.out.println("Current Path " + currentTrack.getID());
                        c.getPath().clear();
//                            Platform.runLater(() -> destination = new ConductorScreen(Thread.currentThread()).getDestination());

                        this.moving = false;
                        while (true) {

                            if (temp != destination
//                                        && searchFunction(currentTrack, direction, destination)
                                    ) {
                                c = new Path(currentTrack, destination, direction);
                                printPath();
                                moving = true;
                                break;
                            }

                        }


                    } else if (c.getPath().get(0).getLeftNeighbor().getID().equals("track") && moving) {


                        c.getPath().get(0).setVisited(false);

                        c.getPath().remove(0);
                        printPath();
                        moveTrain();
                    } else {
                        //check switches
                        checkLeftSwitches();
                        //check lights
                        checkLeftLights();

                    }


                }
            }

            if (c.getPath().isEmpty()) {
                temp = destination;

                valid = false;
                this.moving = false;
//                direction *= -1;

                while (true) {
//
                    if (!temp.equals(destination)) {

                        c = new Path(currentTrack, destination, direction);

                        printPath();
                        moving = true;
                        break;
                    }
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private void setX(int x) {
        this.x = x;
    }

    private void checkSwitches() {


        switch (c.getPath().get(0).getRightNeighbor().getID()) {
            //if the switch is on
            case "urs":
//                c.getPath().get(0).getRightNeighbor().setOccupied(true);
                if (c.getPath().get(0).getRightNeighbor().isOccupied()) {
                    //keep moving but go up
                    gc.clearRect(getX(), getY(), 30, 20);
                    setY(getY() - 75);
                    setLight(true);
                    c.getPath().get(0).getRightNeighbor().getTopNeighbor().setVisited(false);
                    currentTrack = currentTrack.getTopNeighbor();
                    c.getPath().remove(0);
                    c.getPath().remove(0);
                    moveTrain();

                } else {

                    c.getPath().get(0).getRightNeighbor().getTopNeighbor().setVisited(false);

                    c.getPath().remove(0);
                    moveTrain();
                }
                break;
            case "uls":
                if (destination.equals("W")) {
                    c.getPath().remove(0);
                    moveTrain();
                } else if (currentTrack.getRightNeighbor().isOccupied()) {
                    moving = false;
                }
                break;
            case "dls":
                if (currentTrack.getRightNeighbor().isOccupied()) {
                    moving = false;
                }
                break;
            case "drs":


                if (direction == -1) {
                    moveTrain();
                    c.getPath().remove(0);
                } else if (c.getPath().get(0).getRightNeighbor().isOccupied() && !c.getPath().get(0).getRightNeighbor().getBottomNeighbor().isVisited()) {
                    gc.clearRect(getX(), getY(), 30, 20);


                    //  currentTrack = currentTrack.getRightNeighbor().getBottomNeighbor();
                    c.getPath().get(0).getRightNeighbor().getBottomNeighbor().setVisited(false);

                    c.getPath().remove(0);
                    c.getPath().remove(0);
                    setY(getY() + 75);
                    setX(getX() + 55);
                    moveTrain();

                } else {
                    moveTrain();
//                    currentTrack = currentTrack.getRightNeighbor();
                    c.getPath().get(0).getRightNeighbor().getBottomNeighbor().setVisited(false);
                    c.getPath().get(0).getRightNeighbor().setVisited(false);

                    System.out.println("drs " + c.getPath().get(0).getRightNeighbor().getID() + " bottom " + c.getPath().get(0).getRightNeighbor().getBottomNeighbor().isVisited());

                    c.getPath().get(0).getRightNeighbor().getBottomNeighbor().getRightNeighbor().setVisited(false);

                    c.getPath().get(0).getRightNeighbor().getBottomNeighbor().getRightNeighbor().setVisited(false);

                    if (c.getPath().get(0).getRightNeighbor().getBottomNeighbor().getRightNeighbor().getRightNeighbor() != null && c.getPath().get(0).getRightNeighbor().getBottomNeighbor().getRightNeighbor().getBottomNeighbor() != null) {
                        c.getPath().get(0).getRightNeighbor().getBottomNeighbor().getRightNeighbor().getRightNeighbor().setVisited(false);
                        c.getPath().get(0).getRightNeighbor().getBottomNeighbor().getRightNeighbor().getBottomNeighbor().setVisited(false);
                    }
                    c.getPath().remove(0);

                }


                break;


        }
    }

    public boolean isMoving() {
        return moving;
    }

    public void checkLeftSwitches() {


        switch (c.getPath().get(0).getLeftNeighbor().getID()) {
            //if the switch is on
            case "urs":
                //  setY(getY() - 75);
                moveTrain();
//              System.out.println( c.getPath().get(0).getLeftNeighbor().getTopNeighbor().getID() + " " + c.getPath().get(0).getLeftNeighbor().getTopNeighbor().isVisited()+ " " + c.getPath().get(0).getLeftNeighbor().getID() + " ");
                c.getPath().get(0).getLeftNeighbor().getTopNeighbor().setVisited(false);

                c.getPath().remove(0);

                break;
            case "uls":
                if (c.getPath().get(0).getLeftNeighbor().isOccupied()) {
                    c.getPath().get(0).setVisited(false);

                    c.getPath().remove(0);
                    c.getPath().get(0).setVisited(false);

                    c.getPath().remove(0);


                    gc.clearRect(getX(), getY(), 30, 20);

                    if (!destination.equals("C")) {
                        c.getPath().get(0).getLeftNeighbor().getTopNeighbor().setVisited(false);

                        setLight(true);
                        setY(getY() - 75);
                        moveTrain();

                    } else {


                        moveTrain();
                    }

                }
                break;
            case "dls":
                if (currentTrack.getRightNeighbor().isOccupied()) {
                    moving = false;
                }
                break;
            case "drs":
                if (direction == -1) {
                    moveTrain();
                    c.getPath().get(0).setVisited(false);

                    c.getPath().remove(0);
                } else if (c.getPath().get(0).getRightNeighbor().isOccupied() && !c.getPath().get(0).getRightNeighbor().getBottomNeighbor().isVisited()) {
                    gc.clearRect(getX(), getY(), 30, 20);

                    //  currentTrack = currentTrack.getRightNeighbor().getBottomNeighbor();


                    c.getPath().remove(0);


                    c.getPath().remove(0);
                    setY(getY() + 75);
                    setX(getX() + 55);
                    moveTrain();

                } else {
                    moveTrain();
//                    currentTrack = currentTrack.getRightNeighbor();
//                    c.getPath().get(0).getRightNeighbor().getBottomNeighbor().setVisited(false);
//                    c.getPath().get(0).setVisited(false);


                    c.getPath().remove(0);
                }

                break;


        }
    }


    public void checkRightLights() {
        if (c.getPath().get(0).getRightNeighbor().getID().equals("light")) {

            if (c.getPath().get(0).getRightNeighbor().isOccupied()) {
                //light is on red
                moving = false;
//                System.out.println("light is red");


            } else if (!c.getPath().get(0).getRightNeighbor().isOccupied()) {
                //light is on green
//                System.out.println("light is green");
                setLight(true);
                moveTrain();
                c.getPath().get(0).setVisited(false);
                c.getPath().remove(0);
                c.getPath().get(0).setVisited(false);
                c.getPath().remove(0);


            }

        }
    }

    public void checkLeftLights() {
        if (c.getPath().get(0).getLeftNeighbor().getID().equals("light")) {

            if (c.getPath().get(0).getLeftNeighbor().isOccupied()) {
                //light is on red
                moving = false;
//                System.out.println("light is red");


            } else if (!c.getPath().get(0).getLeftNeighbor().isOccupied()) {
                //light is on green
//                System.out.println("light is green");
                setLight(true);
                moveTrain();
                c.getPath().get(0).setVisited(false);

                c.getPath().remove(0);
                c.getPath().get(0).setVisited(false);

                c.getPath().remove(0);


            }

        }
    }

    public void moveTrain() {
        gc.clearRect(getX(), getY(), 30, 20);
        changeCoordinates();
        gc.fillRect(getX(), getY(), 30, 20);
    }

    public boolean isLight() {
        return light;
    }
//

    private void printPath() {
        System.out.print("_______");
        for (int i = 0; i < c.getPath().size(); i++) {
            System.out.print(c.getPath().get(i).getID() + " ");
        }
        System.out.println("______");

    }

    public boolean isValid() {
        return valid;
    }
}
