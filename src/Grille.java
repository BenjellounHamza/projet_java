import gui.Simulable;
import java.awt.Color;
import gui.GUISimulator;
import gui.Rectangle;
import java.awt.Point;
import java.util.LinkedList;
/*
  la classe Grille est la classe mére utilisée par les 3 jeux : (jeu de la vie, jeu d'immigration et le shelling)
  et qui étend la classe Event, elle prend comme attributs:
  manager : c'est celui qui gére les differents evenements.
  nbr_case_x : nombre de lignes dans la grille.
  nbr_case_y: nombre de colonnes dans la grille.
  taille_case_x : la distance entre chaque ligne dans la grille.
  taille_case_y : la distance entre chaque colonne dans la grille.
  nbr_etat: le nombre d'etat utilisé dans la grille.
  grille : c'est un tableau bidimensionnel ou on enregistre l'etat de chaque case;
  grille_initial: c'est un tableau bidimensionnel ou on enregistre l'etat initial de chaque case.
*/

public abstract class Grille extends Event
{
  EventManager manager;
  int taille_case_x;
  int taille_case_y;
  int nbr_case_x;
  int nbr_case_y;
  int nbr_etat;
  int grille[][];
  int grille_initial[][];
  GUISimulator window;
  public Grille(long d, EventManager manag, int taille_x, int taille_y, int nbr_x, int nbr_y, int nbr_et, GUISimulator win)
  {
    super(d);
    manager = manag;
    window = win;
    grille = new int[nbr_x][nbr_y];
    grille_initial = new int[nbr_x][nbr_y];
    nbr_etat = nbr_et;
    taille_case_x = taille_x;
    taille_case_y = taille_y;
    nbr_case_x = nbr_x;
    nbr_case_y = nbr_y;
    for(int i = 0; i < nbr_x; i++)
    {
      for(int j = 0; j < nbr_x; j++)
      {
        grille[i][j] = (int) (Math.random() * nbr_et);
        grille_initial[i][j] = grille[i][j];
      }
    }
  }
  void dessiner()
  {
    /*
      La méthode dessiner nous permet de dessiner chaque case avec une couleur calculée par une formule qui parait bizarre.
      En effet cette formule sert seulement à donner à chaque état une couleur differente de tous les autres états
    */
    int r;
    int g;
    int b;
    int k;
    for(int i = 0; i < nbr_case_x; i++)
    {
      for(int j = 0; j < nbr_case_y; j++)
      {
        k = grille[i][j];
        if(k != 0 && k != 1)
        {
          r = (k * 100 + 200*nbr_case_y + 56)% 255;
          g = (5*(k*200 + 400 * nbr_case_x + 56))% 255;
          b = (k*500 + 600 + taille_case_x + 56) % 255;
        }
        else if(k == 0)
        {
          r = 255;
          g = 255;
          b = 255;
        }
        else
        {
          r = 0;
          g = 0;
          b = 0;
        }
        window.addGraphicalElement(new Rectangle(20+i*taille_case_x, 20 + j*taille_case_y , Color.LIGHT_GRAY, new Color(r, g, b), taille_case_x, taille_case_y));
      }
    }
  }
  public void restart()
  {
    /*
      Cette méthode remet la grille à son état inital.
    */
    window.reset();
    for(int i = 0; i < nbr_case_x; i++)
    {
      for(int j = 0; j < nbr_case_y; j++)
      {
        grille[i][j] = grille_initial[i][j];
      }
    }
    dessiner();
  }
  public int[] voisinage(int x, int y)
  {
    /*
      Cette méthode calcule le nombre d'apparitions de chaque état des 'nbr_etat' de la grille dans les 8 voisins de la case (x, y).
    */
    int neighbors[] = new int[nbr_etat];
    for(int i = 0; i < nbr_etat; i++)
    {
      neighbors[i] = 0;
    }
    for(int i = (x - 1 < 0 ? 0: x - 1); i <= (x + 1 == nbr_case_x ? x: x + 1); i++)
    {
      for(int j = (y - 1 < 0 ? 0: y - 1); j <= (y + 1 == nbr_case_y ? y: y + 1); j++)
      {
        if(i != x || j != y)
        {
          neighbors[grille[i][j]] += 1;
        }
      }
    }
    return neighbors;
  }
  public LinkedList<Point> placeVide()
  {
    /*
      Cette methode retounre une liste avec les cases vides dans la grille c'est à dire le nombre de cases qui ont 0 comme état.
    */
    LinkedList<Point> place_vacante = new LinkedList<Point>();
    for(int i = 0; i < nbr_case_x; i++)
    {
      for(int j = 0; j < nbr_case_y; j++)
      {
        if(grille[i][j] == 0)
        {
          place_vacante.add(new Point(i, j));
        }
      }
    }
    return place_vacante;
  }
}
