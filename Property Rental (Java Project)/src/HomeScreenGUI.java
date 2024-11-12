
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author David O' Connor
 */
public class HomeScreenGUI extends JFrame {
    //JMenu fileMenu, landlordsMenu, propertiesMenu, tenantsMenu, rentalsMenu, helpMenu;

    ///Creating the ArrayLists
    public static ArrayList<Landlord> landlordList = new ArrayList<>();
    public static ArrayList<Property> propertyList = new ArrayList<>();
    public static ArrayList<Property> propertyLet = new ArrayList<>();
    public static ArrayList<Property> propertyAvailable = new ArrayList<>();
    public static ArrayList<Tenant> tenantsAvailable = new ArrayList<>();
    public static ArrayList<Tenant> tenantList = new ArrayList<>();
    public static ArrayList<Rental> rentalList = new ArrayList<>();
    JTextArea area;

    ///Creating the Combo Boxes 
    String[] delPropertyLists = {"Properties Available", "Properties Least Out"};
    JComboBox delPropertyLts = new JComboBox(delPropertyLists);
    JButton b = new JButton("Enter");
    JButton c = new JButton("Cancel");
    JLabel delProText = new JLabel("Select Property List to delete from");

    String[] tenantLists = {"Tenants Available", "Tenants Not Available"};
    JComboBox tenantLts = new JComboBox(tenantLists);
    JButton d = new JButton("Enter");
    JButton f = new JButton("Cancel");
    JLabel tenText = new JLabel("Select Tenant List");

    String[] delTenantLists = {"Tenants Available", "Tenants Not Available"};
    JComboBox delTenantLts = new JComboBox(delTenantLists);
    JButton g = new JButton("Enter");
    JButton h = new JButton("Cancel");
    JLabel delTenText = new JLabel("Select Tenant List to delete from");

    String[] amdTenantLists = {"Tenants Available", "Tenants Not Available"};
    JComboBox amdTenantLts = new JComboBox(amdTenantLists);
    JButton i = new JButton("Enter");
    JButton j = new JButton("Cancel");
    JLabel amdTenText = new JLabel("Select Tenant List to delete from");

    String searchName;
    boolean found = false;

    public static void main(String args[]) throws IOException {
        //Run the Main Application GUI
        HomeScreenGUI ex = new HomeScreenGUI();
        ex.setVisible(true);
    }

    public HomeScreenGUI() {

        //Returns an image which gets pixel data from the specified file.
        Image storeImage = Toolkit.getDefaultToolkit().createImage("images/PropertyManagement.jpg"); //http://www.apartmentleasingtraining.org/property-management-course

        createMenuBar();

        //Loads up all the databases
        loadDatabases();

        setLayout(new BorderLayout());
        setTitle("Property Management System");
        JLabel background = new JLabel(new ImageIcon(storeImage));
        add(background);
        background.setLayout(new FlowLayout());
        setSize(1218, 684);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        area = new JTextArea();
    }

    /**
     * Creates the menu bar on the main GUI
     */
    private void createMenuBar() {

        JMenuBar menuBar = new JMenuBar();
        JMenu listMenu;

        //Load in the Icons
        ImageIcon exitIcon = new ImageIcon("images/exit.png");
        ImageIcon addIcon = new ImageIcon("images/add.png");
        ImageIcon deleteIcon = new ImageIcon("images/delete.png");
        ImageIcon editIcon = new ImageIcon("images/edit.png");
        ImageIcon searchIcon = new ImageIcon("images/search.png");
        ImageIcon displayIcon = new ImageIcon("images/display.png");
        ImageIcon listIcon = new ImageIcon("images/list.png");
        ImageIcon saveIcon = new ImageIcon("images/save.png");
        ImageIcon openIcon = new ImageIcon("images/open.png");
        ImageIcon aboutIcon = new ImageIcon("images/about.png");
        ImageIcon helpIcon = new ImageIcon("images/help.png");

        //Set up all the JMenus and their Hot keys (Hold ALT + key)
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenu landlords = new JMenu("Landlords");
        landlords.setMnemonic(KeyEvent.VK_L);

        JMenu properties = new JMenu("Properties");
        properties.setMnemonic(KeyEvent.VK_P);

        JMenu tenants = new JMenu("Tenants");
        tenants.setMnemonic(KeyEvent.VK_T);

        JMenu rentals = new JMenu("Rentals");
        rentals.setMnemonic(KeyEvent.VK_R);

        JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);

        //////////File Menu Items/////////////////////////////////////////////////////////////////
        //Save data option
        JMenuItem save = new JMenuItem("  Save", saveIcon);
        save.setMnemonic(KeyEvent.VK_S);
        save.setToolTipText("Save any new changes made to the system");
        save.addActionListener(ae -> {
            saveDatabases();
            System.out.println("Save clicked");
        });

        //Load data option
        JMenuItem load = new JMenuItem("  Load", openIcon);
        load.setMnemonic(KeyEvent.VK_L);
        load.setToolTipText("Save any new changes made to the system");
        load.addActionListener(ae -> {
            loadDatabases();
            System.out.println("Load clicked");
        });

        //Exit Option
        JMenuItem exit = new JMenuItem("  Exit", exitIcon);
        exit.setMnemonic(KeyEvent.VK_E);
        exit.setToolTipText("Exit application");
        exit.addActionListener(ae -> {
            System.out.println("Exit option clicked");
            String message = " Would you like to save any changes made to the database? ";
            String title = "Quit";
            int confirm = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_CANCEL_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                saveDatabases();
                JOptionPane.showMessageDialog(null, "Database updated. Goodbye");
                System.exit(0);
            } else if (confirm == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(null, "Database not updated. Goodbye");
                System.exit(0);
            }
        }
        );
        file.add(save);
        file.add(load);
        file.addSeparator();
        file.add(exit);

