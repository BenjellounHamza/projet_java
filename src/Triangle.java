import java.awt.*;
import java.lang.*;
import gui.*;
/*
  Dans notre classe Triangle on represente le triangle par un sommet et un angle.
  En effet avec un sommet et un angle, on peut bien déterminer l'emplacement du triangle dans un espace 2D.
  (l'angle Angle est entre le vecteur OSommet et le vecteur directeur de l'axe des abscisses.)
*/
public class Triangle extends java.lang.Object implements GraphicalElement
{
  Point Sommet;
  double Angle;
  Color Color;
  Color fillColor;
  Triangle(Point s,double a, Color c, Color fc)
  {
    Sommet = s;
    Angle = a;
    Color = c;
    fillColor = fc;
  }
  void rotation(int alpha)
  {
    Angle = (Angle + alpha)%(2*Math.PI);
  }
  void Translate(Point vect_trans)
  {
    Sommet.x += vect_trans.x;
    Sommet.y += vect_trans.y;
  }
  public void paint(Graphics2D g2d)
  {
    int base = 16;
    int hauteur = 16;
    int tab_x[] = new int[4];
    int tab_y[] = new int[4];
    tab_x[0] = Sommet.x;
    tab_y[0] = Sommet.y;
    tab_x[1] = (int)(Sommet.x - (base/2) * Math.sin(Angle) - hauteur * Math.cos(Angle));
    tab_y[1] = (int)(Sommet.y + (base/2) * Math.cos(Angle) - hauteur * Math.sin(Angle));
    tab_x[3] = (int)(Sommet.x + (base/2) * Math.sin(Angle) - hauteur * Math.cos(Angle));
    tab_y[3] = (int)(Sommet.y - (base/2) * Math.cos(Angle) - hauteur * Math.sin(Angle));
    tab_x[2] = (int)(tab_x[0] + tab_x[1] + tab_x[3])/3;
    tab_y[2] = (int)(tab_y[0] + tab_y[1] + tab_y[3])/3;
    Polygon p = new Polygon(tab_x, tab_y, 4);
    g2d.setColor(fillColor);
    g2d.fillPolygon(p);
    g2d.setColor(Color);
    g2d.drawPolygon(p);
  }
}
