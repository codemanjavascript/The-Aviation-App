/*
 Tommy Wan
 3/22/2020
 This class displays the output for ground speed calculations
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import wansteam.Welcome;

//Main Class with requried
public class GroundSpeedOutput extends JFrame implements ActionListener
{
    //Declare Label, Buttons, Panels
    private JLabel outputLabel;
    private JLabel distanceLabel;
    private JLabel timeLabel;
    private JLabel calculationLabel;
    private JLabel brakerLabel;
    private JButton exitButton;
    private JPanel outputPanel;
    private JPanel middlePanel;

    //Image label
    private JLabel imageLabel;

    //Final Image
    private final URL GROUNDSPEED_OUTPUT_PATH = getClass().getResource("groundSpeedOutput.jpg");
    private final ImageIcon GROUNDSPEED_IMAGE = new ImageIcon(new ImageIcon(GROUNDSPEED_OUTPUT_PATH).getImage().getScaledInstance(500, 200, Image.SCALE_DEFAULT));

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

    //Constructor
    public GroundSpeedOutput(double output, double distanceValue, double timeValue)
    {
        //Frame settings
        super("Your Ground Speed");
        this.setBounds(300, 220, 350, 500);

        //General Settings
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(COLOR_TEAL);

        //Output label
        this.outputLabel = new JLabel();
        this.outputLabel.setForeground(COLOR_WHITE);
        
        //Groundspeed label
        this.distanceLabel = new JLabel("Distance: " + distanceValue + " nautical miles");
        this.distanceLabel.setForeground(COLOR_WHITE); 
        this.distanceLabel.setFont(SUB_FONT);
        
        //Time label
        this.timeLabel = new JLabel("Time: " + timeValue + " hours (converted)");
        this.timeLabel.setForeground(COLOR_WHITE); 
        this.timeLabel.setFont(SUB_FONT);
        
        //Computation label
        this.calculationLabel = new JLabel("<html><center>" + distanceValue + " nautical miles ÷ " + timeValue + " hours <br> = " + output + " knots</center></html>", JLabel.CENTER);
        this.calculationLabel.setForeground(COLOR_WHITE); 
        this.calculationLabel.setFont(SUB_FONT);
        
        //Breaker Lable
        this.brakerLabel = new JLabel("<html>--------------------------------<br></html>");
        this.brakerLabel.setForeground(COLOR_WHITE);
        this.brakerLabel.setFont(SUB_FONT);
      
        //Output Panel Code
        this.outputPanel = new JPanel(new FlowLayout());
        outputPanel.setBackground(COLOR_TEAL);

        //Middle Panel Code
        this.middlePanel = new JPanel(new FlowLayout());
        middlePanel.setBackground(COLOR_TEAL);

        //Image label
        this.imageLabel = new JLabel(GROUNDSPEED_IMAGE);

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
        
        //Calculation answers
        this.outputLabel = new JLabel("<html><center>Ground speed is <br>" + output + " Knots</center></html>");
        this.outputLabel.setForeground(COLOR_WHITE);
        this.outputLabel.setFont(TITLE_FONT);
        
        //Add these to the panels
        this.middlePanel.add(imageLabel);
        this.outputPanel.add(outputLabel);
        this.middlePanel.add(distanceLabel);
        this.middlePanel.add(timeLabel);
        this.middlePanel.add(brakerLabel);
        this.middlePanel.add(calculationLabel);
        
        //Exit Button
        this.exitButton = new JButton("Return");
        this.exitButton.addActionListener(this);

        //Add components to layout
        this.add(outputPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(exitButton, BorderLayout.SOUTH);

        //set visibility
        this.setVisible(true);
        this.setJMenuBar(mainBar);
    }

    //Main Method
    public static void main(String[] args)
    {
        //pass in something that is an integer
        GroundSpeedOutput groundSpeedOutputObject = new GroundSpeedOutput(2,4,2); //needs 2 string since changed
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