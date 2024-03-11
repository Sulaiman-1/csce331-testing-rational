package numbers;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThrows;
//import static org.junit.Assert.assertThat;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class RationalTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RationalTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( RationalTest.class );
    }

    /**
     * Rigourous Test :-)
    */

    public void test_NumberToRational_Rational() 
    {
        Rational input = new Rational(3, 4);
        Rational result = input.numberToRational(input);
        assertThat(result, is(input));
    }

    public void test_opposite()
    {
        Rational r = new Rational(3, 5);

        assertThat("the opposite of 3/5 is -3/5", r.opposite(), is(new Rational(-3, 5)));
    }

    public void test_reciprocal()
    {
        Rational r = new Rational(3, 5);

        assertThat("the reciprocal of 3/5 is 5/3", r.reciprocal(), is(new Rational(5, 3)));
    }


//#region value checks tests
    //#region isZero
    public void test_isZero()
    {
        assertThat("0 is 0", new Rational().isZero(), is(true));
    }

    public void test_isZero_Sad()
    {
        assertThat("1 is not 0", new Rational(1).isZero(), is(false));
    }
    //#endregion

    //#region isOne
    public void test_isOne()
    {
        assertThat("1 is 1", new Rational(1).isOne(), is(true));
    }

    public void test_isOne_Sad()
    {
        assertThat("0 is not 1", new Rational().isOne(), is(false));
    }
    //#endregion

    //#region isMinusOne
    public void test_isMinusOne()
    {
        assertThat("-1 is -1", new Rational(-1).isMinusOne(), is(true));
    }

    public void test_isMinusOne_Sad()
    {
        assertThat("0 is not -1", new Rational().isMinusOne(), is(false));
    }
    //#endregion
//#endregion


//#region constructor tests
    public void test_Default_Constructor()
    {
        // Given no preconditions
        // When I create a default `Rational` value
        Rational value = new Rational();
        // Then the value should have numerator 0
        assertThat("the numerator should be 0", value.numerator(), is(0));
        // And the value should have denominator 1
        assertThat("the denominator should be 1", value.denominator(), is(1));
    }

    public void test_Rational_Constructor()
    {
        Rational r = new Rational(1, 2);

        Rational r2 = new Rational(r);

        assertThat("r = r2", r.equals(r2), is(true));
    }

    //sad paths
    public void test_Invalid_Denominator()
    {
        assertThrows(IllegalArgumentException.class, () -> new Rational(1, 0));
    }

    public void test_Negative_Denominator()
    {
        Rational r = new Rational(3, -5);

        assertThat("3/-5  is -3/5", r.equals(new Rational(-3, 5)), is(true));
    }
    
//#endregion


