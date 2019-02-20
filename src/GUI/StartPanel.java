package GUI;

import Controllers.AdminController;
import Controllers.UserController;
import Interfaces.AdminListener;
import Interfaces.UserListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class StartPanel extends JPanel {
    private AdminController adminController;
    private UserController userController;
    private JPanel panelButtons;
    private JButton btnAdmin;
    private JButton btnUser;
    private ArrayList<AdminListener> adminListeners = new ArrayList<>();
    private ArrayList<UserListener> userListeners = new ArrayList<>();

    public StartPanel(){
        setLayout(new BorderLayout());
        initializeComponents();
        registerListeners();
    }

    private void initializeComponents(){
        panelButtons = new JPanel(new GridBagLayout());
        panelButtons.setPreferredSize(new Dimension(800, 100));
        btnAdmin = new JButton("Admin");
        btnUser = new JButton("User");
        btnAdmin.setPreferredSize(new Dimension(140,80));
        btnUser.setPreferredSize(new Dimension(140, 80));

        GridBagConstraints gc1 = new GridBagConstraints();
        gc1.fill = GridBagConstraints.VERTICAL;
        gc1.gridx = 0;
        gc1.gridy = 0;

        GridBagConstraints gc2 = new GridBagConstraints();
        gc1.fill = GridBagConstraints.VERTICAL;
        gc1.gridx = 0;
        gc1.gridy = 1;
        gc1.insets = new Insets(10, 0, 0 ,0);


        panelButtons.add(btnAdmin, gc2);
        panelButtons.add(btnUser, gc1);
        add(panelButtons, BorderLayout.CENTER);

        panelButtons.setBackground(Color.ORANGE);
        setBackground(Color.ORANGE);

    }

    private void registerListeners(){
        btnAdmin.addActionListener(new ButtonAdminListener());
        btnUser.addActionListener(new ButtonUserListener());
    }

    public void addAdminListener(){
        adminListeners.add(adminController);
    }

    public void addUserListener(){
        userListeners.add(userController);
    }

    public void setAdminController(AdminController adminController){
        this.adminController = adminController;
    }

    public void setUserController(UserController userController){
        this.userController = userController;
    }


    private class ButtonAdminListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnAdmin){
                for(AdminListener a : adminListeners){
                    a.setLogInPanel();
                }
            }
        }
    }

    private class ButtonUserListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnUser){
                System.out.println("LEFTY");
            }
        }
    }



}
