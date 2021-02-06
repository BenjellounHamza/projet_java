import java.util.*;

class Insect extends Animal{

    public Insect(Vector position, Vector velocity) {
        super(position, velocity);
    }

    /*Fonction qui met à jour la vélocité des insectes*/
    public void updateVelocityInsects(Bird[] birds, Insect[] insects, int xMax, int yMax, double cohesionCoefficient, int alignmentCoefficient, double separationCoefficient) {
        velocity = velocity.plus(cohesion(insects,  cohesionCoefficient))
                           .plus(alignment(insects, alignmentCoefficient))
                           .plus(separation(insects, separationCoefficient))
                           .plus(separation(birds, 50))
                           .plus(boundPosition(xMax, yMax));
                           // .plus(strongWind());
        limitVelocity();
    }

}