//#region operations
    //#region addition
    public void test_plus()
    {
        Rational r = new Rational(3, 5);
        
        Rational r2 = r.plus(r);

        assertThat("3 + 3 = 6", r2.numerator(), is(6));
        assertThat("denominator is 5", r2.denominator(), is(5));
    }

    public void test_plus_Sad()
    {
        Rational r1 = new Rational(Integer.MAX_VALUE);
        Rational r2 = new Rational(1);

        assertThrows(IllegalArgumentException.class, () -> r1.plus(r2));
    }
    //#endregion

    //#region subtraction
    public void test_minus()
    {
        Rational r = new Rational(3, 5);
        Rational r2 = r.minus(r);

        assertThat("3 - 3 = 0", r2.numerator(), is(0));
        assertThat("denominator is 1 after simplification", r2.denominator(), is(1));
    }
    
    public void test_minus_Sad()
    {
        Rational r1 = new Rational(Integer.MIN_VALUE);
        Rational r2 = new Rational(1);

        assertThrows(IllegalArgumentException.class, () -> r1.minus(r2));
    }
    //#endregion
    
    //#region multiplication
    public void test_times()
    {
        Rational r = new Rational(3, 5);
        r = r.times(r);

        assertThat("3 * 3 = 9", r.numerator(), is(9));
        assertThat("5 * 5 = 25", r.denominator(), is(25));
    }

    public void test_times_Sad()
    {
        Rational r1 = new Rational(Integer.MAX_VALUE);
        Rational r2 = new Rational(2);

        assertThrows(IllegalArgumentException.class, () -> r1.times(r2));
    }
    //#endregion

    //#region division
    public void test_dividedBy()
    {
        Rational r = new Rational(3);
        Rational r1 = new Rational(3, 5);
        r = r.dividedBy(r1);

        assertThat("", r.numerator(), is(5));
        assertThat("denominator is 1", r.denominator(), is(1));

    }

    public void test_dividedBy_0()
    {
        Rational r = new Rational();
        Rational r1 = new Rational(3, 5);

        assertThrows(IllegalArgumentException.class, () -> r1.dividedBy(r));
        
    }

    public void test_dividedBy_Sad()
    {
        Rational r = new Rational(Integer.MAX_VALUE);
        Rational r1 = new Rational(1, 2);

        assertThrows(IllegalArgumentException.class, () -> r.dividedBy(r1));
        
    }
    //#endregion

    //#region raised to
    public void test_raisedToThePowerOf()
    {
        Rational r = new Rational(3, 5);

        r = r.raisedToThePowerOf(5);

        assertThat("3^5 = 243", r.numerator(), is(243));
        assertThat("5^5 = 3125", r.denominator(), is(3125));

    }

    public void test_raisedToThePowerOf_Negative()
    {
        Rational r = new Rational(3, 5);

        r = r.raisedToThePowerOf(-5);

        assertThat("3^-5 = 3125", r.numerator(), is(3125));
        assertThat("5^-5 = 243", r.denominator(), is(243));

    }

    public void test_raisedToThePowerOf_0_to_n()
    {
        Rational r = new Rational();

        assertThat("0 raised to anything > 0 is 0", r.raisedToThePowerOf(4), is(0));
    }
    
    public void test_raisedToThePowerOf_0_to_Negative()
    {
        Rational r = new Rational();

        assertThrows(IllegalArgumentException.class, () -> r.raisedToThePowerOf(-5));
    }
  
    public void test_raisedToThePowerOf_Overflow_Numerator()
    {
        Rational r = new Rational(Integer.MAX_VALUE);
        assertThrows(IllegalArgumentException.class, () -> r.raisedToThePowerOf(2));
    }

    public void test_raisedToThePowerOf_Overflow_Denominator()
    {
        Rational r = new Rational(1, Integer.MAX_VALUE);
        assertThrows(IllegalArgumentException.class, () -> r.raisedToThePowerOf(2));
    }

    //#endregion
//#endregion


