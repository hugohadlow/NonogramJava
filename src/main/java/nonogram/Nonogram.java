package nonogram;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class Nonogram
{
    public byte[][] Solve(byte[][] input, int[][] xClues, int[][] yClues) //0=white, 1=black
    {
        int xDimension = input.length;
        int yDimension = input[0].length;
        if (xClues.length != xDimension) throw new IllegalArgumentException("Wrong number of X clues");
        if (yClues.length != yDimension) throw new IllegalArgumentException("Wrong number of Y clues");

        //Generate possibilities
        List<List<byte[]>> xPossibilities = Possibilities(xClues, yDimension);
        List<List<byte[]>> yPossibilities = Possibilities(yClues, xDimension);

        byte[][] output = input.clone();
        for (int i = 0; i < xDimension; i++)
            for (int j = 0; j < yDimension; j++)
                if (output[i][j] == 0) output[i][j] = 2; //2 = unknown

        boolean[] xSolved = new boolean[xDimension];
        boolean[] ySolved = new boolean[yDimension];

        //Loop
        while (Arrays.asList(xSolved).contains(false) || Arrays.asList(ySolved).contains(false))
        {
            //For each column
            for(int i = 0; i < xDimension; i++)
            {
                byte[] column = GetColumn(output, i);
                Tuple<byte[], Boolean> tuple = Prune(column, xPossibilities.get(i));
                xSolved[i] = tuple.right; //Solved?
                InsertColumn(output, tuple.left, i);
            }

            //For each row
            for (int j = 0; j < yDimension; j++)
            {
                byte[]  row = GetRow(output, j);
                Tuple<byte[], Boolean> tuple = Prune(row, yPossibilities.get(j));
                ySolved[j] = tuple.right; //Solved?
                InsertRow(output, tuple.left, j);
            }
        }

        return output;
    }

    byte[] GetColumn(byte[][] array, int x)
    {
        int length = array[0].length;
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++)
            result[i] = array[x][i];
        return result;
    }

    byte[] GetRow(byte[][] array, int y)
    {
        int length = array.length;
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++)
            result[i] = array[i][y];
        return result;
    }

    private void InsertColumn(byte[][] output, byte[] input, int x)
    {
        int length = output[0].length;
        for (int i = 0; i < length; i++)
            output[x][i] = input[i];
    }

    private void InsertRow(byte[][] output, byte[] input, int y)
    {
        int length = output.length;
        for (int i = 0; i < length; i++)
            output[i][y] = input[i];
    }


    public List<List<byte[]>> Possibilities(int[][] clues, int dimension)
    {
        List<List<byte[]>> result = new ArrayList<List<byte[]>>();
        for (int i = 0; i < clues.length; i++)
        {
            result.add(Possibilities(clues[i], dimension));
        }
        return result;
    }

    public List<byte[]> Possibilities(int[] clues, int dimension)
    {
        int cluesCount = clues.length;
        int blacks = Arrays.stream(clues).sum();
        int buckets = cluesCount + 1;
        int whites = dimension - blacks;
        whites = whites - (buckets - 2); //Middle buckets must contain at least 1 white.

        List<int[]> combinations = Combinations(buckets, whites);

        List<byte[]> possibilities = new ArrayList<byte[]>();
        for (int[] combination : combinations)
        {
            for (int i = 1; i < buckets - 1; i++) combination[i]++; //Middle buckets must contain at least 1 white.
            byte[] possibility = new byte[dimension];
            int index = 0;
            for (int b = 0; b < cluesCount; b++)
            {
                index += combination[b];
                int stop = index + clues[b];
                for (; index < stop; index++)
                    possibility[index] = 1;
            }
            //Final bucket is zeros

            possibilities.add(possibility);
        }
        return possibilities;
    }

    public Tuple<byte[], Boolean> Prune(byte[] input, List<byte[]> possibilities)
    {
        byte[] output = input.clone();
        int inputLength = input.length;

        //Remove possibilities incompatible with input
        for (int p = possibilities.size() - 1; p >= 0; p--)
        {
            byte[] possibility = possibilities.get(p);
            boolean compatible = true;
            for(int i = 0; i < inputLength; i++)
            {
                if (input[i] == 0 && possibility[i] != 0)
                {
                    compatible = false;
                    break;
                }
                if (input[i] == 1 && possibility[i] != 1)
                {
                    compatible = false;
                    break;
                }
            }
            if (!compatible) possibilities.remove(p);
        }

        //Unify remaining possibilities
        int[] unified = new int[inputLength];
        for (int i = 0; i < inputLength; i++)
        {
            boolean black = false;
            boolean white = false;
            for (byte[] possibility : possibilities)
            {
                if (possibility[i] == 1) black = true;
                if (possibility[i] == 0) white = true;
                if (black && white) break;
            }

            //Merge into output
            if (black && white) continue;
            else if (black) output[i] = 1;
            else if (white) output[i] = 0;
        }

        //Return
        boolean solved = !Contains(output, (byte)2);
        return new Tuple<>(output, solved);
    }

    private boolean Contains(byte[] input, byte value) {
        boolean contains = false;
        for(byte x : input) {
            if (x == value) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    public List<int[]> Combinations(int buckets, int pebbles)
    {
        return Combinations(new ArrayList<int[]>() {{ add(new int[0]); }}, buckets, pebbles);
    }

    private List<int[]> Combinations(List<int[]> combinations,
                                     int buckets, int pebbles)
    {
        List<int[]> newCombinations = new ArrayList<int[]>();

        if (buckets == 1)
        {
            for (int[] combination : combinations)
            {
                int[] newCombination = new int[combination.length + 1];
                for (int i = 0; i < combination.length; i++) newCombination[i] = combination[i];
                newCombination[combination.length] = pebbles;
                newCombinations.add(newCombination);
            }
        }
        else
        {
            for (int p = 0; p <= pebbles; p++)
            {
                List<int[]> tempCombinations = new ArrayList<int[]>();

                for (int[] combination : combinations)
                {

                    int[] newCombination = new int[combination.length + 1];
                    for (int i = 0; i < combination.length; i++) newCombination[i] = combination[i];

                    newCombination[combination.length] = p;
                    tempCombinations.add(newCombination);
                }
                newCombinations.addAll(Combinations(tempCombinations, buckets - 1, pebbles - p));
            }
        }
        return newCombinations;
    }
}