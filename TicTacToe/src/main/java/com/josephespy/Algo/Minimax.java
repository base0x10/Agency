package com.josephespy.Algo;

public class Minimax {

    // a non-recursive minimax implementation
    // board is a current state, not some future possible state (no recursion needed)
    // depth is number of rational moves (not number of move cycles) to go
    public static String miniMax(ZeroSumGame board, int maxDepth) {

        VirtualGame cur = new VirtualGame(board);

        while (cur.hasChild() || cur.parent != null) {
            // move up the tree when out of moves
            if (cur.depth == maxDepth || (!cur.hasChild() && cur.getBestGame() == null)) {
                assert(cur.game != null);
                cur.parent.reportGame(cur.move, cur.game);
                cur = cur.parent;
                continue;
            } else if (!cur.hasChild()) {
                assert(cur.getBestGame() != null);
                cur.parent.reportGame(cur.move, cur.getBestGame());
                cur = cur.parent;
                continue;
            }
            cur = cur.getChild();
        }

        assert(cur.bestMove != null);
        // cur is root board now
        return cur.bestMove;
    }

}
