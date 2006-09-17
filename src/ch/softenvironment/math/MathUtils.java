package ch.softenvironment.math;
/**
 * Utility for extended Mathematics.
 * @author Peter Hirzel <i>soft</i>Environment
 */
public class MathUtils {
    /**
     * Calclulate logarithm according to given base, where
     * log(base=a; value=b) = c corresponds to a^c = b
     * 	=> 2^x=8 corresponds to ld(8) => x=3
     * Transformation: log(base=e; value=b) == ln(value=b)
     * 
     * java.util.Math.log(a) represents actually the natural logarithm ln(a),
     * therefore this log-function is not exactly the same.
     * @param base
     * @param value
     * @return
     * @see ln()
     */
	public final static double log(double base, double value) {
	    // log[base]value = log[10]value / log[10]base
	    if ((base > 0) && (base != 1.0) && (value > 0)) {
	    	return ln(value) / ln(base);
	    } else {
	        throw new IllegalArgumentException("base [" + base + "] or value [" + value + "] invalid");
	    }
	}
	/**
	 * Decade Logarithm (base=10).
	 * @param value
	 * @return
	 */
	public final static double lg(double value) {
	    return log(10.0, value);
	}
	/**
	 * Dual Logarithm (base=2).
	 * @param value
	 * @return
	 */
	public final static double ld(double value) {
	    return log(2.0, value);
	}
	/**
	 * Natural Logarithm (base=e)
	 * @param value
	 * @return
	 */
	public final static double ln(double value) {
	    return Math.log(value);
	}
    /**
     * Negate a given value.
     * @param value
     * @return
     */
    public final static Double negate(Double value) {
        if (value == null) {
            return null;
        }
        return new Double(value.doubleValue() * (-1));
    }
    /**
     * Cut the fraction part to accuracy length, for e.g.
     * fix(12.876, 2) => 12.87
     * @param value
     * @param accuracy
     * @return
     */
    public final static double fix(double value, int accuracy) {
        if (accuracy < 0) {
            throw new IllegalArgumentException("accuracy must be >=0");
        }
        if (accuracy == 0) {
            // cast decimal fraction away
            return (double)((long)value);
        }
        double factor = Math.pow(10, accuracy);
        return (double)(((long)(value * factor)) / factor);
    }
    /**
     * Round the fraction part to accuracy length, for e.g.
     * round(12.876, 2) => 12.88
     * @param value
     * @param accuracy
     * @return
     * @see java.util.Math#round()
     */
    public final static double round(double value, int accuracy) {
        if (accuracy < 0) {
            throw new IllegalArgumentException("accuracy must be >=0");
        }
        if (accuracy == 0) {
            return Math.round(value);
        } else {
            double val = value;
            for (int i=0; i<accuracy; i++) {
                val = val * 10;
            }
            val = (long)Math.floor(val + 0.5d);
            for (int i=0; i<accuracy; i++) {
                val = val / 10;
            }
            return fix(val, accuracy); // remove any division inaccuracies
        }
    }
}
