/*
 Tommy Wan
 3/22/2020
 This class displays the Maneuvering Speed Output
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
import wansteam.Welcome;

//Main Class
public class ManeuveringSpeedOutput extends JFrame implements ActionListener
{
    //Declare Label, Buttons, Panels
    private JLabel outputLabel;
    private JLabel loadFactorLabel;
    private JLabel stallSpeedLabel;
    private JLabel calculationLabel;
    private JLabel brakerLabel;
    private JButton exitButton;
    private JPanel outputPanel;
    private JPanel middlePanel;

    //Image label
    private JLabel imageLabel;

    //Final Image
    private final URL SPEED_OUTPUT_PATH = getClass().getResource("speed.jpg");
    private final ImageIcon SPEED_IMAGE = new ImageIcon(new ImageIcon(SPEED_OUTPUT_PATH).getImage().getScaledInstance(500, 200, Image.SCALE_DEFAULT));

    //Styles, Fonts, and Colorings
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 30);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final Font SUB_FONT = new Font("Arial", Font.BOLD, 20);
    public static final Color COLOR_BLACK = new Color(0, 0, 0);
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
    
    //Main constructor
    public ManeuveringSpeedOutput(double output, double loadFactorValue, double stallSpeedValue)
    {
        //Frame settings
        super("Your Maneuvering Speed");
        this.setBounds(300, 220, 350, 500);

        //General Settings
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(COLOR_BLACK);

        //Output label
        this.outputLabel = new JLabel();
        this.outputLabel.setForeground(Color.WHITE);
        
        //Load factor label
        this.loadFactorLabel = new JLabel("Load Factor: " + loadFactorValue + " (for n)");
        this.loadFactorLabel.setForeground(Color.WHITE); //change this to declared color!
        this.loadFactorLabel.setFont(SUB_FONT);
        
        //GroundSpeed label
        this.stallSpeedLabel = new JLabel("Stall Speed: " + stallSpeedValue + " knots");
        this.stallSpeedLabel.setForeground(Color.WHITE); //change this to declared color!
        this.stallSpeedLabel.setFont(SUB_FONT);
        
        //Computation label 
        this.calculationLabel = new JLabel("<html><center>" + stallSpeedValue + "knots x √" + loadFactorValue + " <br> = " + output + " hours</center></html>", JLabel.CENTER);
        this.calculationLabel.setForeground(Color.WHITE); //change this to declared color!
        this.calculationLabel.setFont(SUB_FONT);
        
        //Braker Label
        this.brakerLabel = new JLabel("<html>----------------------------------------<br></html>");
        this.brakerLabel.setForeground(Color.WHITE);
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
        outputPanel.setBackground(COLOR_BLACK);

        //Middle Panel Code
        this.middlePanel = new JPanel(new FlowLayout());
        middlePanel.setBackground(COLOR_BLACK);

        //Image label
        this.imageLabel = new JLabel(SPEED_IMAGE);

        //Calculation answers
        this.outputLabel = new JLabel("<html><center>Maneuvering Speed is <br>" + output + " knots</center></html>");
        this.outputLabel.setForeground(Color.WHITE);
        this.outputLabel.setFont(TITLE_FONT);
        
        //Add Labels to Panels
        this.middlePanel.add(imageLabel);
        this.outputPanel.add(outputLabel);
        this.middlePanel.add(loadFactorLabel);
        this.middlePanel.add(stallSpeedLabel);
        this.middlePanel.add(brakerLabel);
        this.middlePanel.add(calculationLabel);
        
        //Create exit button
        this.exitButton = new JButton("Return");
        this.exitButton.addActionListener(this);

        //Add to Layout
        this.add(outputPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(exitButton, BorderLayout.SOUTH);

        //Show User
        this.setVisible(true);
        this.setJMenuBar(mainBar);
    }

    //Main method
    public static void main(String[] args)
    {
        //pass in something that is an integer
        ManeuveringSpeedOutput ManeuveringSpeedOutputObject = new ManeuveringSpeedOutput(2,4,2); //needs 2 string since changed
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