//#region comparisons
    //#region equals
    public void test_equals_self()
    {
        Rational r = new Rational();
        boolean equals = r.equals(r);

        assertThat("r = r", equals, is(true));
    }

    public void test_equals_diffDenominator()
    {
        Rational r = new Rational(3, 5);
        Rational r2 = new Rational(3, 7);
        
        boolean equals = r.equals(r2);

        assertThat("denominators are not the same", equals, is(false));
    }
    
    public void test_equals_diffNumerator()
    {
        Rational r = new Rational(2, 5);
        Rational r2 = new Rational(3, 5);
        
        boolean equals = r.equals(r2);

        assertThat("numerators are not the same", equals, is(false));
    }
    
    public void test_equals_Number()
    {
        Number n = 0.5;
        Rational r = new Rational(1, 2);

        boolean equals = r.equals(n);

        assertThat("1/2 = 0.5", equals, is(true));
    }

    public void test_equals_Number_Sad()
    {
        Number n = 0.6;
        Rational r = new Rational(1, 2);

        boolean equals = r.equals(n);

        assertThat("1/2 = 0.6", equals, is(false));
    }

    public void test_equals_notNumber()
    {
        String a = "str";
        Rational r = new Rational();

        assertThat("char is not Rational or Number", r.equals(a), is(false));
    }
    
    public void test_Equals_Float_CloseEnough() 
    {
        Rational r = new Rational(1, 2);
        Float closeEnough = 0.5f + (float)Math.pow(2, -21);

        boolean result = r.equals(closeEnough);

        assertThat("Rational(1/2) should be considered 'equal' to Float closeEnough", result, is(true));
    }

    public void test_Equals_Float_NotCloseEnough() 
    {
        Rational r = new Rational(1, 2); // This is 0.5 when converted to double
        Float notCloseEnough = 0.5f + (float)Math.pow(2, -19); // Slightly more than 0.5 but outside 2^-20 difference

        boolean result = r.equals(notCloseEnough);

        assertThat("Rational(1/2) should not be considered 'equal' to Float notCloseEnough", result, is(false));
    }

    public void test_RationalEquals_Integer_WithDenominatorOne() 
    {
        Rational r = new Rational(5, 1); // Represents the integer 5
        Integer i = 5;
        assertThat(r.equals(i), is(true));
    }

    public void test_RationalNotEquals_Integer_WithDifferentValue() 
    {
        Rational r = new Rational(5);
        Integer i = 4;
        assertThat(r.equals(i), is(false));
    }

    public void test_RationalEquals_Long_WithDenominatorOne() //sad
    {
        Rational r = new Rational(3, 2);
        Long l = 5L;
        assertThat(r.equals(l), is(false));
    }

    public void test_RationalEquals_Byte_WithDenominatorOne() 
    {
        Rational r = new Rational(7, 2);
        Byte b = 5;
        assertThat(r.equals(b), is(false));
    }

    public void test_RationalEquals_Short_WithDenominatorOne() 
    {
        Rational r = new Rational(5);
        Short s = 5;
        assertThat(r.equals(s), is(true));
    }
    //#endregion

    //#region greaterThan
    public void test_greaterThan_Rational()
    {
        Rational r = new Rational(3, 5);
        Rational r2 = new Rational(2, 5);

        boolean greater = r.greaterThan(r2);

        assertThat("r > r2", greater, is(true));
    }

    public void test_greaterThan_Rational_Sad()
    {
        Rational r = new Rational(3, 5);
        Rational r2 = new Rational(4, 5);

        boolean greater = r.greaterThan(r2);

        assertThat("r < r2", greater, is(false));
    }
    
    public void test_greaterThan_Number()
    {
        Number n = 0.3;
        Rational r = new Rational(1, 2);

        assertThat("1/2 > 0.3", r.greaterThan(n), is(true));
    }

    public void test_greaterThan_Number_Sad()
    {
        Number n = 0.6;
        Rational r = new Rational(1, 2);

        assertThat("1/2 < 0.6", r.greaterThan(n), is(false));
    }
    
    //#endregion

    //#region lessThan
    public void test_lessThan_Rational()
    {
        Rational r = new Rational(3, 5);
        Rational r2 = new Rational(4, 5);

        boolean less = r.lessThan(r2);

        assertThat("r < r2", less, is(true));
    }

    public void test_lessThan_Rational_Sad()
    {
        Rational r = new Rational(3, 5);
        Rational r2 = new Rational(2, 5);

        boolean less = r.lessThan(r2);

        assertThat("r > r2", less, is(false));
    }
    
    public void test_lessThan_Number()
    {
        Number n = 0.6;
        Rational r = new Rational(1, 2);

        assertThat("1/2 < 0.6", r.lessThan(n), is(true));
    }

    public void test_lessThan_Number_Sad()
    {
        Number n = 0.3;
        Rational r = new Rational(1, 2);

        assertThat("1/2 > 0.3", r.lessThan(n), is(false));
    }
    //#endregion
//#endregion


//#region overrides

    //#region test compareTo
    public void test_compareTo_Equal()
    {
        Number n = 0.6;
        int numerator = 3;
        int denominator = 5;
        Rational r = new Rational(numerator, denominator);

        int comp = r.compareTo(n);
        assertThat("3/5 = " + n, comp, is(0));
    }

    public void test_compareTo_Greater()
    {
        Number n = 0.6;
        int numerator = 4;
        int denominator = 5;
        Rational r = new Rational(numerator, denominator);

        int comp = r.compareTo(n);
        assertThat("4/5 > " + n, comp, is(1));
    }

    public void test_compareTo_Less()
    {
        Number n = 0.6;
        int numerator = 2;
        int denominator = 5;
        Rational r = new Rational(numerator, denominator);

        int comp = r.compareTo(n);
        assertThat("2/5 < " + n, comp, is(-1));
    }
    //#endregion

    public void test_doubleValue()
    {
        int numerator = 3;
        int denominator = 5;
        Rational r = new Rational(numerator, denominator);
        double dr = r.doubleValue();

        assertEquals(((double)numerator/denominator), dr);
    }

    public void test_floatValue()
    {
        int numerator = 3;
        int denominator = 5;
        Rational r = new Rational(numerator, denominator);
        float fr = r.floatValue();

        assertEquals(((float)numerator/denominator), fr);
    }
    
    public void test_intValue()
    {
        int numerator = 3;
        int denominator = 5;
        Rational r = new Rational(numerator, denominator);
        int ir = r.intValue();

        assertEquals((numerator/denominator), ir);
    }
    
    public void test_longValue()
    {
        int numerator = 3;
        int denominator = 5;
        Rational r = new Rational(numerator, denominator);
        long lr = r.longValue();

        assertEquals(((long)numerator/denominator), lr);
    }
//#endregion
}
