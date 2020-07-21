/*
 Tommy Wan
 3/22/2020
 This class serves as the Frequently Asked Questions page for the app
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
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

//Help Class Creation with all Neccesary implentations
public class FAQ extends JFrame implements ActionListener
{
    //Declaring JLabels, Buttons, Panels, Etc.
    private JLabel upperLabel;
    private JPanel upperPanel;
    private JPanel middlePanel;
    private JPanel lowerPanel;
    private JButton returnButton;

    //Image label
    private JLabel imageLabel;

    //Help Image: Plane
    private final URL FAQ_PATH = getClass().getResource("faq.jpeg");
    private final ImageIcon FAQ_IMAGE = new ImageIcon(new ImageIcon(FAQ_PATH).getImage().getScaledInstance(800, 360, Image.SCALE_DEFAULT));

    //Styles, Fonts, and Colorings
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 30);
    public static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 20);
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
    private JMenuItem activityLogItem;
    private JMenuItem exitItem;

    //Help Constructor
    public FAQ(String frameName)
    {
        //Basic Frame Information
        this.setBounds(280, 60, 850, 470);
        this.getContentPane().setBackground(COLOR_RED);

        //Operations of the Frame
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //Upper Label
        this.upperLabel = new JLabel("<html>Frequently Asked Questions</html>");
        this.upperLabel.setForeground(COLOR_WHITE);
        this.upperLabel.setFont(TITLE_FONT);

        //Buttons
        this.returnButton = new JButton("Return");
        this.returnButton.addActionListener(this);

        //Constructing the Menus
        this.mainBar = new JMenuBar();
        this.navigationMenu = new JMenu("Navigation");
        this.helpItem = new JMenuItem("Help");
        this.helpItem.addActionListener(this);
        this.definitionItem = new JMenuItem("Definitions");
        this.definitionItem.addActionListener(this);
        this.homeItem = new JMenuItem("Home");
        this.homeItem.addActionListener(this);
        this.activityLogItem = new JMenuItem("Activity Log");
        this.activityLogItem.addActionListener(this);
        this.exitItem = new JMenuItem("Exit");
        this.exitItem.addActionListener(this);
        mainBar.add(navigationMenu);
        navigationMenu.add(helpItem);
        navigationMenu.add(definitionItem);
        navigationMenu.add(homeItem);
        navigationMenu.add(activityLogItem);
        navigationMenu.add(exitItem);
        
        //Image label
        this.imageLabel = new JLabel(FAQ_IMAGE);

        //Upper Panel
        this.upperPanel = new JPanel(new FlowLayout());
        this.upperPanel.setBackground(COLOR_RED);
        this.upperPanel.add(upperLabel);
      
        //Middle Panel
        this.middlePanel = new JPanel(new FlowLayout());
        this.middlePanel.setBackground(COLOR_RED);
        
        this.lowerPanel = new JPanel(new FlowLayout());
        this.lowerPanel.setBackground(COLOR_RED);
        
        //Adding to Middle Panel
        this.middlePanel.add(imageLabel);
        this.lowerPanel.add(returnButton);
        
        //Adds Labels and Panels
        this.add(upperPanel, BorderLayout.NORTH);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(lowerPanel, BorderLayout.SOUTH);

        //Required Actions
        this.setVisible(true);
        this.setJMenuBar(mainBar);
    }

    //Main method
    public static void main(String[] args)
    {
        FAQ faqObject = new FAQ("FAQ Page");
    }

    @Override //Override Method
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        Object myComponent = e.getSource();
        //if state ments for buttons
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
        this.validate();
        this.repaint();
    }
}