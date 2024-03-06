package numbers;
public class Rational extends Number implements Comparable<Number>
{
    private int theNumerator;
    private int theDenominator;

    private void simplify()
    {
        int gcd = 1;
        for(int number = 1; number < Math.max(theNumerator, theDenominator); number++)
        {
            if(theNumerator % number == 0 && theDenominator % number == 0)
            {
                gcd = number;
            }
        }

        theNumerator /= gcd;
        theDenominator /= gcd;
    }

//#region getters
    public int numerator()
    {
        return theNumerator;
    }

    public int denominator()
    {
        return theDenominator;
    }

    public Rational opposite()
    {
        return new Rational(-1 * theNumerator, theDenominator);
    }

    public Rational reciprocal()
    {
        return new Rational(theDenominator, theNumerator);
    }
//#endregion

//#region constructors
    //default : 0
    public Rational()
    {
        theNumerator = 0;
        theDenominator = 1;
    }

    //from integer
    public Rational(int a)
    {
        this(a, 1);
    }
    
    //from numerator and denominator
    public Rational(int a, int b)
    {
        if(b < 0)
        {
            a*= -1;
            b*= -1;
        }

        //account for integer overflow
        if(b == 0)
        {
            throw new IllegalArgumentException("Cannot set denominator to 0");
        }
        else
        {
            theNumerator = a;
            theDenominator = b;
        }
        simplify();
    }

    //from rational
    public Rational(Rational r)
    {
        this(r.theNumerator, r.theDenominator);
    }
//#endregion

//#region operations
    //addition
    public Rational plus(Rational r)
    {
        int newNumerator = r.denominator() * theNumerator + theDenominator * r.numerator();
        int newDenominator = theDenominator * r.denominator();
        
        return new Rational(newNumerator, newDenominator);
    }
    //subtraction
    public Rational minus(Rational r)
    {
        int newNumerator = r.denominator() * theNumerator - theDenominator * r.numerator();
        int newDenominator = theDenominator * r.denominator();
        
        return new Rational(newNumerator, newDenominator);
    }
    //multiply
    public Rational times(Rational r)
    {
        return new Rational(theNumerator * r.numerator(), theDenominator * r.denominator());
    }
    //divide
    public Rational dividedBy(Rational r)
    {
        //throw exception
        return new Rational(theNumerator * r.denominator(), theDenominator * r.numerator());
    }
    //raise to a power
    public Rational raisedToThePowerOf(int n)
    {
        return new Rational((int)Math.pow(theNumerator, n), (int)Math.pow(theDenominator, n));
    }
//#endregion

//#region compare rationals
    //  - equality
    public boolean equals(Object o)
    {
        Rational r = (Rational)o;
        if(theNumerator == r.numerator() && theDenominator == r.denominator())
        {
            return true;
        }
        return false;
    }
    //  - greater than
    public boolean greaterThan(Object o)
    {
        Rational r = (Rational)o;
        return true;
    }
    //  - less than
    public boolean lessThan(Object o)
    {
        Rational r = (Rational)o;
        return true;
    }
//#endregion

    //is zero
    public boolean isZero()
    {
        return theNumerator == 0;
    }
    // is one
    public boolean isOne()
    {
        return theNumerator == theDenominator;
    }
    //is negative one
    public boolean isMinusOne()
    {
        return theNumerator == -1*theDenominator;
    }
    
    //pretty print
    public String toString()
    {
        if(theDenominator != 1)
        {
            return theNumerator + " / " + theDenominator;
        }
        else
        {
            return theNumerator+"";
        }
        
    }

    @Override
    public int compareTo(Number arg0) 
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

    @Override
    public double doubleValue() 
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'doubleValue'");
    }

    @Override
    public float floatValue() 
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'floatValue'");
    }

    @Override
    public int intValue() 
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'intValue'");
    }

    @Override
    public long longValue() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'longValue'");
    }
}
