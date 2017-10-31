package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class ConductorScreen {

    private int trainDirection;
    private Track currentTrack;
    private ButtonType A, B, C, X, Y, Z;
    private String destination;
    private Thread currentThread;

    public ConductorScreen(Thread currentThread) {

        this.currentThread = currentThread;


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conductor");
        alert.setHeaderText("Select Station");
        alert.setContentText("What Station should the train go to?");

        A = new ButtonType("A");
        B = new ButtonType("B");
        C = new ButtonType("C");
        X = new ButtonType("X");
        Y = new ButtonType("Y");
        Z = new ButtonType("Z");

        alert.getButtonTypes().setAll(A, B, C, X, Y, Z);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == A) {
            this.destination = "A";

        } else if (result.get() == B) {
            this.destination = "B";
        } else if (result.get() == C) {
            this.destination = "C";
        } else if (result.get() == X) {
            this.destination = "X";
        } else if (result.get() == Y) {
            this.destination = "Y";
        } else if (result.get() == Z) {
            this.destination = "Z";
        }




    }

    public String getDestination() {
        return destination;
    }

}
