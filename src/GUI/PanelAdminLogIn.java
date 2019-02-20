package GUI;

import Controllers.AdminController;
import Controllers.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAdminLogIn extends JPanel {
    private AdminController adminController;
    private JPanel pnlLogIn;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JButton btnLogIn;

    public PanelAdminLogIn(){
        setLayout(new BorderLayout());
        initializeComponents();
        registerListeners();
    }

    private void initializeComponents(){
        pnlLogIn = new JPanel(new GridBagLayout());
        txtUsername = new JTextField("Username");
        txtPassword = new JTextField("Password");
        btnLogIn = new JButton("Log In");

        GridBagConstraints gcUsername = new GridBagConstraints();
        gcUsername.fill = GridBagConstraints.VERTICAL;
        gcUsername.gridx = 0;
        gcUsername.gridy = 0;

        GridBagConstraints gcPassword = new GridBagConstraints();
        gcPassword.fill = GridBagConstraints.VERTICAL;
        gcPassword.gridx = 0;
        gcPassword.gridy = 1;
        gcPassword.insets = new Insets(10, 0, 0 ,0);

        GridBagConstraints gcButton = new GridBagConstraints();
        gcButton.fill = GridBagConstraints.VERTICAL;
        gcButton.gridx = 0;
        gcButton.gridy = 2;
        gcButton.insets = new Insets(10, 0 ,0 ,0);

        pnlLogIn.add(txtUsername, gcUsername);
        pnlLogIn.add(txtPassword, gcPassword);
        pnlLogIn.add(btnLogIn, gcButton);
        add(pnlLogIn, BorderLayout.CENTER);
    }

    public void setAdminController(AdminController adminController){
        this.adminController = adminController;
    }

    private void registerListeners(){
        btnLogIn.addActionListener(new LogInButtonListener());
    }

    private class LogInButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnLogIn){
                adminController.logIn(txtUsername.getText(), txtPassword.getText());
                System.out.println("USERNAME: " + txtUsername.getText() + ", PASSWORD: " + txtPassword.getText());
            }
        }
    }

}
