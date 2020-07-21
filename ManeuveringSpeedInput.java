/*
 Tommy Wan
 3/22/2020
 This class accepts input in order to calculate for Maneuvering Speed
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
public class ManeuveringSpeedInput extends JFrame implements ActionListener
{
    //Declare Text Fields
    private JPanel inputPanel;
    private JPanel lowerPanel;
    private JPanel middlePanel;
    private JTextField loadFactorField;
    private JLabel loadFactorLabel;
    private JTextField stallSpeedField;
    private JLabel stallSpeedLabel;
   
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
    private final URL SPEED_PATH = getClass().getResource("speed.jpg");
    private final ImageIcon SPEED_IMAGE = new ImageIcon(new ImageIcon(SPEED_PATH).getImage().getScaledInstance(650, 370, Image.SCALE_DEFAULT));
    private JLabel imageLabel;
    
    //Styles, Fonts, and Colorings
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 30);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final Font SUB_FONT = new Font("Arial", Font.BOLD, 40); 
    public static final Color COLOR_BLACK = new Color(0, 0, 0);
    public static final Color COLOR_WHITE = new Color(255, 255, 255);

    ManeuveringSpeedDisplay gFrame;
    
    //Constructor for Input Frame
    public ManeuveringSpeedInput(ManeuveringSpeedDisplay pFrame)
    {
        super("Calculate Maneuvering Speed");
        
        //Default Operations in the frame
        this.setBounds(300, 200, 800, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(COLOR_BLACK);
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
        inputPanel.setBackground(COLOR_BLACK);

        //Lower Panel Code which contains buttons
        this.lowerPanel = new JPanel(new FlowLayout());
        lowerPanel.setBackground(COLOR_BLACK);

        //Middle Panel Code
        this.middlePanel = new JPanel(new FlowLayout());
        middlePanel.setBackground(COLOR_BLACK);

        //Text Fields 
        this.loadFactorField = new JTextField(7);
        this.stallSpeedField = new JTextField(7);
 
        //Buttons
        this.backButton = new JButton("Back");
        this.backButton.addActionListener(this);
        this.calculateButton = new JButton("Calculate");
        this.calculateButton.addActionListener(this);
        
        //Labels and Text Fields on Top
        this.loadFactorLabel = new JLabel("Load Factor (n): ");
        this.loadFactorLabel.setForeground(COLOR_WHITE);
        this.stallSpeedLabel = new JLabel("Stall Speed (knots): ");
        this.stallSpeedLabel.setForeground(COLOR_WHITE);
       
        //Image label
        this.imageLabel = new JLabel(SPEED_IMAGE);

        //Add Buttons to LowerPanel
        this.lowerPanel.add(calculateButton);
        this.lowerPanel.add(backButton);

        //Add Fields to Input with Label
        this.inputPanel.add(loadFactorLabel);
        this.inputPanel.add(loadFactorField);
        this.inputPanel.add(stallSpeedLabel);
        this.inputPanel.add(stallSpeedField);
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
        String tableName = "ManeuveringSpeed";
        String[] columnHeaders =
        {
           "LoadFactor", "StallSpeed", "ManeuveringSpeed"
        };
        
        ManeuveringSpeedDisplay mainObj = new ManeuveringSpeedDisplay(dbName, tableName, columnHeaders);        
        ManeuveringSpeedInput ManeuveringSpeedInputObj = new ManeuveringSpeedInput(mainObj);
    }

    //Override Method to have for buttons
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //list out variables
        String command = e.getActionCommand();
        
        String dbName = "AviationDatabase";
        String tableName = "ManeuveringSpeed";
        String[] tableHeaders =
        {
           "LoadFactor", "StallSpeed", "ManeuveringSpeed"
        }; 
        //note: the spaces are put in so that the table headers will be centered.
        //new DistanceDisplay(dbName, tableName, tableHeaders);
        
        gFrame.setVisible(false);
        String dbQuery = "INSERT INTO ManeuveringSpeed VALUES (?, ?, ?)";
        
        //Connect to db
        JavaDatabase objDb = new JavaDatabase(dbName);
        Connection myDbConn = objDb.getDbConn();
        
        //list out variables
        Object myComponent = e.getSource();
        String loadFactorInput;
        String stallSpeedInput;
        double loadFactorValue;
        double stallSpeedValue;
        double answer;
        double output;
        String message;
        
        //if state ments for buttons
        if (command.equals("Calculate"))
        {
            try
            {
                //set equate
                loadFactorInput = loadFactorField.getText();
                loadFactorValue = Double.parseDouble(loadFactorInput);

                stallSpeedInput = stallSpeedField.getText();
                stallSpeedValue = Double.parseDouble(stallSpeedInput);

                //empy textField
                loadFactorField.setText("");
                stallSpeedField.setText("");

                if (loadFactorValue < 0)
                {
                       Warning warningObj = new Warning("Error Calculating your Number"); //creates a new frame, which will show in warning frame
                } 
                else if (stallSpeedValue < 0)
                {
                       Warning warningObj = new Warning("Error Calculating your Number");
                } 
                else
                {
                    try
                    {
                        //Calculations from the calculation class
                        ManeuveringSpeedMultiplication maneuveringSpeedObj = new ManeuveringSpeedMultiplication(loadFactorValue, stallSpeedValue); //Recives two values,  and shows it in the passed values
                        output = maneuveringSpeedObj.getManeuveringSpeed();
                        loadFactorValue = maneuveringSpeedObj.getLoadFactorNumber();
                        stallSpeedValue = maneuveringSpeedObj.getStallSpeedNumber();

                        //New Object
                        ManeuveringSpeedOutput maneuveringSpeedObject = new ManeuveringSpeedOutput(output, loadFactorValue, stallSpeedValue);
                    
                        //prepare statement using query we prepared with INSERT for 3 values. PS can assign values to query
                        PreparedStatement ps = myDbConn.prepareStatement(dbQuery);//and import
                        //enter data into query. It dosen't have to be in order since it has labels. It assings 1 to first quetsion mark, and then so on
  
                        ps.setDouble(1, loadFactorValue); 
                        ps.setDouble(2, stallSpeedValue);
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
                    
                    //Calculations from the calculation class
                    ManeuveringSpeedMultiplication speedObj = new ManeuveringSpeedMultiplication(loadFactorValue, stallSpeedValue); //Recives two values,  and shows it in the passed values
                    output = speedObj.getManeuveringSpeed();

                    //New Object
                    ManeuveringSpeedOutput outputObject = new ManeuveringSpeedOutput(output, loadFactorValue, stallSpeedValue);
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
            //goes to options
            this.dispose();
            Option optionObjectOne = new Option("Option Page");
        }
        else if (myComponent == helpItem)
        {
            //goes to help 
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
            //Goes to welcome frame
            this.dispose();
            Welcome welcomeObject2 = new Welcome("Welcome");
        } 
        else if (myComponent == exitItem)
        {
            //exists
            System.exit(0);
        }
        //validation
        this.validate();
        this.repaint();
    }
}