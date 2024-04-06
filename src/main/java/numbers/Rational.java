package numbers;

public class Rational extends Number implements Comparable<Number>
{
    private int theNumerator;
    private int theDenominator;

//#region utilities
    private int gcd(int a, int b) 
    {
        return b == 0 ? Math.abs(a) : gcd(b, a % b);
    }

    // public Rational numberToRational(Number n) 
    // {
    //     double value = n.doubleValue();
    //     return doubleToRational(value);
    // }
    
    // private Rational doubleToRational(double value) 
    // {
    //     int denominator = 1000000; // Arbitrary choice to convert to fraction
    //     int numerator = (int)(value * denominator);
    //     return new Rational(numerator, denominator);
    // }

//#endregion

//#region getters
    public int numerator() { return theNumerator; }

    public int denominator() { return theDenominator; }

    public Rational opposite() 
    {
        if(theNumerator == Integer.MIN_VALUE)
        {
            throw new IllegalArgumentException();
        }
        return new Rational(-1 * theNumerator, theDenominator);
    }

    public Rational reciprocal() { return new Rational(theDenominator, theNumerator); }
//#endregion

//#region constructors
    Rational() { this(0); }

    Rational(int a) { this(a, 1); }

    Rational (Rational r) { this(r.numerator(), r.denominator()); }

    Rational(int a, int b)
    {
        if(b == 0)
        {
            throw new IllegalArgumentException("Denominator cannot be 0");
        }
        try 
        {
            if(a == 0)
            {
                b = 1;
            }
            else if(a == b)
            {
                theNumerator = 1;
                theDenominator = 1;
            }

            else if(b < 0) 
            {
                a = Math.negateExact(a);
                b = Math.negateExact(b);
            }
        } 
        catch (ArithmeticException e) 
        {
            throw new IllegalArgumentException("Overflow when negating");
        }
    
        int gcd = gcd(a, b);
        
        theNumerator = a / gcd;
        theDenominator = b / gcd;
    }
//#endregion

//#region operations
    public Rational plus(Rational r)
    {
        try 
        {
            if(theDenominator == r.theDenominator)
            {
                int newNumerator = Math.addExact(theNumerator, r.theNumerator);
                return new Rational(newNumerator, theDenominator);
            }

            int newNumerator = Math.addExact(Math.multiplyExact(theNumerator, r.theDenominator), Math.multiplyExact(r.theNumerator, theDenominator));
            int newDenominator = Math.multiplyExact(theDenominator, r.theDenominator);
            return new Rational(newNumerator, newDenominator);
        }
        catch (Exception e) 
        {
            throw new IllegalArgumentException(e);
        }
    }

    public Rational minus(Rational r)
    {
        try 
        {
            if(theDenominator == r.theDenominator)
            {
                int newNumerator = Math.subtractExact(theNumerator, r.theNumerator);
                return new Rational(newNumerator, theDenominator);
            }

            int newNumerator = Math.subtractExact(Math.multiplyExact(theNumerator, r.theDenominator), Math.multiplyExact(r.theNumerator, theDenominator));
            int newDenominator = Math.multiplyExact(theDenominator, r.theDenominator);
            
            return new Rational(newNumerator, newDenominator);
        } 
        catch (ArithmeticException e) 
        {
            throw new IllegalArgumentException(e);
        }
    }

    public Rational times(Rational r)
    {
        try 
        {
            Rational r2 = new Rational(theNumerator, r.theDenominator);
            Rational r3 = new Rational(r.theNumerator, theDenominator);
            
            int newNumerator = Math.multiplyExact(r2.numerator(), r3.numerator());
            int newDenominator = Math.multiplyExact(r2.denominator(), r3.denominator());

            return new Rational(newNumerator, newDenominator);
        }
        catch (ArithmeticException e) 
        {
            throw new IllegalArgumentException(e);
        }
    }

    public Rational dividedBy(Rational r)
    {
        return this.times(r.reciprocal());
    }

    public Rational raisedToThePowerOf(int n)
    {
        if (theNumerator == 0 || n == Integer.MAX_VALUE || n == Integer.MIN_VALUE) 
        {
            if( n < 0)
            {
                throw new IllegalArgumentException("Cannot raise 0 to a negative power");
            }
        }

        try 
        {
            int newNumerator = theNumerator;
            int newDenominator = theDenominator;

            if(n < 0)
            {
                newNumerator = theDenominator;
                newDenominator = theNumerator;
            }
            

            int power = Math.abs(n);
            
            //TODO: may not need long

            long tempNumerator = 1;
            long tempDenominator = 1;

            for (int i = 0; i < power; i++) 
            {
                tempNumerator = Math.multiplyExact(tempNumerator, newNumerator);
                tempDenominator = Math.multiplyExact(tempDenominator, newDenominator);
            }

            // Check for overflow
            if (tempNumerator > Integer.MAX_VALUE || tempDenominator > Integer.MAX_VALUE) 
            {
                throw new ArithmeticException("Result causes positive overflow");
            }
      
            return new Rational((int)tempNumerator, (int)tempDenominator);
        } 
        catch (ArithmeticException e) 
        {
            throw new IllegalArgumentException(e);
        }
    }
//#endregion

//#region comparisons
public boolean equals(Object o) 
{
    if (this == o) return true;

    if (!(o instanceof Number)) return false;

    if (o instanceof Rational) 
    {
        Rational r = (Rational) o;
        return this.theNumerator == r.theNumerator && this.theDenominator == r.theDenominator;
    }

    if (o instanceof Integer || o instanceof Long || o instanceof Byte || o instanceof Short) 
    {
        return this.denominator() == 1 && this.numerator() == ((Number) o).longValue();
    }

    double otherValue = ((Number) o).doubleValue();
    double diff = Math.abs(this.doubleValue() - otherValue);
    double tolerance = (o instanceof Float) ? Math.pow(2, -20) : Math.pow(2, -40);

    return diff < tolerance;
}


    public boolean greaterThan(Number n) 
    {
        return this.compareTo(n) == 1;

    }

    public boolean greaterThan(Rational r)
    {
        long lhs = (long) this.theNumerator * r.theDenominator;
        long rhs = (long) r.theNumerator * this.theDenominator;
    
        // If this object's value is greater than r's value, return true
        return lhs > rhs;
    }

    public boolean lessThan(Number n) 
    {
        return this.compareTo(n) == -1;
    }

    public boolean lessThan(Rational r)
    {

        long lhs = (long) this.theNumerator * r.theDenominator;
        long rhs = (long) r.theNumerator * this.theDenominator;

        // If this object's value is less than r's value, return true
        return lhs < rhs;
    }

    //#endregion

//#region value checks (-1, 0, 1)
    public boolean isZero()
    {
        if(theNumerator == 0)
        {
            return true;
        }
        return false;
    }

    public boolean isOne()
    {
        if(theNumerator == theDenominator)
        {
            return true;
        }
        return false;
    }

    public boolean isMinusOne()
    {
        if(theNumerator == -1 * theDenominator)
        {
            return true;
        }
        return false;
    }

    public String toString()
    {
        return(theDenominator == 1) ? theNumerator+"" : theNumerator +"/"+theDenominator;
    }
    //#endregion

//#region override methods
    @Override
    public int compareTo(Number arg0) 
    {
        if(equals(arg0))
        {
            return 0;
        }

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