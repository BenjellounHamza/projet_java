import gui.Simulable;
import java.awt.Color;
import gui.GUISimulator;

class TestJeuImmigration
{
  public static void main(String[] args)
  {
    GUISimulator window = new GUISimulator(500, 500, Color.BLACK);
    EventManager manager = new EventManager();
    manager.addEvent(new JeuImmigration(0, manager, 20, 20, 40, 40, 4, window));
    window.setSimulable(manager);
  }
}
