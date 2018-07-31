package assigment.q2;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * As per assigment specifications, class starts with lower case letter. Created 31/07/2018.
 *
 * @author Matthew Van der Bijl (xq93wv31)
 */
public class registerClass {

    /**
     * @param args Arguments from the command line
     */
    public static void main(String[] args) {
        new registerClass().studentpresent();
    }

    private boolean validate_login(String username, String password) {
        boolean isFound = false; // default to false
        try {
            Connection cnctn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ja_assigment"
                    , "root", "");
            Statement stmt = cnctn.createStatement();

            String q = String.format("SELECT COUNT(*) as 'is_found' from lecturer " +
                    "WHERE username = '%s' AND password = '%s'", username, password);
            System.out.println(q); // for debugging

            ResultSet rs = stmt.executeQuery(q);
            rs.next();

            isFound = rs.getInt("is_found") == 1;

            if (isFound) {
                System.out.println("found");
            } else {
                System.out.println("not found");
            }

            // close connection
            stmt.close();
            cnctn.close();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        return isFound;
    }

    /**
     * Called on the main method only, and only if the username and password are correct.
     */
    public void studentpresent() {
        Login login = new Login();
        login.myLogin();

        while (login.isVisible()) ;

        String username = login.getUsername();
        String password = login.getPassowrd();

        if (!validate_login(username, password)) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "User not found.",
                    "Can't login", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ArrayList<Student> students = new ArrayList<>();

        int numStudents = 0;
        try {
            numStudents = Integer.parseInt(JOptionPane.showInputDialog("How many students are there?"));
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace(System.err);
        }

        for (int i = 0; i < numStudents; i++) {
            // Input
            String name = JOptionPane.showInputDialog("What is the name of the student?");
            String surName = JOptionPane.showInputDialog("What is the student's sirname?");
            int StudnetNo = Integer.parseInt(JOptionPane.showInputDialog("Enter student number?"));
            String subject = JOptionPane.showInputDialog("What is the subject?");

            // Processing
            Student student = new Student(name, surName, StudnetNo, subject);
            student.insertDetails();

            boolean found = false;
            for (Student obj : students) {
                if (obj.getStudnetNo() == StudnetNo) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                students.add(student);
            }
        }

        // Output
        JOptionPane.showMessageDialog(null, students.size() + " students have been added.");

        for (Student student : students) {
            System.out.println(student);
        }
    }
}
