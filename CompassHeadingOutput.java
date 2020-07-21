/*
 Tommy Wan
 3/22/2020
 This class is the output page for compass heading calculations
 */
package wansteam;

//import required packages
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

//Class with nessesary requirements
public class CompassHeadingOutput extends JFrame implements ActionListener
{
    //Declare Label, Buttons, Panels
    private JLabel outputLabel;
    private JLabel trueCourseLabel;
    private JLabel windCorrectionLabel;
    private JLabel magneticVariationLabel;
    private JLabel magneticDeviationLabel;
    private JLabel compassHeading;
    private JLabel calculationLabel;
    private JLabel brakerLabel;
    private JButton exitButton;
    private JPanel outputPanel;
    private JPanel middlePanel;

    //Image label
    private JLabel imageLabel;

    //Compass Image
    private final URL COMPASS_OUTPUT_PATH = getClass().getResource("compass_1.jpg");
    private final ImageIcon COMPASS_IMAGE = new ImageIcon(new ImageIcon(COMPASS_OUTPUT_PATH).getImage().getScaledInstance(500, 190, Image.SCALE_DEFAULT));

    //Styles, Fonts, and Colorings
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 30);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final Font SUB_FONT = new Font("Arial", Font.BOLD, 20);
    public static final Color COLOR_TEAL = new Color(128, 191, 255);
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
    
    //Constructor Main
    public CompassHeadingOutput(double output, double trueCourseValue, double windCorrectionAngleValue, double magneticVariationValue, double magneticDeviationValue)
    {
        //Frame settings
        super("Your Compass Heading");
        this.setBounds(300, 220, 350, 530);
        
        //General Frame Settings
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(COLOR_TEAL);

        //Output label
        this.outputLabel = new JLabel();
        this.outputLabel.setForeground(COLOR_WHITE);
        
        //True Couse label
        this.trueCourseLabel = new JLabel("True Course: " + trueCourseValue + "°");
        this.trueCourseLabel.setForeground(COLOR_WHITE); //change this to declared color!
        this.trueCourseLabel.setFont(SUB_FONT);
        
        //Wind Correction Angle label
        this.windCorrectionLabel = new JLabel("Wind Correction: " + windCorrectionAngleValue + "°");
        this.windCorrectionLabel.setForeground(COLOR_WHITE); //change this to declared color!
        this.windCorrectionLabel.setFont(SUB_FONT);
        
        //Magnetic Variation
        this.magneticVariationLabel = new JLabel("Magnetic Variation: " + magneticVariationValue + "°");
        this.magneticVariationLabel.setForeground(COLOR_WHITE); //change this to declared color!
        this.magneticVariationLabel.setFont(SUB_FONT);
        
        //Magnetic Variation
        this.magneticDeviationLabel = new JLabel("Magnetic Deviation: " + magneticDeviationValue + "°");
        this.magneticDeviationLabel.setForeground(COLOR_WHITE); //change this to declared color!
        this.magneticDeviationLabel.setFont(SUB_FONT);
        
        //computation label 
        this.calculationLabel = new JLabel("<html><center>" + trueCourseValue + "° + " + "(" + windCorrectionAngleValue + ")°" + " + " + "(" + magneticVariationValue + "°)" + " + " +  "(" + magneticDeviationValue + "°)</center></html>", JLabel.CENTER);
        this.calculationLabel.setForeground(COLOR_WHITE); //change this to declared color!
        this.calculationLabel.setFont(SUB_FONT);
        
        //Braker Label
        this.brakerLabel = new JLabel("<html>-----------------<br></html>");
        this.brakerLabel.setForeground(COLOR_WHITE);
        this.brakerLabel.setFont(SUB_FONT);
      
        //Constructing the Menus
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
        
        //Output Panel Code
        this.outputPanel = new JPanel(new FlowLayout());
        outputPanel.setBackground(COLOR_TEAL);

        //Middle Panel Code
        this.middlePanel = new JPanel(new FlowLayout());
        middlePanel.setBackground(COLOR_TEAL);

        //Image label
        this.imageLabel = new JLabel(COMPASS_IMAGE);

        //Calculation answers
        this.outputLabel = new JLabel("<html><center>Compass Heading <br>is " + output + "°</center></html>");
        this.outputLabel.setForeground(COLOR_WHITE);
        this.outputLabel.setFont(TITLE_FONT);
        
        //Adding components to panels
        this.middlePanel.add(imageLabel);
        this.outputPanel.add(outputLabel);
        this.middlePanel.add(trueCourseLabel);
        this.middlePanel.add(windCorrectionLabel);
        this.middlePanel.add(magneticVariationLabel);
        this.middlePanel.add(magneticDeviationLabel);
        this.middlePanel.add(brakerLabel);
        this.middlePanel.add(calculationLabel);
        
        //Exit Button
        this.exitButton = new JButton("Return");
        this.exitButton.addActionListener(this);

        //Layering Panels to Layout
        this.add(outputPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(exitButton, BorderLayout.SOUTH);

        //Show Visibility
        this.setVisible(true);
        this.setJMenuBar(mainBar);
    }

    //Main method
    public static void main(String[] args)
    {
        //Create an object with correct parsing
        CompassHeadingOutput compassHeadingOutputObject = new CompassHeadingOutput(2.0, 2.0, 2.0, 2.0, 2.0); 
    }

    //Override Method to have for button
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        Object myComponent = e.getSource();
        if (command.equals("Return"))
        {
            this.dispose();
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
            //Goes to Welcome
            this.dispose();
            Welcome welcomeObject2 = new Welcome("Welcome");
        }
        else if (myComponent == exitItem)
        {
            System.exit(0);
        }
    }
}