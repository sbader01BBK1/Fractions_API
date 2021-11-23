package fraction;

import java.util.*;

public class FractionImpl implements Fraction {

    private final int numerator;
    private final int denominator;

    /**
     * Private getters to ensure immutability; for use in methods.
     *
     * @return numerator or denominator
     */
    private int getNumerator() { return numerator; }
    private int getDenominator() { return denominator; }

    /**
     * Constructs a new Fraction object using two int parameters: numerator & denominator.
     *
     * Normalises and simplifies the fraction as its created by calling the normalise() helper method on the parameters
     * and assigning the relevant values to the instance variables. The normalise() method will also ensure that when
     * the numerator equals 0, the denominator will equal 1.
     *
     * If the denominator is equal to 0 then constructor throws an ArithmeticException.
     *
     * @param numerator an int value representing the fraction's numerator
     * @param denominator an int value representing the fraction's denominator
     */
    public FractionImpl(int numerator, int denominator) {

        if (denominator != 0) {
            // simplifies and normalises the parameters and assigns them to the instance variables
               this.numerator = normalise(numerator, denominator).get("numerator");
               this.denominator = normalise(numerator, denominator).get("denominator");
        }
        else {
            // if denominator == 0, throws ArithmeticException
            throw new ArithmeticException("Denominator cannot be zero.");
        }
    }

    /**
     * Constructs a new Fraction object using a single int parameter: wholeNumber. This is the fraction's numerator.
     *
     * The fraction's denominator defaults to a value of 1.
     * No normalisation is needed for a wholeNumber fraction.
     *
     * @param wholeNumber an int value representing the numerator
     */
    public FractionImpl(int wholeNumber) {
        this.numerator = wholeNumber;
        this.denominator = 1;
    }

