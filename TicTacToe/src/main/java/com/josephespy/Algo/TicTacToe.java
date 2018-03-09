package com.josephespy.Algo;

import java.util.Arrays;
import java.util.Stack;

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

    // The following is the heuristic function I am using
    // A run of length 'm' with only the character \0 provides no advantage to either player
    // A run of length 'm' where both players have a character in the run provides no advantage
    // A run of length 'm' where there are n 'X's provides 2^(n+1) to 'X' and -(2^(n+1)) to 'O'
    // every run of length 'm' contributes to the heuristic exactly once
    @Override
    public long heuristicObjective(int player) {

        int winner = this.hasWon();
        if (winner != -1) {
            if (winner == player) {
                return Long.MAX_VALUE - 1;
            }
            else {
                return Long.MIN_VALUE + 1;
            }
        }

        assert(m > 0);
        // row, col, and temp offsets into the array
        int i, j, k;

        // store how many times each has been seen
        int numX, numO;
        long util = 0;

        for (i = 0 ; i < n ; i++) {
            for (j = 0 ; j < n ; j++) {

                // try horizontal (right) run
                if (j <= n - m) {

                    numX = 0;
                    numO = 0;

                    for (k = 0 ; k < m ; k++) {
                        switch (arr[i][j+k]) {
                            case '\0':
                                break;
                            case 'X': numX++;
                                break;
                            case 'O': numO++;
                                break;
                            default: assert(false); // invalid board values
                                break;
                        }
                    }

                    // unusual order of conditions is so that I do less checking on common cases
                    if ( (numX > 0 && numO > 0) || (numO == 0 && numX == 0) ) {
                        // do nothing
                    } else if (numO > 0) {
                        if (player == 'O') {
                            // util added to 2^numO
                            util += 2 << numO;
                        } else {
                            util -= 2 << numO;
                        }
                    } else if (numX > 0) {
                        if (player == 'X') {
                            util += 2 << numX;
                        } else {
                            util -= 2 << numX;
                        }
                    } else {
                        // should be unreachable
                        assert(false);
                    }

                }
                // try vertical (down) run
                if (i <= n - m) {
                    numX = 0;
                    numO = 0;

                    for (k = 0 ; k < m ; k++) {
                        switch (arr[i+k][j]) {
                            case '\0':
                                break;
                            case 'X': numX++;
                                break;
                            case 'O': numO++;
                                break;
                            default: assert(false); // invalid board values
                                break;
                        }
                    }

                    // unusual order of conditions is so that I do less checking on common cases
                    if ( (numX > 0 && numO > 0) || (numO == 0 && numX == 0) ) {
                        // do nothing
                    } else if (numO > 0) {
                        if (player == 'O') {
                            // util added to 2^numO
                            util += 2 << numO;
                        } else {
                            util -= 2 << numO;
                        }
                    } else if (numX > 0) {
                        if (player == 'X') {
                            util += 2 << numX;
                        } else {
                            util -= 2 << numX;
                        }
                    } else {
                        // should be unreachable
                        assert(false);
                    }

                }
                // try down and right run
                if (i <= n - m && j <= n - m) {

                    numX = 0;
                    numO = 0;

                    for (k = 0 ; k < m ; k++) {
                        switch (arr[i+k][j+k]) {
                            case '\0':
                                break;
                            case 'X': numX++;
                                break;
                            case 'O': numO++;
                                break;
                            default: assert(false); // invalid board values
                                break;
                        }
                    }

                    // unusual order of conditions is so that I do less checking on common cases
                    if ( (numX > 0 && numO > 0) || (numO == 0 && numX == 0) ) {
                        // do nothing
                    } else if (numO > 0) {
                        if (player == 'O') {
                            // util added to 2^numO
                            util += 2 << numO;
                        } else {
                            util -= 2 << numO;
                        }
                    } else if (numX > 0) {
                        if (player == 'X') {
                            util += 2 << numX;
                        } else {
                            util -= 2 << numX;
                        }
                    } else {
                        // should be unreachable
                        assert(false);
                    }


                }
                // try up and right run
                if (j <= n - m && i + 1 >= m) {
                    numX = 0;
                    numO = 0;

                    for (k = 0 ; k < m ; k++) {
                        switch (arr[i-k][j+k]) {
                            case '\0':
                                break;
                            case 'X': numX++;
                                break;
                            case 'O': numO++;
                                break;
                            default: assert(false); // invalid board values
                                break;
                        }
                    }

                    // unusual order of conditions is so that I do less checking on common cases
                    if ( (numX > 0 && numO > 0) || (numO == 0 && numX == 0) ) {
                        // do nothing
                    } else if (numO > 0) {
                        if (player == 'O') {
                            // util added to 2^numO
                            util += 2 << numO;
                        } else {
                            util -= 2 << numO;
                        }
                    } else if (numX > 0) {
                        if (player == 'X') {
                            util += 2 << numX;
                        } else {
                            util -= 2 << numX;
                        }
                    } else {
                        // should be unreachable
                        assert(false);
                    }

                }
            }
        }

        return util;
    }


    @Override
    public Stack<String> getMoveSet() {

        Stack<String> moveSet = new Stack<>();

        // add to set if position is empty
        for (int i = 0 ; i < n ; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == '\0') {
                    moveSet.add(Integer.toString(i) + "," + Integer.toString(j));
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

        assert(move != null);
        String[] coordinates = move.split(",");

        assert(coordinates.length == 2);

        int row = Integer.parseInt(coordinates[0]);
        int col = Integer.parseInt(coordinates[1]);

        // bounds check
        assert(0 <= row && row < n);
        assert(0 <= col && col < n);

        // must be passed legal move
       if (arr[row][col] != '\0') {
           System.out.println(this.toString());
           assert(false);
       }

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

    @Override
    public int getPlayer() {
        return (int) mover;
    }

    // player X goes after O and vice versa
    private static char getNextPlayer(char player) {
        return player == 'X' ? 'O' : 'X';
    }

    public int hasWon(){

        // row, col, and temp offsets into the array
        int i, j, k;

        // store how many times each has been seen
        int numX, numO;

        for (i = 0 ; i < n ; i++) {
            for (j = 0 ; j < n ; j++) {

                // try horizontal (right) run
                if (j <= n - m) {

                    numX = 0;
                    numO = 0;

                    for (k = 0 ; k < m ; k++) {
                        switch (arr[i][j+k]) {
                            case '\0':
                                break;
                            case 'X': numX++;
                                break;
                            case 'O': numO++;
                                break;
                            default: assert(false); // invalid board values
                                break;
                        }
                    }

                    if (numX == m) {
                        return 'X';
                    } else if (numO == m) {
                        return 'O';
                    }

                }
                // try vertical (down) run
                if (i <= n - m) {
                    numX = 0;
                    numO = 0;

                    for (k = 0 ; k < m ; k++) {
                        switch (arr[i+k][j]) {
                            case '\0':
                                break;
                            case 'X': numX++;
                                break;
                            case 'O': numO++;
                                break;
                            default: assert(false); // invalid board values
                                break;
                        }
                    }

                    if (numX == m) {
                        return 'X';
                    } else if (numO == m) {
                        return 'O';
                    }


                }
                // try down and right run
                if (i <= n - m && j <= n - m) {

                    numX = 0;
                    numO = 0;

                    for (k = 0 ; k < m ; k++) {
                        switch (arr[i+k][j+k]) {
                            case '\0':
                                break;
                            case 'X': numX++;
                                break;
                            case 'O': numO++;
                                break;
                            default: assert(false); // invalid board values
                                break;
                        }
                    }

                    if (numX == m) {
                        return 'X';
                    } else if (numO == m) {
                        return 'O';
                    }



                }
                // try up and right run
                if (j <= n - m && i + 1 >= m) {
                    numX = 0;
                    numO = 0;

                    for (k = 0 ; k < m ; k++) {
                        switch (arr[i-k][j+k]) {
                            case '\0':
                                break;
                            case 'X': numX++;
                                break;
                            case 'O': numO++;
                                break;
                            default: assert(false); // invalid board values
                                break;
                        }
                    }

                    if (numX == m) {
                        return 'X';
                    } else if (numO == m) {
                        return 'O';
                    }


                }
            }
        }

        return -1;
    }

}