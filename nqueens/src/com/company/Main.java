package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("n-queen.txt"));

            String line = br.readLine();
            int n = (line.length() + 1 )/ 2;
            boolean board[][] = new boolean[n][n];
            int j = 0;

            do {
                for (int i = 0 ; i < n ; i++) board[j][i] = (line.charAt(2 * i) == '1');
                j++;
            } while ((line = br.readLine()) != null);
            board = solveNQueens(board);
            System.out.println(Arrays.deepToString(board));

        } catch(FileNotFoundException ex) {

            System.out.println("This needs to be run with a file named n-queens.txt in the same directory");

        } catch (IOException ex) {
            System.out.println("Threw an IO exception");
        }

    }

    public static boolean[][] solveNQueens(boolean[][] board) {

        Queue<boolean[][]> toEvaluate = new ArrayDeque<>();
        toEvaluate.add(board);
        Set<Integer> evaluatedNodes = new HashSet<Integer>();

        /*
            basic structure of my program
             - while there are nodes to expand and we have not found a solution
                - evaluate the node at the top of the queue
                    - find all the reachable states from it
                        - for each reachable state, first check if it is a solution
                        - next add it to the stack
        */

        while (!toEvaluate.isEmpty()) {
            System.out.println("Evaluating a new node, number of nodes in queue is: " + toEvaluate.size());

            // remove the front of the queue
            board = toEvaluate.remove();
            evaluatedNodes.add(Arrays.deepHashCode(board));


            // find all of the reachable boards that have not been searched yet
            reachableBoards iter = new reachableBoards(board);
            boolean[][] e;

            while (iter.hasNext) {
                e = iter.next();
                if (evaluatedNodes.contains(Arrays.deepHashCode(e))){
                    continue;
                }
                // if they are a solution, we are done
                if (isSolved(e)) return e;
                toEvaluate.add(e);
            }


        }
        System.out.println("no solution");
        return null;
    }

    public static boolean isSolved(boolean[][] board) {
        boolean foundOne = false;

        // for each column
        for (int i = 0 ; i < board.length ; i++) {

            // check rows
            for (int j = 0 ; j < board.length ; j++) {
                if (board[i][j] && foundOne) return false;
                else if (board[i][j]) foundOne = true;
            }
            foundOne = false;

            // check columns

        }
        return true;
    }

    // iterator class which goes through all of the boards reachable in one move from a src board
    static class reachableBoards implements Iterator {

        boolean[][] src;
        int row;
        int col;
        boolean hasNext;

        public reachableBoards(boolean[][] board) {
            src = board;
            if (board[0][0]) {
                row = 1;
            } else {
                row = 0;
            }
            col = 0;
            hasNext = true;
        }

        @Override
        public boolean hasNext() {
            // when the next value to altered is past
            return hasNext;
        }

        @Override
        public boolean[][] next() {

            // if we are in the last column and (last row or second to last but src in last row is true) we have no more
            if (col == src.length - 1) {
                if (row == src.length - 2 || src[src.length - 1][src.length - 1]) hasNext = false;
            }

            boolean[][] newBoard = new boolean[src.length][src.length];

            // make a deep copy of src that differs only in row and col
            for (int i = 0 ; i < src.length - 1 ; i++) {
                for (int j = 0 ; j < src.length ; j++) {
                    if (j != col) {
                        newBoard[i][j] = src[i][j];
                    } else if (i == row) {
                        newBoard[i][j] = true;
                    } else if (j != row) {
                        newBoard[i][j] = false;
                    }
                }
            }

            // increment row unless we are at the end of a column
            if (row == src.length - 1) {
                row = 0;
                col++;
            } else {
                row++;
            }
            /*
            // if the next spot to be checked has a queen, increment again
            while (hasNext && src[row][col]) {
                if (row == src.length - 1) {
                    row = 0;
                    col++;
                } else {
                    row++;
                }
            }*/
            return newBoard;
        }
    }
}
