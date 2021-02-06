import gui.Simulable;
import java.awt.Color;
import gui.GUISimulator;

class TestJeuDeLaVie
{
  public static void main(String[] args)
  {
    GUISimulator window = new GUISimulator(500, 500, Color.BLACK);
    EventManager manager = new EventManager();
    manager.addEvent(new JeuxDeLaVie(0, manager, 20, 20, 40, 40, window));
    window.setSimulable(manager);
  }
}
