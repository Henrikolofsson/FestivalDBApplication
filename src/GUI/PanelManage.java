package GUI;

import Controllers.AdminController;

import javax.swing.*;
import java.awt.*;

public class PanelManage extends JPanel {
    private AdminController adminController;

    private JPanel pnlNorthWorker;
    private JPanel pnlCenterBand;

    private JLabel lblWorker;
    private JLabel lblBand;

    private JTextField txtWorkerName;
    private JTextField txtWorkerPersonNbr;
    private JTextField txtWorkerAddress;

    private JTextField txtBandName;
    private JTextField txtBandCountryOfOrigin;
    private JTextField txtBandInfo;
    private JTextField txtBandContactPersonId;

    private JButton btnAddBand;
    private JButton btnAddWorker;
    private JButton btnShowWorkers;
    private JButton btnShowBands;



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
        pnlCenterBand = new JPanel(new GridBagLayout());
        lblWorker = new JLabel("Manage Worker: ");
        lblBand = new JLabel("Manage Band: ");

        txtWorkerName = new JTextField("Workers name");
        txtWorkerPersonNbr = new JTextField("Workers person number");
        txtWorkerAddress = new JTextField("Workers address");

        txtBandName = new JTextField("Bands name");
        txtBandCountryOfOrigin = new JTextField("Bands country of origin");
        txtBandInfo = new JTextField("Bands info");
        txtBandContactPersonId = new JTextField("Workers person number");

        btnAddWorker = new JButton("Add Worker");
        btnAddBand = new JButton("Add Band");
        btnShowWorkers = new JButton("SHOW WORKERS");
        btnShowBands = new JButton("SHOW BANDS");

        //GRIDBAGCONSTRAINTS FOR WORKERS

        GridBagConstraints gcLblWorker = new GridBagConstraints();
        gcLblWorker.fill = GridBagConstraints.VERTICAL;
        gcLblWorker.gridx = 0;
        gcLblWorker.gridy = 0;
        gcLblWorker.insets = new Insets(0,5,0,5);

        GridBagConstraints gcTxtWorkerName = new GridBagConstraints();
        gcTxtWorkerName.fill = GridBagConstraints.VERTICAL;
        gcTxtWorkerName.gridx = 1;
        gcTxtWorkerName.gridy = 0;
        gcTxtWorkerName.insets = new Insets(0,5,0,5);

        GridBagConstraints gcTxtWorkerPersonNbr = new GridBagConstraints();
        gcTxtWorkerPersonNbr.fill = GridBagConstraints.VERTICAL;
        gcTxtWorkerPersonNbr.gridx = 2;
        gcTxtWorkerPersonNbr.gridy = 0;
        gcTxtWorkerPersonNbr.insets = new Insets(0,5,0,5);

        GridBagConstraints gcTxtWorkerAddress = new GridBagConstraints();
        gcTxtWorkerAddress.fill = GridBagConstraints.VERTICAL;
        gcTxtWorkerAddress.gridx = 3;
        gcTxtWorkerAddress.gridy = 0;
        gcTxtWorkerAddress.insets = new Insets(0,5,0,5);

        GridBagConstraints gcBtnWorker = new GridBagConstraints();
        gcBtnWorker.fill = GridBagConstraints.VERTICAL;
        gcBtnWorker.gridx = 4;
        gcBtnWorker.gridy = 0;
        gcBtnWorker.insets = new Insets(0,5,0,5);

        GridBagConstraints gcBtnShowWorkers = new GridBagConstraints();
        gcBtnShowWorkers.fill = GridBagConstraints.VERTICAL;
        gcBtnShowWorkers.gridx = 2;
        gcBtnShowWorkers.gridy = 3;
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

        GridBagConstraints gcTxtBandContactPersonId = new GridBagConstraints();
        gcTxtBandContactPersonId.fill = GridBagConstraints.VERTICAL;
        gcTxtBandContactPersonId.gridx = 4;
        gcTxtBandContactPersonId.gridy = 0;
        gcTxtBandContactPersonId.insets = new Insets(0,5,0,5);

        GridBagConstraints gcBtnBand = new GridBagConstraints();
        gcBtnBand.fill = GridBagConstraints.VERTICAL;
        gcBtnBand.gridx = 5;
        gcBtnBand.gridy = 0;
        gcBtnBand.insets = new Insets(0,5,0,5);

        GridBagConstraints gcBtnShowBands = new GridBagConstraints();
        gcBtnShowBands.fill = GridBagConstraints.VERTICAL;
        gcBtnShowBands.gridx = 3;
        gcBtnShowBands.gridy = 3;
        gcBtnShowBands.insets = new Insets(20,5,20,5);

        pnlNorthWorker.add(lblWorker, gcLblWorker);
        pnlNorthWorker.add(txtWorkerName, gcTxtWorkerName);
        pnlNorthWorker.add(txtWorkerPersonNbr, gcTxtWorkerPersonNbr);
        pnlNorthWorker.add(txtWorkerAddress, gcTxtWorkerAddress);
        pnlNorthWorker.add(btnAddWorker, gcBtnWorker);

        pnlNorthWorker.add(btnShowWorkers, gcBtnShowWorkers);

        pnlCenterBand.add(lblBand, gcLblBand);
        pnlCenterBand.add(txtBandName, gcTxtBandName);
        pnlCenterBand.add(txtBandCountryOfOrigin, gcTxtBandCountryOfOrigin);
        pnlCenterBand.add(txtBandInfo, gcTxtBandInfo);
        pnlCenterBand.add(txtBandContactPersonId, gcTxtBandContactPersonId);
        pnlCenterBand.add(btnAddBand, gcBtnBand);

        pnlCenterBand.add(btnShowBands, gcBtnShowBands);

        add(pnlNorthWorker, BorderLayout.NORTH);
        add(pnlCenterBand, BorderLayout.CENTER);
    }

    private void registerListeners(){

    }
}
