package item.weapon;

/**
 * Program Name:    RandGen.java
 *<p>
 * Purpose:         The purpose of this program is to
 *<p>
 * @version         0.0
 *<p>
 * Created:         February 19, 2025
 *<p>
 * Updated:         MONTH DD, YYYY
 *<p>
 * @author          Sean Dziatkowiec
*/

import java.util.Random;

public class RandGen {
    /**
     * @param randGen:  Of class Random. Will be used to gen numbers.
     * @param intLower: Lower bound of integer generated number.
     * @param intUpper: Upper bound of integer generated number.
     * @param dbLower:  Lower bound of double generated number.
     * @param dbUpper:  Upper bound of double generated number.
    */

    private Random randGen = new Random();

    private int intLower;
    private int intUpper;

    private double dbLower;
    private double dbUpper;

//CONSTRUCTORS---------------------------------------------------------------------

    public RandGen(int intLower, int intUpper) {
        /**
         * Constructor for class RandGen.
         *
         * This constructor will create an integer RandGen object.
        */

        this.intLower = intLower;
        this.intUpper = (intUpper - this.intLower) + 1;
    }

    public RandGen(double dbLower, double dbUpper) {
        /**
         * Constructor for class RandGen.
         *
         * This constructor will create a double RandGen object.
        */

        this.dbLower = dbLower;
        this.dbUpper = (dbUpper - this.dbLower) + 1.0;
    }

//GEN-NUMS-------------------------------------------------------------------------

    public int genInt() {
        /**
         * This method will return a random integer from [intLower, intUpper].
        */

        // Declare new int
        int randNum;

        // Generate new INT number.
        randNum = randGen.nextInt(intUpper) + intLower;

        return randNum;
    }

    public double genDouble() {
        /**
         * This method will return a random integer from
         * [doubleLower, doubleUpper].
        */

        // Decleare new double
        double randNum;

        // Generate new DOUBLE number.
        // Cast upper bound as INT and add DOUBLE lower as is DOUBLE == DOUBLE.
        randNum = randGen.nextInt((int)dbUpper) + dbLower;

        return randNum;
    }

}
