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

        boolean demo = false;
        if (demo) {
            String move;
            TicTacToe test = new TicTacToe(5, 4);

            while (true) {
                move = Minimax.miniMax(test, 5);
                System.out.println(move+"\n");
                test = test.apply(move);
                if (test.hasWon() != -1 || test.getMoveSet().isEmpty()) {
                    break;
                }
                System.out.println(test.toString());
            }

        }

        boolean playGame = true;

        if (playGame) {

            int dim = 5;
            int winLen = 5;
            String move;

            TicTacToe board = new TicTacToe(dim, winLen);

            // while playing uncomment this and then add the moves actually made
            board = board.apply("2,2");
            board = board.apply("4,4");
            board = board.apply("4,3");
            board = board.apply("3,2");
            board = board.apply("3,4");
            board = board.apply("2,3");
            board = board.apply("1,1");
            board = board.apply("3,1");
            board = board.apply("4,2");

            System.out.println(board.toString());




            for (int i = 2 ; i<10 ; i++) {
                move = Minimax.miniMax(board, i);
                System.out.println("depth: "+i+" move: " + move);
            }
        }

        /* TicTacToe test = new TicTacToe(12, 8);
        System.out.println("The initial 12 * 12 board:\n"+test.toString());
        assert(test.heuristicObjective(test.getPlayer()) == 0);

        TicTacToe step1 = test.apply("0,0");
        System.out.println("The board after first player chooses \"0,0\":\n"+step1.toString());
        assert(step1.heuristicObjective(step1.getPlayer()) == -12);


        TicTacToe step2 = step1.apply("11,11");
        System.out.println("The board after first player chooses \"11,11\":\n"+step2.toString());
        assert(step2.heuristicObjective(step2.getPlayer()) == 0);


        System.out.println("~~~~TESTING COMPLETED~~~~");
        assertTrue( true );
        */
    }
}
