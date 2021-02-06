import gui.*;
import java.awt.Color;
import java.awt.Point;
import java.util.*;
import java.lang.*;


class Boids extends Event
{
    /*//////////////////Les attributs de la classe///////////////////////////*/
    GUISimulator gui;
    Bird[] birds;
    Bird[] birds_init;
    Insect[] insects;
    Insect[] insects_init;
    int nombre_birds;
    int nombre_insectes;
    int nombre_insectes_initial;
    int xRes;
    int yRes;
    EventManager manager;
    /*///////////////////////////////////////////////////////////////////////*/


    public Boids(long d, EventManager manag, GUISimulator window)
    {
        super(d);
        this.manager = manag;
        this.gui = window;
        nombre_birds = 20;
        nombre_insectes = 60;
        nombre_insectes_initial = nombre_insectes;
        xRes = window.getPanelWidth();
        yRes = window.getPanelHeight();
        birds = new Bird[this.nombre_birds];
        birds_init = new Bird[this.nombre_birds];
        insects = new Insect[this.nombre_insectes];
        insects_init = new Insect[this.nombre_insectes];
        Random rand = new Random();

        int randx = 0;
        int randy = 0;
        for (int i = 0; i < this.nombre_birds; i++)
        {
            randx = rand.nextInt(xRes);
            randy = rand.nextInt(yRes);
            birds[i] = new Bird(new Vector(randx,randy), new Vector(0,0));
            birds_init[i] =  new Bird(new Vector(randx,randy), new Vector(0,0));
        }
        for (int i = 0; i < this.nombre_insectes; i++)
        {
            randx = rand.nextInt(xRes);
            randy = rand.nextInt(yRes);
            insects[i] = new Insect(new Vector(randx,randy), new Vector(0,0));
            insects_init[i] =  new Insect(new Vector(randx,randy), new Vector(0,0));
        }
    }

    /*Fonction qui retourne une liste avec les indices des voisins les plus
    proches selon une certaine distance*/
    public ArrayList<Integer> neighbors_indexes(int distance, Animal[] animals,int N, int i){
          ArrayList<Integer> neighbors_indexes = new ArrayList<Integer>();
          for (int j = 0; j < N; j++){
            if (animals[i] != animals[j]){
            if ((int)animals[i].position.distanceTo(animals[j].position) < distance){
              neighbors_indexes.add(j);
            }
          }
          }
        return neighbors_indexes;
    }

    /*Fonction qui fait déplacer les oiseaux*/
    public void move_birds(int distance, double cohesionCoefficient, int alignmentCoefficient, double separationCoefficient)
    {
            for (int i = 0; i < this.nombre_birds; i++)
            {
               /*On détermine dans cette partie les voisins les plus proches selon une distance*/
                ArrayList<Integer> neighbors_indexes = new ArrayList<Integer>();
                neighbors_indexes = neighbors_indexes(distance, this.birds, this.nombre_birds, i);

                Bird[] neighbors = new Bird[neighbors_indexes.size()];

                for (int k = 0; k < neighbors_indexes.size(); k++){
                    neighbors[k] = new Bird(new Vector(this.birds[neighbors_indexes.get(k)].position.coord), new Vector(this.birds[neighbors_indexes.get(k)].velocity.coord));
                }
                if (neighbors_indexes.size() != 0){
                birds[i].updateVelocityBirds(neighbors,insects, xRes, yRes, cohesionCoefficient, alignmentCoefficient, separationCoefficient);
                birds[i].updatePosition();
              }
            }

    }

