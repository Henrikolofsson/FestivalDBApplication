package GUI;

import Controllers.SQLController;
import Controllers.UserController;
import Entities.Band;
import Entities.BandMember;

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
    private ArrayList<BandMember> bandMembersList;
    private ArrayList<Integer> bandMemberIdList;

    private String bandName = "Band name: ";
    private StringBuilder bandMembers = new StringBuilder("Band members: ");
    private String bandInformation = "Band information: ";
    private String bandMemberInformation = "Band member information: ";

    private JButton btnBack;

    public PanelBandInformation(){
        setLayout(new BorderLayout());
        initializeComponents();
        registerListeners();
    }

    private void initializeComponents(){
        pnlBandInformation = new JPanel(new GridBagLayout());
        pnlChooseBand = new JPanel(new GridBagLayout());

        lblBandName = new JLabel(bandName);
        lblBandMembers = new JLabel(bandMembers.toString());
        lblBandInformation = new JLabel(bandInformation);
        lblBandMemberInformation = new JLabel(bandMemberInformation);

        cbBandsToChoose = new JComboBox<>();
        btnGetBandInformation = new JButton("GET BAND INFORMATION");

        btnBack = new JButton("BACK");

        GridBagConstraints gcLblBandName = new GridBagConstraints();
        gcLblBandName.fill = GridBagConstraints.VERTICAL;
        gcLblBandName.gridx = 0;
        gcLblBandName.gridy = 0;
        gcLblBandName.insets = new Insets(0,5,100,5);

        GridBagConstraints gcLblBandMembers = new GridBagConstraints();
        gcLblBandMembers.fill = GridBagConstraints.VERTICAL;
        gcLblBandMembers.gridx = 0;
        gcLblBandMembers.gridy = 4;
        gcLblBandMembers.insets = new Insets(0,5,100,5);

        GridBagConstraints gcLblBandInformation = new GridBagConstraints();
        gcLblBandInformation.fill = GridBagConstraints.VERTICAL;
        gcLblBandInformation.gridx = 0;
        gcLblBandInformation.gridy = 8;
        gcLblBandInformation.insets = new Insets(0,5,100,5);

        GridBagConstraints gcLblBandMemberInformation = new GridBagConstraints();
        gcLblBandMemberInformation.fill = GridBagConstraints.VERTICAL;
        gcLblBandMemberInformation.gridx = 0;
        gcLblBandMemberInformation.gridy = 12;
        gcLblBandMemberInformation.insets = new Insets(0,5,100,5);

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

        GridBagConstraints gcBtnBack = new GridBagConstraints();
        gcBtnBack.fill = GridBagConstraints.VERTICAL;
        gcBtnBack.gridx = 0;
        gcBtnBack.gridy = 5;
        gcBtnBack.insets = new Insets(100,5,0,5);

        pnlChooseBand.add(cbBandsToChoose, gcCbBandList);
        pnlChooseBand.add(btnGetBandInformation, gcBtnGetBandList);
        pnlChooseBand.add(btnBack, gcBtnBack);

        pnlBandInformation.setBorder(titledBandInformationBorder);
        pnlChooseBand.setBorder(titledGetBandInformationBorder);

        add(pnlBandInformation, BorderLayout.CENTER);
        add(pnlChooseBand, BorderLayout.SOUTH);

    }

    public void setStringBuilderZero(){
        bandMembers = new StringBuilder("Band members: ");
    }

    private void registerListeners(){
        btnGetBandInformation.addActionListener(new GetBandInfoListener());
        btnBack.addActionListener(new BackButtonlistener());
    }

    public void setUserController(UserController userController){
        this.userController = userController;
    }

    public void setBandList(ArrayList<Band> bandList){
        this.bandList = bandList;
        bandNames = new String[bandList.size()];
        for(int i = 0; i < bandList.size(); i++){
            bandNames[i] = bandList.get(i).getBand_name();

            cbBandsToChoose.addItem(bandList.get(i).getBand_name());
        }


    }

    private class GetBandInfoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Sets the stringbuilder for members to zero, so that it can be filled up with new members.
            setStringBuilderZero();
            //If the memberidlist is not null(first time it is null), it will clear it to be filled with new member ID's
            if(bandMemberIdList != null){
                bandMemberIdList.clear();
            }
            //To get band Id
            int bandId = SQLController.getBandId(cbBandsToChoose.getSelectedItem().toString());

            //For band name
            String bandNameChosen = cbBandsToChoose.getSelectedItem().toString();
            bandName = new String("Band name: " + bandNameChosen);
            lblBandName.setText(bandName);

            //To get bandMemberAssociations
            bandMemberIdList = SQLController.getBandMemberAssociations(Integer.toString(bandId));

            //To get the BandMember objects, initializes the array with bandmembers, and get each one from the database
            bandMembersList = new ArrayList<>();
            for(Integer i : bandMemberIdList){
                bandMembersList.add(SQLController.getBandMember(i));
            }

            //Writes out the band members
            for(BandMember bm : bandMembersList){
                System.out.println(bm);
            }

            //Setting the text of the members in the JLabel
            for(int i = 0; i < bandMembersList.size(); i++){
               if(i == bandMembersList.size() -1){
                   bandMembers.append(bandMembersList.get(i).getBand_member_name());
               } else {
                   bandMembers.append(bandMembersList.get(i).getBand_member_name() + ", ");
               }
            }
            lblBandMembers.setText(bandMembers.toString());

            //Setting the text for band info
            lblBandInformation.setText("Band info: " + SQLController.getBandInformation(Integer.toString(bandId)));

            //Setting text for band members
            StringBuilder bandMembersInformation = new StringBuilder("<html>Band member information: <br>");
            for(BandMember bm : bandMembersList){
                bandMembersInformation.append(bm.getBand_member_name() + ": " + bm.getBand_member_info() +"<br>");
            }
            bandMembersInformation.append("</html>");
            lblBandMemberInformation.setText(bandMembersInformation.toString());
        }
    }

    private class BackButtonlistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnBack){
                userController.onBackButtonPressed("PanelBandInformation");
            }
        }
    }
}
