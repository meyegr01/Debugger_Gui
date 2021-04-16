/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author vandja05
 */
public class GUI {

    public static void main(String[] commandLineArguments) {
       EventQueue.invokeLater(
               ()->
                    {
                    ProgramFrame frame = new ProgramFrame();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setResizable(false);
                    frame.setVisible(true);
                    }
       ); 
    }
    
}
class ProgramFrame extends JFrame
{

    private static final int OUR_DEFAULT_WIDTH = 400;
    private static final int OUR_DEFAULT_HEIGHT = 400;

    private JPanel myProgramPanel;

    private JPanel programPanel()
    {

        return myProgramPanel;

        }

    private void setProgramPanel(JPanel other)
    {

        myProgramPanel = other;

        }

    public ProgramFrame()
    {

        setTitle("Debugger GUI");
        setSize(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);
        
//
        // Create buttons
        //
        //first line buttons
            JButton Aa = new JButton("Aa");
            Aa.setBounds(5,5,90,60);
            JButton Ap = new JButton("Ap");
            Ap.setBounds(100,5,90,60);
            JButton Amb_h = new JButton("Amb H");
            Amb_h.setBounds(195,5,90,60);
            JButton Amb_s = new JButton("Amb S");
            Amb_s.setBounds(290,5,90,60);
            JButton Ba = new JButton("Ba");
            Ba.setBounds(5,70,90,60);
        //second line buttons
            JButton Z = new JButton("Z");
            Z.setBounds(100,70,90,60);
            JButton Mb = new JButton("Mb");
            Mb.setBounds(195,70,90,60);
            JButton N = new JButton("N");
            N.setBounds(290,70,90,60);
            JButton ob = new JButton("ob");
            ob.setBounds(5,135,90,60);
        //Third line buttons
            JButton OD = new JButton("OD");
            OD.setBounds(100,135,90,60);
            JButton S = new JButton("S");
            S.setBounds(195,135,90,60);
            JButton R = new JButton("R");
            R.setBounds(290,135,90,60);
            
        //Last Line Buttons
            JButton Help = new JButton("Help");
            Help.setBounds(100,200,90,60);
            JButton Quit = new JButton("Quit");
            Quit.setBounds(195,200,90,60);
        // Label with the name of the file
           JLabel file_label = new JLabel("File: ");
           file_label.setBounds(100,275,90,30);
           JLabel file_name = new JLabel("No File currently selected");
           file_name.setBounds(125,275,200,30);
        //Add the change file button
            JButton changeFile = new JButton("Change the current file");
            changeFile.setBounds(100,300, 185, 30);

        //
        // Create panel to hold all components
        //
            setProgramPanel(new JPanel());
        
            //makes it so we can set absolute positions for buttons
            programPanel().setLayout(null);
            //adds the buttons to the panel
            programPanel().add(Aa);
            programPanel().add(Ap);
            programPanel().add(Amb_h);
            programPanel().add(Amb_s);
            programPanel().add(Ba);
            programPanel().add(Z);
            programPanel().add(Mb);
            programPanel().add(N);
            programPanel().add(ob);
            programPanel().add(OD);
            programPanel().add(S);
            programPanel().add(R);
            programPanel().add(Help);
            programPanel().add(Quit);
            programPanel().add(file_label);
            programPanel().add(file_name);
            programPanel().add(changeFile);
            //makes the panel visible
            add(programPanel());
      //
      //Creates button action/implements the quit button
      quitAction close = new quitAction();
      Quit.addActionListener(close);
    }
      private class quitAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }        
    }
    
}