    /*Fonction qui fait déplacer les insectes*/
    public void move_insects(int distance, double cohesionCoefficient, int alignmentCoefficient, double separationCoefficient)
    {
            for (int i = 0; i < this.nombre_insectes; i++)
            {
              /*On détermine dans cette partie les voisins les plus proches selon une distance*/
                ArrayList<Integer> neighbors_indexes = new ArrayList<Integer>();
                neighbors_indexes = neighbors_indexes(distance, this.insects, this.nombre_insectes, i);

                Insect[] neighbors = new Insect[neighbors_indexes.size()];

                for (int k = 0; k < neighbors_indexes.size(); k++){
                    neighbors[k] = new Insect(new Vector(this.insects[neighbors_indexes.get(k)].position.coord), new Vector(this.insects[neighbors_indexes.get(k)].velocity.coord));
                }
                if (neighbors_indexes.size() != 0){
                insects[i].updateVelocityInsects(birds, neighbors, xRes, yRes, cohesionCoefficient, alignmentCoefficient, separationCoefficient);
                insects[i].updatePosition();
              }
            }
  }

    /*Fonction qui illustre les oiseaux dévorant les insectes. Elle supprime à
    chaque tour les insectes trop près d'un des oiseaux et met à jour les attributs
    donc insectes et nombre_insectes de la classe à chaque fois.*/
    public void predate(){
      int insects_new_number = this.nombre_insectes;
      ArrayList<Insect> insects_gone = new ArrayList<Insect>();
      for (int i = 0; i < this.nombre_insectes; i++){
        for (int l = 0; l < this.nombre_birds; l++){
          if ((int)insects[i].position.distanceTo(birds[l].position) < 10){
            insects_new_number -= 1;
            insects_gone.add(insects[i]);
            l = this.nombre_birds;
          }
        }
      }
      Insect[] new_insects = new Insect[insects_new_number];
      int index = 0;
      int i = 0;
      while (i < insects_new_number){
        if (insects_gone.contains(insects[index])){
          index++;
        }else{
          new_insects[i] = insects[index];
          index++;
          i++;
        }
      }
      this.insects = new_insects;
      this.nombre_insectes = insects_new_number;
    }

      /*Pour le début Suivant,en utilisant EventManager*/
      @Override
      public void execute(){
        this.gui.reset();
        this.display_boids();
        this.move_birds(200,100,10,20);
        this.move_insects(500,100,10,20);
        this.predate();
        this.date += 1;
        manager.addEvent(this);
      }

      /*Pour le bouton Début, réinitialise l'état, notamment ceux des insectes qui sont tous dévorés '^^*/
      @Override
      public void restart(){
        this.gui.reset();
        for (int j = 0; j < this.nombre_birds; j++){
          this.birds[j] = new Bird(new Vector(this.birds_init[j].position.coord[0],this.birds_init[j].position.coord[1]), new Vector(0,0));
        }
        this.insects = new Insect[this.nombre_insectes_initial];
        this.nombre_insectes = this.nombre_insectes_initial;
        for (int j = 0; j < this.nombre_insectes; j++){
          insects[j] = new Insect(new Vector(this.insects_init[j].position.coord[0],this.insects_init[j].position.coord[1]), new Vector(0,0));
        }
        this.display_boids();
      }


      /*Fonction qui dessine nos boids dans la fenêtre de simulation*/
      private void display_boids(){
          for (int j = 0; j < this.nombre_birds; j++){
            double Angle = Math.atan((double)this.birds[j].velocity.coord[1])/((double)this.birds[j].velocity.coord[0]);
            if(this.birds[j].velocity.coord[0] < 0 && this.birds[j].velocity.coord[1] < 0)
            {
              Angle -= Math.PI;
            }
            if(this.birds[j].velocity.coord[0] < 0 &&  0 < this.birds[j].velocity.coord[1])
            {
              Angle += Math.PI;
            }
            this.gui.addGraphicalElement(
              new Triangle(new Point((int)this.birds[j].position.coord[0], (int)this.birds[j].position.coord[1]),
                            Angle,
                            Color.BLACK, Color.BLACK));
          }
          for (int j = 0; j < this.nombre_insectes; j++){
              this.gui.addGraphicalElement(
                new Oval((int)this.insects[j].position.coord[0], (int)this.insects[j].position.coord[1], Color.RED, Color.RED, 5));
          }
      }
}
