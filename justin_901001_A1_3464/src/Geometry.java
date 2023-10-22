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

    public Triangle(Point p1, Point p2, Point p3) {
        vertices = new Point[] { p1, p2, p3 };
    }

    public double calculatePerimeter() {
        double perimeter = 0;
        for (int i = 0; i < 3; i++) {
            perimeter += vertices[i].distanceTo(vertices[(i + 1) % 3]);
        }
        return perimeter;
    }

    public boolean isIsosceles() {
        double[] sides = new double[3];
        for (int i = 0; i < 3; i++) {
            sides[i] = vertices[i].distanceTo(vertices[(i + 1) % 3]);
        }
        return sides[0] == sides[1] || sides[1] == sides[2] || sides[0] == sides[2];
    }

    public boolean isEquilateral() {
        double[] sides = new double[3];
        for (int i = 0; i < 3; i++) {
            sides[i] = vertices[i].distanceTo(vertices[(i + 1) % 3]);
        }
        return sides[0] == sides[1] && sides[1] == sides[2];
    }
}

public class Geometry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of triangles you want to check with: ");
        int numTriangles = scanner.nextInt();
        Triangle[] triangles = new Triangle[numTriangles];

        for (int i = 0; i < numTriangles; i++) {
            triangles[i] = inputTriangle(scanner, i + 1);
        }

        for (int i = 0; i < numTriangles; i++) {
            double perimeter = triangles[i].calculatePerimeter();
            System.out.println("Perimeter of Triangle " + (i + 1) + " is: " + perimeter);

            if (triangles[i].isEquilateral()) {
                System.out.println("Type: Equilateral");
            } else if (triangles[i].isIsosceles()) {
                System.out.println("Type: Isosceles");
            } else {
                System.out.println("Type: Unknown");
            }
        }

        scanner.close();
    }
    public static Triangle inputTriangle(Scanner scanner, int triangleNumber) {
        System.out.println("Enter the coordinates of Triangle " + triangleNumber + ":");
        Point p1 = inputPoint(scanner, "Vertex 1 (x1 y1)[x1 and y1 seperated by space]:");
        Point p2 = inputPoint(scanner, "Vertex 2 (x2 y2)[x2 and y2 seperated by space]:");
        Point p3 = inputPoint(scanner, "Vertex 3 (x3 y3)[x3 and y3 seperated by space]:");

        return new Triangle(p1, p2, p3);
    }
    public static Point inputPoint(Scanner scanner, String prompt) {
        System.out.print(prompt);
        double x = scanner.nextDouble();
        double y = scanner.nextDouble();
        return new Point(x, y);
    }
}