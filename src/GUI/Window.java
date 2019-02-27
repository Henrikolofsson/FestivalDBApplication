package GUI;

import Controllers.AdminController;
import Controllers.SQLController;
import Controllers.UserController;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private AdminController adminController;
    private UserController userController;
    private int width;
    private int height;

    private StartPanel startPanel = new StartPanel();
    private PanelAdminLogIn logInPanel = new PanelAdminLogIn();
    private PanelManage managePanel = new PanelManage();
    private PanelUser userPanel = new PanelUser();
    private PanelBandInformation bandInformationPanel = new PanelBandInformation();
    private PanelSchedule panelSchedule = new PanelSchedule();
    private RetainedPanel retainedPanel = new RetainedPanel();

    public Window(int width, int height){
        this.width = width;
        this.height = height;
        createWindow();
    }

    private void createWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(width, height));
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        setResizable(false);
    }

    public void setControllers(AdminController adminController, UserController userController){
        this.adminController = adminController;
        this.userController = userController;
        initializePanels();
        setStartPanel(this);
    }

    private void initializePanels(){
        startPanel.setAdminController(adminController);
        startPanel.setUserController(userController);
        startPanel.addAdminListener();
        startPanel.addUserListener();

        logInPanel.setAdminController(adminController);
        managePanel.setAdminController(adminController);

        userPanel.setUserController(userController);
        userPanel.addUserListener();

        bandInformationPanel.setUserController(userController);
        panelSchedule.setUserController(userController);
    }

    private void setStartPanel(JFrame frame){
        frame.add(startPanel);
    }

    public void onBackButtonPressed(String currentPanel){
        switch (currentPanel){
            case "PanelAdminLogIn":
                setStartPanel("PanelAdminLogIn");
                break;

            case "PanelManage":
                remove(managePanel);
                add(logInPanel);
                setVisible(true);
                pack();
                repaint();
                break;

            case "PanelUser":
                remove(userPanel);
                add(startPanel);
                setVisible(true);
                pack();
                repaint();
                break;

            case "PanelSchedule":
                remove(panelSchedule);
                add(userPanel);
                setVisible(true);
                pack();
                repaint();
                break;

            case "PanelBandInformation":
                remove(bandInformationPanel);
                add(userPanel);
                setVisible(true);
                pack();
                repaint();
                break;
        }
    }

    public void setStartPanel(String fromPanel){
        if(fromPanel.equals("PanelAdminLogIn")){
            System.out.println("true");
            remove(logInPanel);
            add(startPanel);
            setVisible(true);
            pack();
            repaint();
        }
        else {
            remove(userPanel);
            add(startPanel);
            setVisible(true);
            pack();
            repaint();
        }
    }

    public void setLogInPanel(){
        remove(startPanel);
        add(logInPanel);
        setVisible(true);
        pack();
        repaint();
    }

    public void setManagePanel(){
        remove(logInPanel);
        add(managePanel);
        setVisible(true);
        pack();
        repaint();
    }

    public void setUserPanel(){
        remove(startPanel);
        add(userPanel);
        setVisible(true);
        pack();
        repaint();
    }

    public void setBandInformationPanel(){
        remove(userPanel);
        bandInformationPanel.setBandList(SQLController.getBands());
        add(bandInformationPanel);
        setVisible(true);
        pack();
        repaint();
    }


    public void setSchedulePanel() {
        remove(userPanel);
        panelSchedule.setConcertLists();
        add(panelSchedule);
        setVisible(true);
        pack();
        repaint();
    }
}
