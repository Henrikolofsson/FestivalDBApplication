package Controllers;

import GUI.Window;
import Interfaces.AdminListener;

public class AdminController implements AdminListener {
    private Window window;
    private SQLController sqlController;

    public AdminController(Window window, SQLController sqlController){
        this.window = window;
        this.sqlController = sqlController;
    }

    public void logIn(String username, String password){
        if(sqlController.logIn(username,password)){
            System.out.println("Log in success");
            window.setManagePanel();
        } else {
            System.out.println("Log in failure");
        }
    }

    @Override
    public void setLogInPanel() {
        System.out.println("PUSHED BUTTON");
        window.setLogInPanel();
    }
}
