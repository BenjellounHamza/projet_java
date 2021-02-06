import gui.*;
import java.awt.*;
import java.util.*;

public class BallsSimulator extends Event{
  EventManager manager;
  private Balls balls_pit;
  private GUISimulator gui;
  private int[] dx_vector;
  private int[] dy_vector;


 /*Constructeur*/
  public BallsSimulator(long d, EventManager manage, GUISimulator window)
  {
    super(d);
    manager = manage;
    this.balls_pit = new Balls(10, 0, 500);
    this.gui = window;

    Random random = new Random();
    /*Les valeurs des deux vecteurs sont soit -10 soit 10 pour que les balles aient différentes trajectoires au début.*/
    dx_vector = new int[balls_pit.getBallsArray().size()];
    dy_vector = new int[balls_pit.getBallsArray().size()];
    for (int j = 0; j < balls_pit.getBallsArray().size(); j++)
    {
      this.dx_vector[j] = (random.nextInt() % 2 == 0) ? 10 : -10;
      this.dy_vector[j] = (random.nextInt() % 2 == 0) ? 10 : -10;
    }
  }

  /*Accesseur de Balls_pit*/
  public Balls getBalls()
  {
    return balls_pit;
  }

  /*Fonction qui fait rebondir les balles sur les bords de la fenêtre*/
  public void transWithBounce(Point ball, int ball_index){
      if (ball.x + this.dx_vector[ball_index] < 0 )
      {
        this.dx_vector[ball_index] = 10;
      }
      else if (ball.x + this.dx_vector[ball_index] > this.gui.getPanelWidth())
      {
        this.dx_vector[ball_index] = -10;
      }
      else if (ball.y + this.dy_vector[ball_index] < 0 ) {
        this.dy_vector[ball_index] = 10;
      }
      else if (ball.y + this.dy_vector[ball_index] > this.gui.getPanelHeight()){
        this.dy_vector[ball_index] = -10;
      }
      ball.translate(this.dx_vector[ball_index], this.dy_vector[ball_index]);
  }

  /*Pour le bouton suivant, en utilisant l'event manager*/
  @Override
  public void execute(){
    this.gui.reset();
    int i=0;
    for (Point ball : this.balls_pit.getBallsArray()){
      this.transWithBounce(ball, i);
      i++;
    }
    this.display_balls();
    date += 1;
    manager.addEvent(this);
  }

  /*Pour le bouton Début*/
  @Override
  public void restart(){
    this.gui.reset();
    balls_pit.reInit();
    this.display_balls();
  }

  /*Dessine les balles sur la fenêtre*/
  private void display_balls(){
    for (int i=0; i < balls_pit.getBallsArray().size(); i++){
      this.gui.addGraphicalElement(
        new Oval(balls_pit.getBallsArray().get(i).x, balls_pit.getBallsArray().get(i).y, Color.RED, Color.RED, 10));
      }
    }
  }
