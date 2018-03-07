package com.josephespy.Algo;

import java.util.Stack;

public class MinimaxRet {
    final public float util;
    final public Stack<String> moveList;

    MinimaxRet(float util, Stack<String> moveList) {
        this.moveList = moveList;
        this.util = util;
    }
}
