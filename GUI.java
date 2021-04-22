package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vm252architecture.VM252Architecture;
import static vm252architecture.VM252Architecture.numberOfMemoryBytes;
import vm252utilities.VM252Utilities;

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
    public String filepath;

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
      FileChange path = new FileChange();
      if (path.file_path() == null)
        {
            String file = window("Please enter a full file path");
            path.ChangeAction(file);
            file_name.setText("..."+file.substring(file.length()-25));
            path(file);
        }
      changeFile.addActionListener(path);
      
      sAction instruction = new sAction();
      S.addActionListener(instruction);
      
      zAction thing = new zAction();
      Z.addActionListener(thing);
      
      nAction next = new nAction();
      N.addActionListener(next);
      
      AaAction setaccum = new AaAction();
      Aa.addActionListener(setaccum);
      
      
      
      byte [] program = VM252Utilities.readObjectCodeFromObjectFile(path.file_path());
      setUpProgram(program);
    }
    public short accumulator = 0;
    public short programCounter = 0;
    public int opcode;
    public static final int numberOfMemoryBytes = 8192; 
    
      private class quitAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }        
    }
      private class zAction implements ActionListener
      {
          
          @Override
          public void actionPerformed(ActionEvent Event)
            {
                programCounter = 0;
            }
      }
      private class FileChange implements ActionListener
    {
        private String file_path;
            
        private String file_path()
            {
                return file_path;
            }
        private void setfile_path(String other)
            {
                file_path = other;
            }
        public String ChangeAction(String other)
            {
                setfile_path(other);
                return file_path();
            }
        @Override
        public void actionPerformed(ActionEvent event)
            {
                String file = window("Please enter a full file path");
                System.out.println(file);       
                ChangeAction(file);
                //figure out how to change the text of the label from here
                //not really sure how to do that
//.file_name.setText("..."+file.substring(file.length()-25));

                //following comment is what you would do to run the following thing
                //byte [] program = VM252Utilities.readObjectCodeFromObjectFile(file_path());
                //VM252Architecture.runProgram(program);
            }
      }
      public String window(String question)
        {
            String input;
            input = JOptionPane.showInputDialog(question);
            return input;
        }
      public int intInput(String question)
        {
            int input;
            input = Integer.parseInt(JOptionPane.showInputDialog(question));
            return input;
        }
      public void path(String path)
        {
            path2file = path;
        }
      public String path2file;
    private class sAction implements ActionListener
      {
        private byte[] program;
        public byte[] getProgram()
        {
            return program;
        }
        public void setProgram()
        {
            program = VM252Utilities.readObjectCodeFromObjectFile(path2file);
        }
  
            @Override
        public void actionPerformed(ActionEvent event)
            {
                setProgram();
                System.out.println("ACC = "+accumulator);
                System.out.println("PC = "+programCounter);
                System.out.print("Next Instruction - ");
                byte [] encodedInstruction
                                = fetchBytePair(memory, programCounter);

                            int [] decodedInstruction
                                = VM252Utilities.decodedInstruction(encodedInstruction);
                             opcode = decodedInstruction[ 0 ];
                            
                            short operand
                                = decodedInstruction.length == 2
                                    ? ((short) (decodedInstruction[ 1 ]))
                                    : 0;
                            switch (opcode)
                            {
                                case VM252Utilities.INPUT_OPCODE-> {
                                    System.out.println("INPUT");
                                }
                                case VM252Utilities.LOAD_OPCODE-> {
                                    System.out.println("LOAD");
                                    System.out.println(operand);
                                }
                                case VM252Utilities.SET_OPCODE-> {
                                    System.out.println("SET");
                                    System.out.println(operand);
                                }
                                case VM252Utilities.STORE_OPCODE -> {
                                    //add the word next to it that it is beign stored to
                                    System.out.println("STORE");
                                    System.out.println(operand);
                                }
                                case VM252Utilities.ADD_OPCODE-> {
                                    System.out.println("ADD");
                                }
                                case VM252Utilities.SUBTRACT_OPCODE-> {
                                    System.out.println("SUB");
                                }
                                case VM252Utilities.JUMP_OPCODE-> {
                                    System.out.println("JUMP");
                                    System.out.println(operand);
                                }
                                case VM252Utilities.JUMP_ON_ZERO_OPCODE-> {
                                    System.out.println("JUMP ON ZERO");
                                    System.out.println(operand);
                                }
                                case VM252Utilities.JUMP_ON_POSITIVE_OPCODE-> {
                                    System.out.println("JUMP ON POS");
                                    System.out.println(operand);
                                }
                                case VM252Utilities.OUTPUT_OPCODE-> {
                                    System.out.println("OUTPUT");
                                }
                                case VM252Utilities.NO_OP_OPCODE-> {
                                    System.out.println("NOOP");
                                }
                                case VM252Utilities.STOP_OPCODE-> {
                                    System.out.println("STOP");
                                }
                            }
            }
        
        }
    private static short nextMemoryAddress(short address)
        {

            return ((short) ((address + 1) % numberOfMemoryBytes));

            }
    private static byte [] fetchBytePair(byte [] memory, short address)
        {

            byte [] bytePair = { memory[ address ], memory[ nextMemoryAddress(address) ] };

            return bytePair;

            }
     private static short bytesToInteger(
            byte mostSignificantByte,
            byte leastSignificantByte
            )
        {

            return
                ((short)
                    ((mostSignificantByte << 8 & 0xff00 | leastSignificantByte & 0xff)));

            }
     public byte[] memory;
        public void setUpProgram(byte[] program)
        {
            
            
            if (program != null)
            {
              memory = new byte[numberOfMemoryBytes];
            for (int loadAddress = 0; loadAddress < program.length; ++ loadAddress)
                            
                        memory[ loadAddress ] = program[ loadAddress ];

            for (int loadAddress = program.length;
                    loadAddress < numberOfMemoryBytes;
                    ++ loadAddress)
                    
                memory[ loadAddress ] = 0;
            
        }
        }
        private static int instructionSize(int opcode)
        {

            switch (opcode) {

                case VM252Utilities.LOAD_OPCODE,
                    VM252Utilities.SET_OPCODE,
                    VM252Utilities.STORE_OPCODE,
                    VM252Utilities.ADD_OPCODE,
                    VM252Utilities.SUBTRACT_OPCODE,
                    VM252Utilities.JUMP_OPCODE,
                    VM252Utilities.JUMP_ON_ZERO_OPCODE,
                    VM252Utilities.JUMP_ON_POSITIVE_OPCODE -> {

                    return 2;

                    }

                default -> {

                    return 1;

                    }

                }

            }

       private class nAction implements ActionListener
      {
            @Override
        public void actionPerformed(ActionEvent event)
            {
                programCounter
                                    = ((short)
                                        ((programCounter + instructionSize(opcode))
                                            % numberOfMemoryBytes)
                                            );
            }
            
      }
      private class AaAction implements ActionListener
      {
            @Override
        public void actionPerformed(ActionEvent event)
            {
                accumulator = ((short)intInput("What do you want to set the accumulator to"));
                System.out.println(accumulator);
            }
            
      }
}


