package model;

import javafx.geometry.Point2D;
import view.Platformm;

import java.util.Random;

public class Food {

 private Point2D position;
 private  Point2D specailposition;
 private Random rn;
 private Random fn;



    public Food(Point2D position) {
         this.rn = new Random();
         this.fn= new Random();
         this.position = position;
         this.specailposition =specailposition;
         }
 public Food() {
         this.rn = new Random();
         this.fn = new Random();
         respawn();
            respawnsp();
         }
 public void respawn() {

         Point2D prev_position = this.position;
         do {

             this.position = new Point2D(rn.nextInt(Platformm.WIDTH), rn.nextInt(Platformm.HEIGHT));
             }
         while(prev_position == this.position);

    }
    public  void  respawnsp(){
        Point2D prev_specailposition = this.specailposition;
        do{
            this.specailposition = new Point2D(fn.nextInt(Platformm.WIDTH), fn.nextInt(Platformm.HEIGHT));
        }  while(prev_specailposition  == this.specailposition);
    }

 public Point2D getPosition() { return position; }

 public  Point2D getspecailPosition() { return specailposition; }
 }
