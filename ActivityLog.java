/*
 Tommy Wan
 5/22/2020
 This class allows for the activity log option page, with a description
 */
package wansteam;

//Import required packages to be used
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//Welcome Class Creation with all Neccesary implentations
public class ActivityLog extends JFrame implements ActionListener
{
    //Declaring JLabels, Buttons, Panels, Etc.
    private JLabel optionLabel;
    private JPanel optionPanel;
    private JLabel descriptionLabel;
    private JPanel lowerPanel;
    private JPanel middlePanel;

    //Buttons and Choices
    private JButton distanceButton;
    private JButton groundSpeedButton;
    private JButton timeButton;
    private JButton milesToKnotsButton;
    private JButton compassHeadingButton;
    private JButton manuveringSpeedButton;

    //Menus
    private JMenuBar mainBar; //Menu Bar
    private JMenu navigationMenu; //Menu
    private JMenuItem helpItem;
    private JMenuItem definitionItem;
    private JMenuItem aboutItem;
    private JMenuItem faqItem;
    private JMenuItem homeItem;
    private JMenuItem exitItem;

    //Styles, Fonts, and Colorings
    public static final Font TITLE_FONT = new Font("Phosphate", Font.BOLD, 70);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final Font OPTION_TEXT_FONT = new Font("Arial", Font.PLAIN, 25);   
    public static final Color COLOR_BLUE = new Color(95, 158, 255);
    public static final Color COLOR_WHITE = new Color(255, 255, 255);
    
