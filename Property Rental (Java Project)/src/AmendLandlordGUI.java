import javax.swing.*;
import java.awt.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author David O' Connor
 */
public class AmendLandlordGUI extends JFrame {
    
    private static final int WIDTH = 800;
    private static final int HEIGHT = 500;
    
    private JLabel nameL, addressLine1L, addressLine2L, addressLine3L, countyL, landLPhoneL, landlordPPSL, landlordStatusL,  landlordIDL, blankL, blank2L;
    private JTextField nameTF, addressLine1TF, addressLine2TF, addressLine3TF, countyTF, landLPhoneTF, landlordPPSTF, landlordStatusTF, landlordIDTF;
    private String[] counties = {"Antrim","Armagh","Carlow","Cavan","Clare","Cork","Derry","Donegal","Down",
            "Dublin","Fermanagh","Galway","Kerry","Kildare","Kilkenny","Laois","Leitrim","Limerick","Longford","Louth","Mayo",
            "Meath","Monaghan","Offaly","Roscommon","Sligo","Tipperary","Tyrone","Waterford","Westmeath","Wexford","Wicklow"};
    private JComboBox countyComboBox;
    private JButton addB, cancelB;
    
    public static Landlord landlord_;
    
    public AmendLandlordGUI(Landlord landlord){
        
        landlord_ = landlord;
        
        //Setting Font Properties
        Font myFont = new Font("Times New Roman", Font.BOLD, 20);
        
        //Instantiate the labels
        nameL = new JLabel("Landlord Name: " , SwingConstants.RIGHT);
        addressLine1L = new JLabel("Address Line 1: ", SwingConstants.RIGHT);
        addressLine2L = new JLabel("Address Line 2: ", SwingConstants.RIGHT);
        addressLine3L = new JLabel("Address Line 3: ", SwingConstants.RIGHT);
        countyL = new JLabel("County: ", SwingConstants.RIGHT);
        landLPhoneL = new JLabel("Contact No: ", SwingConstants.RIGHT);
        landlordPPSL = new JLabel("Landlord's PPS Number: ", SwingConstants.RIGHT);
        landlordStatusL = new JLabel("Landlord Status: ", SwingConstants.RIGHT);
        landlordIDL = new JLabel("Landlord's ID Number: ", SwingConstants.RIGHT);
        blankL = new JLabel("", SwingConstants.RIGHT);
        blank2L = new JLabel("", SwingConstants.RIGHT);
        
        //Text Fields:
        nameTF = new JTextField(10);
        addressLine1TF = new JTextField(10);
        landLPhoneTF = new JTextField(10);
        landlordPPSTF = new JTextField(10);
        landlordStatusTF = new JTextField(10);
        landlordIDTF = new JTextField(10);
        landlordIDTF.setEditable(false);

        //County Drop Down Menu
        countyComboBox = new JComboBox(counties);
        
        nameTF.setText(landlord_.getName());
        addressLine1TF.setText(landlord_.getAddress());
        landLPhoneTF.setText(landlord_.getPhoneNumber());
        landlordPPSTF.setText(landlord_.getPpsNumber());
        landlordStatusTF.setText(landlord_.getLandlordStatus());
        landlordIDTF.setText(""+landlord_.getLandlordID());
        
         //Buttons:
        //Add Button
        addB = new JButton("Commit Changes");
        addB.setFont(myFont);
        addB.setToolTipText("Click to submit details to the system.\n NOTE: Make sure details are correct with landlord");
        addB.addActionListener(ae->{
            landlord_.setName(nameTF.getText());
            landlord_.setAddress(addressLine1TF.getText());
            landlord_.setPhoneNumber(landLPhoneTF.getText());
            landlord_.setPpsNumber(landlordPPSTF.getText());
            landlord_.setLandlordStatus(landlordStatusTF.getText()); 
            
            landlordIDTF.setText(""+landlord_.getLandlordID());
            JOptionPane.showMessageDialog(null, "Landlord info updated!");
        });
        
        //Cancel Button
        cancelB = new JButton("Cancel");
        cancelB.setFont(myFont);
        cancelB.addActionListener(ae->{
            setVisible(false);
            dispose();
        });
        
        setLayout(new GridLayout(11, 2));

        //Add components to the JFrame
        add(nameL);
        add(nameTF);
        add(addressLine1L);
        add(addressLine1TF);
        add(countyL);
        add(countyComboBox);
        add(landLPhoneL);
        add(landLPhoneTF);
        add(landlordPPSL);
        add(landlordPPSTF);
        add(landlordStatusL);
        add(landlordStatusTF);
        add(landlordIDL);
        add(landlordIDTF);
        add(blankL);
        add(blank2L);
        add(addB);
        add(cancelB);
        
        setTitle("Amend Landlord Details");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); //Position application in center of screen
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        toFront();
    }
}
