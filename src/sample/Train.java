package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Train extends TrackObject implements Runnable {
    private Rectangle train = new Rectangle();
    private boolean moving = false;

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
                  System.out.println(currentTrack.getRightNeighbor().isOccupied());
                    if (currentTrack.getRightNeighbor().getID().equals(destination)) {
                        System.out.println("You've reached " + destination);
                        break;
                    }

                    if (currentTrack.getRightNeighbor().getID().equals("light") && currentTrack.getRightNeighbor().isOccupied()) {

                        System.out.println("light is on");
                        setLight(true);

                        currentTrack = currentTrack.getRightNeighbor();
                    }


                    gc.clearRect(getX(), getY(), 30, 20);
                    changeCoordinates();
                    gc.fillRect(getX(), getY(), 30, 20);


                    currentTrack = currentTrack.getRightNeighbor();
                } else {
                    flag = true;
                    System.out.println("Train thread is dying");
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }


    public boolean isLight() {
        return light;
    }
}
