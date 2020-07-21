/*
 Tommy Wan
 3/22/2020
 This class is the welcome frame
 This project is an aviation app that calculates various calculations. 
 See proposal for all calculations 
 */
package wansteam;

//Import required packages to be used
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
import javax.swing.SwingConstants;

//Welcome Class Creation with all Neccesary implentations
public class Welcome extends JFrame implements ActionListener
{
    //Styles, Fonts, and Colorings
    public static final Font TITLE_FONT = new Font("Phosphate", Font.BOLD, 70);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 20);
    public static final Color COLOR_BLUE = new Color(1, 103, 253);
    public static final Color COLOR_WHITE = new Color(255, 255, 255);

    //Declaring JLabels, Buttons, Panels, Etc.
    private JLabel welcomeLabel;
    private JPanel welcomePanel;
    private JLabel descriptionLabel;
    private JPanel lowerPanel;
    private JButton startButton;
    private JButton exitButton;
    
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

    //Image label
    private JLabel imageLabel;

    //Welcome Image: Plane
    private final URL PLANE_PATH = getClass().getResource("plane.jpeg");
    private final ImageIcon PLANE_IMAGE = new ImageIcon(new ImageIcon(PLANE_PATH).getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT));

    public Welcome(String frameName)
    {
        //Basic Frame Information
        super(frameName);
        this.setBounds(300, 200, 800, 500);
        this.getContentPane().setBackground(COLOR_BLUE);

        //Operations of the Frame
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //Welcome Label
        this.welcomeLabel = new JLabel("<html></br>The Aviation App</html>");
        this.welcomeLabel.setForeground(COLOR_WHITE);
        this.welcomeLabel.setFont(TITLE_FONT);

        //Start Button
        this.startButton = new JButton("Start");
        this.startButton.addActionListener(this);

        //Exit Button
        this.exitButton = new JButton("Exit Program");
        this.exitButton.addActionListener(this);

        //Description of Momentum Label
        this.descriptionLabel = new JLabel("<html><center>Created by Tommy Wan. For help, click menu.</center>"
                + "<center> This program calculates common aviation problems. </center> </html>", SwingConstants.CENTER);
        this.descriptionLabel.setForeground(COLOR_WHITE);
        this.descriptionLabel.setFont(TEXT_FONT);

        //Image label
        this.imageLabel = new JLabel(PLANE_IMAGE);

        //Constructing the Menus
        this.mainBar = new JMenuBar();
        this.navigationMenu = new JMenu("Navigation");
        this.helpItem = new JMenuItem("Help");
        this.helpItem.addActionListener(this);
        this.definitionItem = new JMenuItem("Definitions");
        this.definitionItem.addActionListener(this);
        this.aboutItem = new JMenuItem("About Me");
        this.aboutItem.addActionListener(this);
        this.faqItem = new JMenuItem("FAQ");
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

        //Upper Panel
        this.welcomePanel = new JPanel(new FlowLayout());
        this.welcomePanel.setBackground(COLOR_BLUE);
        this.mainBar.setOpaque(true);
        this.mainBar.setBackground(COLOR_BLUE);
        //Adding to Upper Panel
        this.welcomePanel.add(welcomeLabel);
        this.welcomePanel.add(descriptionLabel);
        this.welcomePanel.add(imageLabel);
        //Lower Panel
        this.lowerPanel = new JPanel(new FlowLayout());
        this.lowerPanel.setBackground(COLOR_BLUE);
        this.lowerPanel.add(startButton);
        this.lowerPanel.add(exitButton);

        //Adds Labels and Panels
        this.add(welcomePanel, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        this.setJMenuBar(mainBar);
        this.setVisible(true);
    }

    //Main method
    public static void main(String[] args)
    {
        Welcome welcomeObject = new Welcome("Welcome Page");
    }

    //Override Method to have for button
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        Object myComponent = e.getSource();
        
        if (command.equals("Start"))
        {
            Option optionObjectOne = new Option("Option Page");
            this.dispose();
        } 
        else if (command.equals("Exit Program"))
        {
            //Exits the program
            System.exit(0);
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
            //Nothing in Welcome Page
        }
        else if (myComponent == exitItem)
        {
            System.exit(0);
        }
        this.validate();
        this.repaint();
    }
}