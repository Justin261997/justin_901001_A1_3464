import java.util.Scanner;

class Point {
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Point other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }
}

class Triangle {
    Point[] vertices;

    public Triangle(Point[] vertices) {
        if (vertices.length != 3) {
            throw new IllegalArgumentException("A triangle must have 3 vertices.");
        }
        this.vertices = vertices;
    }

    public double calculatePerimeter() {
        double perimeter = 0;
        for (int i = 0; i < 2; i++) {
            perimeter += vertices[i].distanceTo(vertices[i + 1]);
        }
        perimeter += vertices[2].distanceTo(vertices[0]);
        return perimeter;
    }

    public boolean isEquilateral() {
        double side1 = vertices[0].distanceTo(vertices[1]);
        double side2 = vertices[1].distanceTo(vertices[2]);
        double side3 = vertices[2].distanceTo(vertices[0]);
        return (side1 == side2) && (side2 == side3);
    }

    public boolean isIsosceles() {
        double side1 = vertices[0].distanceTo(vertices[1]);
        double side2 = vertices[1].distanceTo(vertices[2]);
        double side3 = vertices[2].distanceTo(vertices[0]);
        return (side1 == side2) || (side2 == side3) || (side3 == side1);
    }

    public String getType() {
        if (isEquilateral()) {
            return "Equilateral";
        } else if (isIsosceles()) {
            return "Isosceles";
        } else {
            return "Unknown";
        }
    }
}

public class Geometry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of triangles: ");
        int numTriangles = scanner.nextInt();

        Triangle[] triangles = new Triangle[numTriangles];

        for (int i = 0; i < numTriangles; i++) {
            Point[] vertices = new Point[3];
            for (int j = 0; j < 3; j++) {
                System.out.print("Enter the x-coordinate for vertex " + (j + 1) + ": ");
                double x = scanner.nextDouble();
                System.out.print("Enter the y-coordinate for vertex " + (j + 1) + ": ");
                double y = scanner.nextDouble();
                vertices[j] = new Point(x, y);
            }
            triangles[i] = new Triangle(vertices);
        }

        for (int i = 0; i < numTriangles; i++) {
            double perimeter = triangles[i].calculatePerimeter();
            System.out.println("Triangle " + (i + 1) + " is " + triangles[i].getType());
            System.out.println("Perimeter: " + perimeter);
        }
    }
}
