import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Date;
import java.util.ArrayList;

public class GUI implements ActionListener
{
    JFrame window;
    JTextArea textArea;
    JScrollPane scrollPane;
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuColor;
    JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
    JLabel date;
    JSeparator separator;
    File openFile = null;

    String lastSaved;

    public static void main(String[] args) {
        new GUI(); //
    }

    //constructor class
    public GUI()
    { // test
        createWindow();
        createTextArea();
        createMenuBar();
        // potato
        createFileMenu();
        window.setVisible(true); //set the window visible
    }

    //Method that generates the window for the notepad
    public void createWindow()
    {
        window = new JFrame("Notepad"); //top bar of window text
        window.setSize(800, 600); //size of window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //window closes when clicking X

    }

    //Method that generates the text area for the notepad
    public void createTextArea()
    {
        textArea = new JTextArea();

        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); //creates a general border for window (looks better imo but barely visible)
        window.add(scrollPane);
    }


    //Method that creates a bar at the top of the notepad with
    public void createMenuBar()
    {
        menuBar = new JMenuBar();
        window.setJMenuBar(menuBar);

        menuFile = new JMenu("File");
        menuBar.add(menuFile);

        menuEdit = new JMenu("Edit");
        menuBar.add(menuEdit);

        menuFormat = new JMenu("Format");
        menuBar.add(menuFormat);

        menuColor = new JMenu("Color");
        menuBar.add(menuColor);

        separator = new JSeparator();
        menuBar.add(separator);

        date = new JLabel("Last saved:");
        menuBar.add(date);
    }

    //Method to add & hold the items listed in the file section
    public void createFileMenu()
    {
        iNew = new JMenuItem("New");
        menuFile.add(iNew);


        iOpen = new JMenuItem("Open");
        iOpen.addActionListener(this); //makes it so actionListener is listening for actions on this particular button
        menuFile.add(iOpen);

        iSave = new JMenuItem("Save");
        iSave.addActionListener(this); //makes it so actionListener is listening for actions on this particular button
        menuFile.add(iSave);

        iSaveAs = new JMenuItem("Save As");
        iSaveAs.addActionListener(this); //makes it so actionListener is listening for actions on this particular button
        menuFile.add(iSaveAs);

        iExit = new JMenuItem("Exit");
        menuFile.add(iExit);
    }

    @Override
    public void actionPerformed (ActionEvent e){
        if (e.getSource() == iOpen){ //if Open button is clicked, do the following
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null); //select file to open
            //int response = fileChooser.showSaveDialog(null); //select file to save
            lastSaved = ""; //resets last saved
            if (response == JFileChooser.APPROVE_OPTION){ //if the button that was clicked inside the fileChooser is Open
                openFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
                try{
                    FileReader reader = new FileReader(openFile);
                    int data = reader.read();
                    int counter = 0;


                    while(data != -1)
                    {
                        //if last reader reaches last 40 characters of file,
                        //do not print to textArea and rather add to String
                        //of JLabel.
                        //Otherwise, continue writing as normal
                        if (openFile.length() -counter <= 40)
                        {
                            lastSaved += String.valueOf((char)data);
                            data = reader.read();
                            //delete comment to see last 40 printed System.out.println(lastSaved);
                            counter++;
                        }
                        else
                        {
                            textArea.insert(String.valueOf((char)data), counter);
                            data = reader.read();
                            counter++;
                        }
                    }
                    date.setText(lastSaved);
                    reader.close();
                }
                catch(FileNotFoundException notFoundException){
                    System.out.println(notFoundException.getMessage());
                }
                catch(IOException ioException){
                    System.out.println(ioException.getMessage());
                }
            }
        }

        if (e.getSource() == iSave){ //if Save button is clicked, do the following
            String updatedText = textArea.getText();

            try {
                if (openFile == null){
                    textArea.insert("No file currently opened", 0);
                }
                else{
                    FileWriter writer = new FileWriter(openFile);
                    writer.write(updatedText);
                    //below line adds the date to the tail-end of saved file.
                    //it will not appear when file is read (see Open ActionListener func)
                    writer.write("\n Last saved: " + new Date());
                    writer.close();

                    date.setText("Last saved: " + new Date());

                }

            }
            catch (IOException ioException) {
                System.out.println(ioException.getMessage());
            }
        }
        //commit test
        //fork test
    }

}
