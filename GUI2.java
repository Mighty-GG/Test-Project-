//import javax.swing.*;
//import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

public class GUI2 implements ActionListener
{
    JFrame window;
    //Text Area
    JTextArea textArea;
    JScrollPane scrollPane;
    boolean wordWrapOn = false;
    //Top Menu Bar
    JMenuBar menuBar;
    JMenu menuFile, menuEdit, menuFormat, menuColor;
    //File Menu
    JMenuItem iNew, iOpen, iSave, iSaveAs, iExit;
    //Edit Menu
    JMenuItem iUndo, iRedo;
    //Format Menu
    JMenuItem iWrap, iFontArial, iFontCSMS, iFontTNR, iFontSize8, iFontSize12, iFontSize16, iFontSize20, iFontSize24, iFontSize28;
    JMenu menuFont, menuFontSize;

    Function_Format format = new Function_Format(this);
    Function_Edit edit = new Function_Edit(this);

    UndoManager um = new UndoManager();


    public static void main(String[] args)
    {
        new GUI2(); //
    }

    //constructor class
    public GUI2()
    {
        createWindow();
        createTextArea();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createFormatMenu();

        format.selectedFont = "Arial";
        format.createFont(16);
        format.wordWrap();
        window.setVisible(true); //set the window visible
    }

    //Method that generates the window for the notepad
    public void createWindow()
    {
        window = new JFrame("SimpleDiary"); //top bar of window text
        window.setSize(900, 700); //size of window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //window closes when clicking X

    }

    //Method that generates the text area for the notepad
    public void createTextArea()
    {
        textArea = new JTextArea();

        textArea.getDocument().addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e)
            {
                um.addEdit((e.getEdit()));
            }
        });

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
    }

    //Method to add & hold the items listed in the file section
    public void createFileMenu()
    {
        iNew = new JMenuItem("New");
        menuFile.add(iNew);

        iOpen = new JMenuItem("Open");
        menuFile.add(iOpen);

        iSave = new JMenuItem("Save");
        menuFile.add(iSave);

        iSaveAs = new JMenuItem("Save As");
        menuFile.add(iSaveAs);

        iExit = new JMenuItem("Delete"); //possibly changing if not getting rid of entirely
        menuFile.add(iExit);
    }

    public void createEditMenu()
    {
        iUndo = new JMenuItem("Undo");
        iUndo.addActionListener(this);
        iUndo.setActionCommand("Undo");
        menuEdit.add(iUndo);

        iRedo= new JMenuItem("Redo");
        iRedo.addActionListener(this);
        iRedo.setActionCommand("Redo");
        menuEdit.add(iRedo);
    }

    public void createFormatMenu()
    {
        iWrap = new JMenuItem("Word Wrap: Off");
        iWrap.addActionListener(this);
        iWrap.setActionCommand("Word Wrap");
        menuFormat.add(iWrap);

        menuFont = new JMenu("Font");
        menuFormat.add(menuFont);

        iFontArial = new JMenuItem("Arial");
        iFontArial.addActionListener(this);
        iFontArial.setActionCommand("Arial");
        menuFont.add(iFontArial);

        iFontCSMS = new JMenuItem("Comic Sans MS");
        iFontCSMS.addActionListener(this);
        iFontCSMS.setActionCommand("Comic Sans MS");
        menuFont.add(iFontCSMS);

        iFontTNR = new JMenuItem("Times New Roman");
        iFontTNR.addActionListener(this);
        iFontTNR.setActionCommand("Times New Roman");
        menuFont.add(iFontTNR);

        menuFontSize = new JMenu("Font Size");
        menuFormat.add(menuFontSize);

        iFontSize8 = new JMenuItem("8");
        iFontSize8.addActionListener(this);
        iFontSize8.setActionCommand("Size8");
        menuFontSize.add(iFontSize8);

        iFontSize12 = new JMenuItem("12");
        iFontSize12.addActionListener(this);
        iFontSize12.setActionCommand("Size12");
        menuFontSize.add(iFontSize12);

        iFontSize16 = new JMenuItem("16");
        iFontSize16.addActionListener(this);
        iFontSize16.setActionCommand("Size16");
        menuFontSize.add(iFontSize16);

        iFontSize20 = new JMenuItem("20");
        iFontSize20.addActionListener(this);
        iFontSize20.setActionCommand("Size20");
        menuFontSize.add(iFontSize20);

        iFontSize24 = new JMenuItem("24");
        iFontSize24.addActionListener(this);
        iFontSize24.setActionCommand("Size24");
        menuFontSize.add(iFontSize24);

        iFontSize28 = new JMenuItem("28");
        iFontSize28.addActionListener(this);
        iFontSize28.setActionCommand("Size28");
        menuFontSize.add(iFontSize28);

    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();

        switch (command)
        {
            case "Undo": edit.undo(); break;
            case "Redo": edit.redo(); break;
            case "Word Wrap": format.wordWrap(); break;
            case "Arial": format.setFont(command); break;
            case "Comic Sans MS": format.setFont(command); break;
            case "Times New Roman": format.setFont(command); break;
            case "Size8": format.createFont(8); break;
            case "Size12": format.createFont(12); break;
            case "Size16": format.createFont(16); break;
            case "Size20": format.createFont(20); break;
            case "Size24": format.createFont(24); break;
            case "Size28": format.createFont(28); break;
        }
    }

    //test
}
