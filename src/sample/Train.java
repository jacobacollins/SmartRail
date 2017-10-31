package sample;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Train extends TrackObject implements Runnable {
    private Rectangle train = new Rectangle();
    private boolean moving = true;

    private int x = 170;
    private int y = 160;
    private int direction;
    private Canvas canvas;
    private GraphicsContext gc;
    private boolean flag;
    private boolean light = false;
    private TrackObject currentTrack;
    private volatile String destination;
    private boolean threadRunning = true;

    public Train(String TrainID, Canvas canvas, TrackObject currentTrack, String destination, int direction) {
        super(TrainID, false);

        this.canvas = canvas;
        this.currentTrack = currentTrack;
        gc = canvas.getGraphicsContext2D();
        this.destination = destination;
        light = false;
        this.direction = direction;

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
        while (!flag) {


            try {
                gc.setFill(Color.RED);
                gc.fillRect(getX(), getY(), 30, 20);
                System.out.print(currentTrack.getID() + " ");
                Thread.sleep(1000);

                if (direction == 1) {
                    if (currentTrack.getRightNeighbor() != null) {


                        if (currentTrack.getRightNeighbor().getID().equals(destination)) {
                            System.out.println("You made it to your destination");
                            direction *= -1;
                            Platform.runLater(() -> destination = new ConductorScreen(Thread.currentThread()).getDestination());
                            String temp = destination;
                            while (destination != null) {


                                if (temp != destination && searchFunction(currentTrack, direction, destination)) {
                                    moving = true;
                                    break;
                                }
                            }

                        }




                        if (currentTrack.getRightNeighbor().getID().equals("track") && moving) {


                            currentTrack = currentTrack.getRightNeighbor();
                            moveTrain();
                        } else {//check lights
                            checkRightLights();
                            //check switches
                            checkSwitches();
                        }


                    } else {
                        flag = true;
//                        System.out.println("Train thread is dying");
                    }

                } else if (direction == -1) {
                    if (currentTrack.getLeftNeighbor() != null) {


                        if (currentTrack.getLeftNeighbor().getID().equals(destination)) {
//                            System.out.println("You made it to your destination");
                            direction *= -1;
                            String temp = destination;

                            Platform.runLater(() -> destination = new ConductorScreen(Thread.currentThread()).getDestination());


                            while (destination != null) {


                                if (temp != destination && searchFunction(currentTrack, direction, destination)) {
                                    moving = true;
                                    break;
                                }
                            }
//                                    else {
//
//                                        moving = false;
//                                        System.out.println("in the looppppp");
//                                    }

//                                Thread.sleep(3000);


                        }


//                    System.out.println(currentTrack.getRightNeighbor().getID());


                        if (currentTrack.getLeftNeighbor().getID().equals("track") && moving) {


                            currentTrack = currentTrack.getLeftNeighbor();
                            moveTrain();
                        } else {//check lights
                            checkLeftLights();
                            //check switches
                            checkSwitches();
                        }


                    } else {
                        flag = true;
//                        System.out.println("Train thread is dying");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


    public void checkSwitches() {


        switch (currentTrack.getRightNeighbor().getID()) {
            //if the switch is on
            case "urs":
                if (currentTrack.getRightNeighbor().isOccupied()) {
                    //keep moving but go up
                    setY(getY() - 25);
                    currentTrack = currentTrack.getTopNeighbor();
                    moveTrain();

                }
                break;
            case "uls":
                if (currentTrack.getRightNeighbor().isOccupied()) {
                    moving = false;
                }
                break;
            case "dls":
                if (currentTrack.getRightNeighbor().isOccupied()) {
                    moving = false;
                }
            case "drs":
                if (currentTrack.getRightNeighbor().isOccupied()) {
                    setY(getY() + 25);
                    currentTrack = currentTrack.getBottomNeighbor();
                    moveTrain();

                }


        }
    }

    public void checkRightLights() {
        if (currentTrack.getRightNeighbor().getID().equals("light")) {

            if (currentTrack.getRightNeighbor().isOccupied()) {
                //light is on red
                moving = false;
//                System.out.println("light is red");


            } else if (!currentTrack.getRightNeighbor().isOccupied()) {
                //light is on green
//                System.out.println("light is green");
                setLight(true);
                moveTrain();
                currentTrack = currentTrack.getRightNeighbor().getRightNeighbor();


            }

        }
    }

    public void checkLeftLights() {
        if (currentTrack.getLeftNeighbor().getID().equals("light")) {

            if (currentTrack.getLeftNeighbor().isOccupied()) {
                //light is on red
                moving = false;
//                System.out.println("light is red");


            } else if (!currentTrack.getLeftNeighbor().isOccupied()) {
                //light is on green
//                System.out.println("light is green");
                setLight(true);
                moveTrain();
                currentTrack = currentTrack.getLeftNeighbor().getLeftNeighbor();


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

    private boolean searchFunction(TrackObject currentTrack, int direction, String destination) {
//        System.out.println(currentTrack.getID());
//        System.out.println(currentTrack.getLeftNeighbor().getID());
//        System.out.println(direction);
//        System.out.println(destination);

        while (!currentTrack.getLeftNeighbor().getID().equals(null) && !currentTrack.getRightNeighbor().getID().equals(null)) {

            if (direction == 1) {

                if (!currentTrack.getRightNeighbor().getID().equals(destination) && !currentTrack.getRightNeighbor().equals(null)) {
                    currentTrack = currentTrack.getRightNeighbor();
                    searchFunction(currentTrack, direction, destination);

                } else {
                    return true;
                }
            } else if (direction == -1) {

                if (!currentTrack.getLeftNeighbor().getID().equals(destination) && !currentTrack.getLeftNeighbor().equals(null)) {
                    currentTrack = currentTrack.getLeftNeighbor();
                    searchFunction(currentTrack, direction, destination);

                } else {

                    return true;
                }

            }
        }
        return false;
    }
}
