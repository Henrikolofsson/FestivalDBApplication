package Controllers;

import GUI.Window;

public class Main {

    public static void main(String[] args){
        Window window = new Window(800, 800);
        SQLController sqlController = new SQLController();
        AdminController adminController = new AdminController(window, sqlController);
        UserController userController = new UserController(window, sqlController);
        window.setControllers(adminController, userController);
        SQLController.connect();
    }
}
