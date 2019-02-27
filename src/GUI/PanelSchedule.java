package GUI;

import Controllers.SQLController;
import Controllers.UserController;
import Entities.Concerts;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelSchedule extends JPanel {
    private UserController userController;
    private JPanel pnlNorthStageRed;
    private JPanel pnlCenterStageGreen;
    private JPanel pnlSouthStageBlue;

    private DefaultTableModel modelRed = new DefaultTableModel();
    private DefaultTableModel modelGreen = new DefaultTableModel();
    private DefaultTableModel modelBlue = new DefaultTableModel();
    private JTable tableRed;
    private JTable tableGreen;
    private JTable tableBlue;

    private String[] columns = new String[]{"Day", "Time", "Band"};
    private ArrayList<Concerts> concertsRed;
    private ArrayList<Concerts> concertsGreen;
    private ArrayList<Concerts> concertsBlue;

    private Border titledStageRedBorder = BorderFactory.createTitledBorder("Red Stage");
    private Border titledStageGreenBorder = BorderFactory.createTitledBorder("Green Stage");
    private Border titledStageBlueBorder = BorderFactory.createTitledBorder("Blue Stage");

    private JButton btnBack;

    public PanelSchedule(){
        setLayout(new BorderLayout());
        initializeComponents();
        registerListeners();
    }

    private void initializeComponents(){
        pnlNorthStageRed = new JPanel(new GridBagLayout());
        modelRed.addColumn("Day");
        modelRed.addColumn("Time");
        modelRed.addColumn("Band");
        tableRed = new JTable(modelRed);
        TableColumnModel columnModelRed = tableRed.getColumnModel();
        columnModelRed.getColumn(2).setPreferredWidth(200);


        GridBagConstraints gcTableStageRed = new GridBagConstraints();
        gcTableStageRed.fill = GridBagConstraints.VERTICAL;
        gcTableStageRed.gridx = 0;
        gcTableStageRed.gridy = 3;
        gcTableStageRed.insets = new Insets(0,5,0,5);
        pnlNorthStageRed.add(tableRed, gcTableStageRed);
        pnlNorthStageRed.setBorder(titledStageRedBorder);

        pnlCenterStageGreen = new JPanel(new GridBagLayout());
        modelGreen.addColumn("Day");
        modelGreen.addColumn("Time");
        modelGreen.addColumn("Band");
        tableGreen = new JTable(modelGreen);
        TableColumnModel columnModelGreen = tableGreen.getColumnModel();
        columnModelGreen.getColumn(2).setPreferredWidth(200);


        GridBagConstraints gcTableStageGreen = new GridBagConstraints();
        gcTableStageGreen.fill = GridBagConstraints.VERTICAL;
        gcTableStageGreen.gridx = 0;
        gcTableStageGreen.gridy = 3;
        gcTableStageGreen.insets = new Insets(0,5,0,5);
        pnlCenterStageGreen.add(tableGreen, gcTableStageGreen);
        pnlCenterStageGreen.setBorder(titledStageGreenBorder);

        pnlSouthStageBlue = new JPanel(new GridBagLayout());
        modelBlue.addColumn("Day");
        modelBlue.addColumn("Time");
        modelBlue.addColumn("Band");
        tableBlue = new JTable(modelBlue);
        TableColumnModel columnModelBlue = tableBlue.getColumnModel();
        columnModelBlue.getColumn(2).setPreferredWidth(200);


        GridBagConstraints gcTableStageBlue = new GridBagConstraints();
        gcTableStageBlue.fill = GridBagConstraints.VERTICAL;
        gcTableStageBlue.gridx = 0;
        gcTableStageBlue.gridy = 3;
        gcTableStageBlue.insets = new Insets(0,5,0,5);
        pnlSouthStageBlue.add(tableBlue, gcTableStageBlue);
        pnlSouthStageBlue.setBorder(titledStageBlueBorder);

        btnBack = new JButton("BACK");
        btnBack.setPreferredSize(new Dimension(100,30));

        GridBagConstraints gcBackButton = new GridBagConstraints();
        gcBackButton.fill = GridBagConstraints.VERTICAL;
        gcBackButton.gridx = 0;
        gcBackButton.gridy = 10;
        gcBackButton.insets = new Insets(100,5,0,5);
        pnlSouthStageBlue.add(btnBack, gcBackButton);



        add(pnlNorthStageRed, BorderLayout.NORTH);
        add(pnlCenterStageGreen, BorderLayout.CENTER);
        add(pnlSouthStageBlue, BorderLayout.SOUTH);

    }

    private void registerListeners(){
        btnBack.addActionListener(new BackButtonlistener());
    }

    public void setConcertLists(){
        concertsRed = SQLController.getConcertsFromStage("Red");
        concertsGreen = SQLController.getConcertsFromStage("Green");
        concertsBlue = SQLController.getConcertsFromStage("Blue");

        System.out.println("Show Red concerts");
        for(Concerts concert : concertsRed){
            System.out.println(concert);
        }

        System.out.println("Show Green concerts");
        for(Concerts concert : concertsGreen){
            System.out.println(concert);
        }

        System.out.println("Show Blue concerts");
        for(Concerts concert : concertsBlue){
            System.out.println(concert);
        }
        populateTables();
    }

    private void populateTables(){

        for(Concerts concert : concertsRed){
            concert.setBand_name(SQLController.getBandName(concert.getBand_id()));
        }
        for(Concerts concert : concertsRed){
            modelRed.addRow(new Object[]{concert.getDay(), concert.getTime(), concert.getBand_name()});
        }

        for(Concerts concert : concertsGreen){
            concert.setBand_name(SQLController.getBandName(concert.getBand_id()));
        }

        for(Concerts concert : concertsGreen){
            modelGreen.addRow(new Object[]{concert.getDay(), concert.getTime(), concert.getBand_name()});
        }

        for(Concerts concert : concertsBlue){
            concert.setBand_name(SQLController.getBandName(concert.getBand_id()));
        }

        for(Concerts concert : concertsBlue){
            modelBlue.addRow(new Object[]{concert.getDay(), concert.getTime(), concert.getBand_name()});
        }
    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }

    private class BackButtonlistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnBack){
                userController.onBackButtonPressed("PanelSchedule");
            }
        }
    }
}
