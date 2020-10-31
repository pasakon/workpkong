package controller;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Food;
import model.Snake;
import view.Platformm;


public class Launcher extends Application {

    public static Stage primaryStage;

    @Override
 public void start(Stage primaryStage) {
         Launcher.primaryStage =primaryStage;
         Platformm platformm = new Platformm();
         Snake snake = new Snake(new Point2D(platformm.WIDTH/2,platformm.HEIGHT/2));
         Food food = new Food();
         GameLoop gameLoop = new GameLoop(platformm,snake,food);
         Scene scene = new Scene(platformm,platformm.WIDTH*platformm.TILE_SIZE,platformm.HEIGHT * platformm.TILE_SIZE);
         scene.setOnKeyPressed(event-> platformm.setKey(event.getCode()));
         scene.setOnKeyReleased(event -> platformm.setKey(null));
         Launcher.primaryStage.setTitle("Snake");
         Launcher.primaryStage.setScene(scene);
         Launcher.primaryStage.setResizable(false);
         Launcher.primaryStage.show();

     (new Thread(gameLoop)).start();
 }




 }
