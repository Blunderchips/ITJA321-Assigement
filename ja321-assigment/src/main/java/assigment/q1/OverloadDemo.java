package assigment.q1;

/**
 * Method Overloading Demo Created 31/0/2018.
 *
 * @author Matthew Van der Bijl (xq9x3wv31)
 */
public class OverloadDemo {

    /**
     * Area of a square.
     *
     * @param side length of single side of the square
     * @return area of the square
     */
    public double Area(double side) {
        return side * side;
    }

    /**
     * Area of a rectangle.
     *
     * @param length  length of a side
     * @param breadth breadth of a side
     * @return area of the rectangle
     */
    public double Area(double length, double breadth) {
        return length * breadth;
    }

    /**
     * Area of a circle.
     *
     * @param radius radius of the circle
     * @return area of the circle
     */
    public double Area(float radius) {
        return radius * Math.PI;
    }
}
