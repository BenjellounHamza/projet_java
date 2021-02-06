import gui.Simulable;
import gui.GUISimulator;
/*
  la classe JeuImmigration est une grille avec n etats.
  chaque case change d'etat selon les critères decrits dans la methode execute.
*/
class JeuImmigration extends Grille
{
  public JeuImmigration(long d, EventManager manage, int taille_x, int taille_y, int nbr_x, int nbr_y, int nbr_etat, GUISimulator win)
  {
    super(d, manage,taille_x, taille_y, nbr_x, nbr_y, nbr_etat, win);
  }
  public JeuImmigration dup()
  {
    /*
      une duplication de la grille actuelle.
    */
    JeuImmigration dup_grille = new JeuImmigration(this.date + 1, manager, taille_case_x, taille_case_x, nbr_case_x, nbr_case_y, nbr_etat, window);
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
        int neighbors[];
        int compteur = 0;
        neighbors = grille_prec.voisinage(i, j);
        if(2 < neighbors[(grille[i][j] + 1)%nbr_etat])
        {
          grille[i][j] = (grille[i][j]+1)%nbr_etat;
        }
      }
    }
    this.dessiner();
    date += 1;
    manager.addEvent(this);
  }
}
