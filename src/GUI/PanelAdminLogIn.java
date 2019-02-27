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
    private JButton btnBack;

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

        btnBack = new JButton("BACK");
        btnBack.setPreferredSize(new Dimension(80, 30));

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

        GridBagConstraints gcButton2 = new GridBagConstraints();
        gcButton2.fill = GridBagConstraints.VERTICAL;
        gcButton2.gridx = 0;
        gcButton2.gridy = 10;
        gcButton2.insets = new Insets(300, 10 ,0 ,0);

        pnlLogIn.add(txtUsername, gcUsername);
        pnlLogIn.add(txtPassword, gcPassword);
        pnlLogIn.add(btnLogIn, gcButton);
        pnlLogIn.add(btnBack,gcButton2);
        add(pnlLogIn, BorderLayout.CENTER);
    }

    public void setAdminController(AdminController adminController){
        this.adminController = adminController;
    }

    private void registerListeners(){
        btnLogIn.addActionListener(new LogInButtonListener());
        btnBack.addActionListener(new BackButtonlistener());
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

    private class BackButtonlistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnBack){
                adminController.onBackButtonPressed("PanelAdminLogIn");
            }
        }
    }

}