//////////Landlord Menu Items/////////////////////////////////////////////////////////////////
        //Add a Landlord
        JMenuItem registerLandlord = new JMenuItem("  Register Landlord", addIcon);
        registerLandlord.setMnemonic(KeyEvent.VK_A);
        registerLandlord.setToolTipText("Open Register Landlord window");
        registerLandlord.addActionListener(ae -> {
            RegisterLandlordGUI addNewLandlordScreen = new RegisterLandlordGUI();
        });

        //De-Register Landlord 
        JMenuItem deRegisterLandlord = new JMenuItem("  De-Register Landlord", deleteIcon);
        deRegisterLandlord.setMnemonic(KeyEvent.VK_D);
        deRegisterLandlord.setToolTipText("De-Register Landlord from the system");
        deRegisterLandlord.addActionListener(ae -> {
            System.out.println("De-Register Landlord Clicked");
            Landlord removeLandLord = removeLandlord();
        });

        //Amend landlord's details
        JMenuItem amendDetails = new JMenuItem("  Amend Details", editIcon);
        amendDetails.setMnemonic(KeyEvent.VK_C);
        amendDetails.setToolTipText("Find a Landlord and update their details");
        amendDetails.addActionListener(ae -> {
            System.out.println("Change Landlord Details Clicked");
            Landlord searchedLandL = amendLandlord();
            if (searchedLandL != null) {
                AmendLandlordGUI amendLandlordGUI = new AmendLandlordGUI(searchedLandL);
            }
        });

        //Search for Landlord 
        JMenuItem searchLandlord = new JMenuItem("  Search Landlord", searchIcon);
        searchLandlord.setMnemonic(KeyEvent.VK_S);
        searchLandlord.setToolTipText("Search Landlord on the system");
        searchLandlord.addActionListener(ae -> {
            System.out.println("Search Landlord Clicked");
            Landlord seaLandlord = searchLandlord();
        });

        //Display all Landlords
        JMenuItem displayLandlord = new JMenuItem("  Display Landlords", displayIcon);
        displayLandlord.setMnemonic(KeyEvent.VK_L);
        displayLandlord.setToolTipText("Search Landlord on the system");
        displayLandlord.addActionListener(ae -> {
            System.out.println("Display Landlord Clicked");
            StringBuilder builder = new StringBuilder(landlordList.size());
            for (Landlord land : landlordList) {
                builder.append(land.toString() + "\n");
            }

            //JTextArea for displaying the list (StringBuilder)
            JTextArea textArea = new JTextArea(builder.toString());
            JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(null, scrollPane, "List of Landlords", JOptionPane.OK_OPTION);

            for (Landlord c : landlordList) {
                System.out.println(c.toString());
            }
        });

        landlords.add(registerLandlord);
        landlords.add(deRegisterLandlord);
        landlords.add(amendDetails);
        landlords.add(searchLandlord);
        landlords.add(displayLandlord);

//////////Properties MenuItems/////////////////////////////////////////////////////////////////
        //Record New Property
        JMenuItem recordNewProperty = new JMenuItem("  Record New Property", addIcon);
        recordNewProperty.setMnemonic(KeyEvent.VK_R);
        recordNewProperty.setToolTipText("Open Record New Property window");
        recordNewProperty.addActionListener(ae -> {
            RecordNewPropertyGUI addNewPropertyScreen = new RecordNewPropertyGUI();
        });

        //Delete Property 
        JMenuItem deleteProperty = new JMenuItem("  Delete Property", deleteIcon);
        deleteProperty.setMnemonic(KeyEvent.VK_D);
        deleteProperty.setToolTipText("Delete Property from the system");
        deleteProperty.addActionListener(ae -> {
            System.out.println("Delete Property Clicked");
            Property removeProperty = removeProperty();
        });

        //Search For Property 
        JMenuItem searchProperties = new JMenuItem("  Search For Property", searchIcon);
        searchProperties.setMnemonic(KeyEvent.VK_A);
        searchProperties.setToolTipText("Search For a Property on the system");
        searchProperties.addActionListener(ae -> {
            System.out.println("Search For Property Clicked");
        });

        //Display all Properties Available
        JMenuItem propertiesAvailable = new JMenuItem("  Display Properties Available", displayIcon);
        propertiesAvailable.setMnemonic(KeyEvent.VK_A);
        propertiesAvailable.setToolTipText("Search Properties on the system");
        propertiesAvailable.addActionListener(ae -> {
            System.out.println("Display Properties Clicked");

            StringBuilder builder = new StringBuilder(propertyAvailable.size());
            for (Property pr : propertyAvailable) {
                builder.append(pr.toString() + "\n");
            }

            //JTextArea for displaying the list (StringBuilder)
            JTextArea textArea = new JTextArea(builder.toString());
            JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(null, scrollPane, "List of Properties Available", JOptionPane.OK_OPTION);

            for (Property pro : propertyAvailable) {
                System.out.println(pro.toString());
            }

        });

        //Display all Properties 
        JMenuItem displayProperties = new JMenuItem("  Display Properties", displayIcon);
        displayProperties.setMnemonic(KeyEvent.VK_A);
        displayProperties.setToolTipText("Search Properties on the system");
        displayProperties.addActionListener(ae -> {
            System.out.println("Display Properties Clicked");

            StringBuilder builder = new StringBuilder(propertyList.size());
            for (Property pr : propertyList) {
                builder.append(pr.toString() + "\n");
            }

            //JTextArea for displaying the list (StringBuilder)
            JTextArea textArea = new JTextArea(builder.toString());
            JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(null, scrollPane, "List of Properties", JOptionPane.OK_OPTION);

            for (Property pro : propertyAvailable) {
                System.out.println(pro.toString());
            }

        });

        //List Properties Available/Let
        JMenuItem listPropertiesAvailable = new JMenuItem("  List Properties Available/Let", listIcon);
        listPropertiesAvailable.setMnemonic(KeyEvent.VK_L);
        listPropertiesAvailable.setToolTipText("List Properties Available/Let on the system");
        listPropertiesAvailable.addActionListener(ae -> {
            System.out.println("List Properties Available/Let Clicked");
            StringBuilder builder = new StringBuilder(propertyList.size());
            for (Property prop : propertyList) {
                builder.append(prop.toString() + "\n");
            }

            //JTextArea for displaying the list (StringBuilder)
            JTextArea textArea = new JTextArea(builder.toString());
            JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(null, scrollPane, "List of Properties not available", JOptionPane.OK_OPTION);

            for (Property p : propertyList) {
                System.out.println(p.toString());
            }
        });

        properties.add(recordNewProperty);
        properties.add(deleteProperty);
        properties.add(searchProperties);
        properties.add(displayProperties);
        properties.add(propertiesAvailable);
        properties.add(listPropertiesAvailable);

