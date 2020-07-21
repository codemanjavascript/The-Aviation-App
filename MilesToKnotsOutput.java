/*
 Tommy Wan
 3/22/2020
 This class displays miles answer through an Output frame
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

//Main Class
public class MilesToKnotsOutput extends JFrame implements ActionListener
{
    //Declare Label, Buttons, Panels
    private JLabel outputLabel;
    private JLabel milesLabel;
    private JLabel calculationLabel;
    private JLabel brakerLabel;
    private JButton exitButton;
    private JPanel outputPanel;
    private JPanel middlePanel;

    //Image label
    private JLabel imageLabel;

    //Styles, Fonts, and Colorings
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 30);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final Font SUB_FONT = new Font("Arial", Font.BOLD, 20);    
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
    
    //Final Image
    private final URL MILES_PATH = getClass().getResource("TimeOutput.jpg");
    private final ImageIcon MILES_IMAGE = new ImageIcon(new ImageIcon(MILES_PATH).getImage().getScaledInstance(500, 200, Image.SCALE_DEFAULT));
    
    public MilesToKnotsOutput(double output, double milesValue)
    {
        //Frame settings
        super("Your Time");
        this.setBounds(300, 220, 350, 500);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(COLOR_PURPLE);

        //Output label
        this.outputLabel = new JLabel();
        this.outputLabel.setForeground(COLOR_WHITE);
        
        //Distance label
        this.milesLabel = new JLabel("<html><center>Miles: " + milesValue + " statue miles <br>-----------------</center></html>");
        this.milesLabel.setForeground(COLOR_WHITE); //change this to declared color!
        this.milesLabel.setFont(SUB_FONT);
        
        //computation label 
        this.calculationLabel = new JLabel("<html><center>" + milesValue + " statue miles ÷ 1.15078 <br> = " + output + " knots</center></html>", JLabel.CENTER);
        this.calculationLabel.setForeground(COLOR_WHITE); //change this to declared color!
        this.calculationLabel.setFont(SUB_FONT);
        
       
        //Output Panel Code
        this.outputPanel = new JPanel(new FlowLayout());
        outputPanel.setBackground(COLOR_PURPLE);

        //Middle Panel Code
        this.middlePanel = new JPanel(new FlowLayout());
        middlePanel.setBackground(COLOR_PURPLE);

        //image label
        this.imageLabel = new JLabel(MILES_IMAGE);

        //Calculation answers
        this.outputLabel = new JLabel("<html><center>Conversion is <br>" + output + " knots</center></html>");
        this.outputLabel.setForeground(COLOR_WHITE);
        this.outputLabel.setFont(TITLE_FONT);
        
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
        
        //Adding Labels
        this.middlePanel.add(imageLabel);
        this.outputPanel.add(outputLabel);
        this.middlePanel.add(milesLabel);
        this.middlePanel.add(calculationLabel);
        
        //Button
        this.exitButton = new JButton("Return");
        this.exitButton.addActionListener(this);

        //Add to Layout
        this.add(outputPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(exitButton, BorderLayout.SOUTH);

        //Visibility
        this.setVisible(true);
        this.setJMenuBar(mainBar);
    }

    //main method
    public static void main(String[] args)
    {
        //pass in something that is an integer
        MilesToKnotsOutput milesToKnotsOutputObject = new MilesToKnotsOutput(2.0,4.0); //needs 2 string since changed
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