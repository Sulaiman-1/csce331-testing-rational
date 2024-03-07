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
        this(0);
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
        try 
        {
            int newNumerator = Math.addExact(Math.multiplyExact(r.denominator(), theNumerator), Math.multiplyExact(theDenominator, r.numerator()));
            int newDenominator = Math.multiplyExact(theDenominator, r.denominator());
        
            return new Rational(newNumerator, newDenominator);
        } 
        catch (ArithmeticException e) 
        {
            throw new ArithmeticException("Overflow occured");
        }
        
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
        try
        {
            int newNumerator = Math.multiplyExact(theNumerator, r.numerator());
            int newDenominator = Math.multiplyExact(theDenominator, r.denominator());
            return new Rational(newNumerator, newDenominator);
        }
        catch(ArithmeticException e)
        {
            throw new ArithmeticException("Overflow occured");
        }
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

        long newNumerator = (long)Math.pow(theNumerator, n);
        long newDenominator = (long)Math.pow(theDenominator, n);

        

        if(newNumerator > Integer.MAX_VALUE || newDenominator > Integer.MAX_VALUE)
        {
            throw new ArithmeticException("Overflow has occured");
        }
        else
        {
            return new Rational((int)newNumerator, (int)newDenominator);
        }
        
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
        
        if(theNumerator * r.denominator() > r.numerator() * theDenominator)
        {
            return true;
        }
        return false;
    }
    //  - less than
    public boolean lessThan(Object o)
    {
        Rational r = (Rational)o;
        
        if(theNumerator * r.denominator() < r.numerator() * theDenominator)
        {
            return true;
        }
        return false;
    }
//#endregion

//#region is 0, 1, -1
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
//#endregion
    
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

//#region override functions
    @Override
    public int compareTo(Number arg0) 
    {
        double thisValue = doubleValue();
        double thatValue = arg0.doubleValue();

        return Double.compare(thisValue, thatValue);
    }

    @Override
    public double doubleValue() 
    {
        return (double) theNumerator / theDenominator;
    }

    @Override
    public float floatValue() 
    {
        return (float) theNumerator / theDenominator;
    }

    @Override
    public int intValue() 
    {
        return theNumerator / theDenominator;
    }

    @Override
    public long longValue() 
    {
        return (long) theNumerator / theDenominator;
    }
//#endregion
}