//////////Tenants Menu Items/////////////////////////////////////////////////////////////////
        //Register New Tenant
        JMenuItem registerNewTenant = new JMenuItem("  Register New Tenant", addIcon);
        registerNewTenant.setMnemonic(KeyEvent.VK_R);
        registerNewTenant.setToolTipText("Open Register New Tenant window");
        registerNewTenant.addActionListener(ae -> {
            RegisterNewTenantGUI addNewTenantScreen = new RegisterNewTenantGUI();
        });

        //Delete Tenant
        JMenuItem deleteTenant = new JMenuItem("  Delete Tenant", deleteIcon);
        deleteTenant.setMnemonic(KeyEvent.VK_D);
        deleteTenant.setToolTipText("Delete Tenant from the system");
        deleteTenant.addActionListener(ae -> {
            System.out.println("Delete Tenant Clicked");
            Tenant removeTenant = removeTenant();
        });

        //Amend tenant's details
        JMenuItem amendTenant = new JMenuItem("  Amend Tenant's Details", editIcon);
        amendTenant.setMnemonic(KeyEvent.VK_C);
        amendTenant.setToolTipText("Find a Tenant and update their details");
        amendTenant.addActionListener(ae -> {
            System.out.println("Change Tenant Details Clicked");
            Tenant amendTenantDetails = amendTenant();
            if (amendTenantDetails != null) {
                AmendTenantGUI amendTenantGUI = new AmendTenantGUI(amendTenantDetails);
            }
        });

        //Search For Tenant
        JMenuItem searchTenant = new JMenuItem("  Search For Tenant", searchIcon);
        searchTenant.setMnemonic(KeyEvent.VK_S);
        searchTenant.setToolTipText("Search For a Tenant on the system");
        searchTenant.addActionListener(ae -> {
            System.out.println("Search For Tenant Clicked");
            Tenant searchTen = searchTenant();
        });

        //Display All Tenants
        JMenuItem tenantsAvailable = new JMenuItem("  Display Available Tenants", displayIcon);
        tenantsAvailable.setMnemonic(KeyEvent.VK_P);
        tenantsAvailable.setToolTipText("Display Tenants on the system");
        tenantsAvailable.addActionListener(ae -> {
            System.out.println("Display Tenants Clicked");

            String s = tenantLts.getSelectedItem().toString();
            if (s == "Tenants Available") {
                StringBuilder builder = new StringBuilder(HomeScreenGUI.tenantsAvailable.size());
                for (Tenant land : HomeScreenGUI.tenantsAvailable) {
                    builder.append(land.toString() + "\n");
                }

                //JTextArea for displaying the list (StringBuilder)
                JTextArea textArea = new JTextArea(builder.toString());
                JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(500, 500));
                JOptionPane.showMessageDialog(null, scrollPane, "List of Tenants Available", JOptionPane.OK_OPTION);

                System.out.println("Tenant's Available List :");
                for (Tenant t : HomeScreenGUI.tenantsAvailable) {
                    System.out.println("Name: " + t.getName() + "\nID number: "
                            + t.getAddress() + "\nPhone No: " + t.getPhoneNumber() + "\nPPS Number: " + t.getPpsNumber()
                            + "\nTenant ID: " + t.getTenantID() + "\nTenant Status: " + t.getTenantStatus());
                }

            }
        });

        //Display All Tenants
        JMenuItem displayTenants = new JMenuItem("  Display Tenants List", displayIcon);
        displayTenants.setMnemonic(KeyEvent.VK_P);
        displayTenants.setToolTipText("Display Tenants on the system");
        displayTenants.addActionListener(ae -> {
            System.out.println("Display Tenants Clicked");

            String s = tenantLts.getSelectedItem().toString();
            if (s == "Tenants Available") {
                StringBuilder builder = new StringBuilder(tenantList.size());
                for (Tenant ten : tenantList) {
                    builder.append(ten.toString() + "\n");
                }

                //JTextArea for displaying the list (StringBuilder)
                JTextArea textArea = new JTextArea(builder.toString());
                JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(500, 500));
                JOptionPane.showMessageDialog(null, scrollPane, "List of Tenants Available", JOptionPane.OK_OPTION);

                System.out.println("Tenant's Available List :");
                for (Tenant t : HomeScreenGUI.tenantsAvailable) {
                    System.out.println("Name: " + t.getName() + "\nID number: "
                            + t.getAddress() + "\nPhone No: " + t.getPhoneNumber() + "\nPPS Number: " + t.getPpsNumber()
                            + "\nTenant ID: " + t.getTenantID() + "\nTenant Status: " + t.getTenantStatus());
                }

            }
        });

        tenants.add(registerNewTenant);
        tenants.add(deleteTenant);
        tenants.add(amendTenant);
        tenants.add(searchTenant);
        tenants.add(displayTenants);
        tenants.add(tenantsAvailable);

