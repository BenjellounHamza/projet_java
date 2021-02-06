import java.util.*;

class Animal {
    Vector position;
    Vector velocity;

    /*Constructeur*/
    public Animal(Vector position, Vector velocity) {
        this.position = position;
        this.velocity = velocity;
    }

   /*Fonction qui met à jour la position de nos animaux (boids)*/
    public void updatePosition() {
        position = position.plus(velocity);
    }

    /*////////////////////////Nos règles//////////////////////////////////////*/
    public Vector cohesion(Animal[] animals, double cohesionCoefficient) {
        Vector pcJ = new Vector(0,0);
        int length = animals.length;
        for (int i = 0; i < length; i++){
            pcJ = pcJ.plus(animals[i].position);
          }
        pcJ = pcJ.div(length);
        return pcJ.moins(position).div(cohesionCoefficient);
    }


    public Vector alignment(Animal[] animals, int alignmentCoefficient) {
        Vector pvJ = new Vector(0,0);
        int length = animals.length;
        for (int i = 0; i < length; i++){
            pvJ = pvJ.plus(animals[i].velocity);
          }
        pvJ = pvJ.div(length);
        return pvJ.moins(velocity).div(alignmentCoefficient);
    }

    public Vector separation(Animal[] animals, double separationCoefficient) {
        Vector c = new Vector(0,0);
        int length = animals.length;
        for (int i = 0; i < length; i++)
          {
            if ((animals[i].position.moins(position).absolue()) < separationCoefficient)
            {
              c = c.moins(animals[i].position.moins(position));
            }
          }
        return c;
    }

    public Vector boundPosition(int xMax, int yMax) {
        int x = 0;
        int y = 0;
        if (this.position.coord[0] < 0)                x = 20;
        else if (this.position.coord[0]  > xMax)       x = -20;
        if (this.position.coord[1]  < 0)               y = 20;
        else if (this.position.coord[1]  > yMax)       y = -20;
        return new Vector(x,y);
    }


    public Vector strongWind() {
        Vector wind = new Vector(0,0);
        wind.coord[0] = 1;
        wind.coord[1] = 2;
        return wind;
    }

    public Vector predating(Animal[] animals, Animal[] proies, double predatingCoefficient) {
      if (proies.length != 0){
        Vector p = new Vector(0,0);
        int length = proies.length;
        for (int i = 0; i < length; i++){
            p = p.plus(proies[i].position);
          }
        p = p.div(length);
        return p.moins(position).div(predatingCoefficient);
      }
      return new Vector(0,0);
    }


    public void limitVelocity() {
        int vlim = 50;
        if (this.velocity.absolue() > vlim) {
            this.velocity = this.velocity.div(this.velocity.absolue());
            this.velocity = this.velocity.fois(vlim);
        }
    }
    /*/////////////////////////////////////////////////////////////////////////*/
}
