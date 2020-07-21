/*
 Tommy Wan
 3/22/2020
 This class allows for and recieves ground speed input in order to calculate for GS
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

public class GroundSpeedInput extends JFrame implements ActionListener
{
    //Declare Text Fields
    private JPanel inputPanel;
    private JPanel lowerPanel;
    private JPanel middlePanel;
    private JTextField distanceField;
    private JLabel distanceLabel;
    private JTextField timeField;
    private JLabel timeLabel;

    //Buttons
    private JButton calculateButton;
    private JButton backButton;

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
    private final URL GROUND_SPEED_PATH = getClass().getResource("groundSpeed.jpeg");
    private final ImageIcon GROUND_SPEED_IMAGE = new ImageIcon(new ImageIcon(GROUND_SPEED_PATH).getImage().getScaledInstance(650, 370, Image.SCALE_DEFAULT));
    private JLabel imageLabel;

    //Styles, Fonts, and Colorings
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 30);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final Color COLOR_TEAL = new Color(128, 191, 255);
    public static final Color COLOR_WHITE = new Color(255, 255, 255);

    GroundSpeedDisplay gFrame; //Global Frame
    
    //Constructor for Input Frame
    public GroundSpeedInput(GroundSpeedDisplay pFrame)
    {
        super("Calculate Ground Speed");

        //Default Operations in the frame
        this.setBounds(300, 200, 800, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(COLOR_TEAL);
        this.setLayout(new BorderLayout());

        //To fix the scope, we pass g grame to p frame
        this.gFrame = pFrame; //Makes parameter pFrame accessible to other methods. Therefore, we can dispose insert frame IN ButtonsWithDisplay Class, since its passed in both.

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
        inputPanel.setBackground(COLOR_TEAL);

        //Lower Panel Code which contains buttons
        this.lowerPanel = new JPanel(new FlowLayout());
        lowerPanel.setBackground(COLOR_TEAL);

        //Middle Panel Code
        this.middlePanel = new JPanel(new FlowLayout());
        middlePanel.setBackground(COLOR_TEAL);

        //Text Fields 
        this.distanceField = new JTextField(7);
        this.timeField = new JTextField(7);

        //Buttons
        this.backButton = new JButton("Back");
        this.backButton.addActionListener(this);
        this.calculateButton = new JButton("Calculate");
        this.calculateButton.addActionListener(this);

        //Labels and Text Fields on Top
        this.distanceLabel = new JLabel("Distance (Nautical Miles): ");
        this.distanceLabel.setForeground(COLOR_WHITE);
        this.timeLabel = new JLabel("Time (Minutes): ");
        this.timeLabel.setForeground(COLOR_WHITE);

        //Image label
        this.imageLabel = new JLabel(GROUND_SPEED_IMAGE);

        //Add Buttons to LowerPanel
        this.lowerPanel.add(calculateButton);
        this.lowerPanel.add(backButton);

        //Add Fields to Input with Label
        this.inputPanel.add(distanceLabel);
        this.inputPanel.add(distanceField);
        this.inputPanel.add(timeLabel);
        this.inputPanel.add(timeField);
        this.middlePanel.add(imageLabel);

        //Adds Panels inside the frame
        this.add(inputPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        //set visible to user
        this.setJMenuBar(mainBar);
        this.setVisible(true);
    }

    //Main method
    public static void main(String[] args)
    {
        String dbName = "AviationDatabase";
        String tableName = "groundSpeed";
        String[] columnHeaders =
        {
           "Distance", "Time", "Groundspeed"
        };
        
        GroundSpeedDisplay mainObj = new GroundSpeedDisplay(dbName, tableName, columnHeaders);        
        GroundSpeedInput groundSpeedInputObj = new GroundSpeedInput(mainObj);
    }

    //Override Method to have for buttons
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //list out variables
        String command = e.getActionCommand();
        
        String dbName = "AviationDatabase";
        String tableName = "groundSpeed";
        String[] tableHeaders =
        {
           "Distance", "Time", "Groundspeed"
        }; 
        //note: the spaces are put in so that the table headers will be centered.
        //new DistanceDisplay(dbName, tableName, tableHeaders);
        
        gFrame.setVisible(false); //Fix naming
        String dbQuery = "INSERT INTO groundSpeed VALUES (?, ?, ?)";
        
        //Connect to db
        JavaDatabase objDb = new JavaDatabase(dbName);
        Connection myDbConn = objDb.getDbConn();
        
        //list out variables
        Object myComponent = e.getSource();
        String distanceInput;
        String timeInput;
        double distanceValue;
        double timeValue;
        double output;
        //int id;
        String message;

        //if state ments for buttons
        if (command.equals("Calculate"))
        {
            try
            {
                //set equate
                distanceInput = distanceField.getText();
                timeInput = timeField.getText();
                distanceValue = Double.parseDouble(distanceInput);
                timeValue = Double.parseDouble(timeField.getText());

                //empy textField
                distanceField.setText("");
                timeField.setText("");

                if (distanceValue < 0)
                {
                    Warning warningObj = new Warning("Error Calculating your Number"); //creates a new frame, which will show in warning frame
                } 
                else if (timeValue < 0)
                {
                    Warning warningObj = new Warning("Error Calculating your Number");
                } 
                else
                {
                    try
                    {
                        //Calculations from the calculation class
                        GroundSpeedDivision groundSpeedObj = new GroundSpeedDivision(distanceValue, timeValue); //Recives two values,  and shows it in the passed values
                        output = groundSpeedObj.getGroundSpeed();
                        distanceValue = groundSpeedObj.getDistanceNumber();
                        timeValue = groundSpeedObj.getTimeNumber();

                        //New Object
                        GroundSpeedOutput groundSpeedObject = new GroundSpeedOutput(output, distanceValue, timeValue);
                    
                        //prepare statement using query we prepared with INSERT for 3 values. PS can assign values to query
                        PreparedStatement ps = myDbConn.prepareStatement(dbQuery);//and import
                        //enter data into query. It dosen't have to be in order since it has labels. It assings 1 to first quetsion mark, and then so on
  
                        ps.setDouble(1, distanceValue); 
                        ps.setDouble(2, timeValue);
                        ps.setDouble(3, output); 

                        //execute the query in the DB
                        ps.executeUpdate();
                        System.out.println("Data inserted successfully!"); //of we get here, it good.

                        //Deletes table,and immediately reconstructs table, so we update. When we close a connection, it only gets rid of object, but Frames still have info, so we dispose frame before we make a new connection with new info on table
                        gFrame.dispose();
                        //gFrame = new DistanceDisplay(dbName, tableName, tableHeaders);
                        
                        gFrame.toBack();
                    }
                    catch(SQLException se)
                    {
                        System.out.println("Error inserting data");
                    }     
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
            this.dispose();
            Option optionObjectOne = new Option("Option Page");
        } 
        else if (myComponent == helpItem)
        {
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
            this.dispose();
            Welcome welcomeObject2 = new Welcome("Welcome");
        } 
        else if (myComponent == exitItem)
        {
            System.exit(0);
        }
        this.validate();
        this.repaint();
    }
}