package controller;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.stage.Popup;
import model.Direction;
import model.Food;
import model.Snake;
import view.Platformm;
import view.Score;

import java.util.ArrayList;

public class GameLoop implements Runnable {
    private Platformm platform;
    private Snake snake;
    private Food food;
    private float interval = 1000.0f / 10;
    private boolean running;


    public GameLoop(Platformm platformm, Snake snake, Food food) {
        this.snake = snake;
         this.platform = platformm;
         this.food = food;
         running = true;
 }
 private void update() {
         KeyCode cur_key = platform.getKey();
         Direction cur_direction = snake.getCurrentDirection();
         if (cur_key == KeyCode.UP && cur_direction != Direction.DOWN)
             snake.setCurrentDirection(Direction.UP);
         else if (cur_key == KeyCode.DOWN && cur_direction != Direction.UP)
             snake.setCurrentDirection(Direction.DOWN);
         else if (cur_key == KeyCode.LEFT && cur_direction != Direction.RIGHT)
             snake.setCurrentDirection(Direction.LEFT);
         else if (cur_key == KeyCode.RIGHT && cur_direction != Direction.LEFT)
            snake.setCurrentDirection(Direction.RIGHT);
         snake.update();

 }

 private void checkCollision() {
     if (snake.isCollidingWith(food)) {
         snake.grow();
         food.respawn();
     }
     if(snake.isCollidingWithsp(food)) {
         snake.growsp();
         food.respawnsp();
     }
     if (snake.isDead()) {

         running = false;

     }
 }
    public  void showDead(){
        Popup popup = new Popup();
        Label label = new Label("Game Over");
        label.setMinWidth(100);
        label.setMinHeight(100);
        label.setFont(Font.font(20));
        popup.getContent().add(label);
        popup.show(Launcher.primaryStage);
    }
    private void updateScore(ArrayList<Score> scoreList) {

        javafx.application.Platform.runLater(() -> {
            for (int i=0 ; i<scoreList.size() ; i++) {
                scoreList.get(i).setPoint(snake.getScore());
            }
        });
    }
 private void redraw() { platform.render(snake,food); }
 @Override
 public void run() {
     while (running) {
             update();
             checkCollision();
             redraw();
            updateScore(platform.getScoreList());
             try {
                 Thread.sleep((long)interval);
                 } catch (InterruptedException e) {
                 e.printStackTrace();
                 }
     }
     Platform.runLater( () -> {
        showDead();
         //System.exit(0);
     });


    }




    public Platformm getPlatform() {
        return platform;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Snake getSnake() {
        return snake;
    }
}