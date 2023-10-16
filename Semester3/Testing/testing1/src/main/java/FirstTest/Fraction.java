package FirstTest;

public class Fraction {
    private int nom;
    private int denom;

    public Fraction(int nom, int denom){
        if(denom == 0) throw new IllegalArgumentException("Denom can not be null!");
        this.nom = nom;
        this.denom = denom;
    }
    public Fraction(Fraction f){
        if(f== null) throw new IllegalArgumentException("can not copy null object!");
        this.nom=f.getNom();
        this.denom=f.getDenom();


    }
    public int getNom(){
        return this.nom;
    }
    public int getDenom(){
        return this.denom;
    }
    public void setNom(int nom){
        this.nom = nom;

    }
    public void setDenom(int denom){
        if(denom == 0) throw new IllegalArgumentException("Denom can not be null!");
        this.denom = denom;

    }

    /**
     * Sets sign of fraction. If both are positive, nothing changes, if at least nom or denom is negative, only nom has a sign. Sets Denom to 1 if nom is 0.
     */
    public void normalizeSign(){
        if(nom<0 && denom<0){
            denom*=-1;
        }
        else if(nom>0 && denom<0){
            denom*=-1;
            nom *=-1;
        }
        else if(nom == 0){
            denom =1;
        }
    }

    /**
     * shortens the fraction. Keeps signs of nom and denom
     * @return shortend fraction as a new Fraction instance
     */
    public Fraction asReduced(){
        int gcd = Math.abs(gcd(this.nom, this.denom));
        Fraction ftmp= new Fraction((this.nom/gcd), (this.denom/gcd));
        return ftmp;
    }

    /**
     * Converts fraction to decimal number
     * @return decimal representation of the fraction as double
     */
    public double value(){
        return (double)this.nom/this.denom;
    }
    @Override
    public boolean equals(Object f){
        if(f == null)throw new IllegalArgumentException("Compared object can not be null!");
        if(this == f) return true;
        if(getClass() != f.getClass())return false;
        Fraction fr = (Fraction) f;
        if(this.nom != fr.getNom()) return false;
        return this.denom == fr.getDenom();
    }

    /**
     * Compares if two fractions are same regarding their value - e.g. 2/4 and 1/2 are equel
     * @param f Fraction to compare to
     * @return true if both have same numerical value
     */
    public boolean valueEquals(Fraction f){
        this.normalizeSign();
        f.normalizeSign();
        Fraction f1 = this.asReduced();
        Fraction f2 = f.asReduced();
        return f1.equals(f2);

    }

    //helper method to determine greatest common divider
    private int gcd(int p, int q){
        return (q != 0 ? gcd(q, p % q) : p);
    }

}
