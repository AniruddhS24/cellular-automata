import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.io.*;

public class CellularAutomatonGUI extends JPanel implements ActionListener
{
    static int SCREEN_WID = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    static int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    static int cellsize;
    static int NUMGEN;
    static int CASZ;
    static Color backcolor;
    static Color forecolor;

    Timer tm = new Timer(20, this);
    static int y; int vely = 1;
    static int[][] grid;
    static CellularAutomata ca;
    static int ct = 0;

    public static void run(int size, int rule, String c1, String c2)
    {
        cellsize = size*2;
        NUMGEN = (int)(SCREEN_HEIGHT/cellsize);
        CASZ = (int)(SCREEN_WID/cellsize);
        y = (int)(cellsize*NUMGEN*0.3);
        switch (c1)
        {
            case "RED": backcolor = Color.RED; break;
            case "GREEN": backcolor = Color.GREEN; break;
            case "BLUE": backcolor = Color.BLUE; break;
            case "YELLOW": backcolor = Color.YELLOW; break;
            case "CYAN": backcolor = Color.CYAN; break;
            case "MAGENTA": backcolor = Color.MAGENTA; break;
            case "WHITE": backcolor = Color.WHITE; break;
            case "BLACK": backcolor = Color.BLACK; break;
            case "GRAY": backcolor = Color.GRAY; break;
            case "LIGHT GRAY": backcolor = Color.LIGHT_GRAY; break;
            case "DARK GRAY": backcolor = Color.DARK_GRAY; break;
            case "ORANGE": backcolor = Color.ORANGE; break;
            case "PINK": backcolor = Color.PINK; break;
        }
        switch (c2)
        {
            case "RED": forecolor = Color.RED; break;
            case "GREEN": forecolor = Color.GREEN; break;
            case "BLUE": forecolor = Color.BLUE; break;
            case "YELLOW": forecolor = Color.YELLOW; break;
            case "CYAN": forecolor = Color.CYAN; break;
            case "MAGENTA": forecolor = Color.MAGENTA; break;
            case "WHITE": forecolor = Color.WHITE; break;
            case "BLACK": forecolor = Color.BLACK; break;
            case "GRAY": forecolor = Color.GRAY; break;
            case "LIGHT GRAY": forecolor = Color.LIGHT_GRAY; break;
            case "DARK GRAY": forecolor = Color.DARK_GRAY; break;
            case "ORANGE": forecolor = Color.ORANGE; break;
            case "PINK": forecolor = Color.PINK; break;
        }
        CellularAutomatonGUI cagui = new CellularAutomatonGUI();
        JFrame jf = new JFrame();
        jf.setTitle("Cellular Automaton Visual");
        jf.setSize(SCREEN_WID,SCREEN_HEIGHT);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(cagui);

        ca = new CellularAutomata(CASZ,rule,3);
        grid = new int[NUMGEN][CASZ];
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        super.setBackground(backcolor);
        g.setColor(forecolor);
        for(int i = 0; i < NUMGEN; i++)
        {
            for(int j = 0; j < CASZ; j++)
            {
                if(grid[i][j]==1)
                    g.fillRect(0 + (j+1)*cellsize,(i+1)*cellsize + y,cellsize,cellsize);
            }
        }
        tm.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if(ct < NUMGEN)
        {
            newset();
            repaint();
            try
            {
                Thread.sleep(50);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        if(y < 0)
        {
            y += cellsize;
            newset();
        }
        y = y - vely;
        repaint();
    }

    public void newset()
    {
        if(ct < NUMGEN)
        {
            grid[ct] = ca.gennewgen();
            ct++;
        }
        else
        {
            int[][] ngrid = new int[NUMGEN][CASZ];
            for(int i = 0; i < NUMGEN-1; i++)
                for(int j = 0; j < CASZ; j++)
                    ngrid[i][j] = grid[i+1][j];

            ngrid[NUMGEN-1] = ca.gennewgen();

            for(int i = 0; i < NUMGEN; i++)
                for(int j = 0; j < CASZ; j++)
                    grid[i][j] = ngrid[i][j];
        }
    }
}
