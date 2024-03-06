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


    public void test_Plus()
    {
        // Given that I have created Rationals representing 2/3 and 5/7
        Rational p = new Rational(2, 3);
        Rational q = new Rational(5, 7);
        // When I compute the value of 2/3 plus 5/7
        Rational result = p.plus(q);
        // Then the result should be 10/21
        assertThat("2 * 5 = 10", result.numerator(), is(10));
        assertThat("3 * 7 = 21", result.denominator(), is(21));
    }

    public void test_Minus()
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

}
    
}