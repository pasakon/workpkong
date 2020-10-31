package model;

import javafx.geometry.Point2D;
import view.Platformm;

import java.util.ArrayList;
import java.util.List;

public class Snake {
 private Direction direction;
 private Point2D head;
 private Point2D prev_tail;
 private int score;
 private ArrayList<Point2D> body;

 public Snake(Point2D position) {
         direction = Direction.DOWN;
         body = new ArrayList<>();
         this.head = position;
         this.body.add(this.head);
 }
    public int getScore() {
        return score;
    }

    public boolean isDead() {
         boolean isOutOfBound = head.getX() < 0 || head.getY() < 0 || head.getX() > Platformm.WIDTH || head.getY() > Platformm.HEIGHT;
         boolean isHitBody = body.lastIndexOf(head) > 0;


         return isOutOfBound || isHitBody ;
 }

 public void update() {
         head = head.add(direction.current);
         prev_tail = body.remove(body.size() - 1);
         body.add(0, head);

         }
 public void setCurrentDirection(Direction direction) { this.direction = direction; }
 public Direction getCurrentDirection() { return this.direction; }
 public Point2D getHead() { return head; }
 public boolean isCollidingWith(Food food) { return head.equals(food.getPosition()); }
 public boolean isCollidingWithsp(Food food) { return head.equals(food.getspecailPosition()); }
 public void grow() { body.add(prev_tail);
     score++;
 }
    public void growsp() { body.add(prev_tail);
    for(int i=1;i<=5;i++){
        score++;
    }
    }

 public int getLength() { return body.size(); }
 public List<Point2D> getBody() { return body; }


}