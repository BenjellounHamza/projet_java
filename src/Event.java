import java.util.*;

public abstract class Event implements Comparable
{
  protected long date;
  public Event(long d)
  {
    date = d;
  }
  public long getDate()
  {
    return date;
  }
  public int compareTo(Object other)
  {
    if(this.getDate() > ((Event)other).getDate())
    {
      return 1;
    }
    else if(this.getDate() == ((Event)other).getDate())
    {
      return 0;
    }
    else
    {
      return -1;
    }
  }
  public abstract void execute();
  public abstract void restart();

}