    /**
     * Constructs a new Fraction object using a single String parameter.
     *
     * The parameter contains either a whole number, such as "5" or "-3", or a fraction, such as "8/-12".
     * The constructor permits blanks around the integers, but not within an integer, such as "8/-1 2".
     *
     * The constructor throws an ArithmeticException if the denominator equals 0.
     *
     * The constructor throws an IllegalArgumentException if the String contains an invalid integer, or if there are an
     * incorrect number of values (more than one "/").
     *
     * @param fraction the string representation of a fraction or a whole number.
     */
    public FractionImpl(String fraction) {
        String[] valueArr; // stores string values from the parameter
        String numString, denString; // hold the first and second string values, respectively

        // Splits String parameter on "/" and places subStrings into an array of values
        valueArr = fraction.split("/");

        // Checks if there are 1 or 2 values, if there are none or more than 2, throws IllegalArgumentException.
        if (valueArr.length == 2) {
            // trims permissible blanks from around the string values and assigns them to a numString & denString
            numString = valueArr[0].trim();
            denString = valueArr[1].trim();

            // Checks that both numerator & denominator are valid integers by calling isNumeric()
            if (isNumeric(numString) && isNumeric(denString)) {
                // parses the String into an Integer
                int num = Integer.parseInt(numString);
                int den = Integer.parseInt(denString);


                if (den == 0) {
                    // throws exception if denominator equals zero
                    throw new ArithmeticException("Denominator cannot be zero.");
                }
                else {
                    // simplifies and normalises the parameters and assigns them to the instance variables
                    this.numerator = normalise(num, den).get("numerator");
                    this.denominator = normalise(num, den).get("denominator");
                }
            }
            else {
                // if either numString or denString are not numeric, throws IllegalArgumentException
                throw new IllegalArgumentException("Invalid numerator or denominator value.");
            }

        }
        else if (valueArr.length == 1) {
            // does the same as above, but only handles the numerator; the denominator defaults to 1.
            numString = valueArr[0].trim();

            if (isNumeric(numString)) {
                this.numerator = Integer.parseInt(numString);
                this.denominator = 1;
            }
            else {
                throw new IllegalArgumentException("Invalid numerator value.");
            }

        }
        else {
            throw new IllegalArgumentException("Incorrect number of arguments.");
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction add(Fraction f) {
        FractionImpl b = (FractionImpl) f; // casts f as a FractionImpl type

        // uses (ad + bc)/bd formula to obtain new numerator and denominator
        int num = this.numerator * b.getDenominator() + this.denominator * b.getNumerator();
        int den = this.denominator * b.getDenominator();

        return new FractionImpl(num, den);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction subtract(Fraction f) {
        FractionImpl b = (FractionImpl) f; // casts f as a FractionImpl type

        // uses (ad - bc)/bd formula to obtain new numerator and denominator
        int num = this.numerator * b.getDenominator() - this.denominator * b.getNumerator();
        int den = this.denominator * b.getDenominator();

        return new FractionImpl(num, den);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction multiply(Fraction f) {
        FractionImpl b = (FractionImpl) f; // casts f as a FractionImpl type

        // uses (a*c)/(b*d) formula to obtain new numerator and denominator
        int num = this.numerator * b.getNumerator();
        int den = this.denominator * b.getDenominator();

        return new FractionImpl(num, den);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction divide(Fraction f) {
        FractionImpl b = (FractionImpl) f; // casts f as a FractionImpl type

        // uses (a*d)/(b*c) formula to obtain new numerator and denominator
        int num = this.numerator * b.getDenominator();
        int den = this.denominator * b.getNumerator();

        return new FractionImpl(num, den);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction abs() {
        int num = this.numerator;

        // if the numerator is negative in a fraction, converts the numerator to a positive
        if (num < 0) {
            num = num * -1;
        }

        return new FractionImpl(num, this.denominator);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction negate() {
        // returns a new Fraction with the numerator negated
        return new FractionImpl((this.numerator *-1), this.denominator);
    }

    /**
     * @inheritDoc
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        if (obj instanceof Fraction) { // checks if parameter object is a Fraction
            if (this.compareTo((Fraction) obj) == 0) { // calls compareTo to check if equal to this Fraction
                result = true;
            }
        }

        return result;
    }

    /**
     * @inheritDoc
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Fraction inverse() {
        // flips the numerator and denominator
        // FractionImpl constructor automatically normalises, so new denominator will not be < 0 if the numerator was
        return new FractionImpl(this.denominator, this.numerator);
    }

    /**
     * @inheritDoc
     */
    @Override
    public int compareTo(Fraction o) {
        int result = 0; // returns 0 as a default or if the 2 fractions are equal
        FractionImpl b = (FractionImpl) o; // casts o as a FractionImpl type

        // obtains precise double value of both fractions: this & parameter object
        double thisValue = (double) this.numerator / (double) this.denominator;
        double oValue = (double) b.getNumerator() / (double) b.getDenominator();

        if (thisValue > oValue) { // if this fraction is bigger than the object, return 1
            result = 1;
        }
        else if (thisValue < oValue) { // if this fraction is less than the object, return -1
            result = -1;
        }

        return result;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        String result;

        // if denominator = 1 returns the numerator as a whole number; otherwise returns a fraction representation
        if (this.denominator == 1) {
            result = (String.format("%s", this.numerator));
        }
        else {
            result = (String.format("%s/%s", this.numerator, this.denominator));
        }

        return result;
    }

    /**
     * Private helper method that obtains the Greatest Common Divisor (gcd) of 2 integers
     * using the Euclidean Algorithm through recursion.
     *
     * Once n2 = 0, returns n1. If n1 = 0, returns n2.
     *
     * Used in normalising & simplifying fractions. Allows for a negative gcd as this helps with normalisation.
     *
     * @param n1 an int (for our purposes normally the fraction numerator)
     * @param n2 an int (for our purposes normally the fraction denominator)
     * @return either recursively calls itself, or returns the gcd of the 2 integers once n2 = 0
     */
    private static int gcdByEuclideanAlgorithm(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcdByEuclideanAlgorithm(n2, n1 % n2);
    }

    /**
     * Private helper method that normalises & simplifies the numerator & denominator of a fraction by dividing both
     * by the Greatest Common Divisor (gcd).
     *
     * Used in FractionImpl constructors.
     *
     *
     * @param num an int value representing the numerator
     * @param den an int value representing the denominator
     * @return a HashMap containing 2 String values, "numerator" & "denominator", and their corresponding integer values
     */
    private static HashMap<String, Integer> normalise(int num, int den) {
        HashMap<String, Integer> normalise = new HashMap<>();
        int normNum, normDen; // values representing the normalised numerator & denominator

        int greatestCommonDivisor = gcdByEuclideanAlgorithm(num, den); // obtains the gcd of both integers

        // simplifies both integers
        normNum = num / greatestCommonDivisor;
        normDen = den / greatestCommonDivisor;

        // normalises both integers, if the denominator is negative & the numerator is positive
        if (normDen < 0 && normNum > 0) {
            normDen *= -1;
            normNum *= -1;
        }

        // places new values into a HashMap
        normalise.put("numerator", normNum);
        normalise.put("denominator", normDen);

        return normalise;
    }

    /**
     * Private helper method that indicates whether a String is a valid integer or not.
     *
     * Catches a NumberFormatException if the String 's' is not a valid integer.
     *
     * @param s a String, possibly containing a numerical value
     * @return a Boolean - "true" if the String is a valid integer; "False" if otherwise
     */
    private static boolean isNumeric(String s) {
        boolean result;

        try {
            Integer.parseInt(s); // If this doesn't throw a NumberFormatException then the String is a valid integer
            result = true;
        }
        catch (NumberFormatException e) { // Catches a NumberFormatException, implying that the String is not valid
            result = false;
        }

        return result;
    }


    public static void main(String[] args) {
        // Tests for private helper methods

        /*
        // Tests for gcdByEuclideanAlgorithm(int n1, int n2)
        System.out.println(gcdByEuclideanAlgorithm(120, 68)); // Expects 4
        System.out.println(gcdByEuclideanAlgorithm(-10, 6)); // Expects 2
        System.out.println(gcdByEuclideanAlgorithm(0, 48)); // Expects 48
        System.out.println(gcdByEuclideanAlgorithm(-14, -56)); // Expects -14
        System.out.println(gcdByEuclideanAlgorithm(1079, 386)); // Expects 1


        // Tests for normalise(int num, int den)
        HashMap<String, Integer> test1 = normalise(14, -8);
        HashMap<String, Integer> test2 = normalise(0, 84);
        HashMap<String, Integer> test3 = normalise(24, 87);
        HashMap<String, Integer> test4 = normalise(-100, -345);
        HashMap<String, Integer> test5 = normalise(-18, 6);
        System.out.println(test1.get("numerator") + ", " + test1.get("denominator")); // Expects -7, 4
        System.out.println(test2.get("numerator") + ", " + test2.get("denominator")); // Expects 0, 1
        System.out.println(test3.get("numerator") + ", " + test3.get("denominator")); // Expects 8, 29
        System.out.println(test4.get("numerator") + ", " + test4.get("denominator")); // Expects 20, 69
        System.out.println(test5.get("numerator") + ", " + test5.get("denominator")); // Expects -3, 1


        // Tests for isNumeric(String s)
        System.out.println(isNumeric("17839")); // Expects true
        System.out.println(isNumeric("987")); // Expects true
        System.out.println(isNumeric("0")); // Expects true
        System.out.println(isNumeric("29")); // Expects true
        System.out.println(isNumeric("    4567   ".trim())); // Expects true
        System.out.println(isNumeric("  9849£")); // Expects false
        System.out.println(isNumeric("8.6")); // Expects false
        System.out.println(isNumeric("£^(&$)")); // Expects false
        System.out.println(isNumeric("10000 0")); // Expects false
        System.out.println(isNumeric("1 2 3 4 5")); // Expects false
        System.out.println(isNumeric("28 + 45")); // Expects false

         */

    }
}