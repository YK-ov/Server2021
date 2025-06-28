package pl.umcs.oop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Server server = new Server(5000);
        server.start();

        ChatClientGUI GUI = new ChatClientGUI(500,500);

        BorderPane root = new BorderPane();
        root.setCenter(GUI.getCanvas()); //with getters
        root.setBottom(GUI.getChatUI());

        /*
        StackPane root = new StackPane();  -- for StackPane (different kind of layout, without setTop, setBottom etc)
        root.getChildren().add(GUI);
         */

        Scene scene = new Scene(root, 500, 500);

        stage.setScene(scene);
        stage.setTitle("Chat Client");

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
