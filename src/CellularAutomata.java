import java.util.*;
import java.io.*;

public class CellularAutomata
{
    private int nbrsz;
    private int SIZE;
    private int rule;
    private int[] generation;
    private int[] nxtgeneration;

    public CellularAutomata(int SIZE, int rule, int nbrsz)
    {
        this.SIZE = SIZE;
        this.rule = rule;
        this.nbrsz = nbrsz;
        this.generation = new int[SIZE];
        this.nxtgeneration = new int[SIZE];
        this.nxtgeneration[SIZE/2] = 1;
    }

    private void cleargen()
    {
        for(int i = 0; i < SIZE; i++)
        {
            generation[i] = nxtgeneration[i];
            nxtgeneration[i] = 0;
        }
    }

    private void generatenewgen()
    {
        int mk, bitno;
        for(int i = 0; i < SIZE; i++)
        {
            mk = i+nbrsz/2;
            if(mk>=SIZE) mk = mk-SIZE;
            bitno = 0;
            for(int k = 0; k < nbrsz; k++)
            {
                if(generation[mk]==1)
                    bitno += (1<<k);
                mk--; if(mk<0) mk = SIZE+mk;
            }
            nxtgeneration[i] = (rule>>bitno)&1;
        }
    }

    public int[] getnewgen()
    {
        return nxtgeneration;
    }

    public int[] gennewgen()
    {
        int[] tmp = new int[SIZE];
        for(int i = 0; i < SIZE; i++)
            tmp[i] = nxtgeneration[i];
        cleargen();
        generatenewgen();
        return tmp;
    }
    public void printnewgen()
    {
        for(int i = 0; i < SIZE; i++)
            if(nxtgeneration[i]==1)
                System.out.print("X");
            else
                System.out.print(" ");
        System.out.println();
        cleargen();
        generatenewgen();
    }
}
