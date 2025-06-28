package pl.umcs.oop;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.regex.Pattern;

public class ChatClientGUI extends StackPane {

    private Canvas canvas;
    private GraphicsContext gc;

    private TextArea chatArea;
    private TextField inputFieldColour;
    private TextField inputFieldCoordinates;
    private Button sendColourButton;
    private Button sendCoordinatesButton;
    private String sixDigits = "000000";
    private int xOne, xTwo;
    private int yOne, yTwo;
    private VBox chatUI;

    public ChatClientGUI(double width, double height) {
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        drawBackground();

        chatArea = new TextArea();
        chatArea.setEditable(false);

        inputFieldColour = new TextField();
        inputFieldCoordinates = new TextField();

        sendColourButton = new Button("Send colour");
        sendCoordinatesButton = new Button("Send coordinates");

        sendColourButton.setOnAction(e -> sendColour());
        inputFieldColour.setOnAction(e -> sendColour());

        sendCoordinatesButton.setOnAction(e -> sendCoordinates());
        inputFieldCoordinates.setOnAction(e -> sendCoordinates());

        chatUI = new VBox(10, chatArea, inputFieldColour, sendColourButton, inputFieldCoordinates, sendCoordinatesButton);


        //this.getChildren().addAll(canvas, chatUI); -- for StackPane (not BorderPane)
    }

    private void drawBackground() {
        gc.setFill(Paint.valueOf("white"));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void drawLine(){
            if (!sixDigits.equals("000000")) {
                int r = Integer.parseInt(sixDigits.substring(0, 2), 16);
                int g = Integer.parseInt(sixDigits.substring(2, 4), 16);
                int b = Integer.parseInt(sixDigits.substring(4, 6), 16);
                Paint paint = Color.rgb(r,g,b);
                //gc.setFill(paint);
                gc.setStroke(paint);
                gc.strokeLine(xOne, yOne, xTwo, yTwo);
            }
    }

    private void sendColour() {
        String msg = inputFieldColour.getText().trim();
        if (!msg.isEmpty()) {
            //chatArea.appendText("Me: " + msg + "\n"); -- send msg
            sixDigits = msg.toLowerCase();
            //inputFieldColour.clear(); -- clear msg
        }
    }

    private void sendCoordinates() {
        String msg = inputFieldCoordinates.getText().trim();
            if (!msg.isEmpty() && msg.matches("([0-9]+\\s){3}[0-9]+")) {
                //chatArea.appendText("Me: " + msg + "\n"); -- to send messages in chat
                //inputFieldCoordinates.clear();
                String[] values = msg.split(" ");
                xOne = Integer.parseInt(values[0]);
                yOne = Integer.parseInt(values[1]);
                xTwo = Integer.parseInt(values[2]);
                yTwo = Integer.parseInt(values[3]);


                drawLine();
            }
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public VBox getChatUI() {
        return chatUI;
    }

}