//////////Rentals Menu Items/////////////////////////////////////////////////////////////////
        //Process New Rental
        JMenuItem processNewRental = new JMenuItem("  Process New Rental", addIcon);
        processNewRental.setMnemonic(KeyEvent.VK_P);
        processNewRental.setToolTipText("Process New Rental on the system");
        processNewRental.addActionListener(ae -> {
            System.out.println("Process New Rental Clicked");
            StringBuilder builder = new StringBuilder(rentalList.size());
            for (Rental land : rentalList) {
                builder.append(land.toString() + "\n");
            }

            // pick a tenant
            int tenIdx = Integer.parseInt(JOptionPane.showInputDialog("Enter a tenant ID number "
                    + " between 1 and " + HomeScreenGUI.tenantsAvailable.size()));
            // pick a property
            int propIdx = Integer.parseInt(JOptionPane.showInputDialog("Enter a property ID number "
                    + " between 1 and " + propertyAvailable.size()));

            // create a new rental object
            Rental propertyRental = new Rental(propertyAvailable.get(tenIdx - 1),
                    HomeScreenGUI.tenantsAvailable.get(propIdx - 1));
            propertyAvailable.get(propIdx - 1).takeProperty();
            propertyRental.setTerm("1 year");
            propertyRental.setRate(750.00);
            // add the tenant to the property list
            rentalList.add(propertyRental);
            // add taken tenant to tenantList
            tenantList.add(HomeScreenGUI.tenantsAvailable.get(propIdx - 1));
            // remove taken tenant from tenantAvailable list
            HomeScreenGUI.tenantsAvailable.remove(HomeScreenGUI.tenantsAvailable.get(propIdx - 1));
            // add taken property to propertyLet list
            propertyLet.add(propertyAvailable.get(tenIdx - 1));
            // remove taken property from propertyAvailable list
            propertyAvailable.remove(propertyAvailable.get(tenIdx - 1));
        });

        //Search For Rental
        JMenuItem searchRental = new JMenuItem("  Search For Rental", searchIcon);
        searchRental.setMnemonic(KeyEvent.VK_S);
        searchRental.setToolTipText("Search For a Rental on the system");
        searchRental.addActionListener(ae -> {
            System.out.println("Search For Rental Clicked");
        });

        //Display All Rentals
        JMenuItem displayRentals = new JMenuItem("  Display Rentals", displayIcon);
        displayRentals.setMnemonic(KeyEvent.VK_D);
        displayRentals.setToolTipText("Display Rentals on the system");
        displayRentals.addActionListener(ae -> {
            System.out.println("Display Rentals Clicked");

            // note that this gives a very poor display layout
            // instead of using r.toString(), a better-organised display would be needed  
            area.setText("Class List\n");
            // adding scrollbar
            JScrollPane scroll = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            for (Rental r : rentalList) {
                area.append(r.toString());
                if (r.getProperty() instanceof Property) {
                    area.append(" for Class Name: " + ((Property) (r.getProperty())).getAddress_() + "\n\n");
                }
            }
            // setting scrollbar dimension
            scroll.setPreferredSize(new Dimension(500, 500));
            JOptionPane.showMessageDialog(null, scroll);
        });

        rentals.add(processNewRental);
        rentals.add(searchRental);
        rentals.add(displayRentals);

