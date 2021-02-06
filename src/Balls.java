import java.awt.*;
import java.util.LinkedList;

class Balls
{
  private LinkedList<Point> balles;

  private Point balls_init_state[];


  /*Le constructeur de Balls*/
  public Balls(int N, int min, int max)
  {
      balles = new LinkedList<Point>();
      balls_init_state = new Point[N];
      /*Let's now add some balls*/
      for (int i = 0; i < N; i++)
      {
        int x = (int) (Math.random() * (max - min)) + min;
        int y = (int) (Math.random() * (max - min)) + min;
        balles.add(new Point(x, y));
        balls_init_state[i] = new Point(x, y);
      }
    }
  /*Methode pour translater toutes les balles*/
  public void translate_balls(int dx, int dy){
    for (Point ball : balles)
    {
        ball.translate(dx, dy);
    }
  }

  /*Methode pour réinitialiser la position des balles*/
  public void reInit()
  {
    for (int i = 0; i < balles.size(); i++)
    {
        balles.get(i).setLocation(this.balls_init_state[i].x, this.balls_init_state[i].y);
    }
  }

  /*Accesseur de Balls*/
  public LinkedList<Point> getBallsArray()
  {
      return balles;
  }

  /*Focntion qui renvoie la taille de balles*/
  public int getBallsArraySize() {
      return this.balles.size();
  }

  @Override
  public String toString()
  {
      StringBuffer sb = new StringBuffer();
      for (Point ball : balles)
      {
          sb.append("Ball of coordinates : (" + ball.x + "," + ball.y + ")");
          sb.append("\n");
      }
      return sb.toString();
  }
}
