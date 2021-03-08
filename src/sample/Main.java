package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Main extends Application {
    static int sizeX = 16;
    static int sizeY = 16;
    static boolean gameOver = false;


    public void start(Stage theStage) throws Exception {
        GridPane gp = new GridPane();
        GridPane top = new GridPane();
        BorderPane pane = new BorderPane();
        top.setPadding(new Insets(10, 10, 10, 10));
        top.setAlignment(Pos.CENTER);

        pane.setCenter(gp);


        CellManager manager = new CellManager(sizeX, sizeY);
        Face face = new Face();
        top.add(face, 1, 1);
        pane.setTop(top);
        manager.placeMines();

        for (int row = 0; row < sizeX; row++) {
            for (int col = 0; col < sizeY; col++) {

                Cell b = manager.returnCell(row, col);

                int finalRow = row;
                int finalCol = col;
                face.setOnMouseClicked(e -> {
                    MouseButton button = e.getButton();
                    if (button.equals(MouseButton.PRIMARY)) {
                        face.reset(manager);
                        gameOver = false;
                    }
                });

                b.setOnMouseClicked(e -> {
                    MouseButton button = e.getButton();
                    if (!gameOver) {
                        if (button.equals(MouseButton.PRIMARY)) {
                            if (manager.isBomb(finalRow, finalCol) && !(manager.returnCell(finalRow, finalCol).getState() == State.FLAGGED) && manager.firstClick) {
                                manager.revealBombs();
                                manager.setCell(finalRow, finalCol, State.HITMINE);
                                face.lose();
                                gameOver = true;
                            } else {
                                System.out.println("Button was " + button);
                                manager.primaryClick(finalRow, finalCol);
                                if (manager.checkWin()) {
                                    face.win();
                                    gameOver = true;
                                }

                            }
                        } else if (button.equals(MouseButton.SECONDARY)) {
                            System.out.println("Button was " + button);
                            manager.secondaryClick(finalRow, finalCol);
                            if (manager.checkWin()) {
                                face.win();
                                gameOver = true;
                            }
                        }
                    }


                });


                gp.add(b, col, row);


            }
        }

        //theStage.setScene(new Scene(gp));
        theStage.setScene(new Scene(pane));
        theStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}

class OKHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent e) {
        System.out.println("Ok Clicked");

    }
}

class CustomPane extends StackPane {
    public CustomPane(String title) {
        getChildren().add(new Label(title));
        setStyle("-fx-border-color:grey");
        setPadding(new Insets(12, 12, 12, 12));

    }
}
