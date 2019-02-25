package GUI;

import Controllers.SQLController;
import Controllers.UserController;
import Entities.Band;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelBandInformation extends JPanel {
    private JPanel pnlBandInformation;
    private JPanel pnlChooseBand;

    private JLabel lblBandName;
    private JLabel lblBandMembers;
    private JLabel lblBandInformation;
    private JLabel lblBandMemberInformation;

    private JComboBox cbBandsToChoose;
    private JButton btnGetBandInformation;

    private Border titledBandInformationBorder = BorderFactory.createTitledBorder("BandInformation");
    private Border titledGetBandInformationBorder = BorderFactory.createTitledBorder("Get Information");

    private ArrayList<Band> bandList;
    private String[] bandNames;
    private UserController userController;

    public PanelBandInformation(){
        setLayout(new BorderLayout());
        initializeComponents();
    }

    private void initializeComponents(){
        pnlBandInformation = new JPanel(new GridBagLayout());
        pnlChooseBand = new JPanel(new GridBagLayout());

        lblBandName = new JLabel();
        lblBandMembers = new JLabel();
        lblBandInformation = new JLabel();
        lblBandMemberInformation = new JLabel();

        cbBandsToChoose = new JComboBox<>();
        btnGetBandInformation = new JButton("GET BAND INFORMATION");

        GridBagConstraints gcLblBandName = new GridBagConstraints();
        gcLblBandName.fill = GridBagConstraints.VERTICAL;
        gcLblBandName.gridx = 0;
        gcLblBandName.gridy = 3;
        gcLblBandName.insets = new Insets(0,5,0,5);

        GridBagConstraints gcLblBandMembers = new GridBagConstraints();
        gcLblBandMembers.fill = GridBagConstraints.VERTICAL;
        gcLblBandMembers.gridx = 0;
        gcLblBandMembers.gridy = 4;
        gcLblBandMembers.insets = new Insets(0,5,0,5);

        GridBagConstraints gcLblBandInformation = new GridBagConstraints();
        gcLblBandInformation.fill = GridBagConstraints.VERTICAL;
        gcLblBandInformation.gridx = 0;
        gcLblBandInformation.gridy = 5;
        gcLblBandInformation.insets = new Insets(0,5,0,5);

        GridBagConstraints gcLblBandMemberInformation = new GridBagConstraints();
        gcLblBandMemberInformation.fill = GridBagConstraints.VERTICAL;
        gcLblBandMemberInformation.gridx = 0;
        gcLblBandMemberInformation.gridy = 6;
        gcLblBandMemberInformation.insets = new Insets(0,5,0,5);

        pnlBandInformation.add(lblBandName, gcLblBandName);
        pnlBandInformation.add(lblBandMembers, gcLblBandMembers);
        pnlBandInformation.add(lblBandInformation, gcLblBandInformation);
        pnlBandInformation.add(lblBandMemberInformation, gcLblBandMemberInformation);


        GridBagConstraints gcCbBandList = new GridBagConstraints();
        gcCbBandList.fill = GridBagConstraints.VERTICAL;
        gcCbBandList.gridx = 0;
        gcCbBandList.gridy = 3;
        gcCbBandList.insets = new Insets(0,5,0,5);

        GridBagConstraints gcBtnGetBandList = new GridBagConstraints();
        gcBtnGetBandList.fill = GridBagConstraints.VERTICAL;
        gcBtnGetBandList.gridx = 0;
        gcBtnGetBandList.gridy = 4;
        gcBtnGetBandList.insets = new Insets(0,5,0,5);

        pnlChooseBand.add(cbBandsToChoose, gcCbBandList);
        pnlChooseBand.add(btnGetBandInformation, gcBtnGetBandList);

        pnlBandInformation.setBorder(titledBandInformationBorder);
        pnlChooseBand.setBorder(titledGetBandInformationBorder);

        add(pnlBandInformation, BorderLayout.CENTER);
        add(pnlChooseBand, BorderLayout.SOUTH);

    }

    public void setUserController(UserController userController){
        this.userController = userController;
    }

    public void setBandList(ArrayList<Band> bandList){
        this.bandList = bandList;
    }

    private class GetBandInfoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            cbBandsToChoose.

        }
    }
}
