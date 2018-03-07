package com.josephespy.Algo;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        System.out.println("~~~~TESTING BEGAN~~~~");

        TicTacToe test = new TicTacToe(12, 8);
        System.out.println("The initial 12 * 12 board:\n"+test.toString());

        TicTacToe step1 = test.apply("0,0");
        System.out.println("The board after first player chooses \"0,0\":\n"+step1.toString());

        TicTacToe step2 = step1.apply("11,11");
        System.out.println("The board after first player chooses \"11,11\":\n"+step2.toString());

        System.out.println("~~~~TESTING COMPLETED~~~~");
        assertTrue( true );
    }
}
