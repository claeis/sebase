package ch.softenvironment.math.test;

import ch.softenvironment.math.MathUtils;

import junit.framework.TestCase;

/**
 * TestCase for MathUtils.
 * @author Peter Hirzel <i>soft</i>Environment
 * @version $Revision: 1.2 $ $Date: 2006-09-17 11:15:57 $
 */
public class MathUtilsTestCase extends TestCase {
    /**
     * This is typically the log-function provided by Computers.
     * Actually its called ln(number) instead of log(number).
     */
	public void testLogarithmNatural() {
	    double logNatural = MathUtils.ln(59.0);
	    // calculated by HP 28S
	    assertTrue((logNatural > 4.077) && (logNatural < 4.078));
	}
	public void testLog10() {
	    double log10 = MathUtils.lg(3.0);
	    // value from Bartsch
	    assertTrue((log10 > 0.477) && (log10 < 0.478));
	    log10 = MathUtils.lg(59.0);
	    // value HP28S [log]
	    assertTrue((log10 > 1.77) && (log10 < 1.78));
	}
	public void testLog2() {
	    double log2 = MathUtils.ld(4.0);
	    assertTrue(log2 == 2.0);
	    log2 = MathUtils.ld(8.0);
	    assertTrue(log2 == 3.0);
	}
    public void testFix() {
        assertTrue(MathUtils.fix(0.0, 0) == 0.0);
        assertTrue(MathUtils.fix(0.0, 2) == 0.0);
        assertTrue(MathUtils.fix(12.896, 2) == 12.89);
        assertTrue(MathUtils.fix(-76.89667, 4) == -76.8966);
        assertTrue(MathUtils.fix(30.989, 1) == 30.9);
        assertTrue(MathUtils.fix(-76.89667, 0) == -76.0);
    }
    public void testRound() {
        assertTrue(MathUtils.round(0.0, 0) == 0.0);
        assertTrue(MathUtils.round(0.0, 2) == 0.0);
        assertTrue(MathUtils.round(12.876, 2) == 12.88);
        assertTrue(MathUtils.round(-76.89667, 4) == -76.8967);
        assertTrue(MathUtils.round(30.989, 1) == 31.0);
        assertTrue(MathUtils.round(-76.89667, 0) == -77.0);
        assertTrue(MathUtils.round(303.33333333333333333, 2) == 303.33);
    }
}
