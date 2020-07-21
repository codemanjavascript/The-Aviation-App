/*
 Tommy Wan
 3/22/2020
 This class displays time answer through an output frame
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

public class TimeOutput extends JFrame implements ActionListener
{
    //Declare Label, Buttons, Panels
    private JLabel outputLabel;
    private JLabel distanceLabel;
    private JLabel groundSpeedLabel;
    private JLabel calculationLabel;
    private JLabel brakerLabel;
    private JButton exitButton;
    private JPanel outputPanel;
    private JMenuItem activityLogItem;
    private JPanel middlePanel;

    //Image label
    private JLabel imageLabel;

    //Final Image
    private final URL TIME_OUTPUT_PATH = getClass().getResource("TimeOutput.jpg");
    private final ImageIcon TIME_IMAGE = new ImageIcon(new ImageIcon(TIME_OUTPUT_PATH).getImage().getScaledInstance(500, 200, Image.SCALE_DEFAULT));

    //Styles, Fonts, and Colorings
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 30);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final Font SUB_FONT = new Font("Arial", Font.BOLD, 20);
    public static final Color COLOR_ORANGE = new Color(255, 179, 102);
    public static final Color COLOR_WHITE = new Color(255, 255, 255);
    
    //Menus
    private JMenuBar mainBar; //Menu Bar
    private JMenu navigationMenu; //Menu
    private JMenuItem helpItem;
    private JMenuItem definitionItem;
    private JMenuItem aboutItem;
    private JMenuItem faqItem;
    private JMenuItem homeItem;
    private JMenuItem exitItem;
    
    //Main Class
    public TimeOutput(double output, double distanceValue, double groundSpeedValue)
    {
        //Frame settings
        super("Your Time");
        this.setBounds(300, 220, 350, 500);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(COLOR_ORANGE);

        //Output label
        this.outputLabel = new JLabel();
        this.outputLabel.setForeground(COLOR_WHITE);
        
        //distance label
        this.distanceLabel = new JLabel("Distance: " + distanceValue + " nautical miles");
        this.distanceLabel.setForeground(COLOR_WHITE); //change this to declared color!
        this.distanceLabel.setFont(SUB_FONT);
        
        //groundSpeed label
        this.groundSpeedLabel = new JLabel("Ground Speed: " + groundSpeedValue + " knots");
        this.groundSpeedLabel.setForeground(COLOR_WHITE); //change this to declared color!
        this.groundSpeedLabel.setFont(SUB_FONT);
        
        //computation label 
        this.calculationLabel = new JLabel("<html><center>" + distanceValue + " nautical miles ÷ " + groundSpeedValue + " knots <br> = " + output + " hours</center></html>", JLabel.CENTER);
        this.calculationLabel.setForeground(COLOR_WHITE); //change this to declared color!
        this.calculationLabel.setFont(SUB_FONT);
        
        //Braker
        this.brakerLabel = new JLabel("<html>-----------------<br></html>");
        this.brakerLabel.setForeground(COLOR_WHITE);
        this.brakerLabel.setFont(SUB_FONT);
      
        //Output Panel Code
        this.outputPanel = new JPanel(new FlowLayout());
        outputPanel.setBackground(COLOR_ORANGE);

        //Middle Panel Code
        this.middlePanel = new JPanel(new FlowLayout());
        middlePanel.setBackground(COLOR_ORANGE);

        //image label
        this.imageLabel = new JLabel(TIME_IMAGE);

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
        this.outputLabel = new JLabel("<html><center>Time is <br>" + output + " hours</center></html>");
        this.outputLabel.setForeground(COLOR_WHITE);
        this.outputLabel.setFont(TITLE_FONT);
        
        //Add Labels
        this.middlePanel.add(imageLabel);
        this.outputPanel.add(outputLabel);
        this.middlePanel.add(distanceLabel);
        this.middlePanel.add(groundSpeedLabel);
        this.middlePanel.add(brakerLabel);
        this.middlePanel.add(calculationLabel);
        
        //Exit Button 
        this.exitButton = new JButton("Return");
        this.exitButton.addActionListener(this);

        //Add to Layout
        this.add(outputPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(exitButton, BorderLayout.SOUTH);
        
        //Visiblity
        this.setVisible(true);
        this.setJMenuBar(mainBar);
    }

    //Main method
    public static void main(String[] args)
    {
        //pass in something that is an integer
        TimeOutput timeOutputObject = new TimeOutput(2,4,2); //needs 2 string since changed
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