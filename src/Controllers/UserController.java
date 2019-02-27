package Controllers;

import GUI.Window;
import Interfaces.UserListener;

public class UserController implements UserListener {
    private Window window;
    private SQLController sqlController;

    public UserController(Window window, SQLController sqlController){
        this.window = window;
        this.sqlController = sqlController;
    }

    @Override
    public void setUserPanel() {
        window.setUserPanel();
    }

    @Override
    public void setBandInformationPanel() {
        window.setBandInformationPanel();
    }

    @Override
    public void setSchedulePanel() {
        window.setSchedulePanel();
    }

    public void onBackButtonPressed(String currentPanel){
        window.onBackButtonPressed(currentPanel);
    }


}
