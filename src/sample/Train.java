package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Train extends TrackObject implements Runnable {
    private Rectangle train = new Rectangle();
    private boolean moving = true;

    private int x = 170;
    private int y = 160;
    private Canvas canvas;
    private GraphicsContext gc;
    private boolean flag;

    public void setLight(boolean light) {
        this.light = light;
    }

    private boolean light = false;
    private TrackObject currentTrack;
    private String destination;

    public Train(String TrainID, Canvas canvas, TrackObject currentTrack, String destination) {
        super(TrainID, false);

        this.canvas = canvas;
        this.currentTrack = currentTrack;
        gc = canvas.getGraphicsContext2D();
        this.destination = destination;
        light = false;

    }


    private void changeCoordinates() {
        if (isLight()) {
            x += 110;
            setLight(false);
        } else if (!isLight()) {
            x += 55;

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
                Thread.sleep(2000);


                if (currentTrack.getRightNeighbor() != null) {



                    if (currentTrack.getRightNeighbor().getID().equals(destination)) {
                        System.out.println("You made it to your destination");
                        break;
                    }


//                    System.out.println(currentTrack.getRightNeighbor().getID());


                    if (currentTrack.getRightNeighbor().getID().equals("track") && moving ) {


                        currentTrack = currentTrack.getRightNeighbor();
                        moveTrain();
                    }
                    else {//check lights
                        checkLights();
                        //check switches
                        checkSwitches();
                    }


                } else {
                    flag = true;
                    System.out.println("Train thread is dying");
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

    public void checkLights() {
        if (currentTrack.getRightNeighbor().getID().equals("light")) {

            if (currentTrack.getRightNeighbor().isOccupied()) {
                //light is on red
                moving = false;
                System.out.println("light is red");



            } else if(!currentTrack.getRightNeighbor().isOccupied()) {
                //light is on green
//                System.out.println("light is green");
                setLight(true);
                moveTrain();
                 currentTrack = currentTrack.getRightNeighbor().getRightNeighbor();




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
}
