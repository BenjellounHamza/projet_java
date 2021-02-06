import gui.Simulable;
import java.awt.Color;
import gui.GUISimulator;

class TestSchelling
{
  public static void main(String[] args)
  {
    GUISimulator window = new GUISimulator(500, 500, Color.BLACK);
    EventManager manager = new EventManager();
    manager.addEvent(new Schelling(0, manager, 10, 10, 80, 80, 5, window, 4));
    window.setSimulable(manager);
  }
}
