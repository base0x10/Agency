package com.josephespy.Algo;


import java.util.Stack;

public class VirtualGame {

    // virtual games are nodes in the game tree.  Only the real game (root) has null parent
    public VirtualGame parent;

    // depth of 0 is root, etc
    public int depth;
    // every virtual game besides the root has a move that was taken to get there
    public String move;

    // the possible game that this virtual game represents
    public ZeroSumGame game;
    // generated at construction, is set of moves yet to be check that can be found from this state
    private Stack<String> moveSet;

    // As the tree is explored, these values update
    // A child is the only node that updates these, and only when its subtree is entire explored
    private long bestUtil;
    public String bestMove;
    private ZeroSumGame bestGame;

    // takes board of parent and does its own application to create its board
    public VirtualGame(ZeroSumGame parentBoard,VirtualGame parent, String move) {

        assert(parentBoard != null && parent != null && move != null);
        this.parent = parent;
        this.move = move;
        game = parentBoard.apply(move);

        moveSet = game.getMoveSet();
        bestUtil = Long.MIN_VALUE;
        bestMove = null;
        bestGame = null;

        depth = parent.depth + 1;
    }

    // only for use in constructing root of tree
    public VirtualGame(ZeroSumGame board) {
        parent = null;
        move = null;
        game = board;
        depth = 0;
        moveSet = board.getMoveSet();
        bestUtil = Long.MIN_VALUE;
        bestMove = null;
        bestGame = null;
    }

    // called by children, it checks if a child's resultant game is current best option
    // game passed to it is
    public void reportGame(String move, ZeroSumGame terminalGame) {
        assert(this.game != null && terminalGame != null);
        long util = terminalGame.heuristicObjective(this.game.getPlayer());
        if ( util > bestUtil) {
            bestUtil = util;
            bestMove = move;
            bestGame = terminalGame;
        }
    }

    public boolean hasChild() {
        return !moveSet.isEmpty();
    }

    public ZeroSumGame getBestGame() {
        return bestGame;
    }

    public VirtualGame getChild() {
        String move = moveSet.pop();
        assert(move != null);
        return new VirtualGame(this.game, this, move);
    }
}
