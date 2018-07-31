package assigment.q2;

import assigment.q1.OverloadDemo;

/**
 * Main class for assigment question 1.1. Created 31/07/2018.
 *
 * @author Matthew Van der Bijl (xq9x3wv31)
 */
public class Mainclass {

    /**
     * @param args Arguments from the command line
     */
    public static void main(String[] args) {
        OverloadDemo obj = new OverloadDemo();

        System.out.printf("Area of the square is %.2f\n", obj.Area(10d));
        System.out.printf("Area of the rectangle is %.2f\n", obj.Area(20, 50));
        System.out.printf("Area of the circle is %.2f\n", obj.Area(10f));
    }
}
