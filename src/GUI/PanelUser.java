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

        GridBagConstraints gc1 = new GridBagConstraints();
        gc1.fill = GridBagConstraints.VERTICAL;
        gc1.gridx = 0;
        gc1.gridy = 0;

        GridBagConstraints gc2 = new GridBagConstraints();
        gc1.fill = GridBagConstraints.VERTICAL;
        gc1.gridx = 0;
        gc1.gridy = 1;
        gc1.insets = new Insets(10, 0, 0 ,0);

        panelButtons.add(btnSchedule, gc1);
        panelButtons.add(btnBandInformation, gc2);
        add(panelButtons, BorderLayout.CENTER);

        panelButtons.setBackground(Color.ORANGE);
        setBackground(Color.ORANGE);
    }

    private void registerListeners(){
        btnSchedule.addActionListener(listener);
        btnBandInformation.addActionListener(listener);
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

                }
            }
            else if (e.getSource() == btnBandInformation){
                for(UserListener ul : userListeners){
                    ul.setBandInformationPanel();
                }
            }
        }
    }
}
