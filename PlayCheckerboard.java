package com.example.playcheckerboard;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

public class PlayCheckerboard extends Application {

    private GraphicsContext g;
    private int selectedRow, selectedCol;


    public void start(Stage stage) {
       Canvas canvas = new Canvas(406,406);
       g = canvas.getGraphicsContext2D();
       selectedRow = -1;
       draw(g);
       Pane root = new Pane(canvas);
       Scene scene = new Scene(root);

       canvas.setOnMousePressed(this::highlightBox);
       stage.setScene(scene);
       stage.setTitle("Select Checkerboard");
       stage.setResizable(false);
       stage.show();
    }


    public void highlightBox(MouseEvent evt){
        int newBoxX = (int)(evt.getX()/50);
        int newBoxY = (int)(evt.getY()/50);

        if(selectedRow == newBoxX && selectedCol == newBoxY) {
            selectedRow = -1;
        }
        else {
            selectedRow = newBoxX;
            selectedCol = newBoxY;
        }


        if(selectedRow >= 0) {
            draw(g);
            g.setLineWidth(4);
            g.setStroke(Color.CYAN);
            g.strokeRect(1+newBoxX*50,1+newBoxY*50, 52, 52);
        }
        else {
            draw(g);
        }

    }

    public void draw(GraphicsContext g) {
        g.setFill(Color.GRAY);
        g.fillRect(0,0,406,406);
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                if(row % 2 == col % 2)
                    g.setFill(Color.RED);
                else
                    g.setFill(Color.BLACK);
                g.fillRect(3+col*50,3+row*50,50,50);
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}