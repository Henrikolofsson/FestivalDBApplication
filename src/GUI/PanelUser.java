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

public class PanelUser extends JPanel {
    private UserController userController;
    private JPanel panelButtons;
    private JButton btnSchedule;
    private JButton btnBandInformation;
    private ArrayList<UserListener> userListeners = new ArrayList<>();
    private ButtonListener listener = new ButtonListener();
    private JButton btnBack;

    public PanelUser(){
        setLayout(new BorderLayout());
        initializeComponents();
        registerListeners();
    }

    private void initializeComponents(){
        panelButtons = new JPanel(new GridBagLayout());
        panelButtons.setPreferredSize(new Dimension(800, 100));
        btnSchedule = new JButton("Schedule");
        btnBandInformation = new JButton("BandInformation");
        btnSchedule.setPreferredSize(new Dimension(140,80));
        btnBandInformation.setPreferredSize(new Dimension(140,80));

        btnBack = new JButton("BACK");

        GridBagConstraints gc1 = new GridBagConstraints();
        gc1.fill = GridBagConstraints.VERTICAL;
        gc1.gridx = 0;
        gc1.gridy = 0;

        GridBagConstraints gc2 = new GridBagConstraints();
        gc1.fill = GridBagConstraints.VERTICAL;
        gc1.gridx = 0;
        gc1.gridy = 1;
        gc1.insets = new Insets(10, 0, 300 ,0);

        GridBagConstraints gc3 = new GridBagConstraints();
        gc3.fill = GridBagConstraints.VERTICAL;
        gc3.gridx = 0;
        gc3.gridy = 10;
        gc3.insets = new Insets(10, 0, 0 ,0);

        panelButtons.add(btnSchedule, gc1);
        panelButtons.add(btnBandInformation, gc2);
        panelButtons.add(btnBack, gc3);
        add(panelButtons, BorderLayout.CENTER);

        panelButtons.setBackground(Color.ORANGE);
        setBackground(Color.ORANGE);
    }

    private void registerListeners(){
        btnSchedule.addActionListener(listener);
        btnBandInformation.addActionListener(listener);
        btnBack.addActionListener(new BackButtonlistener());
    }

    public void addUserListener(){
        userListeners.add(userController);
    }

    public void setUserController(UserController userController){
        this.userController = userController;
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnSchedule){
                for(UserListener ul : userListeners){
                    ul.setSchedulePanel();
                }
            }
            else if (e.getSource() == btnBandInformation){
                for(UserListener ul : userListeners){
                    ul.setBandInformationPanel();
                }
            }
        }
    }

    private class BackButtonlistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnBack){
                userController.onBackButtonPressed("PanelUser");
            }
        }
    }
}
