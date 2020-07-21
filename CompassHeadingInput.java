/*
 Tommy Wan
 3/22/2020
 This class is the Compass Heading Input where user inputs information
 */
package wansteam;

//Import required packages
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Create class with nessesary requirements
public class CompassHeadingInput extends JFrame implements ActionListener
{
    //Declare All Text Fields
    private JPanel inputPanel;
    private JPanel lowerPanel;
    private JPanel middlePanel;
    private JTextField trueCourseField;
    private JLabel trueCourseLabel;
    private JTextField windCorrectionField;
    private JLabel windCorrectionLabel;
    private JTextField magneticVariationField;
    private JLabel magneticVariationLabel;
    private JTextField magneticDeviationField;
    private JLabel magneticDeviationLabel;
    private JLabel emptyLabel;

    //Buttons
    private JButton calculateButton;
    private JButton backButton;

    //Menus for General
    private JMenuBar mainBar; //Menu Bar
    private JMenu navigationMenu; //Menu
    private JMenuItem helpItem;
    private JMenuItem definitionItem;
    private JMenuItem aboutItem;
    private JMenuItem faqItem;
    private JMenuItem homeItem;
    private JMenuItem activityLogItem;
    private JMenuItem exitItem;

    //Images
    private final URL COMPASS_PATH = getClass().getResource("compass.jpg");
    private final ImageIcon COMPASS_IMAGE = new ImageIcon(new ImageIcon(COMPASS_PATH).getImage().getScaledInstance(630, 350, Image.SCALE_DEFAULT));
    private JLabel imageLabel;

