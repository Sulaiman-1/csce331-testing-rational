package numbers;
public class Rational extends Number implements Comparable<Number>
{
    private int theNumerator;
    private int theDenominator;

//#region utilities
    private int gcd(int a, int b) 
    {
        return b == 0 ? a : gcd(b, a % b);
    }

    public Rational numberToRational(Number n) 
    {
        if (n instanceof Rational) 
        {
            return (Rational) n;
        } 
 
        else 
        { // For Float, Double, and other number types
            // Convert to a double, but be aware this might not be perfectly accurate for very large or very precise numbers
            double value = n.doubleValue();
            return doubleToRational(value);
        }
    }
    
    private Rational doubleToRational(double value) 
    {
        // This is a simplistic conversion that may not handle very precise or very large values well.
        // Consider using a more sophisticated method for converting double to rational in real applications.
        int denominator = 1000000; // Arbitrary choice to convert to fraction
        int numerator = (int)(value * denominator);
        return new Rational(numerator, denominator);
    }

//#endregion

//#region getters
    public int numerator() { return theNumerator; }

    public int denominator() { return theDenominator; }

    public Rational opposite() { return new Rational(-1 * theNumerator, theDenominator); }

    public Rational reciprocal() { return new Rational(theDenominator, theNumerator); }
//#endregion

//#region constructors
    Rational() { this(0); }

    Rational(int a) { this(a, 1); }

    Rational (Rational r) { this(r.numerator(), r.denominator()); }

    Rational(int a, int b) //could overflow
    {
        if(b == 0)
        {
            throw new IllegalArgumentException("Denominator cannot be 0");
        }
        try 
        {
            if(b < 0) 
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
            int newNumerator = Math.addExact(Math.multiplyExact(theNumerator, r.theDenominator), Math.multiplyExact(r.theNumerator, theDenominator));
            int newDenominator = Math.multiplyExact(theDenominator, r.theDenominator);
            //
            return new Rational(newNumerator, newDenominator);
        }
        catch (ArithmeticException e) 
        {
            throw new IllegalArgumentException("Overflow occurred during addition");
        }
    }

    public Rational minus(Rational r)
    {
        try 
        {
            int newNumerator = Math.subtractExact(Math.multiplyExact(theNumerator, r.theDenominator), Math.multiplyExact(r.theNumerator, theDenominator));
            int newDenominator = Math.multiplyExact(theDenominator, r.theDenominator);
            return new Rational(newNumerator, newDenominator);
        } 
        catch (ArithmeticException e) 
        {
            throw new IllegalArgumentException("Overflow occurred during subtraction");
        }
    }

    public Rational times(Rational r)
    {
        try 
        {
            int newNumerator = Math.multiplyExact(theNumerator, r.theNumerator);
            int newDenominator = Math.multiplyExact(theDenominator, r.theDenominator);
            return new Rational(newNumerator, newDenominator);
        } 
        catch (ArithmeticException e) 
        {
            throw new IllegalArgumentException("Overflow occurred during multiplication");
        }
    }

    public Rational dividedBy(Rational r)
    {
        if (r.theNumerator == 0) { throw new IllegalArgumentException("Division by zero"); }
        
        try 
        {
            int newNumerator = Math.multiplyExact(theNumerator, r.theDenominator);
            int newDenominator = Math.multiplyExact(theDenominator, r.theNumerator);
            return new Rational(newNumerator, newDenominator);
        } 
        catch (ArithmeticException e) 
        {
            throw new IllegalArgumentException("Overflow occurred during division");
        }
    }

    public Rational raisedToThePowerOf(int n)
    {
        if (theNumerator == 0) 
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
            throw new IllegalArgumentException("Overflow occurred during exponentiation");
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

        Number n = (Number) o;
        if (n instanceof Integer || n instanceof Long || n instanceof Byte || n instanceof Short) 
        {
            if (this.denominator() == 1) 
            {
                long otherValue = n.longValue();
                return this.numerator() == otherValue;
            }
            return false;
        }

        double otherValue = n.doubleValue();
        double diff = Math.abs(this.doubleValue() - otherValue);
        if (n instanceof Float) 
        {
            return diff < Math.pow(2, -20);
        } 
        else 
        {
            return diff < Math.pow(2, -40);
        }
    }

    public boolean greaterThan(Number n)
    {
        Rational r = numberToRational(n);
        long lhs = (long) theNumerator * r.theDenominator;
        long rhs = (long) r.theNumerator * theDenominator;
        return lhs > rhs;
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
        Rational r = numberToRational(n);
        long lhs = (long) theNumerator * r.theDenominator;
        long rhs = (long) r.theNumerator * theDenominator;
        return lhs < rhs;
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
//#endregion

//#region override methods
    @Override
    public int compareTo(Number arg0) 
    {
        double thisValue = this.doubleValue();
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