    public ActivityLog(String frameName)
    {
        //Basic Frame Information
        super("Activity Log");
        this.setBounds(300, 220, 800, 220);
        this.getContentPane().setBackground(COLOR_BLUE);

        //Operations of the Frame
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //Welcome Label2
        this.optionLabel = new JLabel("<html><center>Activity Log</center></html>");
        this.optionLabel.setForeground(COLOR_WHITE);
        this.optionLabel.setFont(TITLE_FONT);

        //Description of Momentum Label
        this.descriptionLabel = new JLabel("<html><center>Check your past calculations by clicking here:</center>", SwingConstants.CENTER);
        this.descriptionLabel.setForeground(COLOR_WHITE);
        this.descriptionLabel.setFont(OPTION_TEXT_FONT);

        //Distance Button
        this.distanceButton = new JButton("Distance");
        this.distanceButton.addActionListener(this);

        //Ground Speed Button
        this.groundSpeedButton = new JButton("Ground Speed");
        this.groundSpeedButton.addActionListener(this);

        //Time Button
        this.timeButton = new JButton("Time");
        this.timeButton.addActionListener(this);

        //Miles to Knots
        this.milesToKnotsButton = new JButton("Miles to Knots");
        this.milesToKnotsButton.addActionListener(this);

        //Compass Heading Button
        this.compassHeadingButton = new JButton("Compass Heading");
        this.compassHeadingButton.addActionListener(this);

        //Manuvering Speed
        this.manuveringSpeedButton = new JButton("Maneuvering Speed");
        this.manuveringSpeedButton.addActionListener(this);

        //Menu Items
        this.helpItem = new JMenuItem("Help");
        this.helpItem.addActionListener(this);
        this.definitionItem = new JMenuItem("Definitions");
        this.definitionItem.addActionListener(this);
        this.aboutItem = new JMenuItem("About Me!");
        this.aboutItem.addActionListener(this);
        this.faqItem = new JMenuItem("Frequently Asked Questions");
        this.faqItem.addActionListener(this);
        this.homeItem = new JMenuItem("Home");
        this.homeItem.addActionListener(this);
        this.exitItem = new JMenuItem("Exit");
        this.exitItem.addActionListener(this);

        //Constructing the Menus
        this.mainBar = new JMenuBar();
        this.navigationMenu = new JMenu("Navigation");
        mainBar.add(navigationMenu);
        navigationMenu.add(helpItem);
        navigationMenu.add(definitionItem);
        navigationMenu.add(aboutItem);
        navigationMenu.add(faqItem);
        navigationMenu.add(homeItem);
        navigationMenu.add(exitItem);

        //Upper Panel
        this.optionPanel = new JPanel(new FlowLayout());
        this.optionPanel.setBackground(COLOR_BLUE);
        this.optionPanel.setPreferredSize(new Dimension(50,84));

        //Adding to Upper Panel
        this.optionPanel.add(optionLabel);
        
        //Middle Panel
        this.middlePanel = new JPanel(new FlowLayout());
        this.middlePanel.setBackground(COLOR_BLUE);
        this.middlePanel.add(descriptionLabel);

        //Lower Panel
        this.lowerPanel = new JPanel(new FlowLayout());
        this.lowerPanel.setBackground(COLOR_BLUE);
        this.lowerPanel.add(distanceButton);
        this.lowerPanel.add(groundSpeedButton);
        this.lowerPanel.add(timeButton);
        this.lowerPanel.add(milesToKnotsButton);
        this.lowerPanel.add(compassHeadingButton);
        this.lowerPanel.add(manuveringSpeedButton);
        
        //Adds Labels and Panels
        this.add(optionPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        //Required Actions
        this.setJMenuBar(mainBar);
        this.setVisible(true);
    }

    //Main method
    public static void main(String[] args)
    {
        //Create object
        ActivityLog activityLogObject = new ActivityLog("Activity Log");
    }

    @Override //Override Method
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        Object myComponent = e.getSource();

        if (command.equals("Start"))
        {
            Option optionObjectOne = new Option("Option Page");
            this.dispose();
        } 
        else if (command.equals("Exit"))
        {
       
            //Exits the program
            System.exit(0);
        } 
        else if (command.equals("Distance"))
        {
            this.dispose();
            String dbName = "AviationDatabase";
            String tableName = "Distance";
            String[] tableHeaders =
            {
                "Groundspeed", "Time", "Distance"
            }; 
            //note: the spaces are put in so that the table headers will be centered.
            new DistanceDisplay(dbName, tableName, tableHeaders);
        }
        else if (command.equals("Ground Speed"))      
        {
            this.dispose();
            String dbName = "AviationDatabase";
            String tableName = "groundSpeed";
            String[] tableHeaders =
            {
                "Distance", "Time", "Groundspeed"
            }; 
            //note: the spaces are put in so that the table headers will be centered.
            new GroundSpeedDisplay(dbName, tableName, tableHeaders);
        }
        else if (command.equals("Time"))
        {
            this.dispose();
            String dbName = "AviationDatabase";
            String tableName = "Time";
            String[] tableHeaders =
            {
                "Distance", "Groundspeed", "Time"
            }; 
            //note: the spaces are put in so that the table headers will be centered.
            new TimeDisplay(dbName, tableName, tableHeaders);
        }
        else if (command.equals("Miles to Knots"))
        {
            this.dispose();
            String dbName = "AviationDatabase";
            String tableName = "MilesToKnots";
            String[] tableHeaders =
            {
                "Miles", "Knots"
            }; 
            //note: the spaces are put in so that the table headers will be centered.
            new MilesToKnotsDisplay(dbName, tableName, tableHeaders);
        }
        else if (command.equals("Compass Heading"))
        {
            this.dispose();
            String dbName = "AviationDatabase";
            String tableName = "CompassHeading";
            String[] tableHeaders =
            {
                "TrueCourse", "WindCorrectionAngle", "MagneticVariation", "MagneticDeviation", "CompassHeading"
            }; 
            //note: the spaces are put in so that the table headers will be centered.
            new CompassHeadingDisplay(dbName, tableName, tableHeaders);
        }
        else if (command.equals("Maneuvering Speed"))
        {
            this.dispose();
            String dbName = "AviationDatabase";
            String tableName = "ManeuveringSpeed";
            String[] tableHeaders =
            {
                "LoadFactor", "StallSpeed", "ManeuveringSpeed"
            }; 
            //note: the spaces are put in so that the table headers will be centered.
            new ManeuveringSpeedDisplay(dbName, tableName, tableHeaders);
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