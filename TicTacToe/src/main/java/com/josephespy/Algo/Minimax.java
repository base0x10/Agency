package com.josephespy.Algo;

import java.util.HashSet;
import java.util.Stack;
import java.util.stream.Collectors;

public class Minimax {

    public MinimaxRet miniMax(ZeroSumGame board, int depth, Stack<String> moveStack) {

        HashSet<String> moveSet= board.getMoveSet();

        if (moveSet.size() == 0) {

        }

        moveSet.parallelStream()
                // todo implement stack of past moves
                .map(m -> miniMax(board.apply(m), depth - 1, (Stack) null))
                .collect(Collectors.toCollection(HashSet::new));

        // todo use MinimaxRet type
        return null;
    }

}
