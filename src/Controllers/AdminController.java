package Controllers;

import Entities.Band;
import Entities.BandMember;
import Entities.Concerts;
import Entities.Worker;
import GUI.Window;
import Interfaces.AdminListener;

import java.util.ArrayList;

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

    public void addBand(Band band){
        SQLController.addBand(band);
    }

    public void addWorker(Worker worker){
        SQLController.addWorker(worker);
    }

    public void addBandMember(BandMember bandMember){
        SQLController.addBandMember(bandMember);
    }

    public void addBandMemberAssociation(String bandMemberID, String bandID){
        SQLController.addBandMemberAssociation(bandMemberID, bandID);
    }

    public void addConcertToSchedule(Concerts concert){
        SQLController.addConcertToSchedule(concert);
    }

    public ArrayList<Band> getBands(){
        return SQLController.getBands();
    }

    public ArrayList<Worker> getWorkers(){
        return SQLController.getWorkers();
    }

    public ArrayList<BandMember> getBandMembers(){
       // return SQLController.getBandMembers();
        return null;
    }

    public ArrayList<Concerts> getConsertsFromSchedule(String stage){
       return SQLController.getConcertsFromStage(stage);
    }

    @Override
    public void setLogInPanel() {
        System.out.println("PUSHED BUTTON");
        window.setLogInPanel();
    }

    public void onBackButtonPressed(String currentPanel){
        window.onBackButtonPressed(currentPanel);
    }
}
