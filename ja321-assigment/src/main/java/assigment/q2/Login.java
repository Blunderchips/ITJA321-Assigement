package assigment.q2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Login window manager.  Created 31/07/2018.
 *
 * @author Matthew Van der Bijl (xq9x3wv31)
 * @see JFrame
 * @see ActionListener
 */
public class Login implements ActionListener {

    private JFrame window;
    private JTextField txtUsername;
    private JPasswordField txtPassword;

    public Login() {

    }

    public static void main(String[] args) {
        registerClass register = new registerClass();
        register.studentpresent();
    }

    public void myLogin() {
        this.window = new JFrame("Login"); // add title
        this.window.setLayout(new FlowLayout()); // set flow layout

        // username
        this.window.add(new JLabel("Username"));
        this.txtUsername = new JTextField(10);
        this.window.add(txtUsername);
        // --

        // password
        this.window.add(new JLabel("Password"));
        this.txtPassword = new JPasswordField(10);
        this.window.add(txtPassword);
        // --

        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(this);
        this.window.add(btnOk);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);
        this.window.add(btnCancel);

        this.window.setResizable(false);
        this.window.setSize(200, 125);
        this.window.setLocationRelativeTo(null); // move to center of screen
        this.window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        switch (evt.getActionCommand().toLowerCase()) {
            case "ok":
                this.window.setVisible(false);
                this.window.dispose();
                break;
            case "cancel":
                this.window.setVisible(false);
                this.window.dispose();
                System.out.println("Good bye(:");
                break;
        }
    }

    public String getUsername() {
        return txtUsername.getText().trim();
    }

    public String getPassowrd() {
        return txtPassword.getText().trim();
    }

    public boolean isVisible() {
        System.out.println(window.isVisible());
        return window.isVisible();
    }
}
