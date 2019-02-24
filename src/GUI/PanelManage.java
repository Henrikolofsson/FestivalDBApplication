package GUI;

import Controllers.AdminController;
import Controllers.SQLController;
import Entities.Band;
import Entities.BandMember;
import Entities.Worker;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelManage extends JPanel {
    private AdminController adminController;

    private JPanel pnlNorthWorker;
    private JPanel pnlCenterBand;
    private JPanel pnlManageBands;
    private JPanel pnlAssignBandMembers;
    private JPanel pnlSouthConcert;

    private JLabel lblWorker;
    private JLabel lblBand;
    private JLabel lblConcert;
    private JLabel lblAssignBandMember;

    private JTextField txtWorkerName;
    private JTextField txtWorkerPersonNbr;
    private JTextField txtWorkerAddress;

    private JTextField txtBandName;
    private JTextField txtBandCountryOfOrigin;
    private JTextField txtBandInfo;
    private JTextField txtBandId;
    private JTextField txtBandContactPersonId;

    private JTextField txtBandMemberId;
    private JTextField txtBandMemberBandId;
    private JTextField txtBandMemberInfo;

    private JTextField txtConcertBandID;

    private JButton btnAddBand;
    private JButton btnAddWorker;
    private JButton btnAddBandMember;
    private JButton btnAddConcert;
    private JButton btnShowWorkers;
    private JButton btnShowBands;
    private JButton btnShowBandMembers;
    private JButton btnShowConcerts;

    private JComboBox cbConcertStage;
    private JComboBox cbConcertDay;
    private JComboBox cbConcertTime;

    private String[] stages = new String[]{"Green", "Red", "Blue"};
    private String[] days = new String[]{"Thursday", "Friday", "Saturday"};
    private String[] time = new String[]{"19:00", "20:00", "21:00", "22:00", "23:00"};

    private Border titledWorkerBorder = BorderFactory.createTitledBorder("Manage Workers");
    private Border titledBandBorder = BorderFactory.createTitledBorder("Manage Bands");
    private Border titledBandMemberBorder = BorderFactory.createTitledBorder("Assign Bandmembers");
    private Border titledConcertBorder = BorderFactory.createTitledBorder("Manage Concerts");

    private WorkerButtonsListener workerListener = new WorkerButtonsListener();
    private BandButtonListener bandListener = new BandButtonListener();
    private BandMemberButtonListener bandMemberListener = new BandMemberButtonListener();
    private ConcertButtonListener concertListener = new ConcertButtonListener();


    public PanelManage(){
        setLayout(new BorderLayout());
        initializeComponents();
        registerListeners();
    }

    public void setAdminController(AdminController adminController){
        this.adminController = adminController;
    }

    private void initializeComponents(){
        pnlNorthWorker = new JPanel(new GridBagLayout());
        pnlCenterBand = new JPanel(new BorderLayout());
        pnlSouthConcert = new JPanel(new GridBagLayout());
        pnlManageBands = new JPanel(new GridBagLayout());
        pnlAssignBandMembers = new JPanel(new GridBagLayout());

        lblWorker = new JLabel("Manage Worker: ");
        lblBand = new JLabel("Manage Band: ");
        lblConcert = new JLabel("Manage Concerts: ");
        lblAssignBandMember = new JLabel("Assign Bandmembers: ");


        txtWorkerName = new JTextField("Workers name");
        txtWorkerPersonNbr = new JTextField("Workers person number");
        txtWorkerAddress = new JTextField("Workers address");

        txtBandName = new JTextField("Bands name");
        txtBandCountryOfOrigin = new JTextField("Bands country of origin");
        txtBandInfo = new JTextField("Bands info");
        txtBandId = new JTextField("Band ID");
        txtBandContactPersonId = new JTextField("Workers person number");

        txtBandMemberId = new JTextField("Bandmembers ID");
        txtBandMemberBandId = new JTextField("The band's ID");
        txtBandMemberInfo = new JTextField("Information about the bandmember");

        txtConcertBandID = new JTextField("Band id");
        cbConcertStage = new JComboBox(stages);
        cbConcertDay = new JComboBox(days);
        cbConcertTime = new JComboBox(time);

        btnAddWorker = new JButton("Add Worker");
        btnAddBand = new JButton("Add Band");
        btnAddBandMember = new JButton("Add Bandmember");
        btnAddConcert = new JButton("Add Concert");
        btnShowWorkers = new JButton("SHOW WORKERS");
        btnShowBands = new JButton("SHOW BANDS");
        btnShowBandMembers = new JButton("SHOW BAND MEMBERS");
        btnShowConcerts = new JButton("SHOW CONCERTS");

        //GRIDBAGCONSTRAINTS FOR WORKERS

        GridBagConstraints gcLblWorker = new GridBagConstraints();
        gcLblWorker.fill = GridBagConstraints.VERTICAL;
        gcLblWorker.gridx = 0;
        gcLblWorker.gridy = 3;
        gcLblWorker.insets = new Insets(0,5,0,5);

        GridBagConstraints gcTxtWorkerName = new GridBagConstraints();
        gcTxtWorkerName.fill = GridBagConstraints.VERTICAL;
        gcTxtWorkerName.gridx = 1;
        gcTxtWorkerName.gridy = 3;
        gcTxtWorkerName.insets = new Insets(0,5,0,5);

        GridBagConstraints gcTxtWorkerPersonNbr = new GridBagConstraints();
        gcTxtWorkerPersonNbr.fill = GridBagConstraints.VERTICAL;
        gcTxtWorkerPersonNbr.gridx = 2;
        gcTxtWorkerPersonNbr.gridy = 3;
        gcTxtWorkerPersonNbr.insets = new Insets(0,5,0,5);

        GridBagConstraints gcTxtWorkerAddress = new GridBagConstraints();
        gcTxtWorkerAddress.fill = GridBagConstraints.VERTICAL;
        gcTxtWorkerAddress.gridx = 3;
        gcTxtWorkerAddress.gridy = 3;
        gcTxtWorkerAddress.insets = new Insets(0,5,0,5);

        GridBagConstraints gcBtnWorker = new GridBagConstraints();
        gcBtnWorker.fill = GridBagConstraints.VERTICAL;
        gcBtnWorker.gridx = 4;
        gcBtnWorker.gridy = 3;
        gcBtnWorker.insets = new Insets(0,5,0,5);

        GridBagConstraints gcBtnShowWorkers = new GridBagConstraints();
        gcBtnShowWorkers.fill = GridBagConstraints.VERTICAL;
        gcBtnShowWorkers.gridx = 2;
        gcBtnShowWorkers.gridy = 5;
        gcBtnShowWorkers.insets = new Insets(20,5,20,5);

        //GRIDBAGCONSTRAINTS FOR BANDS

        GridBagConstraints gcLblBand = new GridBagConstraints();
        gcLblBand.fill = GridBagConstraints.VERTICAL;
        gcLblBand.gridx = 0;
        gcLblBand.gridy = 0;
        gcLblBand.insets = new Insets(0,5,0,5);

        GridBagConstraints gcTxtBandName = new GridBagConstraints();
        gcTxtBandName.fill = GridBagConstraints.VERTICAL;
        gcTxtBandName.gridx = 1;
        gcTxtBandName.gridy = 0;
        gcTxtBandName.insets = new Insets(0,5,0,5);

        GridBagConstraints gcTxtBandCountryOfOrigin = new GridBagConstraints();
        gcTxtBandCountryOfOrigin.fill = GridBagConstraints.VERTICAL;
        gcTxtBandCountryOfOrigin.gridx = 2;
        gcTxtBandCountryOfOrigin.gridy = 0;
        gcTxtBandCountryOfOrigin.insets = new Insets(0,5,0,5);

        GridBagConstraints gcTxtBandInfo = new GridBagConstraints();
        gcTxtBandInfo.fill = GridBagConstraints.VERTICAL;
        gcTxtBandInfo.gridx = 3;
        gcTxtBandInfo.gridy = 0;
        gcTxtBandInfo.insets = new Insets(0,5,0,5);

        GridBagConstraints gcTxtBandID = new GridBagConstraints();
        gcTxtBandID.fill = GridBagConstraints.VERTICAL;
        gcTxtBandID.gridx = 4;
        gcTxtBandID.gridy = 0;
        gcTxtBandID.insets = new Insets(0,5,0,5);

        GridBagConstraints gcTxtBandContactPersonId = new GridBagConstraints();
        gcTxtBandContactPersonId.fill = GridBagConstraints.VERTICAL;
        gcTxtBandContactPersonId.gridx = 5;
        gcTxtBandContactPersonId.gridy = 0;
        gcTxtBandContactPersonId.insets = new Insets(0,5,0,5);

        GridBagConstraints gcBtnBand = new GridBagConstraints();
        gcBtnBand.fill = GridBagConstraints.VERTICAL;
        gcBtnBand.gridx = 6;
        gcBtnBand.gridy = 0;
        gcBtnBand.insets = new Insets(0,5,0,5);

        GridBagConstraints gcBtnShowBands = new GridBagConstraints();
        gcBtnShowBands.fill = GridBagConstraints.VERTICAL;
        gcBtnShowBands.gridx = 3;
        gcBtnShowBands.gridy = 3;
        gcBtnShowBands.insets = new Insets(20,5,20,5);

        //GRIDBAGCONSTRAINTS FOR BANDMEMBER ASSIGNMENT lblAssignBandMember,
        // txtBandMemberId, txtBandMemberBandId, txtBandMemberInfo, btnAddBandMember; btnShowBandMembers;
        GridBagConstraints gcLblBandMember = new GridBagConstraints();
        gcLblBandMember.fill = GridBagConstraints.VERTICAL;
        gcLblBandMember.gridx = 0;
        gcLblBandMember.gridy = 0;
        gcLblBandMember.insets = new Insets(0,5,0,5);

        GridBagConstraints gcTxtBandMemberId = new GridBagConstraints();
        gcTxtBandMemberId.fill = GridBagConstraints.VERTICAL;
        gcTxtBandMemberId.gridx = 1;
        gcTxtBandMemberId.gridy = 0;
        gcTxtBandMemberId.insets = new Insets(0,5,0,5);

        GridBagConstraints gcTxtBandMemberBandId = new GridBagConstraints();
        gcTxtBandMemberBandId.fill = GridBagConstraints.VERTICAL;
        gcTxtBandMemberBandId.gridx = 2;
        gcTxtBandMemberBandId.gridy = 0;
        gcTxtBandMemberBandId.insets = new Insets(0,5,0,5);

        GridBagConstraints gcTxtBandMemberInfo = new GridBagConstraints();
        gcTxtBandMemberInfo.fill = GridBagConstraints.VERTICAL;
        gcTxtBandMemberInfo.gridx = 3;
        gcTxtBandMemberInfo.gridy = 0;
        gcTxtBandMemberInfo.insets = new Insets(0,5,0,5);

        GridBagConstraints gcBtnAddBandMember = new GridBagConstraints();
        gcBtnAddBandMember.fill = GridBagConstraints.VERTICAL;
        gcBtnAddBandMember.gridx = 4;
        gcBtnAddBandMember.gridy = 0;
        gcBtnAddBandMember.insets = new Insets(0,5,0,5);

        GridBagConstraints gcBtnShowBandMembers = new GridBagConstraints();
        gcBtnShowBandMembers.fill = GridBagConstraints.VERTICAL;
        gcBtnShowBandMembers.gridx = 3;
        gcBtnShowBandMembers.gridy = 3;
        gcBtnShowBandMembers.insets = new Insets(20,5,20,5);

        //GRIDBAGCONSTRAINTS FOR CONCERTS

        GridBagConstraints gcLblConcert = new GridBagConstraints();
        gcLblConcert.fill = GridBagConstraints.VERTICAL;
        gcLblConcert.gridx = 0;
        gcLblConcert.gridy = 0;
        gcLblConcert.insets = new Insets(0,5,0,5);

        GridBagConstraints gcTxtConcertBandId = new GridBagConstraints();
        gcTxtConcertBandId.fill = GridBagConstraints.VERTICAL;
        gcTxtConcertBandId.gridx = 1;
        gcTxtConcertBandId.gridy = 0;
        gcTxtConcertBandId.insets = new Insets(0,5,0,5);

        GridBagConstraints gcCbConcertStage = new GridBagConstraints();
        gcCbConcertStage.fill = GridBagConstraints.VERTICAL;
        gcCbConcertStage.gridx = 2;
        gcCbConcertStage.gridy = 0;
        gcCbConcertStage.insets = new Insets(0,5,0,5);

        GridBagConstraints gcCbConcertDay = new GridBagConstraints();
        gcCbConcertDay.fill = GridBagConstraints.VERTICAL;
        gcCbConcertDay.gridx = 3;
        gcCbConcertDay.gridy = 0;
        gcCbConcertDay.insets = new Insets(0,5,0,5);

        GridBagConstraints gcCbConcertTime = new GridBagConstraints();
        gcCbConcertTime.fill = GridBagConstraints.VERTICAL;
        gcCbConcertTime.gridx = 4;
        gcCbConcertTime.gridy = 0;
        gcCbConcertTime.insets = new Insets(0,5,0,5);

        GridBagConstraints gcBtnAddConcert = new GridBagConstraints();
        gcBtnAddConcert.fill = GridBagConstraints.VERTICAL;
        gcBtnAddConcert.gridx = 5;
        gcBtnAddConcert.gridy = 0;
        gcBtnAddConcert.insets = new Insets(0,5,0,5);

        GridBagConstraints gcBtnShowConcerts = new GridBagConstraints();
        gcBtnShowConcerts.fill = GridBagConstraints.VERTICAL;
        gcBtnShowConcerts.gridx = 3;
        gcBtnShowConcerts.gridy = 3;
        gcBtnShowConcerts.insets = new Insets(20,5,20,5);

        //-------------------------------------------------------------------------------------------------------------------------------

        pnlNorthWorker.add(lblWorker, gcLblWorker);
        pnlNorthWorker.add(txtWorkerName, gcTxtWorkerName);
        pnlNorthWorker.add(txtWorkerPersonNbr, gcTxtWorkerPersonNbr);
        pnlNorthWorker.add(txtWorkerAddress, gcTxtWorkerAddress);
        pnlNorthWorker.add(btnAddWorker, gcBtnWorker);

        pnlNorthWorker.add(btnShowWorkers, gcBtnShowWorkers);

        pnlManageBands.add(lblBand, gcLblBand);
        pnlManageBands.add(txtBandName, gcTxtBandName);
        pnlManageBands.add(txtBandCountryOfOrigin, gcTxtBandCountryOfOrigin);
        pnlManageBands.add(txtBandInfo, gcTxtBandInfo);
        pnlManageBands.add(txtBandId, gcTxtBandID);
        pnlManageBands.add(txtBandContactPersonId, gcTxtBandContactPersonId);
        pnlManageBands.add(btnAddBand, gcBtnBand);

        pnlManageBands.add(btnShowBands, gcBtnShowBands);

        pnlAssignBandMembers.add(lblAssignBandMember, gcLblBandMember);
        pnlAssignBandMembers.add(txtBandMemberId, gcTxtBandMemberId);
        pnlAssignBandMembers.add(txtBandMemberBandId, gcTxtBandMemberBandId);
        pnlAssignBandMembers.add(txtBandMemberInfo, gcTxtBandMemberInfo);
        pnlAssignBandMembers.add(btnAddBandMember, gcBtnAddBandMember);

        pnlAssignBandMembers.add(btnShowBandMembers, gcBtnShowBandMembers);

        pnlSouthConcert.add(lblConcert, gcLblConcert);
        pnlSouthConcert.add(txtConcertBandID, gcTxtConcertBandId);
        pnlSouthConcert.add(cbConcertStage, gcCbConcertStage);
        pnlSouthConcert.add(cbConcertDay, gcCbConcertDay);
        pnlSouthConcert.add(cbConcertTime, gcCbConcertTime);
        pnlSouthConcert.add(btnAddConcert, gcBtnAddConcert);

        pnlSouthConcert.add(btnShowConcerts, gcBtnShowConcerts);


        pnlCenterBand.add(pnlManageBands, BorderLayout.CENTER);
        pnlCenterBand.add(pnlAssignBandMembers, BorderLayout.SOUTH);

        pnlNorthWorker.setBorder(titledWorkerBorder);
        pnlManageBands.setBorder(titledBandBorder);
        pnlAssignBandMembers.setBorder(titledBandMemberBorder);
        pnlSouthConcert.setBorder(titledConcertBorder);

        add(pnlNorthWorker, BorderLayout.NORTH);
        add(pnlCenterBand, BorderLayout.CENTER);
        add(pnlSouthConcert, BorderLayout.SOUTH);

    }

    private void registerListeners(){
        btnAddWorker.addActionListener(workerListener);
        btnShowWorkers.addActionListener(workerListener);

        btnAddBand.addActionListener(bandListener);
        btnShowBands.addActionListener(bandListener);

        btnAddBandMember.addActionListener(bandMemberListener);
        btnShowBandMembers.addActionListener(bandMemberListener);

        btnAddConcert.addActionListener(concertListener);
        btnShowConcerts.addActionListener(concertListener);
    }

    private class WorkerButtonsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnAddWorker){
                Worker worker = new Worker(txtWorkerPersonNbr.getText(), txtWorkerName.getText(), txtWorkerAddress.getText());
                adminController.addWorker(worker);
            }
            else if(e.getSource() == btnShowWorkers){
                System.out.println("BTN-SHOW-WORKERS");
                ArrayList<Worker> workers = adminController.getWorkers();
                for(Worker w : workers){
                    System.out.println(w);
                }
            }
        }
    }

    private class BandButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnAddBand) {
                Band band = new Band(txtBandId.getText(), txtBandName.getText(), txtBandCountryOfOrigin.getText(),
                                        txtBandInfo.getText(), txtBandContactPersonId.getText());
                adminController.addBand(band);
            }
            else if(e.getSource() == btnShowBands){
                System.out.println("BTNSHOWBANDS");
                ArrayList<Band> bands = adminController.getBands();
                for(Band b : bands){
                    System.out.println(b);
                }
            }
        }
    }

    private class BandMemberButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnAddBandMember){
              BandMember bandMember = new BandMember(txtBandMemberId.getText(), txtBandMemberBandId.getText(), txtBandMemberInfo.getText());
                adminController.addBandMember(bandMember);
                adminController.addBandMemberAssociation(txtBandMemberId.getText(), txtBandMemberBandId.getText());
            }
            else if(e.getSource() == btnShowBandMembers){

            }
        }
    }

    private class ConcertButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnAddConcert){

            }
            else if(e.getSource() == btnShowConcerts){
                System.out.println("BTNSHOWCONCERTS");
            }
        }
    }
}
