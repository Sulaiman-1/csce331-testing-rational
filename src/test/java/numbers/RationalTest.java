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
public class RationalTest 
    extends TestCase
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

    public void test_One_Arg_Constructor()
    {
        // Given that I have constructed a `Rational` value using the argument `2`
        Rational value = new Rational(2);
        // Then the value should have numerator `2`
        assertThat("the numerator should be 2", value.numerator(), is(2));
        // And the value should have denominator `1`
        assertThat("the denominator should be 1", value.denominator(), is(1));
    }

    public void test_Two_Args_Constructor()
    {
        // Given that I have created a Rational value using arguments `2`` and `3`
        Rational value = new Rational(2, 3);
        // Then the value should have numerator `2`
        assertThat("the numerator should be 2", value.numerator(), is(2));
        // And the value should have denominator `3`
        assertThat("the denominator should be 3", value.denominator(), is(3));
    }

    public void test_Rational_Arg_Constructor()
    {
        // Given that I have created the `Rational` value `2/3`
        Rational original = new Rational(2, 3);
        // When I create a `Rational` value as a copy of the original `Rational` value
        Rational value = new Rational(original);
        // Then the copy's value should have numerator 2
        assertThat("the numerator should be 2", value.numerator(), is(2));
        // And the copy's value should have denominator 3
        assertThat("the denominator should be 3", value.denominator(), is(3));
    }
    //#endregion

    //#region test recip, opp, and neg denominator
    public void test_Negative_Denominator()
    {
        // Given I have created a `Rational` value using `48` and `-72`
        Rational value = new Rational(48, -72);
        // Then the value should have numerator `-2`
        assertThat("48 / -72 = -2 / 3", value.numerator(), is(-2));
        // And the value should have denominator `3`
        assertThat("48 / -72 = -2 / 3", value.denominator(), is(3));
    }

    public void test_Denominator_0()
    {
        assertThrows(IllegalArgumentException.class, () -> new Rational(1, 0));
    }

    public void test_Opposite()
    {
        //Given that I have created the Rational value 2/3
        Rational value = new Rational(2, 3);
        //When I compute the opposite of the value
        Rational opposite = value.opposite();
        //Then the opposite shuld have numerator -2
        assertThat("the opposite of 2 is -2", opposite.numerator(), is(-2));
        //And the opposite should have denominator 3
        assertThat("the denominator should be 3", opposite.denominator(), is(3));
    }

    public void test_Happy_Reciprocal()
    {
        //Given that I have created the Rational value 2/3
        Rational value = new Rational(2, 3);
        //When I compute the reciprocal of the value
        Rational reciprocal = value.reciprocal();
        //Then the reciprocal should have numerator 3
        assertThat("the numerator should be 3", reciprocal.numerator(), is(3));
        //And the reciprocal should have denominator 2
        assertThat("the denominator should be 2", reciprocal.denominator(), is(2));
    }

    public void test_Sad_Reciprocal()
    {
        //Given that I have created the Rational value 0
        Rational value = new Rational(0);
        //When I compute the reciprocal of the value
        //Then an exception should be thrown
        assertThrows(IllegalArgumentException.class, value::reciprocal);
    }
    //#endregion

    //#region comparisons
    public void test_happy_Equal()
    {
        Rational r = new Rational(3, 2);
        Rational r2 = new Rational(6, 4);

        boolean areEqual = r.equals(r2);

        assertThat("r = r2", areEqual, is(true));
    }

    public void test_sad_Equal()
    {
        Rational r = new Rational(3, 2);
        Rational r2 = new Rational(2, 3);

        boolean areEqual = r.equals(r2);

        assertThat("r =/= r2", areEqual, is(false));
    }

    public void test_sad2_Equal()
    {
        Rational r = new Rational(3, 2);
        Rational r2 = new Rational(3, 4);

        boolean areEqual = r.equals(r2);

        assertThat("r =/= r2", areEqual, is(false));
    }
    
    public void test_happy_greaterThan()
    {
        Rational r = new Rational(3, 2);
        Rational r2 = new Rational(3, 4);

        boolean isGreater = r.greaterThan(r2);

        assertThat("r > r2", isGreater, is(true));
    }

    public void test_sad_greaterThan()
    {
        Rational r = new Rational(3, 20);
        Rational r2 = new Rational(3, 4);

        boolean isGreater = r.greaterThan(r2);

        assertThat("r is not greater than r2", isGreater, is(false));
    }

    public void test_happy_lessThan()
    {
        Rational r = new Rational(3, 20);
        Rational r2 = new Rational(3, 4);

        boolean isLess = r.lessThan(r2);

        assertThat("r < r2", isLess, is(true));
    }

    public void test_sad_lessThan()
    {
        Rational r = new Rational(3, 2);
        Rational r2 = new Rational(3, 4);

        boolean isLess = r.lessThan(r2);

        assertThat("r is not less than r2", isLess, is(false));
    }
    //#endregion

    //#region test operations
    public void test_Plus()
    {
        // Given that I have created Rationals representing 1/3 and 2/3
        Rational t = new Rational(1, 3);
        Rational u = new Rational(2, 3);
        // When I compute the value of 1/3 plus 2/3
        Rational result = t.plus(u);
        // Then the result should be 3/3 or 1
        assertThat("1 + 2 = 3", result.numerator(), is(3));
        assertThat("3 = 3", result.denominator(), is(3));
    }

    public void test_Minus()
    {
        // Given that I have created Rationals representing 3/4 and 1/2
        Rational r = new Rational(3, 4);
        Rational s = new Rational(1, 2);
        // When I compute the value of 3/4 minus 1/2
        Rational result = r.minus(s);
        // Then the result should be 1/4
        assertThat("3 - (2)1 = 1", result.numerator(), is(1));
        assertThat("4 = 4", result.denominator(), is(4));
    }

    public void test_Times()
    {
        // Given that I have created Rationals representing 2/3 and 5/7
        Rational p = new Rational(2, 3);
        Rational q = new Rational(5, 7);
        // When I compute the value of 2/3 times 5/7
        Rational result = p.times(q);
        // Then the result should be 10/21
        assertThat("2 * 5 = 10", result.numerator(), is(10));
        assertThat("3 * 7 = 21", result.denominator(), is(21));
    }

    public void test_DivideBy()
    {
        // Given that I have created Rationals representing 2/3 and 5/7
        Rational p = new Rational(2, 3);
        Rational q = new Rational(5, 7);
        // When I compute the value of 2/3 divided by 5/7
        Rational result = p.dividedBy(q);
        // Then the result should be 14/15
        assertThat("2 * 7 = 14", result.numerator(), is(14));
        assertThat("3 * 5 = 15", result.denominator(), is(15));
    }

    public void test_Raised_To_The_Power_Of()
    {
        // Given that I have created a Rational representing 2/3
        Rational x = new Rational(2, 3);
        // When I raise x to the power of 2
        Rational result = x.raisedToThePowerOf(2);
        // Then the result should be 4/9 because (2/3)^2 = 4/9
        assertThat("2^2 = 4", result.numerator(), is(4));
        assertThat("3^2 = 9", result.denominator(), is(9));
    }
    //#endregion

    //#region test 0, 1, -1
    public void test_Happy_is_0()
    {
        Rational z = new Rational();
        boolean isZero = z.isZero();
        assertThat("0 = 0", isZero, is(true));
    }

    public void test_Sad_is_0()
    {
        Rational z = new Rational(3);
        boolean isZero = z.isZero();
        assertThat("3 =/= 0", isZero, is(false));
    }

    public void test_Happy_is_1()
    {
        Rational z = new Rational(1);
        boolean isOne = z.isOne();
        assertThat("1 = 1", isOne, is(true));
    }

    public void test_Sad_is_1()
    {
        Rational z = new Rational();
        boolean isOne = z.isOne();
        assertThat("0 =/= 1", isOne, is(false));
    }

    public void test_Happy_is_Neg1()
    {
        Rational z = new Rational(-1);
        boolean isNegOne = z.isMinusOne();
        assertThat("0 = 0", isNegOne, is(true));
    }

    public void test_Sad_is_Neg1()
    {
        Rational z = new Rational();
        boolean isNegOne = z.isMinusOne();
        assertThat("0 =/= -1", isNegOne, is(false));
    }
    //#endregion

    //#region test toString
    public void test_toString()
    {
        Rational r = new Rational(3, 7);
        String tS = r.toString();

        assertThat("3 / 7 = "+tS, tS, is("3 / 7"));
    }

    public void test_whole_toString()
    {
        Rational r = new Rational(4);
        String tS = r.toString();

        assertThat("4 = "+tS, tS, is("4"));
    }
    //#endregion

    //#region test overrides
    public void test_DoubleValue() 
    {
        Rational r = new Rational(1, 2);
        double result = r.doubleValue();
        assertThat("1/2 as double should be 0.5", result, is(0.5));
    }

    public void test_FloatValue() 
    {
        Rational r = new Rational(1, 2);
        float result = r.floatValue();
        assertThat("1/2 as float should be 0.5", result, is(0.5f));
    }

    public void test_IntValue() 
    {
        Rational r = new Rational(3, 2);
        int result = r.intValue();
        assertThat("3/2 as int should truncate to 1", result, is(1));
    }

    public void test_LongValue() 
    {
        Rational r = new Rational(5, 2);
        long result = r.longValue();
        assertThat("5/2 as long should truncate to 2", result, is(2L));
    }

    public void test_CompareTo() 
    {
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(2, 3);
        int result = r1.compareTo(r2.doubleValue());
        assertThat("1/2 should be less than 2/3", result, is(-1));
    
        result = r2.compareTo(r1.doubleValue());
        assertThat("2/3 should be greater than 1/2", result, is(1));
    
        Rational r3 = new Rational(2, 4);
        result = r1.compareTo(r3.doubleValue());
        assertThat("1/2 should be equal to 2/4", result, is(0));
    }
    //#endregion
}