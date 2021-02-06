import gui.Simulable;
import gui.GUISimulator;
import java.awt.Point;
import java.util.LinkedList;
/*
  la classe Schelling est une grille avec n etats.
  chaque case change d'etat selon les critères decrits dans la methode execute.
*/
class Schelling extends Grille
{
  int seuil;
  LinkedList<Point> places_vacantes;

  public Schelling(long d, EventManager manage, int taille_x, int taille_y, int nbr_x, int nbr_y, int nbr_etat, GUISimulator win, int s)
  {
    super(d, manage, taille_x, taille_y, nbr_x, nbr_y, nbr_etat, win);
    seuil = s;
    places_vacantes = placeVide();
    if(places_vacantes.size() < nbr_x*nbr_y/nbr_etat)
    {
      int m = (int)(nbr_x*nbr_y/nbr_etat - places_vacantes.size());
      int compteur = 0;
      for(int i = 0; i < nbr_case_x; i++)
      {
        for(int j = 0; j < nbr_case_y; j++)
        {
          if(grille[i][j] != 0)
          {
            grille[i][j] = 0;
            compteur += 1;
          }
          if(compteur == m)
          {
            break;
          }
        }
        if(compteur == m)
        {
          break;
        }
      }
    }
  }
  public Schelling dup()
  {
    /*
      une duplication de la grille actuelle.
    */
    Schelling dup_grille = new Schelling(this.date + 1, manager, taille_case_x, taille_case_x, nbr_case_x, nbr_case_y, nbr_etat, window, seuil);
    for(int i = 0; i < nbr_case_x; i++)
    {
      for(int j = 0; j < nbr_case_y; j++)
      {
        dup_grille.grille[i][j] = grille[i][j];
      }
    }
    return dup_grille;
  }
  public void execute()
  {
    /*
      cette methode permet a chaque case de changer son etat selon son voisinage,
      et cree un nouveau evenement avec la nouvelle grille.
    */
    window.reset();
    Grille grille_prec = dup();
    for(int i = 0; i < nbr_case_x; i++)
    {
      for(int j = 0; j < nbr_case_y; j++)
      {
        int neighbors[] = grille_prec.voisinage(i, j);
        int compteur = 0;
        if(grille[i][j] != 0)
        {
        for(int k = 0; k < nbr_etat; k++)
        {
          if(k != grille[i][j])
          {
            compteur += neighbors[k];
          }
        }
        }
        if(seuil < compteur)
        {
          int n = (int) (Math.random() * places_vacantes.size());
          grille[places_vacantes.get(n).x][places_vacantes.get(n).y] = grille[i][j];
          grille[i][j] = 0;
          places_vacantes.remove(n);
          places_vacantes.add(new Point(i, j));
        }
      }
    }
    this.dessiner();
    date += 1;
    manager.addEvent(this);
  }
}
