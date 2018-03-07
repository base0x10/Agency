package com.josephespy.Algo;

/*
 * Implements an interface for turn based, deterministic, zero sum games with finite, discrete action sets
 * a object of a type that implements ZeroSumGame should always be an immutable type
*/

import java.util.HashSet;

public interface ZeroSumGame {

    // 1 is a sure win for the moving player, -1 is a sure loss for the moving player, 0 is no advantage
    float heuristicObjective();

    // a parallelizable string of moves that moving player could legally make
    HashSet<String> getMoveSet();

    // Game that results from a legal move on the current game, returned game has different moving player as current
    ZeroSumGame apply(String move);

}
