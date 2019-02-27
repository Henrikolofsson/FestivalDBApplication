package Controllers;

import Entities.Band;
import Entities.BandMember;
import Entities.Concerts;
import Entities.Worker;

import java.sql.*;
import java.text.SimpleDateFormat;
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
                    "bands(band_id, band_name, band_country_of_origin, band_info, contact_person_id)" + "VALUES(?, ?, ?, ?, ?)");
            stmt.setString(1, band.getBand_id());
            stmt.setString(2, band.getBand_name());
            stmt.setString(3, band.getBand_country_of_origin());
            stmt.setString(4, band.getBand_info());
            stmt.setString(5, band.getContact_person_id());
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
            stmt.setString(2, bandMember.getBand_member_name());
            stmt.setString(3, bandMember.getBand_member_info());
            stmt.executeQuery();
            stmt.close();
            System.out.println(bandMember);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void addBandMemberAssociation(String bandmemberID, String bandID){
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO bandmember VALUES(?, ?)");
            stmt.setString(1, bandmemberID);
            stmt.setString(2, bandID);
            stmt.executeQuery();
            stmt.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void addConcertToSchedule(Concerts concert){
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO schedule VALUES(?, ?, ?, ?)");
            stmt.setString(1, concert.getDay());
            Time time = Time.valueOf(concert.getTime());
            stmt.setTime(2, time);
            stmt.setString(3, concert.getBand_id());
            stmt.setString(4, concert.getScene());

            stmt.executeUpdate();
            stmt.close();
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

    public static ArrayList<BandMember> getBandMembers(String band_id){
        ArrayList<BandMember> bandMembers = new ArrayList<>();
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM bandmember");
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String band_member_id = rs.getString("band_member_id");
                //String band_id = rs.getString("band_id");
                String band_info = rs.getString("band_member_info");
                System.out.println(bandMembers);
                bandMembers.add(new BandMember(band_member_id, band_id, band_info));
            }
            stmt.close();
            rs.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return bandMembers;
    }


    public static ArrayList<Concerts> getConcertsFromStage(String stage){
        ArrayList<Concerts> concertsOnStage = new ArrayList<>();
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT day, schedule_time, band_id_playing " +
                    "FROM schedule WHERE scene = ? ORDER BY schedule_time ASC");
            stmt.setString(1, stage);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                String day = rs.getString("day");
                Time time = rs.getTime("schedule_time");
                String band_id_playing = rs.getString("band_id_playing");
                Concerts concert = new Concerts(band_id_playing, day, time.toString(), stage);
                concertsOnStage.add(concert);
                System.out.println(time.toString());
                System.out.println(day + " " + time.toString() + " " + band_id_playing);
            }
            stmt.close();
            rs.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return concertsOnStage;
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


    public static int getBandId(String bandName){
        int bandId = -1;
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT band_id FROM bands WHERE band_name = ?");
            stmt.setString(1, bandName);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
               bandId = rs.getInt("band_id");
            }
            stmt.close();
            rs.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return bandId;
    }

    public static ArrayList<Integer> getBandMemberAssociations(String bandId){
        ArrayList<Integer> bandMemberAssociations = new ArrayList<>();
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM bandmember_association WHERE band_id = ?");
            stmt.setString(1, bandId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                bandMemberAssociations.add(rs.getInt("bandmember_id"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return bandMemberAssociations;
    }

    public static BandMember getBandMember(int bandmember_id){
        BandMember bandMember = null;
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM bandmember WHERE bandmember_id = ?");
            stmt.setInt(1, bandmember_id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                bandMember = new BandMember(rs.getString("bandmember_id"), rs.getString("bandmember_name"), rs.getString("bandmember_info"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return bandMember;
    }

    public static String getBandInformation(String band_id){
        String bandinfo = "";
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT band_info FROM bands WHERE band_id = ?");
            stmt.setString(1, band_id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                bandinfo = rs.getString("band_info");
            }
            stmt.close();
            rs.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return bandinfo;
    }

    public static String getBandName(String band_id){
        String bandname = "";
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT band_name FROM bands WHERE band_id = ?");
            stmt.setString(1, band_id);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                bandname = rs.getString("band_name");
            }
            stmt.close();
            rs.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return bandname;
    }

}
