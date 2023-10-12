package FirstCode;

import java.util.Objects;

public class Vector {
    private double x;
    private double y;

    public Vector(){
        this.x = 0;
        this.y = 0;
    }
    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Vector(Vector v){
        if(v == null ) throw new IllegalArgumentException("Can not copy empty value");
        this.x = v.x;
        this.y = v.y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    public double getMagnitude(){
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }

    public Vector asNormalized(){
        if(this.getMagnitude() == 0) throw new IllegalArgumentException("Magnitude can not be null!");
        return new Vector(x/getMagnitude(), y/getMagnitude());
    }

    public void add(Vector v){
        if(v == null) throw new IllegalArgumentException("Can not add from empty Object!");
        this.x += v.getX();
        this.y += v.getY();
    }

    public Vector fromPolar(double angle, double magnitude){
        if(angle<0 || angle > 2* Math.PI || magnitude <0) throw new IllegalArgumentException("illegal parameters when trying to create vector from polar coordinates!");
        return new Vector(magnitude*Math.cos(angle), magnitude*Math.sin(angle));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vector vector)) return false;
        return Double.compare(x, vector.x) == 0 && Double.compare(y, vector.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
