import java.util.*;

class Bird extends Animal{

    public Bird(Vector position, Vector velocity) {
        super(position, velocity);
    }

    /*Fonction qui met à jour la vélocité des oiseaux*/
    public void updateVelocityBirds(Bird[] birds, Insect[] insects, int xMax, int yMax, double cohesionCoefficient, int alignmentCoefficient, double separationCoefficient) {
        velocity = velocity.plus(cohesion(birds,  cohesionCoefficient))
                           .plus(alignment(birds, alignmentCoefficient))
                           .plus(separation(birds, separationCoefficient))
                           .plus(predating(birds, insects, 100))
                           .plus(boundPosition(xMax, yMax));
        limitVelocity();
    }

}
