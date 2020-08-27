import javax.swing.*;
import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        String r1 = JOptionPane.showInputDialog(null, "Enter Rule No. (0-255)","Setup Dialog",1);
        try
        {
            Integer.parseInt(r1);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error! Please enter a valid rule number");
            System.exit(0);
        }

        if(Integer.parseInt(r1)<0||Integer.parseInt(r1)>255)
        {
            JOptionPane.showMessageDialog(null,"Error! Please enter a valid rule number");
            System.exit(0);
        }
        String[] choices = {"1","2","3","4","5","6","7","8","9","10"};
        String r2 = (String) JOptionPane.showInputDialog(null, "Choose zoom factor (10 is largest, 1 is smallest)",
                "Setup Dialog", JOptionPane.QUESTION_MESSAGE, null,choices,choices[0]);

        String[] backchoices = {"RED","GREEN","BLUE","YELLOW","CYAN","MAGENTA","WHITE","BLACK","GRAY","LIGHT GRAY","DARK GRAY","ORANGE","PINK"};
        String r3 = (String) JOptionPane.showInputDialog(null, "Choose background color",
                "Setup Dialog", JOptionPane.QUESTION_MESSAGE, null,backchoices,backchoices[6]);

        String[] forechoices = {"RED","GREEN","BLUE","YELLOW","CYAN","MAGENTA","WHITE","BLACK","GRAY","LIGHT GRAY","DARK GRAY","ORANGE","PINK"};
        String r4 = (String) JOptionPane.showInputDialog(null, "Choose foreground color",
                "Setup Dialog", JOptionPane.QUESTION_MESSAGE, null,forechoices,forechoices[7]);

        if(r2==null||r3==null||r4==null)
        {
            JOptionPane.showMessageDialog(null,"Error. Please enter proper responses.");
            System.exit(0);
        }
        CellularAutomatonGUI.run(Integer.parseInt(r2),Integer.parseInt(r1),r3,r4);

    }
}