//////////Help Menu Items/////////////////////////////////////////////////////////////////
        //The about page
        JMenuItem about = new JMenuItem("  About", aboutIcon);
        about.setMnemonic(KeyEvent.VK_A);
        about.setToolTipText("Information about this system");
        about.addActionListener(ae -> {
            System.out.println("Information about this system Clicked");
            JOptionPane.showMessageDialog(null, "This application was developed by David O' Connor \n"
                    + "as an exercise to further his Java Programming skills");
        });

        //Help/documentation Page
        JMenuItem documentation = new JMenuItem("  Help", helpIcon);
        documentation.setMnemonic(KeyEvent.VK_H);
        documentation.setToolTipText("Opens the help page. This shows information with regard to the operation of this system");
        documentation.addActionListener(ae -> {
            System.out.println("Display help page clicked");
            JOptionPane.showMessageDialog(null, "Use the menu bar on the home Property Management page \n"
                    + "for carrying out tasks. To use the hotkeys, hold: \nalt + press the underlined letter of the option you would like.\n"
                    + "Eg: ALT + T opens the Tenant menu. Now you can use the arrows to navigate");
        });

        help.add(about);
        help.add(documentation);

        //Add top menu bar items
        menuBar.add(file);
        menuBar.add(landlords);
        menuBar.add(properties);
        menuBar.add(tenants);
        menuBar.add(rentals);
        menuBar.add(help);

        //Set the Menu bar
        setJMenuBar(menuBar);
    }//End of createMenuBar Method

    //Saving databases/ArrayLists methods
    private void saveDatabases() {
        //Save the Landlord's File
        saveLandlordsFile();
        //Save the Properties Files
        savePropertiesListFile();
        savePropertiesFile();
        savePropertiesLetFile();
        //Save the Tenants Files
        saveTenantsFile();
        saveTenantsListFile();
        //Save the Rentals File
        saveRentalsFile();
    }

    private void saveLandlordsFile() {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("files/landlords.data"));
            os.writeObject(HomeScreenGUI.landlordList);
            os.close();
        } catch (Exception e) {
            System.out.println("Error occurred when trying to save landlords.dat file");
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "landlord(s) successfully written to file\n");
    }

    private void savePropertiesFile() {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("files/property.data"));
            os.writeObject(HomeScreenGUI.propertyAvailable);
            os.close();
        } catch (Exception e) {
            System.out.println("Error occurred when trying to save properties.dat file");
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Property/Properties successfully written to file\n");
    }

    private void savePropertiesListFile() {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("files/propertyList.data"));
            os.writeObject(HomeScreenGUI.propertyList);
            os.close();
        } catch (Exception e) {
            System.out.println("Error occurred when trying to save propertiesList.dat file");
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Property/Properties successfully written to file\n");
    }

    private void savePropertiesLetFile() {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("files/propertyLet.data"));
            os.writeObject(HomeScreenGUI.propertyLet);
            os.close();
        } catch (Exception e) {
            System.out.println("Error occurred when trying to save propertiesLet.dat file");
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Property/Properties successfully written to file\n");
    }

    private void saveTenantsFile() {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("files/tenants.data"));
            os.writeObject(HomeScreenGUI.tenantsAvailable);
            os.close();
        } catch (Exception e) {
            System.out.println("Error occurred when trying to save tenants.dat file");
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Tenant(s) successfully written to file\n");
    }

    private void saveTenantsListFile() {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("files/tenantsList.data"));
            os.writeObject(HomeScreenGUI.tenantList);
            os.close();
        } catch (Exception e) {
            System.out.println("Error occurred when trying to save tenantsList.dat file");
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Tenant(s) successfully written to file\n");
    }

    private void saveRentalsFile() {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("files/rentals.data"));
            os.writeObject(HomeScreenGUI.rentalList);
            os.close();
        } catch (Exception e) {
            System.out.println("Error occurred when trying to save rentals.dat file");
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Rental(s) successfully written to file\n");
    }

    //Loading databases/ArrayLists methods
    private void loadDatabases() {
        //Load the Landlords File
        loadLandlordsFile();

        //Load the Properties Files
        loadPropertiesListFile();
        loadPropertiesFile();
        loadPropertiesLetFile();

        //Load the Tenants Files
        loadTenantsFile();
        loadTenantsListFile();

        //Load the Rentals File
        loadRentalsFile();
    }

    private void loadLandlordsFile() {
        ObjectInputStream is;
        try {
            is = new ObjectInputStream(new FileInputStream("files/landlords.data"));
            HomeScreenGUI.landlordList = (ArrayList<Landlord>) is.readObject();// note the typecast
            is.close(); //Close the Input Stream
            //JOptionPane.showMessageDialog(null, "Landlords successfully loaded from file\n");
        } catch (Exception e) {
            System.out.println("Error loading Landlords.dat file");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading the Landlords file. System closing\n");
        }
    }

    private void loadPropertiesListFile() {
        ObjectInputStream is;
        try {
            is = new ObjectInputStream(new FileInputStream("files/propertyList.data"));
            HomeScreenGUI.propertyList = (ArrayList<Property>) is.readObject();// note the typecast
            is.close(); //Close the Input Stream
            //JOptionPane.showMessageDialog(null, "Properties successfully loaded from file\n");
        } catch (Exception e) {
            System.out.println("Error loading propertiesList.dat file");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading the Properties file. System closing\n");
        }
    }

    private void loadPropertiesFile() {
        ObjectInputStream is;
        try {
            is = new ObjectInputStream(new FileInputStream("files/property.data"));
            HomeScreenGUI.propertyAvailable = (ArrayList<Property>) is.readObject();// note the typecast
            is.close(); //Close the Input Stream
            //JOptionPane.showMessageDialog(null, "Properties successfully loaded from file\n");
        } catch (Exception e) {
            System.out.println("Error loading properties.dat file");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading the Properties file. System closing\n");
        }
    }

    private void loadPropertiesLetFile() {
        ObjectInputStream is;
        try {
            is = new ObjectInputStream(new FileInputStream("files/propertyLet.data"));
            HomeScreenGUI.propertyLet = (ArrayList<Property>) is.readObject();// note the typecast
            is.close(); //Close the Input Stream
            //JOptionPane.showMessageDialog(null, "Properties successfully loaded from file\n");
        } catch (Exception e) {
            System.out.println("Error loading properties.dat file");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading the Properties file. System closing\n");
        }
    }

    private void loadTenantsFile() {
        ObjectInputStream is;
        try {
            is = new ObjectInputStream(new FileInputStream("files/tenants.data"));
            HomeScreenGUI.tenantsAvailable = (ArrayList<Tenant>) is.readObject();// note the typecast
            is.close(); //Close the Input Stream
            //JOptionPane.showMessageDialog(null, "Tenants successfully loaded from file\n");
        } catch (Exception e) {
            System.out.println("Error loading Tenants.dat file");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading the Tenants file. System closing\n");
        }
    }

    private void loadTenantsListFile() {
        ObjectInputStream is;
        try {
            is = new ObjectInputStream(new FileInputStream("files/tenantsList.data"));
            HomeScreenGUI.tenantList = (ArrayList<Tenant>) is.readObject();// note the typecast
            is.close(); //Close the Input Stream
            //JOptionPane.showMessageDialog(null, "Tenants successfully loaded from file\n");
        } catch (Exception e) {
            System.out.println("Error loading Tenants.dat file");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading the Tenants file. System closing\n");
        }
    }

    private void loadRentalsFile() {
        ObjectInputStream is;
        try {
            is = new ObjectInputStream(new FileInputStream("files/rentals.data"));
            HomeScreenGUI.rentalList = (ArrayList<Rental>) is.readObject();// note the typecast
            is.close(); //Close the Input Stream
            //JOptionPane.showMessageDialog(null, "Rentals successfully loaded from file\n");
        } catch (Exception e) {
            System.out.println("Error loading Rentals.dat file");
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading the Rentals file. System closing\n");
        }
    }

    private Landlord amendLandlord() {
        boolean foundMatch = false;
        ArrayList<String> landlordIdList = new ArrayList<String>();
        String landlIDString = "";
        String selectedLandlord = "";
        Object[] landlordIds = {};
        String searchName = JOptionPane.showInputDialog("Enter name of landlord: ");
        Landlord searchedLandlord = new Landlord();

        if (searchName.equals("")) {
            JOptionPane.showMessageDialog(null, "No name entered!");
        } else {
            StringBuilder builder = new StringBuilder(landlordList.size());
            while (!foundMatch) {
                for (Landlord lan : landlordList) {
                    if (lan.getName().equalsIgnoreCase(searchName)) {
                        builder.append(lan.toString() + "\n");
                        landlIDString = Integer.toString(lan.getLandlordID());
                        landlordIdList.add(landlIDString);
                        landlordIds = landlordIdList.toArray();
                        foundMatch = true;
                    }
                }
                //JTextArea for displaying the list (StringBuilder)
                JTextArea textArea = new JTextArea(builder.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(500, 500));

                //Display list if not empty
                if (!(textArea.getText().trim().length() == 0)) {
                    JOptionPane.showMessageDialog(null, scrollPane, "List of Landlords", JOptionPane.OK_OPTION);
                    //In here should go the select patient code
                    JFrame frame = new JFrame("Please select landlord");
                    selectedLandlord = (String) JOptionPane.showInputDialog(frame,
                            "Which landlord do you wish to change details for? ",
                            "Please select Landlord's ID number:",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            landlordIds,
                            landlordIds[0]);
                } else if (textArea.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "No Landlord registered by the name: " + searchName);
                    foundMatch = true;
                    return null;
                }
                //Now I have the selected landlord ID I must now open a new window displaying their details and allow to change
                for (Landlord land : landlordList) {
                    if (selectedLandlord.equals("" + land.getLandlordID())) {
                        searchedLandlord = land;
                    }
                }
            }
        }
        return searchedLandlord;
    }

    private Landlord searchLandlord() {
        boolean foundMatch = false;
        ArrayList<String> landlordIdList = new ArrayList<String>();
        String landlIDString = "";
        String selectedLandlord = "";
        Object[] landlordIds = {};
        String searchName = JOptionPane.showInputDialog("Enter name of Landlord: ");
        Landlord searchedLandlord = new Landlord();

        if (searchName.equals("")) {
            JOptionPane.showMessageDialog(null, "No name entered!");
        } else {
            StringBuilder builder = new StringBuilder(landlordList.size());
            while (!foundMatch) {
                for (Landlord lan : landlordList) {
                    if (lan.getName().equalsIgnoreCase(searchName)) {
                        builder.append(lan.toString() + "\n");
                        landlIDString = Integer.toString(lan.getLandlordID());
                        landlordIdList.add(landlIDString);
                        landlordIds = landlordIdList.toArray();
                        foundMatch = true;
                    }
                }
                //JTextArea for displaying the list (StringBuilder)
                JTextArea textArea = new JTextArea(builder.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(500, 500));

                //Display list if not empty
                if (!(textArea.getText().trim().length() == 0)) {
                    JOptionPane.showMessageDialog(null, scrollPane, "List of Landlords", JOptionPane.OK_OPTION);
                } else if (textArea.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "No Landlord registered by the name: " + searchName);
                    foundMatch = true;
                    return null;
                }
                //Now I have the selected landlord ID I must now open a new window displaying their details and allow to change
                for (Landlord land : landlordList) {
                    if (selectedLandlord.equals("" + land.getLandlordID())) {
                        searchedLandlord = land;
                    }
                }
            }
        }
        return searchedLandlord;
    }

    private Landlord removeLandlord() {
        boolean foundMatch = false;
        ArrayList<String> landlordIdList = new ArrayList<String>();
        String landlIDString = "";
        String selectedLandlord = "";
        Object[] landlordIds = {};
        String removeName = JOptionPane.showInputDialog("Please enter the name of the landlord you wish to remove: ");
        Landlord removedLandlord = new Landlord();

        if (removeName.equals("")) {
            JOptionPane.showMessageDialog(null, "No name entered!");
        } else {
            StringBuilder builder = new StringBuilder(landlordList.size());
            while (!foundMatch) {
                for (Landlord lan : landlordList) {
                    if (lan.getName().equalsIgnoreCase(removeName)) {
                        builder.append(lan.toString() + "\n");
                        landlIDString = Integer.toString(lan.getLandlordID());
                        landlordIdList.add(landlIDString);
                        landlordIds = landlordIdList.toArray();
                        foundMatch = true;
                    }
                }
                //JTextArea for displaying the list (StringBuilder)
                JTextArea textArea = new JTextArea(builder.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(500, 500));

                for (int i = 0; i < landlordList.size(); i++) {
                    if (removeName.equals(landlordList.get(i).getName())) {
                        landlordList.remove(i);
                        JOptionPane.showMessageDialog(null, removeName + " has been removed from the Landlord's List");
                    } else if (!removeName.equals(landlordList.get(i).getName())) {
                        JOptionPane.showMessageDialog(null, removeName + " is not on the Landlord's List");
                    }
                }
                break;
            }
        }
        return removedLandlord;
    }

    private Property removeProperty() {

        JFrame removePropertyMenu = new JFrame();
        removePropertyMenu.setVisible(true);
        removePropertyMenu.setSize(200, 200);
        removePropertyMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removePropertyMenu.setLocationRelativeTo(null);

        JPanel removeProperty = new JPanel();
        removeProperty.add(delProText);
        removeProperty.add(delPropertyLts);
        removeProperty.add(b);
        removeProperty.add(c);

        removePropertyMenu.add(removeProperty);

        Property removedProperty = new Property();

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String s = delPropertyLts.getSelectedItem().toString();
                if (s == "Property Available") {
                    boolean foundMatch = false;
                    ArrayList<String> propertyIdList = new ArrayList<String>();
                    String propertyIDString = "";
                    String selectedProperty = "";
                    Object[] propertyIds = {};
                    String removeProperty = JOptionPane.showInputDialog("Please enter the address of the property you wish to remove: ");

                    if (removeProperty.equals("")) {
                        JOptionPane.showMessageDialog(null, "No address entered!");
                    } else {
                        StringBuilder builder = new StringBuilder(propertyAvailable.size());
                        while (!foundMatch) {
                            for (Property prop : propertyAvailable) {
                                if (prop.getAddress_().equalsIgnoreCase(removeProperty)) {
                                    builder.append(prop.toString() + "\n");
                                    propertyIDString = Integer.toString(prop.getPropertyID_());
                                    propertyIdList.add(propertyIDString);
                                    propertyIds = propertyIdList.toArray();
                                    foundMatch = true;
                                }
                            }
                            //JTextArea for displaying the list (StringBuilder)
                            JTextArea textArea = new JTextArea(builder.toString());
                            JScrollPane scrollPane = new JScrollPane(textArea);
                            textArea.setLineWrap(true);
                            textArea.setWrapStyleWord(true);
                            scrollPane.setPreferredSize(new Dimension(500, 500));

                            for (int i = 0; i < propertyAvailable.size(); i++) {
                                if (removeProperty.equals(propertyAvailable.get(i).getAddress_())) {
                                    propertyAvailable.remove(i);
                                    JOptionPane.showMessageDialog(null, removeProperty + " has been removed from the Properties List");
                                } else if (!removeProperty.equals(propertyAvailable.get(i).getAddress_())) {
                                    JOptionPane.showMessageDialog(null, removeProperty + " is not on the Properties List");
                                }
                            }
                            break;
                        }
                    }
                } else {
                    boolean foundMatch = false;
                    ArrayList<String> tenantIdList = new ArrayList<String>();
                    String tenantIDString = "";
                    String selectedTenant = "";
                    Object[] tenantIds = {};
                    String removeProperty = JOptionPane.showInputDialog("Please enter the address of the property you wish to remove: ");

                    if (removeProperty.equals("")) {
                        JOptionPane.showMessageDialog(null, "No address entered!");
                    } else {
                        StringBuilder builder = new StringBuilder(propertyList.size());
                        while (!foundMatch) {
                            for (Property prop : propertyList) {
                                if (prop.getAddress_().equalsIgnoreCase(removeProperty)) {
                                    builder.append(prop.toString() + "\n");
                                    tenantIDString = Integer.toString(prop.getPropertyID_());
                                    tenantIdList.add(tenantIDString);
                                    tenantIds = tenantIdList.toArray();
                                    foundMatch = true;
                                }
                            }
                            //JTextArea for displaying the list (StringBuilder)
                            JTextArea textArea = new JTextArea(builder.toString());
                            JScrollPane scrollPane = new JScrollPane(textArea);
                            textArea.setLineWrap(true);
                            textArea.setWrapStyleWord(true);
                            scrollPane.setPreferredSize(new Dimension(500, 500));

                            for (int i = 0; i < propertyList.size(); i++) {
                                if (removeProperty.equals(propertyList.get(i).getAddress_())) {
                                    propertyList.remove(i);
                                    JOptionPane.showMessageDialog(null, removeProperty + " has been removed from the Properties List");
                                } else if (!removeProperty.equals(propertyList.get(i).getAddress_())) {
                                    JOptionPane.showMessageDialog(null, removeProperty + " is not on the Properties List");
                                }
                            }
                            break;
                        }
                    }
                }
            }

        });

        c.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removePropertyMenu.dispose();
            }
        });

        return removedProperty;

    }

    private Tenant removeTenant() {

        JFrame removeTenMenu = new JFrame();
        removeTenMenu.setVisible(true);
        removeTenMenu.setSize(200, 200);
        removeTenMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeTenMenu.setLocationRelativeTo(null);

        JPanel removeTenant = new JPanel();
        removeTenant.add(delTenText);
        removeTenant.add(delTenantLts);
        removeTenant.add(g);
        removeTenant.add(h);

        removeTenMenu.add(removeTenant);

        Tenant removedTenant = new Tenant();

        g.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String s = delTenantLts.getSelectedItem().toString();
                if (s == "Tenants Available") {
                    boolean foundMatch = false;
                    ArrayList<String> tenantIdList = new ArrayList<String>();
                    String tenantIDString = "";
                    String selectedTenant = "";
                    Object[] tenantIds = {};
                    String removeName = JOptionPane.showInputDialog("Please enter the name of the tenant you wish to remove: ");

                    if (removeName.equals("")) {
                        JOptionPane.showMessageDialog(null, "No name entered!");
                    } else {
                        StringBuilder builder = new StringBuilder(tenantsAvailable.size());
                        while (!foundMatch) {
                            for (Tenant ten : tenantsAvailable) {
                                if (ten.getName().equalsIgnoreCase(removeName)) {
                                    builder.append(ten.toString() + "\n");
                                    tenantIDString = Integer.toString(ten.getTenantID());
                                    tenantIdList.add(tenantIDString);
                                    tenantIds = tenantIdList.toArray();
                                    foundMatch = true;
                                }
                            }
                            //JTextArea for displaying the list (StringBuilder)
                            JTextArea textArea = new JTextArea(builder.toString());
                            JScrollPane scrollPane = new JScrollPane(textArea);
                            textArea.setLineWrap(true);
                            textArea.setWrapStyleWord(true);
                            scrollPane.setPreferredSize(new Dimension(500, 500));

                            for (int i = 0; i < tenantsAvailable.size(); i++) {
                                if (removeName.equals(tenantsAvailable.get(i).getName())) {
                                    tenantsAvailable.remove(i);
                                    JOptionPane.showMessageDialog(null, removeName + " has been removed from the Tenant's List");
                                } else if (!removeName.equals(tenantsAvailable.get(i).getName())) {
                                    JOptionPane.showMessageDialog(null, removeName + " is not on the Tenant's List");
                                }
                            }
                            break;
                        }
                    }
                } else {
                    boolean foundMatch = false;
                    ArrayList<String> tenantIdList = new ArrayList<String>();
                    String tenantIDString = "";
                    String selectedTenant = "";
                    Object[] tenantIds = {};
                    String removeName = JOptionPane.showInputDialog("Please enter the name of the tenant you wish to remove: ");

                    if (removeName.equals("")) {
                        JOptionPane.showMessageDialog(null, "No name entered!");
                    } else {
                        StringBuilder builder = new StringBuilder(tenantList.size());
                        while (!foundMatch) {
                            for (Tenant ten : tenantList) {
                                if (ten.getName().equalsIgnoreCase(removeName)) {
                                    builder.append(ten.toString() + "\n");
                                    tenantIDString = Integer.toString(ten.getTenantID());
                                    tenantIdList.add(tenantIDString);
                                    tenantIds = tenantIdList.toArray();
                                    foundMatch = true;
                                }
                            }
                            //JTextArea for displaying the list (StringBuilder)
                            JTextArea textArea = new JTextArea(builder.toString());
                            JScrollPane scrollPane = new JScrollPane(textArea);
                            textArea.setLineWrap(true);
                            textArea.setWrapStyleWord(true);
                            scrollPane.setPreferredSize(new Dimension(500, 500));

                            for (int i = 0; i < tenantList.size(); i++) {
                                if (removeName.equals(tenantList.get(i).getName())) {
                                    tenantList.remove(i);
                                    JOptionPane.showMessageDialog(null, removeName + " has been removed from the Tenant's List");
                                } else if (!removeName.equals(tenantList.get(i).getName())) {
                                    JOptionPane.showMessageDialog(null, removeName + " is not on the Tenant's List");
                                }
                            }
                            break;
                        }
                    }
                }
            }

        });

        h.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeTenMenu.dispose();
            }
        });

        return removedTenant;

    }

    private Tenant amendTenant() {

        JFrame amendTenMenu = new JFrame();
        amendTenMenu.setVisible(true);
        amendTenMenu.setSize(200, 200);
        amendTenMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        amendTenMenu.setLocationRelativeTo(null);

        JPanel amendTenant = new JPanel();
        amendTenant.add(amdTenText);
        amendTenant.add(amdTenantLts);
        amendTenant.add(i);
        amendTenant.add(j);

        amendTenMenu.add(amendTenant);

        Tenant amdTenant = new Tenant();         
        
        
        
        i.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String s = delTenantLts.getSelectedItem().toString();
                if (s == "Tenants Available") {
                    
                    boolean foundMatch = false;
        ArrayList<String> tenantIdList = new ArrayList<String>();
        String tenIDString = "";
        String selectedTenant = "";
        Object[] tenantIds = {};
        String searchTenant = JOptionPane.showInputDialog("Enter name of tenant: ");
        Tenant searchedTenant = new Tenant();
        
        if (searchTenant.equals("")) {
            JOptionPane.showMessageDialog(null, "No name entered!");
        } else {
            StringBuilder builder = new StringBuilder(tenantsAvailable.size());
            while (!foundMatch) {
                for (Tenant ten : tenantsAvailable) {
                    if (ten.getName().equalsIgnoreCase(searchTenant)) {
                        builder.append(ten.toString() + "\n");
                        tenIDString = Integer.toString(ten.getTenantID());
                        tenantIdList.add(tenIDString);
                        tenantIds = tenantIdList.toArray();
                        foundMatch = true;
                    }
        }
                //JTextArea for displaying the list (StringBuilder)
                JTextArea textArea = new JTextArea(builder.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(500, 500));
                
                //Display list if not empty
                if (!(textArea.getText().trim().length() == 0)) {
                    JOptionPane.showMessageDialog(null, scrollPane, "List of Tenants", JOptionPane.OK_OPTION);
                    //In here should go the select patient code
                    JFrame frame = new JFrame("Please select tenant");
                    selectedTenant = (String) JOptionPane.showInputDialog(frame,
                            "Which tenant do you wish to change details for? ",
                            "Please select Tenant's ID number:",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            tenantIds,
                            tenantIds[0]);
                } else if (textArea.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "No Tenant registered by the name: " + searchTenant);
                    foundMatch = true;
                    //return null;
                }
                //Now I have the selected tenant ID I must now open a new window displaying their details and allow to change
                for (Tenant tent : tenantsAvailable) {
                    if (selectedTenant.equals("" + tent.getTenantID())) {
                        searchedTenant = tent;
                    }
                }break;
            }
        }
        //return searchedTenant;
                    
                    
                    
                } else {
                    
                    boolean foundMatch = false;
        ArrayList<String> tenantIdList = new ArrayList<String>();
        String tenIDString = "";
        String selectedTenant = "";
        Object[] tenantIds = {};
        String searchTenant = JOptionPane.showInputDialog("Enter name of tenant: ");
        Tenant searchedTenant = new Tenant();
        
        if (searchTenant.equals("")) {
            JOptionPane.showMessageDialog(null, "No name entered!");
        } else {
            StringBuilder builder = new StringBuilder(tenantList.size());
            while (!foundMatch) {
                for (Tenant ten : tenantList) {
                    if (ten.getName().equalsIgnoreCase(searchTenant)) {
                        builder.append(ten.toString() + "\n");
                        tenIDString = Integer.toString(ten.getTenantID());
                        tenantIdList.add(tenIDString);
                        tenantIds = tenantIdList.toArray();
                        foundMatch = true;
                    }
        }
                //JTextArea for displaying the list (StringBuilder)
                JTextArea textArea = new JTextArea(builder.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(500, 500));
                
                //Display list if not empty
                if (!(textArea.getText().trim().length() == 0)) {
                    JOptionPane.showMessageDialog(null, scrollPane, "List of Tenants", JOptionPane.OK_OPTION);
                    //In here should go the select patient code
                    JFrame frame = new JFrame("Please select tenant");
                    selectedTenant = (String) JOptionPane.showInputDialog(frame,
                            "Which tenant do you wish to change details for? ",
                            "Please select Tenant's ID number:",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            tenantIds,
                            tenantIds[0]);
                } else if (textArea.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "No Tenant registered by the name: " + searchTenant);
                    foundMatch = true;
                    //return null;
                }
                //Now I have the selected tenant ID I must now open a new window displaying their details and allow to change
                for (Tenant tent : tenantList) {
                    if (selectedTenant.equals("" + tent.getTenantID())) {
                        searchedTenant = tent;
                    }
                }
            }
        }
        
                    
                    
                }
            }

        });

        j.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                amendTenMenu.dispose();
            }
        });

        
        return amdTenant;
    }

    private Tenant searchTenant() {
        boolean foundMatch = false;
        ArrayList<String> tenantIdList = new ArrayList<String>();
        String tenantlIDString = "";
        String selectedTenant = "";
        Object[] tenantIds = {};
        String searchName = JOptionPane.showInputDialog("Enter name of tenant: ");
        Tenant searchedTenant = new Tenant();

        if (searchName.equals("")) {
            JOptionPane.showMessageDialog(null, "No name entered!");
        } else {
            StringBuilder builder = new StringBuilder(landlordList.size());
            while (!foundMatch) {
                for (Tenant ten : tenantsAvailable) {
                    if (ten.getName().equalsIgnoreCase(searchName)) {
                        builder.append(ten.toString() + "\n");
                        tenantlIDString = Integer.toString(ten.getTenantID());
                        tenantIdList.add(tenantlIDString);
                        tenantIds = tenantIdList.toArray();
                        foundMatch = true;
                    }
                }
                //JTextArea for displaying the list (StringBuilder)
                JTextArea textArea = new JTextArea(builder.toString());
                JScrollPane scrollPane = new JScrollPane(textArea);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                scrollPane.setPreferredSize(new Dimension(500, 500));

                //Display list if not empty
                if (!(textArea.getText().trim().length() == 0)) {
                    JOptionPane.showMessageDialog(null, scrollPane, "List of Landlords", JOptionPane.OK_OPTION);
                } else if (textArea.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "No Landlord registered by the name: " + searchName);
                    foundMatch = true;
                    return null;
                }
                //Now I have the selected landlord ID I must now open a new window displaying their details and allow to change
                for (Tenant tent : tenantsAvailable) {
                    if (selectedTenant.equals("" + tent.getTenantID())) {
                        searchedTenant = tent;
                    }
                }
            }
        }
        return searchedTenant;
    }
}
