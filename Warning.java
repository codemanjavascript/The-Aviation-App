/*
 Tommy Wan
 3/22/2020
 This class allows for warning popups whenever a try and catch is used
 */
package wansteam;

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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Warning extends JFrame implements ActionListener
{
    //Declare Labels and Panels
    private JLabel warningLabel;
    private JLabel descriptionLabel;
    private JLabel imageLabel;
    private JPanel upperPanel;
    private JPanel middlePanel;
    private JPanel lowerPanel;
    private JButton returnButton;
    
    //Image: Step 1
    private final URL STOP_PATH = getClass().getResource("warning.jpg"); //for image, make a path, and get class/resources gets the image. Nmae can be anything
    //Image: Step 2
    //After, make the image and set the scale/size of the image. Make sure to import both too
    private final ImageIcon STOP_IMAGE = new ImageIcon(new ImageIcon(STOP_PATH).getImage().getScaledInstance(500,300, Image.SCALE_DEFAULT));
    
    //Styles, Fonts, and Colorings
    public static final Font TITLE_FONT = new Font("Phosphate", Font.BOLD, 40);
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
    
    //Main Contructor
    public Warning(String message)
    {
        //General Settings
        super("Warning Frame");
        this.setBounds(470, 150, 500, 530);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(COLOR_RED);
        
        //Create Labels
        this.imageLabel = new JLabel(STOP_IMAGE);
        this.warningLabel = new JLabel("<html><center>Please Enter a <br>Positive Number Only</center></html>");
        this.warningLabel.setFont(TITLE_FONT);
        this.warningLabel.setForeground(COLOR_WHITE);
        this.descriptionLabel = new JLabel("<html><center>Or you may have inputted a letter, <br> symbol, or negative (non-real answers)</center></html>");
        this.descriptionLabel.setFont(SUB_FONT);
        this.descriptionLabel.setForeground(COLOR_WHITE);
        
        //Create Button
        this.returnButton = new JButton("Return to Input");
        this.returnButton.addActionListener(this);
        
        //Create panel
        this.upperPanel = new JPanel(new FlowLayout());
        this.upperPanel.setBackground(COLOR_RED);
        this.middlePanel = new JPanel(new FlowLayout());
        this.middlePanel.setBackground(COLOR_RED);
        this.lowerPanel = new JPanel(new FlowLayout());
        this.lowerPanel.setBackground(COLOR_RED);
        
        this.upperPanel.add(warningLabel);
        this.upperPanel.add(descriptionLabel);
        this.upperPanel.setPreferredSize(new Dimension(50,184));
        this.middlePanel.add(imageLabel);
        this.lowerPanel.add(returnButton);
        
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
        this.homeItem = new JMenuItem("Home");
        this.exitItem = new JMenuItem("Exit");
        this.exitItem.addActionListener(this);
        mainBar.add(navigationMenu);
        navigationMenu.add(helpItem);
        navigationMenu.add(definitionItem);
        navigationMenu.add(aboutItem);
        navigationMenu.add(faqItem);
        navigationMenu.add(homeItem);
        navigationMenu.add(exitItem);
        
        //Center the Label
        this.add(upperPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);
        
        this.setJMenuBar(mainBar);
        this.setVisible(true);
    }
    
    //Create the Main Method
    public static void main(String[] args)
    {
        new Warning("Please Enter a Positive Number Only"); //ananonmous object
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
       String command = e.getActionCommand();
       Object myComponent = e.getSource();
       if (command.equals("Return to Input"))
        {
            this.dispose();
            this.validate();
            this.repaint();
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