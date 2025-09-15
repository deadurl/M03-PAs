import java.util.Arrays;
import java.math.BigInteger;

// Abstract superclass
abstract class GeometricObject 
{
  private String color = "white";
  private boolean filled;

  public GeometricObject() {}

  public GeometricObject(String color, boolean filled) 
  {
    this.color = color;
    this.filled = filled;
  }
  //getters
  public String getColor() {return color;}
  public boolean isFilled() {return filled;}

  //setters
  public void setColor(String color) {this.color = color;}
  public void setFilled(boolean filled) {this.filled = filled;}

  public String toString() {return "Color: " + color + ", Filled: " + filled;}

  //abstract methods
  public abstract double getArea();
  public abstract double getPerimeter();
}

//extending GO and sorting
class Circle extends GeometricObject implements Comparable<Circle> 
{
  private double radius; //radius

  public Circle() {radius = 1.0;}//default
  public Circle(double r) {radius = r;}

  public Circle(double r, String color, boolean filled) //parameterized constructor
  {
    super(color, filled);
    radius = r;
  }

  public double getRadius() {return radius;} //getter
  public void setRadius(double r) {radius = r;}//setter

  public double getArea() {return 3.14159 * radius * radius;}//formula for area
  public double getPerimeter() {return 2 * 3.14159 * radius;} //formula for perimeter

  //compare circles by radius
  public int compareTo(Circle other) 
  {
    if (this.radius > other.radius) return 1;
    else if (this.radius < other.radius) return -1;
    else return 0; //if the radius is equal then return 0
  }

  //check if it is equal
  public boolean equals(Object obj) 
  {
    if (obj instanceof Circle) 
    {
      Circle c = (Circle) obj;
      return this.radius == c.radius;
    }
    return false; //if the circle is not equal then return false
  }

  public String toString() {return "radius: " + radius;}
}

//test
public class M03PA1 
{
  public static void main(String[] args) 
  {
    String[] cities = {"Savannah", "Boston", "Atlanta", "Tampa"};
    Arrays.sort(cities);
    System.out.print("Sorted cities: ");
    for (String city : cities) 
    {
      System.out.print(city + " ");
    }
    System.out.println();

    //create new circle objects for cities
    Circle Savannah = new Circle(1.0);
    Circle Boston = new Circle(1.0);
    Circle Atlanta = new Circle(3.0);
    Circle Tampa = new Circle(4.2);

    //test output, radius of cities
    System.out.println("Savannah's " + Savannah);
    System.out.println("Boston's " + Boston);
    System.out.println("Atlanta's " + Atlanta);
    System.out.println("Tampa's " + Tampa);

    //test output, comparable cities
    System.out.println("Savannah the same size as Atlanta? " + (Savannah.compareTo(Atlanta) == 0 ? "yes" : "no"));
    System.out.println("Boston the same size as Tampa? " + (Boston.equals(Tampa) ? "yes" : "no"));
    System.out.println("Atlanta the same size as Tampa? " + (Atlanta.compareTo(Tampa) == 0 ? "yes" : "no"));

    //big number sorting
    BigInteger[] hugeNumbers = 
    {
      new BigInteger("2323231092923992"),
      new BigInteger("432232323239292"), 
      new BigInteger("54623239292")
    };
    Arrays.sort(hugeNumbers);
    System.out.print("\nSorted big numbers: ");
    for (BigInteger number : hugeNumbers)
    {
      System.out.print(number + " ");
    }
    System.out.println();
  }
}
