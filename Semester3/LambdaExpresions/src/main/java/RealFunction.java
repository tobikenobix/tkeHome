import java.util.Arrays;

/**
 * Functional Interface that can be used for lambda functions.
 * Includes function apply and constant, linear, sine and exp as static functions
 * @author Tobias
 */
@FunctionalInterface
public interface RealFunction {
    double apply(double x);

    //block of static functions as Factories for this interface

    /**
     * Used to create a constant function in format f(x)=c
     * @param c constant value
     * @return RealFunction as constant function
     */
    static RealFunction constant (double c){
        return ((x) ->c);
    }
    /**
     * Used to create a linear function in format f(x)=ax+b
     * @param a gradient
     * @param b y-axes entry
     * @return RealFunction as linear function
     */
    static RealFunction linear(double a, double b){
        return ((x) -> a*x+b);
    }
    /**
     * Used to create a linear function in format f(x)=a*sin(fx)
     * @param a gradient
     * @param f multiplier of x
     * @return RealFunction as a sine function
     */
    static RealFunction sine(double a, double f){
        return ((x)->a*Math.sin(f*x));
    }
    /**
     * Used to create an exponential function in format f(x)=exp(x)
     * @return RealFunction as an exp function
     */
    static RealFunction exp(){
        return (Math::exp);
    }

    //default methode block

    /**
     * Add two RealFunctions
     * @param g RealFunction that gets added
     * @return new RealFunction in format f(x)+g(x)
     */
    default RealFunction addTo(RealFunction g){
        return ((x)->this.apply(x) + g.apply(x));
    }

    /**
     * Applies the current function on RealFunction f
     * @param f RealFunction on which the calling function gets applied on
     * @return new RealFunction in term f(g(x))
     */
    default RealFunction composeWith(RealFunction f){
        return ((x)->this.apply(f.apply(x)));
    }

    /**
     * Multiplies multiplie RealFunctions
     * @param funs Array of functions to multiply
     * @return new RealFunction that multiplies the functions with each other
     * @throws IllegalArgumentException if funs is null or empty
     */
    default RealFunction multiplyWith(RealFunction... funs){
        if(funs == null|| funs.length == 0) throw new IllegalArgumentException("You have to give at least one function");
        return x -> {
            double res = this.apply(x);
            for (RealFunction fun : funs) {
                res *= fun.apply(x);
            }
            return res;
        };
    }

    /**
     * Approximates f'(x)
     * @param h small number to do so
     * @return new RealFunction as f'(x)
     */
    default RealFunction approxDiff(double h){
        return x -> (this.apply(x+h) - this.apply(x))/h;
    }

    /**
     * Determins the highest numeric of given functions
     * @param funs RealFunctions to compare
     * @return REalFunction that is the max of all the given (including function where this methode was applied on)
     */
    default RealFunction max(RealFunction... funs){
        if(funs == null|| funs.length == 0) throw new IllegalArgumentException("You have to give at least one function");
        return x -> {
            double[] res = new double[funs.length+1];
            for(int i =0; i< funs.length; i++){
                res[i]=funs[i].apply(x);
            }
            res[funs.length]=this.apply(x);
            Arrays.sort(res);
            return res[res.length-1];
        };
    }


}
