import gui.Simulable;
import gui.GUISimulator;

public class JeuxDeLaVie extends Grille
{
  /*
    la classe JeuxDeLaVie est une grille avec 2 états.
    chaque case change d'état selon les critères decrits dans la methode execute.
  */
  public JeuxDeLaVie(long d, EventManager manage, int taille_x, int taille_y, int nbr_x, int nbr_y, GUISimulator win)
  {
    super(d, manage, taille_x, taille_y, nbr_x, nbr_y, 2, win);
  }
  public JeuxDeLaVie dup()
  {
    /*
      une duplication de la grille actuelle.
    */
    JeuxDeLaVie dup_grille = new JeuxDeLaVie(this.date + 1, manager, taille_case_x, taille_case_x, nbr_case_x, nbr_case_y, window);
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
    JeuxDeLaVie grille_prec = dup();
    for(int i = 0; i < nbr_case_x; i++)
    {
      for(int j = 0; j < nbr_case_y; j++)
      {
        int neighbors[];
        neighbors = grille_prec.voisinage(i, j);
        if(grille_prec.grille[i][j] == 1)
        {
          if(neighbors[1] != 2 && neighbors[1] != 3)
          {
            grille[i][j] = 0;
          }
        }
        else
        {
          if(neighbors[1] == 3)
          {
            grille[i][j] = 1;
          }
        }
      }
    }
    this.dessiner();
    date += 1;
    manager.addEvent(this);
  }
}
