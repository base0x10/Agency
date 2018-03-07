package com.josephespy.Algo;

import java.util.Arrays;
import java.util.HashSet;

public class TicTacToe implements ZeroSumGame {

    final private int n;
    final private int m;
    final private char arr[][];
    final private char mover;

    final static char FIRST_MOVER = 'X';

    public TicTacToe(int dim, int winLen) {

        n = dim;
        m = winLen;
        arr = new char[n][n];

        mover = FIRST_MOVER;
    }

    // private constructor, to be called by apply(), and any future modifiers (TicTacToe is immutable)
    private TicTacToe(char[][] arr, int winLen, char nextMover){
        n = arr.length;
        m = winLen;
        this.arr = arr;
        mover = nextMover;
    }


    // we care about the number of runs of length m
    // where the only chars are one player's and '\0'
    @Override
    public float heuristicObjective() {

        // not fully implemented
        assert(false);
        int Xruns = 0;

        for (int i = 0 ; i < n ; i++) {
            for (int j = 0 ; j < n ; j++) {
                // try horizontal run
                if(j < n - m){
                    assert(false);
                }
            }
        }


        assert( false ); // unimplemented
        return 0;
    }


    @Override
    public HashSet<String> getMoveSet() {

        HashSet<String> moveSet = new HashSet<>();

        // add to set if position is empty
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == '\0') {
                    moveSet.add(Integer.toString(i) + "," + Integer.toString(i));
                }
            }
        }

        return moveSet;
    }


    /*
     * apply is only guaranteed to work with a result from getMoveStream()
     */
    @Override
    public TicTacToe apply(String move) {

        String[] coordinates = move.split(",");

        assert(coordinates.length == 2);

        int row = Integer.parseInt(coordinates[0]);
        int col = Integer.parseInt(coordinates[1]);

        // bounds check
        assert(0 <= row && row < n);
        assert(0 <= col && col < n);

        // must be passed legal move
        assert(arr[row][col] == '\0');

        // create deep copy of arr for new game
        char[][] newArr = new char[n][n];
        for (int i = 0 ; i < n ; i++) {
            newArr[i] = Arrays.copyOf(arr[i], n);
        }

        newArr[row][col] = mover;

        return new TicTacToe(newArr, m, getNextPlayer(mover));
    }

    // very slow, only use for debugging
    @Override
    public String toString() {
        String res = "";
        for(char[] e: arr) {
            res = res + Arrays.toString(e) + '\n';
        }
        return res;
    }

    // player X goes after O and vice versa
    private static char getNextPlayer(char player) {
        return player == 'X' ? 'O' : 'X';
    }

}