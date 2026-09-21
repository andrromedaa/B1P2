import java.util.Scanner;

abstract class Shape {

    private static int counter = 1000;
    private final String shapeId;

    Shape() {
        counter++;
        shapeId = "SH-" + counter;
    }

    public String getShapeId() {
        return shapeId;
    }

    public abstract double calculateArea();

    // Overloaded method 1
    public void scale(double factor) {
        System.out.println("Shape scaled by " + factor);
    }

    // Overloaded method 2
    public void scale(double xFactor, double yFactor) {
        System.out.println("Shape scaled by " + xFactor +
                " and " + yFactor);
    }
}

class CircleShape extends Shape {

    private double radius;

    CircleShape(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void scale(double factor) {
        radius = radius * factor;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        radius = radius * xFactor;
    }
}

class SquareShape extends Shape {

    private double side;

    SquareShape(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public void scale(double factor) {
        side = side * factor;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        side = side * xFactor;
    }
}

public class Problem1 {

    static void printArea(Shape s) {
        System.out.println("Area = " + s.calculateArea());
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter radius of circle: ");
        double radius = in.nextDouble();

        System.out.print("Enter side of square: ");
        double side = in.nextDouble();

        CircleShape c = new CircleShape(radius);
        SquareShape sq = new SquareShape(side);

        System.out.println("\nCircle Area:");
        printArea(c);

        System.out.println("\nSquare Area:");
        printArea(sq);

        System.out.print("\nEnter scale factor for square: ");
        double factor = in.nextDouble();

        sq.scale(factor);

        System.out.println("New square area:");
        printArea(sq);

        System.out.println("\nCircle ID: " + c.getShapeId());
        System.out.println("Square ID: " + sq.getShapeId());

        in.close();
    }
}