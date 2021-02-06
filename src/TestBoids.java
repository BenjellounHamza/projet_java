import gui.*;
import java.awt.Color;

public class TestBoids {
  public static void main(String [] args){
    GUISimulator window = new GUISimulator(500, 500, Color.WHITE);
    EventManager manager = new EventManager();
    manager.addEvent(new Boids(0, manager, window));
    window.setSimulable(manager);
  }
}
