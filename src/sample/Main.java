package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static java.lang.Thread.State.WAITING;

public class Main extends Application {

    private String destination;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello World");

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color:darkkhaki");
        //left Pane

        VBox leftPane = new VBox();

        leftPane.setPadding(new Insets(50, 25, 50, 25));
        leftPane.setSpacing(100);
        leftPane.setAlignment(Pos.BASELINE_LEFT);
        Label trainNumber = new Label("# of trains");
        trainNumber.setFont(new Font(17));
        Label activeLights = new Label("Current destination is ");
        activeLights.setFont(new Font(17));
        TextField activeSwitches = new TextField("destination");
        Button lightBttn = new Button("Light On/Off");
        activeSwitches.setFont(new Font(17));
        leftPane.getChildren().addAll(trainNumber, activeLights, activeSwitches, lightBttn);

        root.setLeft(leftPane);


        StackPane canvasPane = new StackPane();

        Canvas middle = new Canvas(800, 600);
        canvasPane.getChildren().add(middle);
        canvasPane.setStyle("-fx-background-color: gainsboro");
        canvasPane.setAlignment(Pos.CENTER);
        root.setCenter(canvasPane);
        BorderPane.setMargin(canvasPane, new Insets(25, 25, 25, 25));
//      GraphicsContext gc = middle.getGraphicsContext2D();  


//        layout = [1][11]
        //TrackLayout tl = new TrackLayout(layout);
        LayoutReader lR = new LayoutReader();
      TrackObject[][] layout = lR.getLanes();
        LayoutDisplay layoutDisplay = new LayoutDisplay(middle);
        layoutDisplay.tracksDisplay(layout);
//  ConductorScreen inpute = new ConductorScreen(Thread.currentThread());

        Train t2 = new Train("Train 1", middle, layout[0][1], "U", 1, layout, lightBttn);


        Thread t1 = new Thread(t2);

        activeSwitches.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               if(!t2.isMoving()) {
                   destination = activeSwitches.getText();
                   activeSwitches.clear();
                   t2.setDestination(destination);

                       activeLights.setText("Current destination is " + t2.getDestination());


               } else {
                   activeSwitches.clear();
                   activeLights.setText("Cannot change the destination while " + t2.getTrainID() + " is moving.");
               }
               }
        });


        primaryStage.setScene(new Scene(root, 1100, 650));
        primaryStage.setResizable(false);
        primaryStage.show();

        t1.start();
    }


    public static void main(String[] args) {
        launch(args);
    }


    }

