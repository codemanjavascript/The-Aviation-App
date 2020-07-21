/*
 Tommy Wan
 3/22/2020
 This class displays the output for distance calculation
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

public class DistanceOutput extends JFrame implements ActionListener
{
    //Declare Label, Buttons, Panels
    private JLabel outputLabel;
    private JLabel groundSpeedLabel;
    private JLabel timeLabel;
    private JLabel calculationLabel;
    private JLabel brakerLabel;
    private JButton exitButton;
    private JPanel outputPanel;
    private JMenuItem activityLogItem;
    private JPanel middlePanel;

    //Image label
    private JLabel imageLabel;

    //Distance Image
    private final URL DISTANCE_OUTPUT_PATH = getClass().getResource("distanceOutput.jpg");
    private final ImageIcon FINAL_IMAGE = new ImageIcon(new ImageIcon(DISTANCE_OUTPUT_PATH).getImage().getScaledInstance(500, 150, Image.SCALE_DEFAULT));

   //Styles, Fonts, and Colorings
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 30);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final Font SUB_FONT = new Font("Arial", Font.BOLD, 20);   
    public static final Color COLOR_RED = new Color(253, 73, 69);
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
    
    //Main Constructor
    public DistanceOutput(double output, double groundSpeedValue, double timeValue)
    {
        //Frame Settings
        super("Your Distance");
        this.setBounds(300, 220, 350, 500);
        
        //General Settings
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(COLOR_RED);

        //Output label
        this.outputLabel = new JLabel();
        this.outputLabel.setForeground(COLOR_WHITE);
        
        //Groundspeed label
        this.groundSpeedLabel = new JLabel("Groundspeed: " + groundSpeedValue + " Knots");
        this.groundSpeedLabel.setForeground(COLOR_WHITE); //change this to declared color!
        this.groundSpeedLabel.setFont(SUB_FONT);
        
        //Time label
        this.timeLabel = new JLabel("Time: " + timeValue + " hours (converted)");
        this.timeLabel.setForeground(COLOR_WHITE); //change this to declared color!
        this.timeLabel.setFont(SUB_FONT);
        
        //Computation label
        this.calculationLabel = new JLabel("<html><center>" + groundSpeedValue + " knots x " + timeValue + " hours = " + "<br>" + output + " nautical miles</center></html>");
        this.calculationLabel.setForeground(COLOR_WHITE); //change this to declared color!
        this.calculationLabel.setFont(SUB_FONT);
        
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
        outputPanel.setBackground(COLOR_RED);

        //Middle Panel Code
        this.middlePanel = new JPanel(new FlowLayout());
        middlePanel.setBackground(COLOR_RED);

        //image label
        this.imageLabel = new JLabel(FINAL_IMAGE);

        //Calculation answers
        this.outputLabel = new JLabel("<html><center>Distance is </center>" + output + " Nautical Miles</html>");
        this.outputLabel.setForeground(COLOR_WHITE);
        this.outputLabel.setFont(TITLE_FONT);
        
        //Add componenets to panels
        this.middlePanel.add(imageLabel);
        this.outputPanel.add(outputLabel);
        this.middlePanel.add(groundSpeedLabel);
        this.middlePanel.add(timeLabel);
        this.middlePanel.add(brakerLabel);
        this.middlePanel.add(calculationLabel);
        
        //Exit Buttons
        this.exitButton = new JButton("Return");
        this.exitButton.addActionListener(this);
        
        //Add to Border Layout
        this.add(outputPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(exitButton, BorderLayout.SOUTH);

        //Set visible to users
        this.setVisible(true);
        this.setJMenuBar(mainBar);
    }

    //Main Method
    public static void main(String[] args)
    {
        DistanceOutput distanceOutputObject = new DistanceOutput(2,4,2); //needs 2 string since changed
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