
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color:darkkhaki");
        //left Pane

        VBox leftPane = new VBox();

        leftPane.setPadding(new Insets(50,25,50,25));
        leftPane.setSpacing(100);
        leftPane.setAlignment(Pos.BASELINE_LEFT);
        Label trainNumber = new Label("# of trains");
        trainNumber.setFont(new Font(17));
        Label activeLights = new Label("# of active lights");
        activeLights.setFont(new Font(17));
        Label activeSwitches = new Label("# of active switches");
        activeSwitches.setFont(new Font(17));
        leftPane.getChildren().addAll(trainNumber, activeLights,activeSwitches);

        root.setLeft(leftPane);

        StackPane canvasPane = new StackPane();

        Canvas middle = new Canvas(800,350);
        canvasPane.getChildren().add(middle);
        canvasPane.setStyle("-fx-background-color: gainsboro");
        canvasPane.setAlignment(Pos.CENTER);
        root.setCenter(canvasPane);
        BorderPane.setMargin(canvasPane, new Insets(25,25,25,25));
//      GraphicsContext gc = middle.getGraphicsContext2D();

        TrackObject[][] layout = new TrackObject[1][11];
//        layout = [3][10]
      TrackLayout tl = new TrackLayout(layout);


      Train t2 = new Train( "blah", middle, layout[0][0]);
      Thread t1 = new Thread( t2);

      t1.start();


        primaryStage.setScene(new Scene(root, 1100, 400));
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }


    }

