package assigment.q2;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Registration class. Created 31/07/2018. As per assignment specifications,
 * class starts with lower case letter.
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

    /**
     * @param username username to test
     * @param passwd password to test
     * @return true if user is found
     */
    private boolean validate_login(String username, String passwd) {
        boolean isFound = false; // default to false
        try {
            Connection cnctn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ja_assigment",
                    "root", "");
            Statement stmt = cnctn.createStatement();

            String q = String.format("SELECT COUNT(*) as 'is_found' from lecturer "
                    + "WHERE username = '%s' AND password = '%s'", username, passwd);
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
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                    ex.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
        }
        return isFound;
    }

    /**
     * Called on the main method only, and only if the username and password are
     * correct.
     */
    @SuppressWarnings("empty-statement")
    public void studentpresent() {
        login login = new login();
        login.myLogin(); // show frame

        while (login.isVisible()) ; // wait

        // Get data from login frame
        String username = login.getUsername();
        String password = login.getPassowrd();

        if (!validate_login(username, password)) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "User not found.",
                    "Can't login", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ArrayList<Student> students = new ArrayList<>(); // create new array list

        int numStudents = 0;
        try {
            numStudents = Integer.parseInt(JOptionPane.showInputDialog("How many students are there?"));
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace(System.err);
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Please enter a valid number.",
                    nfe.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (int i = 0; i < numStudents; i++) {
            // Input
            JTextField txtName = new JTextField();
            JTextField txtSurname = new JTextField();
            JTextField txtStudentNum = new JTextField();
            JTextField txtSubject = new JTextField();

            JPanel pnl = new JPanel(new GridLayout(0, 2));
            pnl.add(new JLabel("Name:"));
            pnl.add(txtName);
            pnl.add(new JLabel("Surname:"));
            pnl.add(txtSurname);
            pnl.add(new JLabel("Student Number:"));
            pnl.add(txtStudentNum);
            pnl.add(new JLabel("Subject:"));
            pnl.add(txtSubject);

            JOptionPane.showConfirmDialog(null, pnl, "Login", JOptionPane.OK_OPTION); // show input dialogue

            String name = txtName.getText();
            String surName = txtSurname.getText();
            int StudnetNo = Integer.parseInt(txtStudentNum.getText());
            String subject = txtSubject.getText();

            // Processing
            Student student = new Student(name, surName, StudnetNo, subject);
            student.insertDetails();

            boolean found = false;
            for (Student obj : students) {
                if (obj.getStudnetNo() == StudnetNo) {
                    found = true; // id found
                    break;
                }
            }
            if (!found) {
                students.add(student); // add student to array list
            }
        }

        // Output
        JOptionPane.showMessageDialog(null, students.size() + " students have been added.");

        // print out array list
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
