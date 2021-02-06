import gui.*;
import java.awt.Color;

public class TestBallsSimulator {
  public static void main(String [] args){
    GUISimulator window = new GUISimulator(500, 500, Color.BLACK);
    EventManager manager = new EventManager();
    manager.addEvent(new BallsSimulator(1, manager, window));
    window.setSimulable(manager);
  }
}
