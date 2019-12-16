package main;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParametersTest {

    @Test
    public void testParameters(){
        int[][] boardSizes = new int[2][2];
        boardSizes[0] = new int[]{9, 9};
        boardSizes[1] = new int[]{13,13};
        GameParameters params1 = new GameParameters(true,boardSizes[0]);
        GameParameters params2 = new GameParameters(true,boardSizes[0]);
        GameParameters params3 = new GameParameters(false,boardSizes[1]);
        Object object = new Object();
        assertNotEquals(params1,object);
        GameParameters badParams = new GameParameters(true,new int[]{1});
        assertNotEquals(params1,badParams);
        GameParameters params4 = new GameParameters(true,new int[]{9,4});
        assertNotEquals(params1,params4);
        assertEquals(params1,params2);
        assertNotEquals(params1,params3);
    }
}
