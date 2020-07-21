/*
 Tommy Wan
 3/22/2020
 This class serves as the input page for Miles to Knots 
 */
package wansteam;

//Import required packages
import java.awt.BorderLayout;
import java.awt.Color;
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

//Main Class
public class MilesToKnotsInput extends JFrame implements ActionListener
{
    //Declare Text Fields
    private JPanel inputPanel;
    private JPanel lowerPanel;
    private JPanel middlePanel;
    private JTextField mileField;
    private JLabel mileLabel;

    //Buttons
    private JButton calculateButton;
    private JButton backButton;

    //Styles, Fonts, and Colorings
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 30);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final Font RESULT_FONT = new Font("Arial", Font.BOLD, 25);;
    public static final Color COLOR_PURPLE = new Color(141, 155, 253);
    public static final Color COLOR_WHITE = new Color(255, 255, 255);

    //Menus
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
    private final URL MILES_TO_KNOTS_PATH = getClass().getResource("milesToKnots.jpg");
    private final ImageIcon MILES_TO_KNOTS_IMAGE = new ImageIcon(new ImageIcon(MILES_TO_KNOTS_PATH).getImage().getScaledInstance(650, 370, Image.SCALE_DEFAULT));
    private JLabel imageLabel;

    MilesToKnotsDisplay gFrame; //Global Frame
    
    //Constructor for Input Frame
    public MilesToKnotsInput(MilesToKnotsDisplay pFrame)
    {
        super("Calculate Miles to Knots");

        //Default Operations in the frame
        this.setBounds(300, 200, 800, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(COLOR_PURPLE);
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
        inputPanel.setBackground(COLOR_PURPLE);

        //Lower Panel Code which contains buttons
        this.lowerPanel = new JPanel(new FlowLayout());
        lowerPanel.setBackground(COLOR_PURPLE);

        //Middle Panel Code
        this.middlePanel = new JPanel(new FlowLayout());
        middlePanel.setBackground(COLOR_PURPLE);

        //Text Fields 
        this.mileField = new JTextField(7);

        //Buttons
        this.backButton = new JButton("Back");
        this.backButton.addActionListener(this);
        this.calculateButton = new JButton("Calculate");
        this.calculateButton.addActionListener(this);

        //Labels and Text Fields on Top
        this.mileLabel = new JLabel("Statue Miles: ");
        this.mileLabel.setForeground(COLOR_WHITE);

        //Image label
        this.imageLabel = new JLabel(MILES_TO_KNOTS_IMAGE);

        //Add Buttons to LowerPanel
        this.lowerPanel.add(calculateButton);
        this.lowerPanel.add(backButton);

        //Add Fields to Input with Label
        this.inputPanel.add(mileLabel);
        this.inputPanel.add(mileField);
        this.middlePanel.add(imageLabel);

        //Adds Panels inside the frame
        this.add(inputPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        //set visible to user
        this.setJMenuBar(mainBar);
        this.setVisible(true);
    }

    //Main Method
    public static void main(String[] args)
    {
        String dbName = "AviationDatabase";
        String tableName = "MilesToKnots";
        String[] columnHeaders =
        {
           "Miles", "Knots"
        };
        
        MilesToKnotsDisplay mainObj = new MilesToKnotsDisplay(dbName, tableName, columnHeaders);        
        MilesToKnotsInput MilesToKnotsInputObj = new MilesToKnotsInput(mainObj);
    }

    //Override Method to have for buttons
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //list out variables
        String command = e.getActionCommand();
        
        String dbName = "AviationDatabase";
        String tableName = "MilesToKnots";
        String[] tableHeaders =
        {
           "Miles", "Knots"
        }; 
        //note: the spaces are put in so that the table headers will be centered.
        
        gFrame.setVisible(false);
        String dbQuery = "INSERT INTO MilesToKnots VALUES (?, ?)";
        
        //Connect to db
        JavaDatabase objDb = new JavaDatabase(dbName);
        Connection myDbConn = objDb.getDbConn();
        
        Object myComponent = e.getSource();
        String mileInput;
        double milesValue;
        double output;
        String message;

        //if state ments for buttons
        if (command.equals("Calculate"))
        {
            try
            {
                //set equate
                mileInput = mileField.getText();
                milesValue = Double.parseDouble(mileInput);

                //empy textField
                mileField.setText("");

                if (milesValue < 0)
                {
                    Warning warningObj = new Warning("Error Calculating your Number"); //creates a new frame, which will show in warning frame
                } 
                else
                {
                    try
                    {
                        //Calculations from the calculation class
                        MilesToKnotsMultiplication MilesToKnotsObj = new MilesToKnotsMultiplication(milesValue); //Recives two values,  and shows it in the passed values
                        output = MilesToKnotsObj.getKnots();
                        milesValue = MilesToKnotsObj.getMilesNumber();
                       
                        //New Object
                        MilesToKnotsOutput milesToKnotsObject = new MilesToKnotsOutput(output, milesValue);
                   
                        //prepare statement using query we prepared with INSERT for 3 values. PS can assign values to query
                        PreparedStatement ps = myDbConn.prepareStatement(dbQuery);//and import
                        //enter data into query. It dosen't have to be in order since it has labels. It assings 1 to first quetsion mark, and then so on
                        
                        ps.setDouble(1, milesValue); 
                        ps.setDouble(2, output); 
                        
                        //execute the query in the DB
                        ps.executeUpdate();
                        System.out.println("Error 6");
                        System.out.println("Data inserted successfully!"); //of we get here, it good.

                        //Deletes table,and immediately reconstructs table, so we update. When we close a connection, it only gets rid of object, but Frames still have info, so we dispose frame before we make a new connection with new info on table
                        gFrame.dispose();
                        //gFrame = new DistanceDisplay(dbName, tableName, tableHeaders);
                        
                        gFrame.toBack();
                    }
                    catch(SQLException se)
                    {
                        System.out.println("Error inserting data!");
                    }     
                    
                    //Calculations from the calculation class
                    MilesToKnotsMultiplication knotsObj = new MilesToKnotsMultiplication(milesValue); //Recives two values,  and shows it in the passed values
                    output = knotsObj.getKnots();
                    
                    //New Object
                    MilesToKnotsOutput outputObject = new MilesToKnotsOutput(output, milesValue);
                }
            } 
            //Catch for to check the thing
            catch (NumberFormatException nfe)
            {
                Warning warningObj = new Warning("Error Calculating your Number"); //creates a new frame, which will show in warning frame
            } 
            //General exception catch, if everything fails
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
            //Goes to Option
            this.dispose();
            Option optionObjectOne = new Option("Option Page");
        } 
        else if (myComponent == helpItem)
        {
            //Goes to Help
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
            //Goes to Welcome
            this.dispose();
            Welcome welcomeObject2 = new Welcome("Welcome");
        } 
        else if (myComponent == exitItem)
        {
            //Exits
            System.exit(0);
        }
        //Validaiton
        this.validate();
        this.repaint();
    }
}