    //Styles, Fonts, and Colorings
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 30);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final Font SUB_FONT = new Font("Arial", Font.BOLD, 40);
    public static final Color COLOR_BLUE = new Color(1, 103, 253);
    public static final Color COLOR_WHITE = new Color(255, 255, 255);

    CompassHeadingDisplay gFrame;
    
    //Constructor for Input Frame
    public CompassHeadingInput(CompassHeadingDisplay pFrame)
    {
        //Super Heading on Top
        super("Calculate Compass Heading");

        //Default Operations in the frame
        this.setBounds(300, 200, 800, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(COLOR_BLUE);
        this.setLayout(new BorderLayout());

        this.gFrame = pFrame;
        
        //Constructing the Menus and Information
        this.mainBar = new JMenuBar();
        this.navigationMenu = new JMenu("Navigation");
        this.helpItem = new JMenuItem("Help");
        this.helpItem.addActionListener(this);
        this.definitionItem = new JMenuItem("Definitions");
        this.definitionItem.addActionListener(this);
        this.aboutItem = new JMenuItem("About Me!");
        this.aboutItem.addActionListener(this);
        this.faqItem = new JMenuItem("Frequently Asked Questions");
        this.faqItem.addActionListener(this);
        this.activityLogItem = new JMenuItem("Activity Log");
        this.activityLogItem.addActionListener(this);
        this.homeItem = new JMenuItem("Home");
        this.homeItem.addActionListener(this);
        this.exitItem = new JMenuItem("Exit");
        this.exitItem.addActionListener(this);
        mainBar.add(navigationMenu);
        navigationMenu.add(helpItem);
        navigationMenu.add(definitionItem);
        navigationMenu.add(aboutItem);
        navigationMenu.add(faqItem);
        navigationMenu.add(activityLogItem);
        navigationMenu.add(homeItem);
        navigationMenu.add(exitItem);

        //Input Panel Code
        this.inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(COLOR_BLUE);

        //Lower Panel Code which contains buttons
        this.lowerPanel = new JPanel(new FlowLayout());
        lowerPanel.setBackground(COLOR_BLUE);

        //Middle Panel Code
        this.middlePanel = new JPanel(new FlowLayout());
        middlePanel.setBackground(COLOR_BLUE);

        //Text Fields 
        this.trueCourseField = new JTextField(3);
        this.windCorrectionField = new JTextField(3);
        this.magneticVariationField = new JTextField(3);
        this.magneticDeviationField = new JTextField(3);

        //Buttons
        this.backButton = new JButton("Back");
        this.backButton.addActionListener(this);
        this.calculateButton = new JButton("Calculate");
        this.calculateButton.addActionListener(this);

        //Labels and Text Fields on Top
        this.trueCourseLabel = new JLabel("True Course (°): ");
        this.trueCourseLabel.setForeground(COLOR_WHITE);
        this.windCorrectionLabel = new JLabel("Wind Correction Angle (°): ");
        this.windCorrectionLabel.setForeground(COLOR_WHITE);
        this.magneticVariationLabel = new JLabel("Magnetic Variation (°): ");
        this.magneticVariationLabel.setForeground(COLOR_WHITE);
        this.magneticDeviationLabel = new JLabel("Magnetic Deviaiton (°): ");
        this.magneticDeviationLabel.setForeground(COLOR_WHITE);
        this.emptyLabel = new JLabel("<html><br></html>");

        //Image Label
        this.imageLabel = new JLabel(COMPASS_IMAGE);

        //Add Buttons to LowerPanel
        this.lowerPanel.add(calculateButton);
        this.lowerPanel.add(backButton);

        //Add Fields to Input with Label
        this.inputPanel.add(trueCourseLabel);
        this.inputPanel.add(trueCourseField);
        this.inputPanel.add(windCorrectionLabel);
        this.inputPanel.add(windCorrectionField);
        this.inputPanel.add(magneticVariationLabel);
        this.inputPanel.add(magneticVariationField);
        this.inputPanel.add(magneticDeviationLabel);
        this.inputPanel.add(magneticDeviationField);

        //Adding Image and Size Preferences to Middle Panel
        this.middlePanel.add(imageLabel);
        this.middlePanel.setPreferredSize(new Dimension(5, 8));

        //Adds Panels inside the frame
        this.add(inputPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        //Set Visible to User
        this.setJMenuBar(mainBar);
        this.setVisible(true);
    }

    //Main method
    public static void main(String[] args)
    {
        String dbName = "AviationDatabase";
        String tableName = "CompassHeading";
        String[] columnHeaders =
        {
           "TrueCourse", "WindCorrectionAngle", "MagneticVariation", "MagneticDeviation", "CompassHeading"
        };
        
        CompassHeadingDisplay mainObj = new CompassHeadingDisplay(dbName, tableName, columnHeaders);        
        CompassHeadingInput compassHeadingInputObj = new CompassHeadingInput(mainObj);
    }

    //Override Method to have for buttons
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //list out variables
        String command = e.getActionCommand();
        
        String dbName = "AviationDatabase";
        String tableName = "CompassHeading";
        String[] tableHeaders =
        {
           "TrueCourse", "WindCorrectionAngle", "MagneticVariation", "MagneticDeviation", "CompassHeading"
        }; 
        //note: the spaces are put in so that the table headers will be centered.
        //new DistanceDisplay(dbName, tableName, tableHeaders);
        
        gFrame.setVisible(false);
        String dbQuery = "INSERT INTO CompassHeading VALUES (?, ?, ?, ?, ?)";
        
        //Connect to db
        JavaDatabase objDb = new JavaDatabase(dbName);
        Connection myDbConn = objDb.getDbConn();

        //list out variables
        Object myComponent = e.getSource();
        String trueCourseInput;
        String windCorrectionAngleInput;
        String magneticVariationInput;
        String magneticDeviationInput;
        double trueCourseValue;
        double windCorrectionAngleValue;
        double magneticVariationValue;
        double magneticDeviationValue;
        double answer;
        double output;
        String message;

        //if state ments for buttons
        if (command.equals("Calculate"))
        {
            try
            {
                //set equate
                trueCourseInput = trueCourseField.getText();
                windCorrectionAngleInput = windCorrectionField.getText();
                magneticVariationInput = magneticVariationField.getText();
                magneticDeviationInput = magneticDeviationField.getText();

                //Parse Int for All Variables
                trueCourseValue = Double.parseDouble(trueCourseField.getText());
                windCorrectionAngleValue = Double.parseDouble(windCorrectionField.getText());
                magneticVariationValue = Double.parseDouble(magneticVariationField.getText());
                magneticDeviationValue = Double.parseDouble(magneticDeviationField.getText());

                //Set empty textField
                trueCourseField.setText("");
                windCorrectionField.setText("");
                magneticVariationField.setText("");
                magneticDeviationField.setText("");

                try
                {
                    //Calculations from the calculation class
                    CompassHeadingAddition compassHeadingObj = new CompassHeadingAddition(trueCourseValue, windCorrectionAngleValue, magneticVariationValue, magneticDeviationValue); //Recives two values,  and shows it in the passed values
                    output = compassHeadingObj.getCompassHeading();
                    trueCourseValue = compassHeadingObj.getTrueCourseNumber();
                    windCorrectionAngleValue = compassHeadingObj.getWindCorrectionAngleNumber();
                    magneticVariationValue = compassHeadingObj.getMagneticVariationNumber();
                    magneticDeviationValue = compassHeadingObj.getMagneticDeviationNumber();

                    //New Object
                    CompassHeadingOutput compassHeadingObject = new CompassHeadingOutput(output, trueCourseValue, windCorrectionAngleValue, magneticVariationValue, magneticDeviationValue);
                    System.out.println("Error 3");
                    
                    //prepare statement using query we prepared with INSERT for 3 values. PS can assign values to query
                    PreparedStatement ps = myDbConn.prepareStatement(dbQuery);//and import
                    
                    //enter data into query. It dosen't have to be in order since it has labels. It assings 1 to first quetsion mark, and then so on
                    ps.setDouble(1, trueCourseValue); 
                    ps.setDouble(2, windCorrectionAngleValue);
                    ps.setDouble(3, magneticVariationValue);
                    ps.setDouble(4, magneticDeviationValue);
                    ps.setDouble(5, output); 

                    //execute the query in the DB
                    ps.executeUpdate();
                    System.out.println("Data inserted successfully!"); //of we get here, it good.

                    //Deletes table,and immediately reconstructs table, so we update. When we close a connection, it only gets rid of object, but Frames still have info, so we dispose frame before we make a new connection with new info on table
                    gFrame.dispose();
                    gFrame.toBack();
                }
                catch(SQLException se)
                {
                    System.out.println("Error inserting data");
                }  
               
            } 
            //catch for to check the thing
            catch (NumberFormatException nfe)
            {
                Warning warningObj = new Warning("Error Calculating your Number"); //creates a new frame, which will show in warning frame
            } 
            //general exception catch, if everything fails
            catch (Exception ge)
            {
                Warning warningObj = new Warning("Error Calculating your Number");
            }

            //Validate and Repaint required
            this.validate();
            this.repaint();
        } 
        else if (command.equals("Back"))
        {
            //Dispose of Frame, and heading to Option Object
            this.dispose();
            Option optionObjectOne = new Option("Option Page");
        } 
        else if (myComponent == helpItem)
        {
            //Navigate to Help Frame (Pops)
            Help helpObject = new Help("Help Page");
        } 
        else if (myComponent == definitionItem)
        {
            Definitions definitionObject = new Definitions("Definition Page");
        }
        else if (myComponent == aboutItem)
        {
            About aboutObject = new About("About Page");
        }
        else if (myComponent == faqItem)
        {
            FAQ faqObject = new FAQ("FAQ Page");
        }
        else if (myComponent == activityLogItem)
        {
            ActivityLog activityObject = new ActivityLog("Activity Log");
        }
        else if (myComponent == homeItem)
        {
            //Disposes and welcome frame
            this.dispose();
            Welcome welcomeObject2 = new Welcome("Welcome");
        } 
        else if (myComponent == exitItem)
        {
            //Exits
            System.exit(0);
        }
        //Required Validations
        this.validate();
        this.repaint();
    }
}