package Controllers;

import Entities.Band;

import java.sql.*;

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
