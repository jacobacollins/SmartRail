import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Train extends TrackObject implements Runnable {
    private Rectangle train = new Rectangle();
    private boolean moving = false;
    private int x, y = 25;
    private Canvas canvas;
    private GraphicsContext gc;
    private boolean flag = false;


    public Train(String TrainID, Canvas canvas) {
        super(TrainID, false);
        train.setX(25);
        train.setY(25);
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
    }


    private void changeCoordinates() {
        x += 25;
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
                Thread.sleep(500);

                if (getX() < 500) {
                    gc.clearRect(getX(), getY(), 10, 10);
                    changeCoordinates();
                    gc.fillRect(getX(), getY(), 10, 10);
                } else {
                    flag = true;
                }

                System.out.println(getX() + " " + getY());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }


}
