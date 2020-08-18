package nonogram;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NonogramTests {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void GCHQ()
    {
        Nonogram nonogram = new Nonogram();
        byte[][] result = nonogram.Solve(new byte[][] {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, },
            { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, },
            { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },
        },
        new int[][] {
            new int[]{ 7,2,1,1,7 },
            new int[]{ 1,1,2,2,1,1 },
            new int[]{ 1,3,1,3,1,3,1,3,1 },
            new int[]{ 1,3,1,1,5,1,3,1 },
            new int[]{ 1,3,1,1,4,1,3,1 },
            new int[]{ 1,1,1,2,1,1 },
            new int[]{ 7,1,1,1,1,1,7 },
            new int[]{ 1,1,3 },
            new int[]{ 2,1,2,1,8,2,1 },
            new int[]{ 2,2,1,2,1,1,1,2 },
            new int[]{ 1,7,3,2,1 },
            new int[]{ 1,2,3,1,1,1,1,1 },
            new int[]{ 4,1,1,2,6 },
            new int[]{ 3,3,1,1,1,3,1 },
            new int[]{ 1,2,5,2,2 },
            new int[]{ 2,2,1,1,1,1,1,2,1 },
            new int[]{ 1,3,3,2,1,8,1 },
            new int[]{ 6,2,1 },
            new int[]{ 7,1,4,1,1,3 },
            new int[]{ 1,1,1,1,4 },
            new int[]{ 1,3,1,3,7,1 },
            new int[]{ 1,3,1,1,1,2,1,1,4 },
            new int[]{ 1,3,1,4,3,3 },
            new int[]{ 1,1,2,2,2,6,1 },
            new int[]{ 7,1,3,2,1,1 },
        },

        new int[][] {
            new int[]{ 7,3,1,1,7 },
            new int[]{ 1,1,2,2,1,1 },
            new int[]{ 1,3,1,3,1,1,3,1 },
            new int[]{ 1,3,1,1,6,1,3,1 },
            new int[]{ 1,3,1,5,2,1,3,1 },
            new int[]{ 1,1,2,1,1 },
            new int[]{ 7,1,1,1,1,1,7 },
            new int[]{ 3,3 },
            new int[]{ 1,2,3,1,1,3,1,1,2 },
            new int[]{ 1,1,3,2,1,1 },
            new int[]{ 4,1,4,2,1,2 },
            new int[]{ 1,1,1,1,1,4,1,3 },
            new int[]{ 2,1,1,1,2,5 },
            new int[]{ 3,2,2,6,3,1 },
            new int[]{ 1,9,1,1,2,1 },
            new int[]{ 2,1,2,2,3,1 },
            new int[]{ 3,1,1,1,1,5,1 },
            new int[]{ 1,2,2,5 },
            new int[]{ 7,1,2,1,1,1,3 },
            new int[]{ 1,1,2,1,2,2,1 },
            new int[]{ 1,3,1,4,5,1 },
            new int[]{ 1,3,1,3,10,2 },
            new int[]{ 1,3,1,1,6,6 },
            new int[]{ 1,1,2,1,1,2 },
            new int[]{ 7,2,1,2,5 },
            }
        );

        for(int i = 0; i < 25; i++)
        {
            for (int j = 0; j < 25; j++)
            {
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
        //Assert.Pass();
    }

    @Test
    public void BruteForce_1()
    {
        Nonogram nonogram = new Nonogram();
        List<byte[]> result = nonogram.Possibilities(
                new int[] { 3, 1, 1, 2 }, 10 );
        assertEquals(1, result.size());
        assertTrue(ArrayEquals(result.get(0),
                new byte[] { 1, 1, 1, 0, 1, 0, 1, 0, 1, 1 } ));
    }

    @Test
    public void BruteForce_2()
    {
        Nonogram nonogram = new Nonogram();
        List<byte[]> result = nonogram.Possibilities(
                new int[] { 2, 1, 1, 2 }, 10);
        assertEquals(5, result.size());
    }

    boolean ArrayEquals(byte[] array1, byte[] array2)
    {
        if (array1.length != array2.length) return false;
        for(int i = 0; i < array1.length; i++)
        {
            if (array1[i] != array2[i]) return false;
        }

        return true;
    }

    @Test
    public void Combinations()
    {
        Nonogram nonogram = new Nonogram();
        List<int[]>  result = nonogram.Combinations(5, 5);
        assertEquals(126, result.size());
    }
}