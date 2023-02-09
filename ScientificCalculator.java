package test;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ScientificCalculator extends Application {
    private TextField display = new TextField();
    private double firstNumber;
    private String operator = "";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Add the display to the top row
        GridPane.setConstraints(display, 0, 0, 4, 1);
        display.setEditable(false);
        grid.getChildren().add(display);

        // Add the number buttons
        String[] buttons = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"};
        int row = 1;
        int col = 0;
        for (String button : buttons) {
            Button btn = new Button(button);
            btn.setOnAction(e -> handleButtonClick(btn.getText()));
            GridPane.setConstraints(btn, col, row);
            grid.getChildren().add(btn);
            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }

        // Create the scene and show the stage
        Scene scene = new Scene(grid, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Scientific Calculator");
        primaryStage.show();
    }

    private void handleButtonClick(String text) {
        switch (text) {
            case "+":
            case "-":
            case "*":
            case "/":
                firstNumber = Double.parseDouble(display.getText());
                operator = text;
                display.setText("");
                break;
            case "=":
                double secondNumber = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+":
                        display.setText(String.valueOf(firstNumber + secondNumber));
                        break;
                    case "-":
                        display.setText(String.valueOf(firstNumber - secondNumber));
                        break;
                    case "*":
                        display.setText(String.valueOf(firstNumber * secondNumber));
                        break;
                    case "/":
                        display.setText(String.valueOf(firstNumber / secondNumber));
                        break;
                }
                break;
            default:
                display.setText(display.getText() + text);
                break;
        }
    }
}