import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Train extends TrackObject implements Runnable {
    private Rectangle train = new Rectangle();
    private boolean moving = false;
    private int x, y = 25;
    private Canvas canvas;
    private GraphicsContext gc;
    private boolean flag = false;
    private TrackObject currentTrack;
    private String destination;

    public Train(String TrainID, Canvas canvas, TrackObject currentTrack) {
        super(TrainID, false);
        train.setX(25);
        train.setY(25);
        this.canvas = canvas;
        this.currentTrack = currentTrack;
        gc = canvas.getGraphicsContext2D();
    }


    private void changeCoordinates() {
        x += 40;
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
                Thread.sleep(750);
                gc.fillRect(getX(), getY(), 30, 20);
                System.out.print(currentTrack.getID() + " ");

                if(currentTrack.getRightNeighbor() != null){
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


}
