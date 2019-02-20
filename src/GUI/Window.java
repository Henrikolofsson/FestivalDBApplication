package GUI;

import Controllers.AdminController;
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
    }

    private void setStartPanel(JFrame frame){
        frame.add(startPanel);
    }

    public void setLogInPanel(){
        remove(startPanel);
        add(logInPanel);
        setVisible(true);
        pack();
    }

    public void setManagePanel(){
        remove(logInPanel);
        add(managePanel);
        setVisible(true);
        pack();
    }





}
