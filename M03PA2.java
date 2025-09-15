import java.math.*;
import java.util.Scanner;

public class M03PA2 
{
  public static void main(String[] args) //this initial code is from the example
  {
    Scanner input = new Scanner(System.in); 

    
    System.out.print("Enter rational r1 with numerator and denominator separated by a space: ");
    String n1 = input.next(); 
    String d1 = input.next(); 

   
    System.out.print("Enter rational r2 with numerator and denominator separated by a space: ");
    String n2 = input.next(); 
    String d2 = input.next(); 

    
    RationalUsingBigInteger r1 = new RationalUsingBigInteger(new BigInteger(n1), new BigInteger(d1));
    RationalUsingBigInteger r2 = new RationalUsingBigInteger(new BigInteger(n2), new BigInteger(d2));

    //output
    System.out.println(r1 + " + " + r2 + " = " + r1.add(r2)); //addition
    System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2)); //subtraction
    System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2)); //multiplication
    System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2)); //division
    System.out.println(r2 + " is " + r2.doubleValue()); //decimal value of r2

    input.close(); //close input stream
  }
}

class RationalUsingBigInteger extends Number implements Comparable<RationalUsingBigInteger> 
{
  private BigInteger numerator;   
  private BigInteger denominator; 

  //default constructor
  public RationalUsingBigInteger() 
  {
    numerator = BigInteger.ZERO;
    denominator = BigInteger.ONE;
  }

  //parameterized constructor
  public RationalUsingBigInteger(BigInteger n, BigInteger d) 
  {
    if (d.equals(BigInteger.ZERO)) //check for division by zero
    {
      throw new ArithmeticException("Error: cannot be zero"); //throw error if denominator is zero
    }

    //find greatest common denominator
    BigInteger gcd = n.gcd(d);
    n = n.divide(gcd); //simplify numerator
    d = d.divide(gcd); //simplify denominator

    //check positive
    if (d.compareTo(BigInteger.ZERO) < 0) 
    {
      //flip signs
      n = n.negate(); 
      d = d.negate();
    }

    numerator = n;
    denominator = d;
  }

  //getters
  public BigInteger getNumerator() {return numerator;}
  public BigInteger getDenominator() {return denominator;}

  //addition
  public RationalUsingBigInteger add(RationalUsingBigInteger r) 
  {
    //a/b + c/d = (ad + bc) / bd
    BigInteger n = numerator.multiply(r.getDenominator()).add(denominator.multiply(r.getNumerator()));
    BigInteger d = denominator.multiply(r.getDenominator());
    return new RationalUsingBigInteger(n, d); //return new number
  }

  //subtract
  public RationalUsingBigInteger subtract(RationalUsingBigInteger r) 
  {
    //a/b - c/d = (ad - bc) / bd
    BigInteger n = numerator.multiply(r.getDenominator()).subtract(denominator.multiply(r.getNumerator()));
    BigInteger d = denominator.multiply(r.getDenominator());
    return new RationalUsingBigInteger(n, d);
  }

  //multiplication
  public RationalUsingBigInteger multiply(RationalUsingBigInteger r) 
  {
    //(a/b) * (c/d) = (ac) / (bd)
    BigInteger n = numerator.multiply(r.getNumerator());
    BigInteger d = denominator.multiply(r.getDenominator());
    return new RationalUsingBigInteger(n, d);
  }

  //division
  public RationalUsingBigInteger divide(RationalUsingBigInteger r) 
  {
    if (r.getNumerator().equals(BigInteger.ZERO)) {
      throw new ArithmeticException("Division by zero"); // cannot divide by 0
    }

    //(a/b) / (c/d) = (ad) / (bc)
    BigInteger n = numerator.multiply(r.getDenominator());
    BigInteger d = denominator.multiply(r.getNumerator());
    return new RationalUsingBigInteger(n, d);
  }

  //output for "numerator/denominator"
  public String toString() 
  {
    if (denominator.equals(BigInteger.ONE)) {return numerator.toString();} //just numerator if denominator = 1
    else {return numerator + "/" + denominator;} //show fraction if not
  }

  //compare 
  public int compareTo(RationalUsingBigInteger r) 
  {
    //cross multipliplication
    BigInteger left = numerator.multiply(r.getDenominator());
    BigInteger right = denominator.multiply(r.getNumerator());
    return left.compareTo(right); // -1, 0, or 1
  }

  //check if equal
  public boolean equals(Object other) 
  {
    if (!(other instanceof RationalUsingBigInteger)) return false;
    RationalUsingBigInteger r = (RationalUsingBigInteger) other;
    return numerator.equals(r.getNumerator()) && denominator.equals(r.getDenominator());
  }

  //conversion methods 
  public int intValue() {return (int)doubleValue();}
  public long longValue() {return (long)doubleValue();}
  public float floatValue() {return (float)doubleValue();}
  public double doubleValue() {return numerator.doubleValue() / denominator.doubleValue();}
}
