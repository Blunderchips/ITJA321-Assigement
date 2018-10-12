package assigment.q2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Basic student class. Created 31/07/2018.
 *
 * @author Matthew Van der Bijl (xq9x3wv31)
 */
public class Student {

    private String name;
    private String surName;
    private int StudnetNo;
    private String subject;

    public Student(String name, String surName, int studnetNo, String subject) {
        this.name = name;
        this.surName = surName;
        this.StudnetNo = studnetNo;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getStudnetNo() {
        return StudnetNo;
    }

    public void setStudnetNo(int studnetNo) {
        StudnetNo = studnetNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void insertDetails() {
        try {
            Connection cnctn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ja_assigment"
                    , "root", "");
            Statement stmt = cnctn.createStatement();

            String q = String.format("INSERT INTO `ja_assigment`.`student`(`studentno`,`name`,`surname`,`subject`,`dateEntered`) " +
                    "VALUES(%d,'%s','%s','%s',CURDATE());\n", getStudnetNo(), getName(), getSurName(), getSubject());
            System.out.println(q); // for debugging

            if (!stmt.execute(q)) {
                System.out.println("Added " + name + " to the database.");
            } else {
                System.out.println("failed to add " + name + " to the database.");
            }

            // close connection
            stmt.close();
            cnctn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }


    /**
     * @return student details as a string
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("name='").append(name).append('\'');
        sb.append(", surName='").append(surName).append('\'');
        sb.append(", StudnetNo=").append(StudnetNo);
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", studnetNo=").append(getStudnetNo());
        sb.append('}');
        return sb.toString();
    }
}
