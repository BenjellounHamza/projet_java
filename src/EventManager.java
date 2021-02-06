import java.util.PriorityQueue;
import gui.GUISimulator;
import gui.Simulable;

public class EventManager implements Simulable
{
  PriorityQueue<Event> fileattente;
  private long currentDate;
  public EventManager()
  {
    currentDate = 0;
    fileattente = new PriorityQueue<Event>();
  }
  public void next()
  {
    while(!isFinished() && fileattente.peek().getDate() == currentDate)
    {
      fileattente.poll().execute();
    }
    currentDate += 1;
  }
  void addEvent(Event e)
  {
    fileattente.offer(e);
  }
  boolean isFinished()
  {
    if(fileattente.size() == 0)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  public void restart()
  {
    fileattente.peek().restart();
  }
}
