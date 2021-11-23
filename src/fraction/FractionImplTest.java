package fraction;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class FractionImplTest {

    /** Tests 1st constructor for value 0 */
    @Test
    void testConstructor01() {
        FractionImpl a = new FractionImpl(0, 1);
        assertEquals(a.toString(), "0");
    }

    /** Tests 1st constructor for negative nominator value */
    @Test
    void testConstructor02() {
        FractionImpl a = new FractionImpl(-4, 5);
        assertEquals(a.toString(), "-4/5");
    }

    /** Tests 1st constructor for negative denominator value (normalisation) */
    @Test
    void testConstructor03() {
        FractionImpl a = new FractionImpl(4, -5);
        assertEquals(a.toString(), "-4/5");
    }

    /** Tests 1st constructor for 2 negative values returning a positive fraction */
    @Test
    void testConstructor04() {
        FractionImpl a = new FractionImpl(-4, -5);
        assertEquals(a.toString(), "4/5");
    }

    /** Tests 1st constructor for simplification of large integers */
    @Test
    void testConstructor05() {
        FractionImpl a = new FractionImpl(5000, 10000);
        assertEquals(a.toString(), "1/2");
    }

    /** Tests 1st constructor for ArithmeticException when denominator equals 0 */
    @Test
    void testConstructor06() {
        assertThrows(ArithmeticException.class, ()-> new FractionImpl(4, 0));
    }

    /** Tests wholeNumber constructor for value 0*/
    @Test
    void testWholeNumberConstructor01() {
        FractionImpl a = new FractionImpl(0);
        assertEquals(a.toString(), "0");
    }

    /** Tests wholeNumber constructor for negative value */
    @Test
    void testWholeNumberConstructor02() {
        FractionImpl a = new FractionImpl(-5);
        assertEquals(a.toString(), "-5");
    }

    /** Tests wholeNumber constructor for positive value */
    @Test
    void testWholeNumberConstructor03() {
        FractionImpl a = new FractionImpl(5);
        assertEquals(a.toString(), "5");
    }

    /** Tests wholeNumber constructor for value containing operators */
    @Test
    void testWholeNumberConstructor04() {
        FractionImpl a = new FractionImpl(45-6);
        assertEquals(a.toString(), "39");
    }

    /** Tests wholeNumber constructor for value containing operators */
    @Test
    void testWholeNumberConstructor05() {
        FractionImpl a = new FractionImpl(10+5);
        assertEquals(a.toString(), "15");
    }

    /** Tests wholeNumber constructor for value containing operators */
    @Test
    void testWholeNumberConstructor06() {
        FractionImpl a = new FractionImpl(8-20);
        assertEquals(a.toString(), "-12");
    }

    /** Tests String constructor for positive value containing valid blanks */
    @Test
    void testStringConstructor01() {
        FractionImpl a = new FractionImpl("  1/  2  ");
        assertEquals(a.toString(), "1/2");
    }

    /** Tests String constructor for negative numerator value containing valid blanks */
    @Test
    void testStringConstructor02() {
        FractionImpl a = new FractionImpl("-1  / 2");
        assertEquals(a.toString(), "-1/2");
    }

    /** Tests String constructor for negative denominator value containing valid blanks (normalisation) */
    @Test
    void testStringConstructor03() {
        FractionImpl a = new FractionImpl("  1/-2  ");
        assertEquals(a.toString(), "-1/2");
    }

    /** Tests String constructor for 2 negative values returning a positive fraction */
    @Test
    void testStringConstructor04() {
        FractionImpl a = new FractionImpl("-1/-2");
        assertEquals(a.toString(), "1/2");
    }

    /** Tests String constructor for simplification of a positive fraction */
    @Test
    void testStringConstructor05() {
        FractionImpl a = new FractionImpl("16/4");
        assertEquals(a.toString(), "4");
    }

    /** Tests String constructor for simplification of a negative fraction */
    @Test
    void testStringConstructor06() {
        FractionImpl a = new FractionImpl("-8/72");
        assertEquals(a.toString(), "-1/9");
    }

    /** Tests String constructor for zero value containing valid blanks */
    @Test
    void testStringConstructor07() {
        FractionImpl a = new FractionImpl(" 0       /1");
        assertEquals(a.toString(), "0");
    }

    /** Tests String constructor for whole number value */
    @Test
    void testStringConstructor08() {
        FractionImpl a = new FractionImpl("16");
        assertEquals(a.toString(), "16");
    }

    /** Tests String constructor for ArithmeticException when denominator equals 0 */
    @Test
    void testStringConstructor09() {
        assertThrows(ArithmeticException.class, ()-> new FractionImpl("7  / 0"));
    }

    /** Tests String constructor for IllegalArgumentException when value is a number strings */
    @Test
    void testStringConstructor10() {
        assertThrows(IllegalArgumentException.class, ()-> new FractionImpl(" twelve / thirteen "));
    }

    /** Tests String constructor for negative numerator value containing invalid blanks */
    @Test
    void testStringConstructor11() {
        assertThrows(IllegalArgumentException.class, ()-> new FractionImpl(" -1 2 3  / 123"));
    }

    /** Tests String constructor for symbol values */
    @Test
    void testStringConstructor12() {
        assertThrows(IllegalArgumentException.class, ()-> new FractionImpl("24& / 89"));
    }

    /** Tests String constructor for IllegalArgumentException when whole number value contains invalid blanks */
    @Test
    void testStringConstructor13() {
        assertThrows(IllegalArgumentException.class, ()-> new FractionImpl("  46 7 "));
    }

    /** Tests String constructor for IllegalArgumentException when there's more than one '/' */
    @Test
    void testStringConstructor14() {
        assertThrows(IllegalArgumentException.class, ()-> new FractionImpl("12 / 3 / 2"));
    }

    /** Tests add() method for 2 positive fractions */
    @Test
    void add01() {
        FractionImpl a = new FractionImpl(8, 9);
        FractionImpl b = new FractionImpl(3, 6);
        assertEquals(a.add(b).toString(), "25/18");
    }

    /** Tests add() method for 1 positive & 1 zero value fraction */
    @Test
    void add02() {
        FractionImpl a = new FractionImpl(0, 1);
        FractionImpl b = new FractionImpl(14, 15);
        assertEquals(a.add(b).toString(), "14/15");
    }

    /** Tests add() method for 1 negative & 1 zero value fraction */
    @Test
    void add03() {
        FractionImpl a = new FractionImpl(-18, 7);
        FractionImpl b = new FractionImpl(0, 1);
        assertEquals(a.add(b).toString(), "-18/7");
    }

    /** Tests add() method for 1 whole number & 1 positive fraction */
    @Test
    void add04() {
        FractionImpl a = new FractionImpl(17);
        FractionImpl b = new FractionImpl(3, 10);
        assertEquals(a.add(b).toString(), "173/10");
    }

    /** Tests add() method for 2 large value fractions (simplification) */
    @Test
    void add05() {
        FractionImpl a = new FractionImpl(245, 598);
        FractionImpl b = new FractionImpl(489, 987);
        assertEquals(a.add(b).toString(), "178079/196742");
    }

    /** Tests add() method for 1 positive & 1 negative fraction */
    @Test
    void add06() {
        FractionImpl a = new FractionImpl(5, -8);
        FractionImpl b = new FractionImpl(3, 6);
        assertEquals(a.add(b).toString(), "-1/8");
    }

    /** Tests add() method for 2 negative fractions */
    @Test
    void add07() {
        FractionImpl a = new FractionImpl(5, -8);
        FractionImpl b = new FractionImpl(3, -6);
        assertEquals(a.add(b).toString(), "-9/8");
    }

    /** Tests add() method for 1 String fraction & 1 negative fraction */
    @Test
    void add08() {
        FractionImpl a = new FractionImpl("30/44");
        FractionImpl b = new FractionImpl(3, -6);
        assertEquals(a.add(b).toString(), "2/11");
    }

    /** Tests subtract() method for 2 equal whole number fractions */
    @Test
    void subtract01() {
        FractionImpl a = new FractionImpl(24);
        FractionImpl b = new FractionImpl(24);
        assertEquals(a.subtract(b).toString(), "0");
    }

    /** Tests subtract() method for 2 negative denominator fractions */
    @Test
    void subtract02() {
        FractionImpl a = new FractionImpl(5, -8);
        FractionImpl b = new FractionImpl(3, -6);
        assertEquals(a.subtract(b).toString(), "-1/8");
    }

    /** Tests subtract() method for 2 negative numerator fractions */
    @Test
    void subtract03() {
        FractionImpl a = new FractionImpl(-5, 8);
        FractionImpl b = new FractionImpl(-3, 6);
        assertEquals(a.subtract(b).toString(), "-1/8");
    }

    /** Tests subtract() method for 1 whole number and 1 positive fraction */
    @Test
    void subtract04() {
        FractionImpl a = new FractionImpl(1);
        FractionImpl b = new FractionImpl(12, 13);
        assertEquals(a.subtract(b).toString(), "1/13");
    }

    /** Tests subtract() method for 1 large whole number string fraction & 1 large whole number fraction */
    @Test
    void subtract05() {
        FractionImpl a = new FractionImpl("1879");
        FractionImpl b = new FractionImpl(1878);
        assertEquals(a.subtract(b).toString(), "1");
    }

    /** Tests subtract() method for 1 positive fraction & 1 whole number fraction */
    @Test
    void subtract06() {
        FractionImpl a = new FractionImpl(14, 18);
        FractionImpl b = new FractionImpl(2);
        assertEquals(a.subtract(b).toString(), "-11/9");
    }

    /** Tests multiply() method for 2 equal whole number fractions */
    @Test
    void multiply01() {
        FractionImpl a = new FractionImpl(12);
        FractionImpl b = new FractionImpl(12);
        assertEquals(a.multiply(b).toString(), "144");
    }

    /** Tests multiply() method for 1 positive fraction & 1 zero value fraction */
    @Test
    void multiply02() {
        FractionImpl a = new FractionImpl(9, 6);
        FractionImpl b = new FractionImpl(0, 1);
        assertEquals(a.multiply(b).toString(), "0");
    }

    /** Tests multiply() method for 1 positive fraction & 1 negative fraction */
    @Test
    void multiply03() {
        FractionImpl a = new FractionImpl(3, 2);
        FractionImpl b = new FractionImpl("-3/2");
        assertEquals(a.multiply(b).toString(), "-9/4");
    }

    /** Tests multiply() method for 1 negative fraction & 1 negative whole number */
    @Test
    void multiply04() {
        FractionImpl a = new FractionImpl(-4, 7);
        FractionImpl b = new FractionImpl(-3);
        assertEquals(a.multiply(b).toString(), "12/7");
    }

    /** Tests multiply() method for 2 equal whole number fractions of string & whole number*/
    @Test
    void multiply05() {
        FractionImpl a = new FractionImpl("5");
        FractionImpl b = new FractionImpl(5);
        assertEquals(a.multiply(b).toString(), "25");
    }

    /** Tests multiply() method for 2 positive fractions */
    @Test
    void multiply06() {
        FractionImpl a = new FractionImpl(4, 6);
        FractionImpl b = new FractionImpl(7, 33);
        assertEquals(a.multiply(b).toString(), "14/99");
    }

    /** Tests multiply() method for 2 negative fractions */
    @Test
    void multiply07() {
        FractionImpl a = new FractionImpl(-4, 6);
        FractionImpl b = new FractionImpl(7, -33);
        assertEquals(a.multiply(b).toString(), "14/99");
    }

    /** Tests divide() method for 2 whole number fractions */
    @Test
    void divide01() {
        FractionImpl a = new FractionImpl("5");
        FractionImpl b = new FractionImpl(5);
        assertEquals(a.divide(b).toString(), "1");
    }

    /** Tests divide() method for 1 zero value & 1 positive fraction */
    @Test
    void divide02() {
        FractionImpl a = new FractionImpl(0);
        FractionImpl b = new FractionImpl(4, 5);
        assertEquals(a.divide(b).toString(), "0");
    }

    /** Tests divide() method for 2 positive fractions */
    @Test
    void divide03() {
        FractionImpl a = new FractionImpl(7, 10);
        FractionImpl b = new FractionImpl(2, 9);
        assertEquals(a.divide(b).toString(), "63/20");
    }

    /** Tests divide() method for 1 positive & 1 negative fraction */
    @Test
    void divide04() {
        FractionImpl a = new FractionImpl(7, 10);
        FractionImpl b = new FractionImpl(-2, 9);
        assertEquals(a.divide(b).toString(), "-63/20");
    }

    /** Tests divide() method for 2 negative fractions */
    @Test
    void divide05() {
        FractionImpl a = new FractionImpl(-8, 15);
        FractionImpl b = new FractionImpl(-2, 6);
        assertEquals(a.divide(b).toString(), "8/5");
    }

    /** Tests divide() method for dividing by 1 */
    @Test
    void divide06() {
        FractionImpl a = new FractionImpl(8, 15);
        FractionImpl b = new FractionImpl(1);
        assertEquals(a.divide(b).toString(), "8/15");
    }

    /** Tests divide() method for 2 large value fractions */
    @Test
    void divide07() {
        FractionImpl a = new FractionImpl(-850, 1562);
        FractionImpl b = new FractionImpl(-277, 4000);
        assertEquals(a.divide(b).toString(), "1700000/216337");
    }

    /** Tests abs() method for positive fraction */
    @Test
    void abs01() {
        FractionImpl a = new FractionImpl(4, 5);
        assertEquals(a.abs().toString(), "4/5");
    }

    /** Tests abs() method for negative numerator fraction */
    @Test
    void abs02() {
        FractionImpl a = new FractionImpl(-4, 5);
        assertEquals(a.abs().toString(), "4/5");
    }

    /** Tests abs() method for negative whole number fraction */
    @Test
    void abs03() {
        FractionImpl a = new FractionImpl(-25);
        assertEquals(a.abs().toString(), "25");
    }

    /** Tests abs() method for negative string fraction (& simplification) */
    @Test
    void abs04() {
        FractionImpl a = new FractionImpl("  -12 /   16");
        assertEquals(a.abs().toString(), "3/4");
    }

    /** Tests abs() method for negative denominator fraction */
    @Test
    void abs05() {
        FractionImpl a = new FractionImpl(4, -5);
        assertEquals(a.abs().toString(), "4/5");
    }

    /** Tests abs() method for positive whole number fraction */
    @Test
    void abs06() {
        FractionImpl a = new FractionImpl(23);
        assertEquals(a.abs().toString(), "23");
    }

    /** Tests negate() method for negative fraction */
    @Test
    void negate01() {
        FractionImpl a = new FractionImpl(-1, 2);
        assertEquals(a.negate().toString(), "1/2");
    }

    /** Tests negate() method for positive fraction */
    @Test
    void negate02() {
        FractionImpl a = new FractionImpl(1, 2);
        assertEquals(a.negate().toString(), "-1/2");
    }

    /** Tests negate() method for zero value fraction */
    @Test
    void negate03() {
        FractionImpl a = new FractionImpl(0);
        assertEquals(a.negate().toString(), "0");
    }

    /** Tests negate() method for 2 negative values fraction */
    @Test
    void negate04() {
        FractionImpl a = new FractionImpl(-4, -5);
        assertEquals(a.negate().toString(), "-4/5");
    }

    /** Tests negate() method for negative denominator fraction */
    @Test
    void negate05() {
        FractionImpl a = new FractionImpl(4, -5);
        assertEquals(a.negate().toString(), "4/5");
    }

    @Test
    void testHashCode() {
    }

    /** Tests equals() method for equal Fraction types */
    @Test
    void testEquals01() {
        FractionImpl a = new FractionImpl(-2, 3);
        Fraction b = new FractionImpl(-2, 3);
        assertEquals(b, a);
    }

    /** Tests equals() method for different object types */
    @Test
    void testEquals02() {
        FractionImpl a = new FractionImpl(-2, 3);
        String[] b = new String[]{};
        assertNotEquals(b, a);
    }

    /** Tests equals() method for unequal Fraction types */
    @Test
    void testEquals03() {
        FractionImpl a = new FractionImpl(-2, 3);
        Fraction b = new FractionImpl(2, 3);
        assertNotEquals(b, a);
    }

    /** Tests equals() method for equal Fraction types */
    @Test
    void testEquals04() {
        FractionImpl a = new FractionImpl(5);
        Fraction b = new FractionImpl(5);
        assertEquals(b, a);
    }

    /** Tests equals() method for unequal Fraction types */
    @Test
    void testEquals05() {
        FractionImpl a = new FractionImpl("18 / 56");
        Fraction b = new FractionImpl(5);
        assertNotEquals(b, a);
    }

    /** Tests equals() method for simplified equal Fraction types */
    @Test
    void testEquals06() {
        FractionImpl a = new FractionImpl(50, 100);
        Fraction b = new FractionImpl("1/2");
        assertEquals(b, a);
    }

    /** Tests equals() method for simplified equal Fraction types */
    @Test
    void testEquals07() {
        FractionImpl a = new FractionImpl(125, 25);
        Fraction b = new FractionImpl(5);
        assertEquals(b, a);
    }

    @Test
    void testClone() {
    }

    /** Tests inverse() method for positive fraction */
    @Test
    void inverse01() {
        FractionImpl a = new FractionImpl(7, 8);
        assertEquals(a.inverse().toString(), "8/7");
    }

    /** Tests inverse() method for negative numerator fraction */
    @Test
    void inverse02() {
        FractionImpl a = new FractionImpl(-7, 8);
        assertEquals(a.inverse().toString(), "-8/7");
    }

    /** Tests inverse() method for negative denominator fraction */
    @Test
    void inverse03() {
        FractionImpl a = new FractionImpl(7, -8);
        assertEquals(a.inverse().toString(), "-8/7");
    }

    /** Tests inverse() method for simplifying positive fraction */
    @Test
    void inverse04() {
        FractionImpl a = new FractionImpl(16, 8);
        assertEquals(a.inverse().toString(), "1/2");
    }

    /** Tests inverse() method for simplifying negative fraction fraction */
    @Test
    void inverse05() {
        FractionImpl a = new FractionImpl(-14, 7);
        assertEquals(a.inverse().toString(), "-1/2");
    }

    /** Tests inverse() method for positive whole number fraction */
    @Test
    void inverse06() {
        FractionImpl a = new FractionImpl(17);
        assertEquals(a.inverse().toString(), "1/17");
    }

    /** Tests inverse() method for negative fraction returning a whole number */
    @Test
    void inverse07() {
        FractionImpl a = new FractionImpl("-1/17");
        assertEquals(a.inverse().toString(), "-17");
    }

    /** Tests compareTo() method for positive fraction a > positive fraction b */
    @Test
    void compareTo01() {
        FractionImpl a = new FractionImpl(1, 2);
        FractionImpl b = new FractionImpl(1, 4);
        assertEquals(a.compareTo(b), 1);
    }

    /** Tests compareTo() method for negative fraction a < negative fraction b */
    @Test
    void compareTo02() {
        FractionImpl a = new FractionImpl(-1, 2);
        FractionImpl b = new FractionImpl(-1, 4);
        assertEquals(a.compareTo(b), -1);
    }

    /** Tests compareTo() method for positive fraction a == positive fraction b */
    @Test
    void compareTo03() {
        FractionImpl a = new FractionImpl(1, 2);
        FractionImpl b = new FractionImpl(1, 2);
        assertEquals(a.compareTo(b), 0);
    }

    /** Tests compareTo() method for negative fraction a < positive fraction b */
    @Test
    void compareTo04() {
        FractionImpl a = new FractionImpl(-1, 2);
        FractionImpl b = new FractionImpl(1, 4);
        assertEquals(a.compareTo(b), -1);
    }

    /** Tests compareTo() method for negative fraction a < positive fraction b */
    @Test
    void compareTo05() {
        FractionImpl a = new FractionImpl(-1, 4);
        FractionImpl b = new FractionImpl(1, 4);
        assertEquals(a.compareTo(b), -1);
    }

    /** Tests compareTo() method for positive fraction a > negative fraction b */
    @Test
    void compareTo06() {
        FractionImpl a = new FractionImpl(0, 1);
        FractionImpl b = new FractionImpl(-1, 4);
        assertEquals(a.compareTo(b), 1);
    }

    /** Tests compareTo() method for large positive fraction a > large positive fraction b */
    @Test
    void compareTo07() {
        FractionImpl a = new FractionImpl(18939);
        FractionImpl b = new FractionImpl(18938);
        assertEquals(a.compareTo(b), 1);
    }

    /** Tests compareTo() method for v. small positive fraction a < positive fraction b */
    @Test
    void compareTo08() {
        FractionImpl a = new FractionImpl(1, 15278);
        FractionImpl b = new FractionImpl(1, 4);
        assertEquals(a.compareTo(b), -1);
    }

    /** Tests compareTo() method for v. small positive fraction a == v. small positive fraction b */
    @Test
    void compareTo09() {
        FractionImpl a = new FractionImpl(1, 10000);
        FractionImpl b = new FractionImpl(1, 10000);
        assertEquals(a.compareTo(b), 0);
    }

    /** Tests toString() method for wholeNumber fraction where denominator = 1 */
    @Test
    void testToString01() {
        FractionImpl a = new FractionImpl(0);
        assertEquals(a.toString(), "0");
    }

    /** Tests toString() method for String fraction where denominator = 1 */
    @Test
    void testToString02() {
        FractionImpl a = new FractionImpl("0");
        assertEquals(a.toString(), "0");
    }

    /** Tests toString() method for 2-value fraction where denominator = 1 */
    @Test
    void testToString03() {
        FractionImpl a = new FractionImpl(0, 1);
        assertEquals(a.toString(), "0");
    }

    /** Tests toString() method for positive wholeNumber fraction where denominator = 1 */
    @Test
    void testToString04() {
        FractionImpl a = new FractionImpl(10);
        assertEquals(a.toString(), "10");
    }

    /** Tests toString() method for positive whole number String fraction where denominator = 1 */
    @Test
    void testToString05() {
        FractionImpl a = new FractionImpl("10/1");
        assertEquals(a.toString(), "10");
    }

    /** Tests toString() method for positive fraction */
    @Test
    void testToString06() {
        FractionImpl a = new FractionImpl(1, 2);
        assertEquals(a.toString(), "1/2");
    }

    /** Tests toString() method for positive fraction */
    @Test
    void testToString07() {
        FractionImpl a = new FractionImpl(12, 4);
        assertEquals(a.toString(), "3");
    }

    /** Tests toString() method for large value positive fraction */
    @Test
    void testToString08() {
        FractionImpl a = new FractionImpl(18, 24);
        assertEquals(a.toString(), "3/4");
    }

    /** Tests toString() method for large value negative numerator fraction */
    @Test
    void testToString09() {
        FractionImpl a = new FractionImpl(-18, 24);
        assertEquals(a.toString(), "-3/4");
    }

    /** Tests toString() method for large value negative denominator fraction */
    @Test
    void testToString10() {
        FractionImpl a = new FractionImpl(18, -24);
        assertEquals(a.toString(), "-3/4");
    }

    /** Tests toString() method for String fraction with valid blanks */
    @Test
    void testToString11() {
        FractionImpl a = new FractionImpl("  18  /   24");
        assertEquals(a.toString(), "3/4");
    }
}