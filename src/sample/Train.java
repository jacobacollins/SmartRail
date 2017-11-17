package sample;
import com.sun.deploy.config.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * @author Jacob Collins, Vincent Crespin
 * This is the Train object that will be moving across the screen
 */
public class Train extends TrackObject implements Runnable {

    private boolean moving = true;
    private int x = 55;
    private int y = 10;
    private int direction;
    private Canvas canvas;
    private GraphicsContext gc;
    private boolean flag;
    private boolean light = false;
    private TrackObject currentTrack;
    private String TrainID;
    private String temp;
    private volatile String destination;
    private boolean threadRunning = true;
    private Path c;
    LayoutDisplay lD;
    Timer time;
    TrackObject[][] tO;
    Button lightBttn;

    public Train(String TrainID, Canvas canvas, TrackObject currentTrack,
                 String destination, int direction, TrackObject[][] tO,
                 Button lightBttn) {
        super(TrainID, false);
        this.lightBttn = lightBttn;
        this.tO = tO;
        this.time = new Timer(tO);
        this.canvas = canvas;
        this.time = time;
        this.lD = new LayoutDisplay(canvas);
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

    /**
     * changes coordinates for the train
     */
    private void changeCoordinates() {
        if (isLight()) {
            x += direction * 110;
            setLight(false);
        } else if (!isLight()) {
            x += direction * 55;

        }


    }

    public void run()
    {
        while (!flag) try
        {
          javafx.application.Platform.runLater(new Runnable() {
            @Override
            public void run() {
              lD.tracksDisplay(tO);
              gc.setFill(Color.RED);
              gc.fillRect(getX(), getY(), 30, 20);
              gc.setFill(Color.BLACK);
              gc.fillRect(getX(), getY()+30, 50, 10);
            }
          });
          lightBttn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
              moving = time.changeLight(tO);
            }
          });


            Thread.sleep(500);

            //if the direction is moving to the right, the path isn't empty, and the train is moving
            if (direction == 1 && !c.getPath().isEmpty() && moving)
            {

                if (c.getPath().get(0) != null)
                {

                    //if we make it to our destination
                    if (c.getPath().get(0).getRightNeighbor().getID().equals(destination))
                    {
                        direction *= -1;
                        currentTrack = c.getPath().get(0);
                        c.getPath().clear();
                        temp = destination;
                        this.moving = false;

                        //When we make it to a destination we are checking for a change in where the train is going
                        while (true)
                        {
                            if (!temp.equals(destination))
                            {

                                c = new Path(currentTrack, destination, direction);
                                moving = true;
                                break;
                            }
                        }

                    }
                    //moves from track
                    else if (c.getPath().get(0).getRightNeighbor().getID().equals("track") && moving)
                    {
                        c.getPath().remove(0);
                        printPath();
                        moveTrain();
                    }
                    else
                    {
                        //check lights
                        checkRightLights();
                        //check switches
                        checkSwitches();
                    }


                }


            }
            //if we are moving left the path isn't empty and the train is moving
            else if (direction == -1 && !c.getPath().isEmpty() && moving)
            {

                if (!c.getPath().get(0).getLeftNeighbor().equals(null))
                {
                    if (c.getPath().get(0).getLeftNeighbor().getID().equals(destination))
                    {
                        direction *= -1;
                        String temp = destination;
                        currentTrack = c.getPath().get(0);
                        c.getPath().clear();

                        this.moving = false;
                        while (true)
                        {
                            if (!temp.equals(destination))
                            {
                                c = new Path(currentTrack, destination, direction);
                                printPath();
                                moving = true;
                                break;
                            }

                        }


                    }

                    else if (c.getPath().get(0).getLeftNeighbor().getID().equals("track") && moving)
                    {

                        c.getPath().get(0).setVisited(false);
                        c.getPath().remove(0);
                        printPath();
                        moveTrain();
                    }
                    else
                    {
                        //check switches
                        checkLeftSwitches();
                        //check lights
                        checkLeftLights();

                    }

                }
            }

            //This is the case where there was an invalid destination entered
            if (c.getPath().isEmpty())
            {
                temp = destination;
                this.moving = false;
                while (true)
                {
                    if (!temp.equals(destination))
                    {
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

    /**
     * setter for x
     * @param x int
     */
    private void setX(int x) {
        this.x = x;
    }

    /**
     * checks the right switches
     */
    private void checkSwitches() {


        switch (c.getPath().get(0).getRightNeighbor().getID()) {
            //if the switch is on
            case "urs":
                if (c.getPath().get(0).getRightNeighbor().isOccupied()) {
                    //keep moving but go up
                    gc.clearRect(getX(), getY(), 30, 20);
                    setY(getY() - 75);
                    setLight(true);
                    c.getPath().get(0).getRightNeighbor().getTopNeighbor().setVisited(false);
                    c.getPath().remove(0);
                    c.getPath().get(0).setVisited(false);
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

    /**
     * getter for moving boolean
     * @return moving boolean
     */
    public boolean isMoving() {
        return moving;
    }

    /**
     * checks the left switches
     */
    public void checkLeftSwitches() {


        switch (c.getPath().get(0).getLeftNeighbor().getID()) {
            //if the switch is on
            case "urs":
                //  setY(getY() - 75);
                moveTrain();

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

    /**
     * checks the right lights
     */
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

    /**
     * checks the left lights
     */
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

    /**
     * moves train by drawing
     */
    public void moveTrain() {
        gc.clearRect(getX(), getY(), 30, 20);
        gc.clearRect(getX(), getY()+30, 50, 10);
        changeCoordinates();
    }

    /**
     * getter for light boolean
     * @return light boolean
     */
    public boolean isLight() {
        return light;
    }
//

    /**
     * utility function to print out the path
     */
    private void printPath() {
        System.out.print("_______");
        for (int i = 0; i < c.getPath().size(); i++) {
            System.out.print(c.getPath().get(i).getID() + " ");
        }
        System.out.println("______");

    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public String getTrainID() {

        return TrainID;
    }

    private void setLight(boolean light) {
        this.light = light;
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

    public void setMoving(boolean moving) {
    this.moving = moving;
  }
}
