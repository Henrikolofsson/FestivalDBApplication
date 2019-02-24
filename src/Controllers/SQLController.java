package Controllers;

import Entities.Band;
import Entities.BandMember;
import Entities.Worker;

import java.sql.*;
import java.util.ArrayList;

public class SQLController {
    private static String dbURL = "jdbc:postgresql://pgserver.mah.se/ai0026project";
    private static String dbUser = "ai0026";
    private static String dbPassword = "mycyty58";
    private static Connection dbConnection;


    public static void connect(){
        try {
            dbConnection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            System.out.println("Connected to postgreSQL server sucessfully");
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void disconnect(){
        try {
            if(dbConnection != null && !dbConnection.isClosed()){
                dbConnection.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void addBand(Band band){
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO " +
                    "bands(band_name, band_country_of_origin, band_info, contact_person_id)" + "VALUES(?, ?, ?, ?)");
            stmt.setString(1, band.getBand_name());
            stmt.setString(2, band.getBand_country_of_origin());
            stmt.setString(3, band.getBand_info());
            stmt.setString(4, band.getContact_person_id());
            stmt.executeUpdate();
            stmt.close();
        } catch(SQLException e){
            System.out.println("Could not add band");
            e.printStackTrace();
        }
    }

    public static void addWorker(Worker workerToHire){
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO workers VALUES(?, ?, ?)");
            stmt.setString(1, workerToHire.getPerson_number());
            stmt.setString(2, workerToHire.getName());
            stmt.setString(3, workerToHire.getAddress());
            stmt.executeUpdate();
            stmt.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void addBandMember(BandMember bandMember){
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO bandmember VALUES(?, ?, ?)");
            stmt.setString(1, bandMember.getBand_member_id());
            stmt.setString(2, bandMember.getBand_id());
            stmt.setString(3, bandMember.getBand_member_info());
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void addBandMemberAssociation(String bandmemberID, String bandID){
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO bandmember VALUES(?, ?)");
            stmt.setString(1, bandmemberID);
            stmt.setString(2, bandID);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Worker> getWorkers(){
        ArrayList<Worker> workers = new ArrayList<>();
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM workers");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String person_nbr = rs.getString("person_number");
                String name = rs.getString("worker_name");
                String address = rs.getString("address");
                Worker worker = new Worker(person_nbr, name, address);
                workers.add(worker);
            }
            stmt.close();
            rs.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return workers;
    }

    public static ArrayList<Band> getBands(){
        ArrayList<Band> bands = new ArrayList<>();
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM bands");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String band_id = rs.getString("band_id");
                String band_name = rs.getString("band_name");
                String band_country_of_origin = rs.getString("band_country_of_origin");
                String band_info = rs.getString("band_info");
                String contact_person_id = rs.getString("contact_person_id");
                bands.add(new Band(band_id, band_name, band_country_of_origin, band_info, contact_person_id));
            }
            stmt.close();
            rs.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return bands;
    }

    public boolean logIn(String userName, String password){
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT username, password FROM system_administrators WHERE username = ? AND password = ?");
            stmt.setString(1, userName);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return true;
            }
            stmt.close();
            rs.close();

        } catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}
