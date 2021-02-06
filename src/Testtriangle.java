import java.awt.*;
import java.lang.*;
import java.awt.Color;
import gui.GUISimulator;

class Testtriangle
{
  public static void main(String [] args)
  {
    GUISimulator window = new GUISimulator(500, 500, Color.WHITE);
    window.addGraphicalElement(new Triangle(new Point(90, 90), -3*Math.PI/4, Color.RED, Color.RED));
    //window.addGraphicalElement(new Triangle(new Point(90, 90), 3*Math.PI/4, Color.RED, Color.RED));

  }
}
