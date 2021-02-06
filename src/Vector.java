public class Vector {
    int N;
    double[] coord;

  /*Constructeurs de notre classe*/
    public Vector(int d) {
        N = d;
        coord = new double[N];
    }

    public Vector(double... a) {
        N = a.length;
        coord = new double[N];
        for (int i = 0; i < N; i++)
            coord[i] = a[i];
    }


    /*Focntion qui calcule le produit cartésien de deux vecteurs*/
    public double dot(Vector that) {
        double sum = 0.0;
        for (int i = 0; i < N; i++)
            sum += this.coord[i] * that.coord[i];
        return sum;
    }

    /*Fonction qui calcule la valeur avsolue d'un vecteur*/
    public double absolue() {
        return Math.sqrt(this.dot(this));
    }

    /*Fonction qui calcule la distance d'un vecteur à un autre*/
    public double distanceTo(Vector that) {
        return this.moins(that).absolue();
    }

    /*Fonction qui calcule l'addition de deux vecteurs*/
    public Vector plus(Vector that) {
        Vector c = new Vector(N);
        for (int i = 0; i < N; i++)
            c.coord[i] = this.coord[i] + that.coord[i];
        return c;
    }

    /*Fonction qui calcule la soustraction de deux vecteurs*/
    public Vector moins(Vector that) {
        Vector c = new Vector(N);
        for (int i = 0; i < N; i++)
            c.coord[i] = this.coord[i] - that.coord[i];
        return c;
    }

    /*Fonction qui calcule le produit d'un vecteur avec un nombre (facteur)*/
    public Vector fois(double factor) {
        Vector c = new Vector(N);
        for (int i = 0; i < N; i++)
            c.coord[i] = factor * coord[i];
        return c;
    }

    /*Fonction qui calcule la division d'un vecteur par un facteur*/
    public Vector div(double factor) {
        Vector c = new Vector(N);
        for (int i = 0; i < N; i++)
            c.coord[i] = coord[i] / factor;
        return c;
    }

}
