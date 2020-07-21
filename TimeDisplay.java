/*
 Tommy Wan
 7/20/2020
 This is the display page for time calculations. Through database, calculations will be shown here
 */
package wansteam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class TimeDisplay extends JFrame implements ActionListener
{
    //Colors
    public static final Color COLOR_GREEN = new Color(172, 236, 213);
    public static final Color COLOR_YELLOW = new Color(255, 249, 170);
    public static final Color COLOR_PEACH = new Color(255, 213, 184);
    public static final Color COLOR_ORANGE = new Color(255, 235, 204);
    public static final Color COLOR_PURPLE = new Color(177, 156, 217);
    public static final Color COLOR_WHITE = new Color(255, 255, 255);
        
    //ArrayList for DB
    private final String[] ENTRY_HEADER = {"Entry #"};
    
    private ArrayList<ArrayList<String>> dataList;
    private Object[][] data; //we are converting array list to object since tables are in 2d
    private Object[][] data1 = 
    {
        {"1"},
        {"2"},
        {"3"},
        {"4"},
        {"5"},
        {"6"},
        {"7"},
        {"8"},
        {"9"},
        {"10"},
        {"11"},
        {"12"},
        {"13"},
        {"14"}
    };
    
    
    private JTable dbTable;
    private JTable idTable;
    private JScrollPane scrollTable; //create table basics to insert 2d array
    private JScrollPane scrollTable2;
    private JTableHeader header;
    private JTableHeader headerOne;
    private TableColumn column; //remember, no J for this one.
    private JButton backButton;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton helpButton;
    private JPanel buttonPanel;

    //Creating the constructor for table with name table name and headers
    public TimeDisplay(String dbName, String tableName, String[] tableHeaders)
    {
        //Formatting the frame
        super("Time Activity Log");
        this.setBounds(320, 240 , 750, 447);
        this.getContentPane().setBackground(Color.WHITE);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //Contruct the data or "grab" or data
        JavaDatabase dbObj = new JavaDatabase(dbName); //gets the data
        dataList = dbObj.getData(tableName, tableHeaders); //gets data Table. This is array list!
        
        //If statement is for when if we don't have first time data.
        if (dataList.size()==0)
        {
            data = new Object[0][0];
        }
        else
        {
            data = dbObj.to2dArray(dataList); //convert the data list we recieve using the 2darray method we made
        }
        
        //closes DB Connection for safety. Brings the method to close
        dbObj.closeDbConn(); 

        //Now we need to display it after we get the data
        //Contruct table
        dbTable = new JTable(data, tableHeaders); //tableHeaders
        idTable = new JTable(data1, ENTRY_HEADER);
        
        //Format Table
        dbTable.setGridColor(Color.BLACK);
        dbTable.setBackground(COLOR_ORANGE);
        dbTable.setFont(new Font("Arial", Font.BOLD, 20)); //font is ananmous, we only need it once
        
        idTable.setGridColor(Color.BLACK);
        idTable.setBackground(COLOR_ORANGE);
        idTable.setFont(new Font("Arial", Font.BOLD, 20)); //font is ananmous, we only need it once

        //Format Header
        header = dbTable.getTableHeader(); //getting value from variable on top and assing to header
        headerOne = idTable.getTableHeader(); //getting value from variable on top and assing to header
        
        header.setBackground(COLOR_PURPLE);
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 25));
        
        headerOne.setBackground(COLOR_PURPLE);
        headerOne.setForeground(Color.WHITE);
        headerOne.setFont(new Font("Arial", Font.BOLD, 25));

        //Format rows
        dbTable.setRowHeight(25);
        idTable.setRowHeight(25);
  
        column = idTable.getColumnModel().getColumn(0);
        column.setPreferredWidth(10);
      
        //Format columns
        column = dbTable.getColumnModel().getColumn(0);
        column.setPreferredWidth(50);
        column = dbTable.getColumnModel().getColumn(1);
        column.setPreferredWidth(50);
        column = dbTable.getColumnModel().getColumn(2);
        column.setPreferredWidth(50);
        
        //buttons and panels new
        this.backButton = new JButton("Back");
        backButton.addActionListener(this);
        this.insertButton = new JButton("Insert");
        insertButton.addActionListener(this);
        this.updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        this.deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        this.helpButton = new JButton("Help/Tutorial");
        helpButton.addActionListener(this);
        this.buttonPanel = new JPanel();
        buttonPanel.setBackground(COLOR_GREEN);
        buttonPanel.add(backButton);
        
        
        //Put JPanel into Panel scrollPabe
        scrollTable = new JScrollPane();
        scrollTable.getViewport().add(dbTable);
        scrollTable2 = new JScrollPane();
        scrollTable2.getViewport().add(idTable);//Table to ScrollPane
        dbTable.setFillsViewportHeight(true);
        idTable.setFillsViewportHeight(true);

        this.scrollTable.setPreferredSize(new Dimension(628,20));
        this.scrollTable2.setPreferredSize(new Dimension(122,20));
        
        //Scroll Pane goes into panel
        this.add(scrollTable, BorderLayout.EAST);
        this.add(scrollTable2, BorderLayout.WEST);
        this.add(buttonPanel, BorderLayout.NORTH);
        
        this.setVisible(true);
    }

    //Main Method with Identical Stuff as Database and Table Info
    public static void main(String[] args)
    {
        String dbName = "AviationDatabase";
        String tableName = "Time";
        String[] tableHeaders =
        {
            "Distance", "Groundspeed", "Time"
        }; 
        //note: the spaces are put in so that the table headers will be centered.
        new TimeDisplay(dbName, tableName, tableHeaders);
    }

    //For Action Listener
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if (command.equals("Back"))
        {
            this.dispose();
           
        }
        else if (command.equals("Help"))
        {
            Help help2 = new Help("Help");
        }
        else if (command.equals("Update"))
        {
            //UpdateDisplay updateObject2 = new UpdateDisplay(this);
        }
        else if (command.equals("Delete"))
        {
            //DeleteDisplay deleteObject2 = new DeleteDisplay(this);
        }
        //Validate and Repaint required
        this.validate();
        this.repaint();
    }

}
